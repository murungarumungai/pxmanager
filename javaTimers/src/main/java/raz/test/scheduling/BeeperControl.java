package raz.test.scheduling;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

;

/**
 * http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/ScheduledExecutorService.html
 * */

public class BeeperControl {

	private final ScheduledExecutorService scheduler = Executors
			.newScheduledThreadPool(1);

	private class Beeper implements Runnable {

		public void run() {
			System.out.println(Thread.currentThread()+" running Task:"+this);
			System.out.println("beep");
		}

	}

	private class TaskCancelator implements Runnable {
		
		ScheduledFuture scheduledTask;
		
		public TaskCancelator(ScheduledFuture param){
			scheduledTask = param;
		}
		
		public void run() {
			System.out.println(Thread.currentThread()+" Canceling Task:"+scheduledTask);
			scheduledTask.cancel(true);
		}
	}

	public void beepForSpecifiedTime(long keepAlive) {

		final Beeper beeper = new Beeper();

		long initialDelay = 1;
		long repeatPeriod = 5;

		final ScheduledFuture<?> beepHandle = scheduler.scheduleAtFixedRate(
				beeper, initialDelay, repeatPeriod, SECONDS);

		// cancel the task after one hour has expired
		scheduler.schedule(new TaskCancelator(beepHandle), keepAlive, SECONDS);
		
		
		
	}

	public static void main(String[] args) {
		BeeperControl beepControl = new BeeperControl();
		beepControl.beepForSpecifiedTime(30);
		//wait for any ongoing tasks that are executed to terminate and then shut-down
				//scheduler.shutdown();
	}

}

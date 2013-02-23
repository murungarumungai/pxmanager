package raz.test.RazvanExecutor;

import java.util.concurrent.Executor;

/**
 * http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/Executor.html
 * 
 * More typically, tasks are executed in some thread other than the caller's
 * thread. The executor below spawns a new thread for each task.
 * */
public class ThreadPerTaskExecutor implements Executor {

	public void execute(Runnable command) {
		new Thread(command).start();
	}
	
	public static void main(String[] args){
		
		Runnable task = new Runnable() {
			
			public void run() {
				System.out.println(Thread.currentThread() + " beep");
			}
		};
		
		ThreadPerTaskExecutor simpleExecutor = new ThreadPerTaskExecutor();
		System.out.println(Thread.currentThread()+"Calling a task");
		simpleExecutor.execute(task);
	}
	
}

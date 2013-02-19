package raz.test.RazvanExecutor;

import java.util.concurrent.Executor;

public class DirectExecutor  implements Executor{

	public void execute(Runnable command) {
		command.run();
	}
	
	public static void main(String[] args){
		
		Runnable task = new Runnable() {
			
			public void run() {
				System.out.println(Thread.currentThread() + " beep");
			}
		};
		
		DirectExecutor directExecutor = new DirectExecutor();
		System.out.println(Thread.currentThread()+"Calling a task");
		directExecutor.execute(task);
	}

}



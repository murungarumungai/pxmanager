package java_book;

/**
 * The easiest way to create a thread is to create a class that implements the
 * <code>Runnable</code> interface. A class needs only implement a single method
 * called <code>run()</code> Inside run you will define the code that
 * constitutes the thread. It is important to understand that <code>run()</code>
 * can call other methods, use other classes, and declare variables, just like
 * the main thread can. The only difference is the <code>run()</code>
 * establishes the entry point for another, concurrent thread of execution
 * within your program. This thread will end when <code>run()</code> returns.
 * */

public class ThreadDemo {

	public static void main(String args[]) {
		new NewThread();
		
		
		try{
			for(int i =5; i>0; i--){
				System.out.println("Main thread:"+i);
				Thread.sleep(1000);
			}
		} catch(InterruptedException e){
			System.out.println("Main Thread interupted");
		}
		System.out.println("Main Thread Exiting");
	}

	static class NewThread implements Runnable {

		Thread t;

		public NewThread() {
			// Create a new, second thread
			t = new Thread(this, "Demo Thread");
			System.out.println("instantiating: " + t);
			t.start();//
		}

		@Override
		public void run() {
			try {
				for (int i = 5; i > 0; i--) {
					System.out.println("Child thread:" + i);
					Thread.sleep(500);
				}
			} catch (InterruptedException e) {
				System.out.println("Child interupted");
			}
			System.out.println("Exiting child thread");
		}

	}

}

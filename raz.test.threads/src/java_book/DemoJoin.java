package java_book;

public class DemoJoin {
	
	
	
	public static void main(String[] args){
		
		NewThread ob1 = new NewThread("One");
		NewThread ob2 = new NewThread("Two");
		NewThread ob3 = new NewThread("Three");
		
		System.out.println("Thread "+ob1.name+" is alive: "+ob1.t.isAlive());
		System.out.println("Thread "+ob2.name+" is alive: "+ob2.t.isAlive());
		System.out.println("Thread "+ob3.name+" is alive: "+ob3.t.isAlive());
		
		try{
			System.out.println(Thread.currentThread()+"waiting for the threads to finis");
			/*
			 * Method waits until the thread on which is called terminates.
			 * It names comes from the concept of the calling thread waiting until the specified thread joins in.
			 * */
			ob1.t.join();
			ob2.t.join();
			ob3.t.join();
		} catch (InterruptedException e){
			System.out.println("Main Thread interupted");
		}
		
		System.out.println("Thread "+ob1.name+" is alive: "+ob1.t.isAlive());
		System.out.println("Thread "+ob2.name+" is alive: "+ob2.t.isAlive());
		System.out.println("Thread "+ob3.name+" is alive: "+ob3.t.isAlive());
		
		System.out.println("Main Thread exiting");
		
		
	}
	

	static class NewThread implements Runnable {

		String name;
		Thread t;

		NewThread(String threadName) {
			name = threadName;
			t = new Thread(this, name);
			System.out.println("instantiating:" + t);
			t.start();
		}

		@Override
		public void run() {
			try {
				for (int i = 5; i > 0; i--) {
					System.out.println(name + ":" + i);
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				System.out.println(name + "interrupted");
			}
			System.out.println(name + " exiting");
		}

	}
	
	
}

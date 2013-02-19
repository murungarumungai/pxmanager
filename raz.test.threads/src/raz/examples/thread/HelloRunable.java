package raz.examples.thread;

public class HelloRunable  implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread()+"Hello from a thread");
		
	}
	
	public static void main(String[] args){
		(new Thread(new HelloRunable())).start();
	}

}

package java_book.synch;

public class CallMe {
	
	public void call(String msg){
		System.out.println(Thread.currentThread());
		System.out.println("["+msg);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("interrupted");
		}
		System.out.println("]");
	}

}

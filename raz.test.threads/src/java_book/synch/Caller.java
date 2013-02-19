package java_book.synch;

public class Caller implements Runnable{
	
	CallMe target;
	String msg;
	Thread t;
	
	public Caller(CallMe pTarget, String s){
		target = pTarget;
		msg = s;
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		target.call(msg);	
	}

}

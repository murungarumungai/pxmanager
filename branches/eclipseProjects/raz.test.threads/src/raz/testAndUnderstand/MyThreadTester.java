package raz.testAndUnderstand;

public class MyThreadTester {

	
	public static void main(String[] args){
		MyThread test = new MyThread();
		test.start();
		test.modify(1);
	}
	
}

class MyThread extends Thread {
	
	private int field_1 = 0;
	private int field_2 = 0;
	
	
	@Override
	public void run() {
		super.run();
		//setDaemon(true);// this thread will not keep the app alive
		
		while(true){
			System.out.println("["+Thread.currentThread().getName()+"]"+" field_1="+field_1+
					" field_2=" + field_2 );
			
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	synchronized public void modify( int new_value ){
		System.out.println("["+Thread.currentThread().getName()+"] - setting new value:"+new_value);
		field_1 = new_value;
        field_2 = new_value;
	}
	
}

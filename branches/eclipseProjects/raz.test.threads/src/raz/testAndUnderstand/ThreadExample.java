package raz.testAndUnderstand;

import static raz.callable.RazLongCalculationSubThread.debugThreadInfo;

public class ThreadExample extends Thread{
	
	int count;
	
	public ThreadExample(int i){
		this.count = i;
	}

	@Override
	public void run() {
		
		for(int i=1; i<4; i++){
			count = count + 1;			
		}
		System.out.println("["+Thread.currentThread().getName()+"]"+count);		
	}
	
	public static void main(String[] args){
		int x  = 0;
		do{
			//ThreadExample t = new ThreadExample(10);
			//t.start();
			//System.out.println(t.count);
			new ThreadExample(10).start();
			x++;
		} while(x<10);
		
	}
}

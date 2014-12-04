package raz.testAndUnderstand;

public class ThreadExample extends Thread{
	
	int count;
	
	public ThreadExample(int i){
		this.count = i;
	}

	@Override
	public void run() {
		super.run();
		for(int i=1; i<4; i++){
			count = count + i;
		}
		
	}
	
	public static void main(String[] args){
		int x  = 0;
		do{
			ThreadExample t = new ThreadExample(10);
			t.start();
			System.out.println(t.count);
			x++;
		} while(x<10);
		
	}
}

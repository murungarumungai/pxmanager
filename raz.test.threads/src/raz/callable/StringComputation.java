package raz.callable;

/**
 * this is a computation that takes long time to complete
 * it return the length of a String.
 * The long running work in the <code>compute</code> method is done on another sub-tread
 * */
class StringComputation implements Runnable{
	private String word;
	private Integer result = null;
	Thread  t;
	
	public Integer getResult(){
		return result;
	}
	
	/**
	 * returns a reference to the ThreadObject which is performing this taks
	 * */
	public Thread getThread(){
		return t;
	}
	
	public StringComputation(String word){
		this.word = word;
		t = new Thread(this);
		t.start();
	}
	
	/**
	 * this method takes a lot of time(5 seconds) to process
	 * */
	private void compute(){
		result =  Integer.valueOf(word.length());
		//To simulate this, we use thread.sleep
		try {
			Thread.currentThread().sleep(5*RazLongCalculationSubThread.SECOND);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		RazLongCalculationSubThread.debugThreadInfo("Done, result:"+result);
	}

	//do the long running task on another thread
	@Override
	public void run() {
		compute();
		
	}
}
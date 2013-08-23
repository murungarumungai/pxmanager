package raz.callable;

/**
 * single thread synchronous call - the caller waits until the called method returns
 * */
public class RazLongCalculationSingleThread {
	
	public static long SECOND = 1000;
	
	/**
	 * this is a computation that takes long time to complete
	 * it return the length of a String
	 * */
	private static class StringComputation{
		private String word;
		private Integer result = null;
		public Integer getResult(){
			return result;
		}
		
		public StringComputation(String word){
			this.word = word;
		}
		
		/**
		 * this method takes a lot of time to process
		 * */
		public void compute(){
			result =  Integer.valueOf(word.length());
			//To simulate this, we use thread.sleep
			try {
				Thread.currentThread().sleep(5*SECOND);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			debugThreadInfo("Done, result:"+result);
		}
	}
	
	public static void main(String[] args){
		String[] words = {"test","bla"};
		for (String word: words) {
			debugThreadInfo("Calling long computation for:"+word);
			StringComputation task = new StringComputation(word);
			task.compute();
			//Do other stuff is blocked
			debugThreadInfo("MeanWhile... Doing other stuff");
			Integer result =  task.getResult();
			debugThreadInfo("Showing result computation:"+result);
			
		}
	}
	
	public static void debugThreadInfo(String message){
		String threadName = Thread.currentThread().getName();
		System.out.format("%s: %s%n", threadName, message);
	}

}

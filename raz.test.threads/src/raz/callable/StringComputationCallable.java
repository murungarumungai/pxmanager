package raz.callable;

import java.util.concurrent.Callable;

public class StringComputationCallable implements Callable<Integer> {

	private String word;

	public StringComputationCallable(String word) {
		this.word = word;
	}

	@Override
	public Integer call() throws Exception {
		
		return compute();
	}

	/**
	 * this method takes a lot of time to process
	 * */
	private Integer compute() {
		Integer result = null;
		result = Integer.valueOf(word.length());
		// To simulate this, we use thread.sleep
		try {
			Thread.sleep(5 * RazLongCalculationSubThread.SECOND);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		RazLongCalculationSubThread.debugThreadInfo("Done, result:" + result);
		return result;
	}

}

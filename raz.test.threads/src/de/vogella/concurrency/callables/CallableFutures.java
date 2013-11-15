package de.vogella.concurrency.callables;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import raz.callable.RazLongCalculationSubThread;

public class CallableFutures {

	private static final int NTHREDS = 10;

	public static void main(String[] args) {
		
		
		long sum1to20_000ASYNC = sumAsync();
		RazLongCalculationSubThread.debugThreadInfo("SUM:" + sum1to20_000ASYNC);
		
		long sum1to20_000 = sumOneThread();
		RazLongCalculationSubThread.debugThreadInfo("SUM:" + sum1to20_000);
		

	}
	
	public static long sumOneThread(){
		long start = System.currentTimeMillis();
		
		long rez = 0;
		
		for(int i = 0;i <= 20000; i++){
			rez=rez+i;
		}
		
		long execTime = System.currentTimeMillis() - start;
		RazLongCalculationSubThread.debugThreadInfo("Execution time sumOneThread:" + execTime);
		
		return rez;
	}

	private static long sumAsync() {

		long start = System.currentTimeMillis();
		
		ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);

		List<Future<Long>> list = new ArrayList<Future<Long>>();

		for (int i = 0; i < 200; i++) {
			// Devide the work into Callables
			Callable<Long> worker = new MyCallable(i);
			// submit the Callables to the ThreadPool
			Future<Long> submit = executor.submit(worker);
			// Keep a reference to the future result
			RazLongCalculationSubThread
			.debugThreadInfo("Submittied:"+ ((MyCallable) worker).getId() + "it shloud start doing it's work");
			list.add(submit);
		}
		long sum = 0;
		RazLongCalculationSubThread.debugThreadInfo(">>>>>" + list.size());
		// Now retrieve the result
		for (Future<Long> future : list) {
			try {
				long futureResult = future.get();
				RazLongCalculationSubThread
						.debugThreadInfo("I have got result from a worker:"
								+ futureResult);
				sum += futureResult;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		executor.shutdown();

		long execTime = System.currentTimeMillis() - start;
		RazLongCalculationSubThread.debugThreadInfo("Execution time sumAsync:" + execTime);
		
		return sum;
	}

}

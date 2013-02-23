package raz.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * single thread synchronous call - the caller waits until the called method returns
 * */
public class RazLongCalculationSubThread {
	
	static long SECOND = 1000;
	
	public static void main(String[] args){
		for (String word: args) {
			doWorkUsingCallable(word);
			//callAsyncButNoValidResult(word);
			//callerWaitsForTaskToFinishToGetTheResult(word);
		}
	}
	
	public static void callAsyncButNoValidResult(String word){
		debugThreadInfo("Calling long computation for:"+word);
		StringComputation task = new StringComputation(word);//The long computation is done async, on another thread
		
		//Do other stuff is not blocked anymore
		debugThreadInfo("MeanWhile... Doing other stuff which does not need the result calculated on the other thread");
		Integer result =  task.getResult();
		//but the problem is now, that we do not have access to a valid result.
		//And we need the result of the computation
		debugThreadInfo("Showing result computation:"+result);
	}
	
	public static void callerWaitsForTaskToFinishToGetTheResult(String word){
		debugThreadInfo("Calling long computation for:"+word);
		StringComputation task = new StringComputation(word);//The long computation is done async, on another thread
		
		/*
		 * If the other stuff that we need to do,
		 * depends on the result that is being
		 * calculated async on the other thread, we need to wait for that thread to finish,
		 * so we can be sure that we have a valid result
		 * */
		Thread subThread = task.getThread();
		
		try {
			subThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Do other stuff is not blocked anymore
		debugThreadInfo("MeanWhile... Doing other stuff with the result");
		Integer result =  task.getResult();
		//but the problem is now, that we do not have access to a valid result.
		//And we need the result of the computation
		debugThreadInfo("Showing result computation:"+result);
	}
	
	
	public static void doWorkUsingCallable(String word){
		
		StringComputationCallable task = new StringComputationCallable(word);
		debugThreadInfo("Calling long computation for:"+word);
		ExecutorService pool =  Executors.newSingleThreadExecutor();//TODO: u need to stop the ThreadPool
		Future<Integer> future = pool.submit(task);
		
		//Do other stuff is not blocked anymore
		debugThreadInfo("MeanWhile... Doing other stuff which does not need the result calculated on the other thread");
		
		try {
			Integer result = future.get();
			debugThreadInfo("MeanWhile... Doing other stuff with the result:"+result);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} catch (ExecutionException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void debugThreadInfo(String message){
		String threadName = Thread.currentThread().getName();
		System.out.format("%s: %s%n", threadName, message);
	}

}

package de.vogella.concurrency.callables;

//http://www.vogella.com/articles/JavaConcurrency/article.html#concurrency_volatile

import java.util.concurrent.Callable;

import raz.callable.RazLongCalculationSubThread;

/**
 * a unit of work which is executed an a Thread
 * The work that is does: it sums up until 100
 * */
public class MyCallable implements Callable<Long> {
	
	private final int id;
	
	public MyCallable(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}

	@Override
	public Long call() throws Exception {
		long sum = 0;
		for (long i = 0; i <= 100; i++) {
			sum += i;
			RazLongCalculationSubThread.debugThreadInfo("MyCallable:"+id+" adding:"+i);
		}
		return sum;
	}

}

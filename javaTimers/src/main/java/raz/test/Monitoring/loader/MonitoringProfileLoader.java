package raz.test.Monitoring.loader;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import raz.test.Monitoring.MonitoringProfile;


public class MonitoringProfileLoader {
	
	
	private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
			//Executors.newScheduledThreadPool(1);
	
	/**
	 * schedules a MonitoringProfileLoaderTask
	 * @param repeatPeriod in minutes
	 * 
	 * */
	public void sheduleTask(long repeatPeriod){
		
		MonitoringProfileLoaderTask task = new MonitoringProfileLoaderTask();
		
		ScheduledFuture<?> taskResult = scheduler.scheduleAtFixedRate( task, 0, repeatPeriod, SECONDS);
		
		MonitoringProfile result = null;
		
		try {
			result = (MonitoringProfile) taskResult.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		MonitoringProfileFactory.setInstance(result);
		
		
	}
	
	public static void main(String[] args){
		MonitoringProfileLoader profileLoader = new MonitoringProfileLoader();
		profileLoader.sheduleTask(10);
	}

}

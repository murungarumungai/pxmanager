package raz.test.Monitoring;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.SECONDS;

public class MonitoringChecker {
	
	private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	
	private long repeatPeriod;
	//private MonitoringProfile monitoringProfile;
	
	/**
	 * regular time period to run the checks in seconds
	 * */
	public MonitoringChecker( long regularRepeatPeriod){
		
		this.repeatPeriod = regularRepeatPeriod;
	}
	
	public MonitoringChecker(/*MonitoringProfile monitoringProfile,*/ long regularRepeatPeriod, Date... timePoints){}
	
	public void scheduleCheck(){
		
		MonitoringProfileTask monitoringTask = new MonitoringProfileTask();
		
		ScheduledFuture<?> checkResult = scheduler.scheduleAtFixedRate(monitoringTask, 0, repeatPeriod, SECONDS);
		
		
	}
	

}

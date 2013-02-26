package at.apa.pdfwlserver.monitoring;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import at.apa.pdfwlserver.monitoring.data.MonitoringProfileCache;

public class MonitoringProfileChecker {
	
	private static Logger logger = LoggerFactory.getLogger(MonitoringProfileChecker.class);
	
	private static MonitoringProfileChecker _instance = null;

	public static String GROUP_JOB = "group1";
	public static String NAME_JOB = "CHECK_JOB";
	public static String NAME_TRIGGER = "regularChecksTrigger";
	public static String GROUP_TRIGGER = GROUP_JOB;//"group1";

	
	
	private JobKey  jobKey;//hold a JobKey Reference to the check Job
	
	private SimpleTrigger regularChecksTrigger;
	
	private Scheduler scheduler;
	/*TODO: razvan in toate tutorialele pe care le-am facut de quartz, toate obiectele quartz
	 * Scheduler, Job, Trigger nu erau variabile membru/instance variable, 
	 * Oare e bine daca le expun ca instance variable
	 * */
	
	public static synchronized MonitoringProfileChecker getInstance(){
		if(null == _instance){
			_instance = new MonitoringProfileChecker();
		}
		return _instance;
	}


	/**
	 *  Note: this constructor is private to prevent the creation of objects.
	 * */
	private MonitoringProfileChecker(){}
	
	/**
	 * Returns the JobKey of the Monitoring check job  
	 * */
	public JobKey getJobKey(){
		return jobKey;
	}
	
	public SimpleTrigger getRegularChecksTrigger(){
		return regularChecksTrigger;
	}
	
	public Scheduler getScheduler(){
		return scheduler;
	}

	/**
	 * launches the triggers that will fire the check job
     * @return return the First Trigger Date
	 * */
	public Date launchCheckJob() throws SchedulerException {

		scheduler = buildScheduler(); 

		// build job
		JobDetail job = newJob(MonitoringProfileCheckJob.class)
				.withIdentity(NAME_JOB,GROUP_JOB)
				.build();
		jobKey = job.getKey();
		logger.debug("JOB DETAIL CREATED:"+jobKey+" hashCode:"+job.hashCode());

		regularChecksTrigger = buildTrigger(NAME_TRIGGER , (int) MonitoringProfileCache.getMonitoringProfile().getRepeatPeriod());
		
		
		// schedule it to run!
		Date ft = scheduler.scheduleJob(job, regularChecksTrigger);
		/*
		logger.info("TRIGGER KEY:"+regularChecksTrigger.getKey()+" TriggerHash:"+regularChecksTrigger.hashCode()+
				" JobKey:"+job.getKey() + " will run at: " + ft + " and repeat: "
						+ regularChecksTrigger.getRepeatCount() + " times, every "
						+ regularChecksTrigger.getRepeatInterval() / 1000 + " seconds");*/
		
		// All of the jobs have been added to the scheduler, but none of the jobs
        // will run until the scheduler has been started
		//Razvan sheduler should already be started by MonitoringLoaderJob
		//scheduler.start();
        //logger.info("------- Started Scheduler -----------------");
		//logger.info("-------   ---------- Scheduler:"+scheduler);
        return ft;
     
	}

	/**
	 * Should cleanUp the Jobs and triggers that were launched by this class
	 * */
/*	public void cleanUp() throws SchedulerException{
		//scheduler.shutdown(true);//Razvan: Do not shut down the Scheduler since it is common to both jobs
		// display some stats about the schedule that just ran
        SchedulerMetaData metaData = scheduler.getMetaData();
        logger.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
        
		monitoringProfile = null;
		MonitoringProfileCache.setInstance(null);//We make sure that the caller of this method will set a new MonitoringProfile in cache
		jobKey = null;
		regularChecksTrigger = null;
		//scheduler = null;
		logger.info("Clean up executed! monitoringProfile>"+monitoringProfile+" jobKey>"+jobKey+" regularChecksTrigger>"+ regularChecksTrigger
				+" scheduler>"+scheduler);
	}*/
	
	/**
	 * Clean Up
	 * Delete the identified Job from the Scheduler - and any associated Triggers.
	 * 
	 * */
	public void cleanUp2() throws SchedulerException{
		
		SchedulerMetaData metaData = scheduler.getMetaData();
        logger.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
        
        logger.debug("Delete Job:"+jobKey);
		scheduler.deleteJob(jobKey);
		
		
		MonitoringProfileCache.setMonitoringProfile(null);//We enforce the caller of this method will set a new MonitoringProfile in cache
		jobKey = null;
		regularChecksTrigger = null;
		logger.info("Clean up executed! monitoringProfile>"+MonitoringProfileCache.getMonitoringProfile()+" jobKey>"+jobKey+" regularChecksTrigger>"+ regularChecksTrigger);
	}
	
	public static Scheduler buildScheduler() throws SchedulerException {
		// First we must get a reference to a scheduler
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		return sched;
	}

	/**
	 * builds a trigger that repeats every timed interval/repeat period
	 * @param repeatInterval in seconds
	 * */
	public static SimpleTrigger buildTrigger(String name ,int repeatInterval) {
		// Build Trigger
		SimpleTrigger trigger = null;
		trigger = newTrigger()
				.withIdentity(name, GROUP_TRIGGER)
				.startNow()// start immediately with no delay
				.withSchedule(
						simpleSchedule()
						.withIntervalInSeconds(repeatInterval)
						// .withRepeatCount(10)
						.repeatForever())
				.build();
		return trigger;
	}

}

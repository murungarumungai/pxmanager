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

import at.apa.pdfwlserver.monitoring.data.MonitoringProfile;
import at.apa.pdfwlserver.monitoring.data.MonitoringProfileCache;

public class MonitoringProfileChecker {
	
	private static Logger logger = LoggerFactory.getLogger(MonitoringProfileChecker.class);
	
	private static MonitoringProfileChecker _instance = null;

	public static String GROUP_JOB = "group1";
	public static String NAME_JOB = "CHECK_JOB";
	public static String NAME_TRIGGER = "regularChecksTrigger";
	public static String GROUP_TRIGGER = GROUP_JOB;//"group1";

	private MonitoringProfile monitoringProfile;
	
	private JobKey  jobKey;//hold a JobKey Reference to the check Job
	
	private SimpleTrigger regularChecksTrigger;
	
	private Scheduler scheduler;
	/*TODO: razvan in toate tutorialele pe care le-am facut de quartz, toate obiectele quartz
	 * Scheduler, Job, Trigger nu erau variabile membru/instance variable, 
	 * Oare e bine daca le expun ca instance variable
	 * */
	
	public static MonitoringProfileChecker getInstance(){
		if(null == _instance){
			_instance = new MonitoringProfileChecker(MonitoringProfileCache.getInstance());
		}
		return _instance;
	}

	private MonitoringProfileChecker(MonitoringProfile monitoringProfile) {
		if(null == monitoringProfile){
			throw new IllegalArgumentException("MonitoringProfile can't be null!");
		}
		this.monitoringProfile = monitoringProfile;
	}
	
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
		logger.debug("JobDetail created:"+jobKey+" hashCode:"+job.hashCode());

		regularChecksTrigger = buildTrigger(NAME_TRIGGER , (int) monitoringProfile.getRepeatPeriod());
		
		
		// schedule it to run!
		Date ft = scheduler.scheduleJob(job, regularChecksTrigger);
		logger.info("TriggerKey:"+regularChecksTrigger.getKey()+" TriggerHash:"+regularChecksTrigger.hashCode()+
				" JobKey:"+job.getKey() + " will run at: " + ft + " and repeat: "
						+ regularChecksTrigger.getRepeatCount() + " times, every "
						+ regularChecksTrigger.getRepeatInterval() / 1000 + " seconds");
		
		// All of the jobs have been added to the scheduler, but none of the jobs
        // will run until the scheduler has been started
		//Razvan sheduler should already be started by MonitoringLoaderJob
		//scheduler.start();
        //logger.info("------- Started Scheduler -----------------");
        return ft;
     
	}

	public void cleanUp() throws SchedulerException{
		scheduler.shutdown(true);
		// display some stats about the schedule that just ran
        SchedulerMetaData metaData = scheduler.getMetaData();
        logger.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
        
		monitoringProfile = null;
		jobKey = null;
		regularChecksTrigger = null;
		scheduler = null;
		logger.info("Clean up executed! monitoringProfile>"+monitoringProfile+" jobKey>"+jobKey+" regularChecksTrigger>"+ regularChecksTrigger
				+" scheduler>"+scheduler);
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

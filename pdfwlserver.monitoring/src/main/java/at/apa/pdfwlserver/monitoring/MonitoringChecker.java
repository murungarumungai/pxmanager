package at.apa.pdfwlserver.monitoring;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import at.apa.pdfwlserver.monitoring.data.MonitoringProfile;
import at.apa.pdfwlserver.monitoring.data.MonitoringProfileCache;

public class MonitoringChecker {
	
	private static Logger logger = LoggerFactory.getLogger(MonitoringChecker.class);
	
	private static MonitoringChecker _instance = null;

	public static String GROUP_JOB = "group1";
	public static String NAME_JOB = "job1";
	public static String NAME_TRIGGER = "trigger1";
	public static String GROUP_TRIGGER = "group1";

	private MonitoringProfile monitoringProfile;
	
	public static MonitoringChecker getInstance(){
		if(null == _instance){
			_instance = new MonitoringChecker(MonitoringProfileCache.getInstance());
		}
		return _instance;
	}

	private MonitoringChecker(MonitoringProfile monitoringProfile) {
		this.monitoringProfile = monitoringProfile;
	}

	/**
	 * launches the triggers that will fire the check job
	 * */
	public void launchCheckJob() throws SchedulerException {

		Scheduler scheduler = getScheduler();

		// build job
		JobDetail job = newJob(MonitoringProfileJob.class)
				.withIdentity(NAME_JOB,GROUP_JOB)
				.build();
		logger.debug("JobDetail created:"+job.getKey()+" hashCode:"+job.hashCode());

		SimpleTrigger regularChecksTrigger = buildTrigger((int) monitoringProfile.getRepeatPeriod());
		
		// schedule it to run!
		Date ft = scheduler.scheduleJob(job, regularChecksTrigger);
		logger.info("TriggerKey:"+regularChecksTrigger.getKey()+" TriggerHash:"+regularChecksTrigger.hashCode()+
				" JobKey:"+job.getKey() + " will run at: " + ft + " and repeat: "
						+ regularChecksTrigger.getRepeatCount() + " times, every "
						+ regularChecksTrigger.getRepeatInterval() / 1000 + " seconds");
		
		// All of the jobs have been added to the scheduler, but none of the jobs
        // will run until the scheduler has been started
		scheduler.start();
        logger.info("------- Started Scheduler -----------------");
		

	}

	private Scheduler getScheduler() throws SchedulerException {
		// First we must get a reference to a scheduler
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		return sched;
	}

	private SimpleTrigger buildTrigger(int repeatInterval) {
		// Build Trigger
		SimpleTrigger trigger = null;
		trigger = newTrigger()
				.withIdentity(NAME_TRIGGER, GROUP_TRIGGER)
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

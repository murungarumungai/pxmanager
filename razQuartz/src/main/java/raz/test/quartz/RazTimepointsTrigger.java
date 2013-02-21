package raz.test.quartz;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RazTimepointsTrigger {

	/**
	 * The TimePoints problem from .csv can be solved with quartz in 2 ways: 1.
	 * Either you need a chronTrigger // job 2 will run every other minute (at
	 * 15 seconds past the minute) "15 30 11:10 * * ?"
	 * 
	 * 2.Wither with AnnualCalendar
	 * */

	private static Logger logger = LoggerFactory
			.getLogger(RazExampleTrigger.class);

	public Scheduler getScheduler() throws SchedulerException {
		// First we must get a reference to a scheduler
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		return sched;
	}

	public CronTrigger getCronTrigger(String cronExpresion) {
		CronTrigger trigger = newTrigger()
				.withIdentity("trigger1", "group1")
				.withSchedule(cronSchedule(cronExpresion))
				.build();
		return trigger;
	}
	
	

	public void scheduleJobUsingChron() throws Exception {
		logger.info("------- Initializing -------------------");
		Scheduler sched = getScheduler();

		// build job
		JobDetail job = newJob(RazExampleJob.class).withIdentity("job1",
				"group1").build();
		logger.debug("JobDetail created:" + job.getKey() + " hashCode:"
				+ job.hashCode());
		/**
		 * Timepoints Examples:
		 * Due-Date Data Delivery	01.01.2013 04:00
		 * Data processed			01.01.2013 04:30
		 * 							02.01.2013 04:00
		 * 							02.01.2013 04:30
		 * */
		
		/*To test: (21.02.2013)-everyday at 11:33  */
		String cronExpresion = "15 58 11 * * ?";
		
		
		CronTrigger trigger = getCronTrigger(cronExpresion);
		Date ft = sched.scheduleJob(job, trigger);
	    logger.info(job.getKey() + " has been scheduled to run at: " + ft
	                + " and repeat based on expression: "
	                + trigger.getCronExpression());
	    
	    
	    // All of the jobs have been added to the scheduler, but none of the
        // jobs
        // will run until the scheduler has been started
        sched.start();
	    
        logger.info("------- Waiting five minutes... ------------");
        try {
            // wait five minutes to show jobs
            Thread.sleep(300L * 1000L);
            // executing...
        } catch (Exception e) {
        }

        logger.info("------- Shutting Down ---------------------");

        sched.shutdown(true);
	    
	}
	
	public void sheduleJobUsingAnnualCalendar(){
		
		/**
		 * Timepoints Examples:
		 * Due-Date Data Delivery	01.01.2013 03:00
		 * Data processed			01.01.2013 04:30
		 * 							02.01.2013 03:00
		 * 							02.01.2013 04:30
		 * */
		
		//Get the startTime when it will 1st start
		//Get the repeat Time, interval = [Due-DateDelivery] - [Data processed]
		//Add dates to annual calendar
		
	}
	
	public static void main(String[] args) throws Exception {
		
		//System.setProperty("log4j.configuration", "file:log4j.properties");
		
		RazTimepointsTrigger example = new RazTimepointsTrigger();
		example.scheduleJobUsingChron();
	}

}

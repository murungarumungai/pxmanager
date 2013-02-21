package raz.test.quartz;

import static org.quartz.JobBuilder.newJob;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.examples.example2.SimpleJob;
import org.quartz.examples.example2.SimpleTriggerExample;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class RazExampleTrigger {

	private static Logger logger = LoggerFactory
			.getLogger(RazExampleTrigger.class);
	
	
	public static void main(String[] args) {
				
		RazExampleTrigger example = new RazExampleTrigger();
		
		
		try {
			example.sheduleJob();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	} 

	public void sheduleJob() throws Exception {

		// First we must get a reference to a scheduler
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();

		// build job
		JobDetail job = newJob(RazExampleJob.class)
				.withIdentity("job1","group1")
				.build();

		logger.debug("JobDetail created:"+job.getKey()+" hashCode:"+job.hashCode());
		

		// Build Trigger
		SimpleTrigger trigger = null;
		
		// get a "nice round" time a few seconds in the future...
        Date startTime = DateBuilder.nextGivenSecondDate(null, 15);

		trigger  = newTrigger()
	            .withIdentity("trigger1", "group1")
	            .startNow()//start immediately with no delay
	            .withSchedule(simpleSchedule()
	                    .withIntervalInSeconds(10)
	                    .withRepeatCount(10))
	            .build();

		// schedule it to run!
		Date ft = sched.scheduleJob(job, trigger);
		logger.info("TriggerKey:"+trigger.getKey()+"TriggerHash:"+trigger.hashCode()+" JobKey:"+job.getKey() + " will run at: " + ft + " and repeat: "
				+ trigger.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000 + " seconds");
		
		
		
		
		// All of the jobs have been added to the scheduler, but none of the jobs
        // will run until the scheduler has been started
        sched.start();
        logger.info("------- Started Scheduler -----------------");
        
        //wait 50 seconds to fire the job 5 times, each 10 seconds
        Thread.sleep(50L * 1000L);
        
        logger.info("-----Resheduling  ----");
        trigger = newTrigger()
        		.withIdentity("trigger1", "group1")
        		.startNow()
        		.withSchedule(simpleSchedule()
        				.withIntervalInSeconds(2)
        				.withRepeatCount(5)
        				)
        		.build();
        ft = sched.rescheduleJob(trigger.getKey(), trigger);
        
        logger.info("TriggerKey:"+trigger.getKey()+"TriggerHash:"+trigger.hashCode()+" JobKey:"+job.getKey() + " will run at: " + ft + " and repeat: "
				+ trigger.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000 + " seconds");

        
        
        				
		

		logger.info("------- Waiting five minutes... ------------");
		try {
			// wait five minutes to show jobs
			Thread.sleep(300L * 1000L);
			// executing...
		} catch (Exception e) {
		}

		logger.info("------- Shutting Down ---------------------");

		sched.shutdown(true);

		logger.info("------- Shutdown Complete -----------------");

	}

}

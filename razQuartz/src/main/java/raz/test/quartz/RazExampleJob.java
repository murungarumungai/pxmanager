package raz.test.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RazExampleJob implements Job{
	
	private static Logger logger = LoggerFactory.getLogger(RazExampleJob.class);
	
	//Quartz requires public constructor
	public RazExampleJob(){}

	/**
     * <p>
     * Called by the <code>{@link org.quartz.Scheduler}</code> when a
     * <code>{@link org.quartz.Trigger}</code> fires that is associated with
     * the <code>Job</code>.
     * </p>
     * 
     * @throws JobExecutionException
     *             if there is an exception while executing the job.
     */
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		// This job simply prints out its job name and the
        // date and time that it is running
        JobKey jobKey = context.getJobDetail().getKey();
        logger.info(">> " + jobKey + " executing at " + new Date());
		
		
	}

}

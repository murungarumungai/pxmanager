package at.apa.pdfwlserver.monitoring;

import static org.quartz.JobBuilder.newJob;

import java.io.File;
import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitoringProfileLoader {
	
	
	static String NAME_JOB = "LOAD_JOB";
	static String NAME_TRIGGER = "loadTrigger";
	
	private static Logger logger = LoggerFactory.getLogger(MonitoringProfileLoader.class);
	
	private File xml;
	private File csv;
	private int repeatPeriod; //in minutes
	private MonitoringProfileReader profileReader;
	
	/**
	 * before setting the files, make sure they exist physically on the file system,
	 * and can read.
	 * 
	 * @param repeatPeriod in minutes - how often should we re-read the files?
	 * 
	 * */
	public MonitoringProfileLoader(File xml, File csv, int repeatPeriod){
		this.xml = xml;
		this.csv = csv;
		this.repeatPeriod = repeatPeriod;
		
		profileReader = new MonitoringProfileReaderImpl(xml, csv);
	}
	
	/**
	 * launches the trigger that will fire 
	 * MonitoringLoadingJob which will periodically read the
	 * .csv,.xml files and re-instancitate a MonitoringProfileJob
	 * */
	public void launchMonitoringLoadingJob() throws SchedulerException {
		
		Scheduler sched = MonitoringProfileChecker.buildScheduler();
		
		// build job
		JobDetail job = newJob(MonitoringProfileLoaderJob.class)
				.withIdentity(NAME_JOB,MonitoringProfileChecker.GROUP_JOB)
				.build();
		
		// pass initialization parameters into the job
        //job.getJobDataMap().put(MonitoringProfileLoaderJob.PROFILE_READER, profileReader);
		MonitoringProfileLoaderJob.setMonitoringProfileReader(profileReader);
		
		JobKey jobKey = job.getKey();
		logger.debug("JOB DETAIL CREATED:"+jobKey+" hashCode:"+job.hashCode());
		
		SimpleTrigger trigger = MonitoringProfileChecker.buildTrigger(NAME_TRIGGER,repeatPeriod);
		
		// schedule it to run!
		Date ft = sched.scheduleJob(job, trigger);
		/*
		logger.info("TRIGGER KEY:"+trigger.getKey()+" TriggerHash:"+trigger.hashCode()+
				" JobKey:"+job.getKey() + " will run at: " + ft + " and repeat: "
				+ trigger.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000 + " seconds");*/
				
		//All of the jobs have been added to the scheduler, but none of the jobs
		//will run until the scheduler has been started
		sched.start();
		//logger.info("------- Started Scheduler ---------- Scheduler:"+sched);
		
	}
	
	/**
	 * This is the entry point into the application
	 * */
	public static void main(String[] args){
		
		//input
		String xmlPath = "";
		String csvPath =  null;
		String repeatTimeString = "10";
		
		int repeatTime = -1;
		
		repeatTime = Integer.parseInt(repeatTimeString);
		File xml = new File("CustomerFolderStructureConfiguration3.xml");
		File csv = null;
		
		MonitoringProfileLoader monitoringProfileLoader = new MonitoringProfileLoader(xml, csv, repeatTime);
		
		
		
		try {
			monitoringProfileLoader.launchMonitoringLoadingJob();
		} catch (SchedulerException e) {
			
			e.printStackTrace();
		}
	}

}

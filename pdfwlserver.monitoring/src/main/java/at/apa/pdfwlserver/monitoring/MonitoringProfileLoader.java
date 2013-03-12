package at.apa.pdfwlserver.monitoring;

import static org.quartz.JobBuilder.newJob;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.apa.pdfwlserver.monitoring.utils.FileUtils;

public class MonitoringProfileLoader {
	
	
	static String NAME_JOB = "LOAD_JOB";
	static String NAME_TRIGGER = "loadTrigger";
	
	private static Logger logger = LoggerFactory.getLogger(MonitoringProfileLoader.class);
	
	
	private int repeatPeriod; //in minutes
	private MonitoringProfileReader profileReader;
	
	/**
	 * before setting the files, make sure they exist physically on the file system,
	 * and can read.
	 * 
	 * @param repeatPeriod in minutes - how often should we re-read the files?
	 * 
	 * */
	public MonitoringProfileLoader(File configPropertiesFilePath, File customerBaseDir, File xml, File csv, int repeatPeriod){
		
		
		this.repeatPeriod = repeatPeriod;
		
		profileReader = new MonitoringProfileReaderImpl(configPropertiesFilePath,customerBaseDir, xml, csv);
	}
	
	/**
	 * launches the trigger that will fire 
	 * MonitoringLoadingJob which will periodically read the
	 * .csv,.xml files and re-instantiate a MonitoringProfileJob
	 * */
	public void launchMonitoringLoadingJob() throws SchedulerException {
		
		Scheduler sched = MonitoringProfileChecker.buildScheduler();
		
		// build job
		JobDetail job = newJob(MonitoringProfileLoaderJob.class)
				.withIdentity(NAME_JOB,MonitoringProfileChecker.GROUP_JOB)
				.build();
		
		
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
	public static void main(String[] args) throws NumberFormatException, IOException, SchedulerException {
		
		//input TODO: should be read from command line
		String propsPath = "";
		String xmlPath = "CustomerFolderStructureConfiguration3.xml";
		String csvPath =  "mutationExample.csv";
		String repeatTimeString = "10";
		String customerBaseDirectoryPath = "d:/zTask/_MP/MPS-66-Monitoring for optimizing Hotline/derstandard/";
		
		int repeatTime = 0;//how often should we re-read the files and reload the monitotingProfile in minutes
		
		repeatTime = Integer.parseInt(repeatTimeString);
		
		File propertiesFilePath = null;
		File xml = null;
		File csv = null;
		File customerBaseDir = null;
		
		propertiesFilePath = FileUtils.checkFileExists(propsPath);
		xml = FileUtils.checkFileExists(xmlPath);
		csv = FileUtils.checkFileExists(csvPath);
		customerBaseDir = FileUtils.checkDirExists(customerBaseDirectoryPath);
		
		
		MonitoringProfileLoader monitoringProfileLoader = new MonitoringProfileLoader(propertiesFilePath, customerBaseDir, xml, csv, repeatTime);
		
		monitoringProfileLoader.launchMonitoringLoadingJob();
		
	}

}

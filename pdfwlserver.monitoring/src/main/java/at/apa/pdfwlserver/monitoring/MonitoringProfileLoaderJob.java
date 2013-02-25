package at.apa.pdfwlserver.monitoring;

import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.apa.pdfwlserver.monitoring.data.MonitoringProfile;
import at.apa.pdfwlserver.monitoring.data.MonitoringProfileCache;

@DisallowConcurrentExecution
public class MonitoringProfileLoaderJob implements Job {
	
	private static Logger logger = LoggerFactory.getLogger(MonitoringProfileLoaderJob.class);
	
	public static String PROFILE_READER = "PROFILE_READER";
	
	MonitoringProfileReader monitoringProfileReader;

	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		JobKey jobKey = context.getJobDetail().getKey();
		logger.info("JobKey >"+jobKey + " executing at " + new Date());

		MonitoringProfile monitoringProfile = null;
		monitoringProfileReader =getMonitoringProfileReader(context);
		
		try {	
			monitoringProfile = monitoringProfileReader.readMonitoringProfile();
		} catch (JAXBException jaxbEx) {
			logger.error("Error Reading monitoring profile",jaxbEx);
			throw new JobExecutionException(jaxbEx);
		}
		
		if (MonitoringProfileCache.getInstance() == null) {
			// it's the 1st time when we run the checker
			MonitoringProfileCache.setInstance(monitoringProfile);
			try {
				MonitoringProfileChecker.getInstance().launchCheckJob();
			} catch (SchedulerException e) {
				e.printStackTrace();
			}

		} else {
			//monitoring profile already exists
			if(MonitoringProfileCache.isProfileUpdated()==false){
				//Do nothing. A profile exist and the CheckJob has been launched
				//To make sure, just log the next fire time
				logger.debug("MonitoringProfile already exists. MonitoringProfile has not been updated");
				logger.debug("The Check Job has already been launched. Next fire time:"+MonitoringProfileChecker.getInstance().getRegularChecksTrigger().getNextFireTime());
			} else {
				//profile was updated
				//Check to see if the job is currently executing
				if(isCheckJobCurrentlyRunning()){
					
				} else {
					//CheckJob Is not currently running. 
					//Just go ahead and reinitialize everything
					try {
						MonitoringProfileChecker.getInstance().cleanUp();
					} catch (SchedulerException e) {
						e.printStackTrace();
					}
					MonitoringProfileCache.setInstance(monitoringProfile);
					try {
						MonitoringProfileChecker.getInstance().launchCheckJob();
					} catch (SchedulerException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	

	
	//This is used in production
	private MonitoringProfileReader getMonitoringProfileReader(JobExecutionContext context){
		if(null == monitoringProfileReader){
			JobDataMap data = context.getJobDetail().getJobDataMap();			
			monitoringProfileReader = (MonitoringProfileReader) data.get(PROFILE_READER);
		}
		return monitoringProfileReader;
	}
	
	/**
	 * this is used in UnitTests
	 * */
	public void setMonitoringProfileReader(MonitoringProfileReader profileReader){
		this.monitoringProfileReader = profileReader;
	}

	/**
	 * Verifica daca exista un Quartz Job checkJob, asta se poate face apeland
	 * quatrz get(group, jobId)
	 * 
	 * apelul/check-ul asta este echivalent cu verificarea daca
	 * MonitoringProfileExists, pentru ca este o mapare 1:1 intre Job is
	 * MonitoringProfile
	 * */
	@Deprecated
	// Razvan: in loc de asta vezi daca exista MonitoringProfile
	private boolean chekJobExists() {
		boolean result = false;

		return result;
	}
	
	private boolean isCheckJobCurrentlyRunning(){
		boolean result = false;
		Scheduler sched = MonitoringProfileChecker.getInstance().getScheduler();
		/*
		 * sched nu are cum sa fie null.
		 * Daca e null inseamna ca pornirea initiala nu s-a facut correct.
		 * Daca e null. deja programul nu s-a executat corect pina in punctul asta
		 * */
		List<JobExecutionContext> runningJobs = null;
		try {
			runningJobs = sched.getCurrentlyExecutingJobs();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		if(null!=runningJobs && runningJobs.size()>0){
			for(JobExecutionContext jobContext: runningJobs){
				JobKey jobKey = jobContext.getJobDetail().getKey();
				if(jobKey == MonitoringProfileChecker.getInstance().getJobKey()){
					return true;
				}
			}
		}
		return result;
	}

	
}

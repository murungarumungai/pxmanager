package at.apa.pdfwlserver.monitoring;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

import at.apa.pdfwlserver.monitoring.data.MonitoringProfile;
import at.apa.pdfwlserver.monitoring.data.MonitoringProfileCache;

public class MonitoringLoaderJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {

		MonitoringProfile monitoringProfile = readMonitoringProfile();
		
		if (MonitoringProfileCache.getInstance() == null) {
			// it's the 1st time when we run the checker
			MonitoringProfileCache.setInstance(monitoringProfile);
			
			try {
				MonitoringChecker.getInstance().launchCheckJob();
			} catch (SchedulerException e) {
				
				e.printStackTrace();
			}

		} else {
			//monitoring profile already exists
			if(MonitoringProfileCache.isProfileUpdated()==false){
				//Do nothing. A profile exist an the CheckJob is running
				//To make sure, just log the next fire time
			} else {
				//profile was updated
				//Check to see if the job is currently executing
			}
		}

	}

	/**
	 * reads the .csv and .xml and instantiate a monitoring profile
	 * */
	public MonitoringProfile readMonitoringProfile() {
		MonitoringProfile result = null;
		return result;
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

	
}

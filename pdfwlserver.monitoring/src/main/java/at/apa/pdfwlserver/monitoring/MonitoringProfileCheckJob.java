package at.apa.pdfwlserver.monitoring;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.apa.pdfwlserver.monitoring.data.AvailableMutationChecker;
import at.apa.pdfwlserver.monitoring.data.AvailableMutationCheckerImpl;
import at.apa.pdfwlserver.monitoring.data.CheckSession;
import at.apa.pdfwlserver.monitoring.data.IncomingSubDirResult;
import at.apa.pdfwlserver.monitoring.data.MonitoringProfileCache;
import at.apa.pdfwlserver.monitoring.data.MutationResult;
import at.apa.pdfwlserver.monitoring.data.ReportResult;
import at.apa.pdfwlserver.monitoring.data.SubDirChecker;
import at.apa.pdfwlserver.monitoring.data.SubDirResult;
import at.apa.pdfwlserver.monitoring.utils.DateUtils;
import at.apa.pdfwlserver.monitoring.utils.FileUtils;

/**
 * The purpose of this class is to execute a complete check
 * 
 * it does not know when it is called, or how often, and it does not care about scheduling
 * 
 * it is just a sequence of logical steps, which are executed in order to run a check,
 * and produce a reportResult, and write the status-page.html
 * 
 * De ce am nevoie? what parameters does it need in order to do its job??
 * Se pare ca am nevoie DOAR de <code>List<SubDirChecker> subDirectoriesToBeChecked</code>
 * */

public class MonitoringProfileCheckJob implements Job {
	
	private static Logger logger = LoggerFactory.getLogger(MonitoringProfileCheckJob.class);

	List<SubDirChecker> subDirectoriesToBeChecked = null;
	
	
	/**
     * Empty constructor for job initialization
     * required by quartz
     */
	public MonitoringProfileCheckJob(){
		subDirectoriesToBeChecked = MonitoringProfileCache.getMonitoringProfile().getCustomerFileSystemStructure();
	}

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		JobKey jobKey = context.getJobDetail().getKey();
		logger.info("JobKey >"+jobKey + " executing at " + new Date());
		//check();
		
		//we simulate long running job by sleeping the thread
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

		
		//Notify LoadJob that execution is complete 
		synchronized (MonitoringProfileCache.getMonitoringProfile()) {
			MonitoringProfileCache.getMonitoringProfile().setCheckJobRunning(false);
			MonitoringProfileCache.getMonitoringProfile().notifyAll();
		}
		
	}
	
	

	/**
	 * @throws IOException 
	 * @Schedule(every 20 Minutes every TimePoint: [Due-Date
	 *                 Datadelivery]-02.30AM [Data processed]-4.30 AM)
	 */
	public ReportResult check() throws IOException {
		SubDirResult dataDeliveryResult = checkDataDelivery();
		
		SubDirResult importResult = checkImport();
		/*Razvan 25.04.2013
		 * it makes sense to check for available mutation only if the import process was done
		 * otherwise why should we even check for availableMutation?
		 * */
		MutationResult mutationResult = checkMutation(null);
		return createReport(dataDeliveryResult, importResult,mutationResult);
	}

	/**
	 * TODO: Razvan: 13.03.2013 Ask Roman how importer works.
	 * It can happen the following scenario: 
	 * 1. that we have a file in incoming Dir within TimePoint, => we display status "not processed yet"
	 * 2. Importer begins importing and moves the file from "incoming" dir to the next Directory, which is "import"
	 * but then after 5 min, at the next check we see that there is no file in "incoming" dir, 
	 * and we still display status "waiting" or "No data received in time". 
	 * But this doesn't make sense since a valid dataFile was received in "incoming" folder, but then was processed by importer and moved to "import" folder  
	 * */
	public SubDirResult checkDataDelivery() {
		SubDirResult dataDeliveryStatus = null;
		//check 1.incoming folder to check dataDelivery, we know form .xml tha incoming folder is the first folder 
		
		dataDeliveryStatus = subDirectoriesToBeChecked.get(0).checkDir();
		return dataDeliveryStatus;
	}
	/**
	 * it must return a result. It can not return null. If it returns null there's a
	 * programming error.
	 * Razvan 24.04.2013 - yes, it can return null if we have no files in any of the checked directories: import, success, error.
	 * So it's ok to return null
	 * @throws IOException 
	 */
	public SubDirResult checkImport()  {
		SubDirResult importStatus = null;
		/**
		 * check the subDirectories in the order which they are in the List
		 * 2.import 3.succes 4.error
		 */
		SubDirResult mostRecentStatus = null;
		Date mostRecentDate = null;
		File latestFileWithinCheckInterval = null;
		for(int i=1; i<subDirectoriesToBeChecked.size();i++){
			importStatus = subDirectoriesToBeChecked.get(i).checkDir();
			
			if(null!=importStatus){
				//Try to return the most recent status based on the latest received file(last modified date)
				if(mostRecentDate==null){
					latestFileWithinCheckInterval = importStatus.getLatestFileWithinTheCheckInterval();
					mostRecentDate = FileUtils.getReceivedDate(latestFileWithinCheckInterval);
					mostRecentStatus = importStatus;
				} else {
					latestFileWithinCheckInterval = importStatus.getLatestFileWithinTheCheckInterval();
					Date fileDate =  FileUtils.getReceivedDate(latestFileWithinCheckInterval);
					if(fileDate.after(mostRecentDate)){
						mostRecentDate = fileDate;
						mostRecentStatus = importStatus;
					}
				}
			}
		}
		return mostRecentStatus;
	}

	/**
	 * it must return a result. It can not return null.
	 */
	public MutationResult checkMutation(Date now) {
		MutationResult result = null;
		
		CheckSession checkSession = CheckSession.getMutationBeingCheckedRightNow(now);
		if(null == checkSession){
			//either the issues.csv is expired, or the monitoring profile is null
			throw new IllegalArgumentException("CheckInterval for current time:"+now+" is null. " +
					"Isses.csv is outdated, there are now issues to be checked, please update it");
		}
		Date fromDate = checkSession.getCurrentCheckedMutation().getDataEarliestDelivery();
		Date issueDate = checkSession.getCurrentCheckedIssue().getIssuseDate();
		//Check for available Mutations for the certain issue-date (from 00:00 to 23:59).
		Date toDate = DateUtils.getEndOfDayTime(issueDate);
		
		AvailableMutationChecker mutationChecker = new AvailableMutationCheckerImpl();
		
		mutationChecker.checkMutationAvailability(fromDate, toDate);
		/*
		 * I have to know Mutation being checked right now
		 * and check whether it's available or not
		 * */
		return result;
	}

	/**
	 * Compile the results and creates a report, Before writing the report, read
	 * every time the FreeMarkerTemplate write StatusPage.html
	 */
	private ReportResult createReport(SubDirResult dataDeliveryResult, SubDirResult importResult, MutationResult mutationResult) {
		/*
		 * Razvan 15.03.2013
		 * when you consolidate a result from DataDelivery and ImportStatus
		 * Display the status of dataDelivery ONLY if all other statuses are null. 
		 * Else, if we have a status after incoming, is means that we had data in incoming, 
		 * and in this case just display fileName and Date of the last known file within the CheckSession
		 * */
		
		if(null==importResult){
			//ReportResult.setStatus() system is incoming state
		} else {
			/*
			 * it means that we are passed the incoming state, we already received data in coming
			 * so just let the user know about the last known file in incoming within this checking session
			 * */
			/*ReportResult.set()*/ ((IncomingSubDirResult) dataDeliveryResult).getIncomingLastKnownFileWithinCheckInterval();
		}
		
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
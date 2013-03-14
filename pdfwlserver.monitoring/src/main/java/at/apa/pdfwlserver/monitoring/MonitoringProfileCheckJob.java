package at.apa.pdfwlserver.monitoring;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.apa.pdfwlserver.monitoring.data.MonitoringProfileCache;
import at.apa.pdfwlserver.monitoring.data.MutationResult;
import at.apa.pdfwlserver.monitoring.data.ReportResult;
import at.apa.pdfwlserver.monitoring.data.SubDirChecker;
import at.apa.pdfwlserver.monitoring.data.SubDirResult;

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
		MutationResult mutationResult = checkMutation();
		return createReport();
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
		//check 1.incoming
		/*
		for (SubDirChecker subDir : subDirectoriesToBeChecked) {
			// if subDir = "incoming"
			dataDeliveryStatus = subDir.checkDir();
		}*/
		/*
		 * "incoming" subDir is the 1st one defined in .xml.
		 * So dataDelivery results from checking ONLY this dir
		 * */
		dataDeliveryStatus = subDirectoriesToBeChecked.get(0).checkDir();
		return dataDeliveryStatus;
	}

	
	public SubDirResult checkImportDir()  {
		SubDirResult importDirStatus = null;
		for(int i=0; i<subDirectoriesToBeChecked.size();i++){
			if(i==1){//I know that in .xml import is on 2nd pos
				return subDirectoriesToBeChecked.get(i).checkDir();
			}
		}
		return importDirStatus;
	}
	
	/**
	 * it must return a result. It can not return null. If it returns null there's a
	 * programming error
	 * @throws IOException 
	 */
	public SubDirResult checkImport()  {
		SubDirResult importStatus = null;
		/**
		 * check the subDirectories in the order which they are in the List
		 * 2.import 3.succes 4.error
		 */
		for (SubDirChecker subDir : subDirectoriesToBeChecked) {
			importStatus = subDir.checkDir();
		}
		return importStatus;
	}

	/**
	 * it must return a result. It can not return null.
	 */
	public MutationResult checkMutation() {
		MutationResult result = null;
		return result;
	}

	/**
	 * Compile the results and creates a report, Before writing the report, read
	 * every time the FreeMarkerTemplate write StatusPage.html
	 */
	private ReportResult createReport() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

}

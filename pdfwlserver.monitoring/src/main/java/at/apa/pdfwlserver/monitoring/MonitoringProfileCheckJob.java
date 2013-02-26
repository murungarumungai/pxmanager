package at.apa.pdfwlserver.monitoring;

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
 * it does not know and it does not care about scheduling
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
	 * @Schedule(every 20 Minutes every TimePoint: [Due-Date
	 *                 Datadelivery]-02.30AM [Data processed]-4.30 AM)
	 */
	public ReportResult check() {
		SubDirResult dataDeliveryResult = checkDataDelivery();
		SubDirResult importResult = checkImport();
		MutationResult mutationResult = checkMutation();
		return createReport();
	}

	/**
	 * reprezinta zona din raport Data-Delivery it must return a result. It can
	 * not return null. Daca returneaza null e o eroare de programare
	 */
	private SubDirResult checkDataDelivery() {
		SubDirResult dataDeliveryStatus = null;
		// check 1.incoming
		for (SubDirChecker subDir : subDirectoriesToBeChecked) {
			// if subDir = "incoming"
			dataDeliveryStatus = subDir.checkDir();
		}
		return dataDeliveryStatus;
	}

	/**
	 * it must return a result. It can not return null. Daca returneaza null e o
	 * erare de programare
	 */
	private SubDirResult checkImport() {
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
	private MutationResult checkMutation() {
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

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

	private static Logger logger = LoggerFactory
			.getLogger(MonitoringProfileLoaderJob.class);

	public static String PROFILE_READER = "PROFILE_READER";

	private static MonitoringProfileReader monitoringProfileReader;

	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		JobKey jobKey = context.getJobDetail().getKey();
		logger.info("JobKey >" + jobKey + " executing at " + new Date());

		MonitoringProfile monitoringProfile = null;
		// MonitoringProfileReader monitoringProfileReader
		// =getMonitoringProfileReader(context);

		try {
			monitoringProfile = monitoringProfileReader.readMonitoringProfile();
		} catch (JAXBException jaxbEx) {
			logger.error("Error Reading monitoring profile", jaxbEx);
			throw new JobExecutionException(jaxbEx);
		}

		if (MonitoringProfileCache.getInstance() == null) {
			logger.debug("it's the 1st time when we run the checker");
			// it's the 1st time when we run the checker
			MonitoringProfileCache.setInstance(monitoringProfile);
			try {
				MonitoringProfileChecker.getInstance().launchCheckJob();
			} catch (SchedulerException e) {
				e.printStackTrace();
			}

		} else {
			// monitoring profile already exists
			if (MonitoringProfileCache.isProfileUpdated() == false) {
				// Do nothing. A profile exist and the CheckJob has been
				// launched
				// To make sure, just log the next fire time
				logger.debug("MonitoringProfile already exists. MonitoringProfile has not been updated");
				logger.debug("The Check Job has already been launched. Next fire time:"
						+ MonitoringProfileChecker.getInstance()
								.getRegularChecksTrigger().getNextFireTime());
			} else {
				logger.debug("MonitoringProfile already exists.");
				logger.debug("Profile was updated");
				// profile was updated
				// Check to see if the job is currently executing
				if (isCheckJobCurrentlyRunning()) {
					logger.debug("CheckJob IS CurrentlyRunning");
					MonitoringProfileCache.getInstance().setCheckJobRunning(true);
					// 1. reschedule this job a second later(hoping that
					// meanwhile the CheckJob is done)
					// 2. Or maybe a listener that will notify us that the
					// CheckJob has just finished execution
					// 3. Thread.sleep and then continue
					// 4. Notification between threads - not ok! because I don't
					// have a shared object between Jobs to call wait()/notify()
					// on!
					waitForCheckJobToFinish(monitoringProfile);
				} else {
					// CheckJob Is not currently running.
					// Just go ahead and reinitialize everything
					logger.debug("CheckJob NOT CurrentlyRunning");
					try {
						MonitoringProfileChecker.getInstance().cleanUp2();
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

	private void waitForCheckJobToFinish(MonitoringProfile monitoringProfile) {
		long start = System.currentTimeMillis();

		synchronized (MonitoringProfileCache.getInstance()) {
			while (MonitoringProfileCache.getInstance().isCheckJobRunning()) {
				logger.info("WAITING for CHECK_JOB to finish");
				try {
					MonitoringProfileCache.getInstance().wait();
				} catch (InterruptedException e) {
				}
			}
			long stop = System.currentTimeMillis();
			long timeWaited = stop - start;
			logger.debug("Milis waited:" + timeWaited);
			try {
				MonitoringProfileChecker.getInstance().cleanUp2();
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

	// This is used in production
	/*
	 * private MonitoringProfileReader
	 * getMonitoringProfileReader(JobExecutionContext context){
	 */
	/*
	 * Razvan:Job instance variables are no use for quartz Job, because Jobs are
	 * instantiated each time.
	 */
	// if(null == monitoringProfileReader){
	// JobDataMap data = context.getJobDetail().getJobDataMap();
	// monitoringProfileReader = (MonitoringProfileReader)
	// data.get(PROFILE_READER);
	// }
	/*
	 * MonitoringProfileReader monitoringProfileReader = null; JobDataMap data =
	 * context.getJobDetail().getJobDataMap(); monitoringProfileReader =
	 * (MonitoringProfileReader) data.get(PROFILE_READER); return
	 * monitoringProfileReader; }
	 */

	/**
	 * this is used in UnitTests
	 * */
	public static void setMonitoringProfileReader(
			MonitoringProfileReader profileReader) {
		MonitoringProfileLoaderJob.monitoringProfileReader = profileReader;
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

	private boolean isCheckJobCurrentlyRunning() {
		boolean result = false;
		Scheduler sched = MonitoringProfileChecker.getInstance().getScheduler();
		/*
		 * sched nu are cum sa fie null. Daca e null inseamna ca pornirea
		 * initiala nu s-a facut correct. Daca e null. deja programul nu s-a
		 * executat corect pina in punctul asta
		 */
		List<JobExecutionContext> runningJobs = null;
		try {
			runningJobs = sched.getCurrentlyExecutingJobs();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		if (null != runningJobs && runningJobs.size() > 0) {
			for (JobExecutionContext jobContext : runningJobs) {
				JobKey jobKey = jobContext.getJobDetail().getKey();
				if (jobKey == MonitoringProfileChecker.getInstance()
						.getJobKey()) {
					return true;
				}
			}
		}
		return result;
	}

}

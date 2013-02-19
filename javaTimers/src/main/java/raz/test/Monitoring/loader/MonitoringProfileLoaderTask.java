package raz.test.Monitoring.loader;

import java.util.concurrent.Callable;

import raz.test.Monitoring.MonitoringProfile;

public class MonitoringProfileLoaderTask implements Callable<MonitoringProfile>, Runnable {
	
	MonitoringProfile result;

	/**
	 * read the .csv, the .xml and create a MonitoringProfile
	 * */
	public MonitoringProfile call() throws Exception {
		
		return result;
	}

	private MonitoringProfile readProfile() {
		// TODO Auto-generated method stub, not implemented yet
		ThreadDebug.threadMessage("Readind profile");
		return null;
	}

	public void run() {
		result = null;
		result = readProfile();
		
	}
	
	

}

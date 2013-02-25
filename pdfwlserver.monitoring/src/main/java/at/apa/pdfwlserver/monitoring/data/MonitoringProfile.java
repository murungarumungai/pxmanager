package at.apa.pdfwlserver.monitoring.data;

import java.util.List;

import at.apa.pdfwlserver.monitoring.xml.CustomerBaseDir;

public class MonitoringProfile {
	/**
	 * because MonitoringProfile is the only shared Object between MonitoringProfileLoaderJob and MonitoringProfileCheckJob
	 * I decided to use this as a thread notification mechanism
	 * 
	 * This signals if the Check Job is currently running
	 * */
	private  boolean checkJobRunning = false;
	
	private final CustomerBaseDir customerBaseDirJAXB;
	private final long repeatPeriod;
	
	private List<SubDirChecker> customerFileSystemStructure;
	
	//TODO the only one thing that I am missing is the time-points from .csv

	public MonitoringProfile(CustomerBaseDir customerBaseDirJAXB, long regularCheckRepeatPeriod) {
		this.customerBaseDirJAXB = customerBaseDirJAXB;
		this.repeatPeriod = regularCheckRepeatPeriod;
	}
	
	/**
	 * this method loads from CustomerBaseDirJAXB - which contains only String-DirNames, 
	 * the actually directories on the file system.
	 * checks if all directories configured in the .xml really phisicaly exist on the file System
	 * */
	private List<SubDirChecker> loadCustomerFileSystemStructure(){
		return customerFileSystemStructure;
	}

	public CustomerBaseDir getCustomerBaseDirJAXB() {
		return customerBaseDirJAXB;
	}

	

	public long getRepeatPeriod() {
		return repeatPeriod;
	}

	public boolean isCheckJobRunning() {
		return checkJobRunning;
	}

	public void setCheckJobRunning(boolean checkJobRunning) {
		this.checkJobRunning = checkJobRunning;
	}

	
}
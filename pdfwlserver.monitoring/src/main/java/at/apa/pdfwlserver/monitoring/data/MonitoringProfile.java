package at.apa.pdfwlserver.monitoring.data;

import java.util.List;

import at.apa.pdfwlserver.monitoring.utils.PropertiesReader;



public class MonitoringProfile {
	/**
	 * because MonitoringProfile is the only shared Object between <code>MonitoringProfileLoaderJob</code> and <code>MonitoringProfileCheckJob</code>
	 * I decided to use this as a thread notification mechanism
	 * 
	 * This signals if the Check Job is currently running
	 * */
	private  boolean checkJobRunning = false;
	
	private final PropertiesReader propertiesReader;
	private final String checkedFileExtension;
	private final String errorFileExtension;
	private final long repeatPeriod;
	private final List<SubDirChecker> customerFileSystemStructure;
	private final List<Issue> issues;//issues extracted from .csv, ordered by issueDate
	

	public MonitoringProfile( List<SubDirChecker> customerFileSystemStructure, List<Issue> issues, final PropertiesReader propertiesReader 
			/*, long regularCheckRepeatPeriod, String checkedFileExtension, String errorFileExtension*/) {
		this.checkedFileExtension = propertiesReader.getCheckedFileExtension();
		this.errorFileExtension = propertiesReader.getErrorFileExtension();
		this.customerFileSystemStructure = customerFileSystemStructure;
		this.issues = issues;
		this.repeatPeriod = propertiesReader.getRegularCheckRepeatPeriod();
		this.propertiesReader = propertiesReader;
	}
	
	public String getCheckedFileExtension(){
		return checkedFileExtension;
	}
	
	public  List<SubDirChecker> getCustomerFileSystemStructure(){
		return customerFileSystemStructure;
	}
	
	public List<Issue> getIssues(){
		return issues;
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

	public String getErrorFileExtension() {
		return errorFileExtension;
	}

	/**
	 * return the instantiated PropertiesReader
	 * */
	public PropertiesReader getPropertiesReader(){
		return propertiesReader;
	} 
	
}
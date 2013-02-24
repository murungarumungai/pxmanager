package at.apa.pdfwlserver.monitoring.data;

import java.util.List;

import at.apa.pdfwlserver.monitoring.xml.CustomerBaseDir;

public class MonitoringProfile {
	
	private CustomerBaseDir customerBaseDirJAXB;
	private List<SubDirChecker> customerFileSystemStructure;
	private long repeatPeriod;
	//TODO the only one thing that I am missing is the time-points from .csv

	public MonitoringProfile(CustomerBaseDir customerBaseDirJAXB, long regularCheckRepeatPeriod) {
		this.setCustomerBaseDirJAXB(customerBaseDirJAXB);
		this.setRepeatPeriod(regularCheckRepeatPeriod);
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

	public void setCustomerBaseDirJAXB(CustomerBaseDir customerBaseDirJAXB) {
		this.customerBaseDirJAXB = customerBaseDirJAXB;
	}

	public long getRepeatPeriod() {
		return repeatPeriod;
	}

	public void setRepeatPeriod(long repeatPeriod) {
		this.repeatPeriod = repeatPeriod;
	}
}
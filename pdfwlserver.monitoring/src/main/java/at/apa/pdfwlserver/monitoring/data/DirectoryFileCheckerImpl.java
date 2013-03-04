package at.apa.pdfwlserver.monitoring.data;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class DirectoryFileCheckerImpl implements DirectoryFileChecker {
	
	private final Date earliestDataDelivery ;
    private final Date nextEarliestDataDelivery;
    private final Date dataDueDate;
    
    public DirectoryFileCheckerImpl(Date earliestDataDelivery, Date nextEarliestDataDelivery,  Date dataDueDate){
    	this.earliestDataDelivery = earliestDataDelivery;
    	this.nextEarliestDataDelivery = nextEarliestDataDelivery;
    	this.dataDueDate = dataDueDate;
    }

	public File getLatestFileWithinCheckInterval() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

/*	public File getLatestFileWithinDueDateDataDelivery() throws IOException {
		Not needed by any fileConditions!
		return null;
	}*/

}

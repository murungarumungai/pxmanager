package at.apa.pdfwlserver.monitoring.data;

import java.io.File;
import java.util.Date;

import at.apa.pdfwlserver.monitoring.xml.Status;

public class IncomingSubDirResult extends SubDirResult {
	
	/**
	 * this is the same data as <code>latestFileWithinCheckInterval</code> in the parent class
	 * but I decided to have it here for clarity, as it has different purpose
	 * */
	File incomingLastKnownFileWithinCheckInterval;
	
	/**
     * @param latestFileWithinCheckInterval - this is the latest file in directory within the check interval
     * @param latestFileOutOfCheckInterval - this is the latest file in directory (outside the check interval ,even if the check interval has expired)
     * */
    public IncomingSubDirResult(Date checkTime, File subDir,Status status, File latestFileWithinCheckInterval, File latestFileOutOfCheckInterval, File incomingLastKnownFileWithinCheckInterval){
        super( checkTime,  subDir,   status,  latestFileWithinCheckInterval,  latestFileOutOfCheckInterval);
        this.incomingLastKnownFileWithinCheckInterval = incomingLastKnownFileWithinCheckInterval;
    }
    
    public File getIncomingLastKnownFileWithinCheckInterval(){
    	return incomingLastKnownFileWithinCheckInterval;
    }

}

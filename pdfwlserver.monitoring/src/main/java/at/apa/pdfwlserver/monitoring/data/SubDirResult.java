/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.apa.pdfwlserver.monitoring.data;

import java.io.File;
import java.util.Date;

import at.apa.pdfwlserver.monitoring.xml.Status;

/**
 *
 * @author rgaston
 */
public class SubDirResult {
    
	private Date checkTime;
    private File subDir;
    //private Date receivedDate; TODO: it is now clear receivedDate to which file refers? 
    private Status status;
    private File latestFileWithinCheckInterval;
    private File latestFileOutOfCheckInterval;
    
    
    public SubDirResult(){}
    
    /**
     * @param latestFileWithinCheckInterval - this is the latest file in directory within the check interval
     * @param latestFileOutOfCheckInterval - this is the latest file in directory (outside the check interval ,even if the check interval has expired)
     * */
    public SubDirResult(Date checkTime, File subDir, /*Date receivedDate,*/ Status status, File latestFileWithinCheckInterval, File latestFileOutOfCheckInterval){
    	this.checkTime =checkTime;
    	this.subDir = subDir;
    	//this.receivedDate =receivedDate;
    	this.status = status;
    	this.latestFileWithinCheckInterval = latestFileWithinCheckInterval;
    	this.latestFileOutOfCheckInterval = latestFileOutOfCheckInterval;
    }

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public File getSubDir() {
		return subDir;
	}

	public void setSubDir(File subDir) {
		this.subDir = subDir;
	}

/*	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}*/
    
	public Status getStatus(){
		return status;
	}
	
	public File getLatestFileWithinTheCheckInterval(){
		return latestFileWithinCheckInterval;
	}
	
	public File getLatestFileOutOfCheckInterval(){
		return latestFileOutOfCheckInterval;
	}
    
}

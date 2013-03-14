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
	//We must trace the data Packets through the importing process during a CheckInterval of a certain mutation
    private boolean wasInIncoming = false; 
    //Razvan: Asta e echivalent cu this.latestFileWithinCheckInterval!=null
	//Razvan 14.03.2013 - Nu nu este echivalent cu (this.latestFileWithinCheckInterval!=null)
	//this.latestFileWithinCheckInterval!=null - reprezinta starea DirectoryFileCondition at the moment(instant) when the check was done
	
    //----
    
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
    public SubDirResult(Date checkTime, File subDir, /*Date receivedDate,*/ Status status, File latestFileWithinCheckInterval, File latestFileOutOfCheckInterval, boolean wasInIncoming){
    	this.checkTime =checkTime;
    	this.subDir = subDir;
    	//this.receivedDate =receivedDate;
    	this.status = status;
    	this.latestFileWithinCheckInterval = latestFileWithinCheckInterval;
    	this.latestFileOutOfCheckInterval = latestFileOutOfCheckInterval;
    	this.wasInIncoming = wasInIncoming;
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

	/**
	 * We need a way to know if data was in this directory,
	 * for example <code>incoming</code>, but was later moved by the importer
	 * */
	/*public boolean dataIsInThisSubDir(){
		return this.latestFileWithinCheckInterval!=null;
	}*/
	
	public boolean wasInIncoming() {
		return wasInIncoming;
	}
/*
	public void setWasInIncoming(boolean wasInIncoming) {
		this.wasInIncoming = wasInIncoming;
	}*/
    
}

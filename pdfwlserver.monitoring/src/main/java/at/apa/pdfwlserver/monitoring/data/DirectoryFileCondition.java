/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.apa.pdfwlserver.monitoring.data;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.apa.pdfwlserver.monitoring.utils.FileUtils;
import at.apa.pdfwlserver.monitoring.xml.Status;

/**
 *
 * @author rgaston
 * 
 */
public class DirectoryFileCondition {
    
	protected final File subDirPath;// the canonicalFilePath of the dir
	protected final Status status;//a FileCondition can return this status if the conditions are fulfilled, or it not not return it 
    protected final Boolean fileExists; //<fileCondition exists="true">
    protected Boolean isWithinTimePoint = null;//this condition can miss, that is, it can be null
    protected Date now;//the moment when the check is done
   
    private Date earliestDataDelivery ;
    private Date nextEarliestDataDelivery; 
    private CheckInterval checkInterval;
    
    private static Logger logger = LoggerFactory.getLogger(DirectoryFileCondition.class);
    
    
    private DirectoryFileChecker fileChecker; 
    
    public DirectoryFileCondition(boolean fileConditionExists, Boolean isWithinTimePoint, Status status, File subDirPath){
    	this.isWithinTimePoint = isWithinTimePoint;
        this.fileExists = fileConditionExists;
        this.status = status;
        this.subDirPath = subDirPath;
    }
    
    //TODO overrite this in importFileCondition
    public SubDirResult checkDirectoryForFile() throws IOException {
        SubDirResult result = null;
        
        //begin check, we chek now
        now = new Date();
        logger.debug("Began checking folder>>"+subDirPath+" at:"+now);
        /*
         * result status of this FileCondition, 
         * if the evaluation of the conditions matches the set conditions configured in .xml, then we return the configured status.
         * 
         * Else this FileCondition returns null. For folders: import, success, error, 
         * if the file doesn't exist, we do not return a status, so it's ok to return null  
         * */
        Status status = checkFileExistence();
        
       
        
        return result;
    }
    
    /**
     * get the Issues, Mutations, that are being checked
     * */
    private CheckInterval getCheckInterval(){
    	if(null == checkInterval){
    		checkInterval = CheckInterval.getMutationBeingCheckedRightNow(now);
    		earliestDataDelivery = checkInterval.getCurrentCheckedMutation().getDataEarliestDelivery();
    		nextEarliestDataDelivery = checkInterval.getNextEarliestDataDelivery();
    	} else if((now.equals(earliestDataDelivery) || now.after(earliestDataDelivery))  && now.before(nextEarliestDataDelivery)){
    		//we already have a check interval, and now() is still within the check interval, so no reason to request a new mutation
    		// just return the current one
    		return checkInterval;
    		} else {
    			//Dates are expired, make a new request for the current checked issue->mutation
    			checkInterval = CheckInterval.getMutationBeingCheckedRightNow(now);
    			earliestDataDelivery = checkInterval.getCurrentCheckedMutation().getDataEarliestDelivery();
        		nextEarliestDataDelivery = checkInterval.getNextEarliestDataDelivery();
    	}
    	return checkInterval;
    }
    
       
    protected Status checkFileExistence() throws IOException{
    	Status result = null;
    	 if(isWithinTimePoint!=null){//IsWithinTimePoint condition was set         	
         	if( (fileExists==fileExistsEvaluation()) && (isWithinTimePoint==isWithinTimepointEvaluation()) ){
         		//condition fulfilled => return status
         		result = status;
         	} else {
         		//condition not fulfilled return null
         		result = null;
         	}
         } else {
        	 if(fileExists==fileExistsEvaluation()){
        		 //condition fulfilled => return status
        		 result = status;
        	 }
         }
    	return result;
    }
    
    
    protected DirectoryFileChecker getDirectoryFileChecker(){
    	if(null==fileChecker){
    		fileChecker = new DirectoryFileCheckerImpl(earliestDataDelivery,nextEarliestDataDelivery, checkInterval.getCurrentCheckedMutation().getDataDueDate() ); 
    	}
    	return fileChecker;
    }
  
    /**
     * to be used in unit Tests
     * */
    public void setFileChecker(DirectoryFileChecker fileChecker){
    	this.fileChecker = fileChecker;
    }
    
   
    protected boolean fileExistsEvaluation() throws IOException{
    	File latestFileWithinCheckInterval = getDirectoryFileChecker().getLatestFileWithinCheckInterval();
    	if(null!= latestFileWithinCheckInterval) {
    		//File exists in folder within Check interval
            return true;
        } else {
        	//No file exists in folder within check interval
        	return false;
        }
    }
    
   
    protected boolean isWithinTimepointEvaluation(){
        
        boolean result = false;
        
        if((now.equals(earliestDataDelivery) || now.after(earliestDataDelivery))  && now.before(checkInterval.getCurrentCheckedMutation().getDataDueDate())){
        	result = true;
        }
        
        //now() - the time the check si done
        //[Due-Date Delivery] >{is before} now(){the moment in which the check is done} >{is after} [Earliest Data Delivery]
        //return 
        
        return result;
    }
    
}

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
   
    private Date earliestDataDelivery ;//cache the dates 
    private Date nextEarliestDataDelivery;//cache the dates 
    private CheckSession checkSession;
    //cache the checkInterval, so that we have the same checkInterval instance for the whole period of a checking session for a certain Issue.Mutation 
    
    private static Logger logger = LoggerFactory.getLogger(DirectoryFileCondition.class);
    
    
    private DirectoryFileChecker fileChecker; 
    
    public DirectoryFileCondition(boolean fileConditionExists, Boolean isWithinTimePoint, Status status, File subDirPath){
    	this.isWithinTimePoint = isWithinTimePoint;
        this.fileExists = fileConditionExists;
        this.status = status;
        this.subDirPath = subDirPath;
    }
    
    
    public SubDirResult checkDirectoryForFile() {
        SubDirResult result = null;
        
        //begin check, we chek now
        now = now();
        logger.debug("Began checking DirectoryFileCondition at:"+now+" checking FileCondition:"+this);
        /*
         * result status of this FileCondition, 
         * if the evaluation of the conditions matches the set conditions configured in .xml, then we return the configured status.
         * Else this FileCondition returns null. 
         */
        /*
         * For folders: import, success, error, 
         * if the file doesn't exist, we do not return a status, so it's ok to return null  
         * */
        Status status = checkFileExistence();
        
       if(status!=null){
    	   File latestFileWithinCheckInterval = getDirectoryFileChecker().getLatestFileWithinCheckInterval(
    			   getCheckInterval().getCurrentCheckedMutation().getDataEarliestDelivery(),
    			   getCheckInterval().getNextEarliestDataDelivery());
    	   
    	   result = new SubDirResult(now, subDirPath, status, latestFileWithinCheckInterval, getDirectoryFileChecker().getLatestFile());
       }
        
        return result;
    }
    
    /**
     * get the Issues, Mutations, that are being checked
     * */
    protected CheckSession getCheckInterval(){
    	if(null == checkSession){
    		checkSession = CheckSession.getMutationBeingCheckedRightNow(now);
    		if(null == checkSession){
    			//either the issues.csv is expired, or the monitoring profile is null
    			throw new IllegalArgumentException("CheckInterval for current time:"+now+" is null. " +
    					"Isses.csv is outdated, there are now issues to be checked, please update it");
    		}
    		earliestDataDelivery = checkSession.getCurrentCheckedMutation().getDataEarliestDelivery();
    		nextEarliestDataDelivery = checkSession.getNextEarliestDataDelivery();
    	} else if((now.equals(earliestDataDelivery) || now.after(earliestDataDelivery))  && now.before(nextEarliestDataDelivery)){
    		//we already have a check interval, and now() is still within the check interval, so no reason to request a new mutation
    		// just return the current one
    		return checkSession;
    		} else {
    			//Dates are expired, make a new request for the current checked issue->mutation
    			checkSession = CheckSession.getMutationBeingCheckedRightNow(now);
    			if(null == checkSession){
        			//either the issues.csv is expired, or the monitoring profile is null
    				throw new IllegalArgumentException("CheckInterval for current time:"+now+" is null. " +
        					"Isses.csv is outdated, there are now issues to be checked, please update it");
        		}
    			earliestDataDelivery = checkSession.getCurrentCheckedMutation().getDataEarliestDelivery();
        		nextEarliestDataDelivery = checkSession.getNextEarliestDataDelivery();
    	}
    	return checkSession;
    }
    
       
    protected Status checkFileExistence() {
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
    		fileChecker =  new DirectoryFileCheckerImpl(subDirPath); 
    	}
    	return fileChecker;
    }
    
    /**
     * NOTE: this is just used in UnitTesting
     * TODO: in production, the who application flow is built arround the idea that 
     * now() - is CurrentTime. so now should always return the present Time = The moment in time when the chekc is done
     * */
    protected Date now(){
    	
    		if(DirectoryFileConditionNowMocker.getNow()==null){
    			//Normal Flow, Production Flow
    			now = new Date();
    		}else{
    			//UnitTest Flow
    			now = DirectoryFileConditionNowMocker.getNow();
    		}
    	
    	return now;
    }
  
    /**
     * NOTE: to be used in unit Tests
     * */
    public void setFileChecker(DirectoryFileChecker fileChecker){
    	this.fileChecker = fileChecker;
    }
    
   
    protected boolean fileExistsEvaluation() {
    	File latestFileWithinCheckInterval = getDirectoryFileChecker().getLatestFileWithinCheckInterval(
    			getCheckInterval().getCurrentCheckedMutation().getDataEarliestDelivery(),
    			getCheckInterval().getNextEarliestDataDelivery());
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
        Date dataDueDate = getCheckInterval().getCurrentCheckedMutation().getDataDueDate();
        if( (now.equals(earliestDataDelivery) || now.after(earliestDataDelivery))  && (now.before(dataDueDate)|| now.equals(dataDueDate)) ){
        	result = true;
        }
        
        //now() - the time the check si done
        //[Due-Date Delivery] >{is before} now(){the moment in which the check is done} >{is after} [Earliest Data Delivery]
        //return 
        
        return result;
    }
    
    @Override
    public String toString(){
    	String NEW_LINE = System.getProperty("line.separator");
    	StringBuilder result = new StringBuilder();
    	result.append(this.getClass().getSimpleName());
    	result.append(NEW_LINE);
    	result.append("{");
    	result.append("subDir:").append("[").append(FileUtils.getLastFolderInPath(subDirPath)).append("]");
    	result.append("fileExists:").append("[").append(fileExists).append("]");
    	result.append("isWithinTimePoint:").append("[").append(isWithinTimePoint).append("]");
    	result.append("earliestDataDelivery:").append("[").append(earliestDataDelivery).append("]");
    	result.append("nextEarliestDataDelivery:").append("[").append(nextEarliestDataDelivery).append("]");
    	result.append("}");
    	return result.toString();
    }
}

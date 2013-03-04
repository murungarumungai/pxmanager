package at.apa.pdfwlserver.monitoring.data;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.apa.pdfwlserver.monitoring.utils.FileUtils;
import at.apa.pdfwlserver.monitoring.xml.Status;

public class IncomingDirectoryFileCondition extends DirectoryFileCondition {
	
	private static Logger logger = LoggerFactory.getLogger(IncomingDirectoryFileCondition.class);

	public IncomingDirectoryFileCondition(boolean fileConditionExists, Boolean isWithinTimePoint, Status status, File subDirPath) {
		super(fileConditionExists, isWithinTimePoint, status, subDirPath);
		
	}
	
	@Override
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
        
        /*
         * Only for incoming SubDir, there is much work to do, special conditions
         * Razvan: see activity flow diagram
         * */
        File latestFileInDirectory = null;
        Date dateReceived = null;
        if(fileExists && isWithinTimePoint==null && null!= status){
        	File latestFileWithinCheckInterval = getDirectoryFileChecker().getLatestFileWithinCheckInterval(
        			getCheckInterval().getCurrentCheckedMutation().getDataEarliestDelivery(),
        			getCheckInterval().getNextEarliestDataDelivery(), 
        			getCheckInterval().getCurrentCheckedMutation().getDataDueDate()); 
        	dateReceived = FileUtils.getReceivedDate(latestFileWithinCheckInterval);
        	//expected status: not processed yet
        } else if(fileExists==false && isWithinTimePoint==true && null!=status ){
        	//expected status: waiting
        } else if(fileExists==false && isWithinTimePoint==false && null!=status){
        	//expected status: no data received
        	latestFileInDirectory = FileUtils.getLatestFileFromDir(subDirPath);
        	dateReceived = FileUtils.getReceivedDate(latestFileInDirectory);
        }
        
        result = new SubDirResult(now, subDirPath, dateReceived, status, latestFileInDirectory);
        
        return result;
    }
	
	
/*	@Override
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
    }*/
	
	
	/*
	 * TODO: Razvan, ce se intampla cand primesti fisierul dupa dataDueDate, 
	 * that is outside the timpePoint, but within the checkInterval => ar trebui sa returneze not processed yet
	 * TestCase: fisierul este primit dupa dataDueDate, but within checkInterval
	 * */
/*	@Override 
	protected boolean fileExistsEvaluation() throws IOException{
		File latestFileWithinCheckInterval = getDirectoryFileChecker().getLatestFileWithinCheckInterval();
    	if(null!= latestFileWithinCheckInterval) {
    		//File exists in folder within Check interval
            return true;
        } else {
        	//No file exists in folder within check interval
        	return false;
        }
		
	}*/

}

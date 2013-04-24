package at.apa.pdfwlserver.monitoring.data;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.apa.pdfwlserver.monitoring.utils.DateUtils;
import at.apa.pdfwlserver.monitoring.utils.FileScanner;
import at.apa.pdfwlserver.monitoring.utils.FileUtils;
import at.apa.pdfwlserver.monitoring.xml.ErrorSpecialHandling;
import at.apa.pdfwlserver.monitoring.xml.Status;

public class ErrorDirectoryFileCondition extends DirectoryFileCondition {
	
	private static Logger logger = LoggerFactory.getLogger(ErrorDirectoryFileCondition.class);
	
	private List<ErrorSpecialHandling> errorSpecialHandling;

	public ErrorDirectoryFileCondition(boolean fileConditionExists, Boolean isWithinTimePoint, Status status, File subDirPath, List<ErrorSpecialHandling> errorSpecialHandling) {
		super(fileConditionExists, isWithinTimePoint, status, subDirPath);
		this.errorSpecialHandling = errorSpecialHandling;
	}
	
	@Override
	public SubDirResult checkDirectoryForFile(){
		SubDirResult result = null;
		if(null!=errorSpecialHandling){
			//1st step get the general status
			result = super.checkDirectoryForFile();
			if(null!=result){
				//file exists in error dir and thus we have a general status
				//2nd step find the error description file
				Date earliestDataDelivery = getCheckInterval().getCurrentCheckedMutation().getDataEarliestDelivery();
	        	Date nextEarliestDataDelivery = getCheckInterval().getNextEarliestDataDelivery();
	        	File latestFileWithinCheckInterval = getDirectoryFileChecker().getLatestFileWithinCheckInterval(earliestDataDelivery, nextEarliestDataDelivery);
	        	//we have ErrorSpecialHandling, attempt to return a more specific status
	        	String errorFileDescriptionExtension = MonitoringProfileCache.getMonitoringProfile().getErrorFileExtension();
	        	//at this point latestFileWithinCheckInterval can't be null
	        	String latestFileWithinCheckIntervalName = latestFileWithinCheckInterval.getName();
	        	File errorDescriptionFile = new File(subDirPath, latestFileWithinCheckIntervalName+errorFileDescriptionExtension);
	        	if (errorDescriptionFile.exists() && errorDescriptionFile.isFile() && errorDescriptionFile.canRead()){
	        		//scan it for error strings
	        		/**
	    			 * Implement a special handling for error-directory: For the ZIP-File also a txt-File (Filename <ZIP-File>.txt e.g. xyz.zip.txt) exists.
	    			 * If any of a configurable set of error-strings (e.g. ApaError or ApaOrCustomerError) is found in this TXT-File, set the status to another configurable value. 
	    			 * 
	    			 * */
	        		for(ErrorSpecialHandling oneErrorSpecialHandling : errorSpecialHandling){
	        			/*
	        			we can define more errorCauses, 
	        			but error specialHandling cases should be mutual exclusive, that is 
	        			there can be only one errorCause that could have had happened(caused the error), so return the first errorCause that is described in errorFileDescription
	        			*/
	        			/**
	        			 * parse the txt file and if you find any of the errorStrings
	        			 * return the status, else return null 
	        			 * */
	        			boolean containsErrorString = FileScanner.parseErrorDescriptionFile(errorDescriptionFile, oneErrorSpecialHandling.getErrorString());
	        			if(containsErrorString){
	        				Status detailedStatus = oneErrorSpecialHandling.getStatus();
	        				result.setStatus(detailedStatus);
	        			}
	        		}
	        	}
			}
		} else {
			result = super.checkDirectoryForFile();
		}
		return result;
	}
}

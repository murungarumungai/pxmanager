package raz.test.Monitoring;

import java.util.List;

/**
 * After I read the .csv and .xml here I have the:
 * 1. TimePoints from .csv so that I know what are the date.time when I must run a check
 * 2. Repeat period, so I know at what time interval a check is run
 * 3. Customer Dir Structure so I know what folders to check, 
 * the file extension(.zip) and what are the conditions, statuses, etc...
 * 4.  
 * */

public class MonitoringProfile {
	
	
	
	
	 List<SubDirChecker> subDirectoriesToBeChecked = null;

	    /**
	     * @Schedule(every 20 Minutes every TimePoint: 
	     * [Due-Date Datadelivery]-02.30AM 
	     * [Data processed]-4.30 AM)
	     */
	    public void check() {
	        checkDataDelivery();
	        checkImport();
	        checkMutation();
	        createReport();
	    }
	    
	    /**
	     * reprezinta zona din raport Data-Delivery
	     * it must return a result. It can not return null. 
	     * Daca returneaza null e o eroare de programare
	     */
	    private SubDirResult checkDataDelivery(){
	         SubDirResult dataDeliveryStatus  = null;
	         //check 1.incoming
	        for(SubDirChecker subDir : subDirectoriesToBeChecked){
	            //if subDir = "incoming"
	            dataDeliveryStatus = subDir.checkDir();
	        }
	        return dataDeliveryStatus;
	    }
	    
	    /**
	     * it must return a result. It can not return null.
	     * Daca returneaza null e o erare de programare
	     */
	    private SubDirResult checkImport(){
	        SubDirResult importStatus = null;
	        /**
	         * check the subDirectories in the order which they are in the List
	         *  2.import 3.succes 4.error
	         */
	        for(SubDirChecker subDir : subDirectoriesToBeChecked){
	             importStatus = subDir.checkDir();
	        }
	        return importStatus;
	    }
	    
	    /**
	     * it must return a result. It can not return null.
	     */
	    private MutationResult checkMutation(){
	        MutationResult result = null;
	        return result;
	    }

	    /**
	     * Compile the results and create a report,
	     * Before writing the report, read every time the FreeMarkerTemplate
	     * write StatusPage.html
	     */
	    private void createReport() {
	        throw new UnsupportedOperationException("Not yet implemented");
	    }

}

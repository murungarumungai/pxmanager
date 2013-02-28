/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.apa.pdfwlserver.monitoring.data;

import java.io.File;
import java.util.Date;

/**
 *
 * @author rgaston
 */
public  class FileCondition {
    
    private final Boolean fileExists; //<fileCondition exists="true">
    private Boolean isWithinTimePoint = null;//this contition can miss, that is, it can be null
    private boolean IsWithinCheckInterval = true; //Razvan: asta nu mai are sens, pentru ca: @see getLatestFileWithinCheckInterval()  
    //Always the latest file must be within IsWithinCheckInterval. Conditia asta se  indeplineste Automat cand apelezi metoda:getLatestFileWithinCheckInterval(); 
    private Date earliestDataDelivery ;
    private Date nextEarliestDataDelivery;
    
    public FileCondition(boolean fileConditionExists){
        this.fileExists = fileConditionExists;
    }
    
   

    SubDirResult checkFile() {
        SubDirResult result = null;
        
        if(fileExistsCONDITION()==true){
        	if(fileExistsEVALUATION()){
        		//Conditia este indeplinita
        		//return status "not yet processed"
        	}
        } /*else {
            //fileContitionExists = false
            File latestFileWithinCheckInterval = getLatestFileWithinCheckInterval();
            if(null == latestFileWithinCheckInterval){
            }
        }*/
        
        if(isWithinTimepointCONDITION()==true && isWithinTimepointEVALUATION()){
            File latestFileWithinCheckInterval = getLatestFileWithinCheckInterval();
            if(null == latestFileWithinCheckInterval  ){
                
                //conditia este indeplinita
                
                //return status "waiting"
            }
        }
        
        return result;
    }
    
    /**
     * when getting the latest file from the folder, always apply the contion <b>IsWithinCheckInterval</b>
     * that means: 
     * Next[Earliest Data delivery] >{isBefore}   now(){the moment in which the check is done} >{is after} [Earliest Data delivery] 
     */
    private File getLatestFileWithinCheckInterval(){
        File latestFile = null;
        return latestFile;
    }
    
    /**
     * This condition must be tested! 
     * daca metoda asta intoarce true inseamna ca conditia trebuie testata
     * daca e indeplinita returnam statusul
     * */
    protected boolean fileExistsCONDITION(){
        return fileExists;
    }
    
    private boolean fileExistsEVALUATION(){
    	File latestFileWithinCheckInterval = getLatestFileWithinCheckInterval();
    	if(null!= latestFileWithinCheckInterval) {
    		//Conditia este indeplinita
            return true;
        } else {
        	return false;
        }
    }
    
    /**
     * This condition must be evaluated
     * */
    protected boolean isWithinTimepointCONDITION(){
        return isWithinTimePoint;
    }
    
    /**
     * all contitions must check that now, the moment the check is done is IsWithinCheckInterval
     * TODO: maybe this should be done at a higher level on the call stack
     */
    final boolean isWithinCheckInterval(){
        return true;
    }
    
    public boolean isWithinTimepointEVALUATION(){
        
        boolean result = false;
        
        //now() - the time the check si done
        //[Due-Date Delivery] >{is before} now(){the moment in which the check is done} >{is after} [Earliest Data Delivery]
        //return 
        
        return result;
    }
    
}

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
    
    boolean fileExists = true; //<fileCondition exists="true" IsWithinTimePoint="true">
    boolean isWithinTimePoint = true;
    boolean IsWithinCheckInterval = true; //Always the latest file must be within IsWithinCheckInterval
    private Date earliestDataDelivery ;
    private Date nextEarliestDataDelivery;
    
    public FileCondition(boolean fileConditionExists){
        this.fileExists = fileConditionExists;
    }
    
   

    SubDirResult checkFile() {
        SubDirResult result = null;
        
        if(fileContitonExists()==true){
            File latestFileWithinCheckInterval = getLatestFileWithinCheckInterval();
            
            if(null!= latestFileWithinCheckInterval) {
                //Conditia este indeplinita
                //return status "not yet processed"
            }
        } /*else {
            //fileContitionExists = false
            File latestFileWithinCheckInterval = getLatestFileWithinCheckInterval();
            if(null == latestFileWithinCheckInterval){
            }
        }*/
        
        if(isWithinTimepointCondition()==true && isWithinTimepoint()){
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
    
    protected   boolean fileContitonExists(){
        return fileExists;
    }
    
    protected boolean isWithinTimepointCondition(){
        return isWithinTimePoint;
    }
    
    /**
     * all contitions must check that now, the moment the check is done is IsWithinCheckInterval
     * TODO: maybe this should be done at a higher level on the call stack
     */
    final boolean isWithinCheckInterval(){
        return true;
    }
    
    public boolean isWithinTimepoint(){
        
        boolean result = false;
        
        //now() - the time the check si done
        //[Due-Date Delivery] >{is before} now(){the moment in which the check is done} >{is after} [Earliest Data Delivery]
        //return 
        
        return result;
    }
    
}

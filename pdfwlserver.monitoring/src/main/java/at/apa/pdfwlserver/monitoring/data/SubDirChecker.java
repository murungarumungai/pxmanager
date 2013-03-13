/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.apa.pdfwlserver.monitoring.data;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author rgaston
 */
public class SubDirChecker {
    
   
    
    private final List<DirectoryFileCondition> directoryFileConditions;
    
    /**
     * @param subDirPath - the canonicalFilePath of the dir
     * */
    public SubDirChecker(List<DirectoryFileCondition> directoryFileConditions) {
    	
    	this.directoryFileConditions = directoryFileConditions;
    }
    
    public SubDirResult  checkDir() throws IOException{
        SubDirResult subDirStatus = null;
        for(DirectoryFileCondition directoryFileCondition : directoryFileConditions){
            subDirStatus = directoryFileCondition.checkDirectoryForFile();
            if(null!=subDirStatus){
            	/*
            	 * Depending on the system state, Only one condition should happen and be returned, 
            	 * reflecting the system state, so if status!=null return a result
            	 * */
            	return subDirStatus;
            }
        }
        return subDirStatus;
    }
}

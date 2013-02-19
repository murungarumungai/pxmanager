/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package raz.test.Monitoring;

import java.io.File;
import java.util.List;

/**
 *
 * @author rgaston
 */
public class SubDirChecker {
    
    /* TODO: what is a File reference? when we send a File reference in Java, does it mean that we have an open File Handler to that directory
     * What is the best way? 
     * a open a File Reference, keep it on and send it as parameter?
     * b store a String(DirPath) and who ever needs to make an operation on that Dir, should get a File reference, use it, close it
     */
    File subDir; //the subDir that is being checked. Must be a valid FileSystem Directory
    List<FileCondition> fileConditions;
    
    public SubDirResult  checkDir(){
        SubDirResult subDirStatus = null;
        for(FileCondition fileCondition : fileConditions){
            subDirStatus = fileCondition.checkFile();
        }
        return subDirStatus;
    }
}

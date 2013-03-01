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
    
	Date checkTime;
    File subDir;
    Date receivedDate;
    Status status;
    File latestFilePath;
    
}

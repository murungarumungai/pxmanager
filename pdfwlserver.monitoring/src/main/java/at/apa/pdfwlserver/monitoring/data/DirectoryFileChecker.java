package at.apa.pdfwlserver.monitoring.data;

import java.io.File;
import java.io.IOException;

public interface DirectoryFileChecker {
	
	 //--------------------
    //private boolean IsWithinCheckInterval = true; //Razvan: asta nu mai are sens, pentru ca: @see getLatestFileWithinCheckInterval()  
    //Always the latest file must be within IsWithinCheckInterval. Conditia asta se  indeplineste Automat cand apelezi metoda: getLatestFileWithinCheckInterval(); 
    //--------------------
    /**
     * 
     * when getting the latest file from the folder, always apply the condition <b>IsWithinCheckInterval</b>
     * that means: 
     * Next[Earliest Data delivery] >{isBefore}   now(){the moment in which the check is done} >{is after} [Earliest Data delivery] 
     */
	
	File getLatestFileWithinCheckInterval() throws IOException;

	//File getLatestFileWithinDueDateDataDelivery() throws IOException; not needed
}

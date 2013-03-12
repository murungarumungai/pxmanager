package at.apa.pdfwlserver.monitoring.data;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public interface DirectoryFileChecker {
	
	 //--------------------
    //private boolean IsWithinCheckInterval = true; //Razvan: asta nu mai are sens, pentru ca: @see getLatestFileWithinCheckInterval()  
    //Always the latest file must be within IsWithinCheckInterval. Conditia asta se  indeplineste Automat cand apelezi metoda: getLatestFileWithinCheckInterval(); 
    //--------------------
    /**
     * 
     * when getting the latest file from the folder, always apply the condition <b>IsWithinCheckInterval</b>
     * that means: 
     * Next[Earliest Data delivery] >{isBefore}  {the date of the latest file} >{is after} [Earliest Data delivery] 
     */
	
	File getLatestFileWithinCheckInterval(Date earliestDataDelivery, Date nextEarliestDataDelivery) throws IOException;
	
	/**
	 * Return the latest file from this directory
	 * @return may return null if directory is empty
	 * */
	public File getLatestFile();

	//File getLatestFileWithinDueDateDataDelivery() throws IOException; not needed
}

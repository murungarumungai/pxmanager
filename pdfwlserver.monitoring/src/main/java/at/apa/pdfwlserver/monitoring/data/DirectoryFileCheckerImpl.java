package at.apa.pdfwlserver.monitoring.data;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class DirectoryFileCheckerImpl implements DirectoryFileChecker {
	
	private static DirectoryFileCheckerImpl _instance = null;
	
	private DirectoryFileCheckerImpl(){}
	
	public static DirectoryFileChecker getInstance(){
		if(null == _instance){
			return new DirectoryFileCheckerImpl();
		} else {
			return _instance;
		}
	}

	public File getLatestFileWithinCheckInterval(Date earliestDataDelivery, Date nextEarliestDataDelivery,  Date dataDueDate) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}



}

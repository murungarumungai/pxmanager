package at.apa.pdfwlserver.monitoring.data;

import java.util.Date;

/**
 * I need this class just in UnitTest to setup TestCases, 
 * which depending on <code>now()</code> can return different statuses
 * */
public class DirectoryFileConditionNowMocker {
	
	private static Date now = null;
	
	public static void setNow(Date now){
		DirectoryFileConditionNowMocker.now = now;
	}
	
	public static Date getNow(){
		return now;
	}

}

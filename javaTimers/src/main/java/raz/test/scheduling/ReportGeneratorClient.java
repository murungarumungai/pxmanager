package raz.test.scheduling;

import java.util.Timer;

public class ReportGeneratorClient {
	
	static final long SECOND = 1000;
	
	public static void main(String[] args){
		
		//every Timer object starts a thread in the background.
		 Timer timer =  new Timer();
		 
		 ReportGenerator reportTask = new ReportGenerator();
		 
		 long delay = 10;//ms
		
		 long repeatPeriod = 10*SECOND;//every 10 seconds
		 
		 timer.schedule(reportTask, delay, repeatPeriod);
	}

}

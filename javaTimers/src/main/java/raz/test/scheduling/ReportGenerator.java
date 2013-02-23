package raz.test.scheduling;

import java.util.TimerTask;

/**
 * http://oreilly.com/pub/a/java/archive/quartz.html?page=1
 * */

public class ReportGenerator extends TimerTask{

	@Override
	public void run() {
		System.out.println("["+Thread.currentThread()+"]"+"Generating report");
		//TODO generate report
	}

		
}

package raz.timer;

import static raz.callable.RazLongCalculationSingleThread.*;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	static class ReportGenerator extends TimerTask {

		@Override
		public void run() {
			debugThreadInfo("Reading .csv, .xml => loading Monitoring Profile");
		}
	}
	
	public static void main(String[] args){
		 Timer timer =  new Timer();
		 ReportGenerator task = new ReportGenerator();
		 timer.schedule(task, 0, 5*SECOND);
	}

}

package blocking.queue.logger.example;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

public class ProducerLoggerClient implements Runnable {

	
	private ArrayBlockingQueue<String> messageQueue = new ArrayBlockingQueue<String>(10);
	
	Logger logger =  new Logger(messageQueue); 
	
	public void run(){
		
		String someMessage = produce();
		try{
			while(true){
				messageQueue.put(someMessage);
			}
		} catch (InterruptedException e){}
		
	}
	
	private String produce(){
		String result = "";
		 Date d1 = new Date();
		result = d1.toString();
		return result;
	};
	
}

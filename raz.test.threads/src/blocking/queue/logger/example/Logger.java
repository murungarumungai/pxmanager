package blocking.queue.logger.example;

import java.util.concurrent.BlockingQueue;

public class Logger implements Runnable{

	private BlockingQueue<String> msgQueue;

	public Logger(BlockingQueue<String> mq) {
		msgQueue = mq;
	}

	public void run() {
		try {

			while (true) {
				String message = msgQueue.take();
				// Log the message, consume the message
				System.out.println("Consumed message:" + message);
			}
		} catch (InterruptedException ie) {
		}
	}

}

package raz.callable;

public class SynchronizedClass {
	
	private volatile boolean focusGainedFlag;
	
	public void setFocusGainedFlag(){
		focusGainedFlag = true;
	}

}

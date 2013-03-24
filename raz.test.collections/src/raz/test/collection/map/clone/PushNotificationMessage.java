package raz.test.collection.map.clone;

import java.io.Serializable;

public class PushNotificationMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private String message;
	private String rightButtonText;
	
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getRightButtonText() {
		return rightButtonText;
	}
	
	public void setRightButtonText(String rightButtonText) {
		this.rightButtonText = rightButtonText;
	}
	
	private String getReferenceString() {
	    StringBuffer buf = new StringBuffer();
	    if (message != null) {
	        buf.append(message);
	    }
	    buf.append('/');
	    if (rightButtonText != null) {
	        buf.append(rightButtonText);
	    }
	    return buf.toString();
	}
	
	@Override
	public int hashCode() {
	    return getReferenceString().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!(obj instanceof PushNotificationMessage)) {
	        return false;
	    }
	    PushNotificationMessage other = (PushNotificationMessage) obj;
	    return getReferenceString().equals(other.getReferenceString());
	}
	
}

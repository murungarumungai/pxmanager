package at.apa.pdfwlserver.monitoring.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Status {
	
	@XmlElement
	private String text;
	@XmlElement
	private String todo;
	
	public Status(){
		
	}

	public String getText() {
		return text;
	}

	public String getTodo() {
		return todo;
	}
	
}

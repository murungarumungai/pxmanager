package at.apa.pdfwlserver.monitoring.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder =  {
	    "errorString", "status"}
	)
public class ErrorSpecialHandling {
	
	@XmlAttribute(required=true)
	private int id;
	@XmlElement
	private List<String> errorString;
	@XmlElement
	private Status status;
	
	public ErrorSpecialHandling() {
		
	}

	public int getId() {
		return id;
	}

	public List<String> getErrorString() {
		return errorString;
	}

	public Status getStatus() {
		return status;
	}

}

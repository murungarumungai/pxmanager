package at.apa.pdfwlserver.monitoring.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FileCondition {

	@XmlAttribute(required = true)
	private Boolean exists;

	@XmlAttribute(required = false, name = "IsWithinTimePoint")
	private Boolean isWithinTimePoint;

	@XmlElement
	private Status status;

	@XmlElement(required = false)
	private List<ErrorSpecialHandling> errorSpecialHandling;

	public FileCondition() {

	}

	public Boolean isExists() {
		return exists;
	}

	public Status getStatus() {
		return status;
	}

	public List<ErrorSpecialHandling> getErrorSpecialHandling() {
		return errorSpecialHandling;
	}

	public Boolean isWithinTimePoint() {
		return isWithinTimePoint;
	}

}

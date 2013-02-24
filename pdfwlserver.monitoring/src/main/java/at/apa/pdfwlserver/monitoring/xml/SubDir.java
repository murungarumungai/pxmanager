package at.apa.pdfwlserver.monitoring.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SubDir {
	
	@XmlAttribute(required=true)
	private String name;
	
	@XmlElement
	private List<FileCondition> fileCondition;
	
	public SubDir(){
		
	}

	public String getName() {
		return name;
	}

	public List<FileCondition> getFileCondition() {
		return fileCondition;
	}
	
}

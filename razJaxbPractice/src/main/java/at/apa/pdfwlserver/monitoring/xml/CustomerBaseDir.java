/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.apa.pdfwlserver.monitoring.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author rgaston
 */

@XmlRootElement
@XmlType(name = "CustomerBaseDir")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerBaseDir {

	@XmlAttribute(required = true)
	private String name;
	@XmlElement
	private List<SubDir> subDir;

	public CustomerBaseDir() {
	}

	public String getName() {
		return name;
	}

	public List<SubDir> getSubDir() {
		return subDir;
	}

}

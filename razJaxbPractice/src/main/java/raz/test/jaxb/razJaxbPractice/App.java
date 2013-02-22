package raz.test.jaxb.razJaxbPractice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import at.apa.pdfwlserver.monitoring.xml.CustomerBaseDir;
import at.apa.pdfwlserver.monitoring.xml.SubDir;

/**
 * Hello world!
 *
 */
public class App{

    public static void main(String[] args) throws Exception{
       
        //System.out.println("Hello World!");
        JAXBContext jc = JAXBContext.newInstance(CustomerBaseDir.class);
        
        File xml = new File("CustomerFolderStructureConfiguration3.xml");
        
        CustomerBaseDir customerStructure = unMarshal(jc, xml);
        
        customerStructure.toString();
        
        //marshal(jc);
        
    }
    
    public static CustomerBaseDir unMarshal(JAXBContext jc, File xml) throws Exception{
    	CustomerBaseDir result = null;
    	Unmarshaller u = jc.createUnmarshaller();
    	result = (CustomerBaseDir) u.unmarshal(xml);
    	
    	return  result;
    }
    
    public static void marshal(JAXBContext jc) throws Exception{
    	
    	CustomerBaseDir customerBaseDir = new CustomerBaseDir();
    	//customerBaseDir.setName("derStandard");
    	
    	List<SubDir> subDirs = new ArrayList<SubDir>();
    	subDirs.add(new SubDir());
    	subDirs.add(new SubDir());
    	
    	//customerBaseDir.getSubDir().addAll(subDirs);
    	Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(customerBaseDir, System.out);
    	
    }
}

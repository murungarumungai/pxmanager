package at.apa.pdfwlserver.monitoring.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * UnMarshals <i>XML-> Java</i> a customer file system structure
 *
 */
public class CustomerDirStructureMarshaler {

    private static JAXBContext jc = null;

    private static JAXBContext getJAXBContext() throws JAXBException {
        if (null == jc) {
        	jc = JAXBContext.newInstance(CustomerBaseDir.class);
        }
        return jc;
    }

    public static CustomerBaseDir unMarshal(File xmlFilePath) throws JAXBException {
        CustomerBaseDir result;
        Unmarshaller u = getJAXBContext().createUnmarshaller();
        result = (CustomerBaseDir) u.unmarshal(xmlFilePath);
        return result;
    }
    
    
}

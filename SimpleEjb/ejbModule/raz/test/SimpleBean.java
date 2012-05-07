package raz.test;

import javax.ejb.Local;
import javax.ejb.Stateless;
import raz.test.ejb.HelloBeanLocal;
import raz.test.ejb.HelloBeanRemote;

/**
 * Session Bean implementation class SimpleBean
 */
@Stateless
//@Local({HelloBeanLocal.class, HelloBeanRemote.class})
public class SimpleBean implements HelloBeanLocal, HelloBeanRemote {

    /**
     * Default constructor. 
     */
    public SimpleBean() {
        
    }

	@Override
	public String hello(String param) {
		
		return "Hello"+param;
	}

}

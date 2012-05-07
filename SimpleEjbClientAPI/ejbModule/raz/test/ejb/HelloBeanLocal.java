package raz.test.ejb;

import javax.ejb.Local;

@Local
public interface HelloBeanLocal {

	String hello(String param);
	
}

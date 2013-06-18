package raz.test.resteasy;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//Alternative REST Service Registration

/**
 * Let's say you have the Resteasy servlet configured and reachable at a root path 
 * of http://myhost.com/services.
 * */
//https://docs.jboss.org/author/display/AS7/JAX-RS+Reference+Guide
@ApplicationPath("/restapp")
public class MyRESTApplication extends Application {
	

}

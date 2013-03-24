package annotations.main;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import annotations.testbean.MySpringBeanWithDependency;


public class Main {
	
	public static void main(String[] args){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("/application-context.xml"); 
		
		BeanFactory factory = context;
		
		MySpringBeanWithDependency test = (MySpringBeanWithDependency) factory
		        .getBean("mySpringBeanWithDependency");
		
		test.run();
	}

}

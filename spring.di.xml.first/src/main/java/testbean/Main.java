package testbean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		/*
		 * MySpringBeanWithDependency bean = new MySpringBeanWithDependency();
		 * bean.run();
		 */

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans-configuration.xml");
		BeanFactory factory = context;
		MySpringBeanWithDependency test = (MySpringBeanWithDependency) factory
				.getBean("mySpringBeanWithDependency");
		test.run();

	}

}

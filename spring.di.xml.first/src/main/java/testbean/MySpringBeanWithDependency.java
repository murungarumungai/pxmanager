package testbean;

import writer.IWriter;

/**
 * Clasa asta, which is a JavaBean has a dependency of IWriter
 * Which is managed by Spring (.xml configuration)
 * */

public class MySpringBeanWithDependency {
	
	private IWriter writer;
	
	public void setWriter(IWriter writer){
		this.writer = writer;
	}
	
	public void run(){
		String s = "This is my test";
		writer.write(s);
	}

}

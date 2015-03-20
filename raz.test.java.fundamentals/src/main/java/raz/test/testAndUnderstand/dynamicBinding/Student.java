package raz.test.testAndUnderstand.dynamicBinding;

public class Student  extends Person{
	
	@Override
	public void talk() {		
		//super.talk();
		System.out.println("I am a Student");
	}

}

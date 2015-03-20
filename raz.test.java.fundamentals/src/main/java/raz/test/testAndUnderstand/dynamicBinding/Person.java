package raz.test.testAndUnderstand.dynamicBinding;

public class Person {
	
	public void talk(){
		System.out.println("I am a Person");
	}
	
	
	public static void main(String[] args){
		Person p = new Student();
		p.talk();
	}

}

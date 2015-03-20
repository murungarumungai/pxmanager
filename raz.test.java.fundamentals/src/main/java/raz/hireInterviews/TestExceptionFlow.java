package raz.hireInterviews;

public class TestExceptionFlow {

	
	public static void main(String[] args){
		Person p =null;
		try{
			p.talk();
		} catch (NullPointerException npe){
			System.out.println("NullPointerException occured");
		} catch (Exception e) {
			System.out.println("Exception");
		}
		System.out.println("Everything went fine");
	}
	
}

class Person{
	void talk(){}
}

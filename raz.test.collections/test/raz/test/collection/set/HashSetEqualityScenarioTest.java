package raz.test.collection.set;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * I want to make sure that 2 Set<String> are equal if they contain the same elements but in different order
 * */
public class HashSetEqualityScenarioTest {

	HashSetEqualityScenario testScenarioProvider = new HashSetEqualityScenario();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		
		int runs = 10;
		
		while(runs>0){
			oneRun();
			System.out.println("----------------------------------------------------");
			runs--;
		}
				
		
	}
	
	private  void oneRun(){
		List<String> testDataInput = testScenarioProvider.generateRandomStrings(1000);
		System.out.println("testInput>>"+testDataInput);
		
		List<String> random1 = HashSetEqualityScenario.randomize(testDataInput);
		assertFalse(testDataInput.equals(random1));
		System.out.println("random1>>"+random1);
		
		List<String> random2 = HashSetEqualityScenario.randomize(testDataInput);
		assertFalse(testDataInput.equals(random1));
		assertFalse(random2.equals(random1));
		System.out.println("random2>>"+random2);
		
		Set<String> set1 = new HashSet<String>(random1);
		System.out.println("set1>>"+set1);
		Set<String> set2 = new HashSet<String>(random2);
		System.out.println("set2>>"+set2);
		
		assertTrue(set1.equals(set2));
	}

}

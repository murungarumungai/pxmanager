package raz.test.collection.set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HashSetEqualityScenario {
	
	public List<String> generateRandomStrings(int numberOfItems){
		List<String> result = new ArrayList<String>(numberOfItems);
		for(int i =0; i<numberOfItems; i++){
			String random = StringGenerator.generatePassword(8);
			result.add(random);
		}
		return result;
	}
	
	//https://stackoverflow.com/questions/4228975/how-to-randomize-arraylist/4229001#4229001
	/**
	 * Returns a new List which has all the elements of the original List,
	 * but are randomized
	 * */
	public static List<String> randomize(List<String> original){
		List<String> list = new ArrayList<String>(original);
		long seed = System.nanoTime();
		Collections.shuffle(list, new Random(seed));
		return list;
	}

}

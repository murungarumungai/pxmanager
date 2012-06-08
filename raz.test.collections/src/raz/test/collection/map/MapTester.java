package raz.test.collection.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapTester {

	static String NAME = "name";
	static String AGE = "age";
	static String MARRIED = "isMarried";

	Map<String, Object> employeeFields = null;

	private void init() {
		employeeFields = new HashMap<String, Object>();

		employeeFields.put(NAME, new String("Razvan"));
		employeeFields.put(AGE, new Integer(28));
		employeeFields.put(MARRIED, new Boolean(true));
	}

	private void traverseTheMap() {

		Set<Entry<String, Object>> keySet = employeeFields.entrySet();
		Iterator<Entry<String, Object>> it = keySet.iterator();

		while (it.hasNext()) {
			Map.Entry<String, Object> keyValue = it.next();

			String fieldName = keyValue.getKey();
			Object fieldValue = keyValue.getValue();

			System.out.println(fieldName + "[" + fieldValue + "]");
			it.remove(); // avoids a ConcurrentModificationException
		}

	}
	
	/**
	 * http://stackoverflow.com/questions/1066589/java-iterate-through-hashmap
	 * Method #1: Iterating over entries using For-Each loop.
	 * **/
	private void traverseAndPrintMap(){
		for(Map.Entry<String, Object> entry : employeeFields.entrySet()) {
			String fieldName = entry.getKey();
			Object fieldValue = entry.getValue();

			System.out.println(fieldName + "[" + fieldValue + "]");
		}
	}

	private void removeAgeValue() {
		Iterator<Entry<String, Object>> it = employeeFields.entrySet()
				.iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> keyValue = it.next();
			if (keyValue.getKey().equals(AGE)) {
				employeeFields.remove(AGE);
			}
			//it.remove(); // avoids a ConcurrentModificationException
		}
	}

	public static void main(String[] args) {
		MapTester t = new MapTester();
		t.init();
		t.traverseAndPrintMap();
		//t.removeAgeValue();
		t.removeNameValue();
		t.traverseAndPrintMap();
	}
	

	private void removeNameValue(){
		//iterating over keys only
		for(String key : employeeFields.keySet()){
			if(key.equals(NAME)){
				employeeFields.remove(key);
				/*
				 * This removes the Key all together! The map does not contain the key anymore!
				 * */
			}
		}
	}

}

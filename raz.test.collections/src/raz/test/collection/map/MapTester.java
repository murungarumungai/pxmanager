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
	
	private void init(){
		employeeFields = new HashMap<String, Object>();
		
		employeeFields.put(NAME, new String("Razvan"));
		employeeFields.put(AGE, new Integer(28));
		employeeFields.put(MARRIED, new Boolean(true));
	}
	
	private void traverseTheMap(){
		
		Set<Entry<String, Object>> keySet = employeeFields.entrySet();
		Iterator<Entry<String, Object>> it = keySet.iterator();
		
		while (it.hasNext()){
			Map.Entry<String, Object> keyValue = it.next();
			
			String fieldName = keyValue.getKey();
			Object fieldValue = keyValue.getValue();
			
			System.out.println(fieldName + "[" + fieldValue + "]");
		}
		
	}
	
	

}

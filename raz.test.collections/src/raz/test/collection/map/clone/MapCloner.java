package raz.test.collection.map.clone;

import java.util.HashMap;
import java.util.Map;

public class MapCloner {
	
	
	public static void main(String[] args){

		//testCaseShallowCopy();
		
		testCaseDeepCopy();
	}
	
	public static void testCaseDeepCopy(){
		Map<String, PushNotificationMessage> original = getMap();
		traverseAndPrintMap(original);
		
		Map<String, PushNotificationMessage> deepCopy = clonePushNotificationMessages(original);
		deepCopy.get("en").setMessage("modified");
		traverseAndPrintMap(deepCopy);
		
		traverseAndPrintMap(original);
	}
	
	public static void testCaseShallowCopy(){
		Map<String, PushNotificationMessage> original = getMap();
		traverseAndPrintMap(original);
		
		Map<String, PushNotificationMessage> shallowCopy = new HashMap<String, PushNotificationMessage>(original);
		shallowCopy.get("en").setMessage("modified");
		traverseAndPrintMap(shallowCopy);
		
		traverseAndPrintMap(original);
	}
	
	/**
	 * @return returns a new instance of a map with 2 distinct objects
	 * */
	public static Map<String, PushNotificationMessage> getMap(){
		
		Map<String, PushNotificationMessage> original = new HashMap<String, PushNotificationMessage>();
		PushNotificationMessage pushM1 = new PushNotificationMessage();
		pushM1.setMessage("M1");
		PushNotificationMessage pushM2 = new PushNotificationMessage();
		pushM2.setMessage("M2");
		original.put("en", pushM1);
		original.put("de", pushM2);
		return original;
	}
	
	
	/**
	 * http://stackoverflow.com/questions/1066589/java-iterate-through-hashmap
	 * Method #1: Iterating over entries using For-Each loop.
	 * **/
	public static void traverseAndPrintMap(Map<String, PushNotificationMessage> map){
		System.out.println(map.hashCode());
		for(Map.Entry<String, PushNotificationMessage> entry : map.entrySet()) {
			
			String fieldName = entry.getKey();
			PushNotificationMessage fieldValue = entry.getValue();

			System.out.println("["+fieldName+"]" + "{" + fieldValue.hashCode() + "}" + "["+fieldValue.getMessage()+"]"+"["+fieldValue.getRightButtonText()+"]");
		}
	}
	
	/**
	 * @return returns a clone/deep copy of the original Map
	 * */
	public static Map<String, PushNotificationMessage> clonePushNotificationMessages(Map<String, PushNotificationMessage> original){
		Map<String, PushNotificationMessage> result = new HashMap<String, PushNotificationMessage>(original.size());
		//clone keys
		PushNotificationMessage value;
		for(String key : original.keySet()){
			value = new PushNotificationMessage();
			value.setMessage(original.get(key).getMessage());
			value.setRightButtonText(original.get(key).getRightButtonText());
			result.put(new String(key), value);
		}
		return result;
	}

}

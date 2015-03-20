package raz.test.tree.json;
import org.apache.commons.json.JSONObject;

public class JsonReader {

	 public static Object parseJSON(String json){
		 Object result = new Object();
		 JSONObject input = JSONObject.fromObject(json);
		// Data
	    JSONObject data = input.getJSONObject("entries");
	    
	   JSONArray leaders = input.getJSONArray(Segment.JSON_LEADERS);
	   
	   Iterator it = leaders.iterator();
		 return result;
	 }
	
}

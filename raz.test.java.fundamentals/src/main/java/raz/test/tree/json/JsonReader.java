package raz.test.tree.json;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

//import org.json.JSONArray;
//import org.json.JSONObject;

import java.util.List;
import java.util.Map;





import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;




import raz.test.file.io.StringUtil;

//import org.apache.commons.json.JSONObject;

public class JsonReader {
	
	public static void jsonJackson() throws JsonProcessingException, IOException{
		//http://www.journaldev.com/2324/jackson-json-processing-api-in-java-example-tutorial
		
		String currentDir = "";
		 try {
			 currentDir = new java.io.File( "." ).getCanonicalPath();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		File f  = new File(currentDir,"src/main/java/raz/test/tree/json/input.json");
		System.out.println(">>"+f.getAbsolutePath());
		String json = "";
		//read json file data to String
		Path path = Paths.get(f.getAbsolutePath());
		byte[] byteArray = null;
		try {
			 byteArray = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		json = new String(byteArray);
		
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		
		//read JSON like DOM Parser
		JsonNode rootNode = objectMapper.readTree(json);
		
		JsonNode entries = rootNode.path("entries");
		
		Iterator<JsonNode> elements = entries.elements();
		
		Map<String, SimpleNode> cache = new HashMap<String,SimpleNode>();
		
		//create ObjectMapper instance
		ObjectMapper nodeMapper = new ObjectMapper();
		nodeMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		
		while(elements.hasNext()){
			JsonNode node = elements.next();
			JsonNode nodeNode = node.path("node");
			
			
			System.out.println(">>"+node);
			System.out.println(">>"+nodeNode);
			
			//Convert JSON to Object
			SimpleNode simpleNode = nodeMapper.readValue(nodeNode.toString(), SimpleNode.class);
			
			cache.put(simpleNode.getId(), simpleNode);
		}
		
		//- Acum vreau sa imi construiec Node-urile si sa le adaug la o lista?
		
		List<Node> allNodes = new ArrayList<Node>();
		Tree tree = null;
		for(String nodeId : cache.keySet()){
			SimpleNode nod = cache.get(nodeId);
			if(nod.getParentId()==null || nod.getParentId().isEmpty()){
				//It means that this is the root node
				Node root = new Node(nod.getId());
				tree = new Tree(root);
				
				//process his leafs
				processNodes(root, nod.getChildrenId(), cache);
			}
			
		}
		
		System.out.println(tree.toString());
		
	}
	
	/** Recursive Process Nodes */
	public static void processNodes(Node parent, List<String> childNodes, Map<String, SimpleNode> cache){
		if(childNodes==null || childNodes.isEmpty() || ( childNodes.size()==1 && childNodes.get(0).trim().isEmpty() ) ){
			return;//Stop recursion
		} else{
			for(String childId : childNodes){
				SimpleNode cNode = cache.get(childId);
				if(cNode == null){
					System.err.println("Node Id:"+childId+" Not found in chache");
					continue;
				}
				Node childNode = new Node(cNode.getId());
				parent.getChildren().add(childNode);//For Parent: set the Children
				childNode.setParent(parent);//For Child: set the Parent
				processNodes(childNode, cNode.getChildrenId(), cache);
			}
		}
	}
	
	
	
	public static void main(String[] args){
		
		try {
			jsonJackson();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		String currentDir = "";
		 try {
			 currentDir = new java.io.File( "." ).getCanonicalPath();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		File f  = new File(currentDir,"src/main/java/raz/test/tree/json/input.json");
		System.out.println(">>"+f.getAbsolutePath());
		String json = "";
		try {
			json = StringUtil.readFile(f);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		*/
		//parseJSON(json);
	}

/*	 public static Object parseJSON(String json){
		 
		 
		 
		 Object result = new Object();
		 JSONObject input = JSONObject.fromObject(json);
		// Data
	    //JSONObject data = input.getJSONObject("entries");
	    
	   JSONArray entries  = input.getJSONArray("entries");
	   
	   //Iterator it = entries.
		 return result;
	 }*/
	
}

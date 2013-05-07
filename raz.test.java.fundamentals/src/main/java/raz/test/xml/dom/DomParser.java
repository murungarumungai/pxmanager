package raz.test.xml.dom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomParser {

	public static void main(String[] args) {

		try {
			File f = new File(".");
			System.out.println(f.getAbsolutePath());
			String xml = parseXML();
			System.out.println("----");
			System.out.println("xml:"+xml);
		} catch (Exception e) {
			
			e.printStackTrace();
		} 

	}
	
	public static String parseXML() throws ParserConfigurationException, FileNotFoundException, SAXException, IOException{
		String result = "null";
		
		//Creating a Java DOM XML parser is done using the
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		builder = builderFactory.newDocumentBuilder();
		
		//The DOM Document object represents an XML document
		Document document = builder.parse(new FileInputStream("text.xml"));
		
		/**
		 * A DOM object contains a lot of different nodes connected in a tree-like structure.
		 * At the top is the Document object. 
		 * The Document object has a single root element, which is returned by calling getDocumentElement() like this:
		 * */
		Element rootElement = document.getDocumentElement();
		System.out.println("Root element :" + rootElement.getNodeName());
		//DOM Elements, Child Elements, and the Node Interface
		/**
		 * The root element has children which can be elements, comments, processing instructions, characters etc. 
		 * You get the children of an element like this:
		 * */
		/**
		 * The Node interface is a superinterface for pretty much all of the different node types in DOM. 
		 * This means, that the Document interface inherits from (extends) Node, 
		 * the Element interface extends Node, 
		 * the Attr (attribute) interface extends Node etc.
		 * */
		NodeList nodes = rootElement.getChildNodes();
		printNodeList(nodes);
		return document.getTextContent();
	}
	
	private static void printNodeList(NodeList nodes){
		
		for(int i=0; i<nodes.getLength(); i++){
			Node oneNode = nodes.item(i);
			
			if(oneNode.getNodeType() == Node.ELEMENT_NODE){
				
				// get node name and value
				System.out.println("\n");
				System.out.println("<"+oneNode.getNodeName()+">");
				System.out.println("Node Value =" + oneNode.getTextContent());
				
				if(oneNode.hasAttributes()){
					// get attributes names and values
					NamedNodeMap nodeMap = oneNode.getAttributes();
					for (int j = 0; j < nodeMap.getLength(); j++) {
						Node node = nodeMap.item(i);
						System.out.println("attr name : " + node.getNodeName());
						System.out.println("attr value : " + node.getNodeValue());
					}
				}
				
				if(oneNode.hasChildNodes()){
					//loop again if has child nodes
					printNodeList(oneNode.getChildNodes());
				}
				
				System.out.println("</"+oneNode.getNodeName()+">");
			}
		}
		
	}

}

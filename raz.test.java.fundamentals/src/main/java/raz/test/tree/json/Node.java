package raz.test.tree.json;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private final String id;
	
	private Node parent;
	
	private List<Node> children = new ArrayList<Node>();
	
	public Node(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

}

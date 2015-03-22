package raz.test.tree.json;

import java.util.List;

public class Tree {
	
	/**
	 * - a tree can have only one ROOT node
	 * */
	private final Node root;
	
	//private List<Node> nodes;//All of the Nodes, except the root?
	
	public Tree(Node root){
		this.root = root;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(root.getId());
		String leftIdentation = "+---";
		addNode(root.getChildren(), sb, leftIdentation);
		return sb.toString();
	}
	
	private void addNode( List<Node> children, StringBuilder sb, String leftIdentation){
		if(children==null){
			return;
		} else{
			for(Node child : children){
				sb.append("\n");
				sb.append(leftIdentation);
				sb.append(child.getId());
				addNode(child.getChildren(), sb, leftIdentation+"+---");
			}
			
		}
		
	}
	

}

package raz.test.tree.json;

import java.util.List;


public class SimpleNode {

	private String id;
	
	private List<String> childrenId;
	
	private String parentId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getChildrenId() {
		return childrenId;
	}

	public void setChildrenId(List<String> childrenId) {
		this.childrenId = childrenId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
}

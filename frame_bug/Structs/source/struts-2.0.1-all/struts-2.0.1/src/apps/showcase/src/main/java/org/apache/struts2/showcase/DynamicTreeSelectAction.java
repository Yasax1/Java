package org.apache.struts2.showcase;

import org.apache.struts2.showcase.ajax.tree.Category;

import com.opensymphony.xwork2.ActionSupport;

//START SNIPPET: treeExampleDynamicJavaSelected

public class DynamicTreeSelectAction extends ActionSupport {

	private long nodeId;
	private Category currentCategory;
	
	
	public void setNodeId(long nodeId) {
		this.nodeId = nodeId;
	}
	public long getNodeId() {
		return nodeId;
	}
	
	
	public String execute() throws Exception {
		currentCategory = Category.getById(nodeId);
		return SUCCESS;
	}
	
	
	public String getNodeName() {
		return currentCategory.getName();
	}
}

//START SNIPPET: treeExampleDynamicJavaSelected


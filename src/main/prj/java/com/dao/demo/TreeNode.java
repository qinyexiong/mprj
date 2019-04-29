package main.prj.java.com.dao.demo;
 
import java.util.ArrayList;
import java.util.List;
 
public class TreeNode {
 
	private String id;
	private String name;
	private String desc;
	private String pid;
	private List<TreeNode> children;
	
	
	public TreeNode() {
		super();
	}
	
	public TreeNode(String id, String name, String desc, String pid) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.pid = pid;
		this.children = new ArrayList<TreeNode>();
	}
 
 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
 
	public List<TreeNode> getChildren() {
		return children;
	}
 
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
}

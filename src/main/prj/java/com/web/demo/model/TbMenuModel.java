package main.prj.java.com.web.demo.model;


import java.util.List;

import main.prj.java.com.dao.demo.TbMenu;

public class TbMenuModel {
	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 * @param navTabId
	 * @param callbackType
	 * @param forwardUrl
	 * @param rel 
	 */
	String menuTree;
	
	
	public String getMenuTree() {
		return menuTree;
	}
	public void setMenuTree(String menuTree) {
		this.menuTree = menuTree;
	}
	private  TbMenu value;
	private  List<TbMenu> valueList;
	public TbMenu getValue() {
		return value;
	}
	public void setValue(TbMenu value) {
		this.value = value;
	}
	public List<TbMenu> getValueList() {
		return valueList;
	}
	public void setValueList(List<TbMenu> valueList) {
		this.valueList = valueList;
	}
	
	
	
}

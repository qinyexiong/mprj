package main.prj.java.com.web.demo.model;


import java.util.List;

import main.prj.java.com.dao.demo.SysConfig;

public class SysConfigModel {
	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 * @param navTabId
	 * @param callbackType
	 * @param forwardUrl
	 * @param rel 
	 */
	
	
	private  SysConfig value;
	private  List<SysConfig> valueList;
	public SysConfig getValue() {
		return value;
	}
	public void setValue(SysConfig value) {
		this.value = value;
	}
	public List<SysConfig> getValueList() {
		return valueList;
	}
	public void setValueList(List<SysConfig> valueList) {
		this.valueList = valueList;
	}
	
	
	
}

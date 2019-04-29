package main.prj.java.com.web.demo.model;


import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import main.prj.java.com.dao.demo.TbRole;
import main.prj.java.com.dao.demo.TbUser;

public class TbRoleModel {
	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 * @param navTabId
	 * @param callbackType
	 * @param forwardUrl
	 * @param rel 
	 */
	
	
	private TbRole value;
	private  List<TbRole> valueList;
	public TbRole getValue() {
		return value;
	}
	public void setValue(TbRole value) {
		this.value = value;
	}
	public List<TbRole> getValueList() {
		return valueList;
	}
	public void setValueList(List<TbRole> valueList) {
		this.valueList = valueList;
	}
	
	
}

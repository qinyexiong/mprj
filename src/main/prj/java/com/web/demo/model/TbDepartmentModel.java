package main.prj.java.com.web.demo.model;

import java.util.List;

import com.github.pagehelper.PageInfo;

import main.common.base.web.struts.BaseModel;
import main.prj.java.com.dao.demo.TbDepartment;
import main.prj.java.com.dao.demo.TbUser;

public class TbDepartmentModel extends  BaseModel{
	private  TbDepartment value;
	private  List<TbDepartment> valueList;
	public TbDepartment getValue() {
		return value;
	}
	public void setValue(TbDepartment value) {
		this.value = value;
	}
	public List<TbDepartment> getValueList() {
		return valueList;
	}
	public void setValueList(List<TbDepartment> valueList) {
		this.valueList = valueList;
	}
	
	


}

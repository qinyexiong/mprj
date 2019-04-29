package main.prj.java.com.web.demo.model;


import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import main.prj.java.com.dao.demo.TbUser;

public class TbUserModel {
	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 * @param navTabId
	 * @param callbackType
	 * @param forwardUrl
	 * @param rel 
	 */
	
	
	private  TbUser value;
	private  List<TbUser> valueList;
	private  String outFileName;
	private InputStream excelStream;
	private File file; 
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public TbUser getValue() {
		return value;
	}
	public void setValue(TbUser value) {
		this.value = value;
	}
	public List<TbUser> getValueList() {
		return valueList;
	}
	public void setValueList(List<TbUser> valueList) {
	
		this.valueList = valueList;
	}
	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}
	public String getOutFileName() {
		return outFileName;
	}
	//解决浏览器文件名称兼容性问题.目前发现最客观的,测试兼容IE Chrome Firefox
	public void setOutFileName(String outFileName) {
		try {
			String strName = new String(outFileName.getBytes("gb2312"),"iso8859-1");
			outFileName=strName;
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		this.outFileName = outFileName;
	}

}

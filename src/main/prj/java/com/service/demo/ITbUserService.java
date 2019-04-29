package main.prj.java.com.service.demo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import main.common.base.dao.BaseDao;
import main.prj.java.com.dao.demo.TbUser;

public interface ITbUserService extends BaseDao<TbUser>  {
	/**
	 * 
	 * @Title: getExcel 
	 * @param strTitles
	 * @return
	 * @return HSSFWorkbook    返回类型
	 */
	public HSSFWorkbook getExcel(String  strTitles);
	
	
	

 
}
/**   
 * @Title: SysConfigServiceImpl.java 
 * @Package main.prj.java.com.service.demo.impl 
 * @Description: TODO
 * @author qinyx
 * @date 2018年12月1日 上午10:03:45 
 * @version V1.0   
 * @email qinyexiong@foxmail.com
 */
package main.prj.java.com.service.demo.impl;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;

import main.common.base.dao.BaseDaoImpl;
import main.common.base.web.constant.WebContant;
import main.common.base.web.result.BaseResult;
import main.common.utils.Uuids;
import main.prj.java.com.dao.demo.SysConfig;
import main.prj.java.com.service.demo.SysConfigServic;

/** 
 * @ClassName: SysConfigServiceImpl 
 * @Description: TODO
 * @author qinyx
 * @date 2018年12月1日 上午10:03:45 
 * @version  [1.0, 2018年12月1日]
 * @since  version 1.0
 * @email qinyexiong@foxmail.com 
 */

@Service
public class SysConfigServiceImpl extends BaseDaoImpl<SysConfig> implements SysConfigServic{
	
	
	

	/** 
	 * @Title: add 
	 * @param param
	 * @return 
	 * @return void    返回类型 
	 */
	public BaseResult add(Map param) {
		// TODO Auto-generated method stub
		SysConfig value = (SysConfig) param.get("value");
		SysConfig dbValue = new SysConfig();	
		dbValue.setId(Uuids.getUUID32());
		dbValue.setSysSwitchName(value.getSysSwitchName());
		dbValue.setSysCode(value.getSysCode());
		dbValue.setSysSwitch(value.getSysSwitch());
		dbValue.setDescription(value.getDescription());
//		模板字段
		dbValue.setCreateTime(new Date());
		dbValue.setCreator(WebContant.getCurrentUser().getUserName());
		
		this.insert(dbValue);
		return BaseResult.ok();
	}

	/** 
	 * @Title: update 
	 * @param param
	 * @return 
	 * @return void    返回类型 
	 */
	public BaseResult update(Map param) {
			SysConfig value = (SysConfig) param.get("value");
			SysConfig dbValue =this.load(value.getId());
			dbValue.setSysSwitchName(value.getSysSwitchName());
			dbValue.setSysCode(value.getSysCode());
			dbValue.setSysSwitch(value.getSysSwitch());
			dbValue.setDescription(value.getDescription());
			dbValue.setUpdateTime(new Date());
			dbValue.setMender(WebContant.getCurrentUser().getUserName());
			dbValue.setVersion("1");
			this.update(dbValue);
			return BaseResult.ok();
		
	}

	/** 
	 * @Title: getSwitch 
	 * @param vcode
	 * @return  0 是打开  1是关闭
	 * @return boolean    返回类型 
	 */
	public boolean getSwitch(String vcode) {
		SysConfig value = this.getSqlSession().selectOne("main.prj.java.com.dao.demo.SysConfig.sysSwitch", vcode);
		if (value.getSysSwitch().equals("0")) {
			return true;
		}
		return false;
	}

}
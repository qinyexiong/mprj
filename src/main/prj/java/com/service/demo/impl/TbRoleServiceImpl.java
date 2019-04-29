/**   
 * @Title: TbRoleServiceImpl.java 
 * @Package main.prj.java.com.service.demo.impl 
 * @Description: TODO
 * @author qinyx
 * @date 2018年12月1日 上午10:03:45 
 * @version V1.0   
 * @email qinyexiong@foxmail.com
 */
package main.prj.java.com.service.demo.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import main.common.base.dao.BaseDaoImpl;
import main.common.base.web.result.BaseResult;
import main.common.utils.Uuids;
import main.prj.java.com.dao.demo.TbRole;
import main.prj.java.com.service.demo.TbRoleServic;

/** 
 * @ClassName: TbRoleServiceImpl 
 * @Description: TODO
 * @author qinyx
 * @date 2018年12月1日 上午10:03:45 
 * @version  [1.0, 2018年12月1日]
 * @since  version 1.0
 * @email qinyexiong@foxmail.com 
 */

@Service
public class TbRoleServiceImpl extends BaseDaoImpl<TbRole> implements TbRoleServic{
	
	
	

	/** 
	 * @Title: add 
	 * @param param
	 * @return 
	 * @return void    返回类型 
	 */
	public BaseResult add(Map param) {
		// TODO Auto-generated method stub
		TbRole value = (TbRole) param.get("value");
		TbRole dbValue = new TbRole();	
		dbValue.setId(Uuids.getUUID32());
		dbValue.setRoleName(value.getRoleName());
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
			TbRole value = (TbRole) param.get("value");
			TbRole dbValue = this.load(value.getId());
			dbValue.setRoleName(value.getRoleName());
			this.update(dbValue);
			return BaseResult.ok();
		
	}

	

}
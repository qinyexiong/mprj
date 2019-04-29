/**   
 * @Title: RoleAction.java 
 * @Package main.prj.java.com.web.demo.action 
 * @Description: TODO
 * @author qinyx
 * @date 2018年12月1日 上午9:55:16 
 * @version V1.0   
 * @email qinyexiong@foxmail.com
 */
package main.prj.java.com.web.demo.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import main.common.base.web.struts.BaseAction;
import main.prj.java.com.dao.demo.TbRole;
import main.prj.java.com.dao.demo.TbRole;
import main.prj.java.com.service.demo.impl.TbRoleServiceImpl;
import main.prj.java.com.web.demo.model.TbRoleModel;

/**
 * @ClassName: RoleAction
 * @Description: TODO 角色控制层
 * @author qinyx
 * @date 2018年12月1日 上午9:55:16
 * @version [1.0, 2018年12月1日]
 * @since version 1.0
 * @email qinyexiong@foxmail.com
 */
public class RoleAction extends BaseAction<TbRoleModel> {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private TbRoleServiceImpl tbRoleServiceImpl;

	public String query() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		List<TbRole> queryList = tbRoleServiceImpl.queryList(paramMap, getPageParam());
		model.setValueList(queryList);
		
		
		return operateSuccessToCustomViews("query");
	}

	/**
	 * 
	 * @Title: toadd
	 * @return
	 * @return String 返回类型
	 */

	public String edit() {
		TbRole value = model.getValue();
		if (value != null) {
			TbRole load = tbRoleServiceImpl.load(value.getId());
			model.setValue(load);
		}
		return operateSuccessToCustomViews("edit");
	}

	/**
	 * 
	 * @Title: add
	 * @return
	 * @return String 返回类型
	 */
	public String add() {

			TbRole value = model.getValue();
			Map param = new HashMap<>();
			param.put("value", value);
			tbRoleServiceImpl.add(param);
		return operateSuccess();
	}

	/**
	 * 
	 * @Title: add
	 * @return
	 * @return String 返回类型
	 */
	public String update() {
	
			TbRole value = model.getValue();
			Map param = new HashMap<>();
			param.put("value", value);
			tbRoleServiceImpl.update(param);
	
		return operateSuccess();
	}

	/**
	 * 
	 * @Title: delete
	 * @return
	 * @return String 返回类型
	 */

	public String delete() {

			String id = model.getValue().getId();
			tbRoleServiceImpl.delete(id);
	
		return operateSuccess();
	}

}

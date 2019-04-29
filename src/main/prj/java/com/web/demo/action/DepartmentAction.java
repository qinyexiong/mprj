/**   
 * @Title: DepartmentAction.java 
 * @Package main.prj.java.com.web.demo.action 
 * @Description: TODO
 * @author qinyx
 * @date 2018年8月3日 上午12:30:21 
 * @version V1.0   
 * @email qinyexiong@foxmail.com
 */
package main.prj.java.com.web.demo.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import main.common.base.web.struts.BaseAction;
import main.prj.java.com.dao.demo.TbDepartment;
import main.prj.java.com.service.demo.impl.TbDepartmentServiceImpl;
import main.prj.java.com.web.demo.model.TbDepartmentModel;

/**
 * @ClassName: DepartmentAction
 * @Description: TODO
 * @author qinyx
 * @date 2018年8月3日 上午12:30:21
 * @version [1.0, 2018年8月3日]
 * @since version 1.0
 * @email qinyexiong@foxmail.com
 */
public class DepartmentAction extends BaseAction<TbDepartmentModel> {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private TbDepartmentServiceImpl tbDepartmentServiceImpl;

	/**
	 * 
	 * @Title: querUserList
	 * @return
	 * @return String 返回类型
	 */

	public String query() {
		Map param = new HashMap<>();
		List<TbDepartment> queryList = tbDepartmentServiceImpl.queryList(param, getPageParam());
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
		TbDepartment value = model.getValue();
		if (value != null) {
			TbDepartment load = tbDepartmentServiceImpl.load(value.getId());
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
		TbDepartment value = model.getValue();
		Map param = new HashMap<>();
		param.put("value", value);
		tbDepartmentServiceImpl.add(param);
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
		tbDepartmentServiceImpl.delete(id);
		return operateSuccess();
	}

}

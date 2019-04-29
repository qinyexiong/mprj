/**   
 * @Title: MenuAction.java 
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

import org.springframework.stereotype.Controller;

import main.common.base.web.struts.BaseAction;
import main.prj.java.com.dao.demo.TbMenu;
import main.prj.java.com.service.demo.impl.TbMenuServiceImpl;
import main.prj.java.com.web.demo.model.TbMenuModel;

/**
 * @ClassName: MenuAction
 * @Description: TODO 角色控制层
 * @author qinyx
 * @date 2018年12月1日 上午9:55:16
 * @version [1.0, 2018年12月1日]
 * @since version 1.0
 * @email qinyexiong@foxmail.com
 */
@Controller
public class MenuAction extends BaseAction<TbMenuModel> {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private TbMenuServiceImpl tbMenuServiceImpl;

	public String query() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<TbMenu> queryList = tbMenuServiceImpl.queryList(paramMap, getPageParam());
		model.setValueList(queryList);
		String jsonTree = tbMenuServiceImpl.getJsonTree();
		model.setMenuTree(jsonTree);
		return operateSuccessToCustomViews("query");
	}

	/**
	 * 
	 * @Title: toadd
	 * @return
	 * @return String 返回类型
	 */

	public String edit() {
		TbMenu value = model.getValue();
		if (value != null) {
			TbMenu load = tbMenuServiceImpl.load(value.getId());
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

			TbMenu value = model.getValue();
			Map param = new HashMap<>();
			param.put("value", value);
			tbMenuServiceImpl.add(param);
		return operateSuccess();
	}

	/**
	 * 
	 * @Title: add
	 * @return
	 * @return String 返回类型
	 */
	public String update() {
	
			TbMenu value = model.getValue();
			Map param = new HashMap<>();
			param.put("value", value);
			tbMenuServiceImpl.update(param);
	
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
			tbMenuServiceImpl.delete(id);
	
		return operateSuccess();
	}

}

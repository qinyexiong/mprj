package main.prj.java.com.web.demo.action;

import org.springframework.stereotype.Controller;

import main.common.base.web.struts.BaseAction;
import main.prj.java.com.web.demo.model.TbMenuModel;

/**
 * @ClassName: SearchAction
 * @Description: TODO 搜索控制层
 * @author qinyx
 * @date 2018年12月1日 上午9:55:16
 * @version [1.0, 2018年12月1日]
 * @since version 1.0
 * @email qinyexiong@foxmail.com
 */
@Controller
public class SearchAction extends BaseAction<TbMenuModel> {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;


	public String query() {

		return operateSuccessToCustomViews("query");
	}



}

/**   
 * @Title: ReportFormAction.java 
 * @Package main.prj.java.com.web.demo.action 
 * @Description: TODO
 * @author qinyx
 * @date 2018年12月30日 下午2:16:49 
 * @version V1.0   
 * @email qinyexiong@foxmail.com
 */
package main.prj.java.com.web.demo.action;

import main.common.base.web.struts.BaseAction;
import main.prj.java.com.web.demo.model.TbUserModel;

/** 
 * @ClassName: ReportFormAction 
 * @Description: TODO
 * @author qinyx
 * @date 2018年12月30日 下午2:16:49 
 * @version  [1.0, 2018年12月30日]
 * @since  version 1.0
 * @email qinyexiong@foxmail.com 
 */
public class ReportFormAction extends BaseAction<TbUserModel>  {

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 1L;
	
	
	public String userform() {
		System.out.println("I AM  OK !");
		return operateSuccessToCustomViews("userform");
	}

}

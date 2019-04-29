package main.prj.java.com.web.demo.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import main.common.base.web.constant.WebContant;
import main.common.base.web.result.BaseResult;
import main.common.base.web.struts.BaseAction;
import main.prj.java.com.dao.demo.TbUser;
import main.prj.java.com.service.demo.impl.SysConfigServiceImpl;
import main.prj.java.com.service.demo.impl.TbUserServiceImpl;
import main.prj.java.com.web.demo.model.TbUserModel;

/**
 * 
 * @ClassName: LoginAction
 * @Description: TODO  用户登录状态控制层
 * @author qinyx
 * @date 2018年12月1日 下午4:21:28
 * @version [1.0, 2018年12月1日]
 * @since version 1.0
 * @email qinyexiong@foxmail.com
 */
public class LoginAction extends BaseAction<TbUserModel> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TbUserServiceImpl tbUserServiceImpl;
	
	@Resource
	private SysConfigServiceImpl sysConfigServiceImp;

	/**
	 * 
	 * @Title: indexPage 已经登录的直接跳到主页面
	 * @return
	 * @return String 返回类型
	 */
	public String indexPage() {
		System.out.println("到主页面!");
		return operateSuccessToCustomViews("indexPage");
	}

	/**
	 * 
	 * @Title: 用户登录页面
	 * @return
	 * @return String 返回类型
	 */
	public String logPage() {
		System.out.println("到登录页面!");
		return operateSuccessToCustomViews("logPage");
	}

	/***
	 * 
	 * @Title: 
	 * @return
	 * @return String    返回类型
	 */
	public String chekIfLog() {
		return operateSuccess();
	}

	public String toLogin() {
		/**
		 * 校登录验数据
		 */
		TbUser value = model.getValue();
		if (StringUtils.isBlank(value.getUserNo())) {
			this.putData("userNoMsg", "用户名不能为空!");
			return operateSuccessToCustomViews("input");
		}
		if (StringUtils.isBlank(value.getUserPwd())) {
			this.putData("userPwdMsg", "密码不能为空!");
			return operateSuccessToCustomViews("input");
		}
		
		
		if(sysConfigServiceImp.getSwitch("vcode")) {
			String valverifycodeue = (String) this.getHttpRequest().getSession().getAttribute(WebContant.VERIFY_CODE);
			String vcode = this.getString("vcode");
		if (!vcode.equals(valverifycodeue)) {
			this.putData("code", "验证码不正确!");
			return operateSuccessToCustomViews("input");
		}
		}
		
		HashMap<Object, Object> param = new HashMap<>();
		param.put("value", value);
		BaseResult Result = tbUserServiceImpl.userLogin(param);
		if (Result.getStatus() == 200) {
			return "indexPage";
		} else {
			this.putData("errorMsg", "用户名或密码不正确!");
			return operateSuccessToCustomViews("input");
		}

	}
	public String toLoginOut() {
		return operateSuccessToCustomViews("toLoginOut");
	}

	public String loginOut() {

		try {
			BaseResult result = tbUserServiceImpl.loginOut();
			if (result.getStatus() == 200) {
				return operateSuccessToCustomViews("logPage");
			}
		} catch (Exception e) {
			operateSuccess("当前用户,成功退出失败!");
			e.printStackTrace();
		}
		return operateSuccessToCustomViews("logPage");
	}
	
	
		
		

}

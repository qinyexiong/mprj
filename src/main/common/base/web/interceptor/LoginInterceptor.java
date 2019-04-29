/**   
 * @Title: LoginInterceptor.java 
 * @Package main.common.base.web.interceptor 
 * @Description: TODO
 * @author qinyx
 * @date 2018年10月26日 下午5:28:16 
 * @version V1.0   
 * @email qinyexiong@foxmail.com
 */
package main.common.base.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import main.common.base.web.constant.WebContant;
import main.common.utils.CookieTool;
import main.prj.java.com.dao.demo.TbUser;
import main.prj.java.com.service.demo.impl.TbUserServiceImpl;

/** 
 * @ClassName: LoginInterceptor 
 * @Description: TODO
 * 				对action 进行拦截
 * @author qinyx
 * @date 2018年10月26日 下午5:28:16 
 * @version  [1.0, 2018年10月26日]
 * @since  version 1.0
 * @email qinyexiong@foxmail.com 
 */
public class LoginInterceptor extends AbstractInterceptor {
/*
 * 拦截器是在面向切面编程中应用的，就是在你的service或者一个方法前调用一个方法，或者在方法后调用。是基于JAVA的反射机制。拦截器在struts.xml中配置，
 */
	
	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 1L;


	@Autowired
	private TbUserServiceImpl tbUserServiceImpl;
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		@SuppressWarnings("all")
		
		//用户权限登录处理
		Object user	= invocation.getInvocationContext().getContext().getSession().get(WebContant.SESSION_ADMIN);
		boolean isLogin = false;
		if (user == null){
			// 从cookie中取出用户
			Cookie cookie = CookieTool.getCookie(WebContant.COOKIE_ADMIN);
			if (cookie != null){
				String userId = cookie.getValue();
				user =tbUserServiceImpl.load(userId);
				if (user != null){
					invocation.getInvocationContext().getSession().put(WebContant.SESSION_ADMIN, user);
					// 把当前登录用户的操作放到session中
//					Map<String, List<String>> operas = identityService.getUserOperas(user.getUserId());
//					invocation.getInvocationContext().getSession().put(WebContant.ALL_POPEDOM, operas);
					isLogin = true;
				}
			}else{
				isLogin = true;
			}
		}else{
			
			isLogin = true;
		}
		if (isLogin){
			return invocation.invoke();
		}else{
			//转到index  页面
			return "indexPage";
		}
	}
	
	
	
	
	
}
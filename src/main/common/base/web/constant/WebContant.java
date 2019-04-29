package main.common.base.web.constant;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

import main.prj.java.com.dao.demo.TbUser;

/**
 * 
 * @ClassName: WebContant 
 * @Description: TODO
 * @author qinyx
 * @date 2018年10月22日 下午11:27:21 
 * @version  [1.0, 2018年10月22日]
 * @since  version 1.0
 * @email qinyexiong@foxmail.com
 */
public final class WebContant {
	/** 定义一页显示的数量 */
	public static final int PAGE_SIZE = 10;
	/** 定义验证码保存在session中的字符串  */
	public static final String VERIFY_CODE = "verfify_code";
	/** 定义保存在session中的当前用户字符串  */
	public static final String SESSION_ADMIN = "user";
	/** 定义cookie存活时间: 为一周  7 * 24 * 60 * 60*/
	public static final int COOKIE_MAX_AGE = 7 * 24 * 60 * 60;
	/** 定义cookie名字 */
	public static final String COOKIE_ADMIN = "cookie_admin";
	
	public static final String LOGIN_PATH = "//WEB-INF/page/login.jsp";

	/** 定义重定向到页面的请求 */
	public static final String REDIRECT_PAGE="/logs/log_logPage.action";
	/** 转发到index 页面 */
	public static final String FORWARD_INDEX_PAGE="/logs/log_indexPage.action";
	/** 定义用户所有的权限 */
	public static final String ALL_POPEDOM = "all_popedom";
	/** 定义当前模块的权限 */
	public static final String MODULE_POPEDOM = "module_popedom";
	/** 定义无权限操作时的提示信息 */
	public static final String POPEDOM_TIP = "popedom_tip";
	
	public static final String LEAVE_TYPE_JSON_FILE = "/json/leaveType.json";
	
	/** 定义Result结果字符串 */
	public static final class Results{
		public static final String NONE = "none";
		public static final String JSON = "json";
		public static final String ADMIN_LOGIN = "admin.login";
	}
	/**
	 * 获取当前登录的用户
	 * @return 当前的用户
	 */
	public static TbUser getCurrentUser() {
		TbUser	user=(TbUser)ServletActionContext.getRequest().getSession().getAttribute(SESSION_ADMIN);
				return user;
	}
	/**
	 * get请求转码方法
	 * @param source
	 * @return
	 */
	public static String isoToUtf8(String source){
		if (!StringUtils.hasText(source)){
			return source;
		}
		if (ServletActionContext.getRequest().getMethod().equalsIgnoreCase("get")){
			try {
				source = decode(source);
				source = new String(source.getBytes("iso8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return source;
	}
	/**
	 * decode转码方法
	 * @param source
	 * @return
	 */
	public static String decode(String source){
		if (StringUtils.hasText(source)){
			try {
				return URLDecoder.decode(source, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return source;
	}
	public static String getRealPath() {
		return ServletActionContext.getServletContext().getRealPath("/");
	}
}






package main.common.base.web.constant;



 /** 
 * @ClassName: SessionConstant 
 * @Description: TODO
 * @author qinyx
 * @date 2018年10月15日 下午10:21:23 
 * @version  [1.0, 2018年10月15日]
 * @since  version 1.0
 * @email qinyexiong@foxmail.com 
 */
public class SessionConstant {

	/**
	 * 登录用户的session键名.
	 */
	public static final String USER_SESSION_KEY = "user";
	
	/**
	 * 商户主帐号ID的session键名.
	 */
	public static final String MAIN_USER_ID_SESSION_KEY = "userId";

	/**
	 * 登录用户拥有的权限集合的session键名.
	 */
	public static final String ACTIONS_SESSION_KEY = "actions";
	
	/**
	 * 用户密码连续输错次数限制(默认5).
	 */
	public static int WEB_PWD_INPUT_ERROR_LIMIT = 5;

}

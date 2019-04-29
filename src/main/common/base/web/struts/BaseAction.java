package main.common.base.web.struts;

import java.lang.reflect.ParameterizedType;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import main.common.base.web.page.PageParam;
import main.common.base.web.themes.dwz.DwzParam;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	
	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 1L;




	/**
	 * 编码类型 ISO-8859-1.
	 */
	// private static final String ISO8859_1 = "iso8859-1";
	/**
	 * 编码类型 UTF-8.
	 */
	private static final String UTF_8 = "utf-8";

	
	

	// =============== ModelDriven的支持 ==================

	protected T model;

	public BaseAction() {
		try {
			// 通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建model的实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}
	////////////////////////////////////////////////////////////////////////

	public Integer pageNum;

	/**
	 * pageBean.
	 * 
	 * @return the pageBean
	 */

	/**
	 * 取得当前request
	 * 
	 * @return
	 */
	public HttpServletRequest getHttpRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 取得当前response
	 * 
	 * @return
	 */
	public HttpServletResponse getHttpResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 取得当前web应用的根路径
	 * 
	 * @return
	 */
	public String getWebRootPath() {
		return ServletActionContext.getServletContext().getRealPath("/");
	}

	/**
	 * 取得会话ID(sessionId).
	 * 
	 * @return sessionId .
	 */
	public String getSessionId() {
		return ServletActionContext.getRequest().getSession().getId();
	}

	/**
	 * 获取session里面的属性
	 * 
	 * @return
	 */
	public Map<String, Object> getSessionMap() {
		return ActionContext.getContext().getSession();
	}

	/**
	 * 添加cookie
	 * 
	 * @param path
	 * @param key
	 * @param value
	 * @param maxAge
	 */
	public void addCookie(String path, String key, String value, int maxAge) {
		Cookie cookie = new Cookie(key, value);
		if (path != null) {
			cookie.setPath(path);
		}
		cookie.setMaxAge(maxAge);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addCookie(cookie);
	}

	/**
	 * 添加cookie
	 * 
	 * @param key
	 * @param value
	 */
	public void addCookie(String key, String value) {
		Cookie cookie = new Cookie(key, value);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addCookie(cookie);
	}

	// ///////////////// 结合DWZ-UI的分页参数获取方法 ///////////////////////////
	/**
	 * 获取当前页（DWZ-UI分页查询参数）.<br/>
	 * 如果没有值则默认返回1.
	 * 
	 * @author qyx.
	 */
	private int getPageNum() {
		// 当前页数
		String pageNumStr = getHttpRequest().getParameter("pageNum");
		int pageNum = 1;
		if (StringUtils.isNotBlank(pageNumStr)) {
			pageNum = Integer.valueOf(pageNumStr);
		}
		return pageNum;
	}

	/**
	 * 获取每页记录数（DWZ-UI分页查询参数）.<br/>
	 * 如果没有值则默认返回15.
	 * 
	 * @author qyx.
	 */
	private int getNumPerPage() {
		String numPerPageStr = getHttpRequest().getParameter("numPerPage");
		int numPerPage = 15;
		if (StringUtils.isNotBlank(numPerPageStr)) {
			numPerPage = Integer.parseInt(numPerPageStr);
		}
		return numPerPage;
	}

	/**
	 * 获取分页参数，包含当前页、每页记录数.
	 * 
	 * @return PageParam .
	 */
	public PageParam getPageParam() {
		
		return new PageParam(getPageNum(), getNumPerPage());
	}

	// //////////////////////// 存值方法 /////////////////////////////////
	/**
	 * 将数据放入Struts2上下文的值栈中.<br/>
	 * ActionContext.getContext().getValueStack().push(obj);
	 */
	public void pushData(Object obj) {
		ActionContext.getContext().getValueStack().push(obj);
	}

	/**
	 * 将数据放入Struts2上下文中.<br/>
	 * ActionContext.getContext().put(key, value);
	 */
	public void putData(String key, Object value) {
		ActionContext.getContext().put(key, value);
	}

	/**
	 * 响应DWZ-UI的Ajax成功请求（statusCode="200"）,<br/>
	 * 跳转到operateSuccess视图并提示“操作成功”.
	 * 
	 * @author qyx.
	 * @param message
	 *            提示消息.
	 * @return operateSuccess .
	 */
	public String operateSuccess() {
		ajaxDone("200", "操作成功");
		return "operateSuccess";
	}

	/**
	 * 响应DWZ的Ajax成功请求（statusCode="200"）,<br/>
	 * 跳转到operateSuccess视图，提示设置的消息内容.
	 * 
	 * @author qyx.
	 * @param message
	 *            提示消息.
	 * @return operateSuccess .
	 */
	public String operateSuccess(String message) {
		ajaxDone("200", message);
		return "operateSuccess";
	}
	/**
	 * 响应DWZ的Ajax成功请求（statusCode="200"）,<br/>
	 * 跳转到自定义视图，提示设置的消息内容.
	 * 
	 * @author qyx.
	 * @param message
	 *            提示消息.
	 *        views
	 *        	试图页面    
	 * @return operateSuccess .
	 */
	public String operateSuccessToCustomViews(String message,String views) {
		ajaxDone("200", message);
		return views;
	}
	/**
	 * 响应DWZ的Ajax成功请求（statusCode="200"）,<br/>
	 * 跳转到operateSuccess视图，提示设置的消息:"操作成功!".
	 * 
	 * @author qyx.
	 * @param message
	 *            提示消息.
	 * @return operateSuccess .
	 */
	public String operateSuccessToCustomViews(String views) {
		ajaxDone("200", "操作成功!");
		return views;
	}

	/**
	 * 响应DWZ的ajax失败请求（statusCode="300"）,跳转到ajaxDone视图.
	 * 
	 * @author qyx.
	 * @param message
	 *            提示消息.
	 * @return ajaxDone .
	 */
	public String operateError(String message) {
		ajaxDone("300", message);
		return "operateError";
	}

	/**
	 * 给不是从页面提交跳转提供类似于下面的参数 <input type="hidden" name="navTabId" value="list">
	 * <input type="hidden" name="callbackType" value="closeCurrent">
	 * <input type="hidden" name="forwardUrl" value="">
	 * 
	 * @param str1
	 * @param str2
	 */
	public void userparameter(String str1, String str2) {
		HttpServletRequest req = ServletActionContext.getRequest();
		req.setAttribute("navTabId", "list");
		req.setAttribute("callbackType", str1);
		req.setAttribute("forwardUrl", str2);
	}

	/**
	 * 响应DWZ的Ajax请求.
	 * 
	 * @author qyx.
	 * @param statusCode
	 *            statusCode:{ok:200, error:300, timeout:301}.
	 * @param message
	 *            提示消息.
	 */
	private void ajaxDone(String statusCode, String message) {
		DwzParam param = getDwzParam(statusCode, message);
		ActionContext.getContext().getValueStack().push(param);
	}

	/**
	 * 根据request对象，获取页面提交过来的DWZ框架的AjaxDone响应参数值.
	 * 
	 * @author qyx.
	 * @param statusCode
	 *            状态码.
	 * @param message
	 *            操作结果提示消息.
	 * @return DwzParam :封装好的DwzParam对象 .
	 */
	public DwzParam getDwzParam(String statusCode, String message) {
		// 获取DWZ Ajax响应参数值,并构造成参数对象
		HttpServletRequest req = ServletActionContext.getRequest();
		String navTabId = req.getParameter("navTabId");
		String dialogId = req.getParameter("dialogId");
		String callbackType = req.getParameter("callbackType");
		String forwardUrl = req.getParameter("forwardUrl");
		String rel = req.getParameter("rel");
		return new DwzParam(statusCode, message, navTabId, dialogId, callbackType, forwardUrl, rel, null);
	}
	
	
	/**
	 * 根据参数名从HttpRequest中获取Integer类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return IntegerValue or null .
	 */
	public Integer getInteger(String key) {
		String value = getHttpRequest().getParameter(key);
		if (StringUtils.isNotBlank(value)) {
			return Integer.parseInt(value);
		}
		return null;
	}

	/**
	 * 根据参数名从HttpRequest中获取Long类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return LongValue or null .
	 */
	public Long getLong(String key) {
		String value = getHttpRequest().getParameter(key);
		if (StringUtils.isNotBlank(value)) {
			return Long.parseLong(value);
		}
		return null;
	}

	/**
	 * 根据参数名从HttpRequest中获取String类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return String or null .
	 */
	public String getString(String key) {
		return getHttpRequest().getParameter(key);
	}

	/**
	 * 根据参数名从HttpRequest中获取String类型的参数值，无值则返回"" .
	 * 
	 * @param key
	 *            .
	 * @return String .
	 */
	public String getString_UrlDecode_UTF8(String key) {
		try {
			return URLDecoder.decode(this.getString(key), UTF_8);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 根据参数名从HttpRequest中获取String[] 类型的参数值 有 返回字符串数组 无 返回null;
	 * 
	 * @param key
	 *            .
	 * @return String[] or null .
	 */
	public String[] getStringArr(String key) {
		return getHttpRequest().getParameterValues(key);
	}

	/**
	 * 根据参数名从HttpRequest中获取Integer[] 类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return Integer[] or null .
	 */
	public Integer[] getIntegerArr(String key) {
		String[] values = getHttpRequest().getParameterValues(key);
		Integer[] returnArr = null;
		if (values != null) {
			int valueLength = values.length;
			returnArr = new Integer[valueLength];
			for (int i = 0; i < valueLength; i++) {
				returnArr[i] = Integer.parseInt(values[i]);
			}
		}
		return returnArr;
	}

	/**
	 * 根据参数名从HttpRequest中获取Long[] 类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return Long[] or null .
	 */
	public Long[] getLongArr(String key) {
		String[] values = getHttpRequest().getParameterValues(key);
		Long[] returnArr = null;
		if (values != null) {
			int valueLength = values.length;
			returnArr = new Long[valueLength];
			for (int i = 0; i < valueLength; i++) {
				returnArr[i] = Long.parseLong(values[i]);
			}
		}
		return returnArr;
	}

	/**
	 * 根据参数名从HttpRequest中获取Long[] 类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return Long[] or null .
	 */
	public Double[] getDoubleArr(String key) {
		String[] values = getHttpRequest().getParameterValues(key);
		Double[] returnArr = null;
		if (values != null) {
			int valueLength = values.length;
			returnArr = new Double[valueLength];
			for (int i = 0; i < valueLength; i++) {
				returnArr[i] = Double.parseDouble(values[i]);
			}
		}
		return returnArr;
	}


}

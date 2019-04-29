package main.common.base.web.result;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

import main.common.base.web.constant.WebContant;
import main.common.utils.VerifyAction;

public class BaseJson extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private String result;
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;

	}

	/**
	 * 
	 * 处理ajax请求
	 * 
	 * @return SUCCESS
	 * 
	 */
	public String excuteAjax() {
		try {
			String vcodeStr = ServletActionContext.getServletContext().getAttribute(WebContant.VERIFY_CODE).toString();
			HashMap<Object, Object> hashMap = new HashMap<>();
			hashMap.put("vcodeStr", vcodeStr);
			result = JSONObject.toJSON(hashMap).toString();
		} catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println(result);
		return SUCCESS;

	}

}

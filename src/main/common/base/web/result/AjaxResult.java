package main.common.base.web.result;

import java.lang.reflect.Method;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * @ClassName: AjaxResult 
 * @Description: TODO
 * @author qinyx
 * @date 2018年10月24日 下午10:45:01 
 * @version  [1.0, 2018年10月24日]
 * @since  version 1.0
 * @email qinyexiong@foxmail.com
 */
public class AjaxResult  extends AbstractInterceptor implements  Result   {

	private static final long serialVersionUID = -6038432418771676969L;
	/** 定义获取异步请求的响应数据的方法 */
	private String dataMethod = "CodeStr"; // getAjaxData
	/** 定义响应数据需不需要开启ZIP压缩 */
	private boolean zipEnable = true;
	
	

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		/** 获取目标对象 */
		Object target = invocation.getAction();
		/** 转化成get方法名  */
		String getMethodName = "get" + StringUtils.capitalize(dataMethod); // AjaxData
		/** 获取方法对象 */
		Method method = target.getClass().getMethod(getMethodName);
		/** 调用目标对象中的方法获取数据 */
		Object data = method.invoke(target);
		System.out.println("data: " + data);
		
		if (data != null){
			/** 获取HttpServletResponse */
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			/** 判断是否启了gzip压缩 */
			if (zipEnable){
				response.setHeader("Content-Encoding", "GZIP");
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				GZIPOutputStream gzip = new GZIPOutputStream(bos);
				gzip.write(data.toString().getBytes("utf-8"));
				gzip.flush();
				gzip.close();
				response.getOutputStream().write(bos.toByteArray());
			}else{
				/** 向浏览器输出 */
//				response.getWriter().print(data.toString());
				return JSONObject.toJSONString(data);
			}
			
			
		}
		return JSONObject.toJSONString(data);
	
	}
	
	/** setter method */
	public void setDataMethod(String dataMethod) {
		System.out.println(dataMethod); // ajaxData
		this.dataMethod = dataMethod;
	}
	public void setZipEnable(boolean zipEnable) {
		this.zipEnable = zipEnable;
	}

	/* (非 Javadoc) 
	 * <p>Title: execute</p> 
	 * <p>Description: </p> 
	 * @param paramActionInvocation
	 * @throws Exception 
	 * @see com.opensymphony.xwork2.Result#execute(com.opensymphony.xwork2.ActionInvocation) 
	 */
	@Override
	public void execute(ActionInvocation paramActionInvocation) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
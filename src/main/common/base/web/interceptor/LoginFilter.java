/**   
 * @Title: LoginFilter.java 
 * @Package main.common.base.web.interceptor 
 * @Description: TODO
 * @author qinyx
 * @date 2018年10月27日 上午12:32:23 
 * @version V1.0   
 * @email qinyexiong@foxmail.com
 */
package main.common.base.web.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.common.base.web.constant.WebContant;
import main.common.utils.CookieTool;


public class LoginFilter implements Filter {
	

	/*
	 * Servlet中的过滤器Filter是实现了javax.servlet.Filter接口的服务器端程序，主要的用途是过滤字符编码、做一些业务逻辑判断等。其工作原理是，
	 * 只要你在web.xml文件配置好要拦截的客户端请求，它都会帮你拦截到请求，此时你就可以对请求或响应(Request、Response)统一设置编码，简化操作；
	 * 同时还可进行逻辑判断，如用户是否已经登陆、有没有权限访问该页面等等工作。它是随你的web应用启动而启动的，只初始化一次，以后就可以拦截相关请求，
	 * 只有当你的web应用停止或重新部署的时候才销毁。在web.xml中配置
	 */

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("开始web 初始化......");
//		ServletContext servletContext = filterConfig.getServletContext();
        //获取XML文件中配置参数
       

	}

	@Override
	public void destroy() {

	}

	@Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 获得在下面代码中要用的request,response,session对象
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        //1.获取请求URL
        String servletPath = httpRequest.getServletPath();  
        System.out.println("老夫乃是Filter拦截器！");

        //2.检测1中获取的servletPath是否为不需要检测的URl中的一个.若是,放行
//        List<String> urls = Arrays.asList(uncheckedUrls.split(","));
//        if (urls.contains(servletPath)) {
//            filterChain.doFilter(httpRequest, httpResponse);
//            return;
//        }

        //3.从session中获取SessionKey对应值,若值不存在,则重定向到redirectUrl
        Cookie cookie = CookieTool.getCookie(WebContant.COOKIE_ADMIN);
        if (cookie != null && ( WebContant.LOGIN_PATH.equals(servletPath)) ) {
        	//这里只能先用重定向了. filter 来拦截url   interceptor 拦截action
        	  httpResponse.sendRedirect(httpRequest.getContextPath() + WebContant.FORWARD_INDEX_PAGE);        
            return;
         }
        if (cookie == null && ( WebContant.LOGIN_PATH.equals(servletPath)) ) {
      	  httpResponse.sendRedirect(httpRequest.getContextPath() + WebContant.REDIRECT_PAGE);        
          return;
       }
        
      else {
    	//4.若存在,则放行
          filterChain.doFilter(httpRequest, httpResponse);
      	}
        
    }

	

}

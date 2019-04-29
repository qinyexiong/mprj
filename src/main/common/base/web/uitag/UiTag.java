/**   
 * @Title: UiTag.java 
 * @Package main.common.base.web.uitag 
 * @Description: TODO
 * @author qinyx
 * @date 2018年12月5日 上午1:05:14 
 * @version V1.0   
 * @email qinyexiong@foxmail.com
 */
package main.common.base.web.uitag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/** 
 * @ClassName: UiTag 
 * @Description: TODO
 * @author qinyx
 * @date 2018年12月5日 上午1:05:14 
 * @version  [1.0, 2018年12月5日]
 * @since  version 1.0
 * @email qinyexiong@foxmail.com 
 */
public class UiTag extends TagSupport {

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 1L;
	


	@Override
	public int doStartTag() throws JspException {
		
		try {
			
			
			
			this.pageContext.getOut().write("hello tag!!!");
		} catch (IOException e) {
			throw new 
			RuntimeException(e);
		}
		return 0;
	}

	
	

}

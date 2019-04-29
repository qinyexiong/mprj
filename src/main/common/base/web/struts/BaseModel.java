package main.common.base.web.struts;

import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: BaseModel 
 * @Description: TODO
 * @author qinyx
 * @date 2018年12月1日 下午6:02:42 
 * @version  [1.0, 2018年12月1日]
 * @since  version 1.0
 * @email qinyexiong@foxmail.com
 */
public class BaseModel {
	
	/***
	 * 分页
	 */
	private PageInfo<Object> page = new PageInfo<Object>(null);

	////////////////////////////////////// getter and setter ////////////////////////
	public PageInfo<Object> getPage() {
		return page;
	}

	public void setPage(PageInfo<Object> page) {
		this.page = page;
	}

}

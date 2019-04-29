package main.common.base.web.page;

import java.io.Serializable;

/** 
 * @ClassName: PageParam 
 * @Description: TODO
 * @author qinyx
 * @date 2018年6月19日 下午9:48:12 
 * @version  [1.0, 2018年6月19日]
 * @since  version 1.0
 * @email qinyexiong@foxmail.com 
 */
public class PageParam implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6297178964005032338L;
	private int pageNum; // 当前页数
	private int numPerPage; // 每页记录数
	
	public PageParam(int pageNum, int numPerPage) {
		super();
		this.pageNum = pageNum;
		this.numPerPage = numPerPage;
	}

	/** 当前页数 */
	public int getPageNum() {
		return pageNum;
	}

	/** 当前页数 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/** 每页记录数 */
	public int getNumPerPage() {
		return numPerPage;
	}

	/** 每页记录数 */
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

}

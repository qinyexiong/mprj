package main.prj.java.com.dao.demo.abs;

import java.util.Date;

import main.common.base.entity.BaseEntityDao;
/**
 * 
 * @ClassName: AbsTbMenu 
 * @Description: TODO 菜单资源实体
 * 
 * @author qinyx
 * @date 2018年12月5日 上午10:17:11 
 * @version  [1.0, 2018年12月5日]
 * @since  version 1.0
 * @email qinyexiong@foxmail.com
 */

public class AbsTbMenu extends BaseEntityDao {
    /** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 1L;

    private String menuName;

    private String menuType;

    private String menuUrl;
    
    private String pid;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	

    
    

    
}
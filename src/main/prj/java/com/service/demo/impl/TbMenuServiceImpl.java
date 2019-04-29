/**   
 * @Title: TbMenuServiceImpl.java 
 * @Package main.prj.java.com.service.demo.impl 
 * @Description: TODO
 * @author qinyx
 * @date 2018年12月1日 上午10:03:45 
 * @version V1.0   
 * @email qinyexiong@foxmail.com
 */
package main.prj.java.com.service.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.druid.util.DaemonThreadFactory;
import com.opensymphony.xwork2.ActionContext;

import main.common.base.dao.BaseDaoImpl;
import main.common.base.web.result.BaseResult;
import main.common.utils.Uuids;
import main.prj.java.com.dao.demo.TbMenu;
import main.prj.java.com.dao.demo.TreeNode;

/** 
 * @ClassName: TbMenuServiceImpl 
 * @Description: TODO
 * @author qinyx
 * @date 2018年12月1日 上午10:03:45 
 * @version  [1.0, 2018年12月1日]
 * @since  version 1.0
 * @email qinyexiong@foxmail.com 
 */

@Service
public class TbMenuServiceImpl extends BaseDaoImpl<TbMenu>{
	
	@Resource
	private RecursiveTree recursiveTree;
	

	/** 
	 * @Title: add 
	 * @param param
	 * @return 
	 * @return void    返回类型 
	 */
	public BaseResult add(Map param) {
		// TODO Auto-generated method stub
		TbMenu value = (TbMenu) param.get("value");
		TbMenu dbValue = new TbMenu();	
		dbValue.setId(Uuids.getUUID32());
		dbValue.setMenuName(value.getMenuName());
		dbValue.setMenuType(value.getMenuType());
		dbValue.setMenuUrl(value.getMenuUrl());
		dbValue.setCreateTime(new Date());
		dbValue.setCreator("admin");
		
		this.insert(dbValue);
		return BaseResult.ok();
	}

	/** 
	 * @Title: update 
	 * @param param
	 * @return 
	 * @return void    返回类型 
	 */
	public BaseResult update(Map param) {

			TbMenu value = (TbMenu) param.get("value");
			TbMenu dbValue = this.load(value.getId());	
			dbValue.setMenuName(value.getMenuName());
			dbValue.setMenuType(value.getMenuType());
			dbValue.setMenuUrl(value.getMenuUrl());
			dbValue.setUpdateTime(new Date());
			dbValue.setMender("root");
			this.update(dbValue);
			return BaseResult.ok();
		
	}
	
	public String getJsonTree() {
		Map<String, Object> paramMap = new HashMap<>();
		List<TbMenu> queryList = this.queryList(paramMap);
		List<TreeNode> treeList=new ArrayList<TreeNode>();
		TreeNode root = null;
		for (TbMenu tbMenu : queryList) {
			root= 	new TreeNode(tbMenu.getId(), tbMenu.getMenuName(), tbMenu.getMenuUrl(), tbMenu.getPid());
			treeList.add(root);
		}
		String switchNodeListToTree = recursiveTree.switchNodeListToTree(treeList);
		return switchNodeListToTree;
	}
	

}
package main.prj.java.com.service.demo.impl;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import main.prj.java.com.dao.demo.TreeNode;
 
/**
 * 从数据库中查询出所有的树节点
 * 自动将该节点数组转化为逻辑树结构
 * @author 327084
 *
 */

@Service
public class RecursiveTree {
	
	
	/**
	 * 自动将节点数据转化为树结构
	 * @param nodes
	 * @return
	 */
	public static String switchNodeListToTree(List<TreeNode> nodes){
		TreeNode root = getRoot(nodes);
		root = recursive(root, nodes);
		return JSON.toJSONString(root, true);
	}
 
	/**
	 * 递归添加子节点
	 * @param node
	 * @param nodes
	 * @return
	 */
	public static TreeNode recursive(TreeNode node, List<TreeNode> nodes){
		
		for (TreeNode treeNode : nodes) {
			if(treeNode.getPid().equals(node.getId())){//说明是所给节点的子节点
				node.getChildren().add(treeNode);
				recursive(treeNode, nodes);
			}
		}
		
		return node;
	}
	
	/**
	 * 获取根节点
	 * @param nodes
	 * @return
	 */
	public static TreeNode getRoot(List<TreeNode> nodes){
		TreeNode root = null;
		if(nodes != null && nodes.size() > 0){
			for (TreeNode treeNode : nodes) {
				if("".equals(treeNode.getPid().trim())){
					root = treeNode;
					break;
				}
			}
		}
		return root;
	}
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode root = new TreeNode("1", "中国", "中国", "");
		TreeNode node1 = new TreeNode("11", "北京", "北京", "1");
		TreeNode node2 = new TreeNode("12", "上海", "上海", "1");
		TreeNode node3 = new TreeNode("13", "山东", "山东", "1");
		TreeNode node4 = new TreeNode("131", "潍坊", "潍坊", "13");
		TreeNode node5 = new TreeNode("1311", "安丘", "安丘", "131");
		TreeNode node6 = new TreeNode("111", "朝阳区", "朝阳区", "11");
		TreeNode node7 = new TreeNode("2", "美国", "加州", "");
		TreeNode node8 = new TreeNode("21", "美国", "加州小城", "2");
		
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		nodes.add(root);
		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		nodes.add(node4);
		nodes.add(node5);
		nodes.add(node6);
		nodes.add(node7);
		nodes.add(node8);
		
		
		
		System.out.println(switchNodeListToTree(nodes));
	}
}

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="leftside">
	<div id="sidebar_s">
		<div class="collapse">
			<div class="toggleCollapse">
				<div></div>
			</div>
		</div>
	</div>
	<div id="sidebar">
		<div class="toggleCollapse">
			<h2>主菜单</h2>
			<div>收缩</div>
		</div>
	<div class="accordion" fillSpace="sidebar">
			<div class="accordionHeader">
				<h2>
					<span>Folder</span>用户管理
				</h2>
			</div>
			<div class="accordionContent">
				<ul class="tree treeFolder">
					<li><a href="${ctx}/users/user_query.action" target="navTab"
						rel="quer_user_list" fresh="true">用户信息管理</a></li>
				</ul>
			</div>
			<div class="accordion" fillSpace="sidebar">
			<div class="accordionHeader">
				<h2>
					<span>Folder</span>用户管理
				</h2>
			</div>
			<div class="accordionContent">
				<ul class="tree treeFolder">
					<li><a href="${ctx}/users/user_query.action" target="navTab"
						rel="quer_user_list" fresh="true">用户信息管理</a></li>
				</ul>
			</div>

			<div class="accordionHeader">
				<h2>
					<span>Folder</span>部门管理
				</h2>
			</div>
			<div class="accordionContent">
				<ul class="tree treeFolder">
					<li><a href="${ctx}/deparments/deparment_query.action"
						target="navTab" rel="quer_dep_list" fresh="true">部门管理</a></li>
				</ul>
			</div>

			<div class="accordionHeader">
				<h2>
					<span>Folder</span>权限管理
				</h2>
			</div>
			<div class="accordionContent">
				<ul class="tree treeFolder">
					<li><a href="${ctx}/menus/menu_query.action" target="navTab"
						rel="quer_menu_list" fresh="true">菜单管理</a></li>
					<li><a href="${ctx}/roles/role_query.action" target="navTab"
						rel="quer_role_list" fresh="true">角色管理</a></li>
				</ul>
			</div>


		</div>
	</div>



</div>
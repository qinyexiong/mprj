package main.prj.java.com.service.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.opensymphony.xwork2.ActionContext;

import main.common.base.dao.BaseDaoImpl;
import main.common.base.web.constant.WebContant;
import main.common.base.web.result.BaseResult;
import main.common.utils.CookieTool;
import main.common.utils.ExcelFileGenerator;
import main.common.utils.Uuids;
import main.prj.java.com.dao.demo.TbUser;
import main.prj.java.com.service.demo.ITbUserService;

@SuppressWarnings("all")
@Service
public class TbUserServiceImpl extends BaseDaoImpl<TbUser> implements ITbUserService {

	/**
	 * 
	 * @Title: userLogin
	 * @param param
	 * @return
	 * @return TbUser 返回类型
	 */
	public BaseResult userLogin(Map param) {
		TbUser value = (TbUser) param.get("value");
		String userNo = value.getUserNo();
		String userPwd = DigestUtils.md5DigestAsHex(value.getUserPwd().getBytes());
		Map paramMap = new HashMap<>();
		paramMap.put("userNo", userNo);
		List<TbUser> queryList = this.queryList(paramMap);
		for (TbUser tbUser : queryList) {
			String userPwd2 = tbUser.getUserPwd();
			if (userPwd2 != null && userPwd2 != "" && userPwd2.equals(userPwd)) {
				Map<String, Object> session = ServletActionContext.getContext().getSession();
				session.put(WebContant.SESSION_ADMIN, tbUser);
				CookieTool.addCookie(WebContant.COOKIE_ADMIN, tbUser.getId(), WebContant.COOKIE_MAX_AGE);
				return BaseResult.ok();
			}

		}

		return null;
	}

	/***
	 * 
	 * @Title: add
	 * @param param
	 * @return 
	 * @return void 返回类型
	 */
	public BaseResult add(Map param) {
		TbUser value = (TbUser) param.get("value");
		TbUser dbValue = new TbUser();
		dbValue.setId(Uuids.getUUID32());
		dbValue.setUserName(value.getUserName());// 用户名
		dbValue.setUserNo(value.getUserNo());// 登录账号
		dbValue.setUserPwd(DigestUtils.md5DigestAsHex(value.getUserPwd().getBytes()));
		dbValue.setMobileNo(value.getMobileNo());
		dbValue.setCreateTime(new Date());// 创建时间
		dbValue.setStatus(1);// 状态
		dbValue.setUserType("0");// 用户类型
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
		TbUser value = (TbUser) param.get("value");
		TbUser dbValue = new TbUser();
		dbValue.setId(value.getId());
		dbValue.setUserName(value.getUserName());// 用户名
		dbValue.setUserNo(value.getUserNo());// 登录账号
		dbValue.setUserPwd(DigestUtils.md5DigestAsHex(value.getUserPwd().getBytes()));
		dbValue.setMobileNo(value.getMobileNo());
		dbValue.setCreateTime(new Date());// 创建时间
		dbValue.setStatus(1);// 状态
		dbValue.setUserType("0");// 用户类型
		this.update(dbValue);
		return BaseResult.ok();
	}
	/**
	 * 
	 */
	@Override
	public HSSFWorkbook getExcel(String strTitles) {
		ArrayList<String> arrayList = null;
		ArrayList<ArrayList<String>> arrayList2 = new ArrayList<ArrayList<String>>();
		ArrayList<String> fieldName = new ArrayList<String>();
		ArrayList<ArrayList<String>> fieldData = new ArrayList<ArrayList<String>>();
		// //excel数据内容
		// 表头
		String[] strTitle = strTitles.split(",");
		for (String str : strTitle) {
			fieldName.add(str);
		}
		// 查询数据库信息
		Map param = new HashMap<>();
		ArrayList<TbUser> queryList = (ArrayList<TbUser>) this.queryList(param);
		for (TbUser tbUser : queryList) {
			arrayList = new ArrayList<String>();
			arrayList.add(tbUser.getUserName());
			arrayList.add(tbUser.getUserNo());
			arrayList.add(tbUser.getUserType());
			arrayList.add(tbUser.getUserPwd());
			arrayList.add(tbUser.getEmail() == null ? "未设置邮箱" : tbUser.getEmail().toString());
			arrayList2.add(arrayList);
		}
		fieldData.addAll(arrayList2);
		// 调用转化工具
		ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(fieldName, fieldData);
		HSSFWorkbook expordExcel = excelFileGenerator.expordExcel();
		return expordExcel;

	}

	/**
	 * @Title: updateLastLoginTime
	 * @param value
	 * @return 
	 * @return void 返回类型
	 */
	public BaseResult updateLastLoginTime(String uerId) {
		TbUser load = this.load(uerId);
		load.setLastLoginTime(new Date());
		this.update(load);
		return BaseResult.ok();
	}

	/**
	 * @Title: loginOut
	 * @return void 返回类型
	 */
	public BaseResult loginOut() {
		
		// 使session失效
		HttpSession session = ServletActionContext.getRequest().getSession();
		TbUser TbUser =(TbUser) session.getAttribute(WebContant.SESSION_ADMIN);
		TbUser.setLastLoginTime(new Date());
		this.update(TbUser);
		session.removeAttribute(WebContant.SESSION_ADMIN);
//		session.invalidate();
		// 删除cookie
		CookieTool.deleteCookie(WebContant.COOKIE_ADMIN);
		return BaseResult.ok();

	}

	

}

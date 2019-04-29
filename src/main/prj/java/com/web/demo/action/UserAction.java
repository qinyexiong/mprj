package main.prj.java.com.web.demo.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import main.common.base.web.result.BaseResult;
import main.common.base.web.struts.BaseAction;
import main.common.utils.GenerateSqlFromExcel;
import main.prj.java.com.dao.demo.TbUser;
import main.prj.java.com.service.demo.impl.TbUserServiceImpl;
import main.prj.java.com.web.demo.model.TbUserModel;

/**
 * 
 * @ClassName: UserAction
 * @Description: TODO 用户控制层
 * @author qinyx
 * @date 2018年12月1日 下午4:20:21
 * @version [1.0, 2018年12月1日]
 * @since version 1.0
 * @email qinyexiong@foxmail.com
 */

@SuppressWarnings("all")
public class UserAction extends BaseAction<TbUserModel> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private TbUserServiceImpl tbUserServiceImpl;

	/**
	 * 
	 * @Title: querUserList
	 * @return
	 * @return String 返回类型
	 */

	public String query() {
		Map param = new HashMap<>();
		List<TbUser> queryList = tbUserServiceImpl.queryList(param, getPageParam());
		model.setValueList(queryList);
		return operateSuccessToCustomViews("query");
	}

	/**
	 * 
	 * @Title: toadd
	 * @return
	 * @return String 返回类型
	 */

	public String edit() {
		TbUser value = model.getValue();
		if (value != null) {
			TbUser load = tbUserServiceImpl.load(value.getId());
			model.setValue(load);
		}
		return operateSuccessToCustomViews("edit");
	}

	/**
	 * 
	 * @Title: add
	 * @return
	 * @return String 返回类型
	 */
	public String add() {
		try {
			TbUser value = model.getValue();
			Map param = new HashMap<>();
			param.put("value", value);
			tbUserServiceImpl.add(param);
		} catch (Exception e) {

		}
		return operateSuccess();
	}

	/**
	 * 
	 * @Title: add
	 * @return
	 * @return String 返回类型
	 */
	public String update() {
	
			TbUser value = model.getValue();
			Map param = new HashMap<>();
			param.put("value", value);
			 tbUserServiceImpl.update(param);
		return operateSuccess();
	}

	/**
	 * 
	 * @Title: delete
	 * @return
	 * @return String 返回类型
	 */

	public String delete() {
	
			String id = model.getValue().getId();
			tbUserServiceImpl.delete(id);
		
		return operateSuccess();
	}

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public UserAction() {
		// TODO Auto-generated constructor stub
	}

	
	public String userExport() {
		return operateSuccessToCustomViews("userExport");
	}
	/**
	 * 导出文件
	 * 
	 * @Title: export
	 * @return void 返回类型
	 */

	public String export() {
		// excel表头
		// 定义默认表头
		String strTitles = "用户姓名,户登录名,用户类型,密码,电子邮箱";
		HSSFWorkbook expordExcel = tbUserServiceImpl.getExcel(strTitles);
		// 进行流输出

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			expordExcel.write(os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// baos = (ByteArrayOutputStream) os;
		byte[] but = os.toByteArray();
		model.setExcelStream(new ByteArrayInputStream(but));
		;
		// 定义文件名称
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		model.setOutFileName("用户信息" + df.format(new Date()) + ".xls");
		return operateSuccessToCustomViews("excelExport");
	}

	/**
	 * 跳转到导入页面
	 * 
	 * @Title: toupload
	 * @return
	 * @return String 返回类型
	 */
	public String toupload() {
		return operateSuccessToCustomViews("toupload");
	}

	public String importExcel() {
		File file = model.getFile();
		System.out.println(file.getName());
		GenerateSqlFromExcel formExcel = new GenerateSqlFromExcel();
		try {
			ArrayList<String[]> generateUserSql = formExcel.generateUserSql(file);
			for (String[] strings : generateUserSql) {

				TbUser value = new TbUser();
				Map param = new HashMap<>();
				value.setUserName(strings[0]);
				value.setUserNo(strings[1]);
				value.setUserType(strings[2]);
				value.setUserPwd(strings[3]);
				value.setEmail(strings[4].getBytes());
				param.put("value", value);
				tbUserServiceImpl.add(param);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return operateSuccess();
	}

}

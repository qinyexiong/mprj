package main.prj.java.com.service.demo.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import main.common.base.dao.BaseDaoImpl;
import main.common.base.web.result.BaseResult;
import main.common.utils.Uuids;
import main.prj.java.com.dao.demo.TbDepartment;
import main.prj.java.com.service.demo.TbDpartmentService;

@SuppressWarnings("all")
@Service
public class TbDepartmentServiceImpl extends BaseDaoImpl<TbDepartment> implements TbDpartmentService {

	

	/***
	 * 
	 * @Title: add
	 * @param param
	 * @return 
	 * @return void 返回类型
	 */
	public BaseResult add(Map param) {
		TbDepartment value = (TbDepartment)param.get("value");
		value.setId(Uuids.getUUID32());
		this.insert(value);
		return BaseResult.ok();
		
	}

}

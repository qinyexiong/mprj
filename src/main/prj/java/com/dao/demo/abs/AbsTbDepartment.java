package main.prj.java.com.dao.demo.abs;

import main.common.base.entity.BaseEntityDao;

public class AbsTbDepartment extends BaseEntityDao {


    /** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 1L;

	private String departmentName;

    private String departmentCode;

   
   

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode == null ? null : departmentCode.trim();
    }
}
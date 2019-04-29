package main.prj.java.com.dao.demo.abs;

import main.common.base.entity.BaseEntityDao;

public class 	AbsTbRole extends BaseEntityDao {
   

    private String roleName;

   
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
}
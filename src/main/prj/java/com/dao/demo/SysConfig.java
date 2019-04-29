package main.prj.java.com.dao.demo;

import main.common.base.entity.BaseEntityDao;

public class SysConfig extends BaseEntityDao{
    /** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 1L;

	private String id;

	private String sysSwitchName;
	
	private String sysCode;
	
    private String sysSwitch;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSysSwitch() {
        return sysSwitch;
    }

    public void setSysSwitch(String sysSwitch) {
        this.sysSwitch = sysSwitch == null ? null : sysSwitch.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

	public String getSysSwitchName() {
		return sysSwitchName;
	}

	public void setSysSwitchName(String sysSwitchName) {
		this.sysSwitchName = sysSwitchName;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
    
    
}
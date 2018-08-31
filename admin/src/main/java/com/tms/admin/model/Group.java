package com.tms.admin.model;

//import java.util.List;

public class Group {
	
	private int serialNumber;
	private int adminId;
	private String adminName;
	private String groupEmpIds;
	private String groupName;
	private String description;
	private String isArchived;
	
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGroupEmpIds() {
		return groupEmpIds;
	}
	public void setGroupEmpIds(String groupEmpIds) {
		 
		this.groupEmpIds = groupEmpIds;
	}
	public String getIsArchived() {
		return isArchived;
	}
	public void setIsArchived(String  isArchived) {
		
			this.isArchived = isArchived;
	}
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	
}

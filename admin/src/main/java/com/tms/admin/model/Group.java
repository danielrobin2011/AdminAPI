package com.tms.admin.model;

import java.util.List;

public class Group {
	
	private int adminId;
	private List<Integer> empIds;
	private String groupName;
	
	public Group(int adminId, String groupName, List<Integer> empIds){
		this.adminId=adminId;
		this.groupName=groupName;
		this.empIds=empIds;
	}
	
	public Group() {
		super();
	}
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public List<Integer> getEmpIds() {
		return empIds;
	}
	public void setEmpIds(List<Integer> empIds) {
		this.empIds = empIds;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}

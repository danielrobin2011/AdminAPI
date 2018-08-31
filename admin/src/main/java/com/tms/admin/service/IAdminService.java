package com.tms.admin.service;

import java.util.List;

import com.tms.admin.model.Employee;
import com.tms.admin.model.Group;

public interface IAdminService {
	
	public List<Employee> getAllEmployees();
	public List<Group> getAllGroups();
	public void addGroup(Group obj);
//	public Group getOneGroup(int id);
}

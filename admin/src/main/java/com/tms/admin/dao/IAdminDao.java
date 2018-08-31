package com.tms.admin.dao;

import java.util.List;

import com.tms.admin.model.Employee;
import com.tms.admin.model.Group;

public interface IAdminDao {
	
	public List<Employee> getAllEmployees();
	public List<Group> getAllGroups();
	public void addGroup(Group obj);
//	public Group getOneGroup(int id);
//	public void updateGroup(Group obj);

}

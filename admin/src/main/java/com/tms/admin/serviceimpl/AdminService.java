package com.tms.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tms.admin.dao.IAdminDao;
import com.tms.admin.model.Employee;
import com.tms.admin.service.IAdminService;


@Service
public class AdminService implements IAdminService{
	
	@Autowired
	private IAdminDao adminDao; 
	
	public List<Employee> getAllEmployees() {
		
		return adminDao.getAllEmployees();
	}
	
}

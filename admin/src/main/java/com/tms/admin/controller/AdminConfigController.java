package com.tms.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tms.admin.model.Employee;
import com.tms.admin.service.IAdminService;


@RestController
public class AdminConfigController {
		    
		@Autowired
		private IAdminService adminService;
		
		@RequestMapping(value="/employees", method=RequestMethod.GET)
		@ResponseBody
		public List<Employee> getAllEmployees(){
			System.out.println("Inside: Controller");
			return adminService.getAllEmployees();
		}
}

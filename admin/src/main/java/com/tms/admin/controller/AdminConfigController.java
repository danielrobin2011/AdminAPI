package com.tms.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tms.admin.model.Employee;
import com.tms.admin.model.Group;
import com.tms.admin.service.IAdminService;


@RestController
public class AdminConfigController {
		    
		@Autowired
		private IAdminService adminService;
		
		@RequestMapping(value="/employees", method=RequestMethod.GET)
		@ResponseBody
		public List<Employee> getEmployees(){
			System.out.println("Inside: Controller");
			return adminService.getAllEmployees();
		}
		@CrossOrigin("*")
		
		@RequestMapping(value="/groups", method=RequestMethod.GET)
		@ResponseBody
		public List<Group> getGroups(){
			System.out.println("Inside: Controller");
			return adminService.getAllGroups();
		}
		
		@RequestMapping(value="/groups", method=RequestMethod.POST)
		@ResponseBody
		public void addGroup(@RequestBody Group obj){
			System.out.println("Inside: Controller");
			adminService.addGroup(obj);
		}
		
//		@RequestMapping(value="/group/{id}", method=RequestMethod.GET)
//		@ResponseBody
//		public Group getId(@PathVariable int id) {
//			System.out.println("Inside: Controller");
//			return adminService.getOneGroup(id);
//		}
		

		
}

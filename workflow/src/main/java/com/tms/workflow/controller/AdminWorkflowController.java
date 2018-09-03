package com.tms.workflow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.tms.workflow.model.Status;
import com.tms.workflow.model.Workflow;
import com.tms.workflow.model.WorkflowCreate;
import com.tms.workflow.model.WorkflowStatusRef;
import com.tms.workflow.service.IAdminWorkflowService;

@RestController
public class AdminWorkflowController {
	
	@Autowired
	private IAdminWorkflowService adminWorkflowService;
	
	@RequestMapping(value="/statuses", method=RequestMethod.GET)
	@ResponseBody
	public List<Status> getStatuses(){
		System.out.println("Inside: Controller");
		return adminWorkflowService.displayStatuses();
	}

	@RequestMapping(value="/statusRank", method=RequestMethod.PUT)
	@ResponseBody
	public void updateStatusAndRanks(@RequestBody WorkflowStatusRef obj){
		System.out.println("Inside: Controller");
		adminWorkflowService.setStatusesToWorkflow(obj);
	}
	
	@RequestMapping(value="/workflows", method=RequestMethod.GET)
	@ResponseBody
	public List<Workflow> getWorkflows(){
		System.out.println("Inside: Controller");
		return adminWorkflowService.displayWorkflows();
	}
	
	@RequestMapping(value="/workflow", method=RequestMethod.POST)
	@ResponseBody
	public void createAWorkflow(@RequestBody WorkflowCreate obj){
		System.out.println("Inside: Controller");
		adminWorkflowService.createWorkflow(obj);
	}
	
}

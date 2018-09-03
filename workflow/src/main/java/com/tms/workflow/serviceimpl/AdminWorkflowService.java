package com.tms.workflow.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tms.workflow.dao.IAdminWorkflowDao;
import com.tms.workflow.model.Status;
import com.tms.workflow.model.Workflow;
import com.tms.workflow.model.WorkflowCreate;
import com.tms.workflow.model.WorkflowStatusRef;
import com.tms.workflow.service.IAdminWorkflowService;

@Service
public class AdminWorkflowService implements IAdminWorkflowService {
	
	@Autowired
	private IAdminWorkflowDao adminWorkflowDao;
	
	public List<Status> displayStatuses(){
			
		return adminWorkflowDao.displayStatuses();

	}

	@Override
	public List<Workflow> displayWorkflows() {
		// TODO Auto-generated method stub
		return adminWorkflowDao.displayWorkflows();
	}

	@Override
	public void createWorkflow(WorkflowCreate obj) {
		// TODO Auto-generated method stub
		adminWorkflowDao.createWorkflow(obj);
	}

	@Override
	public void setStatusesToWorkflow(WorkflowStatusRef obj) {
		adminWorkflowDao.setStatusesToWorkflow(obj);
		
	}
}

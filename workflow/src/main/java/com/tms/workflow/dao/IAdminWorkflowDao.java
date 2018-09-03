package com.tms.workflow.dao;

import java.util.List;

import com.tms.workflow.model.Status;
import com.tms.workflow.model.Workflow;
import com.tms.workflow.model.WorkflowCreate;
import com.tms.workflow.model.WorkflowStatusRef;

public interface IAdminWorkflowDao {
	
	public List<Status> displayStatuses();
	public void setStatusesToWorkflow(WorkflowStatusRef obj);
	public List<Workflow> displayWorkflows();
	public void createWorkflow(WorkflowCreate obj);
	
}

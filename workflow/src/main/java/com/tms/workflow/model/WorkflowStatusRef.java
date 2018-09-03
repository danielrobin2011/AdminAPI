package com.tms.workflow.model;

public class WorkflowStatusRef {
	
	private int workflowId;
	private String statusIdWorkflow;
	private String rank;
	
	public int getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(int workflowId) {
		this.workflowId = workflowId;
	}
	public String getstatusIdWorkflow() {
		return statusIdWorkflow;
	}
	public void setstatusIdWorkflow(String statusIdWorkflow) {
		this.statusIdWorkflow = statusIdWorkflow;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
}

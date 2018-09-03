package com.tms.workflow.model;

public class WorkflowBatch {
	
	private int batchStatusId;
	private int batchRank;
	private int batchWorkflowId;
	private int batchOldStatusId;
	private int batchOldRank;
	
	public int getBatchStatusId() {
		return batchStatusId;
	}
	public void setBatchStatusId(int batchStatusId) {
		this.batchStatusId = batchStatusId;
	}
	public int getBatchRank() {
		return batchRank;
	}
	public void setBatchRank(int batchRank) {
		this.batchRank = batchRank;
	}
	public int getBatchWorkflowId() {
		return batchWorkflowId;
	}
	public void setBatchWorkflowId(int batchWorkflowId) {
		this.batchWorkflowId = batchWorkflowId;
	}
	public int getBatchOldStatusId() {
		return batchOldStatusId;
	}
	public void setBatchOldStatusId(int batchOldStatusId) {
		this.batchOldStatusId = batchOldStatusId;
	}
	public int getBatchOldRank() {
		return batchOldRank;
	}
	public void setBatchOldRank(int batchOldRank) {
		this.batchOldRank = batchOldRank;
	}
	

}


package com.tms.workflow.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tms.workflow.dao.IAdminWorkflowDao;
import com.tms.workflow.model.Status;
import com.tms.workflow.model.Workflow;
import com.tms.workflow.model.WorkflowBatch;
import com.tms.workflow.model.WorkflowCreate;
import com.tms.workflow.model.WorkflowStatusRef;

@Repository
public class AdminWorkflowDao implements IAdminWorkflowDao {

	private static final String FETCH_WORKFLOWS = "SELECT workflow, workflow_description,isArchived FROM workflow";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Status> displayStatuses() {

		return this.jdbcTemplate.query("select id,status from ticket_status where isArchived=false",
				new RowMapper<Status>() {
					public Status mapRow(ResultSet rs, int rowNum) throws SQLException {
						Status s = new Status();
						s.setStatusId(rs.getInt("id"));
						s.setStatus(rs.getString("status"));
						return s;
					}
				});

	}

	@Override
	public void setStatusesToWorkflow(WorkflowStatusRef obj) {

		System.out.println("Inside setStatusesToWorkflow function");
		String FETCH_OLD_STATUSID = "SELECT statusId FROM workflow_status_ref WHERE workflowId='" + obj.getWorkflowId()
				+ "'";
		List<Integer> oldStatusIdWorkflow = jdbcTemplate.queryForList(FETCH_OLD_STATUSID, Integer.class);
		for (int j = 0; j < oldStatusIdWorkflow.size(); j++) {
			System.out.println(oldStatusIdWorkflow.get(j));
		}

		String FETCH_OLD_RANKS = "SELECT rankOfStatus FROM workflow_status_ref WHERE workflowId='" + obj.getWorkflowId()
				+ "'";
		List<Integer> oldRanks = jdbcTemplate.queryForList(FETCH_OLD_RANKS, Integer.class);
		for (int j = 0; j < oldRanks.size(); j++) {
			System.out.println(oldRanks.get(j));
		}

//		String oldStatusIdWorkflow = obj.getstatusIdWorkflow();       
//      String[] oldStatusStrings = oldStatusIdWorkflow.split(" "); 
//    	int[] oldArrayOfStatuses = new int[oldStatusStrings.length]; 
//    	for (int i = 0; i < oldStatusStrings.length; i++){
//    		oldArrayOfStatuses[i] = Integer.parseInt(oldStatusStrings[i]); 
//    	}

		String statusIdWorkflow = obj.getstatusIdWorkflow();
		String[] statusStrings = statusIdWorkflow.split(" ");
		int[] array_of_statuses = new int[statusStrings.length];
		for (int i = 0; i < statusStrings.length; i++) {
			array_of_statuses[i] = Integer.parseInt(statusStrings[i]);
		}

		String rank = obj.getRank();

		System.out.println("Array of ranks");
		String[] rankStrings = rank.split(" ");
		int[] array_of_ranks = new int[rankStrings.length];
		for (int i = 0; i < rankStrings.length; i++) {
			array_of_ranks[i] = Integer.parseInt(rankStrings[i]);
			System.out.println(array_of_ranks[i]);
		}

		List<WorkflowBatch> data_of_status_workflow_ref = new ArrayList<WorkflowBatch>();

		int id = obj.getWorkflowId();

		for (int i = 0; i < oldStatusIdWorkflow.size(); i++) {
			WorkflowBatch tmp = new WorkflowBatch();
			tmp.setBatchStatusId(array_of_statuses[i]);
			tmp.setBatchRank(array_of_ranks[i]);
			tmp.setBatchWorkflowId(id);
			tmp.setBatchOldStatusId(oldStatusIdWorkflow.get(i));
			tmp.setBatchOldRank(oldRanks.get(i));
			// {array_of_statuses[i], array_of_ranks[i],id, oldStatusIdWorkflow.get(i)};
			// jdbcTemplate.update("UPDATE workflow_status_ref SET statusId='?',
			// rankOfStatus='?' WHERE workflowId='?' and statusId='?'",
			// array_of_statuses[i], array_of_ranks[i],id, oldStatusIdWorkflow.get(i));
			data_of_status_workflow_ref.add(tmp);
		}

		System.out.println("Status Ids");
		for (int i = 0; i < data_of_status_workflow_ref.size(); i++) {
			System.out.println(data_of_status_workflow_ref.get(i).getBatchStatusId());
		}

		jdbcTemplate.batchUpdate("UPDATE workflow_status_ref SET statusId=?  WHERE workflowId=? and rankOfStatus=?",
				new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {

						WorkflowBatch smallBatch = data_of_status_workflow_ref.get(i);
						ps.setInt(1, smallBatch.getBatchStatusId());
						// ps.setInt(2, smallBatch.getBatchRank());
						ps.setInt(2, smallBatch.getBatchWorkflowId());
						// ps.setInt(4, smallBatch.getBatchOldStatusId());
						ps.setInt(3, smallBatch.getBatchOldRank());
					}

					@Override
					public int getBatchSize() {
						return data_of_status_workflow_ref.size();
					}
				});

		jdbcTemplate.batchUpdate("UPDATE workflow_status_ref SET rankOfStatus=?  WHERE workflowId=? and statusId=?",
				new BatchPreparedStatementSetter() {
					//
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {

						WorkflowBatch smallBatch = data_of_status_workflow_ref.get(i);
						//ps.setInt(1, smallBatch.getBatchStatusId());
						ps.setInt(1, smallBatch.getBatchRank());
						ps.setInt(2, smallBatch.getBatchWorkflowId());
						ps.setInt(3, smallBatch.getBatchStatusId());
						//ps.setInt(3, smallBatch.getBatchOldStatusId());
						//ps.setInt(3, smallBatch.getBatchOldRank());
					}

					@Override
					public int getBatchSize() {

						return data_of_status_workflow_ref.size();
					}
				});

		System.out.println("Almost Out");
	}

	public List<Workflow> displayWorkflows() {
		return this.jdbcTemplate.query(FETCH_WORKFLOWS, new RowMapper<Workflow>() {
			public Workflow mapRow(ResultSet rs, int rowNum) throws SQLException {
				Workflow s = new Workflow();
				s.setName(rs.getString("workflow"));
				s.setDescription(rs.getString("workflow_description"));
				s.setIsActive(rs.getBoolean("isArchived"));
				return s;
			}
		});
	}

	public void createWorkflow(WorkflowCreate obj) {

		jdbcTemplate.update("INSERT INTO workflow (workflow, workflow_description) VALUES (?,?)",obj.getName(),obj.getDescription());
		
		String FETCH_WORKFLOWID = "SELECT Id FROM workflow WHERE workflow='" + obj.getName()+ "'"+" and workflow_description='"+obj.getDescription()+"'";
		int workflowId = jdbcTemplate.queryForObject(FETCH_WORKFLOWID, Integer.class);
		
		//System.out.println("The id of the new Workflow is:"+count);

		
		String status = obj.getStatus();       
        String[] statusStrings = status.split(" "); 
    	int[] array_of_statuses = new int[statusStrings.length]; 
    	for (int i = 0; i < statusStrings.length; i++){
    		array_of_statuses[i] = Integer.parseInt(statusStrings[i]); 
    	}		            
       
        String rank = obj.getRanks();
        String[]rankStrings = rank.split(" "); 
    	int[] array_of_ranks = new int[rankStrings.length]; 
    	for (int i = 0; i < rankStrings.length; i++){
    		array_of_ranks[i] = Integer.parseInt(statusStrings[i]); 
    	}
    	
    	for (int i = 0; i < oldStatusIdWorkflow.size(); i++) {
			WorkflowBatch tmp = new WorkflowBatch();
			tmp.setBatchStatusId(array_of_statuses[i]);
			tmp.setBatchRank(array_of_ranks[i]);
			tmp.setBatchWorkflowId(id);
			tmp.setBatchOldStatusId(oldStatusIdWorkflow.get(i));
			tmp.setBatchOldRank(oldRanks.get(i));
			// {array_of_statuses[i], array_of_ranks[i],id, oldStatusIdWorkflow.get(i)};
			// jdbcTemplate.update("UPDATE workflow_status_ref SET statusId='?',
			// rankOfStatus='?' WHERE workflowId='?' and statusId='?'",
			// array_of_statuses[i], array_of_ranks[i],id, oldStatusIdWorkflow.get(i));
			data_of_status_workflow_ref.add(tmp);
		}
    	
    	jdbcTemplate.batchUpdate("INSERT INTO workflow_status_ref(workflowId, statusId, rankOFStatus) VALUES (?,?,?)",
				new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {

						WorkflowBatch smallBatch = data_of_status_workflow_ref.get(i);
						ps.setInt(1, smallBatch.getBatchStatusId());
						// ps.setInt(2, smallBatch.getBatchRank());
						ps.setInt(2, smallBatch.getBatchWorkflowId());
						// ps.setInt(4, smallBatch.getBatchOldStatusId());
						ps.setInt(3, smallBatch.getBatchOldRank());
					}

					@Override
					public int getBatchSize() {
						return data_of_status_workflow_ref.size();
					}
				});

	}

}

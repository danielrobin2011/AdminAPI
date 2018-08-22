package com.tms.admin.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tms.admin.dao.IAdminDao;
import com.tms.admin.model.Employee;

@Repository
public class AdminDao implements IAdminDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String FETCH_EMPLOYEES="select empId,fName,mName,lName from employee where isArchived=false";
	
	@Override
	public List<Employee> getAllEmployees() {
		return this.jdbcTemplate.query(FETCH_EMPLOYEES, new RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee s=new Employee();
			s.setEmpFname(rs.getString("fName"));
			s.setEmpMname(rs.getString("mName"));
			s.setEmpLname(rs.getString("lName"));
			s.setEmpId(rs.getInt("empId"));
			return s;
			}
		});	
	}	
}

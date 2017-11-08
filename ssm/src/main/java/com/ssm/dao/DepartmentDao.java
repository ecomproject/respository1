package com.ssm.dao;

import java.util.List;

import com.ssm.model.Department;

public interface DepartmentDao {
	 public List<Department> findByPid(String pid);  
     public List<Department> findById(String id);  
}

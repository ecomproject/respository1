package com.ssm.service;

import java.util.List;

import com.ssm.model.Department;

public interface DepartmentService {
	public List<Department> findByPid(String pid);

	public List<Department> findById(String id);
}

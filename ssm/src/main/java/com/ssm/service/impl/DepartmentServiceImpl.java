package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.dao.DepartmentDao;
import com.ssm.model.Department;
import com.ssm.service.DepartmentService;

@Service("departmentServiceImpl") 
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public List<Department> findByPid(String pid) {
		return departmentDao.findByPid(pid);
	}

	@Override
	public List<Department> findById(String id) {
		return departmentDao.findById(id);
	}
}

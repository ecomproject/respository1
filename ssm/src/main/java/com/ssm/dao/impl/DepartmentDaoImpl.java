package com.ssm.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ssm.dao.DepartmentDao;
import com.ssm.model.Department;
import com.ssm.util.MybatisUtils;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	public List<Department> findByPid(String pid) {
	
		SqlSessionFactory factory = MybatisUtils.getFactory();
		SqlSession session = factory.openSession();
		String statement = "com.ssm.mapper.findById";
		List<Department> list = session.selectList(statement,pid);
		session.close();
		return list;
	}

	@Override
	public List<Department> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

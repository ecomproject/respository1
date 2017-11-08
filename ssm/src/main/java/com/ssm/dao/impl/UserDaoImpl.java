package com.ssm.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import com.ssm.dao.IUserDao;
import com.ssm.model.User;
import com.ssm.util.MybatisUtils;

@Repository
public class UserDaoImpl implements IUserDao {

	/**
	 *  通过 ID 获取一个对象
	 */
	@Override
	public User getUserById(int id) {
		SqlSessionFactory factory = MybatisUtils.getFactory();
		SqlSession session = factory.openSession();
		String statement = "com.ssm.mapper.getUserById";
		User user = session.selectOne(statement, id);
		session.close();
		return user;
	}

	/**
	 *  获取所有对象
	 */
	@Override
	public List<User> getAllUsers() {
		System.out.println( "1231312321");
		
	/*	SqlSessionFactory factory = MybatisUtils.getFactory();
		SqlSession session = factory.openSession();
		String statement = "com.ssm.mapper.getAllUsers";
		List<User> list = session.selectList(statement);
		session.close();
		return list;*/
		
		return null;
	}

	/**
	 * 添加一个对象
	 */
	@Override
	public void insertUser(User user) {
		System.out.println( "1231312321");
		
		SqlSessionFactory factory = MybatisUtils.getFactory();
		SqlSession session = factory.openSession();
		String statement = "com.ssm.mapper.addUser";
		
		
	
		System.out.println( user.getAge());
		
		
		
		
		int insert = session.insert(statement , user);
		session.commit();
		session.close();
		System.out.println(insert);
	}

	/**
	 *  更新一个对象
	 */
	@Override
	public void updateUser(User user) {
		SqlSessionFactory factory = MybatisUtils.getFactory();
		SqlSession session = factory.openSession();
		String statement = "com.ssm.mapper.updateUser";
		int update = session.update(statement , user);
		session.commit();
		session.close();
		System.out.println(update);
	}

	/**
	 *  删除一个对象
	 */
	@Override
	public void deleteUser(int id) {
		SqlSessionFactory factory = MybatisUtils.getFactory();
		SqlSession session = factory.openSession();
		String statement = "com.ssm.mapper.deleteUser";
		int delete = session.delete(statement , id);
		session.commit();
		session.close();
		System.out.println(delete);
	}

}

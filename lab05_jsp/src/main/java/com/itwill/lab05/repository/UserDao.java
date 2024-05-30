package com.itwill.lab05.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.datasource.DataSourceUtil;
import com.zaxxer.hikari.HikariDataSource;

public enum UserDao {
	INSTANCE;
	
	 //Data Access Object
	private static final Logger log = LoggerFactory.getLogger(UserDao.class);
	private final HikariDataSource ds = DataSourceUtil.getInstance().getDataSource();  //getConnetion
	private static final PostDao postDao = PostDao.INSTANCE;
	
	//TODO: users 테이블에 insert SQL문장과 SQL실행할 메서드 만들기.
	private static final String SQL_USER_INSERT = "insert into users (userid, password, email) values (?, ?, ?)";
	public int signUp(User user) {
		List<User> list = new ArrayList<>();
		
		Connection  conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			stmt= conn.prepareStatement(SQL_USER_INSERT);
			stmt.setString(1, user.getUserid());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			postDao.closeResources(conn, stmt);
		}
		return result;
	}

}

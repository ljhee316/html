package com.itwill.lab05.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	private  final HikariDataSource ds = DataSourceUtil.getInstance().getDataSource();  //getConnetion()를 불러오기위해.
	
	//TODO: users 테이블에 insert SQL문장과 SQL실행할 메서드 만들기.
	private static final String SQL_USER_INSERT = "insert into users (userid, password, email) values (?, ?, ?)";
	public int signUp(User user) {
		
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
			closeResources(conn, stmt);
		}
		return result;
	}
	
	private static final String SQL_SIGN_IN = "select * from users where userid = ? and password = ?";
	/**
	 * 로그인할때 필요한 메서드
	 * @param user 로그인을 시도한 userid, password를 저장한 객체
	 * @return 데이터베이스의 users테이블에서 userid와 password가 일치하는 레코드가 있으면 null이아닌 User타입 객체를 리턴.
	 * 		   또는 userid 또는 password가 일치하지 않으면 null를 리턴.
	 */
	public User selectByUseridAndPassword(User user) {
		log.debug("selectByUseridPassword({})", user);
		log.debug(SQL_SIGN_IN);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User result = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_SIGN_IN);
			stmt.setString(1, user.getUserid());
			stmt.setString(2, user.getPassword());
			rs = stmt.executeQuery();
			
			if(rs.next()) { //SQL문장이 실행이 되면.
				result = fromResultSetToUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		
		return result; //false= null로 리턴.
	}
	
	//users 테이블의 points 컬럼 업데이트 메서드.
	private static final String SQL_UPDATE_POINTS = "update users set points = points + ? where userid = ?";
	public int updatePoints(String userid, int points) {
		log.debug("userid={} , updatePoints={})" ,userid, points ); 
		log.debug(SQL_UPDATE_POINTS);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		try {
			conn =ds.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE_POINTS);
			stmt.setInt(1, points);
			stmt.setString(2,userid);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt);
		}		
		return result;
	}
	
	private static final String SQL_MYPAGE = "select * from users where userid = ? ";
	public User showMypage(String uesrid) {
		log.debug("showMypage uesrid=({})", uesrid);
		log.debug(SQL_MYPAGE);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User result = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_MYPAGE);
			stmt.setString(1,uesrid);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				result = fromResultSetToUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		
		return result; 
	}
	
	
	private User fromResultSetToUser(ResultSet rs) throws SQLException {
		
		int id = rs.getInt("id");
		String userid = rs.getString("userid");
		String password = rs.getString("password");
		String email = rs.getString("email");
		int points = rs.getInt("points");
		
		return User.builder().id(id).userid(userid).password(password).email(email).points(points).build();
				
	}
	
	public void closeResources(Connection conn, Statement stmt, ResultSet rs) {
		//DB 자원들을 해제하는 순서: 생성된 순서 반대로.
		try {
			if(rs != null) rs.close();
			if(stmt!= null) stmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void closeResources(Connection conn, Statement stme) {
		closeResources(conn, stme, null);
	}

}

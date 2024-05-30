package com.itwill.lab05.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.datasource.DataSourceUtil;
import com.zaxxer.hikari.HikariDataSource;

//MVC 아키텍쳐에서 영속성 계층(repository layer) 을 담당하는 클래스.
//CRUD 작업을 담당.
//DAO(Data Access Object)
public enum PostDao {
	INSTANCE;
	
	private static final Logger log = LoggerFactory.getLogger(PostDao.class);
	private final HikariDataSource ds = DataSourceUtil.getInstance().getDataSource();//=커넥셔 풀.
	
	//Posts 테이블에서 모두 읽어오는 SQL
	private static final String SQL_SELECT_ALL = "select * from posts order by id desc";
	public  List<Post> select() {
		log.debug("select()");
		log.debug(SQL_SELECT_ALL);
		
		List<Post> list = new ArrayList<>(); //select결과를 저장할 리스트
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();//커넥션풀에서 가져옴
			stmt = conn.prepareStatement(SQL_SELECT_ALL);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Post post = fromResultSetPost(rs);
				list.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		return list;
	}
	
	//Posts 테이블에 insert하는 SQL
	private static final String SQL_INSERT = "insert into posts (title, content, author) values (?, ?, ?)";
	public int insert(Post post) {
		List<Post> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, post.getTitle());
			stmt.setString(2, post.getContent());
			stmt.setString(3, post.getAuthor());
			result = stmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeResources(conn, stmt);
		}
		
		return result;
	}
	
	//Post테이블에 delete하는 SQL
	private static final String SQL_DELETE = "delete from posts where id = ?";
	public int delete(int id) {
		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, id);
			result = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt);
		}
		
		return result;
	}
	
	//posts 테이블에서  id(pk)로 검색하는 SQL
	private static final String SQL_SELECT_BY_ID = "select * from posts where id = ?";
	public Post select(int id) {
		log.debug("select(id={})", id);
		log.debug(SQL_SELECT_BY_ID);
		
		Post post = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setInt(1,id);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				post = fromResultSetPost(rs);				
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		return post;
	}
	
	
	
	private Post fromResultSetPost(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String title = rs.getString("title");
		String content = rs.getString("content");
		String author = rs.getString("author");
		LocalDateTime createdTime  = rs.getTimestamp("created_Time").toLocalDateTime();
		LocalDateTime modifiedTime  = rs.getTimestamp("modified_Time").toLocalDateTime();
		
		return Post.builder().id(id).title(title).content(content).author(author)
				.createdTime(createdTime).modifiedTime(modifiedTime).build();	
	}
	
	
	private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
		//DB 자원들을 해제하는 순서: 생성된 순서 반대로.
		try {
			if(rs != null) rs.close();
			if(stmt!= null) stmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	private void closeResources(Connection conn, Statement stme) {
		closeResources(conn, stme, null);
	}

}

package com.itwill.spring2.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleDriver;

@Slf4j //Logger4j, slf4j2 라이브러리를 이용한 로그출력하기위해서
@ExtendWith(SpringExtension.class) //스프링프레임워크에서 유닛테스트를 실행하기위한 메인 클래스.
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/application-context.xml"}  //스프링 컨텍스트(환경변수) 파일(들)의 경로(이름)
		) 
public class JdbcTest {
	
	@Test  //테스트메서드 ->단위 테스트에서 실행할 메서드
	public void testOracleJdbc() throws SQLException {
		
		//JDBC 1. JDBC라이브러리를 드라이버 매니저에 등록
		DriverManager.registerDriver(new OracleDriver());
		log.debug("오라클 JDBC라이브러리 등록 성공");
		
		//JDBC 2. Connection 객체생성
		final String url = "jdbc:oracle:thin:@localhost:1521:xe";
		final String user = "jspstudy";
		final String password = "jspstudy";
		Connection conn = DriverManager.getConnection(url, user, password);
		Assertions.assertNotNull(conn);  //conn이 null이 아니면 단위테스트 성공
		log.debug("conn={}", conn);
		
		//JDBC 3. 사용했던 리소스 해제
		conn.close();
		log.debug("오라클 연결 해제 성공");
	}

}

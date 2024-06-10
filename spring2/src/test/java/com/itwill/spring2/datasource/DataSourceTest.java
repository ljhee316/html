package com.itwill.spring2.datasource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)//스프링프레임워크에서 유닛테스트를 실행하기위한 메인 클래스.(서버가 켜져있지않은채 테스트하기위해.)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/application-context.xml"} 
		)

public class DataSourceTest {
	
	/**
	 * 의존성 주입(DI: dependency injection), 제어의 역전(IoC inversion of control)
	 * 전통적인 자바개발 방법에서는 객체를 사용하는 곳에서 객체를 생성하고, 그 기능을 이용함.
	 * 스프링프레임워크에서는 스프링컨테이너가 필요한 객체들을 미리 메모리에 생성해두고(application-context.xml파일에서 객체가 생성됨.), 객체를 필요로 하는 곳에서는 변수선언과 애너테이션@Autowired만 사용하면
	 * 스프링컨테이너가 관리하는 빈을 필요한곳에 주입을 해줌!  ->의존성주입.
	 * application-context.xml : 스프링컨테이너가 생성/관리 하는 빈들을 설정하는 파일.
	 * 
	 * !!!스프링프레임워크: 모델2 MVC(위임패턴) 아키텍쳐를 제공하고, 의존성 주입을 제공하는 프레임워크.
	 */
	
	
	@Autowired  //스프링 컨테이너가 생성하고 관리하는 빈(bean)을 변수에 자동으로 할당(주입).  xml의(locations) 객체를 자동으로 값을 넣어줌.
	private HikariDataSource ds;
	
	@Autowired
	private SqlSessionFactoryBean session;
	
	@Test
	public void test() {
		Assertions.assertNotNull(ds);
		log.debug("ds={}", ds);
		
		Assertions.assertNotNull(session);
		log.debug("session={}", session);
	}
	
	

}

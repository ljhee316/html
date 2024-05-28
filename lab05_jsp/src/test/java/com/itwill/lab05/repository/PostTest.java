package com.itwill.lab05.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostTest {
	//log사용위해 선언.
	private static final Logger log = LoggerFactory.getLogger(PostTest.class);
	
	private PostDao dao = PostDao.INSTANCE;
	
	
	//JUnit 모듈에서 단위 테스트를 하기위해서 호출하는 메서드.
	//  규칙 1.public void  2.아규먼트 갖지않음.
//	@Test
	public void test() {
		//Post 타입 객체 생성 - Builder 디자인패턴
		 Post p = Post.builder()
				 .title("테스트")
				 .author("관리자")
				 .content("builder design pattern")
				 .id(1)
				 .build();
		 //assertNotNull(arg):arg가 null이 아니면 JUnit 테스트 성공, null이면 테스트 실패.
		 //assertNull(arg):arg가 null이 아니면 JUnit 테스트 실패, null이면 테스트 성공.
		 Assertions.assertNotNull(p);
		log.debug("p = {}", p);
	}
	
	@Test
	public void testPostDao( ) {
		Assertions.assertNotNull(dao); //PostDao가 null이 아니면 단위테스트 성공.
		log.debug("dao={}", dao);
		List<Post> result = dao.select();
		Assertions.assertNull(result);
	}

}

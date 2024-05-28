package com.itwill.lab05.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostTest {
	//log사용위해 선언.
	private static final Logger log = LoggerFactory.getLogger(PostTest.class);
	
	private PostDao dao = PostDao.INSTANCE;  //싱글톤객체 가져옴.
	
	
	//JUnit 모듈에서 단위 테스트를 하기위해서 호출하는 메서드.
	//  규칙--> 1.public void  2.아규먼트 갖지않음.
	
//	@Test  주석처리되면 실행되지않음.
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
	
//	@Test
	public void testSelect( ) {
		Assertions.assertNotNull(dao); //PostDao가 null이 아니면 단위테스트 성공.
		log.debug("dao={}", dao);
		List<Post> result = dao.select();
		Assertions.assertEquals(3, result.size());//두개의 값이 같은면 성공, 다르면 실패.(기대값, 원하는코드의값)
		for(Post p : result) {
			log.debug(p.toString());
		}
	}
	
//	@Test
	public void testInsert() {
		//insert()단위테스트
		Post post = Post.builder()
				.title("insert추가").content("insert추가테스트확인").author("guest2").build();
		int result = dao.insert(post);
		Assertions.assertEquals(1, result);
		
	}
	
//	@Test
	public void testDelete() {
		//delete()단위테스트
		int result = dao.delete(5);
	}
	
	@Test
	public void testSelect_ID() {
		Post post = dao.select(1); //id=1 테이블에 있는경우
		Assertions.assertNotNull(post);
		log.debug(post.toString());
		
		post = dao.select(0); //id=0이 테이블이 없는경우
		Assertions.assertNull(post);
	}

}

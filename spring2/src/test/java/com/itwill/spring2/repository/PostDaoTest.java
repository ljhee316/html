package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class) //  테스트를 위해 메인역할.  xml파일읽음.
@ContextConfiguration (
		locations = {"file:src/main/webapp/WEB-INF/application-context.xml"}
		)

public class PostDaoTest {

	@Autowired  //의존성주입.
	private PostDao postDao;       //////////////////////////////////////////////인터페이스를 주입하는이유?????
	
//	@Test
	public void testSelectAll() {
		Assertions.assertNotNull(postDao);
		
		List<Post> list = postDao.selectOrderByIdDesc();
		for(Post p : list) {
			System.out.println("\t" + p);
			//log.debug("p={}", p);
		}
	}
	
//	@Test
	public void testSelectById() {
		Post post1 = postDao.selectById(4);  //db에 데이터가존재할때
		Assertions.assertNotNull(post1);
		log.debug(post1.toString());
		
		Post post2 = postDao.selectById(5); //db에 데이터가 없는경우
		Assertions.assertNull(post2);
	}
	
//	@Test
	public void insert() {
		//insert할 데이터
		Post post = Post.builder().title("MyBatis테스트").content("insert테스트확인").author("히히").build();
		int result = postDao.insertPost(post);
		Assertions.assertEquals(1,result);
	}
	
//	@Test
	public void update() {		
		Post post = Post.builder().id(8).title("update").content("수정테스트").build();
		int result = postDao.updatePost(post);
		Assertions.assertEquals(1, result);
		log.debug(post.toString());
	}
	
	@Test
	public void delete() {
		int result = postDao.deletePost(7);
		Assertions.assertEquals(1, result);
	}
}






















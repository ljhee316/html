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
@ExtendWith(SpringExtension.class)  //서버없이 스프링 컨텍스트를 읽을수 있는 메인역할을 사용하기위해
@ContextConfiguration(     //xml파일위치를 알려주는 애너테이션
		locations = {"file:src/main/webapp/WEB-INF/application-context.xml"}
		)
public class CommentDaoTest {
	
	@Autowired  //스프링컨테이너가 생성/관리하는 빈을 주입받음.
	private CommentDao commentDao;
	
//	@Test
	public void select() {
		Assertions.assertNotNull(commentDao);
		List<Comment> list = commentDao.selectByPostId(4);
		for(Comment c : list) {
			log.debug(c.toString());
		}
	 }
	
//	@Test
	public void insert() {
		Comment comment = Comment.builder().postId(4).username("가가").ctext("yyyyy").build();
		int result = commentDao.insert(comment);
		Assertions.assertEquals(1, result);
	}
	
//	@Test
	public void update() {
		Comment comment = Comment.builder().id(2).ctext("댓글수정").build();
		int result = commentDao.update(comment);
		Assertions.assertEquals(1, result);
	}
	
//	@Test
	public void deleteById() {
		int result = commentDao.deleteById(2);
		Assertions.assertEquals(1, result);;
	}
	
//	@Test
	public void deleteByPostId() {
		int result = commentDao.deleteByPostId(4);
		Assertions.assertEquals(0, result);
	}
	
//	@Test
	public void selectCommentCount() {
		int result = commentDao.selectCommentCount(4);
		log.debug("selectCommentCount/=({})", result);
		Assertions.assertEquals(5, result);
	}
	
	@Test
	public void selectById() {
		Comment comment1 = commentDao.selectById(10);
		Assertions.assertNotNull(comment1);//id존재
		log.debug(comment1.toString());
		
		Comment comment2 = commentDao.selectById(21);//id없을때
		Assertions.assertNull(comment2);
	}
}

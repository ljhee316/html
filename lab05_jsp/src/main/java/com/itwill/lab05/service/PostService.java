package com.itwill.lab05.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.Post;
import com.itwill.lab05.repository.PostDao;
import com.itwill.lab05.repository.User;
import com.itwill.lab05.repository.UserDao;

//MVC아키텍쳐에서 Service(Business) 계층을 담당하는 클래스
//영속성(Persistence(=repository)) 계층의 기능을(postDao 를 호출해서) 사용해서 비즈니스 로직을 구현하는 객체.
//Controller(Web)계층에게 비즈니스 로직 결과를 리턴.
public enum PostService {
	INSTANECE;
	
	private static final Logger log = LoggerFactory.getLogger(PostService.class);
	private final UserDao userDao = UserDao.INSTANCE;
	
	//레파지토리계층의 기능들을 사용하기 위해 선언함.
	private final PostDao postDao = PostDao.INSTANCE;
	
	public List<Post> read() {
		log.debug("read()");
		List<Post>list = postDao.select();
		log.debug("list size={}",list.size());
		
		return list;
	}
	
	public int create(Post post) {
		log.debug("create(post={})", post);
		//repository 계층의 메서드를 사용해서 db테이블에 행을 삽입
		int result = postDao.insert(post);
		log.debug("insert result={}", result);
		
		//UserDao호출하기(포인트 적립) users의 points 컬럼 업데이트.
		//userid, points 불러오기  --> post get.author == userid
		userDao.updatePoints(post.getAuthor(), 10);		
		
		return result;
	}
	
	public Post read(int id) {
		log.debug("read(id={}", id);
		
		//영속성 계층 (repostory) 메서드를 호출 해서 db테이블에서 id로 검색하는 SQL실행.
		Post post = postDao.select(id);
		log.debug("{}", post);
		return post;  //컨트롤러에세 검색한 post 객체를 리턴.
	}
	
	public int delete(int id) {
		log.debug("delete(id={})", id);
		int result = postDao.delete(id);
		log.debug("delete result={}", result);
		
		return result;
	}
	
	public int update(Post post) {
		log.debug("update({})", post);
		
		//영속성 계층에 있는 메서드를 호출해서 db posts 테이블을 업데이 수행.
		 int result = postDao.update(post);
		 log.debug("update result={}", result);
		 
		return result;
		
	}
	

}

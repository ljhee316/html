package com.itwill.lab05.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.Post;
import com.itwill.lab05.repository.PostDao;

//MVC아키텍쳐에서 Service(Business) 계층을 담당하는 클래스
//영속성(Persistence(=repository)) 계층의 기능을(postDao 를 호출해서) 사용해서 비즈니스 로직을 구현하는 객체.
//Controller(Web)계층에게 비즈니스 로직 결과를 리턴.
public enum PostService {
	INSTANECE;
	
	private static final Logger log = LoggerFactory.getLogger(PostService.class);
	
	//레파지토리계층의 기능들을 사용하기 위해 선언함.
	private final PostDao postDao = PostDao.INSTANCE;
	
	public List<Post> read() {
		return postDao.select();
	}

}

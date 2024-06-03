package com.itwill.lab05.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.User;
import com.itwill.lab05.repository.User.UserBuilder;
import com.itwill.lab05.repository.UserDao;

//서비스(비즈니스)계층 싱글턴객체
public enum UserService {
	INSTANCE;
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	private UserDao userDao = UserDao.INSTANCE;
	
	//회원가입에 필요한 메서드 .  userDao.insert() 호출
	public int signUp(User user) {
		log.debug("SignUp={}", user);
		int result = userDao.signUp(user);
		log.debug("signUp result={}", result);
		
		return result;
	}
	
	public User signIn(String userid, String password) {
		log.debug("signIn(user={}, password={})", userid, password);
		
		//Date Transfer Object
		User dto = User.builder().userid(userid).password(password).build();
		User user = userDao.selectByUseridAndPassword(dto);
		log.debug("로그인결과 ={}", user);
		
		return user;
	}
	
	public User mypage(String uesrid) {
		log.debug("uesrid={}", uesrid);
		User user = userDao.showMypage(uesrid);
		log.debug("mypage결과={}", user);
		
		return user;
	}
}

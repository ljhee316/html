package com.itwill.lab05.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.User;
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
}

package com.itwill.spring2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserContoroller {

	@GetMapping("/signup")  // get방식의 /user/signup 요청을 처리하는 컨트롤러 메서드
	public void signup() {
		log.debug("GET signup()");
	}
	
    // TODO: 사용자 아이디 중복체크 REST 컨트롤러
	
	
	
	
	
	
	
	
	
	
	
	
}

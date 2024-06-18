package com.itwill.spring2.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//DB users 테이블의 모델객체
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class User {

	private Integer id;
	private String username;
	private String password;
	private String email;
	private Integer points;
	
	
}

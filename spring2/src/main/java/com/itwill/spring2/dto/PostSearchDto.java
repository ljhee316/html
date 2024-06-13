package com.itwill.spring2.dto;

import lombok.Data;

@Data
public class PostSearchDto {
	
	// 요청주소의 name과 똑같이 이름으로 선언해줘야 getter,setter를 사용할수 있음.
	private String category;
	private String keyword;

}

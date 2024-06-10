package com.itwill.spring2.repository;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//MVC아키텍쳐에서 Model담당. 데이터베이스의 posts 테이블구조.
@Builder
@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class Post {
	
	private Integer id;
	private String title;
	private String content;
	private String author;
	private LocalDateTime createdTime;  //컬럼이름에는 밑줄이 사용되었지만 필드이름은 카멜표기법으로 작성함.
	private LocalDateTime modifiedTime;

}

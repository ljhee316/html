package com.itwill.spring2.dto;

/**
 * PostController create()메서드 아규먼트 넣어주기위해 만든 DTO클래스.
 * 기본생성자와 setter메서드를 통해 자동으로  
 */



import com.itwill.spring2.repository.Post;

import lombok.Data;
@Data
public class PostCreateDto {
	
	//DTO의 필드는 요청파라미터이름과 같게 선언하고, 기본생성자와 setter메서드 만들기.  + db테이블이 가지고 있는 컬럼이여야함.
	private String title;
	private String content;
	private String author;
	
	public Post toEntity() {  //PostService에서 사용하기위해 만든 메서드. 실제 데이터베이스와 관련된 중요한 역할
		return Post.builder().author(author).content(content).title(title).build();
	}
}

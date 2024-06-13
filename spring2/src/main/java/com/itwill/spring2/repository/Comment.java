package com.itwill.spring2.repository;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//db comments 테이블의 model.
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class Comment {
	
	private Integer id;  //PK
	private Integer postId;  //posts테이블의 id참조  ->FK
	private String username;
	private String ctext;
	private LocalDateTime cerateTime;
	private LocalDateTime modifiedTime;
	
	
	

}

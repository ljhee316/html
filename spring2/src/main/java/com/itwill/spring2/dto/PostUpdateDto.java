package com.itwill.spring2.dto;

import java.time.LocalDateTime;

import com.itwill.spring2.repository.Post;

import lombok.Data;

//업데이트요청의 요청파라미터들을 저장하기위한 DTO
@Data
public class PostUpdateDto {

	private Integer id;
	private String title;
	private String content;
	
	
	public Post toEntity() {
		return Post.builder().id(id).content(content).title(title).build();
	}
	
	
}

package com.itwill.spring2.dto;

import java.time.LocalDateTime;

import com.itwill.spring2.repository.Post;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class PostSearchDto {
	
	private int id;
	private String title;
	private String author;

	
	public static PostSearchDto toEntity(Post post) {
		return PostSearchDto.builder().id(post.getId()).title(post.getTitle()).author(post.getAuthor()).build();
	}

}

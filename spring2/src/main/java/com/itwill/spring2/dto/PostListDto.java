package com.itwill.spring2.dto;

import java.time.LocalDateTime;

import com.itwill.spring2.repository.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//DTO data transfer object  db에 영향을 주지않음!
//뷰와 컨트롤러 또는 컨트롤러와 서비스 사이에서 데이터를 주고받을때 사용하는 객체.
@Data//+RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostListDto {

	private Integer id;
	private String title;
	private String author;
	private LocalDateTime modifiedTime;
	
	public static PostListDto fromEntity(Post post) {
		return PostListDto.builder().id(post.getId()).title(post.getTitle()).author(post.getAuthor())
						  .modifiedTime(post.getModifiedTime()).build();
	}
}

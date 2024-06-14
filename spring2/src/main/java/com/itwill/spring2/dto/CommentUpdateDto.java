package com.itwill.spring2.dto;

import com.itwill.spring2.repository.Comment;

import lombok.Data;

@Data
public class CommentUpdateDto {

	private Integer id;
	private String ctext;
	
	public Comment toEntity() {
		return Comment.builder().id(id).ctext(ctext).build();  //CommentUpdateDto -> Comment타입으로 변환해주는 메서드.
	}
}

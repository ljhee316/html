package com.itwill.spring2.dto;

import com.itwill.spring2.repository.Comment;

import lombok.Data;

//댓글등록할때 요청파라미터로 전달되는 값들을 저장하기 위한 DTO 클라이언트->db에 저장할거임.
@Data
public class CommentCreateDto {

	private Integer postId;
	private String ctext;
	private String username;

	
	public Comment toEntity() {
		//CommentCreateDto타입 ->Comment타입으로 변환해서 리턴
		return Comment.builder().postId(postId).ctext(ctext).username(username).build();
		
	}
}

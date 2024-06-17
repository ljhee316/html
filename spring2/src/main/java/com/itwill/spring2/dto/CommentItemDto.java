package com.itwill.spring2.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.itwill.spring2.repository.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CommentItemDto {

	private Integer id;
	private String ctext;
	private String username;
	private Timestamp modifiedTime; //자바스크립트에서 시간과 날짜 타입을 표현하기 윙해서.
	
	public static CommentItemDto fromEntity(Comment comment) {  //Comment 타입의 객체를 CommentItemDto객체로 변환해서 리턴해주는 메서드.
																//(테이블에서 가져오는거랑 서비스계층에서필요한 타입이 달라서.)
		return CommentItemDto.builder().id(comment.getId()).ctext(comment.getCtext()
				).username(comment.getUsername())
				.modifiedTime(Timestamp.valueOf(comment.getModifiedTime())).build(); 
	}
}

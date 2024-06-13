package com.itwill.spring2.repository;

import java.util.List;

//mapper.xml파일에서 실행할 sql문장들이 실행될 메서드들을 작성할 인터페이스.
public interface CommentDao {
	
	//포스트에 달려있는 모든 댓글 검색
	public List<Comment> selectByPostId(Integer postId);
	
	//포스트에 새로운 댓글 추가하기
	public int insert(Comment comment);
	
	//댓글내용, 수정시간 업데이트
	int update(Comment comment);
	
	//댓글아이디로 삭제
	int deleteById(Integer id);
	
	//포스트에 달려있는 모든 댓글 삭제
	int deleteByPostId(Integer postId);
	
	//포스트의 달려있는 댓글갯수 검색
	Integer selectCommentCount(Integer postId);
	
	//댓글아이디(PK)로 검색
	Comment selectById(Integer id);

}

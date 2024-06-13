package com.itwill.spring2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.dto.PostSearchDto;
import com.itwill.spring2.dto.PostUpdateDto;
import com.itwill.spring2.repository.Post;
import com.itwill.spring2.repository.PostDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service //스프링컨테이너에 서비스 컴포넌트로 등록.
@RequiredArgsConstructor  //final필드들을 초기화하는 아규먼트를 갖는 생성자. 필수로 필요한 아규먼트를 갖는 생성자를 초기화해줌.
public class PostService {
	
//	@Autowired  //애너테이션을 사용한 의존성 주입(DI:dependency Injection)
//	private PostDao postDao;

	//생성자에 의한 의존성주입:
	//1. final 필드선언. 2.final 필드를 초기화하는 생성자 작성.
	private final PostDao postDao;	
	
//	public PostService(PostDao postDao) {  //아규먼트로 postDao의 값을 주입시켜줌.    ==>@RequiredArgsConstructor  애너테이션으로 대신사용가능.
//		this.postDao = postDao;
//	}
	
	public List<PostListDto> read() {
		log.debug("read()");
		
		List<Post> list = postDao.selectOrderByIdDesc();
		
//      List<PostListDto> result = new ArrayList<>();   //아래 람다표현식으로 대체함.
//      for (Post p : list) {
//          result.add(PostListDto.fromEntity(p));
//      }
		
		return list.stream()
				.map(PostListDto::fromEntity) //map((x)-> PostListDto.fromEntity(x))  람다표현식 추합형  //map사용이유: 타입변환 PostListDto -> List<PostListDto>
				.toList();
	}
	
	
	public Post readByID(int id) {
		log.debug("readByID({})", id);
		Post post =postDao.selectByID(id);
		
		return post;
	}
	
	public int create(PostCreateDto dto) {
		log.debug("create({})", dto);
		int result = postDao.insertPost(dto.toEntity());//PostCreateDto 에서 만듬.
		log.debug("insert결과={}", result);
		return result;		
	}
	
	public int update(PostUpdateDto dto) {
		log.debug("update({})",dto);
		int result = postDao.updatePost(dto.toEntity());
		return result;
	}
	
	public int delete(int id) {
		log.debug("delete(id=({})", id);
		//리포지토리메서드를 호출해서 delete쿼리 실행.
		int result = postDao.deletePost(id);
		log.debug("delete결과=({})", result);
		
		return result;
	}
	
	public List<PostListDto> search(PostSearchDto dto) {
		log.debug("search({})",dto);
		
		List<Post> list = postDao.search(dto);
		return list.stream().map(PostListDto::fromEntity).toList();
	}
	

	
}

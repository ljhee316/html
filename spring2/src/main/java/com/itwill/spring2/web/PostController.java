package com.itwill.spring2.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/post") //->PostController 클래스의 모든 컨트롤러 메서드의 매핑주소는 /post로 시작함.
public class PostController {
	
	private final PostService postService; //생성자의 의한 의존성주입.

	@GetMapping("/list")  // 실 url주소: /post/list
	public void list(Model model) {
		log.debug("list()");
		
		//서비스 컴포넌트의 메서드를 호출, 포스트목록을 읽어옴-> 뷰에 전달.
		List<PostListDto> list = postService.read();
		model.addAttribute("post", list);
		
		//뷰이름:WEB-INF/views/post/list.jsp
	}
}

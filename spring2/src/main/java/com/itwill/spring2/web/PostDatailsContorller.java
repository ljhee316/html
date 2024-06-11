package com.itwill.spring2.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.repository.Post;
import com.itwill.spring2.service.PostService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostDatailsContorller {
	
	private final PostService postService;
	
	@GetMapping("/post/details")
	public void details(Model model, @RequestParam(name="id") int id) {
		log.debug("details()");	
		
		//id로 테이블에서 내용 불러오고 뷰에 전달.
		Post post = postService.readByID(id);
		model.addAttribute("post", post);
	}

}

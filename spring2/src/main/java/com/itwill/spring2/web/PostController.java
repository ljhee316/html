package com.itwill.spring2.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.dto.PostSearchDto;
import com.itwill.spring2.dto.PostUpdateDto;
import com.itwill.spring2.repository.Post;
import com.itwill.spring2.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/post") //->PostController 클래스의 모든 컨트롤러 메서드의 매핑주소는 /post로 시작함.
public class PostController {
	
	private final PostService postService; //생성자의 의한 의존성주입. ->final

	@GetMapping("/list")  // 실 url주소: /post/list
	public void list(Model model) {
		log.debug("list()");
		
		//서비스 컴포넌트의 메서드를 호출, 포스트목록을 읽어옴-> 뷰에 전달.
		List<PostListDto> list = postService.read();
		model.addAttribute("posts", list);
		
		//뷰이름:WEB-INF/views/post/list.jsp
	}
	
	@GetMapping({"/details", "modify"})  //get방식 details와modify 2개의 요청을 처리하는 메서드 -> 같은기능이라 가능.
	public void details(Model model, @RequestParam(name="id") int id) {
		log.debug("details({})", id);	
		
		//id로 테이블에서 내용 불러오고 뷰에 전달.
		Post post = postService.readByID(id);
		model.addAttribute("post", post);
		
		// 리턴 타입이 void이므로 뷰의 이름은
        // (1) 요청 주소가 /post/details인 경우 /WEB-INF/views/post/details.jsp
        // (2) 요청 주소가 /post/modify인 경우 /WEB-INF/views/post/modify.jsp
	}
	
	@GetMapping("/create")
	public void create() {
		log.debug("create()");
	}

	@PostMapping("/create") 
//	public String create(@RequestParam(name="title") String title, @RequestParam(name="content") String content, 
//						 @RequestParam(name="author") String author, Model model) {
	
	public String create(PostCreateDto dto) {  //RequestParam이 여러가지일 경우 DTO클래스 생성해서 아규먼트로 설정하기
		
		log.debug("Post:create(dto={})", dto);
		
		//서비스 컴포넌트의 메서드를 호출해 db에 새글을 저장.
		postService.create(dto);
		
		return "redirect:/post/list";  //post/list 페이지로 이동
	}
	
//	modify.jsp  따로 맵핑한 메서드   실제맵핑은 위에 datails랑 같이 함.
//	@GetMapping("/modify")
//	public void update(PostListDto dto, Model model)aaa {
//		log.debug("update()");
//		Post post = postService.readByID(dto.getId());
//		model.addAttribute("post",post);
//		
//		postService.update(post);
//	}
	
	
	@GetMapping("/delete")   //관련postid에서 댓글들을 먼저 삭제하고, post를 삭제하기.
	public String delete(@RequestParam(name="id") int id) {
		log.debug("delete(id={})", id);
		//서비스 컴포넌트의 메서드를 호출해서 db에서 해당 아이디의 글을 삭제.
		postService.delete(id);
		return "redirect:/post/list";
	}
	
	@PostMapping("/update")  //  =>static/js/post/modify에서 요청주소를 /update로 지정함.
	public String update (PostUpdateDto dto) {
		log.debug("update()");
		postService.update(dto);	
		return "redirect:/post/details?id=" + dto.getId();  //요청주소가 변경된 내용으로 상세보기창으로 리다이렉트함.
	}
	
	@GetMapping("/search")
	public String search (Model model, PostSearchDto dto) {
        log.debug("search({})", dto);
        
        List<PostListDto> list = postService.search(dto);
        model.addAttribute("posts", list);
        
        return "post/list";
	}
}

package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.service.PostService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name="postDeleteController", urlPatterns = {"/post/delete"})
public class PostDeleteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostDeleteController.class);
	private final PostService postService = PostService.INSTANECE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doGet()");
		
		//쿼리 문자열에 포함된 요청 파라미터 id값 읽기
		int id = Integer.parseInt(req.getParameter("id"));
		log.debug("id={}", id); 
		
		//서비스계층의 메서드를 호출해서 글 삭제서비스 실행
		postService.delete(id);
		
		//list 뷰로 이동(redirect-->delete?id=2  에서 list 주소로 변경하기위해서. )
		String url = req.getContextPath() + "/post/list";  //getContextPath 
		log.debug("redirect:{}", url);
		resp.sendRedirect(url);
	
		
	}

}

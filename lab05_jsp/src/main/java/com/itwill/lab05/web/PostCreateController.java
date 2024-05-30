package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.Post;
import com.itwill.lab05.repository.Post.PostBuilder;
import com.itwill.lab05.service.PostService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name="postCreateController", urlPatterns = {"/post/create"})
public class PostCreateController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostCreateController.class);
	private final PostService postService = PostService.INSTANECE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doGet()");
		
		// 새글 작성 폼(양식)을 작성하는 뷰(jsp)로 이동
		req.getRequestDispatcher("/WEB-INF/views/post/create.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doPost()");
		
		//요청에 포함된 제목,내용,작성자 읽기
		//req.getParameter(arg) 요청파라미터의 아규먼트는 name속성의 값.
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String author = req.getParameter("author");
		Post post = Post.builder().title(title).content(content).author(author).build();  //builder 메서드로 객체생성
		log.debug("post={}", post);
		
		//TODO 서비스객체에 메서드 호출해서 DB저장
		postService.create(post);		
		
		//post/list 페이지로 이동  contextRoot를 꼭 같이 입력해줘야함.
		 String url = req.getContextPath() + "/post/list";//contextRoot 호출
		 log.debug("redirect: " + url);
		 resp.sendRedirect(url);   
		 //PRG방식(post-redirect-get)
	}
	
	
}

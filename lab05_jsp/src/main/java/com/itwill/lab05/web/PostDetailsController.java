package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.Post;
import com.itwill.lab05.service.PostService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name ="postDetailsController", urlPatterns = {"/post/details"})
public class PostDetailsController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostDetailsController.class);
	private final PostService postService = PostService.INSTANECE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doGet()");
		//query string 에 포함 된 요청 파라미터 id값을 읽음.
		int id = Integer.parseInt(req.getParameter("id"));
		log.debug("id={}",id);
		//서비스계층의 메서드를 호출해서 해당 id의 post 정보를 db에서 읽음
		Post post = postService.read(id);  //서비스메서드 호출
		
		//검색된 Post객체를 뷰에게 전달
		req.setAttribute("post", post); //뷰에게 전달  details.jsp.에서 EL${}을 사용할수있었던 이유.
		
		//details 뷰로 화면이동(forward)
		req.getRequestDispatcher("/WEB-INF/views/post/details.jsp").forward(req, resp);
	}

}

package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.User;
import com.itwill.lab05.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="myPageController" , urlPatterns = {"/user/mypage"})
public class MyPageController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MyPageController.class);
	private final UserService userSerive = UserService.INSTANCE;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doGet()");
		
		String userid = req.getParameter("userid");
		log.debug("userid={}", userid);
		
		//id로 정보읽어오기  user서비스계층 메서드 호출하기
		User user = userSerive.mypage(userid);
		req.setAttribute("user", user);  //mypage.jsp에서 ${user.userid} ${user.password} 등 정보를 불러올 변수이름
		
		//user/mypage뷰 보이기
		req.getRequestDispatcher("/WEB-INF/views/user/mypage.jsp").forward(req, resp);
	}

}

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

@WebServlet(name = "userSignUpController", urlPatterns = { "/user/signup" })
public class UserSignUpController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UserSignUpController.class);
	private final UserService userService = UserService.INSTANCE;

	// TODO: 회원가입에 필요한 요청 처리 메서드
	// 회원가입 폼 작성하는 뷰로 이동(doGet)
	
	  @Override 
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { log.debug("doGet()");
	  	req.getRequestDispatcher("/WEB-INF/views/user/signup.jsp").forward(req,resp);  
	  }

	  @Override
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("dopost()");
		// 요청에 포함된 userid, password, email 읽기 (dopost)
		String userid = req.getParameter("userid");
		String password = req.getParameter("password");
		String email = req.getParameter("email");

		User user = User.builder().userid(userid).password(password).email(email).build();
		log.debug("{}", user);

		// 서비스 객체에 메서드 호출해서 db에 저장하기
		userService.signUp(user);

		// 회원가입 완료 되면 lab05 뷰로 이동 (redirect)
		String url = req.getContextPath();
		log.debug("rediret:{}", url);
		resp.sendRedirect(url);

	}

}

package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name="userSignOutController" , urlPatterns = {"/user/signout"})
public class UserSignOutController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UserSignOutController.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doGet()");
		
		//로그아웃:
		//세션에 저장된 signedInUser(로그인정보)를 삭제
		
		//세션객체를 삭제(무효화한다)  --> 세션객체만 삭제를 해도 로그인정보가 같이 삭제된다. 사실 이거 한개만해도됨.
		
		HttpSession session = req.getSession(); // 세션을 읽어와서
		session.removeAttribute("signedInUser"); //로그인정보삭제함  아규먼트: setAttribute에서 사용한 첫번재 속성이름.
		session.invalidate(); // 세션삭제
		
		//로그아웃이후에 로그인 페이지로 이동
		String url = req.getContextPath() + "/user/signin";
		resp.sendRedirect(url);// 페이지로 이동.
	}
	

}

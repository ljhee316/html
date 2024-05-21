package com.itwill.lab04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class RedirectServlet
 */
@WebServlet(name="redirectServlet",urlPatterns = {"/ex4"})
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("RedirectServlet::doGet() 호출");
		
		response.sendRedirect("ex3");//ex3로 리다이렉트하라고 웹에게 응답을 보냄. 
		//response.sendRedirect("https://www.naver.com"); //다른 웹 서버로 페이지 이동

		//리다이렉트방식의 페이지 이동
		//최초요청(request) -> redirect 응답(response) -> 재요청(새로운 request, response가 만들어짐)-> 응답
		//최초요청주소가 이동하는 페이지로 바뀜.
		//최초요청의 request, response 객체가 이동하는 페이지로 전달되지 않음!
		//같은 WAS뿐만 아니라 다른 웹 서버, 다른 웹 애플리케이션으로도 이동 가능.
	}

}

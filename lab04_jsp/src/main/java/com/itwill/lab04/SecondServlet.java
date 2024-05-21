package com.itwill.lab04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class SecondServlet
 */
 //서블릿 URL(요청주소)매핑방법:
 //1. web.xml(배포관리자.deployment descript) 에서 <servlet>,<servlet-mapping> 로 설정하거나
 //2. 서블릿 클래스에서 @WebServlet 에너테이션으로 설정
 // 둘중 하나만 설정해야함!
@WebServlet(name="secondServlet", urlPatterns = {"/ex2"})// servlet 설정하기.
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SecondServlet::doGet() 메서드호출");
		// WAS가 클라이언트로 보내는 컨텐드 타입 설정:
		response.setContentType("text/html; charset-UTF-8");
		 PrintWriter out = response.getWriter();
		 out.append("<html>")
		 	.append("<body>")
		 	.append("<h1>두번째 서블릿</h1>")
		 	.append("<a href='/lab04'>인덱스페이지</a>")
		 	.append("</body>")
		 	.append("</html>");
	}

}

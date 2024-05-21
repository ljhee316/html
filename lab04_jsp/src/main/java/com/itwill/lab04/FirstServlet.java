package com.itwill.lab04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class FirstServlet
 */
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        System.out.println("FirstServelt 생성...");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //WAS(Web Application Server):웹 요청(request)/응답(response)을 처리하는 ㅍ로그램.=>tomcat
    //doGet: get방식의 요청이 왔을때 WAS가 호출하는 메서드.
    //doPost: post방식의 요청이 왔을때 WAS가 호출하는 메서드.
    //파라미터 request는 클라이언트가 서버로 보낸 요청의 정보, 기능들을 갖는 객체.
    //파라미터 reponse는 서버가 클라이언트로 보낼 응답의 정보, 기능들을 갖는 객체.
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FirstServelt::doGet호출");
		
		response.setContentType("text/html; charset=UTF-8");
		
		response.getWriter()
		.append("<!doctype html>")
		.append("<html>")
		.append("<head>")
		.append("<meta charset='UTF-8' />")
		.append("<title>Servlet</title>")
		.append("</head>")
		.append("<body>")
		.append("<h1>첫번째 Servlet</h1>")
		.append("<a href='/lab04'>인덱스페이지</a>")
		.append("</body>")
		.append("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FirstServelt::doPost()호출");
		doGet(request, response);
	}

}

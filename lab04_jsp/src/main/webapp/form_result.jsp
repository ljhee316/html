<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>form_result</title>
<style>
    <% 
    String color = request.getParameter("color");//query string에 포함된 요청 파라미터를 찾음.
    String textColor = "";
    switch(color) {
    case "r":
        textColor = "crimson";
        break;
    case "b":
    	textColor = "slateblue";
        break;
    case "g":
    	textColor = "darkgreen";
        break;
     default:
        textColor = "black";
    }
    %>
    span#username {
    color: <%= textColor %>
    }
</style>
</head>
<body>
    <%@ include file="header.jspf" %>
    <h1>폼 양식 제출 결과</h1>
    <%--
    JSP내장객체:
    jsp파일이 java파일로 변환될 때 _jspService(request, response)메서드안에서 
    선언된 지역 변수.
    스트립트릿에서 지역 변수를 사용할수 없는 이름.
    -request: 클라이언트에서 서버로 보내는 요청 정보들과 메서드를 가지고 있는 객체.
        -getPrameter()
    -response: 서버에서 응답을 만들때 사용되는 객체.
        -setContentType(), sendRedirect()
    -out: JSP Writer. html 코드 작성 기능을 가지고 있는 객체.
        -write(), print()
    -pageContext: JSP페이지가 유지되는 동안 정보를 저장하기 위한 객체.
    -session: 세션이 유지되는 동안 정보를 저장하기 위한 객체. (로그인상태유지할때)
    -application: 웹 애플리케이션이 동작중에 유지되는 정보를 저장하기 위한 객체.
    -config:서블릿의 환경설정 정보를 저장하는 객체.    
     --%>
    <%
    String username = request.getParameter("username");
    %>
    <h2>안녕하세요<span id="username"><%= username %></span>!</h2>
    
    <% if(username.equals("admin")) { %>
        <h3>관리자 페이지</h3>
    <% } else { %>
        <h3>일반 사용자 페이지</h3>
    <% } %>
    
</body>
</html>
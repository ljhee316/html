<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    <%-- page 지시문 trimDirectiveWhitespaces 속성:
    jsp파일이 java 코드로 변환되는 과정에서 JSP태그들이 빈줄로 대체되는데
    빈줄을 삭제할 것인지 아닌지를 설정하는 속성. 기본값은 false. --%>
    
    
    <%-- JSP주석
    
    1.Servlet(Serve + Applet): WAS에서 실행되는, 요청을 처리하고 응답을 보내는 작은 자바프로그램.
     (1)서블릿객체의 생성과 관리, 서블릿 메서드, 호출은 WAS가 담당.
     (2)서블릿의 동작방식:
       -최초요청 : WAS가 서블릿 객체를 생성(생성자호출) -> 메서드(doGet/doPost)호출 -> 응답.
       -최초요청이 아닌경우 : 생성된 서블릿 객체의 메서드 호출 -> 응답. 
         
    2.JSP(Java/Jakarta Server Page)
     (1)서블릿은 순수 자바 코드이기 때문에 html작성 어려움.
     (2)html형식의 파일에서 자바 코드들이 실행 될수 있도록 만든 server-side문법.
     (3)JSP동작원리:
      -최초요청 : jsp파일을 java파일로 변환->java코드를 class파일로 컴파일 -> 객체생성(생성자호출)
                  ->매서드 호출 -> 응답
      -최초요청이 아닌경우 : 생성된 객체에서 메서드 호출 -> 응답
      
    3.JSP구성요소(문법)
     (1)주석 : jsp파일이 java코드로 변환될때 무시되는 코드.
     (2)지시문(directive) : <%@ ... %> 
        -JSP페이지설정, 컨텐트타입. 인코딩, 옵션들을 설정. Java import구문.
     (3)선언문(declaration) : <%! ... %>
        -jsp파일이 java코드로 변환될때, 클래스의 필드,메서드 선언부분.
     (4)스트립트릿(scriptlet) : <% ... %>   자바 메인메서드바디안.
        -jsp파일이 java코드로 변환될때, _jspService(req, resp)메서드 안에 포함되는 자바 코드.
        -지역변수 선언 & 초기화, 메서드 호출, 조건문, 반복문, ...
     (5)식, 표현식(expression) : <%= ... %>
        -jsp파일이 java코드로 변환될때, out.write() 메서드의 아규먼트로 전달되는 값.
        -html코드에 문자열을 삽입할때 사용함.
     --%>
     
<%!
//java코드 사용가능 (java declaration)
private static final String USER ="scott";  //상수필드

//메서드서언
private void printLog(String msg) {
 System.out.println("[intro.jsp]" + msg);   
}

%>     

<% /* scriptlet*/
printLog("intro.jsp 실행");

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
    <nav>
        <a href="/lab04">인덱스페이지</a>
    </nav>
    <main>
        <h1>JSP소개</h1>
        
        <% //스크립트릿
        LocalDateTime now = LocalDateTime.now();  //지역변수선언&초기화
        String date = String.format("%d-%02d-%02d",
        	    now.getYear(),now.getMonthValue(),now.getDayOfMonth());
        String time = String.format("%02d:%02d:%02d",
                now.getHour(), now.getMinute(),now.getSecond());
        %>
        
        <h2>날짜: <%= date %></h2> <!-- expression -->
        <h2>시간: <%= time %></h2>
        <h2>USER: <%= USER %></h2>
    </main>
</body>
</html>
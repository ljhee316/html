<%@page import="com.itwill.lab04.model.Contact"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Action tag</title>
<style>
    p {
        border: 1px solid gray;
        border-radius: 8px;
        margin: 16px;
        padding: 16px;
    }
</style>
</head>
<body>
    <%@ include file="header.jspf" %>
    <main>
        <h1>JSP Action Tag</h1>
        <%--
        JSP 액션태그: 스크립트릿에서 사용되는 일부 자바 코드들을 html 또는 xml과 비슷하게 
                      태그, 태그속성, 태그컨텐트를 작성해서 대체하는 문법.
        -JSP action tag 대/소문자를 구분!!!(html태그는 대/소문자 구분안함)
        ==> 확장명이 jsp 이여야만함!
        -<jsp:forward></jsp:forward> : 페이지이동
        -<jsp:include></jsp:include> : 파일합치기
        -<jsp:useBean></jsp:useBean> : 객체생성(기본생성자호출)
        -<jsp:getProperty></jsp:getProperty> : getter
        -<jsp:setProperty></jsp:setProperty> : setter
         --%>
         
         <h2>액션태그를 사용하지 않은 자바객체생성</h2>
         <%
         Contact contact1 = new Contact();
         contact1.setId(1); 
         contact1.setName("홍길동");
         contact1.setPhone("010-1111-2222");
         contact1.setEmail("hgd@itwill.com");
         //Contact contact3 = new Contact(3,"3","3","3");
         %>
         
         <p>
            ID: <%= contact1.getId() %><br />
            NAME: <%= contact1.getName() %><br />
            PHONE: <%= contact1.getPhone() %><br />
            EMAIL: <%= contact1.getEmail() %><br />
            <%-- <%= contact3 %>  -Contact [id=3, name=3, phone=3, email=3]  >--%>
         </p>
         
         <h2>액션태그 자바 빈 사용한 객체생성</h2>
         <jsp:useBean id="contact2" class="com.itwill.lab04.model.Contact"/> <%-- Contact contact2 = new Contact(); --%>
         <jsp:setProperty property="id" name="contact2" value="2"/> <%-- contact2.setId(2); --%>
         <jsp:setProperty property="name" name="contact2" value="오쌤"/>
         <jsp:setProperty property="phone" name="contact2" value="010-1234-5678"/>
         <jsp:setProperty property="email" name="contact2" value="oo@itwill.com"/>
         
         <p>
            ID: <jsp:getProperty property="id" name="contact2"/><br />
            NAME: <jsp:getProperty property="name" name="contact2"/><br />
            PHONE: <jsp:getProperty property="phone" name="contact2"/><br />
            EMAIL: <jsp:getProperty property="email" name="contact2"/><br />
         </p>
         
         
    </main>
    <jsp:include page="footer.jsp" />
</body>
</html>
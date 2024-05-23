<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL</title>
</head>
<body>
    <%@ include file="header.jspf" %>
    <main>
        <h1>EL(Expression Language)</h1>
        <%--
        EL : JSP의 expression(<%= ... %>)을 대체하는 문법.
        EL문법: ${ 식 }
         -지시문<%@ ... %>안에서는 사용 못함.
         -선언문<%! ... %>안에서는 사용 못함.
         -스크립트릿<% ... %>안에서는 사용 못함.
         -expression(%= ... %>안에서는 사용 못함   ===> JSP태그 안에서 모두 사용 안됨!!!
         -그 외 JSP태그를 제외한 JSP모든 코드 안에서는 언제든지 사용가능.
            --html태그의 컨텐드, 속성 값 설정
            --css 속성 값 설정
            --html안에 <script>태그에 포함된 javascript 코드안에서 사용가능.(별도의 jsp파일이 아닌)
            --JSTL안에서 사용 가능.
         --%>
         
         <p><%= 1 + 1 %></p> <%--JSP Expression --%>
         <p>${1 + 1}</p> <%-- EL --%>
         
         <%-- 상태저장에 사용되는 JSP 내장객체 
         -pageContext: JSP페이지가 유지되는 동안만 정보저장.
         -request: 요청객체가 유지되는 동안 정보저장.
         -session:세션이 유지되는 동안 정보저장.
         -application:웹 애플리케이션이 동작하는 동안 정보저장.
         
         -->사용범위: pageContext < request < session < application
         -->상태 저장 메서드: setAttribute("속성이름",속성값)
         -->상태 값 읽는 메서드: getAtrribute("속성이름")
         --%>
         
         <% 
         pageContext.setAttribute("id", 1);
         request.setAttribute("id", 2);
         session.setAttribute("id", "admin");
         %>   
         
         <h2>JSP Expression을 사용한 상태정보읽기</h2>
         <p>
         page: id = <%= pageContext.getAttribute("id") %><br />
         request: id = <%= request.getAttribute("id") %><br />
         session: id = <%= session.getAttribute("id") %>       
         </p>
         <%--
         EL vs JSP 내장 객체
         o. pageScope - pageContxct
         o. requestScope - requset
         o. sessionScope - session
         o. applicationScope - application
        
         EL ${ attr }에서 상태 정보를 찾는 순서:
         (1) ${pageScope.attr}
         (2) ${requestScope.attr}
         (3) ${sessionScope.attr}
         (4) ${applicationScope.attr}
          --%>
          <p> EL: id =${id} </p> <%-- ${pageContext.id} --%>
          
          <% request.setAttribute("username", "scott"); %>
          <p>EL: username = ${username}</p>
          
          <h2>EL 삼항연산자</h2>
          <% pageContext.setAttribute("number", 123); %>
          <p>${ number } = ${ (number % 2 == 1) ? '홀수' : '짝수' }</p>  <%-- ==1 true인지 확인 --%>
          <% session.setAttribute("logInUser", "admin"); %>
          <p>${ (logInUser !=  null) ? '안녕하세요,' : '로그인하세요.' } ${ logInUser }!</p>
          
         
         <h2>EL을 사용한 상태 정보 읽기</h2> <%-- _Scope.속성이름 --%>
         <p>
         page: id = ${ pageScope.id} <br/>
         request: id = ${requestScope.id} <br/>
         session: id = ${sessionScope.id} 
         </p>
         
         
    </main>

</body>
</html>
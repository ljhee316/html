<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Spring Legacy2</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <div class="container-fluid">
        <c:set var="pageTitle" value="Post List"/>
        <%@ include file="../fragments/header.jspf" %>
    </div>
    <main>
        <div class="card">
            <div class="mt-2 card-header text-center">
                <c:url var="postSearchPage" value="/post/search"/>
                <form method="get" action="${postSearchPage}">
                    <div class="row"> <!-- 비율로 분할(기본 12개컬럼으로 이루어져있음.) -->
                        <div class="col-3">
                            <select class="form-control" name="category">
                                <option value="t">제목</option>
                                <option value="c">내용</option>
                                <option value="tc">제목+내용</option>
                                <option value="a">작성자</option>
                            </select>
                        </div>
                        <div class="col-7">
                            <input class="form-control" type="text" name="keyword" placeholder="검색어입력" required />
                        </div>    
                        <div class="col-2">
                            <input type="submit" class="form-control btn btn-outline-secondary" value="검색" />
                        </div>
                    </div>
                </form>
            </div>
            <div class="mt-2 card-body">
                <table class="table table-hover text-center">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>수정시간</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${posts}" var="p">
                            <tr>
                                <td>${p.id}</td>
                                <td><c:url var="postDetailsPage" value="/post/details">
                                        <c:param name="id" value="${p.id}"></c:param>
                                    </c:url> 
                                    <a href="${postDetailsPage}">${p.title}</a>
                                </td>
                                <td>${p.author}</td>
                                <td>${p.modifiedTime}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
    
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>
</body>
</html>
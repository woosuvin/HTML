<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Spring2</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" 
            rel="stylesheet" 
            integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" 
            crossorigin="anonymous">
	</head>
	<body>
		<header class="alert alert-success container text-center" role="alert">
            <h1>포스트 목록 페이지</h1>
        </header>
        
        <nav class="navbar navbar-expand-lg bg-body-tertiary container text-center">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ">
                <li class="nav-item">
                    <c:url var="mainPage" value="/"></c:url>
                    <a class="nav-link active" aria-current="page" href="${ mainPage }">메인 페이지</a>
                </li>
                <li class="nav-item">
                    <c:url var="postCreatePage" value="/post/create"></c:url>
                    <a class="nav-link active" aria-current="page" href="${ postCreatePage }">새 포스트 작성</a>
                </li>
            </ul>
        </nav>
        
        <main>
            <div>
                <table class="table container text-center">
                    <thead>
                        <tr>
                            <th scope="col">번호</th>
                            <th scope="col">제목</th>
                            <th scope="col">내용</th>
                            <th scope="col">작성시간</th>
                        </tr>
                    </thead>
                    <tbody class="table-group-divider">
                        <c:forEach items="${ posts }" var="post">
                            <tr>
                                <td scope="row">${ post.id }</td>
                                <td scope="row">${ post.title }</td>
                                <td scope="row">${ post.content }</td>
                                <td scope="row">${ post.modified_time }</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>            
            </div>
        </main>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" 
                crossorigin="anonymous">
            </script>
	</body>
</html>
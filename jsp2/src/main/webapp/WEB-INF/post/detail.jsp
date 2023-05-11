<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>POST</title>
	</head>
	<body>
		<header>
            <h1>detail</h1>
        </header>
        
        <nav>
            <ul>
                <li>
                    <c:url var="mainPage" value="/"></c:url>
                    <a href="${ mainPage }">메인페이지</a>
                </li>
            </ul>
        </nav>
        
        <main>
            <c:url var="postDetail" value="/post/detail"></c:url>
            
            <form action="${ postDetail }" method="post">
                <div>
                    <input type="text" name="title" value="${ post.title }" />
                </div>
                <div>
                    <textarea rows="5" cols="80" name="content">${ post.content }</textarea>
                </div>
                <div>
                    <input type="text" name="author" value="${ post.author }" />
                </div>
                <div>
                    <input type="text" name="author" value="${ post.modifiedTime }" />
                </div>
            </form>
        </main>
        
	</body>
</html>
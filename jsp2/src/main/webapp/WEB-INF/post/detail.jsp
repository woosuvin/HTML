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
            <h1>포스트 상세 보기 페이지</h1>
        </header>
        
        <nav>
            <ul>
                <li>
                    <c:url var="mainPage" value="/"></c:url>
                    <a href="${ mainPage }">메인페이지</a>
                </li>
                <li>
                    <c:url var="postList" value="/post"></c:url>
                    <a href="${ postList }">포스트 목록 페이지</a>
                </li>
                <li>
                    <c:url var="postModify" value="/post/modify">
                        <c:param name="id" value="${ post.id }"></c:param>
                    </c:url> 
                    <a href="${ postModify }">포스트 수정</a>
                </li>
            </ul>
        </nav>
        
        <main>
            <form>
                <div>
                    <input type="number" value="${ post.id }" readonly />
                </div>
                <div>
                    <input type="text" name="title" value="${ post.title }" readonly />
                </div>
                <div>
                    <textarea rows="5" cols="80" name="content" readonly >${ post.content }</textarea>
                </div>
                <div>
                    <input type="text" name="author" value="${ post.author }" readonly />
                </div>
                <div>
                    <input type="text" name="time" value="${ post.modifiedTime }" readonly />
                </div>
            </form>
        </main>
        
	</body>
</html>
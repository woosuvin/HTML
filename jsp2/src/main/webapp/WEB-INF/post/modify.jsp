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
            <h1>포스트 수정 페이지</h1>
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
                    <c:url var="postDetail" value="/post/detail">
                        <c:param name="id" value="${ post.id }"></c:param>
                    </c:url> 
                    <a href="${ postDetail }">포스트 상세 보기</a>
                </li>
            </ul>
        </nav>
        
        <main>
            <form id="postModifyForm">
                <div>
                    <input id="id" name="id"
                         type="number" value="${ post.id }" readonly />
                </div>
                <div>
                    <input id="title" name="title" 
                        type="text" value="${ post.title }" autofocus />
                </div>
                <div>
                    <textarea id ="content" name="content" rows="5" cols="80" >${ post.content }</textarea>
                </div>
                <div>
                    <input type="text" value="${ post.author }" readonly />
                </div>
                <div>
                    <button id="btnUpdate">수정완료</button>
                    <button id="btnDelete">삭제</button>
                </div>
            </form>
        </main>
        
        <script src="../js/post-modify.js"></script>
	</body>
</html>

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
            <h1>새 포스트 작성 페이지</h1>
        </header>
        
        <nav>
            <ul>
                <li>
                    <span>${ signedInUser }</span> 
                    <c:url var="signOut" value="/user/signout"></c:url> 
                    <a href="${ signOut }">로그아웃</a>
                </li>
                <li>
                    <%-- <c:url>에서 "/" 요청주소는 context root까지. --%>
                    <c:url var="mainPage" value="/"></c:url>
                    <a href="${ mainPage }">메인 페이지</a>
                </li>
                <li>
                    <c:url var="postList" value="/post"></c:url>
                    <a href="${ postList }">포스트 목록 페이지</a>
                </li>
            </ul>
        </nav>
        
        <main>
            <c:url var="postCreate" value="/post/create"></c:url>
            <form action="${ postCreate }" method="post">
            <!-- action="요청 보내는 주소", 따로 지정하지 않으면 기본값은 현재 페이지 -->
                <div>
                    <input type="text" name="title" placeholder="제목 입력" required autofocus />
                </div>
                <div>
                    <textarea rows="5" cols="80" name="content" placeholder="내용 입력" required></textarea>
                </div>
                <div>
                    <%-- 로그인한 사용자 아이디를 value로 설정, hidden: 숨김, 화면에는 보이지 않게. --%>
                    <input type="hidden" name="author" value="${ signedInUser }" readonly />
                </div>
                <div>
                    <input type="submit" value="작성 완료" />
                </div>
            </form>
        </main>
        
	</body>
</html>
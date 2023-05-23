<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<div class="container-fluid">
		<header class="my-2 p-5 text-center text-bg-secondary">
            <h1>포스트 상세 보기</h1>
        </header>
        
        <nav class="navbar-expand-lg bg-body-tertiary text-center justify-content-center">
            <ul class="nav justify-content-center">
                <li class="nav-item">
                    <c:url var="mainPage" value="/" />
                    <a class="nav-link active text-body-emphasis" aria-current="page" href="${ mainPage }">메인 페이지</a>
                </li>
                <li class="nav-item">
                    <c:url var="postListPage" value="/post/list" />
                    <a class="nav-link text-body-emphasis"  href="${ postListPage }">포스트 목록</a>
                </li>
            </ul>
        </nav>
        
        <main class="my-2">
            <section class="card">
                <form>
                    <div class="card-body">
                        <div class="my-4">
                            <label class="form-label" for="id">NO.</label>
                            <input class="form-control" id="id" value="${ post.id }" readonly />
                        </div>
                        <div class="my-4">
                            <label class="form-label" for="title">제목</label> <%-- id=title의 label --%>
                            <input class="form-control" id="title" value="${ post.title }" readonly /> <%-- name = request parameter --%>
                        </div>
                        <div class="my-4">
                            <label class="form-label" for="content">내용</label>
                            <textarea class="form-control" id="content" readonly>${ post.content }</textarea>
                        </div>
                        <div class="my-4">
                            <label class="form-label" for="author">작성자 아이디</label>
                            <input class="form-control"id="author" value="${ post.author }" readonly />
                        </div>
                        <div class="my-4">
                            <label class="form-label" for="time">날짜</label>
                            <fmt:formatDate value="${ post.modifiedTime }" pattern="yyyy.MM.dd HH:mm" var="modifiedTime" />
                            <input class="form-control" id="time" value="${ modifiedTime }" readonly />
                        </div>
                    </div>
                </form>
                <div class="card-footer">
                    <c:url var="postModifyPage" value="/post/modify">
                        <c:param name="id" value="${ post.id }"></c:param>
                    </c:url>
                    <a href="${ postModifyPage }" class="btn form-control">수정</a>
                </div>
            </section> <!-- 포스트 상세 보기 카드 -->
        
            <section class="my-2 card">
                <div class="card-header text-bold">
                    <span>댓글</span>
                    <span id="replyCount">${ post.rcnt }</span>개 <!-- TODO: 실제 댓글 개수 -->
                    <button class="btn" id="btnToggleReply" data-toggle="toggle-off">보이기</button>
                </div>
                <div class="card-body collapse" id="replyToggleDiv">
                    <!-- 내 댓글 등록 -->
                    <div class="row my-2">
                        <label class="form-label" for="replyText">내 댓글</label>
                        <div class="col-11">
                            <textarea class="form-control" id="replyText"></textarea>
                            <input class="d-none" id="writer" value="admin" /> <!-- TODO: 로그인 사용자 아이디 -->
                        </div>
                        <div class="col-1">
                            <button class="form-control btn btn-outline-success" id="btnAddReply">등록</button>
                        </div>
                    </div>    
                    
                    <!-- 댓글 목록 보여줄 영역 -->
                    <div class="row my-3" id="replies">댓글 목록</div>
                </div>
            </section> <!-- 댓글 등록, 댓글 리스트 카드 -->
        </main>   
                
        
    
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" 
                crossorigin="anonymous">
        </script>
        
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
        
        <script src="../static/js/reply.js"></script>
	</div>
	</body>
</html>
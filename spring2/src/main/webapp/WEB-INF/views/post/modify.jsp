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


<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<script src="../static/summernote/lang/summernote-ko-KR.js"></script>
<script>
	$(document).ready(function() {
		$('#summernote').summernote({
			lang : 'ko-KR',              // default: 'en-US'
			height: 300,                 // set editor height
			minHeight: null,             // set minimum height of editor
			maxHeight: null,             // set maximum height of editor
			focus: true,                 // set focus to editable area after initializing summernote
			toolbar: [
			    // [groupName, [list of button]]
			    ['fontname', ['fontname']],
			    ['fontsize', ['fontsize']],
			    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
			    ['color', ['forecolor','color']],
			    ['table', ['table']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']],
			    ['insert',['picture','link','video']],
			    ['view', ['fullscreen', 'help']]
			  ],
			fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']
		});
	});
</script>
</head>
	<body>
	<div class="container-fluid">
		<header class="my-2 p-5 text-center text-bg-secondary">
            <h1>포스트 수정 페이지</h1>
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
                <li class="nav-item">
                    <c:url var="postDetailPage" value="/post/detail">
                        <c:param name="id" value="${ post.id }"></c:param>
                    </c:url>
                    <a class="nav-link text-body-emphasis"  href="${ postDetailPage }">상세 보기</a>
                </li>
            </ul>
        </nav>
        
        <main class="my-2">
        <div class="card">
            <form id="postModifyForm">
                <div class="card-body" id="modifyForm" >
                    <div class="my-4">
                        <label class="form-label" for="id">NO.</label>
                        <input class="form-control" id="id" name="id" value="${ post.id }" readonly />
                    </div>
                    <div class="my-4">
                        <label class="form-label" for="title">제목</label> <%-- id=title의 label --%>
                        <input class="form-control" id="title" name="title" value="${ post.title }" autofocus /> 
                    </div>
                    <div class="my-4">
                        <label class="form-label" for="content">내용</label>
                        <textarea class="form-control" id="summernote" name="content">${ post.content }</textarea>
                    </div>
                    <div class="my-4">
                        <label class="form-label" for="author">작성자 아이디</label>
                        <input class="form-control" id="author" name="author" value="${ post.author }" readonly />
                    </div>
                    <div class="my-4">
                        <label class="form-label" for="time">날짜</label>
                        <input class="form-control" id="time" name="modifiedTime" value="${ post.modifiedTime }" readonly />
                    </div>
                </div>
            </form>
            <div class="card-footer"> <%-- button을 form 안에 넣을 경우 form submit 기능이 기본값임. --%>
                <div class="d-flex justify-content-center">
                    <button id="btnDelete" class="btn btn-secondary mx-4">삭제</button>
                    <button id="btnUpdate" class="btn btn-secondary mx-4">업데이트</button>
                </div>
            </div>
        </div>
        </main>   
        
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" 
                crossorigin="anonymous">
        </script>
        
        <script src="../static/js/post-modify.js"></script>
        
	</div>
    
    
    
	</body>
</html>
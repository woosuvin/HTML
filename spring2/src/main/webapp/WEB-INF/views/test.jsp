<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
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
            
            <script src="/js/summernote/summernote-lite.js"></script>
        <script src="/js/summernote/lang/summernote-ko-KR.js"></script>
        <link rel="stylesheet" href="/css/summernote/summernote-lite.css">
        
	</head>
	<body>
	<div>

        <!-- form 안에 에디터를 사용하는 경우 (보통 이경우를 많이 사용하는듯)-->
        <form method="post">
            <textarea id="summernote" name="editordata"></textarea>
        </form>




        <!-- div에 에디터를 사용하는 경우 -->
        <div id="summernote">Hello Summernote</div>



        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" 
                crossorigin="anonymous">
        </script>
        
        <script>
        $(document).ready(function() {
            //여기 아래 부분
            $('#summernote').summernote({
                  height: 300,                 // 에디터 높이
                  minHeight: null,             // 최소 높이
                  maxHeight: null,             // 최대 높이
                  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
                  lang: "ko-KR",                    // 한글 설정
                  placeholder: '최대 2048자까지 쓸 수 있습니다'    //placeholder 설정
                  
            });
        });
        </script>
        
        
    </div>
	</body>
</html>
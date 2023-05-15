<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>POST</title>
	</head>
	<body>
        <header>
            <h1>로그인</h1>
        </header>
        
        <main>
            <form method="post">
                <div>
                    <input type="text" name="username" placeholder="ID" required autofocus />
                </div>
                <div>
                    <input type="password" name="password" placeholder="PASSWORD" required />
                </div>
                <div>
                    <input type="submit"  value="로그인" />
                </div>
            </form>
        </main>
        
	</body>
</html>

<!-- action 요청을 보내는 주소, 따로 지정하지 않으면 현재 브라우저에서 보여지는 주소 그래로 요청을 보냄 | method 요청을 보내는 방식 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<html lang="en">
  <head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" 
            rel="stylesheet" 
            integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" 
            crossorigin="anonymous"> <!-- bootstrap -->
  </head>
  
  <body>
  <header class="my-2 p-5 text-center text-bg-secondary">
            <h1>게시판</h1>
        </header>
		
        <nav class="navbar-expand-lg bg-body-tertiary text-center justify-content-center">
            <ul class="nav justify-content-center">
                <li class="nav-item">
                    <c:url var="postListPage" value="/post/list" />
                    <a class="nav-link text-body-emphasis"  href="${ postListPage }">포스트 목록</a>
                </li>
            </ul>
        </nav>
    
    
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" 
                crossorigin="anonymous">
        </script> <!-- bootstrap -->
            
  </body>
</html>


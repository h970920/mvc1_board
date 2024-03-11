<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*, java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    BoardDAO dao = new BoardDAO();
    List<BoardVO> ls = dao.selectAll();
    pageContext.setAttribute("ls", ls);
%> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 목록</title>
    
        <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: #fff;
            text-transform: uppercase;
        }
        tr:hover {
            background-color: #f2f2f2;
        }
        a {
            text-decoration: none;
            color: #007bff;
        }
        a:hover {
            text-decoration: underline;
        }
        .add-link {
            display: block;
            margin-top: 20px;
        }
        
        .button-container {
            text-align: center;
            margin-top: 50px;
        }
        button {
            margin: 0 10px;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #0056b3;
        }
        h2 {
        text-align: center;
        }
    </style>
</head>
<body>
    <h2>게시글 목록</h2>
    <table border="1">
        <tr>
            <td>번호</td>
            <td>제목</td>
            <td>작성자</td>
            <td>등록일</td>
            <td>조회수</td>
        </tr>
        <c:forEach var="board" items="${ls}">
            <tr>
                <td>${board.num}</td>
                <td><a href="${pageContext.request.contextPath}/board/boardDetail.jsp?num=${board.num}">${board.title}</a></td>
                <td>${board.writer}</td>
                <td>${board.regdate}</td>
                <td>${board.cnt}</td>
            </tr>
        </c:forEach> 
    </table>  
      <div class="button-container">
    <a href="<c:url value="/board/registForm.jsp"/>"><button>글등록</button></a>	  
    </div>	
</body>
</html>

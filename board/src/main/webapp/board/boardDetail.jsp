<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="dao" class="board.BoardDAO"/>

<% 
    int num = Integer.parseInt(request.getParameter("num"));
    BoardVO vo = dao.selectOne(num);
    pageContext.setAttribute("vo", vo);    
%>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글내용</title>
    
     <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
   			text-align:center;
            align-items: center;
            justify-content: center;
        }
        table {
            width: 50%;
            margin: 50px auto;
            border-collapse: collapse;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            
        }
        th, td {
            padding: 10px;
            border-bottom: 1px solid #ccc;
            text-align: left;
  
        }
        th {
            background-color: #f2f2f2;
        }
        .button-container {
            text-align: center;
            margin-top: 20px;
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
    </style>
</head>
<body>
    <h3>글 정보</h3>
    <p>번호: ${vo.num}</p>
    <p>제목: ${vo.title}</p>
    <p>작성자: ${vo.writer}</p>
    <p>내용: ${vo.content}</p>
    <p>등록일자: ${vo.regdate}</p>
    <p>조회수: ${vo.cnt}</p>
    <a href="<c:url value="/board/editForm.jsp?num=${vo.num}"/>"><button>수정</button></a>
    <a href="<c:url value="/board/deleteForm.jsp?num=${vo.num}"/>"><button>삭제</button></a>
    <a href="<c:url value="/board/list.jsp"/>"><button>게시글 목록으로 돌아가기</button></a>
</body>
</html>

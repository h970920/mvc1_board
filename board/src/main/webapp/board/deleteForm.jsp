<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 확인</title>
</head>
<body>
<%
    String numParameter = request.getParameter("num");
    int num = 0; // 기본값 설정
    
    if (numParameter != null && !numParameter.isEmpty()) {
        num = Integer.parseInt(numParameter);
    }
%>
<form action="delete.jsp">
<input type="hidden" value="<%=num%>" name="num">
삭제 하시겠습니까? <input type="submit" value="예">
</form>
</body>
</html>

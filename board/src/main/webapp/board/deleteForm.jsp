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
<form id="deleteForm" action="delete.jsp" method="post">
    <input type="hidden" value="<%=num%>" name="num">
    삭제 하시겠습니까? 
    <button type="button" onclick="confirmDelete()">예</button>
    <button type="button" onclick="cancelDelete()">아니오</button>
</form>

<script>
    function confirmDelete() {
        if (confirm("삭제 하시겠습니까?")) {
            document.getElementById("deleteForm").submit();
        }
    }

    function cancelDelete() {
        alert("삭제를 취소했습니다.");
        // list.jsp로 이동
        window.location.href = "list.jsp";
    }
</script>
</body>
</html>

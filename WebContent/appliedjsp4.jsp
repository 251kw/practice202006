<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.User"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>JSPを使いこなす</title>
</head>
	<body>
		<%
			User user = new User("yamada", "pass1", "山田 太郎");
			// リクエストオブジェクトに値を保存
			request.setAttribute("user", user);
		%>
		<jsp:forward page="appliedjsp5.jsp">
		<jsp:param name="value1" value="100"/>
		<jsp:param name="value2" value="200"/>
		</jsp:forward>
	</body>
</html>
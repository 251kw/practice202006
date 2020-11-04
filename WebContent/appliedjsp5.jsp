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
		<h3>JSP 基本的なタグの利用</h3>
		<%
			// 送信情報の取得
			String message1 = request.getParameter("value1");
			String message2 = request.getParameter("value2");

			// リクエストオブジェクトから値を取得
			User u = (User)request.getAttribute("User");

			int number1 = Integer.parseInt(message1);
			int number2 = Integer.parseInt(message2);
		%>

		<%= message1 %> + <%= message2 %> = <%= (number1+number2) %><br>
			ユーザー名 : <%= u.getUserName() %>

		<hr>
		<h3>式言語の利用</h3>
		${param.value1} + ${param.value2} = ${param.value1 + param.value2}<br>
		ユーザー名 : ${user.userName}
	</body>
</html>
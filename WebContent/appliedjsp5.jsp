<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.User"%>
<%@ page import="bean.Animal"%>
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
			String message3 = request.getParameter("value3");

			// リクエストオブジェクトから値を取得
			User u = (User)request.getAttribute("key");		//Object型→User型に
			User h = (User)request.getAttribute("haya");
			Animal a =(Animal)request.getAttribute("cry");

			int number1 = Integer.parseInt(message1);
			int number2 = Integer.parseInt(message2);
			int number3 = Integer.parseInt(message3);
		%>

		<%= message1 %> + <%= message2 %> * <%= message3 %> = <%= (number1 + number2 / number3 > number1) %><br>
			ユーザー名 : <%= u.getUserName() %>
			パスワード : <%= h.getPassword() %><br>
			鳥 : <%= a.getBird() %><br>

		<hr>
		<h3>式言語の利用</h3>
		${param.value1} + ${param.value2} / ${param.value3} = ${param.value1 + param.value2 * param.value3 > param.value2}<br>
		ユーザー名 : ${key.userName}
		パスワード : ${haya.password}<br>
		鳥 : ${cry.bird}
	</body>
</html>

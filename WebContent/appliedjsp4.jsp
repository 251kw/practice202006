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
		<%
			User abc = new User("yamada", "pass1", "山田 太郎");
			// リクエストオブジェクトに値を保存		getparameter:ユーザーが入力した情報、get方式のform
			request.setAttribute("key", abc);		//getAttribute setAttribute 開発側が設定した情報を使用するときに使用	12行目を持ってくる	map方式でとれる

			User pass = new User("hayashi", "pass2", "林");
			request.setAttribute("haya", pass);

			Animal ani = new Animal("ワン", "ニャー","くるっぽー");
			request.setAttribute("cry",ani);
		%>

		<jsp:forward page="appliedjsp5.jsp">
			<jsp:param name="value1" value="100"/>
			<jsp:param name="value2" value="200"/>
			<jsp:param name="value3" value="300"/>
		</jsp:forward>
	</body>
</html>
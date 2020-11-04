<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>JSPを使いこなす</title>
</head>
	<body>
		<h3>インクルードディレクティブ</h3>
		<%@ include file="inc1.html" %>

		<h3>インクルードアクション</h3>
		<jsp:include page="inc1.html"/>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登録完了</title>
	<link rel="stylesheet" href="./css/skyblue.css">
	<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
	<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-30">
		<div class="container padding-y-5">
			<h2 class="text-center">Shouter&nbsp;<span class="icon-speaker pe-2x pe-va"></span></h2>
		</div>
	</div>
	<br>
	<h5 class="text-center">登録が完了しました</h5>
<%
	String newloginId = request.getParameter("newloginId");
	String newpassword = request.getParameter("newpassword");
	String newuserName = request.getParameter("newuserName");
	String newicon = request.getParameter("newicon");
	String newprofile = request.getParameter("newprofile");
%>
	<form action="./ReturnLogin" method="post">
		<table style="width: 400px" class="table container padding-y-5">
			<tr>
				<th class="color-main">ログインID:</th>
				<td>${requestScope.newloginId}</td>
			</tr>
			<tr>
				<th class="color-main">パスワード:</th>
				<td>${requestScope.newpassword}</td>
			</tr>
			<tr>
				<th class="color-main">ユーザー名:</th>
				<td>${requestScope.newuserName}</td>
			</tr>
			<tr>
				<th class="color-main">アイコン:</th>
				<td class="text-center"><span class="${requestScope.newicon} pe-2x pe-va"></span></td>
			</tr>
			<tr>
				<th class="color-main">プロフィールに追加する一言:</th>
				<td>${requestScope.newprofile}</td>
			</tr>
			<tr>
				<td colspan="2" class="text-right"><input class="btn" type="submit" value="ログイン画面へ" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
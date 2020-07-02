<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新規登録確認</title>
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
<%
	String newloginId = request.getParameter("newloginId");
	String newpassword = request.getParameter("newpassword");
	String newuserName = request.getParameter("newuserName");
	String newicon = request.getParameter("newicon");
	String newprofile = request.getParameter("newprofile");
%>
	<form action="./registDB" method="post">
		<table style="width: 400px" class="table container padding-y-5">
			<tr>
				<th class="color-main">ログインID:</th>
				<td>${requestScope.newloginId}</td>
				<td><input type="hidden" name="newloginId" value="${requestScope.newloginId}"></td>
			</tr>
			<tr>
				<th class="color-main">パスワード:</th>
				<td>${requestScope.newpassword}</td>
				<td><input type="hidden" name="newpassword" value="${requestScope.newpassword}"></td>
			</tr>
			<tr>
				<th class="color-main">ユーザー名:</th>
				<td>${requestScope.newuserName}</td>
				<td><input type="hidden" name="newuserName" value="${requestScope.newuserName}"></td>
			</tr>
			<tr>
				<th class="color-main">アイコン:</th>
				<td class="text-center"><span class="${requestScope.newicon} pe-2x pe-va"></span></td>
				<td><input type="hidden" name="newicon" value="${requestScope.newicon}"></td>
			</tr>
			<tr>
				<th class="color-main">プロフィールに追加する一言:</th>
				<td>${requestScope.newprofile}</td>
				<td><input type="hidden" name="newprofile" value="${requestScope.newprofile}"></td>
			</tr>
			<tr>
				<td colspan="2" class="text-right"><input class="btn"
					type="submit" value="この内容で登録" /></td>
			</tr>
		</table>
	</form>
	<form action="./TurnInput" method="post">
		<table style="width: 400px" class="table container padding-y-5">
			<tr>
				<td><input type="hidden" name="newloginId" value="${requestScope.newloginId}"></td>
				<td><input type="hidden" name="newpassword" value=""></td>
				<td><input type="hidden" name="newuserName" value="${requestScope.newuserName}"></td>
				<td><input type="hidden" name="newicon" value="${requestScope.newicon}"></td>
				<td><input type="hidden" name="newprofile" value="${requestScope.newprofile}"></td>
			</tr>
			<tr>
				<td colspan="2" class="text-right">
					<input class="btn" type="submit" value="入力画面に戻る" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
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
String newloginId = (String)session.getAttribute("newloginId");
String newpassword = (String)session.getAttribute("newpassword");
String newuserName = (String)session.getAttribute("newuserName");
String newicon = (String)session.getAttribute("newicon");
String newprofile = (String)session.getAttribute("newprofile");
%>
<form action="./register2" method="post">
		<table style="width: 400px" class="table container padding-y-5">
			<tr>
				<th class="color-main">ログインID:</th>
				<td><%=newloginId%></td>
			</tr>
			<tr>
				<th class="color-main">パスワード:</th>
				<td><%=newpassword%></td>
			</tr>
			<tr>
				<th class="color-main">ユーザー名:</th>
				<td><%=newuserName%></td>
			</tr>
			<tr>
				<th class="color-main">アイコン:</th>
				<td class="text-center"><span class="<%=newicon %> pe-2x pe-va"></span></td>
			</tr>
			<tr>
				<th class="color-main">プロフィールに追加する一言:</th>
				<td><%=newprofile%></td>
			</tr>
			<tr>
				<td colspan="2" class="text-right"><input class="btn"
					type="submit" value="この内容で登録" /></td>
			</tr>
		</table>
	</form>
	<form action="UserAddInput.jsp" method="post">
<table style="width: 400px" class="table container padding-y-5">
	<tr>
		<td colspan="2" class="text-right">
			<input class="btn" type="submit" value="入力画面に戻る" />
		</td>
	</tr>
</table>
</form>
</body>
</html>
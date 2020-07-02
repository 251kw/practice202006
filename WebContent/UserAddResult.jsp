<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>登録完了</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<%
	String loginId = (String)request.getAttribute("loginId");
	String password = (String)request.getAttribute("password");
	String userName = (String)request.getAttribute("userName");
	String icon = (String)request.getAttribute("icon");
	String profile = (String)request.getAttribute("profile");
	%>
	<div class="bg-success padding-y-5">
		<div class="padding-y-5 text-center">
			<strong>登録しました</strong>
		</div>
	</div>
	<div style="width: 40%" class="container padding-y-5">
		<form action='index.jsp' method='post'>
			<table border='1' class='table'>
				<tr>
					<th>ログインID</th>
					<td><%= loginId %></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><%= password %></td>
				</tr>
				<tr>
					<th>名前</th>
					<td><%= userName %></td>
				</tr>
				<tr>
					<th>アイコン</th>
					<td><span class="icon-<%= icon %> pe-2x pe-va"></span></td>
				</tr>
				<tr>
					<th>プロフィール</th>
					<td><%= profile %></td>
				</tr>
				<tr>
					<td colspan='2' style='text-align: right'><input type='submit'
						value='戻る'></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
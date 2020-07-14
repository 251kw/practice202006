<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>登録内容確認</title>
	<link rel="stylesheet" href="./css/skyblue.css">
	<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
	<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<jsp:useBean id="user" scope="request" type="dto.UserDTO" />
	<div class="bg-success padding-y-5">
		<div class="padding-y-5 text-center">
			<strong>登録内容はこれで大丈夫？</strong>
		</div>
	</div>
	<div style="width: 40%" class="container padding-y-5">
		<form action='rst2' method='post'>
			<table border='1' class="table">
				<tr>
					<th>ログインID</th>
					<td>${user.loginId}</td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td>${user.password}</td>
				</tr>
				<tr>
					<th>名前</th>
					<td>${user.userName}</td>
				</tr>
				<tr>
					<th>アイコン</th>
					<td><span class="icon-${user.icon} pe-2x pe-va"></span></td>
				</tr>
				<tr>
					<th>プロフィール</th>
					<td>${user.profile}</td>
				</tr>
				<tr>
					<td colspan='2' style='text-align: right'>
						<input type='submit' value='OK'>
						<input type="hidden" name="loginId" value="${user.loginId}">
						<input type="hidden" name="password" value="${user.password}">
						<input type="hidden" name="userName" value="${user.userName}">
						<input type="hidden" name="icon" value="${user.icon}">
						<input type="hidden" name="profile" value="${user.profile}">
					</td>
				</tr>
			</table>
		</form>
		<form action='add_input.jsp' method='post'>
			<table border='1' class='table'>
				<tr>
					<td colspan='2' style='text-align: right'>
						<input type='submit' value='戻る'>
						<input type="hidden" name="loginId" value="${user.loginId}">
						<input type="hidden" name="password" value="${user.password}">
						<input type="hidden" name="userName" value="${user.userName}">
						<input type="hidden" name="icon" value="${user.icon}">
						<input type="hidden" name="profile" value="${user.profile}">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
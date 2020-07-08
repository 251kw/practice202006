<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang ="ja">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Shouter-確認-</title>
		<link rel="stylesheet" href="./css/skyblue.css">
		<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
		<link rel="stylesheet" href="./css/helper.css">
	</head>
	<body>

		<div class="bg-warning padding-y-5">
			<div class="padding-y-5 text-center">
				<strong>Shouter</strong>
				<span class="icon-speaker pe-2x pe-va"></span>
			</div>
		</div>

		<div class="padding-y-5 text-center" >ログインユーザ情報</div>

		<jsp:useBean id="user" scope="request" type="dto.UserDTO" />
		<div class="padding-y-5">
			<div style="width: 65%" class="container padding-y-5">
					<table class ="table table-bordered">
						<tr>
							<td class="color-main text-left">ログインID</td>
							<td colspan="2">${user.loginId}</td>
						</tr>
						<tr>
							<td class="color-main text-left">パスワード</td>
							<td class="color-main text-left">セキュリティのため表示できません</td>
						</tr>
						<tr>
							<td class="color-main text-left">ユーザー名</td>
							<td width="256">${user.userName}</td>
						</tr>
						<tr>
							<td class="color-main text-left">プロフィール</td>
							<td colspan="2">${user.profile}</td>
						</tr>
						<tr>
							<td class="color-main text-left">アイコン</td>
							<td><span class="${user.icon} pe-2x pe-va"></span></td>
						</tr>
					</table>
			</div>
		</div>

		<div align="right">
			<form action="./InsertUser" method="post">
				<input type="hidden" name="loginID" value="${user.loginId}">
				<input type="hidden" name="password" value="${user.password}">
				<input type="hidden" name="userName" value="${user.userName}">
				<input type="hidden" name="profile" value="${user.profile}">
				<input type="hidden" name="icon" value="${user.icon}">
				<div align="center">
					<input class="btn" type="submit" style="height:50px" value="登録確定" />
				</div>
			</form>
		</div>


		<div align="right">
			<form action="./fails" method="post">
				<input type="hidden" name="loginID" value="${user.loginId}">
				<input type="hidden" name="userName" value="${user.userName}">
				<input type="hidden" name="profile" value="${user.profile}">
				<input type="hidden" name="icon" value="${user.icon}">
				<div align="center">
					<input class="btn btn-grey btn-sm" type="submit" style="height:50px" value="戻る" />
				</div>
			</form>
		</div>

	</body>
</html>
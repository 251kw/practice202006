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

		<div class="padding-y-5 text-center" >削除ユーザ情報</div>

		<jsp:useBean id="deleteUser" scope="request" type="java.util.ArrayList<dto.UserDTO>" />
		<c:forEach var="deleteUser" items="${deleteUser}">
			<div class="padding-y-5">
				<div style="width: 65%" class="container padding-y-5">
					<table class="table table-bordered">
						<tr>
							<td class="color-main text-left">ログインID</td>
							<td colspan="2">${deleteUser.loginId}</td>
						</tr>
						<tr>
							<td class="color-main text-left">パスワード</td>
							<td class="color-main text-left">セキュリティのため表示できません</td>
						</tr>
						<tr>
							<td class="color-main text-left">ユーザー名</td>
							<td width="256">${deleteUser.userName}</td>
						</tr>
						<tr>
							<td class="color-main text-left">プロフィール</td>
							<td colspan="2">${deleteUser.profile}</td>
						</tr>
						<tr>
							<td class="color-main text-left">アイコン</td>
							<td><span class="${deleteUser.icon} pe-2x pe-va"></span></td>
						</tr>
					</table>
				</div>
			</div>
		</c:forEach>

		<div class="color-error text-left">
			<c:if test="${requestScope.alert != null && requestScope.alert != ''}">
				<c:out value="${requestScope.alert}" />
			</c:if>
		</div>

		<div align="center">
			<form action="./multiDeleteUserResult" method="post">
				<c:forEach var="deleteUser" items="${deleteUser}">
					<input type="hidden" name="loginId" value="${deleteUser.loginId}">
				</c:forEach>
				<input class="btn" type="submit" style="height:50px" value="削除確定" />
			</form>
		</div>

		<div align="center">
			<form action="./returnSearchResult" method="post">
			<c:forEach var="checkUsers" items="${checkUsers}">
					<input type="hidden" name="checkUsers" value="${checkUsers}">
			</c:forEach>
				<input class="btn btn-grey btn-sm" type="submit" style="height:50px" value="戻る" />
			</form>
		</div>

	</body>
</html>
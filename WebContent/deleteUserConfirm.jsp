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

<div class="padding-y-5 text-center" >選択ユーザー情報</div>

<%-- リクエストスコープにある　UserDTO型のオブジェクトを参照 --%>
<jsp:useBean id="deleteUser" scope="request" type="dto.UserDTO" />
<div class="padding-y-5">
	<div style="width: 65%" class="container padding-y-5">
			<table class ="table table-bordered">
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

<%-- リクエストスコープにalertがあれば --%>
		<c:if test="${requestScope.alert != null && requestScope.alert != ''}">
		<table>
		<tr>
			<%-- リクエストスコープのalertの値を出力 --%>
			<td colspan="2" class="color-error text-left">
				<c:out value="${requestScope.alert}" />
			</td>
		</tr>
		</table>
		</c:if>

<div align="right">
<form action="./deleteUserResult" method="post">
		<input type="hidden" name="loginId" value="${deleteUser.loginId}">
	<input class="btn" type="submit" style="height:50px" value="削除確定" />
</form>
</div>


<div align="right">
<form action="./returnSearchResult" method="post">
	<input class="btn btn-grey btn-sm" type="submit" style="height:50px" value="戻る" />
</form>
</div>


</body>
</html>
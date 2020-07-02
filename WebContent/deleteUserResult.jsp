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

<div class="padding-y-5 text-center" >このユーザーを削除しました</div>

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



<jsp:useBean id="user" scope="session" type="dto.UserDTO" />
<c:if test="${user.loginId != deleteUser.loginId}">
	<div align="right">
	<form action="./returnSearchResult" method="post">
		<input class="btn btn-grey btn-sm" type="submit" style="height:50px" value="検索結果に戻る" />
	</form>
	</div>
</c:if>

<c:if test="${user.loginId == deleteUser.loginId}">
	<div align="right">
	<form action="./logout" method="post">
		<input class="btn btn-grey btn-sm" type="submit" style="height:50px" value="INDEXにもどる" />
	</form>
	</div>
</c:if>


</body>
</html>
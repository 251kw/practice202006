<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang ="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter-検索-</title>
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

<h5 class="padding-y-5 text-center">ユーザー検索</h5>
<jsp:useBean id="sUser" scope="session" type="bean.SearchUserBean" />
<form action="./search" method="post">
	<table style="width: 70%" class="table container">
		<tr>
			<td class="color-main text-left">ログインID</td>
			<td class="text-left">
				<input class="form-control" type="text" name="loginId" value="${sUser.loginId}" size="20" maxlength="20" />
			</td>
		</tr>
		<tr>
			<td class="color-main text-left">ユーザー名</td>
			<td class="text-left"><input class="form-control" type="text" name="userName" value="${sUser.userName}" size="20" maxlength="15" /></td>
		</tr>
		<tr>
			<td class="color-main text-left">アイコン</td>
			<td>
				<label><input type="checkbox" name="icon_car" value="icon-car" <c:if test="${sUser.car == 'icon-car'}">checked</c:if> ><span></span></label>
				<span class="icon-car pe-2x pe-va"></span>
				<label><input type="checkbox" name="icon_paperclip" value="icon-paperclip" <c:if test="${sUser.clip == 'icon-paperclip'}">checked</c:if> ><span></span></label>
				<span class="icon-paperclip pe-2x pe-va"></span>
				<label><input type="checkbox" name="icon_radio" value="icon-radio" <c:if test="${sUser.radio == 'icon-radio'}">checked</c:if> ><span></span></label>
				<span class="icon-radio pe-2x pe-va"></span>
			</td>
		</tr>
		<tr>
			<td class="color-main text-left" height="100%">プロフィール</td>
			<td><textarea name="profile" rows="4" cols="40" >${sUser.profile}</textarea></td>

		</tr>
	</table>

	<div align="center">
		<input class="btn" type="submit" value="検索" />
	</div>

</form>

<form action="./returnTop" method="post">
	<div align="center">
		<input class="btn btn-grey" type="submit" value="戻る" />
	</div>
</form>


</body>
</html>
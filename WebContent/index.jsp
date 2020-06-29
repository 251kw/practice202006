<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang ="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter-ログイン-</title>
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

<h5 class="padding-y-5 text-center">ログインIDとパスワードを入力してください</h5>

<form action="./login" method="post">
	<table style="width: 40%" class="table container">
		<tr>
			<%-- ログインID入力欄はloginId --%>
			<td class="color-main text-left">ログインID</td>
			<td class="text-left"><input class="form-control" type="text" name="loginID" value="" size="20" /></td>
		</tr>
		<tr>
			<%-- パスワード入力欄の名前はpassword --%>
			<td class="color-main text-left">パスワード</td>
			<td class="text-left"><input class="form-control" type="password" name="password" value="" size="20" /></td>
		</tr>
		<tr>
			<td colspan="2" class="text-right"><input class="btn" type="submit" value="ログイン" /></td>
		</tr>
		<%-- リクエストスコープにalertがあれば --%>
		<c:if test="${requestScope.alert != null && requestScope.alert != ''}">
		<tr>
			<%-- リクエストスコープのalertの値を出力 --%>
			<td colspan="2" class="color-error text-left">
				<c:out value="${requestScope.alert}" />
			</td>
		</tr>
		</c:if>

	</table>
</form>

<div align="right">
<form action="./newuser" method="get">
	<input class="btn btn-sm" type="submit" value="新規ユーザー登録" />
</form>
</div>

</body>
</html>
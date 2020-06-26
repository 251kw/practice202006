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

<div class="padding-y-5 text-center" >登録に成功しました</div>

<%-- リクエストスコープにある　UserDTO型のオブジェクトを参照 --%>
<jsp:useBean id="user" scope="session" type="dto.UserDTO" />
<div class="padding-y-5">
	<div style="width: 65%" class="container padding-y-5">
		<form action="./logout" method="post">
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
		</form>
	</div>
</div>

<%-- action属性にサーブレットを指定 --%>
<form action="./sucs" method="get">
	<table style="width: 40%" class="container padding-y-5">
		<tr>
			<td colspan="2" class="text-right"><input class="btn" type="submit" value="INDEXに戻る" /></td>
		</tr>
	</table>
</form>

</body>
</html>
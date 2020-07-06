<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang ="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter-検索結果-</title>
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

<div class="padding-y-5 text-center" >検索結果</div>

<jsp:useBean id="searchUser" scope="request" type="java.util.ArrayList<dto.UserDTO>" />
<div class="padding-y-5">
	<div style="width: 75%" class="container padding-y-5">
	<form action="./multiDeleteUSerComfirmServlet" id="checks" method="post"></form>
	<table class ="table table-striped table-bordered table-hover">
		<tr>
			<td><input type="checkbox" name="all" /></td>
			<td class="color-main text-left">ログインID</td>
			<td class="color-main text-left">ユーザー名</td>
			<td class="color-main text-left">プロフィール</td>
			<td class="color-main text-left">アイコン</td>
			<td class="color-main text-left">更新</td>
			<td class="color-main text-left">削除</td>
		</tr>
		<c:forEach var="search" items="${searchUser}">
				<tr>
					<td><input type="checkbox" name="checked" form="checks" value="${search.loginId}" /></td>
					<td>${search.loginId}</td>
					<td>${search.userName}</td>
					<td>${search.profile}</td>
					<td><span class="${search.icon} pe-2x pe-va"></span></td>
					<td>
						<form action="./updateUser" method="post">
							<input type="hidden" name="loginId" value="${search.loginId}">
							<input class="btn btn-sm" type="submit" value="更 新" />
						</form>
					</td>
					<td>
						<form action="./deleteUser" method="post">
							<input type="hidden" name="loginId" value="${search.loginId}">
							<input class="btn btn-sm" type="submit" value="削 除" />
						</form>
					</td>
				</tr>
		 </c:forEach>
	</table>
			<input class="btn " type="submit" form="checks" value="選択したユーザーを削除" />
		</div>
</div>

<form action="./searchBack" method="post">
	<table style="width: 40%" class="container padding-y-5">
		<tr>
			<td colspan="2" class="text-right"><input class="btn" type="submit" value="検索条件に戻る" /></td>
		</tr>
	</table>
</form>

<form action="./returnTop" method="post" id="check">
	<table style="width: 40%" class="container padding-y-5">
		<tr>
			<td colspan="2" class="text-right"><input class="btn btn-grey" type="submit" value="TOPに戻る" /></td>
		</tr>
	</table>
</form>

</body>
</html>
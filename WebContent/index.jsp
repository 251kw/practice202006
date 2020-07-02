<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>shouter-ログイン-</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="padding-y-5 text-center">
			<h1>
				<strong> Shouter</strong> <span class="icon-speaker pe-1x pe-va"></span>
			</h1>
		</div>
	</div>
	<%-- action 属性にサーブレットを指定 --%>
	<form action="./login" method="post">

		<table style="width: 450px" class="table">
			<tr>
				<td><nobr>ログインIDとパスワードを入力してください</nobr></td>
			</tr>

			<tr>
				<%-- ログインID入力欄の名前はloginId --%>
				<td class="color-main text-left">ログインID</td>
				<td class="text-right"><input class="form-control" type="text"
					name="loginId" value="" /></td>
			</tr>
			<tr>
				<%-- パスワード入力欄の名前はpassword --%>
				<td class="color-main text-left">パスワード</td>
				<td class="text-right"><input class="form-control"
					type="password" name="password" value="" /></td>
			</tr>

		</table>
		<table style="width: 450px" class="table">
			<tr>
				<!--  <td><input type="submit" value="新規登録" class="btn"></td> -->
				<td><input type="button"
					onclick="location.href='./userAddInput.jsp'" value="新規登録はこちら"
					class="btn"></td>
				<td colspan="2" class="text-right"><input type="submit"
					value="ログイン" class="btn"></td>
			</tr>
			<%-- リクエストスコープにalertがあれば --%>
			<c:if
				test="${requestScope.alert != null && requestScope.alert != ''}">
				<tr>
					<%-- リクエストスコープのalertの値を出力 --%>
					<td colspan="2" class="color-error text-center"><c:out
							value="${requestScope.alert}" /></td>
				</tr>
			</c:if>
		</table>

	</form>


	<!--
<form action="./nu" method="post">
<table style="width: 400px" class="table">
<tr>
	<td><input type="submit" value="新規登録" class="btn"></td>
</tr>
</table>
</form>
 -->
	<!--  <input type="button" onclick="location.href='./add.jsp'" value="新規登録はこちら"> -->
</body>
</html>
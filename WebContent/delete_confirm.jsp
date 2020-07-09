<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>削除内容確認</title>
	<link rel="stylesheet" href="./css/skyblue.css">
	<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
	<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="padding-y-5 text-center">
			<strong>本当に削除しますか？</strong>
		</div>
	</div>
	<div style="width: 40%" class="container padding-y-5">
		<form action='./udc' method='post'>
			<table border='1' class="table">
				<%-- リクエストスコープのalertの値を出力 --%>
				<c:if test="${requestScope.alert != null && requestScope.alert != ' '}">
					<tr>
						<td colspan="2" class="color-error text-center">
							<c:out value="${requestScope.alert}" />
						</td>
					</tr>
				</c:if>
				<tr>
					<th>ログインID</th>
					<td>${duser[0]}</td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td>${duser[1]}</td>
				</tr>
				<tr>
					<th>名前</th>
					<td>${duser[2]}</td>
				</tr>
				<tr>
					<th>アイコン</th>
					<td><span class="${duser[3]} pe-2x pe-va"></span></td>
				</tr>
				<tr>
					<th>プロフィール</th>
					<td>${duser[4]}</td>
				</tr>
				<tr>
					<td colspan='2' style='text-align: right'>
						<input type='submit' value='OK'>
						<input type="hidden" name="loginId" value="${duser[0]}">
						<input type="hidden" name="password" value="${duser[1]}">
						<input type="hidden" name="userName" value="${duser[2]}">
						<input type="hidden" name="icon" value="${duser[3]}">
						<input type="hidden" name="profile" value="${duser[4]}">
					</td>
				</tr>
			</table>
		</form>
		<form action='./udr' method='post'>
			<table border='1' class='table'>
				<tr>
					<td colspan='2' style='text-align: right'>
						<input type='submit' value='戻る'>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
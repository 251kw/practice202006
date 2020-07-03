<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>新規登録</title>
		<link rel="stylesheet" href="./css/skyblue.css">
		<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
		<link rel="stylesheet" href="./css/helper.css">
	</head>
	<body>
		<div class="bg-success padding-y-30">
			<div class="container padding-y-5">
				<h2 class="text-center">Shouter&nbsp;<span class="icon-speaker pe-2x pe-va"></span></h2>
			</div>
		</div>
		<br>
		<h4 class="text-center">以下のユーザーを削除しますか？</h4>
		<br>
		<form action="./UserDelete" method="post">
			<table style="width: 400px" class="table table-borderd container padding-y-5">
				<%-- ログアウト警告 --%>
				<c:if test="${requestScope.logoutalert != null && requestScope.logoutalert != ''}">
					<tr>
						<td colspan="2" class="color-error text-center"><c:out value="${requestScope.logoutalert}" /></td>
					</tr>
				</c:if>
				<tr>
				<tr>
					<th class="color-main text-center">ログインID:</th>
					<td class="text-center">${requestScope.user.loginId}</td>
				</tr>
				<tr>
					<th class="color-main text-center">パスワード:</th>
					<td class="text-center">${requestScope.user.password}</td>
				</tr>
				<tr>
					<th class="color-main text-center">ユーザー名:</th>
					<td class="text-center">${requestScope.user.userName}</td>
				</tr>
				<tr>
					<th class="color-main text-center">アイコン:</th>
					<td class="text-center"><span class="${requestScope.user.icon} pe-2x pe-va"></span></td>
				</tr>
				<tr>
					<th class="color-main text-center">プロフィール:</th>
					<td class="text-center">${requestScope.user.profile}</td>
				</tr>
				<tr>
					<td colspan="2" class="text-right">
						<input class="btn" type="submit" value="削除" />
						<input type="hidden" name="dloginId" value="${requestScope.user.loginId}">
						<input type="hidden" name="sloginId" value="${requestScope.sloginId}">
						<input type="hidden" name="suserName" value="${requestScope.suserName}">
						<input type="hidden" name="sicon" value="${requestScope.sicon}">
						<input type="hidden" name="sprofile" value="${requestScope.sprofile}">
						<input type="hidden" name="logoutalert" value="${requestScope.logoutalert}">
					</td>
				</tr>
			</table>
		</form>
		<form action="./UserSearchSqueeze" method="post">
			<table style="width: 400px" class="table table-borderd container padding-y-5">
				<tr>
					<td>
						<input type="hidden" name="sloginId" value="${requestScope.sloginId}">
						<input type="hidden" name="suserName" value="${requestScope.suserName}">
						<input type="hidden" name="sicon" value="${requestScope.sicon}">
						<input type="hidden" name="sprofile" value="${requestScope.sprofile}">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-right">
						<input class="btn" type="submit" value="検索結果に戻る" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
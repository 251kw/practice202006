<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー検索条件</title>
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
		<h5 class="text-center">編集したい内容を入力または選択してください</h5>
	<br>
	<form action="./EditUserCheckChar" method="post">
		<table style="width: 400px" class="table container padding-y-5">
			<%-- 全入力チェック --%>
			<c:if test="${requestScope.alertblank != null && requestScope.alertblank != ''}">
				<tr>
					<td colspan="2" class="color-error text-left"><c:out value="${requestScope.alertblank}" /></td>
				</tr>
			</c:if>
			<tr>
				<td class="color-main text-center">ログインID</td>
				<td class="text-center"><input class="form-control" type="text" name="eloginId" value="${sessionScope.user.loginId}" size="20" /></td>
			</tr>
			<%-- ID被りチェック --%>
			<c:if test="${requestScope.alertsame != null && requestScope.alertsame != ''}">
				<tr>
					<td colspan="2" class="color-error text-left"><c:out value="${requestScope.alertsame}" /></td>
				</tr>
			</c:if>
			<%-- ID文字数or半角英数チェック --%>
			<c:if test="${requestScope.alertid != null && requestScope.alertid != ''}">
				<tr>
					<td colspan="2" class="color-error text-left"><c:out value="${requestScope.alertid}" /></td>
				</tr>
			</c:if>
			<tr>
				<td class="color-main text-center">パスワード</td>
				<td class="text-center"><input class="form-control" type="password" name="epassword" value="${sessionScope.user.password}" size="20" /></td>
			</tr>
			<%-- パスワード文字数チェック --%>
			<c:if test="${requestScope.alertpass != null && requestScope.alertpass != ''}">
				<tr>
					<td colspan="2" class="color-error text-left"><c:out value="${requestScope.alertpass}" /></td>
				</tr>
			</c:if>
			<tr>
				<td class="color-main text-center">ユーザー名</td>
				<td class="text-center"><input class="form-control" type="text" name="euserName" value="${sessionScope.user.userName}" size="20" /></td>
			</tr>
			<tr>
				<td class="color-main text-center">アイコン</td>
				<td>
					<select name="eicon" class="form-control">
						<c:choose>
							<c:when test="${sessionScope.user.icon==''}">
								<option value="all" selected>全選択</option>
								<option value="icon-user">男性</option>
								<option value="icon-user-female">女性</option>
							</c:when>
							<c:when test="${sessionScope.user.icon=='icon-user'}">
								<option value="all">全選択</option>
								<option value="icon-user" selected>男性</option>
								<option value="icon-user-female">女性</option>
							</c:when>
							<c:when test="${sessionScope.user.icon=='icon-user-female'}">
								<option value="all">全選択</option>
								<option value="icon-user">男性</option>
								<option value="icon-user-female" selected>女性</option>
							</c:when>
						</c:choose>
					</select>
				</td>
			</tr>
			<tr>
				<td class="color-main text-center">プロフィール</td>
				<td class="text-center"><input class="form-control" type="text" name="eprofile" value="${sessionScope.user.profile}" size="20" /></td>
			</tr>
			<tr>
				<td colspan="2" class="text-right"><input class="btn" type="submit" value="この内容で変更する" />
					<input type="hidden" name="sloginId" value="${requestScope.sloginId}">
					<input type="hidden" name="suserName" value="${requestScope.suserName}">
					<input type="hidden" name="sicon" value="${requestScope.sicon}">
					<input type="hidden" name="sprofile" value="${requestScope.sprofile}">
				</td>
			</tr>
		</table>
	</form>
	<form action="./UserSearchSqueeze" method="post">
			<table style="width: 400px" class="table container padding-y-5">
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
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
		<h4 class="text-center">新規登録する情報を入力してください</h4>
		<br>
<%
	String newloginId = request.getParameter("newloginId");
	String newpassword = request.getParameter("newpassword");
	String newuserName = request.getParameter("newuserName");
	String newicon = request.getParameter("newicon");
	String newprofile = request.getParameter("newprofile");
%>
		<%-- ログインフォーム入力欄 --%>
		<form action="./getinfo" method="post">
			<table style="width: 400px" class="table container padding-y-5">

				<%-- 全入力チェック --%>
				<c:if test="${requestScope.alertblank != null && requestScope.alertblank != ''}">
					<tr>
						<td colspan="2" class="color-error text-left"><c:out value="${requestScope.alertblank}" /></td>
					</tr>
				</c:if>
				<tr>
					<td class="color-main text-center">ログインID</td>
					<td class="text-center"><input class="form-control" type="text" name="newloginId" value="${requestScope.newloginId}" size="20" /></td>
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
					<td class="text-center"><input class="form-control" type="password" name="newpassword" value="${requestScope.password}" size="20" /></td>
				</tr>
				<%-- パスワード文字数チェック --%>
				<c:if test="${requestScope.alertpass != null && requestScope.alertpass != ''}">
					<tr>
						<td colspan="2" class="color-error text-left"><c:out value="${requestScope.alertpass}" /></td>
					</tr>
				</c:if>
				<tr>
					<td class="color-main text-center">ユーザー名</td>
					<td class="text-center"><input class="form-control" type="text" name="newuserName" value="${requestScope.newuserName}" size="20" /></td>
				</tr>
				<tr>
					<td class="color-main text-center">アイコン</td>
					<td>
						<select name="newicon" class="form-control">
							<c:choose>
								<c:when test="${requestScope.newicon==''}">
									<option value="icon-user" selected>男性</option>
									<option value="icon-user-female">女性
								</c:when>
								<c:when test="${requestScope.newicon=='icon-user'}">
									<option value="icon-user" selected>男性</option>
									<option value="icon-user-female">女性
								</c:when>
								<c:when test="${requestScope.newicon=='icon-user-female'}">
									<option value="icon-user">男性
									<option value="icon-user-female" selected>女性</option>
								</c:when>
							</c:choose>
						</select>
					</td>
				</tr>
				<tr>
					<td class="color-main text-center">プロフィールに追加する一言</td>
					<td class="text-right"><input class="form-control" type="text" name="newprofile" value="${requestScope.newprofile}" size="20" /></td>
				</tr>
				<tr>
					<td colspan="2" class="text-right"><input class="btn" type="submit" value="新規登録" /></td>
				</tr>
			</table>
		</form>
		<form action="./TurnLogin" method="post">
			<table style="width: 400px" class="table container padding-y-5">
				<tr>
					<td colspan="2" class="text-right">
						<input class="btn" type="submit" value="ログイン画面に戻る" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
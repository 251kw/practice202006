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
<%
	String sloginId = request.getParameter("sloginId");
	String suserName = request.getParameter("suserName");
	String sicon = request.getParameter("sicon");
	String sprofile = request.getParameter("sprofile");
%>
		<h5 class="text-center">検索したい条件を入力または選択してください</h5>
		<br>
		<form action="./UserSearchSqueeze" method="post">
			<table style="width: 400px" class="table container padding-y-5">
				<%-- 全入力チェック --%>
				<c:if test="${requestScope.notfound != null && requestScope.notfound != ''}">
					<tr>
						<td colspan="2" class="color-error text-center"><c:out value="${requestScope.notfound}" /></td>
					</tr>
				</c:if>
				<tr>
					<%-- ログインID検索入力欄の名前はsloginId --%>
					<td class="color-main text-center">ログインID</td>
					<td class="text-center"><input autofocus class="form-control" type="text" name="sloginId" value="${requestScope.sloginId}" size="20" /></td>
				</tr>
				<%-- ID文字数or半角英数チェック --%>
				<c:if test="${requestScope.alertid != null && requestScope.alertid != ''}">
					<tr>
						<td colspan="2" class="color-error text-left"><c:out value="${requestScope.alertid}" /></td>
					</tr>
				</c:if>
				<tr>
					<%-- ユーザー名検索入力欄の名前はsuserName --%>
					<td class="color-main text-center">ユーザー名</td>
					<td class="text-center"><input class="form-control" type="text" name="suserName" value="${requestScope.suserName}" size="20" /></td>
				</tr>
				<tr>
					<%-- icon検索入力欄の名前はsicon --%>
					<td class="color-main text-center">アイコン</td>
					<td>
						<select name="sicon" class="form-control">
							<c:choose>
								<c:when test="${requestScope.sicon==''}">
									<option value="all" selected>全選択</option>
									<option value="icon-user">男性</option>
									<option value="icon-user-female">女性</option>
								</c:when>
								<c:when test="${requestScope.sicon=='icon-user'}">
									<option value="all">全選択</option>
									<option value="icon-user" selected>男性</option>
									<option value="icon-user-female">女性</option>
								</c:when>
								<c:when test="${requestScope.sicon=='icon-user-female'}">
									<option value="all">全選択</option>
									<option value="icon-user">男性</option>
									<option value="icon-user-female" selected>女性</option>
								</c:when>
							</c:choose>
						</select>
					</td>
				</tr>
				<tr>
					<%-- プロフィール検索入力欄の名前はsprofile --%>
					<td class="color-main text-center">プロフィール</td>
					<td class="text-center"><input class="form-control" type="text" name="sprofile" value="${requestScope.sprofile}" size="20" /></td>
				</tr>
				<tr>
					<td colspan="2" class="text-right"><input class="btn" type="submit" value="検索" /></td>
				</tr>
			</table>
		</form>
		<form action="./TurnBoardTop" method="post">
			<table style="width: 400px" class="table container padding-y-5">
				<tr>
					<td colspan="2" class="text-right">
						<input class="btn" type="submit" value="掲示板に戻る" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
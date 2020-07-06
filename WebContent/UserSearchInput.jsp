<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Arrays"%>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>検索情報入力</title>
	<link rel="stylesheet" href="./css/skyblue.css">
	<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
	<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<% request.setCharacterEncoding("UTF-8");%>
	<div class="bg-success padding-y-5">
		<div class="padding-y-5 text-center">
			<strong>検索フォーム</strong>
		</div>
	</div>
	<div class="padding-y-5">
		<div style="width: 50%" class="padding-y-5 container ">
			<form action="./usi" method="post">
				<h5 class="text-center">*検索したい言葉を入れてね</h5>
				<table border="1" class="table">
					<%-- リクエストスコープのalertの値を出力 --%>
					<c:if test="${requestScope.alert != null && requestScope.alert != ' '}">
						<tr>
							<td colspan="2" class="color-error text-center">
								<c:out value="${requestScope.alert}" />
							</td>
						</tr>
					</c:if>
					<tr>
						<th><label for="loginId">
							<span class="icon-smile pe-2x pe-va"></span>&nbsp;ログインID
						</label></th>
						<td>
							<input type="text" pattern="^[0-9A-Za-z]+$" title="半角英数字" value="${param.loginId}" name="loginId" maxlength="15" id="loginId" class="form-control">
						</td>
					</tr>
					<tr>
						<th><label for="userName">
							<span class="icon-note pe-2x pe-va"></span>&nbsp;名前
						</label></th>
						<td>
							<input type="text" name="userName" value="${param.userName}" id="userName" class="form-control">
						</td>
					</tr>
					<tr>
						<th><span class="icon-users pe-2x pe-va"></span>&nbsp;アイコン</th>
						<td><input type="checkbox" name="icon" value="icon-joy"<c:if test="${param.icon.contains('joy')==true}">checked</c:if>>
							<span class="icon-joy pe-2x pe-va"></span>&nbsp;
							<input type="checkbox" name="icon" value="icon-moon"<c:if test="${param.icon.contains('moon')==true}">checked</c:if>>
							<span class="icon-moon pe-2x pe-va"></span>&nbsp;
							<input type="checkbox" name="icon" value="icon-smile"<c:if test="${param.icon.contains('smile')==true}">checked</c:if>>
							<span class="icon-smile pe-2x pe-va"></span>&nbsp;
							<input type="checkbox" name="icon" value="icon-music"<c:if test="${param.icon.contains('music')==true}">checked</c:if>>
							<span class="icon-music pe-2x pe-va"></span>&nbsp;
							<input type="checkbox" name="icon" value="icon-cloud"<c:if test="${param.icon.contains('cloud')==true}">checked</c:if>>
							<span class="icon-cloud pe-2x pe-va"></span>&nbsp;
							<input type="checkbox" name="icon" value="icon-ball"<c:if test="${param.icon.contains('ball')==true}">checked</c:if>>
							<span class="icon-ball pe-2x pe-va"></span>&nbsp;
							<input type="checkbox" name="icon" value="icon-coffee"<c:if test="${param.icon.contains('coffee')==true}">checked</c:if>>
							<span class="icon-coffee pe-2x pe-va"></span>&nbsp;
						</td>
					</tr>
					<tr>
						<th><span class="icon-note2 pe-2x pe-va"></span>&nbsp;プロフィール</th>
						<td><textarea rows="5" cols="20" name="profile" class="form-control">${param.profile}</textarea></td>
					</tr>
					<tr>
						<td colspan="2" class="text-left">
								<input class="btn" type="submit" name="cmd" value="検索">
							<input class="btn" type="submit" name="cmd" value="全件表示">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="container" style="width: 50%">
		<form action="top.jsp" method="post">
			<table class="table">
				<tr>
					<td><input class="btn" type="submit" value="戻る"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
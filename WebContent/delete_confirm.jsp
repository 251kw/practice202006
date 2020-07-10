<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>shouter-削除確認-</title>
		<link rel="stylesheet" href="./css/skyblue.css">
		<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
		<link rel="stylesheet" href="./css/helper.css">
	</head>

	<body>
		<%---------------------------------------------------------------------------------%>
		<div class="bg-success padding-y-5">
			<div class="padding-y-5 text-center">
				<h1>Shouter<span class="icon-speaker pe-1x pe-va"></span></h1>
			</div>
		</div>
		<div class="padding-y-5 text-center">
			<h5>以下のユーザー情報を削除しますか？</h5>
		</div>
		<%---------------------------------------------------------------------------------%>

		<%--リクエストスコープにあるArrayList型のオブジェクトを参照 --%>
		<jsp:useBean id="users" scope="request" type="java.util.ArrayList<dto.UserDTO>" />
		<div class="padding-y-5 text-center">
			<div style="width: 50%" class="container padding-y-5">

				<%-- リクエストスコープにalert（自分を消す時）があれば --%>
				<c:if
					test="${requestScope.alert != null && requestScorpe.alert !=''}">
					<tr>
						<%--リクエストスコープのalertの値を出力 --%>
						<td colspan="2" class="color-error text-left">
							<c:out value="${requestScope.alert}" /></td>
					</tr>
				</c:if>

				<form action="./drs" method="post">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th></th>
							<th>ログインID</th>
							<th>ユーザー名</th>
							<th>アイコン</th>
							<th>プロフィール</th>
						</tr>
					</thead>
					<c:forEach var="users" items="${users}" varStatus="loop">
					<tbody>
						<tr>
							<td>${loop.count}</td>
							<td>${users.loginId}
								<input type="hidden" name="loginId" value="${users.loginId}"></td>
							<td>${users.userName}</td>
							<td><span class="${users.icon} pe-2x pe-va"></span></td>
							<td>${users.profile}</td>
						</tr>
					</c:forEach>
				</table>
					<input type="hidden" name="only" value="${only}">
					<c:forEach var="checkbox" items="${checkbox}" varStatus="loop">
						<input type="hidden" name="checkbox" value="${checkbox}">
					</c:forEach>
					<input type="submit" value="キャンセル" name="btn" class="btn">
					<input type="submit" value="削除" name="btn" class="btn">
				</form>
			</div>
		</div>
	</body>
</html>
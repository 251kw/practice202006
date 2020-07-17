<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date"%>
<%@ page import="util.Check" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>shouter-検索結果-</title>
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
			<h5>検索結果一覧</h5>
		</div>
		<%---------------------------------------------------------------------------------%>

		<%--リクエストスコープにあるArrayList型のオブジェクトを参照 --%>
		<jsp:useBean id="users" scope="session" type="java.util.ArrayList<dto.UserDTO>" />
		<div class="padding-y-5">
			<div style="width: 60%" class="container padding-y-5">
			<form action="./srs" method="post">
				<table class="table table-striped table-bordered table-hover">

					<thead>
						<tr>
							<th><button class="btn btn-sm" type="submit" name="btn" value="${flg}">全選択/解除</button></th>
							<th>ログインID</th>
							<th>ユーザー名</th>
							<th>アイコン</th>
							<th>プロフィール</th>
						</tr>
					</thead>
					<%--検索結果回す --%>
					<c:forEach var="users" items="${users}" varStatus="loop">
					<tbody>
						<tr><c:choose>
								<c:when test="${users.d_flg == 1}"><td><font color="#FF0000">削除済</font></td></c:when>
								<c:otherwise>
									<td><input type="checkbox" name="checkbox" value="${users.loginId}" ${Check.checkBox(users.loginId, loginIds)}/></td>
									</c:otherwise>
							</c:choose>
							<td>${users.loginId}</td>
							<td>${users.userName}</td>
							<td><span class="${users.icon} pe-2x pe-va"></span></td>
							<td>${users.profile}</td>
						</tr>
					</c:forEach>
				</table>
				<table class="container padding-y-5">
					<%-- リクエストスコープにalertがあれば --%>
					<c:if
						test="${requestScope.alert != null && requestScorpe.alert !=''}">
						<tr>
							<%--リクエストスコープのalertの値を出力 --%>
							<td colspan="2" class="color-error text-left">
							<c:out value="${requestScope.alert}" /></td>
						</tr>
					</c:if>

					<tr>
						<td colspan="2" class="text-right">
						<input class="btn  btn-error" type="submit" name="btn" value="選択項目を削除">
						<input class="btn  btn-warning" type="submit" name="btn" value="選択項目を編集">
						<input class="btn" type="submit" value="戻る" name="btn">
						</td>
					</tr>
				</table>
			</form>
			</div>
		</div>
	</body>
</html>
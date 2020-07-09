<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="util.Check" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>shouter-新規登録-</title>
		<link rel="stylesheet" href="./css/skyblue.css">
		<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
		<link rel="stylesheet" href="./css/helper.css">
	</head>
	<body>

		<%---------------------------------------------------------%>
		<div class="bg-success padding-y-5">
			<div class="padding-y-5 text-center">
				<h1>Shouter<span class="icon-speaker pe-1x pe-va"></span></h1>
			</div>
		</div>
		<div class="padding-y-5 text-center">
			<h5>ユーザー情報を入力してください</h5>
		</div>
		<%----------------------------------------------------------%>

		<%--jsp:useBean id="newuser" scope="request" type="dto.UserDTO"/--%>
		<div class="padding-y-5">
			<div style="width: 40%" class="container padding-y-5">
				<form action="./sui" method="post">
				<table border="1" class="table">

					<%-- リクエストスコープにalertがあれば --%>
					<c:if
						test="${requestScope.alert != null && requestScorpe.alert !=''}">
						<tr>
							<%--リクエストスコープのalertの値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert}" /></td>
						</tr>
					</c:if>

						<tr>
							<th><label for="loginId"><font color="#00CCFF"><span class="icon-id pe-2x pe-va"></span></font>&nbsp;ログインID
							</label></th>
							<td><div><h6>半角英数字で入力してください</h6></div><input type="text" name="loginId" id="loginId" value="${loginId}" class="form-control"></td>
						</tr>
						<tr>

							<th><label for="pass"><font color="#CC99CC"><span class="icon-key pe-2x pe-va"></span></font>&nbsp;パスワード</label></th>
							<td><input type="password"name="pass" id="pass" value="${password}" class="form-control"></td>
						</tr>
						<tr>
							<th><label for="userName"><font color="#FF3333"><span class="icon-users pe-2x pe-va"></span></font>&nbsp;ユーザ名</label></th>
							<td><input type="text"name="userName" id="userName" value="${userName}" class="form-control"></td>
						</tr>
						<tr>
							<th><font color="#FFCC33"><span class="icon-smile pe-2x pe-va"></span></font>&nbsp;アイコン</th>
							<td>
								<select name="icon" class="form-control">
								<c:choose>
									<c:when test="${(icon).equals('icon-user') || icon==null || icon==''}">
									<option value="icon-user" selected="selected">男性</option></c:when>
									<c:otherwise><option value="icon-user">男性</option></c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${(icon).equals('icon-user-female')}">
									<option value="icon-user-female" selected="selected">女性</option></c:when>
									<c:otherwise><option value="icon-user-female">女性</option></c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${(icon).equals('icon-bell')}">
									<option value="icon-bell" selected="selected">ベル</option></c:when>
									<c:otherwise><option value="icon-bell">ベル</option></c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${(icon).equals('icon-star')}">
									<option value="icon-star" selected="selected">星</option></c:when>
									<c:otherwise><option value="icon-star">星</option></c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${(icon).equals('icon-moon')}">
									<option value="icon-moon" selected="selected">月</option></c:when>
									<c:otherwise><option value="icon-moon">月</option></c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${(icon).equals('icon-rocket')}">
									<option value="icon-rocket" selected="selected">ロケット</option></c:when>
									<c:otherwise><option value="icon-rocket">ロケット</option></c:otherwise>
								</c:choose>
								</select>
							</td>
						</tr>
						<tr>
							<th><font color="#99CC66"><span class="icon-comment pe-2x pe-va"></span></font>&nbsp;プロフィール</th>
							<td><textarea rows="5" cols="20" name="profile" class="form-control">${profile}</textarea></td>
						</tr>
						<tr>
							<td colspan="2" class="text-right">
							<input class="btn" type="submit" value="確認画面へ" name="btn"/>
							<input type="button" value="戻る" onClick="location.href='./sur'" class="btn">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>

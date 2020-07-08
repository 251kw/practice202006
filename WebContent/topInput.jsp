<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang ="ja">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Shouter-みんなの心の叫び-</title>
		<link rel="stylesheet" href="./css/skyblue.css">
		<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
		<link rel="stylesheet" href="./css/helper.css">
	</head>
	<body>

		<div class="bg-warning padding-y-5">
			<div class="padding-y-5 text-center">
				<strong>Shouter</strong>
				<span class="icon-speaker pe-2x pe-va"></span>
			</div>
		</div>

		<div class="padding-y-5 text-center" >ログインユーザ情報</div>

		<jsp:useBean id="user" scope="session" type="dto.UserDTO" />
		<div class="padding-y-5">
			<div style="width: 40%" class="container padding-y-5">
				<%-- action 属性にサーブレットを指定 --%>
					<table class ="table table-bordered">
						<tr>
							<td rowspan="2" class="text-center"><span class="${user.icon} pe-3x pe-va"></span></td>
							<td width="256">${user.userName}</td>
							<td>
								<form action="./logout" method="post">
									<input class="btn btn-lidht" type="submit" value="ログアウト" />
								</form>
								<form action="./searchFirst" method="post">
									<input class="btn btn-lidht" type="submit" value="ユーザー検索" />
								</form>
							</td>
						</tr>
						<tr>
							<td colspan="2">${user.profile}</td>
						</tr>
					</table>
			</div>
		</div>

		<h5 class="padding-y-5 text-center">今の気持ちを叫ぼう</h5>

		<form action="./bbs" method="post">
			<table style="width: 40%" class="container padding-y-5">
				<tr>
					<td><input class="form-control" type="text" name="shout" value="" size="60" /></td>
					<td><input class="btn" type="submit" value="叫ぶ" /></td>
				</tr>
				<c:if test="${requestScope.alert != null && requestScope.alert != ''}">
					<tr>
						<td colspan="2" class="color-error text-left">
							<c:out value="${requestScope.alert}" />
						</td>
					</tr>
				</c:if>
			</table>
		</form>

		<jsp:useBean id="shouts" scope="session" type="java.util.ArrayList<dto.ShoutDTO>" />
		<div style="width: 50%" class="container padding-y-5">
			<c:forEach var="shout" items="${shouts}">
				<table class="table table-striped table-bordered">
					<tr>
						<td rowspan="2" class="text-center"><span class="${shout.icon} pe-3x pe-va"></span></td>
						<td>${shout.userName}</td>
					</tr>
					<tr>
						<td>${shout.date}</td>
					</tr>
					<tr>
						<td colspan="2"><textarea rows="5" class="form-control">${shout.writing}</textarea></td>
					</tr>
				</table>
			 </c:forEach>
		</div>

	</body>
</html>
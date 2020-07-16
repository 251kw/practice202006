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
		<link rel="stylesheet" href="./css/origin.css">
	</head>
	<body>

			<div class="bg-warning padding-y-5">
				<div class="padding-y-5 text-center">
					<strong>Shouter</strong>
					<span class="icon-speaker pe-2x pe-va"></span>
				</div>
			</div>

			<div class="padding-y-5 text-center" >ログインユーザ情報</div>
			<c:if test="${requestScope.alertshout != null && requestScope.alertshout != ''}">
				<div class="color-error text-center">${requestScope.alertshout}</div>
			</c:if>
			<c:if test="${requestScope.alert != null && requestScope.alert != ''}">
				<div class="color-error text-center">${requestScope.alert}</div>
			</c:if>

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

		<%-- <div class="upper"> --%>

			<h5 class="padding-y-5 text-center">今の気持ちを叫ぼう</h5>
			<form action="./bbs" method="post">
				<table style="width: 70%" class="container padding-y-5">
					<tr>
						<td><input class="form-control" type="text" name="shout" value="" size="60" /></td>
						<td><input class="btn" type="submit" value="叫ぶ" autofocus/></td>
					</tr>
				</table>
			</form>

		<%-- </div> --%>

			<jsp:useBean id="shouts" scope="session" type="java.util.ArrayList<dto.ShoutDTO>" />
			<form action="./deleteOrUpdateShoutsConfirmServlet" method="post">

			<c:if test="${ck == 'on' }"><div class="text-center"><button class="btn" type="submit" name="shoutsId" value="allchkon" >すべて選択</button></div></c:if>
			<c:if test="${ck == 'off' }"><div class="text-center"><button class="btn" type="submit" name="shoutsId" value="allchkoff" >すべて解除</button></div></c:if>

				<div style="width: 50%" class="container padding-y-5">
					<c:forEach var="shout" items="${shouts}">
						<table class="table table-bordered">
							<tr>
								<td>
									<label class="fancy-checkbox"><input type="checkbox" name="checked" value="${shout.shoutsId}" <c:forEach var="checks" items="${checkShouts}"><c:if test="${shout.shoutsId == checks}">checked</c:if></c:forEach> /><span></span></label>
								</td>
								<c:if test="${shout.loginId != user.loginId}">
									<td>
										<button class="btn btn-sm btn-light" type="submit" name="shoutsId" value="${shout.shoutsId}" disabled >更 新</button>
									</td>
								</c:if>
								<c:if test="${shout.loginId == user.loginId}">
									<td>
										<button class="btn btn-sm" type="submit" name="shoutsId" value="${shout.shoutsId}" >更 新</button>
									</td>
								</c:if>

							</tr>
							<tr>
								<td rowspan="2" class="text-center"><span class="${shout.icon} pe-3x pe-va"></span></td>
								<td>${shout.userName}</td>
							</tr>
							<tr>
								<td>${shout.date}</td>
							</tr>
							<tr>
								<td colspan="2" height="150">${shout.writing}</td>
							</tr>
						</table>
					 </c:forEach>
					<c:if test="${not empty shouts}"><button class="btn" type="submit" name="shoutsId" value="alldel" >選択した叫びを削除する</button></c:if>
				</div>
			</form>

			<%-- <div style="margin-bottom:200px"></div> --%>


	</body>
</html>
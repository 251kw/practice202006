<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang ="ja">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Shouter-確認-</title>
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

		<div class="padding-y-5 text-center" >削除シャウト情報</div>

		<jsp:useBean id="searchShouts" scope="request" type="java.util.ArrayList<dto.ShoutDTO>" />
			<div style="width: 50%" class="container padding-y-5">
				<c:forEach var="shout" items="${searchShouts}">
					<table class="table table-bordered">
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

		<div align="center">
			<form action="./multiDeleteShoutsResultServlet" method="post">
				<c:forEach var="shout" items="${searchShouts}">
					<input type="hidden" name="shoutsId" value="${shout.shoutsId}">
				</c:forEach>
				<input class="btn" type="submit" style="height:50px" value="削除確定" />
			</form>
		</div>

		<div align="center">
			<form action="./returnTop" method="post">
				<input class="btn btn-grey btn-sm" type="submit" style="height:50px" value="戻る" />
			</form>
		</div>

	</body>
</html>
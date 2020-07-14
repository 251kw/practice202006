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

		<div class="padding-y-5 text-center" >叫び直す</div>

		<jsp:useBean id="searchShout" scope="request" type="dto.ShoutDTO" />
		<form action="./updateShoutConfirmServlet" method="post">
			<input type="hidden" name="shoutsId" value="${searchShout.shoutsId}">
			<table style="width: 40%" class="table container">
				<tr>
					<td class="color-main text-left">アイコン</td>
					<td><span class="${searchShout.icon} pe-2 pe-va"></span></td>
				</tr>
				<tr>
					<td class="color-main text-left">ユーザー名</td>
					<td class="color-main text-left">${searchShout.userName}</td>
				</tr>
				<tr>
					<td class="color-main text-left" height="100%">叫び直そう</td>
					<td>
						<textarea name="shout" rows="4" cols="40">${searchShout.writing}</textarea>
					</td>
				</tr>
				<c:if test="${requestScope.alert != null && requestScope.alert != ''}">
					<tr>
						<td colspan="2" class="color-error text-left">
							<c:out value="${requestScope.alert}" />
						</td>
					</tr>
				</c:if>
			</table>

			<div align="center">
				<input class="btn" type="submit" value="叫び直す" />
			</div>

		</form>

		<div align="center">
			<form action="./returnTop" method="post">
				<c:forEach var="shoutsIds" items="${shoutsIds}">
					<input type="hidden" name="shoutsId" value="${shoutsIds}">
				</c:forEach>
				<input class="btn btn-grey btn-sm" type="submit" style="height:50px" value="戻る" />
			</form>
		</div>

	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>変更入力</title>
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
		<h4 class="text-center">書き込み内容を入力してください</h4>
		<br>
		<form action="./ShoutEditCheckChar" method="post">
			<table style="width: 400px" class="table table-borderd container padding-y-5">
				<c:if test="${requestScope.alertmessage != null && requestScope.alertmessage != ''}">
					<tr>
						<td colspan="4" class="color-error text-center"><c:out value="${requestScope.alertmessage}" /></td>
					</tr>
				</c:if>
				<tr>
					<th class="color-main text-center">ログインID:</th>
					<td class="text-center">${requestScope.shoutinfo.loginId}</td>
				</tr>
				<tr>
					<th class="color-main text-center">ユーザー名:</th>
					<td class="text-center">${requestScope.shoutinfo.userName}</td>
				</tr>
				<tr>
					<th class="color-main text-center">アイコン:</th>
					<td class="text-center"><span class="${requestScope.shoutinfo.icon} pe-2x pe-va"></span></td>
				</tr>
				<tr>
					<th class="color-main text-center">書き込み内容</th>
					<c:choose>
						<c:when test="${empty requestScope.eswriting}">
							<td class="text-center"><input autofocus class="form-control" type="text" name="eswriting" value="${requestScope.shoutinfo.writing}" size="20" /></td>
						</c:when>
						<c:when test="${!empty requestScope.eswriting}">
							<td class="text-center"><input autofocus class="form-control" type="text" name="eswriting" value="${requestScope.eswriting}" size="20" /></td>
						</c:when>
					</c:choose>
				</tr>
				<tr>
					<td colspan="2" class="text-right">
						<input class="btn" type="submit" value="確認画面へ" />
						<input type="hidden" name="eshoutsId" value="${requestScope.eshoutsId}">
						<input type="hidden" name="esloginId" value="${requestScope.shoutinfo.loginId}">
						<input type="hidden" name="esuserName" value="${requestScope.shoutinfo.userName}">
						<input type="hidden" name="esicon" value="${requestScope.shoutinfo.icon}">
						<input type="hidden" name="esdate" value="${requestScope.shoutinfo.date}">
						<input type="hidden" name="eswriting" value="${requestScope.shoutinfo.writing}">
					</td>
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
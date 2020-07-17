<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="util.Check" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>shouter-登録情報編集-</title>
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
			<h5>「叫ぶ」内容を変更できます。</h5>
		</div>
		<%----------------------------------------------------------%>

		<jsp:useBean id="shout" scope="request" type="dto.ShoutDTO"/>
		<div class="padding-y-5">
			<div style="width: 40%" class="container padding-y-5">
				<form action="./udis" method="get">
				<table border="1" class="table">

						<tr>
							<th><label for="userName"><font color="#FF3333"><span class="icon-users pe-2x pe-va"></span></font>
								&nbsp;ユーザ名</label>
							</th>
							<td>${shout.userName}</td>
						</tr>

						<tr>
							<th><font color="#FFCC33"><span class="icon-smile pe-2x pe-va"></span></font>&nbsp;アイコン</th>
							<td><span class="${shout.icon} pe-2x pe-va"></span></td>
						</tr>

						<tr>
							<th><font color="#80ffff"><span class="icon-chat pe-2x pe-va"></span></font>&nbsp;叫び</th>
							<td><c:choose>
									<c:when test="${comment==null}">
									<textarea rows="5" cols="20" name="comment" class="form-control">${shout.writing}</textarea></c:when>
									<c:otherwise>
									<textarea rows="5" cols="20" name="comment" class="form-control">${comment}</textarea></c:otherwise>
							</c:choose></td>
						</tr>

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
							<input type="hidden" value="${upshoutId}" name="upshoutId">
							<c:forEach var="s" items="${shoutIds}">
								<input type="hidden" value="${s}" name="shoutId">
							</c:forEach>
							<input class="btn" type="submit" value="確認画面へ" name="btn"/>
							<input class="btn" type="submit" value="キャンセル" name="btn">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>
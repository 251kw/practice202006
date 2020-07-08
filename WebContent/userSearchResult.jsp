<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>検索結果</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>

		<jsp:useBean id="users" scope="request"
			type="java.util.ArrayList<dto.SearchDTO>" />
		<div class="padding-y-5">
			<div style="width: 80%" class="container padding-y-5">
				<%-- リストにある要素の数だけ繰り返し --%>
					<table  width="500" class="table table-striped table-bordered">
						<tr>
							<td width="50" height="10"><span class="icon-users pe-2x pe-va"></span></td>
							<td width="113" height="10">名前</td>
							<td width="120" height="10">ログインID</td>
							<td width="170" height="10">プロフィール</td>
							<td width="120" height="10">更新</td>
							<td width="120" height="10">削除</td>
							<c:forEach var="users" items="${users}">
								<tr>
									<td width="50" height="10"><span
										class="${users.icon} pe-2x pe-va"></span></td>
									<td width="130" height="10">${users.userName}</td>
									<td width="120" height="10">${users.loginId}</td>
									<td width="170" height="10">${users.profile}</td>
									<td><input type="button" onclick="location.href='./uui?loginId=${users.loginId}'"
									value="更新" class="btn"></td>
									<td><input type="button" onclick="location.href='./udi?loginId=${users.loginId}'"
									value="削除" class="btn"></td>
								</tr>
							</c:forEach>
						</tr>
					</table>

			</div>
		</div>
	<form action="./userSearchInput.jsp" method="post">
		<%
			//文字化け対策
			request.setCharacterEncoding("UTF-8");

			String[] icon = (String[]) request.getAttribute("icon");
			String loginId = (String) request.getAttribute("loginId");
			String userName = (String) request.getAttribute("userName");
			String profile = (String) request.getAttribute("profile");

			request.setAttribute("icons", icon);

		%>
		<input type="hidden" name="suserName" value="<%=userName%>">
		<input type="hidden" name="sloginId" value="<%=loginId%>">
		<input type="hidden" name="sprofile" value="<%=profile%>">
		<c:choose>
			<c:when test="${length == 1}">
				<input type="hidden" name="sicon" value="<%=icon[0]%>">
				<input type="hidden" name="length" value="${length}">
				<input type="hidden" name="flag" value="<%="on"%>">
			</c:when>
			<c:when test="${length == 2}">
				<input type="hidden" name="sicon" value="<%=icon[0]%>">
				<input type="hidden" name="sicon2" value="<%=icon[1]%>">
				<input type="hidden" name="length" value="${length}">
				<input type="hidden" name="flag" value="<%="on"%>">
			</c:when>
		</c:choose>

		<table style="width: 500px" class="table">
			<tr>
				<td></td>
				<td colspan="4" class="text-center"><input type="submit"
					value="戻る" class="btn"></td>
			</tr>
		</table>
	</form>
</body>
</html>
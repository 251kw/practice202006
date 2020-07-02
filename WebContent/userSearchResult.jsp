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
		<div style="width: 60%" class="container padding-y-5">
			<table  width="500" class="table table-striped table-bordered">
			<tr>
					<td width="50" height="10"><span class="icon-users pe-2x pe-va"></span></td>
					<td width="130" height="10">名前</td>
					<td width="120" height="10">ログインID</td>
					<td width="180" height="10">プロフィール</td>
			</tr>
			</table>
			<%-- リストにある要素の数だけ繰り返し --%>
			<c:forEach var="users" items="${users}">
				<table  width="500" class="table table-striped table-bordered">
					<tr>
						<td width="50" height="10"><span
							class="${users.icon} pe-2x pe-va"></span></td>
						<td width="130" height="10">${users.userName}</td>
						<td width="120" height="10">${users.loginId}</td>
						<td width="180" height="10">${users.profile}</td>
					</tr>
				</table>
			</c:forEach>
		</div>
	</div>
	<table style="width: 500px" class="table">
	<tr>
				<td><input type="button"
					onclick="location.href='./userSearchInput.jsp'" value="戻る"
					class="btn"></td>
			</tr>
	</table>
</body>
</html>
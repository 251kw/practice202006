<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー検索結果</title>
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
	<%-- セッションスコープにあるArrayList型のオブジェクトを参照 --%>
	<jsp:useBean id="resultList" scope="request" type="java.util.ArrayList<dto.UserDTO>" />
	<div class="padding-y-5">
		<div style="width: 400px" class="container padding-y-5">
			<%-- リストにある要素の数だけ繰り返し --%>
			<c:forEach var="resultList" items="${resultList}">
				<table style="width: 400px" class="table table-striped table-borderd container">
					<tr>
						<th>ログインID</th>
						<td>${resultList.loginId}</td>
					</tr>
					<tr>
						<th>ユーザー名</th>
						<td>${resultList.userName}</td>
					</tr>
					<tr>
						<th>アイコン</th>
						<td><span class="${resultList.icon} pe-2x pe-va"></span></td>
					</tr>
					<tr>
						<th>プロフィール</th>
						<td>${resultList.profile}</td>
					</tr>
				</table>
				<hr>
			</c:forEach>
		</div>
	</div>
	<form action="./ReturnSearchInput" method="post">
		<table style="width: 400px" class="table container padding-y-5">
			<tr>
				<td><input type="hidden" name="sloginId" value="${requestScope.sloginId}"></td>
				<td><input type="hidden" name="suserName" value="${requestScope.suserName}"></td>
				<td><input type="hidden" name="sicon" value="${requestScope.sicon}"></td>
				<td><input type="hidden" name="sprofile" value="${requestScope.sprofile}"></td>
			</tr>
			<tr>
				<td colspan="2" class="text-right">
					<input class="btn" type="submit" value="入力画面に戻る" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
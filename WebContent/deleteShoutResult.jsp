<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>削除結果</title>
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
		<h4 class="text-center">以下の書き込みを削除しました</h4>
		<br>
		<form action="./TurnBoardTop" method="post">
			<%-- リクエストスコープにあるArrayList型のオブジェクトを参照 --%>
			<jsp:useBean id="sdeleteList" scope="session" type="java.util.ArrayList<dto.UserDTO>" />
			<%-- リストにある要素の数だけ繰り返し --%>
			<table style="width: 600px" class="table table-borderd container padding-y-5">
				<tr class="bg-light">
					<th class="text-center">ログインID</th><th class="text-center">ユーザー名</th>
					<th class="text-center">アイコン</th><th class="text-center">プロフィール</th>
				</tr>
				<c:forEach var="sdeleteList" items="${sdeleteList}">
					<tr>
						<td class="text-center">${sdeleteList.loginId}</td>
						<td class="text-center">${sdeleteList.userName}</td>
						<td class="text-center"><span class="${sdeleteList.icon} pe-2x pe-va"></span></td>
						<td class="text-center">${sdeleteList.writing}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4" class="text-right">
						<input class="btn" type="submit" value="掲示板に戻る" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
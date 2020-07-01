<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang ="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter-検索結果-</title>
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

<div class="padding-y-5 text-center" >検索に成功しました</div>


<%-- セッションスコープスコープにあるArrayList型のオブジェクトを参照 --%>
<jsp:useBean id="searchUser" scope="request" type="java.util.ArrayList<dto.UserDTO>" />
<div class="padding-y-5">
	<div style="width: 50%" class="container padding-y-5">
	<%-- リストにある要素の数だけ繰り返し --%>
	<table class ="table table-bordered">
		<tr>
			<td class="color-main text-left">ログインID</td>
			<td class="color-main text-left">ユーザー名</td>
			<td class="color-main text-left">プロフィール</td>
			<td class="color-main text-left">アイコン</td>
		</tr>
		<c:forEach var="search" items="${searchUser}">
				<tr>
					<td>${search.loginId}</td>
					<td>${search.userName}</td>
					<td>${search.profile}</td>
					<td><span class="${search.icon} pe-3x pe-va"></span></td>
				</tr>
		 </c:forEach>
	</table>
	</div>
</div>

<form action="./searchBack" method="post">
	<input type="hidden" name="loginId" value="${requestScope.loginId}">
	<input type="hidden" name="userName" value="${requestScope.userName}">
	<input type="hidden" name="profile" value="${requestScope.profile}">
	<input type="hidden" name="icon_car" value="${requestScope.icon_car}">
	<input type="hidden" name="icon_paperclip" value="${requestScope.icon_paperclip}">
	<input type="hidden" name="icon_radio" value="${requestScope.icon_radio}">
	<table style="width: 40%" class="container padding-y-5">
		<tr>
			<td colspan="2" class="text-right"><input class="btn" type="submit" value="検索条件に戻る" /></td>
		</tr>
	</table>
</form>

</body>
</html>
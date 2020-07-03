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
	<h4 class="text-center">検索結果</h4>
	<br>
	<%-- リクエストスコープにあるArrayList型のオブジェクトを参照 --%>
	<jsp:useBean id="resultList" scope="request" type="java.util.ArrayList<dto.UserDTO>" />
		<%-- リストにある要素の数だけ繰り返し --%>
		<table style="width: 700px" class="table table-borderd container padding-y-5">
			<tr class="bg-light">
				<th class="text-center"><input type="checkbox" name="checkall" value="checkall"></th>
				<th class="text-center">ログインID</th><th class="text-center">ユーザー名</th>
				<th class="text-center">アイコン</th><th class="text-center">プロフィール</th>
				<th class="text-center">更新</th>
				<th class="text-center">削除</th>
			</tr>
			<c:forEach var="resultList" items="${resultList}">
			<tr>
				<td class="text-center"><input type="checkbox" name="select" value="select"></td>
				<td class="text-center">${resultList.loginId}</td>
				<td class="text-center">${resultList.userName}</td>
				<td class="text-center"><span class="${resultList.icon} pe-2x pe-va"></span></td>
				<td class="text-center">${resultList.profile}</td>
				<td class="text-center">
					<form action="./EditUser" method="post"><input class="butten" type="submit" value="更新">
						<%-- 該当するログインIDと入力情報を送る --%>
						<input type="hidden" name="eloginId" value="${resultList.loginId}">
						<input type="hidden" name="sloginId" value="${requestScope.sloginId}">
						<input type="hidden" name="suserName" value="${requestScope.suserName}">
						<input type="hidden" name="sicon" value="${requestScope.sicon}">
						<input type="hidden" name="sprofile" value="${requestScope.sprofile}">
					</form>
				</td>
				<td class="text-center">
					<form action="./TurnDeleteConfirm" method="post"><input class="butten" type="submit" value="削除">
						<%-- 該当するログインIDと入力情報を送る --%>
						<input type="hidden" name="dloginId" value="${resultList.loginId}">
						<input type="hidden" name="sloginId" value="${requestScope.sloginId}">
						<input type="hidden" name="suserName" value="${requestScope.suserName}">
						<input type="hidden" name="sicon" value="${requestScope.sicon}">
						<input type="hidden" name="sprofile" value="${requestScope.sprofile}">
					</form>
				</td>
			</tr>
			</c:forEach>
		</table>
	<form action="./TurnSearchInput" method="post">
		<table style="width: 700px" class="table container padding-y-5">
			<tr>
				<td><input type="hidden" name="sloginId" value="${requestScope.sloginId}"></td>
				<td><input type="hidden" name="suserName" value="${requestScope.suserName}"></td>
				<td><input type="hidden" name="sicon" value="${requestScope.sicon}"></td>
				<td><input type="hidden" name="sprofile" value="${requestScope.sprofile}"></td>
			</tr>
			<tr>
				<td colspan="4" class="text-right">
					<input class="btn" type="submit" value="入力画面に戻る" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
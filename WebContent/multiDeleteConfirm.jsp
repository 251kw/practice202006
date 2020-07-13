<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>削除確認</title>
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
		<h4 class="text-center">以下のユーザーを削除しますか？</h4>
		<br>
		<form action="./UserMultiDelete" method="post">
			<%-- リクエストスコープにあるArrayList型のオブジェクトを参照 --%>
			<jsp:useBean id="deleteList" scope="session" type="java.util.ArrayList<dto.UserDTO>" />
			<%-- リストにある要素の数だけ繰り返し --%>
			<table style="width: 600px" class="table table-borderd container padding-y-5">
				<%-- ログアウト警告 --%>
				<c:if test="${requestScope.logoutalert != null && requestScope.logoutalert != ''}">
					<tr>
						<td colspan="4" class="color-error text-center"><c:out value="${requestScope.logoutalert}" /></td>
					</tr>
				</c:if>
				<tr class="bg-light">
					<th class="text-center">ログインID</th><th class="text-center">ユーザー名</th>
					<th class="text-center">アイコン</th><th class="text-center">プロフィール</th>
				</tr>
				<c:forEach var="deleteList" items="${deleteList}">
					<tr>
						<td class="text-center">${deleteList.loginId}</td>
						<td class="text-center">${deleteList.userName}</td>
						<td class="text-center"><span class="${deleteList.icon} pe-2x pe-va"></span></td>
						<td class="text-center">${deleteList.profile}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4" class="text-right">
						<input class="btn" type="submit" value="削除" />
						<input type="hidden" name="sloginId" value="${requestScope.sloginId}">
						<input type="hidden" name="suserName" value="${requestScope.suserName}">
						<input type="hidden" name="sicon" value="${requestScope.sicon}">
						<input type="hidden" name="sprofile" value="${requestScope.sprofile}">
						<input type="hidden" name="logoutalert" value="${requestScope.logoutalert}">
					</td>
				</tr>
			</table>
		</form>
		<form action="./UserSearchSqueeze" method="post">
			<table style="width: 600px" class="table table-borderd container padding-y-5">
				<tr>
					<td colspan="4" class="text-right">
						<input class="btn" type="submit" value="検索結果に戻る" />
						<input type="hidden" name="sloginId" value="${requestScope.sloginId}">
						<input type="hidden" name="suserName" value="${requestScope.suserName}">
						<input type="hidden" name="sicon" value="${requestScope.sicon}">
						<input type="hidden" name="sprofile" value="${requestScope.sprofile}">
						<input type="hidden" name="checkall" value="${requestScope.checkall}">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
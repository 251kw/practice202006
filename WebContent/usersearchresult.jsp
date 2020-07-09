<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
		<form action="./RelayFunction" method="post">
			<%-- リクエストスコープにあるArrayList型のオブジェクトを参照 --%>
			<jsp:useBean id="resultList" scope="request" type="java.util.ArrayList<dto.UserDTO>" />
			<%-- リストにある要素の数だけ繰り返し --%>
			<table style="width: 700px" class="table table-borderd container padding-y-5">
				<c:if test="${requestScope.notselectedalert != null && requestScope.notselectedalert != ''}">
					<tr>
						<td colspan="7" class="color-error text-center"><c:out value="${requestScope.notselectedalert}" /></td>
					</tr>
				</c:if>
				<tr class="bg-light">
					<th>
						<button type="submit" formaction="./AllSelected" name="checkall" value="${requestScope.checkall}">全選択</button>
					</th>
					<th class="text-center">ログインID</th><th class="text-center">ユーザー名</th>
					<th class="text-center">アイコン</th><th class="text-center">プロフィール</th>
					<th class="text-center">更新</th>
					<th class="text-center">削除</th>
				</tr>
				<c:forEach var="resultList" items="${resultList}">
					<tr>
						<%--チェックボックス --%>
						<c:choose>
							<c:when test="${!empty requestScope.checkall}">
								<td class="text-center"><input type="checkbox" checked name="select" value="${resultList.loginId}"></td>
							</c:when>
							<c:otherwise>
								<td class="text-center"><input type="checkbox" name="select" value="${resultList.loginId}"></td>
							</c:otherwise>
						</c:choose>
						<td class="text-center">${resultList.loginId}</td>
						<td class="text-center">${resultList.userName}</td>
						<td class="text-center"><span class="${resultList.icon} pe-2x pe-va"></span></td>
						<td class="text-center">${resultList.profile}</td>
						<td class="text-center">
							<%-- 該当するログインIDを送る --%>
							<button type="submit" name="eloginId" value="${resultList.loginId}">更新</button>
						</td>
						<td class="text-center">
							<%-- 該当するログインIDを送る --%>
							<button type="submit" name="dloginId" value="${resultList.loginId}">削除</button>
						</td>
					</tr>
				</c:forEach>
			</table>
			<table style="width: 700px" class="table container padding-y-5">
				<tr>
					<td colspan="4" class="text-right">
						<button type="submit" class="btn">選択したユーザーを削除</button>
						<%-- 入力情報を送る --%>
						<input type="hidden" name="sloginId" value="${requestScope.sloginId}">
						<input type="hidden" name="suserName" value="${requestScope.suserName}">
						<input type="hidden" name="sicon" value="${requestScope.sicon}">
						<input type="hidden" name="sprofile" value="${requestScope.sprofile}">
					</td>
				</tr>
			</table>
		</form>
		<form action="./TurnSearchInput" method="post">
			<table style="width: 700px" class="table container padding-y-5">
				<tr>
					<td colspan="4" class="text-right">
						<input class="btn" type="submit" value="入力画面に戻る" />
						<input type="hidden" name="sloginId" value="${requestScope.sloginId}">
						<input type="hidden" name="suserName" value="${requestScope.suserName}">
						<input type="hidden" name="sicon" value="${requestScope.sicon}">
						<input type="hidden" name="sprofile" value="${requestScope.sprofile}">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
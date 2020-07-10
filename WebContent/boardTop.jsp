<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Shouter - みんなの心の叫び -</title>
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
	<h4 class="color-main text-center">今の気持ちを叫ぼう</h4>
	<%-- 検索ボタン --%>
	<form action="./TurnSearchInput" method="post">
		<table style="width: 40%" class="table container padding-y-5">
			<tr>
				<td colspan="2" class="text-right">
				<input class="btn" type="submit" value="ユーザー検索" />
				</td>
			</tr>
		</table>
	</form>
	<%-- セッションスコープにある UserDTO型のオブジェクトを参照 --%>
	<jsp:useBean id="user" scope="session" type="dto.UserDTO" />
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./logout" method="post">
				<table class="table table-borderd">
					<tr>
						<td rowspan="2" class="text-center"><span class="${user.icon} pe-3x pe-va"></span></td>
						<td width="256">${user.userName}</td>
						<td><input class="btn btn-light" type="submit" value="ログアウト" /></td>
					</tr>
					<tr>
						<td colspan="2">${user.profile}</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<%-- action 属性にサーブレットを指定 --%>
	<h4 class="color-main text-center">みんなの叫び</h4>
		<form action="./ShoutsRegistDB" method="post">
			<table style="width: 40%" class="table container padding-y-5 text-right">
				<tr>
					<%-- 今の気持ち入力欄の名前は shout --%>
					<td><input class="form-control" type="text" name="shout" value="" size="40"/></td>
					<td><input class="btn" type="submit" value="叫ぶ" /></td>
				</tr>
				<%--入力エラーメッセージ --%>
				<c:if test="${requestScope.alert != null && requestScope.alert != ''}">
					<tr>
						<%-- リクエストスコープの alert の値を出力 --%>
						<td colspan="2" class="color-error text-left"><c:out value="${requestScope.alert}" /></td>
					</tr>
				</c:if>
			</table>
		</form>
		<form action="" method="Get">
			<%-- セッションスコープにあるArrayList型のオブジェクトを参照 --%>
			<jsp:useBean id="shouts" scope="session" type="java.util.ArrayList<dto.ShoutDTO>" />
			<div class="padding-y-5">
				<div style="width: 40%" class="container padding-y-5">
					<%-- リストにある要素の数だけ繰り返し --%>
					<c:forEach var="shout" items="${shouts}">
						<table class="table table-striped table-borderd">
							<tr>
								<td rowspan="2" class="text-center"><span class="${shout.icon} pe-3x pe-va"></span></td>
								<td class="text-center">${shout.userName}</td>
							</tr>
							<tr>
								<td class="text-center">${shout.date}</td>
							</tr>
							<tr>
								<td colspan="2">
									<textarea rows="5" class="form-control">${shout.writing}</textarea>
								</td>
							<tr>
								<td colspan="2" class="text-right"><button type="submit" name="ewriting" value="${shout.writing}">編集</button>
								<button type="submit" name="dwriting" value="${shout.writing}">削除</button></td>
							</tr>
						</table>
					</c:forEach>
				</div>
			</div>
		</form>
	</body>
</html>
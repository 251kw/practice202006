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
						<td><input class="btn" type="submit" value="ログアウト" /></td>
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
			<table style="width:40%" class="table container padding-y-5 text-right">
				<tr>
					<%-- 今の気持ち入力欄の名前は shout --%>
					<td><input autofocus class="form-control" type="text" name="shout" value="" size="40"/></td>
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
		<form action="./ShoutRelayFunction" method="Post">
			<%-- セッションスコープにあるArrayList型のオブジェクトを参照 --%>
			<jsp:useBean id="shouts" scope="session" type="java.util.ArrayList<dto.ShoutDTO>" />
			<div class="padding-y-5">
				<div style="width: 40%" class="container padding-y-5">
					<c:if test="${requestScope.notselectedalert != null && requestScope.notselectedalert != ''}">
							<div class="color-error text-center"><c:out value="${requestScope.notselectedalert}" /></div>
					</c:if>
					<c:if test="${!empty sessionScope.shouts}">
						<table class="table">
							<tr>
								<td><button type="submit" formaction="./AllSelected" name="checkall" value="${requestScope.checkall}">全選択</button></td>
								<td class="text-right"><button type="submit" name="dpush" value="pushed">選択した書き込みを削除</button></td>
							</tr>
						</table>
					</c:if>
					<%-- リストにある要素の数だけ繰り返し --%>
					<c:forEach var="shout" items="${shouts}">
						<table class="table table-striped table-borderd">
							<tr>
								<td rowspan="2" class="text-center">
									<c:if test="${!empty checkall}">
										<input checked type="checkbox" name="select" value="${shout.shoutsId}">
									</c:if>
									<c:if test="${empty checkall}">
										<c:if test="${!empty sessionScope.select}">
											<c:set var="hitflag" value="zero" />
											<c:forEach var="sessionselect" items="${sessionScope.select}">
												<c:if test="${shout.shoutsId == sessionselect && endflg != 'one'}">
													<input checked type="checkbox" name="select" value="${shout.shoutsId}">
													<c:set var="hitflag" value="one" />
												</c:if>
											</c:forEach>
											<c:if test="${hitflag != 'one'}">
												<input type="checkbox" name="select" value="${shout.shoutsId}">
											</c:if>
										</c:if>
										<c:if test="${empty sessionScope.select}">
											<input type="checkbox" name="select" value="${shout.shoutsId}">
										</c:if>
									</c:if>
								</td>
								<td rowspan="2" class="text-center">
									<span class="${shout.icon} text-center pe-3x pe-va"></span>
								</td>
								<td class="text-center">${shout.userName}</td>
							</tr>
							<tr>
								<td class="text-center">${shout.date}</td>
							</tr>
							<tr>
								<td colspan="3" style="height:100px">
									${shout.writing}
									<input type="hidden" name="checkall" value="${requestScope.checkall}">
								</td>
							<tr>
								<td colspan="3" class="text-right"><button type="submit" name="eshoutsId" value="${shout.shoutsId}">編集</button>
							</tr>
						</table>
					</c:forEach>
				</div>
			</div>
		</form>
	</body>
</html>
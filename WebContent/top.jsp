<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE htm>
<html lang="ja">
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="./css/skyblue.css">
		<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
		<link rel="stylesheet" href="./css/helper.css">
	</head>
	<body>
		<div class="bg-success padding-y-5">
			<div class="padding-y-5 text-center">
				<h1>
					<strong> Shouter</strong> <span class="icon-speaker pe-1x pe-va"></span>
				</h1>
			</div>
		</div>

		<%--　セッションスコープにある　UserDTOオブジェクトを参照 --%>
		<jsp:useBean id="user" scope="session" type="dto.UserDTO" />
		<div class="padding-y-5">
			<div style="width: 40%" class="container padding-y-5">
				<%-- action属性にサーブレット指定 --%>
				<form action="./logout" method="post">
					<table class="table table-borderd">

						<tr>
							<td rowspan="2" class="text-center"><span
								class="${user.icon} pe-3x pe-va"></span></td>
							<td width="256">${user.userName}</td>
							<td><input class="btn" type="submit" value="ログアウト" /></td>

						</tr>
						<tr>
							<td colspan="2">${user.profile }</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

		<%-- action 属性にサーブレット指定 --%>
		<form action="./bbs" method="post">
			<table>
				<tr>
					<td class="color-black text-center">————今の気持ちを叫ぼう————</td>
				</tr>
			</table>
			<table class="shout">
				<tr>
					<td><input class="form-control" type="text" name="shout"
						value="" size="20" /></td>
					<td><input class="btn" type="submit" value="叫ぶ" /></td>
				</tr>
				<%-- リクエストスコープにalertがあれば --%>
				<c:if
					test="${requestScope.alert2 != null && requestScope.alert2 != ''}">
					<tr>
						<%-- リクエストスコープのalertの値を出力 --%>
						<td colspan="2" class="color-error text-center"><c:out
								value="${requestScope.alert2}" /></td>
					</tr>
				</c:if>


			</table>
		</form>
		<form action="./userSearchInput.jsp" method="post">
			<table class="table table-borderd" style="width:400">
				<tr>
					<td ><div>
							<input class="btn" type="submit" value="ユーザー検索" />
						</div></td>
				</tr>
			</table>
		</form>

		<%-- セッションスコープにあるArrayList型のオブジェクトを参照 --%>
		<jsp:useBean id="shouts" scope="session"
			type="java.util.ArrayList<dto.ShoutDTO>" />
		<div class="padding-y-5">
			<div style="width: 40%" class="container padding-y-5">
				<%-- リストにある要素の数だけ繰り返し --%>
				<c:forEach var="shout" items="${shouts}">
					<table class="table table-striped table-bordered">
						<tr>
							<td rowspan="2" class="text-center"><span
								class="${shout.icon} pe-3x pe-va"></span></td>
							<td>${shout.userName}</td>
						</tr>
						<tr>
							<td>${shout.date}</td>
						</tr>
						<tr>
							<td colspan="2"><textarea rows="5" class="form-control">${shout.writing}</textarea>
							</td>
						</tr>
					</table>
				</c:forEach>
			</div>
		</div>
</body>
</html>
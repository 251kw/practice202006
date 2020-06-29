<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang ="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter-みんなの心の叫び-</title>
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

<div class="padding-y-5 text-center" >ログインユーザ情報</div>

<%-- セッションスコープにある　UserDTO型のオブジェクトを参照 --%>
<jsp:useBean id="user" scope="session" type="dto.UserDTO" />
<div class="padding-y-5">
	<div style="width: 40%" class="container padding-y-5">
		<%-- action 属性にサーブレットを指定 --%>
		<form action="./logout" method="post">
			<table class ="table table-bordered">
				<tr>
					<td rowspan="2" class="text-center"><span class="${user.icon} pe-3x pe-va"></span></td>
					<td width="256">${user.userName}</td>
					<td><input class="btn btn-lidht" type="submit" value="ログアウト" /></td>
				</tr>
				<tr>
					<td colspan="2">${user.profile}</td>
				</tr>
			</table>
		</form>
	</div>
</div>

<h5 class="padding-y-5 text-center">今の気持ちを叫ぼう</h5>

<%-- 属性にサーブレットを追加 --%>
<form action="./bbs" method="post">
	<table style="width: 40%" class="container padding-y-5">
		<tr>
			<%-- 今の気持ち入力欄はshout --%>
			<td><input class="form-control" type="text" name="shout" value="" size="60" /></td>
			<td><input class="btn" type="submit" value="叫ぶ" /></td>
		</tr>

		<%-- 未記入のときは --%>
	<c:if test="${requestScope.alert != null && requestScope.alert != ''}">
		<tr>
			<%-- リクエストスコープのalertの値を出力 --%>
			<td colspan="2" class="color-error text-left">
				<c:out value="${requestScope.alert}" />
			</td>
		</tr>
	</c:if>
	</table>


</form>

<%-- セッションスコープスコープにあるArrayList型のオブジェクトを参照 --%>
<jsp:useBean id="shouts" scope="session" type="java.util.ArrayList<dto.ShoutDTO>" />
<div class="padding-y-5">
	<div style="width: 40%" class="container padding-y-5">
	<%-- リストにある要素の数だけ繰り返し --%>
	<c:forEach var="shout" items="${shouts}">
		<table class="table table-striped table-borderd">
			<tr>
				<td rowspan="2" class="text-center"><span class="${shout.icon} pe-3x pe-va"></span></td>
				<td>${shout.userName}</td>
			</tr>
			<tr>
				<td>${shout.date}</td>
			</tr>
			<tr>
				<td colspan="2"><textarea rows="5" class="form-control">${shout.writing}</textarea></td>
			</tr>
		</table>
	 </c:forEach>
	</div>
</div>


</body>
</html>
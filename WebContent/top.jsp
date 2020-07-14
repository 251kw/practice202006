<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date"%>
<%@ page import="util.Check" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>shouter-みんなの叫び-</title>
		<link rel="stylesheet" href="./css/skyblue.css">
		<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
		<link rel="stylesheet" href="./css/helper.css">
		<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
		<link rel="stylesheet" href="./css/button.css">
	</head>
	<body>

		<%---------------------------------------------------------------------------------%>
		<div class="bg-success padding-y-5">
			<div class="padding-y-5 text-center">
				<h1>Shouter<span class="icon-speaker pe-1x pe-va"></span></h1>
			</div>
		</div>
		<div class="padding-y-5 text-center">
			<div style="width: 40%" class="container padding-y-5 text-left">
				<strong class="color-main">ログインユーザー情報</strong>
			</div>
		</div>
		<%---------------------------------------------------------------------------------%>

		<%-- リクエストスコープにあるUserDTO型のオブジェクトを参照 --%>
		<jsp:useBean id="user" scope="session" type="dto.UserDTO" />
		<div class="padding-y-5">
			<div style="width: 40%" class="container padding-y-5">
				<%-- action属性にサーブレットを指定 --%>
				<%--form action="./logout" method="post"--%>
					<table class="table table-bordered">
						<tr>
							<td rowspan="2" class="text-center"><span
								class="${user.icon} pe-3x pe-va"></span></td>
							<td width="256">${user.userName}</td>
							<td><form action="./logout" method="post"><input class="btn btn-sm" type="submit" value="ログアウト" /></form>
								<form action="./sis" method="post"><input class="btn btn-sm" type="submit" value="検索" name="btn" /></form></td>
						</tr>
						<tr>
							<td colspan="2">${user.profile}</td>
						</tr>
					</table>
			</div>
		</div>
		<div class="padding-y-5">
			<div style="width: 40%" class="container padding-y-5 text-left">
				<strong class="color-main">今の気持ちを叫ぼう</strong>
			</div>
		</div>
		<div class="padding-y-5 text-center">
			<div style="width: 40%" class="container padding-y-5 text-center">

				<%-- action属性にサーブレットを指定 --%>
				<form method="post" action="./top">
					<table class="table">
						<tr>
							<%--今の気持ち入力欄の名前は shout--%>
							<td><input class="form-control" type="text" name="shout" value="" size="60" /></td>
							<td><input type="submit" class="btn-gradient-3d-simple" name="btn" value="叫ぶ"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>

		<%---------------------------------------------------------------------------------%>
		<%-- リクエストスコープにalertがあれば --%>
		<c:if test="${requestScope.alert != null && requestScorpe.alert !=''}">
		<div class="padding-y-5">
			<div style="width: 40%" class="container padding-y-5 text-left">
					<%--リクエストスコープのalertの値を出力 --%>
					<strong class="color-error text-center">
					<c:out value="${requestScope.alert}" /></strong>
			</div>
		</div>
		</c:if>
		<form action="./drs" method="get">
		<div class="padding-y-5 text-center">
			<div style="width: 40%" class="container padding-y-5 text-left">
				<strong class="color-main">みんなの叫び</strong>&nbsp;&nbsp;&nbsp;<input type="submit" class="btn-flat-dashed-filled-red" name="btn" value="☑選択項目を削除">
			</div>
		</div>
		<%---------------------------------------------------------------------------------%>

		<%--セッションスコープにあるArrayList型のオブジェクトを参照 --%>
		<jsp:useBean id="shouts" scope="session"
			type="java.util.ArrayList<dto.ShoutDTO>" />
		<div class="padding-y-5">
			<div style="width: 40%" class="container padding-y-5">
				<c:forEach var="shout" items="${shouts}">
					<table class="table table-bordered">
						<tr>
							<td rowspan="2" class="text-center">
								<span class="${shout.icon} pe-3x pe-va"></span>
							</td>
							<td>${shout.userName}</td>
							<td><label class="fancy-checkbox">
								<input type="checkbox" name="shoutId" value="${shout.shoutsId}" ${Check.checkBox(String.valueOf(shout.shoutsId), shoutIds)}><span>${String.valueOf(shout.shoutsId)}</span>
								</label></td>
						</tr>
						<tr>
							<td>${shout.date}</td>
							<td><a href="#" class="btn-flat-dashed-filled-yellow">編集</a></td>
						</tr>

						<tr>
							<td colspan="2" class="text-center">
							<div class="balloon4">
  							<p>${shout.writing}</p>
							</div></td></tr>
					</table>
				</c:forEach>
			</div>
		</div>
		</form>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>トップ</title>
	<link rel="stylesheet" href="./css/skyblue.css">
	<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
	<link rel="stylesheet" href="./css/helper.css">
</head>
<body onload="document.getElementById('firstinput').focus();">
	<div class="bg-success padding-y-10">
		<div class="text-center ">
			<h1>
				Shouter <span class="icon-speaker pe-2x pe-va"></span>
			</h1>
		</div>
	</div>
	<br>
	<div class="text-center padding-y-5">
		<div class="boxContainer">
			<div class="box">
				<h4>ログインユーザー情報</h4>
			</div>
			<div class="box">
				<form action="search_input.jsp" method="post">
					<input class="btn btn-sm btn-warning" type="submit" value="検索">
				</form>
			</div>
		</div>
	</div>
	<%-- セッションスコープにある UserdTO型のオブジェクトを参照 --%>
	<jsp:useBean id="user" scope="session" type="dto.UserDTO" />
	<div style="width: 40%" class="container padding-y-5">
		<%-- action 属性にサーブレットを指定 --%>
		<form action="./logout" method="post">
			<table class="table table-bordered  padding-y-5 ">
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
	<br>
	<br>
	<div class="text-center padding-y-5">
		<h4>今の気持ちを叫ぼう</h4>
	</div>
	<%-- action 属性にサーブレットを指定 --%>
	<form action="./bbs" method="post">
		<table style="width: 400px;" class="table container padding-y-5">
			<tr>
				<%-- 今の気持ち入力欄の名前は shout --%>
				<td class="text-center"><input class="form-contorol"
				type="text" name="shout" value="" id="firstinput" size="60" /></td>
				<td><input class="btn" type="submit" value="叫ぶ" /></td>
			</tr>
			<%-- リクエストスコープにalertがあれば --%>
			<c:if test="${requestScope.alert != null && requestScope.alert != ' '}">
				<tr>
					<%-- リクエストスコープのalertの値を出力 --%>
					<td colspan="2" class="color-error text-left">
					<c:out value="${requestScope.alert}" /></td>
				</tr>
			</c:if>
		</table>
	</form>
	<%-- セッションスコープにあるArrayLisst型のオブジェクトを参照 --%>
	<jsp:useBean id="shouts" scope="session" type="java.util.ArrayList<dto.ShoutDTO>" />
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">
			<%-- リストにある要素の数だけ繰り返し --%>
			<c:forEach var="shout" items="${shouts}">
				<table class="table table-striped table-bordered">
					<tr>
						<td rowspan="2" class="text-center">
							<span class="${shout.icon} pe-3x pe-va"></span>
						</td>
						<td>${shout.userName}</td>
					</tr>
					<tr>
						<td>${shout.date}</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="mybox">
								${shout.writing}
							</div>
						</td>
					</tr>
				</table>
			</c:forEach>
		</div>
	</div>
</body>
</html>
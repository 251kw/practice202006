<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter - ログイン -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>

<%
	session.invalidate();
%>
<div class="bg-success padding-y-30">
	<div class="container padding-y-5">
		<h2 class="text-center">Shouter&nbsp;<span class="icon-speaker pe-2x pe-va"></span></h2>
	</div>
</div>

<%-- 新規登録ボタン --%>
<form action="UserAddInput.jsp" method="post">
<table style="width: 400px" class="table container padding-y-5">
	<tr>
		<td colspan="2" class="text-right">
			<input class="btn" type="submit" value="新規登録はこちらから" />
		</td>
	</tr>
</table>
</form>
<h5 class="text-center padding-y-5">ログインIDとパスワードを入力してください</h5>
<%-- action 属性にサーブレットを指定 --%>
<form action="./login" method="post" >
	<table style="width: 400px" class="table container padding-y-5">
		<tr>
			<%-- ログインID入力欄の名前はloginId --%>
			<td class="color-main text-left">ログインID</td>
			<td class="text-left"><input class="form-control" type="text" name="loginId" value="" size="20" /></td>
		</tr>
		<tr>
			<%-- パスワード入力欄の名前はpassword --%>
			<td class="color-main text-left">パスワード</td>
			<td class="text-left"><input class="form-control" type="password" name="password" value="" size="20" /></td>
		</tr>
		<tr>
			<td colspan="2" class="text-right"><input class="btn" type="submit" value="ログイン" /></td>
		</tr>
		<%-- リクエストスコープにalertがあれば --%>
		<c:if
			test="${requestScope.alert != null && requestScope.alert != ''}">
			<tr>
				<%-- リクエストスコープの alert の値を出力 --%>
				<td colspan="2" class="color-error text-left"><c:out value="${requestScope.alert}" /></td>
			</tr>
		</c:if>
	</table>
</form>


</body>
</html>
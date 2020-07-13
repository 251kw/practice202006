<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="./css/skyblue.css">
		<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
		<link rel="stylesheet" href="./css/helper.css">
		<title>ログイン画面</title>
	</head>
	<body>
		<div class="bg-main padding-y-5">
			<div class="padding-y-5 text-center">
				<font size="6"><strong>&nbsp;Shouter&nbsp;</strong><span class="icon-speaker pe-2x pe-va"></span></font>
			</div>
		</div>
		<%-- 入力項目をLoginServletにsubmit送信 --%>
		<div class="padding-y-5">
			<form action="./login" method="post">
				<table style="width: 40%" class="container padding-y-5 table">
					<caption>
						<nobr><span class="color-main text-left"><font size="4">ログインIDとパスワードを入力してください</font></span></nobr>
					</caption>
					<tr>
						<%-- ログインID入力欄の名前はloginId --%>
						<td class="color-main text-left">ログインID</td>
						<td class="text-left"><input class="form-control" type="text" name="loginId" value="" size="30" /></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前はpassword --%>
						<td class="color-main text-left">パスワード</td>
						<td class="text-left"><input class="form-control" type="password" name="password" value="" size="30" /></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn" type="submit" value="ログイン" /></td>
					</tr>
					<%-- リクエストスコープにalertがあれば --%>
					<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
						<tr>
							<%-- リクエストスコープのalertの値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out value="${requestScope.alert}" /></td>
						</tr>
					</c:if>
				</table>
			</form>
		</div>
		<%-- 新規会員登録へ移動(./userInsert.jspへ遷移) --%>
		<div class="container padding-y-5" style="width: 15%">
			<form action="./userInsert.jsp" method="post">
				<span class="text-right"><input class="btn" type="submit" value="新規会員登録はこちら"></span>
			</form>
		</div>
	</body>
</html>
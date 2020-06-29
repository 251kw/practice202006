<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>完了画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<%
	//文字化け対策
	request.setCharacterEncoding("UTF-8");
	String userName = (String) session.getAttribute("userName");
	String loginId = (String) session.getAttribute("loginId");
	String password = (String) session.getAttribute("password");
	String icon = (String) session.getAttribute("icon");
	String profile = (String) session.getAttribute("profile");
%>
<body>
	<div class="bg-success padding-y-5">
		<div class="padding-y-5 text-center">
			<strong>登録完了</strong>
		</div>
	</div>
	<table border="1" class="complete">
		<tr>
			<td>名前</td>
			<td><%=userName%></td>
		</tr>
		<tr>
			<td>ログインID</td>
			<td><%=loginId%></td>
		</tr>
		<tr>
			<td>パスワード</td>
			<td><%=password%></td>
		</tr>
		<tr>
			<td>アイコン</td>
			<td><span class="<%=icon%> pe-2x pe-va"></span></td>
		</tr>
		<tr>
			<td>プロフィール</td>
			<td><%=profile%></td>
		</tr>
	</table>

	<table>
		<%
			session.invalidate();
		%>
		<tr>
			<td><input type="button" onclick="location.href='./index.jsp'"
				value="戻る" class="btn"></td>
		</tr>
	</table>
</body>
</html>
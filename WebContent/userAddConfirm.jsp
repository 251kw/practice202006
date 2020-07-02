<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%

%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登録確認</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="padding-y-5 text-center">
			<strong>登録確認</strong>
		</div>
	</div>


	<form action="./uar" method="post">
		<%
			//文字化け対策
			request.setCharacterEncoding("UTF-8");

			String icon = (String) request.getAttribute("icon");
			String loginId = (String) request.getAttribute("loginId");
			String userName = (String) request.getAttribute("userName");
			String password = (String) request.getAttribute("password");
			String profile = (String) request.getAttribute("profile");

		%>
		<input type="hidden" name="suserName" value="<%=userName%>">
		<input type="hidden" name="spassword" value="<%=password%>">
		<input type="hidden" name="sloginId" value="<%=loginId%>">
		<input type="hidden" name="icon" value="<%=icon%>">
		<input type="hidden" name="sprofile" value="<%=profile%>">

		<table border="1" style="border-collapse: collapse">
			<tr>
				<td class="text-left">名前</td>
				<td class="text-right"><%=userName%></td>
			</tr>
			<tr>
				<td class="text-left">ログインID</td>
				<td class="text-right"><%=loginId%></td>
			</tr>
			<tr>
				<td class="text-left">パスワード</td>
				<td class="text-right"><%=password%></td>
			</tr>
			<tr>
				<td class="text-left">アイコン</td>
				<td><span class="<%=icon%> pe-3x pe-va text-right"></span></td>
			</tr>
			<tr>
				<td class="text-left">プロフィール</td>
				<td class="text-right"><%=profile%></td>
			</tr>
			<tr>
				<td class="color-main text-right"><nobr>上記の内容でよろしいですか</nobr></td>
			</tr>
		</table>
		<table style="width: 300px" class="table">

			<tr>
				<td colspan="4" class="text-center"><input type="submit"
					value="はい" class="btn"></td>
			</tr>
		</table>
	</form>
	<form action="./userAddInput.jsp" method="post">
	<input type="hidden" name="suserName" value="<%=userName%>">
	<input type="hidden" name="spassword" value="<%=password%>">
	<input type="hidden" name="sloginId" value="<%=loginId%>">
	<input type="hidden" name="icon" value="<%=icon%>">
	<input type="hidden" name="sprofile" value="<%=profile%>">
	<table border="1" style="border-collapse: collapse">
		<tr>
		<td colspan="4" class="text-center"><input type="submit"
			value="いいえ" class="btn"></td>
		</tr>
	</table>
	</form>
</body>
</html>
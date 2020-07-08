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
		String icon = (String) request.getAttribute("icon");
		String loginId = (String) request.getAttribute("loginId");
		String userName = (String) request.getAttribute("userName");
		String password = (String) request.getAttribute("password");
		String profile = (String) request.getAttribute("profile");
	%>
	<body>
		<div class="bg-success padding-y-5">
			<div class="padding-y-5 text-center">
				<strong>登録完了</strong>
			</div>
		</div>

		<div class="padding-y-5">
			<div style="width: 60%" class="container padding-y-5">
				<table   class="table table-striped table-bordered">
					<tr>
						<td width="80" height="10">名前</td>
						<td width="80" height="10"><%=userName%></td>
					</tr>
					<tr>
						<td width="80" height="10">ログインID</td>
						<td width="80" height="10"><%=loginId%></td>
					</tr>
					<tr>
						<td width="80" height="10">パスワード</td>
						<td width="80" height="10"><%=password%></td>
					</tr>
					<tr>
						<td width="80" height="10">アイコン</td>
						<td width="80" height="10"><span class="<%=icon%> pe-2x pe-va"></span></td>
					</tr>
					<tr>
						<td width="80" height="10">プロフィール</td>
						<td width="80" height="10"><%=profile%></td>
					</tr>
				</table>
			</div>
		</div>
		<table>
			<tr>
				<td><input type="button" onclick="location.href='./index.jsp'"
					value="戻る" class="btn"></td>
			</tr>
		</table>
	</body>
</html>
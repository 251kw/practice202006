<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>更新確認</title>
		<link rel="stylesheet" href="./css/skyblue.css">
		<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
		<link rel="stylesheet" href="./css/helper.css">
	</head>
	<body>
		<%
			//送信情報の取得
			String userName = (String)request.getAttribute("userName");
			String icon = (String) request.getAttribute("sicon");
			String loginId = (String) request.getAttribute("loginId");
			String password = (String) request.getAttribute("password");
			String profile = (String) request.getAttribute("profile");
			//現在ログイン中のユーザーのログインID
			String sloginId = (String) request.getAttribute("sloginId");
		%>
		<div class="bg-success padding-y-5">
			<div class="padding-y-5 text-center">
				<strong>更新確認</strong>
			</div>
		</div>
		<form action="./uur" method="post">
			<input type="hidden" name="userName" value="<%=userName%>">
			<input type="hidden" name="password" value="<%=password%>">
			<input type="hidden" name="loginId" value="<%=loginId%>">
			<input type="hidden" name="icon" value="<%=icon%>">
			<input type="hidden" name="profile" value="<%=profile%>">
			<input type="hidden" name="sloginId" value="<%=sloginId%>">
			<div class="padding-y-5">
				<div style="width: 40%" class="container padding-y-5">
					<table   class="table table-striped table-bordered">
						<tr>
							<td  width="50" height="10">名前</td>
							<td  width="50" height="10"><%=userName%></td>
						</tr>
						<tr>
							<td   width="50" height="10">パスワード</td>
							<td   width="50" height="10"><%=password%></td>
						</tr>
						<tr>
							<td  width="50" height="10">アイコン</td>
							<td><span class="<%=icon%> pe-2x pe-va text-right"></span></td>
						</tr>
						<tr>
							<td  width="50" height="10">プロフィール</td>
							<td  width="50" height="10"><%=profile%></td>
						</tr>
					</table>
					<table   class="table table-striped table-bordered">
						<tr>
							<td class="color-main text-center"><nobr>上記の内容に変更してよろしいですか</nobr></td>
						</tr>
					</table>
				</div>
			</div>
			<table>
				<tr>
					<td colspan="2" class="text-center"><input type="submit"value="はい" class="btn"></td>
				</tr>
			</table>
		</form>
		<form action="./userUpdateInput.jsp" method="post">
			<input type="hidden" name="suserName" value="<%=userName%>">
			<input type="hidden" name="spassword" value="<%=password%>">
			<input type="hidden" name="sloginId" value="<%=loginId%>">
			<input type="hidden" name="sicon" value="<%=icon%>">
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
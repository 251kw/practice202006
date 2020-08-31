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

			<div class="padding-y-5">
				<div style="width: 40%" class="container padding-y-5">
					<table   class="table table-striped table-bordered">
						<tr>
							<td  width="50" height="10">名前</td>
							<td  width="50" height="10"><%=userName%></td>
						</tr>
						<tr>
							<td  width="50" height="10">ログインID</td>
							<td  width="50" height="10"><%=loginId%></td>
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
						<tr>
							<td class="color-main text-right"><nobr>上記の内容でよろしいですか</nobr></td>
						</tr>
					</table>
					<table >
						<tr>
							<td colspan="4" class="text-center"><input type="submit"
								value="はい" class="btn"></td>
						</tr>
					</table>
				</div>
			</div>
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
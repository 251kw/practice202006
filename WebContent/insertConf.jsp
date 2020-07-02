<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="controller.InsertCheckServlet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<title>ユーザ登録確認画面</title>
</head>
<body>
	<div class="bg-accyell padding-y-5">
		<div class="padding-y-5 text-center">
			<font size="5"><span class="icon-diamond pe-2x pe-va"></span><strong>&nbsp;Shouter&nbsp;</strong><span
				class="icon-diamond pe-2x pe-va"></span></font>
		</div>
	</div>

	<%-- action属性にサーブレットを指定 --%>
	<div class="padding-y-5">
		<form action="./registraction" method="post">
			<table style="width: 40%" class="container padding-y-5 table">
				<tr>
					<td class="none" nowrap><span class="color-main text-left"><font
							size="4">新規会員登録【確認画面】</font></span></td>
				</tr>
				<%//文字化け防止
				request.setCharacterEncoding("UTF-8");
				%>
				<tr>
					<%-- ログインID入力欄の名前はLoginId --%>
					<td class="color-main text-left">ログインID</td>
					<td class="text-left">${loginId}</td>
				</tr>
				<tr>
					<%-- パスワード入力欄の名前はpassword --%>
					<td class="color-main text-left">パスワード</td>
					<td class="text-left">${password}</td>
				</tr>
				<tr>
					<%-- 名前入力欄の名前はuserName --%>
					<td class="color-main text-left">氏名</td>
					<td class="text-left">${userName}</td>
				</tr>
				<tr>
					<%-- icon入力欄の名前はicon --%>
					<td class="color-main text-left">アイコン</td>
					<td class="text-left">
					<span class="${icon} pe-2x pe-va"></span></td>
				</tr>
				<tr>
					<%-- プロフィール入力欄の名前はprofile --%>
					<td class="color-main text-left">プロフィール</td>
					<td class="text-left">${profile}</td>
				</tr>
				<tr>
					<%-- <td class="none" nowrap><span class="color-main text-left"><font
							size="4">新規会員登録</font></span></td> --%>
					<td class="color-error text-left"><font size="3">上記の内容でよろしいでしょうか？</font></td>
					<td></td>
				</tr>
				<tr>
					<%-- ボタン --%>
					<td class="none text-right" colspan="2"><input class="btn"
						type="submit" value="OK" /></td>
				</tr>
			</table>
			<input type="hidden" name="loginId" value="${param.loginId}">
			<input type="hidden" name="password" value="${param.password}">
			<input type="hidden" name="userName" value="${param.userName}">
			<input type="hidden" name="icon" value="${param.icon}">
			<input type="hidden" name="profile" value="${param.profile}">
		</form>

		<%-- 修正の場合は入力情報保持したままinsert.jspに戻る --%>
		<form action="insert.jsp" method="post">
			<table style="width: 40%" class="container padding-y-5 table">
				<tr>
					<td class="none text-right" colspan="2"><input class="btn" type="submit"
						value="修正する" /></td>
				</tr>
			</table>
			<input type="hidden" name="loginId" value="${param.loginId}">
			<input type="hidden" name="password" value="${param.password}">
			<input type="hidden" name="userName" value="${param.userName}">
			<input type="hidden" name="icon" value="${param.icon}">
			<input type="hidden" name="profile" value="${param.profile}">
		</form>
	</div>
</body>
</html>
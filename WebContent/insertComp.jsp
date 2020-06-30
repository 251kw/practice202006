<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dto.UserDTO"%>
<%@ page import="controller.insertCheckServlet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="./css/skyblue.css">
	<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
	<link rel="stylesheet" href="./css/helper.css">
	<title>ユーザ登録完了画面</title>
</head>
<body>
<div class="bg-accyell padding-y-5">
	<div class="padding-y-5 text-center">
		<font size="5"><span class="icon-diamond pe-2x pe-va"></span><strong>&nbsp;Welcome to Shouter !&nbsp;</strong><span class="icon-diamond pe-2x pe-va"></span></font>
	</div>
</div>

<jsp:useBean id="userDTO" scope="session" type="dto.UserDTO" />
<div class="padding-y-5">
<form action="./index.jsp"   method="post">
	<table style="width: 40%" class="container padding-y-5 table">
		<tr>
			<td class="none" nowrap><span class="color-main text-left"><font size="4">新規会員登録【完了画面】</font></span></td>
		</tr>
		<tr>
			<%-- ログインID入力欄の名前はLoginId --%>
			<td class="color-main text-left">ログインID</td>
			<td class="text-left">${userDTO.loginId}</td>
		</tr>
		<tr>
			<%-- パスワード入力欄の名前はpassword --%>
			<td class="color-main text-left">パスワード</td>
			<td class="text-left">${userDTO.password}</td>
		</tr>
		<tr>
			<%-- 名前入力欄の名前はuserName --%>
			<td class="color-main text-left">氏名</td>
			<td class="text-left">${userDTO.userName}</td>
		</tr>
		<tr>
			<%-- icon入力欄の名前はicon --%>
			<td class="color-main text-left">アイコン選択</td>
			<td class="text-left">
			<span class="${userDTO.icon} pe-2x pe-va"></span></td>
		</tr>
		<tr>
			<%-- プロフィール入力欄の名前はprofile --%>
			<td class="color-main text-left">プロフィール</td>
			<td class="text-left">${userDTO.profile}</td>
		</tr>
		<%-- 登録して登録完了結果引っ張ってくる --%>
		<%
		%>
		<c:if
			test="${result == true}">
				<tr>
				<td nowrap><span class="color-error text-left"><font size="3">上記の内容で登録しました！</font></span></td>
				<td></td>
				</tr>
		</c:if>
		<c:if
			test="${result == false}">
				<tr>
					<td nowrap><span class="color-error text-left">
					<font size="3">登録時にエラーが発生しました。最初からやり直してください。</font></span></td>
					<td></td>
				</tr>
			</c:if>
		<tr>
			<%-- ボタン --%>
			<td class="none text-right" colspan="2" class="text-right"><input class="btn" type="submit" value="TOPに戻る" /></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>
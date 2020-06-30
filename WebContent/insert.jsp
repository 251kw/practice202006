<%@page import="java.net.URLDecoder"%>
<%@ page import="com.sun.javafx.runtime.SystemProperties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dto.errorDTO"%>
<%@ page import="dto.CookieDTO"%>
<%@ page import="dto.UserDTO"%>
<%@ page import="dto.CookieDTO" %>
<%@ page import="controller.CookieServat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<title>ユーザ登録画面</title>
</head>
<body>
	<div class="bg-accyell padding-y-5">
		<div class="padding-y-5 text-center">
			<font size="5"><span class="icon-diamond pe-2x pe-va"></span><strong>&nbsp;Shouter&nbsp;</strong><span
				class="icon-diamond pe-2x pe-va"></span></font>
		</div>
	</div>

	<%-- <jsp:useBean id="errorDTO" scope="session" type="dto.errorDTO" /> 明言しなくても${}で書くとセッションの中身順番に見ていってくれるみたい--%>
	<%-- 入力情報をinsertCheckに飛ばす --%>
	<div class="padding-y-5">
		<form action="./inCheck" method="post">
			<table style="width: 40%" class="container padding-y-5 table">
				<tr>
					<td class="none" nowrap><span class="color-main text-left"><font
							size="4">新規会員登録</font></span></td>
				</tr>
				<% request.setCharacterEncoding("Shift_JIS");%>
				<% %>
				<tr>
					<%-- ログインID入力欄の名前はLoginId --%>
					<td class="color-main text-left">ログインID
					<br><font size="2" color="color-error">※英数字で入力してください</font></td>
					<td class="text-left"><input class="form-control" type="text"
						name="loginId"
						value=""
						size="30" maxlength="15" />
					<c:if test="${errorDTO != null && errorDTO != ''}">
						<br><font size="2" color="color-error">${errorDTO.errMsId}</font>
					</c:if>
					<c:if test="${errorDTO != null && errorDTO != ''}">
						<br><font size="2" color="color-error">${errorDTO.errId}</font>
					</c:if>
					<c:if test="${errorDTO != null && errorDTO != ''}">
						<font size="2" color="color-error">${errorDTO.errDepMs}</font>
					</c:if>
					</td>
				</tr>
				<tr>
					<%-- パスワード入力欄の名前はpassword --%>
					<td class="color-main text-left">パスワード
					<br><font size="2" color="color-error">※英数字で入力してください</font></td>
					<td class="text-left"><input class="form-control" type="password"
						name="password"
						value=""
						size="30" maxlength="15" />
					<c:if test="${errorDTO != null && errorDTO != ''}">
						<br><font size="2" color="color-error">${errorDTO.errMsPass}</font>
					</c:if>
					<c:if test="${errorDTO != null && errorDTO != ''}">
						<br><font size="2" color="color-error">${errorDTO.errPass}</font>
					</c:if>
					</td>
				</tr>
				<tr>
					<%-- 名前入力欄の名前はuserName --%>
					<td class="color-main text-left">氏名</td>
					<td class="text-left"><input class="form-control" type="text"
						name="userName" value="<%= request.getParameter("uName")%>"
						size="30" maxlength="100" />
					<c:if test="${errorDTO != null && errorDTO != ''}">
						<br><font size="2" color="color-error">${errorDTO.errMsUname}</font>
					</c:if>
					</td>
				</tr>
				<tr>
					<%-- icon入力欄の名前はicon --%>
					<td class="color-main text-left">アイコン</td>
					<td class="text-left">
						<%-- 属性値checked="checked" --%> <br> <input
						class="form-control" type="radio" name="icon" value="icon-sun"
						size="30" checked="checked" /><span class="icon-sun pe-2x pe-va"></span>
						<br> <input class="form-control" type="radio" name="icon"
						value="icon-umbrella" size="30" /><span
						class="icon-umbrella pe-2x pe-va"></span> <br> <input
						class="form-control" type="radio" name="icon" value="icon-moon"
						size="30" /><span class="icon-moon pe-2x pe-va"></span>
					<c:if test="${errorDTO != null && errorDTO != ''}">
						<br><font size="2" color="color-error">${errorDTO.errMsIcon}</font>
					</c:if>
					</td>
				</tr>
				<tr>
					<%-- プロフィール入力欄の名前はprofile --%>
					<td class="color-main text-left">プロフィール</td>
					<td class="text-left"><input class="form-control" type="text"
						name="profile" value="<%= request.getParameter("prof")%>"
						size="30" maxlength="200" />
					<c:if test="${errorDTO != null && errorDTO != ''}">
						<br><font size="2" color="color-error">${errorDTO.errMsProf}</font>
					</c:if>
					</td>
				</tr>
				<tr>
					<%-- ボタン --%>
					<td colspan="2" class="text-right"><input class="btn"
						type="submit" value="OK" /></td>
				</tr>
			</table>
		</form>
		<form action="index.jsp" method="post">
			<table style="width: 40%" class="container padding-y-5 table">
				<tr>
					<td class="none text-right"><input class="btn" type="submit"
						value="TOPへ戻る" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
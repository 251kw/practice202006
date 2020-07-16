<%@page import="java.net.URLDecoder"%>
<%@ page import="com.sun.javafx.runtime.SystemProperties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="./css/skyblue.css">
		<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
		<link rel="stylesheet" href="./css/helper.css">
		<title>ユーザ検索画面</title>
	</head>
	<body>
		<div class="bg-accyell padding-y-5">
			<div class="padding-y-5 text-center">
				<font size="5"><span class="icon-diamond pe-2x pe-va"></span><strong>&nbsp;Shouter&nbsp;</strong><span
					class="icon-diamond pe-2x pe-va"></span></font>
			</div>
		</div>
		<%-- 入力情報をsearchServletに飛ばす --%>
		<div class="padding-y-5">
			<form action="./searchSrv" method="post">
				<table style="width: 40%" class="container padding-y-5 table">
					<tr>
						<td class="none" nowrap><span class="color-main text-left"><font size="4">検索項目を選択してください</font></span></td>
					</tr>
					<%//文字化け防止
					request.setCharacterEncoding("UTF-8");
					%>
					<tr>
						<%-- ログインID入力欄の名前はLoginId --%>
						<td class="color-main text-left">ログインID<br><font size="2" color="color-error">※半角英数字で入力してください</font></td>
						<td class="text-left">
							<input class="form-control" type="text" name="loginId" value="${param.loginId}" size="30" maxlength="15" id="focus"/>
								<script type="text/javascript">
								    // 上記の入力欄にフォーカスを与える
								    document.getElementById('focus').focus();
								</script>
							<c:if test="${errorId != ''}">
								<br><font size="2" color="color-error">${errId}</font>
							</c:if>
						</td>
					</tr>
					<tr>
						<%-- 名前入力欄の名前はuserName --%>
						<td class="color-main text-left">氏名</td>
						<td class="text-left"><input class="form-control" type="text" name="userName" value="${param.userName}" size="30" maxlength="100" />
							<c:if test="${errorDTO != null && errorDTO != ''}">
								<br><font size="2" color="color-error">${errorDTO.errMsUname}</font>
							</c:if>
						</td>
					</tr>
					<tr>
						<%-- icon入力欄の名前はicon --%>
						<td class="color-main text-left">アイコン</td>
						<td class="text-left">
							<%-- 属性値checked="checked" --%>
							<br>
							<input class="form-control" type="radio" name="icon" value="icon-sun" size="30"
								<c:if test="${param.icon == 'icon-sun'}">checked</c:if> /><span class="icon-sun pe-2x pe-va"></span>
							<br>
							<input class="form-control" type="radio" name="icon" value="icon-umbrella" size="30"
						 	<c:if test="${param.icon == 'icon-umbrella'}">checked</c:if> /><span class="icon-umbrella pe-2x pe-va"></span>
							<br>
							<input class="form-control" type="radio" name="icon" value="icon-moon" size="30"
							<c:if test="${param.icon == 'icon-moon'}">checked</c:if> /><span class="icon-moon pe-2x pe-va"></span>
							<c:if test="${errorDTO != null && errorDTO != ''}">
								<br><font size="2" color="color-error">${errorDTO.errMsIcon}</font>
							</c:if>
						</td>
					</tr>
					<tr>
						<%-- プロフィール入力欄の名前はprofile --%>
						<td class="color-main text-left">プロフィール</td>
						<td class="text-left"><input class="form-control" type="text" name="profile" value="${param.profile}" size="30" maxlength="200" />
							<c:if test="${errorDTO != null && errorDTO != ''}">
								<br><font size="2" color="color-error">${errorDTO.errMsProf}</font>
							</c:if>
						</td>
					</tr>
					<tr>
						<%-- searchSerletへ送信 --%>
						<td colspan="2" class="text-right"><input class="btn" type="submit" name="searchBtn" value="検索する" /></td>
					</tr>
				</table>
			</form>
			<%-- ./loginを経由してtop.jspへ戻る --%>
			<form action="./login" method="post">
				<table style="width: 40%" class="container padding-y-5 table">
					<tr>
						<td class="none text-right"><input class="btn" type="submit" value="TOPへ戻る" /></td>
					</tr>
				</table>
				<%-- 現在のログイン情報を./loginへ返す --%>
				<%-- セッションスコープにあるUserDTO型のオブジェクトを参照 --%>
				<jsp:useBean id="user" scope="session" type="dto.UserDTO" />
				<input type="hidden" name="loginId" value="${user.loginId}">
				<input type="hidden" name="password" value="${user.password}">
				<%-- 更新/削除完了画面から./searchSrvを通ってtop.jspに戻るときまでhiddenで情報を保持する --%>
				<input type="hidden" name="reLoginId" value="${param.reLoginId}">
				<input type="hidden" name="rePassword" value="${param.rePassword}">
			</form>
		</div>
	</body>
</html>
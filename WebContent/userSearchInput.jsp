<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー検索</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="padding-y-5 text-center">
			<strong>ユーザー検索</strong>
		</div>
	</div>
	<%
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		//一度入力してされた値を取得
		//String[] sicon = (String[]) request.getAttribute("icon");
		String sloginId = (String) request.getAttribute("loginId");
		String suserName = (String) request.getAttribute("userName");
		String spassword = (String) request.getAttribute("password");
		String sprofile = (String) request.getAttribute("profile");

		String icon = (String) request.getParameter("icon");

		if (suserName == null) {
			suserName = request.getParameter("suserName");
			if (suserName == null) {
				suserName = "";
			}
		}

		if (sloginId == null) {
			sloginId = request.getParameter("sloginId");
			if (sloginId == null) {
				sloginId = "";
			}
		}

		if (spassword == null) {
			spassword = request.getParameter("spassword");
			if (spassword == null) {
				spassword = "";
			}
		}

		if (sprofile == null) {
			sprofile = request.getParameter("sprofile");
			if (sprofile == null) {
				sprofile = "";
			}
		}
	%>
	<form action="./usi" method="post">
		<table style="width: 450px" class="table">
			<tr>
				<td class="color-black text-left"><font face="HG丸ｺﾞｼｯｸM-PRO"
					size="4">条件絞り込み</font></td>
			</tr>
			<tr>
				<%-- ログインID入力欄の名前はloginId --%>
				<td class="color-main text-left">ログインID</td>
				<td class="text-right"><input class="form-control" type="text"
					name="loginId" value="<%=sloginId%>" /></td>
			</tr>
			<tr>
				<%-- パスワード入力欄の名前はpassword --%>
				<td class="color-main text-left">名前</td>
				<td class="text-right"><input class="form-control" type="text"
					name="userName" value="<%=suserName%>" /></td>
			</tr>
			<tr>
				<%-- パスワード入力欄の名前はpassword --%>
				<td class="color-main text-left">プロフィール</td>
				<td class="text-right"><input class="form-control" type="text"
					name="profile" value="<%=sprofile%>" /></td>
			</tr>
			<tr>
				<%-- パスワード入力欄の名前はpassword --%>
				<td class="color-main text-left">アイコン<%=icon %>${param.icon}</td>
				<c:choose>
					<c:when test="${param.icon == 'icon-plane'}">
						<td><input type="checkbox" name="sicon" value="icon-plane"
							checked="checked"><span class="icon-plane pe-2x pe-va"></span>
							<input type="checkbox" name="sicon" value="icon-rocket"><span
							class="icon-rocket pe-2x pe-va"></span></td>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${param.icon == 'icon-rocket'}">
								<td><input type="checkbox" name="sicon" value="icon-plane"><span
									class="icon-plane pe-2x pe-va"></span> <input type="checkbox"
									name="sicon" value="icon-rocket" checked="checked"><span
									class="icon-rocket pe-2x pe-va"></span></td>
							</c:when>
							<c:otherwise>
								<c:choose>
					<c:when test="${icon == 'double'}">
						<td><input type="checkbox" name="sicon" value="icon-plane"
							checked="checked"><span class="icon-plane pe-2x pe-va"></span>
							<input type="checkbox" name="sicon" value="icon-rocket"
							checked="checked"><span class="icon-rocket pe-2x pe-va"></span>
						</td>
					</c:when>
					<c:otherwise>
						<td><input type="checkbox" name="sicon" value="icon-plane"><span
							class="icon-plane pe-2x pe-va"></span> <input type="checkbox"
							name="sicon" value="icon-rocket"><span
							class="icon-rocket pe-2x pe-va"></span></td>
					</c:otherwise>
				</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>

			</tr>
			<%-- リクエストスコープにalertがあれば --%>
			<c:if
				test="${requestScope.alert != null && requestScope.alert != ''}">
				<tr>
					<%-- リクエストスコープのalertの値を出力 --%>
					<td colspan="2" class="color-error text-center"><c:out
							value="${requestScope.alert}" /></td>
				</tr>
			</c:if>
			<tr>
				<td><input type="button" onclick="location.href='./top.jsp'"
					value="戻る" class="btn"></td>
				<td colspan="2" class="text-right"><input type="submit"
					value="検索" class="btn"></td>
			</tr>
		</table>
	</form>
</body>
</html>
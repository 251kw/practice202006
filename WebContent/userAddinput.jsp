<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="padding-y-5 text-center">
			<strong>新規登録</strong>
		</div>
	</div>

	<%
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		//一度入力してされた値を取得
		String sicon = (String) session.getAttribute("icon");
		String sloginId = (String) session.getAttribute("loginId");
		String suserName = (String) session.getAttribute("userName");
		String spassword = (String) session.getAttribute("password");
		String sprofile = (String) session.getAttribute("profile");


		if (sloginId == null) {
			sloginId = "";
		}
		if (suserName == null) {
			suserName = "";
		}
		if (spassword == null) {
			spassword = "";
		}
		if (sprofile == null) {
			sprofile = "";
		}

	%>
	<%-- action 属性にサーブレットを指定 --%>
	<form action="./nu" method="post">
		<table style="width: 450px" class="table">
			<tr>
				<%-- ユーザー名入力欄の名前はuserName --%>
				<td class="color-main text-left">名前</td>
				<td class="text-left"><input class="form-control" type="text"
					name="userName" value="<%=suserName%>" size="20" /></td>

			</tr>
			<tr>
				<%-- ログインID入力欄の名前はloginId --%>
				<td class="color-main text-left">ログインID</td>
				<td class="text-left"><input class="form-control" type="text"
					name="loginId" value="<%=sloginId%>" size="20" /></td>
			</tr>
			<tr>
				<c:if
					test="${requestScope.alert2 != null && requestScope.alert2 != ''}">
					<tr>
						<%-- リクエストスコープのalertの値を出力 --%>
						<td colspan="2" class="color-error text-left"><c:out
								value="${requestScope.alert2}" /></td>
					</tr>
				</c:if>
			</tr>
			<tr>
				<%-- パスワード入力欄の名前はpassword --%>
				<td class="color-main text-left">パスワード</td>
				<td class="text-left"><input class="form-control"
					type="password" name="password" value="<%=spassword%>" size="20" /></td>
			</tr>
			<tr>

				<%-- アイコン入力欄の名前はicon --%>
				<td width="150" class="color-main text-left">アイコン<%=sicon%></td>
				<td><select name="icon" class="form-control">
						<c:choose>
							<c:when test="${sicon == 'icon-rocket'}">
								<option value="icon-rocket" selected>ロケット</option>
							</c:when>
							<c:otherwise>
								<option value="icon-rocket">ロケット</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${sicon == 'icon-car'}">
								<option value="icon-car" selected>車</option>
							</c:when>
							<c:otherwise>
								<option value="icon-car">車</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${sicon == 'icon-bicycle'}">
								<option value="icon-bicycle" selected>自転車</option>
							</c:when>
							<c:otherwise>
								<option value="icon-bicycle">自転車</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${sicon == 'icon-cart'}">
								<option value="icon-cart" selected>カート</option>
							</c:when>
							<c:otherwise>
								<option value="icon-cart">カート</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${sicon == 'icon-plane'}">
								<option value="icon-plane" selected>飛行機</option>
							</c:when>
							<c:otherwise>
								<option value="icon-plane">飛行機</option>
							</c:otherwise>
						</c:choose>
				</select></td>
			</tr>
			<tr>
				<%-- プロフィール入力欄の名前はprofile --%>
				<td width="150" class="color-main text-left">プロフィール</td>
				<td><textarea rows="5" cols="20" name="profile"
						class="form-control"><%=sprofile%></textarea></td>
			</tr>

			<tr>
				<td><input type="button" onclick="location.href='./Cut'"
					value="戻る" class="btn"></td>
				<td colspan="2" class="text-right"><input type="submit"
					value="登録" class="btn"></td>
			</tr>
			<%-- エラー検出 --%>
			<c:if
				test="${requestScope.alert != null && requestScope.alert != ''}">
				<tr>
					<%-- リクエストスコープのalertの値を出力 --%>
					<td colspan="2" class="color-error text-left"><c:out
							value="${requestScope.alert}" /></td>
				</tr>
			</c:if>

		</table>
	</form>
</body>
</html>
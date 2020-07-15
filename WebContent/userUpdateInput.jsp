<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>更新入力</title>
		<link rel="stylesheet" href="./css/skyblue.css">
		<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
		<link rel="stylesheet" href="./css/helper.css">
	</head>
	<body>
	<%
		//ログインIdを取得
		String sloginId = (String)request.getAttribute("sloginId");

		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		//一度入力してされた値を取得
		String sicon = (String) request.getAttribute("sicon");
		String loginId = (String) request.getAttribute("loginId");
		String userName = (String) request.getAttribute("userName");
		String password = (String) request.getAttribute("password");
		String profile = (String) request.getAttribute("profile");

		if(userName == null){
			userName = request.getParameter("suserName");
			if (userName == null) {
				userName = "";
			}
		}

		if(loginId == null){
			loginId = request.getParameter("sloginId");
			if (loginId == null) {
				loginId = "";
			}
		}

		if(password == null){
			password = request.getParameter("spassword");
			if (password == null) {
				password = "";
			}
		}

		if(profile == null){
			profile = request.getParameter("sprofile");
			if (profile == null) {
				profile = "";
			}
		}

		sicon = request.getParameter("sicon");
	%>
	<%
				String[] dloginId = (String[])request.getAttribute("hogeId");
	%>
		<div class="bg-success padding-y-5">
			<div class="padding-y-5 text-center">
				<strong>ユーザー情報更新</strong>
			</div>
		</div>
		<form action="./uuc" method="post">
			<table style="width: 450px" class="table">
				<tr>
					<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
						<tr>
							<%-- リクエストスコープのalertの値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert}" /></td>
						</tr>
					</c:if>
				</tr>
				<tr>
					<%-- 名前入力欄の名前はuserName --%>
					<td class="color-main text-left">名前</td>
					<td class="text-right"><input class="form-control" type="text"
						name="userName" value="<%=userName %>" /></td>
				</tr>
				<tr>
					<%-- パスワード入力欄の名前はpassword --%>
					<td class="color-main text-left">パスワード</td>
					<td class="text-right"><input class="form-control" type="password"
						name="password" value="<%=password %>" /></td>
				</tr>
				<tr>
					<%-- パスワード入力欄の名前はpassword --%>
					<td class="color-main text-left">プロフィール</td>
					<td class="text-right"><input class="form-control" type="text"
						name="profile" value="<%=profile %>" /></td>
				</tr>
				<tr>
					<%-- アイコン入力欄の名前はicon --%>
					<td width="150" class="color-main text-left">アイコン</td>
					<td><select name="sicon" class="form-control">
							<c:choose>
								<c:when test="${param.sicon == 'icon-rocket'}">
									<option value="icon-rocket" selected>ロケット</option>
								</c:when>
								<c:otherwise>
									<option value="icon-rocket">ロケット</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${param.sicon == 'icon-plane'}">
									<option value="icon-plane" selected>飛行機</option>
								</c:when>
								<c:otherwise>
									<option value="icon-plane">飛行機</option>
								</c:otherwise>
							</c:choose>
					</select></td>
				</tr>
			</table>
			<input type="hidden" name="sloginId" value="<%=sloginId%>">
			<table>
				<tr>
					<td colspan="4" class="text-center"><input type="submit"
					value="変更" class="btn"></td>
				</tr>
			</table>
		</form>
		<form action="./udb" method="post">
		<c:forEach var="hloginId" items="${hogeId}">
				<input type='hidden' name='hId' value="${hloginId}">
		</c:forEach>
		<input type="hidden" name="suserName" value="<%=userName%>">
			<input type="hidden" name="spassword" value="<%=password%>">
			<input type="hidden" name="sloginId" value="<%=loginId%>">
			<input type="hidden" name="sicon" value="<%=sicon%>">
			<input type="hidden" name="sprofile" value="<%=profile%>">
		<table>
			<tr>
				<td colspan="4" class="text-center"><input type="submit"
				value="戻る" class="btn"></td>
			</tr>
		</table>
	</form>
	</body>
</html>
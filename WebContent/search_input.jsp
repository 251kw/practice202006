<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="util.Check" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>shouter-ユーザー検索-</title>
		<link rel="stylesheet" href="./css/skyblue.css">
		<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
		<link rel="stylesheet" href="./css/helper.css">
	</head>
	<body>

		<%---------------------------------------------------------------------------------%>
		<div class="bg-success padding-y-5">
			<div class="padding-y-5 text-center">
				<h1>Shouter<span class="icon-speaker pe-1x pe-va"></span></h1>
			</div>
		</div>
		<div class="padding-y-5 text-center">
			<h5>検索条件を入力、または選択してください。</h5>
		</div>
		<%---------------------------------------------------------------------------------%>

		<jsp:useBean id="user" scope="request" type="dto.SearchUserDTO"/>
		<div class="padding-y-5">
			<div style="width: 40%" class="container padding-y-5">
				<form action="./sis" method="post">
				<table border="1" class="table">

					<%-- リクエストスコープにalertがあれば --%>
					<c:if
						test="${requestScope.alert != null && requestScorpe.alert !=''}">
						<tr>
							<%--リクエストスコープのalertの値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert}" /></td>
						</tr>
					</c:if>

						<tr>
							<th><label for="loginId"><font color="#00CCFF"><span class="icon-id pe-2x pe-va"></span></font>
								&nbsp;ログインID</label>
							</th>
							<td>
								<div><h6>半角英数字で入力してください</h6></div>
								<input type="text" name="loginId" id="loginId" value="${user.loginId}" class="form-control">
							</td>
						</tr>

						<tr>
							<th><label for="userName"><font color="#FF3333"><span class="icon-users pe-2x pe-va"></span></font>
								&nbsp;ユーザ名</label>
							</th>
							<td><input type="text"name="userName" id="userName" value="${user.userName}" class="form-control"></td>
						</tr>

						<tr>
							<th><font color="#FFCC33"><span class="icon-smile pe-2x pe-va"></span></font>&nbsp;アイコン</th>
							<td>
							<input type="checkbox" name="icon" value="icon-user" ${Check.checkIcons("icon-user", user.icon)} /><span class="icon-user pe-va"></span><%-- 男性 --%>
							<input type="checkbox" name="icon" value="icon-user-female" ${Check.checkIcons("icon-user-female", user.icon)}/><span class="icon-user-female pe-va"></span><%-- 女性 --%>
							<input type="checkbox" name="icon" value="icon-bell" ${Check.checkIcons("icon-bell", user.icon)}/><span class="icon-bell pe-va"></span><%-- ベル --%>
							<input type="checkbox" name="icon" value="icon-star" ${Check.checkIcons("icon-star", user.icon)}/><span class="icon-star pe-va"></span><%-- 星 --%>
							<input type="checkbox" name="icon" value="icon-moon" ${Check.checkIcons("icon-moon", user.icon)}/><span class="icon-moon pe-va"></span><%-- 月 --%>
							<input type="checkbox" name="icon" value="icon-rocket" ${Check.checkIcons("icon-rocket", user.icon)}/><span class="icon-rocket pe-va"></span><%-- ロケット --%>
							</td>
						</tr>

						<tr>
							<th><font color="#99CC66"><span class="icon-comment pe-2x pe-va"></span></font>&nbsp;プロフィール</th>
							<td><textarea rows="5" cols="20" name="profile" class="form-control">${user.profile}</textarea></td>
						</tr>

						<tr>
							<td colspan="2" class="text-right">
							<input class="btn" type="submit" value="検索する" name="btn"/>
							<input class="btn" type="submit" value="掲示板へ戻る" name="btn">
							</td>
						</tr>

					</table>
				</form>
			</div>
		</div>
	</body>
</html>
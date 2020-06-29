<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規登録</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
<div class="bg-success padding-y-30">
	<div class="container padding-y-5">
		<h2 class="text-center">Shouter&nbsp;<span class="icon-speaker pe-2x pe-va"></span></h2>
	</div>
</div>
<br>
<h4 class="text-center">新規登録する情報を入力してください</h4>
<br>

<%
	String newloginId = (String)session.getAttribute("newloginId");
	String newpassword = (String)session.getAttribute("newpassword");
	String newuserName = (String)session.getAttribute("newuserName");
	String newicon = (String)session.getAttribute("newicon");
	String newprofile = (String)session.getAttribute("newprofile");

	if(newloginId==null){
		newloginId = "";
	}
	if(newuserName==null){
		newuserName = "";
	}
	if(newprofile==null){
		newprofile = "";
	}
%>
<%-- ログインフォーム入力欄 --%>
<form action="./register" method="post">
	<table style="width: 400px" class="table container padding-y-5">
		<c:if
			test="${requestScope.alert != null && requestScope.alert != ''}">
			<tr>
				<%-- リクエストスコープの alert の値を出力 --%>
				<td colspan="2" class="color-error text-left"><c:out value="${requestScope.alert}" /></td>
			</tr>
		</c:if>
		<tr>
<%-- ログインID入力欄の名前はnewloginId --%>
			<td class="color-main text-center">ログインID</td>
			<td class="text-center"><input class="form-control" type="text" name="newloginId" value="<%=newloginId %>" size="20" /></td>
		</tr>
<%-- リクエストスコープにalertがあれば --%>
		<c:if
			test="${requestScope.alert1 != null && requestScope.alert1 != ''}">
			<tr>
<%-- リクエストスコープの alert の値を出力 --%>
				<td colspan="2" class="color-error text-left"><c:out value="${requestScope.alert1}" /></td>
			</tr>
		</c:if>
<%-- 入力チェック↓ --%>
		<c:if
			test="${requestScope.alert2 != null && requestScope.alert2 != ''}">
			<tr>
				<%-- リクエストスコープの alert の値を出力 --%>
				<td colspan="2" class="color-error text-left"><c:out value="${requestScope.alert2}" /></td>
			</tr>
		</c:if>
<%-- 入力チェック↑ --%>
		<tr>
			<%-- パスワード入力欄の名前はnewpassword --%>
			<td class="color-main text-center">パスワード</td>
			<td class="text-center"><input class="form-control" type="password" name="newpassword" value="" size="20" /></td>
		</tr>
<%-- 入力チェック↓ --%>
		<c:if
			test="${requestScope.alert3 != null && requestScope.alert3 != ''}">
			<tr>
				<%-- リクエストスコープの alert の値を出力 --%>
				<td colspan="2" class="color-error text-left"><c:out value="${requestScope.alert3}" /></td>
			</tr>
		</c:if>
<%-- 入力チェック↑ --%>
		<tr>
			<%-- ユーザー名入力欄の名前はnewuserName --%>
			<td class="color-main text-center">ユーザー名</td>
			<td class="text-center"><input class="form-control" type="text" name="newuserName" value="<%=newuserName %>" size="20" /></td>
		</tr>
		<tr>
			<%-- icon入力欄の名前はnewicon --%>
			<td class="color-main text-center">アイコン</td>
			<td>
				<select name="newicon" class="form-control">
					<c:choose>
						<c:when test="${newicon==null}">
							<option value="icon-user" selected>男性</option>
							<option value="icon-user-female">女性
						</c:when>
						<c:when test="${newicon=='icon-user'}">
							<option value="icon-user" selected>男性</option>
							<option value="icon-user-female">女性
						</c:when>
						<c:when test="${newicon=='icon-user-female'}">
							<option value="icon-user">男性
							<option value="icon-user-female" selected>女性</option>
						</c:when>
					</c:choose>
				</select>
			</td>
		</tr>
		<tr>
			<%-- profile入力欄の名前はnewprofile --%>
			<td class="color-main text-center">プロフィールに追加する一言</td>
			<td class="text-right"><input class="form-control" type="text" name="newprofile" value="<%=newprofile%>" size="20" /></td>
		</tr>
		<tr>
			<td colspan="2" class="text-right"><input class="btn" type="submit" value="新規登録" /></td>
		</tr>
	</table>
</form>
<form action="index.jsp" method="post">
<table style="width: 400px" class="table container padding-y-5">
	<tr>
		<td colspan="2" class="text-right">
			<input class="btn" type="submit" value="ログイン画面に戻る" />
		</td>
	</tr>
</table>
</form>
</body>
</html>
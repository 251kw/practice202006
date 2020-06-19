<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>

<%---------------------------------------------------------------------------------%>
<div class="bg-success padding-y-5">
	<div class="padding-y-5 text-center">
		<h1>Shouter<span class="icon-smile pe-1x pe-va"></span></h1>
	</div>
</div>
<div class="padding-y-5 text-center">
<h5>ログインIDとパスワードを入力してください</h5>
</div>
<%---------------------------------------------------------------------------------%>

	<%-- action属性にサーブレットを指定 --%>
	<form action="./login" method="post">
		<%--table style="width: 400px;margin:0px auto" class="table"--%>
		<table style="width: 40%" class="container padding-y-5">
			<tr>
				<%-- ログインID入力欄の名前はloginId --%>
				<td class="color-main text-left">ログインID</td>
				<td class="text-left"><input class="form-control" type="text"
					name="loginId" value="" size="20" /></td>
			</tr>
			<tr>
				<%-- パスワード入力欄の名前はpassword --%>
				<td class="color-main text-left">パスワード</td>
				<td class="text-left"><input class="form-control"
					type="password" name="password" value="" size="20" /></td>
			</tr>
			<tr>
				<td colspan="2" class="text-right"><input class="btn"
					type="submit" value="ログイン" /></td>
			</tr>
			<%-- リクエストスコープにalertがあれば --%>
			<c:if
				test="${requestScope.alert != null && requestScorpe.alert !=''}">
				<tr>
					<%--リクエストスコープのalertの値を出力 --%>
					<td colspan="2" class="color-error text-left"><c:out
							value="${requestScope.alert}" /></td>
				</tr>
			</c:if>
		</table>
	</form>

</body>
</html>
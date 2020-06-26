<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-10">
		<div class="text-center ">
			<h1>
				Shouter <span class="icon-speaker pe-2x pe-va"></span>
			</h1>
		</div>
	</div>
	<div class="text-center padding-y-5">
		<h4>ログインIDとパスワードを入力してください</h4>
	</div>
	<div class="text-right padding-y-5">
		<form action="new.jsp" method="post">

			<input class="btn" type="submit" value="新規登録" />

		</form>
	</div>

	<form action="./login" method="post">
		<table style="width: 400px;" class="container padding-y-5 table">
			<tr>
				<%-- ログインID入力欄の名前はloginId --%>
				<td class="color-main text-center">ログインID</td>
				<td class="text-center"><input class="form-control" type="text"
					name="loginId" value="" size="20" /></td>
			</tr>
			<tr>
				<%-- パスワード入力欄の名前はloginId --%>
				<td class="color-main text-center">パスワード</td>
				<td class="text-center"><input class="form-control" type="text"
					name="password" value="" size="20" /></td>
			</tr>
			<tr>
				<td colspan="2" class="text-right"><input class="btn"
					type="submit" value="ログイン" /></td>
			</tr>
			<%-- リクエストスコープにalertがあれば --%>
			<c:if
				test="${requestScope.alert != null && requestScope.alert != ' '}">
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
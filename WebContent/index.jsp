<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>shouter-ログイン-</title>
		<link rel="stylesheet" href="./css/skyblue.css">
		<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
		<link rel="stylesheet" href="./css/helper.css">
	</head>
	<body>
		<div class="bg-success padding-y-5">
			<div class="padding-y-5 text-center">
				<h1>Shouter<span class="icon-speaker pe-1x pe-va"></span></h1>
			</div>
			</div>
		<div class="padding-y-5 text-center">
			<h5>aaaaaaaaaaaaaaaaaaaaaaaaa</h5>
			<h5>bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb</h5>
			<h5>cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc</h5>
		</div>
		<%---------------------------------------------------------------------------------%>
		<%---------------------------------------------------------------------------------%>

		<%---------------------------------------------------------------------------------%>
		<%---------------------------------------------------------------------------------%>

		<%-- action属性にサーブレットを指定 --%>
		<form action="./login" method="post">
			<table style="width: 40%" class="container padding-y-5">
			<c:choose>
				<c:when test="${requestScope.id == null && requestScorpe.id ==''}">
				<tr>
					<%-- ログインID入力欄の名前はloginId --%>
					<td class="color-main text-left">ログインID</td>
					<td class="text-left"><input class="form-control" type="text"
						name="loginId" value="" size="20" /></td>
				</tr>
				</c:when><%---------------------------------------------------------------------------------%>

				<c:otherwise>
				<tr>
					<%-- ログインID入力欄の名前はloginId --%>
					<td class="color-main text-left">ログインID</td>
					<td class="text-left"><input class="form-control" type="text"<%---------------------------------------------------------------------------------%>
						name="loginId" value="${requestScope.id}" size="20" autofocus/></td>
				</tr>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${requestScope.pass == null && requestScorpe.pass ==''}">
				<tr>
					<td class="color-main text-left">パスワード</td>
					<td class="text-left"><input class="form-control"
						type="password" name="password" value="" size="20" /></td>
				</tr>
				</c:when>

				<c:otherwise>
				<tr>

					<td class="color-main text-left">パスワード</td>
					<td class="text-left"><input class="form-control"
						type="password" name="password" value="${requestScope.pass}" size="20" /></td>
				</tr>
				</c:otherwise>
			</c:choose>

				<tr><th><img src="odorupen.gif" alt="tw" width="96" height="65"></th>
					<td colspan="2" class="text-right">
					<input class="btn" type="submit" value="新規登録" name="btn"/>
					<input class="btn" type="submit" value="ログイン" name="btn"/></td>
				</tr>


				<c:if
					test="${requestScope.alert != null && requestScorpe.alert !=''}">
					<tr>

						<td colspan="2" class="color-error text-left">
						<c:out value="${requestScope.alert}" /></td>
					</tr>
				</c:if>
			</table>
		</form>
		<h1>あってんのかわかんね</h1>
		<h1>あってんのかわかんね</h1>

		<h1>( ;∀;)</h1>
		<h1>なんかわかってきた</h1>
		<h1>(-_-)</h1>
		<h1>( ﾟДﾟ)</h1>
		<h1>(-_-)</h1>
	</body>
</html>
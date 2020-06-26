<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>登録内容設定</title>
	<link rel="stylesheet" href="./css/skyblue.css">
	<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
	<link rel="stylesheet" href="./css/helper.css">
</head>
<body>

<div class="bg-success padding-y-5">
	<div class="padding-y-5 text-center">
		<strong>会員情報入力フォーム</strong>
	</div>
</div>
<div class="padding-y-5">
	<div style="width: 50%" class="padding-y-5 container ">
		<form action="./rst" method="post">
			<table border="1" class="table">
				<tr>
					<th><label for="loginId"><span class="icon-smile pe-2x pe-va"></span>&nbsp;ログインID</label></th>
						<td><input type="text"name="loginId" id="loginId" value="${sessionScope.loginId}" class="form-control"></td>
				</tr>
				<tr>
					<th><label for="pass"><span class="icon-note pe-2x pe-va"></span>&nbsp;パスワード</label></th>
						<td><input type="password" name="pass" id="pass" value="${sessionScope.password}" class="form-control"></td>
				</tr>
				<tr>
					<th><label for="userName"><span class="icon-note pe-2x pe-va"></span>&nbsp;名前</label></th>
						<td><input type="text" name="userName" id="userName" value="${sessionScope.userName}" class="form-control"></td>
				</tr>
				<tr>
					<th><span class="icon-users pe-2x pe-va"></span>&nbsp;アイコン</th>
						<td>
						<input type="radio" name="icon" value="joy" checked><span class="icon-joy pe-2x pe-va"></span>&nbsp;
						<input type="radio" name="icon" value="moon"><span class="icon-moon pe-2x pe-va"></span>&nbsp;
						<input type="radio" name="icon" value="smile"><span class="icon-smile pe-2x pe-va"></span>&nbsp;
						<input type="radio" name="icon" value="music"><span class="icon-music pe-2x pe-va"></span>&nbsp;
						<input type="radio" name="icon" value="cloud"><span class="icon-cloud pe-2x pe-va"></span>&nbsp;
						<input type="radio" name="icon" value="ball"><span class="icon-ball pe-2x pe-va"></span>&nbsp;
						<input type="radio" name="icon" value="coffee"><span class="icon-coffee pe-2x pe-va"></span>&nbsp;
						<input type="radio" name="icon" value="glasses"><span class="icon-glasses pe-2x pe-va"></span>&nbsp;
						</td>
				</tr>
				<tr>
					<th><span class="icon-note2 pe-2x pe-va"></span>&nbsp;プロフィール</th>
					<td><textarea rows="5" cols="20" name="remarks"  class="form-control">${sessionScope.profile}</textarea></td>
				</tr>
				<tr>
					<td colspan="2" class="text-left"><input class="btn" type="submit" value="送信" ></td>
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
	</div>
</div>
</body>
</html>
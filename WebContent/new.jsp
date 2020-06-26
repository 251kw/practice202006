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
	<div style="width: 40%" class="padding-y-5">
		<form action="./rst" method="post">
			<table border="1" class="table">
				<tr>
					<th><label for="loginId"><span class="icon-smile pe-2x pe-va"></span>&nbsp;ログインID</label></th>
						<td><input type="text"name="loginId" id="loginId" class="form-control"></td>
				</tr>
				<tr>
					<th><label for="pass"><span class="icon-note pe-2x pe-va"></span>&nbsp;パスワード</label></th>
						<td><input type="password" name="pass" id="pass" class="form-control"></td>
				</tr>
				<tr>
					<th><label for="userName"><span class="icon-note pe-2x pe-va"></span>&nbsp;名前</label></th>
						<td><input type="text" name="userName" id="userName" class="form-control"></td>
				</tr>
				<tr>
					<th><span class="icon-users pe-2x pe-va"></span>&nbsp;アイコン</th>
						<td>
						<label class="fancy-radio"><input type="radio" name="icon" value="user" checked><span class="icon-users pe-2x pe-va"></span>&nbsp;人</label>
						</td>
						<td>
						<label class="fancy-radio"><input type="radio" name="icon" value="note"><span class="icon-note2 pe-2x pe-va"></span>&nbsp;ノート</label>
						</td>
						<td>
						<label class="fancy-radio"><input type="radio" name="icon" value="smile"><span class="icon-smile pe-2x pe-va"></span>&nbsp;笑顔</label>
						</td>
				</tr>
				<tr>
					<th><span class="icon-note2 pe-2x pe-va"></span>&nbsp;プロフィール</th>
					<td><textarea rows="5" cols="20" name="remarks" class="form-control"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" class="text-right"><input class="btn" type="submit" value="送信" ></td>
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
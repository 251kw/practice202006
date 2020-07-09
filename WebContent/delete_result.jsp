<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>削除完了</title>
	<link rel="stylesheet" href="./css/skyblue.css">
	<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
	<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="padding-y-5 text-center">
			<strong>削除しました</strong>
		</div>
	</div>
	<div style="width: 40%" class="container padding-y-5">
		<form action='./udr' method='post'>
			<table border='1' class='table'>
				<tr>
					<th>ログインID</th>
					<td>${loginId}</td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td>${password}</td>
				</tr>
				<tr>
					<th>名前</th>
					<td>${userName}</td>
				</tr>
				<tr>
					<th>アイコン</th>
					<td><span class="${icon} pe-2x pe-va"></span></td>
				</tr>
				<tr>
					<th>プロフィール</th>
					<td>${profile}</td>
				</tr>
				<tr>
					<td colspan='2' style='text-align: right'>
					<input type='submit' value='戻る'>
					<input type="hidden" name="d_loginId" value="${loginId }">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
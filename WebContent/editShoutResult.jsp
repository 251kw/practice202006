<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>変更完了</title>
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
			<h5 class="text-center">以下の内容で変更しました</h5>
		<br>
		<form action="./TurnBoardTop" method="post">
			<table style="width: 400px" class="table table-borderd container padding-y-5">
				<tr>
					<th class="color-main text-center">ログインID:</th>
					<td class="text-center">${requestScope.reshout.loginId}</td>
				</tr>
				<tr>
					<th class="color-main text-center">ユーザー名:</th>
					<td class="text-center">${requestScope.reshout.userName}</td>
				</tr>
				<tr>
					<th class="color-main text-center">アイコン:</th>
					<td class="text-center"><span class="${requestScope.reshout.icon} pe-2x pe-va"></span></td>
				</tr>
				<tr>
					<th class="color-main text-center">書き込み内容:</th>
					<td class="text-center">${requestScope.reshout.writing}</td>
				</tr>
				<tr>
					<td colspan="2" class="text-right">
						<input class="btn" type="submit" value="掲示板に戻る" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
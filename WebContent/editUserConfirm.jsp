<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>変更確認</title>
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
		<h4 class="text-center">以下の内容で登録情報を変更しますか？</h4>
		<br>
		<form action="./EditUserRegist" method="post">
			<table style="width: 400px" class="table table-borderd container padding-y-5">
				<tr>
					<th class="color-main text-center">パスワード:</th>
					<td class="text-center">${sessionScope.euser.password}</td>
				</tr>
				<tr>
					<th class="color-main text-center">ユーザー名:</th>
					<td class="text-center">${sessionScope.euser.userName}</td>
				</tr>
				<tr>
					<th class="color-main text-center">アイコン:</th>
					<td class="text-center"><span class="${sessionScope.euser.icon} pe-2x pe-va"></span></td>
				</tr>
				<tr>
					<th class="color-main text-center">プロフィール:</th>
					<td class="text-center">${sessionScope.euser.profile}</td>
				</tr>
				<tr>
					<td colspan="2" class="text-right">
						<input class="btn" type="submit" value="変更する" />
						<input type="hidden" name="sloginId" value="${requestScope.sloginId}">
						<input type="hidden" name="suserName" value="${requestScope.suserName}">
						<input type="hidden" name="sicon" value="${requestScope.sicon}">
						<input type="hidden" name="sprofile" value="${requestScope.sprofile}">
					</td>
				</tr>
			</table>
		</form>
		<form action="./TurnEditUserInput" method="post">
			<table style="width: 400px" class="table table-borderd container padding-y-5">
				<tr>
					<td>
						<input type="hidden" name="sloginId" value="${requestScope.sloginId}">
						<input type="hidden" name="suserName" value="${requestScope.suserName}">
						<input type="hidden" name="sicon" value="${requestScope.sicon}">
						<input type="hidden" name="sprofile" value="${requestScope.sprofile}">
						<input type="hidden" name="checkall" value="${requestScope.checkall}">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-right">
						<input class="btn" type="submit" value="入力画面に戻る" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
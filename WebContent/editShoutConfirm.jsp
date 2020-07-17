<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>叫び変更確認</title>
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
		<h4 class="text-center">以下の内容に変更しますか？</h4>
		<br>
		<form action="./ShoutEditRegist" method="post">
			<table style="width: 400px" class="table table-borderd container padding-y-5">
				<tr>
					<th class="color-main text-center">ログインID:</th>
					<td class="text-center">${requestScope.esloginId}</td>
				</tr>
				<tr>
					<th class="color-main text-center">ユーザー名:</th>
					<td class="text-center">${requestScope.esuserName}</td>
				</tr>
				<tr>
					<th class="color-main text-center">アイコン:</th>
					<td class="text-center"><span class="${requestScope.esicon} pe-2x pe-va"></span></td>
				</tr>
				<tr>
					<th class="color-main text-center">書き込み内容:</th>
					<td class="text-center">${requestScope.eswriting}</td>
				</tr>
				<tr>
					<td colspan="2" class="text-right">
						<input class="btn" type="submit" value="この内容で変更" />
						<input type="hidden" name="eshoutsId" value="${requestScope.eshoutsId}">
						<input type="hidden" name="eswriting" value="${requestScope.eswriting}">
					</td>
				</tr>
			</table>
		</form>
		<form action="./TurnEditShoutInput" method="post">
			<table style="width: 400px" class="table container padding-y-5">
				<tr>
					<td colspan="2" class="text-right">
						<input class="btn" type="submit" value="入力画面に戻る" />
						<input type="hidden" name="eshoutsId" value="${requestScope.eshoutsId}">
						<input type="hidden" name="esloginId" value="${requestScope.esloginId}">
						<input type="hidden" name="esuserName" value="${requestScope.esuserName}">
						<input type="hidden" name="esicon" value="${requestScope.esicon}">
						<input type="hidden" name="eswriting" value="${requestScope.eswriting}">
						<input type="hidden" name="checkall" value="${requestScope.checkall}">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
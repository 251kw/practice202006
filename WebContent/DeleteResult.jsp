<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>削除完了</title>
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
		<h4 class="text-center">削除が完了しました</h4>
		<br>
		<form action="./UserSearchSqueeze" method="post">
			<table style="width: 400px" class="table container padding-y-5">
				<tr>
					<td><input type="hidden" name="sloginId" value="${requestScope.sloginId}"></td>
					<td><input type="hidden" name="suserName" value="${requestScope.suserName}"></td>
					<td><input type="hidden" name="sicon" value="${requestScope.sicon}"></td>
					<td><input type="hidden" name="sprofile" value="${requestScope.sprofile}"></td>
				</tr>
			<tr>
				<td colspan="4" class="text-center">
					<input class="btn" type="submit" value="検索結果に戻る" />
				</td>
			</tr>
		</table>
	</form>
	</body>
</html>
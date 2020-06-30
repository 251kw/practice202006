<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang ="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter-検索-</title>
	<link rel="stylesheet" href="./css/skyblue.css">
	<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
	<link rel="stylesheet" href="./css/helper.css">
</head>
<body>

<div class="bg-warning padding-y-5">
	<div class="padding-y-5 text-center">
		<strong>Shouter</strong>
		<span class="icon-speaker pe-2x pe-va"></span>
	</div>
</div>

<h5 class="padding-y-5 text-center">ユーザー検索</h5>

<form action="./search" method="post">
	<input type="hidden" name="sort" value="loginId">
	<table style="width: 70%" class="table container">
		<tr>
			<td class="text-left">
				<input class="form-control" type="text" name="loginId" value="" size="20" maxlength="20" />
			</td>
			<td class="color-main text-left">ログインID</td>
		</tr>
		<tr>
			<td class="text-left"><input class="form-control" type="text" name="userName" value="" size="20" maxlength="15" /></td>
			<td class="color-main text-left">ユーザー名</td>
		</tr>
		<tr>
			<td>
				<input type="hidden" name="icon-car" value="">
				<label class="fancy-radio"><input type="checkbox" name="icon-car" value="icon-car"><span>車</span></label>
				<span class="icon-car pe-2x pe-va"></span>
				<input type="hidden" name="icon-paperclip" value="">
				<label class="fancy-radio"><input type="checkbox" name="icon-paperclip" value="icon-paperclip" ><span>クリップ</span></label>
				<span class="icon-paperclip pe-2x pe-va"></span>
				<input type="hidden" name="icon-radio" value="">
				<label class="fancy-radio"><input type="checkbox" name="icon-radio" value="icon-radio" ><span>ラジオ</span></label>
				<span class="icon-radio pe-2x pe-va"></span>
			</td>
			<td class="color-main text-left">アイコン</td>
		</tr>
		<tr>
			<td><textarea name="profile" rows="4" cols="40"></textarea></td>
			<td class="color-main text-left" height="100%">プロフィール</td>
		</tr>
	</table>

	<div align="right">
		<input class="btn" type="submit" value="検索" />
	</div>

</form>


</body>
</html>
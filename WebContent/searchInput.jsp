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
				<input class="form-control" type="text" name="loginId" value="${loginID}" size="20" maxlength="20" />
			</td>
			<td class="color-main text-left">ログインID</td>
		</tr>
		<tr>
			<td class="text-left"><input class="form-control" type="text" name="userName" value="${userName}" size="20" maxlength="15" /></td>
			<td class="color-main text-left">ユーザー名</td>
		</tr>
		<tr>
			<td>

				<label class="fancy-radio"><input type="checkbox" name="icon_car" value="icon-car" <% if(request.getAttribute("icon_car").equals("icon-car")){ out.print("checked"); } %>><span>車</span></label>
				<span class="icon-car pe-2x pe-va"></span>
				<label class="fancy-radio"><input type="checkbox" name="icon_paperclip" value="icon-paperclip" <% if(request.getAttribute("icon_paperclip").equals("icon-paperclip")){ out.print("checked"); } %> ><span>クリップ</span></label>
				<span class="icon-paperclip pe-2x pe-va"></span>
				<label class="fancy-radio"><input type="checkbox" name="icon_radio" value="icon-radio" <% if(request.getAttribute("icon_radio").equals("icon-radio")){ out.print("checked"); } %> ><span>ラジオ</span></label>
				<span class="icon-radio pe-2x pe-va"></span>
			</td>
			<td class="color-main text-left">アイコン</td>
		</tr>
		<tr>
			<td><textarea name="profile" rows="4" cols="40" >${profile}</textarea></td>
			<td class="color-main text-left" height="100%">プロフィール</td>
		</tr>
	</table>

	<div align="right">
		<input class="btn" type="submit" value="検索" />
	</div>

</form>

<form action="./topInput.jsp" method="get">
	<div align="right">
		<input class="btn btn-grey" type="submit" value="戻る" />
	</div>
</form>


</body>
</html>
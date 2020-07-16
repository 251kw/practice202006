<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang ="ja">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Shouter-新規登録-</title>
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
		<h5 class="padding-y-5 text-center">登録情報を入力してください</h5>
		<form action="./newuser" method="post">
			<table style="width: 70%" class="table container">
				<tr>
					<td colspan="2" class="color-error text-left">
						<c:out value="${requestScope.alert}" />
					</td>
				</tr>
				<tr>
					<td class="color-main text-left">ログインID</td>
					<td class="text-left">
						<p align="right" style="font-size: small; color: #ff0000;">半角英数字,4文字以上で入力してください</p>
						<input class="form-control" type="text" name="loginID" value="${loginID}" size="20" maxlength="20" autofocus/>
					</td>
				</tr>
				<tr>
					<td class="color-main text-left">パスワード</td>
					<td class="text-left">
						<p align="right" style="font-size: small; color: #ff0000;">半角英数字,4文字以上で入力してください</p>
						<input class="form-control" type="password" name="password1" value="" size="20" maxlength="20" />
					</td>
				</tr>
				<tr>
					<td class="color-main text-left">パスワード確認</td>
					<td class="text-left">
						<p align="right" style="font-size: small; color: #ff0000;">半角英数字,4文字以上で入力してください</p>
						<input class="form-control" type="password" name="password2" value="" size="20" maxlength="20" />
					</td>
				</tr>
				<tr>
					<td class="color-main text-left">ユーザー名</td>
					<td class="text-left"><input class="form-control" type="text" name="userName" value="${userName}" size="20" maxlength="15" /></td>
				</tr>
				<tr>
					<td class="color-main text-left">初期アイコン</td>
					<td>
						<label>
							<input type="radio"name="icon" value="icon-car" <% if(request.getAttribute("icon").equals("icon-car")){ out.print("checked"); } %>><span></span>
						</label>
						<span class="icon-car pe-2x pe-va"></span>
						<label>
							<input type="radio" name="icon" value="icon-paperclip" <% if(request.getAttribute("icon").equals("icon-paperclip")){ out.print("checked"); } %> ><span></span>
						</label>
						<span class="icon-paperclip pe-2x pe-va"></span>
						<label>
							<input type="radio" name="icon" value="icon-radio" <% if(request.getAttribute("icon").equals("icon-radio")){ out.print("checked"); } %> ><span></span>
						</label>
						<span class="icon-radio pe-2x pe-va"></span>
					</td>
				</tr>
				<tr>
					<td class="color-main text-left" height="100%">プロフィール</td>
					<td>
						<textarea name="profile" rows="4" cols="40">${profile}</textarea>
					</td>
				</tr>
				<c:if test="${requestScope.alert != null && requestScope.alert != ''}">
					<tr>
						<td colspan="2" class="color-error text-left">
							<c:out value="${requestScope.alert}" />
						</td>
					</tr>
				</c:if>
			</table>
			<div align="center">
				<input class="btn" type="submit" value="登録" />
			</div>
		</form>
		<form action="./returnIndexResultServlet" method="get">
			<div align="center">
				<input class="btn btn-grey btn-sm" type="submit" value="INDEXに戻る" />
			</div>
		</form>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>shouter-登録確認-</title>
		<link rel="stylesheet" href="./css/skyblue.css">
		<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
		<link rel="stylesheet" href="./css/helper.css">
	</head>
	<body>

		<%-------------------------------------------------------------%>
		<div class="bg-success padding-y-5">
			<div class="padding-y-5 text-center">
				<h1>Shouter<span class="icon-speaker pe-1x pe-va"></span></h1>
			</div>
		</div>
		<div class="padding-y-5 text-center">
			<h5>以下の登録内容でよろしいですか？</h5>
		</div>
		<%-------------------------------------------------------------%>

		<%-- 登録確認内容表示 --%>
		<jsp:useBean id="newuser" scope="request" type="dto.UserDTO" />
		<div class="padding-y-5">
			<div style="width: 40%" class="container padding-y-5">
				<form action="./sui" method="post">
					<table border="1" class="table">
						<tr>
							<th><label for="loginId"><font color="#00CCFF"><span class="icon-id pe-2x pe-va"></span></font>&nbsp;ログインID</label></th>
							<td>${newuser.loginId}</td>
						</tr>
						<tr>
							<th><label for="pass"><font color="#CC99CC"><span class="icon-key pe-2x pe-va"></span></font>&nbsp;パスワード</label></th>
							<td>${newuser.password}</td>
						</tr>
						<tr>
							<th><label for="userName"><font color="#FF3333"><span class="icon-users pe-2x pe-va"></span></font>&nbsp;ユーザ名</label></th>
							<td>${newuser.userName}</td>
						</tr>
						<tr>
							<th><label for="icon"><font color="#FFCC33"><span class="icon-smile pe-2x pe-va"></span></font>&nbsp;アイコン</label></th>
							<td><span class="${newuser.icon} pe-2x pe-va"></span></td>
						</tr>
						<tr>
							<th><font color="#99CC66"><span class="icon-comment pe-2x pe-va"></span></font>&nbsp;プロフィール</th>
							<td>${newuser.profile}</td>
						</tr>
						<tr>
							<td colspan="2" class="text-right">
							<input type="hidden" name="loginId" value="${newuser.loginId}">
							<input type="hidden" name="pass" value="${newuser.password}">
							<input type="hidden" name="userName" value="${newuser.userName}">
							<input type="hidden" name="icon" value="${newuser.icon}">
							<input type="hidden" name="profile" value="${newuser.profile}">
							<input class="btn" type="submit" value="登録する" name="btn"/>
							<input class="btn" type="submit" value="戻る" name="btn">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>
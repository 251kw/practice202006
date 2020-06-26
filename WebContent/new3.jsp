<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>登録完了</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
<jsp:useBean id="user" scope="session" type="dto.UserDTO"/>
	<div class="bg-success padding-y-5">
		<div class="padding-y-5 text-center">
			<strong>登録しました</strong>
		</div>
	</div>
	<div class="padding-y-5">
		<div style="width: 40%" class="padding-y-5">
			<form action='index.jsp' method='post'>
				<table border='1' class='table'>
					<tr>
						<th>ログインID</th>
						<td>${user.loginId}</td>
					</tr>
					<tr>
						<th>パスワード</th>
						<td>${user.password}</td>
					</tr>
					<tr>
						<th>名前</th>
						<td>${user.userName}</td>
					</tr>
					<tr>
						<th>プロフィール</th>
						<td>${user.profile}</td>
					</tr>
					<tr>
						<td colspan='2' style='text-align: right'><input
							type='submit' value='戻る'></td>
					</tr>
				</table>
			</form>

</div>
</div>
</body>
</html>
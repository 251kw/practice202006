<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>shouter-登録完了-</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>

<%---------------------------------------------------------------------------------%>
<div class="bg-success padding-y-5">
	<div class="padding-y-5 text-center">
		<h1>Shouter<span class="icon-speaker pe-1x pe-va"></span></h1>
	</div>
</div>
<div class="padding-y-5 text-center">
<h5>以下の内容で登録を完了いたしました。</h5>
</div>
<%---------------------------------------------------------------------------------%>

<%-- 登録内容表示 --%>
<jsp:useBean id="newuser" scope="session" type="dto.UserDTO" />
<div class="padding-y-5">
	<div style="width: 40%" class="container padding-y-5">
		<form action="./suc" method="post">
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
					<input type="submit" value="ログイン画面へ戻る" class="btn">
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="util.Check" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>shouter-登録情報編集-</title>
		<link rel="stylesheet" href="./css/skyblue.css">
		<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
		<link rel="stylesheet" href="./css/helper.css">
	</head>
	<body>

		<%---------------------------------------------------------%>
		<div class="bg-success padding-y-5">
			<div class="padding-y-5 text-center">
				<h1>Shouter<span class="icon-speaker pe-1x pe-va"></span></h1>
			</div>
		</div>
		<div class="padding-y-5 text-center">
			<h5>以下の内容で更新しますか？</h5>
		</div>
		<%----------------------------------------------------------%>
		<jsp:useBean id="shout" scope="request" type="dto.ShoutDTO"/>
			<div class="padding-y-5">
				<div style="width: 40%" class="container padding-y-5">
					<form action="./udis" method="get">
					<table border="1" class="table">

							<tr>
								<th><label for="userName"><font color="#FF3333"><span class="icon-users pe-2x pe-va"></span></font>
									&nbsp;ユーザ名</label>
								</th>
								<td>${shout.userName}</td>
							</tr>

							<tr>
								<th><font color="#FFCC33"><span class="icon-smile pe-2x pe-va"></span></font>&nbsp;アイコン</th>
								<td><span class="${shout.icon} pe-2x pe-va"></span></td>
							</tr>

							<tr>
								<th><font color="#80ffff"><span class="icon-chat pe-2x pe-va"></span></font>&nbsp;叫び</th>
								<td>${shout.writing}</td>
							</tr>

							<tr>
								<td colspan="2" class="text-right">
								<input class="btn" type="submit" value="更新する" name="btn"/>
								<input class="btn" type="submit" value="戻る" name="btn">
								</td>
							</tr>

						</table>
					</form>
				</div>
			</div>
	</body>
</html>
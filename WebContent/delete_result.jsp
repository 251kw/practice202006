<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>shouter-削除完了-</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>

<body>
<%---------------------------------------------------------------------------------%>
	<div class="bg-success padding-y-5">
		<div class="padding-y-5 text-center">
			<h1>
				Shouter<span class="icon-speaker pe-1x pe-va"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<h5>以下のユーザー情報を削除しました。</h5>
	</div>
<%---------------------------------------------------------------------------------%>
<%--リクエストスコープにあるArrayList型のオブジェクトを参照 --%>
	<jsp:useBean id="users" scope="request" type="java.util.ArrayList<dto.UserDTO>" />
	<div class="padding-y-5">
		<div style="width: 60%" class="container padding-y-5">
			<table class="table table-striped table-bordered table-hover">

				<thead>
					<tr>
						<th></th>
						<th>ログインID</th>
						<th>ユーザー名</th>
						<th>アイコン</th>
						<th>プロフィール</th>
						<th>操作</th>
					</tr>
				</thead>
				<c:forEach var="users" items="${users}" varStatus="loop">
				<tbody>
					<tr>
						<td>${loop.count}</td>
						<td>${users.loginId}</td>
						<td>${users.userName}</td>
						<td><span class="${users.icon} pe-2x pe-va"></span></td>
						<td>${users.profile}</td>
					</tr>
				</c:forEach>
			</table>
					<input type="submit" value="検索結果画面へ" name="btn" class="btn">

		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
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
				<h1>Shouter<span class="icon-speaker pe-1x pe-va"></span></h1>
			</div>
		</div>
		<div class="padding-y-5 text-center">
			<h5>以下の「叫び」を削除しますか？</h5>
		</div>
		<%---------------------------------------------------------------------------------%>

		<%--リクエストスコープにあるArrayList型のオブジェクトを参照 --%>
		<jsp:useBean id="shouts" scope="request" type="java.util.ArrayList<dto.ShoutDTO>" />
		<div class="padding-y-5 text-center">
			<div style="width: 50%" class="container padding-y-5">
			<form action="./drs" method="get">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th></th>
							<th>ユーザー名</th>
							<th>アイコン</th>
							<th>叫び</th>
						</tr>
					</thead>
					<c:forEach var="shout" items="${shouts}" varStatus="loop">
					<tbody>
						<tr>
							<td><input type="hidden" name="shoutId" value="${shout.shoutsId}">
								${loop.count}</td>
							<td>${shout.userName}</td>
							<td><span class="${shout.icon} pe-2x pe-va"></span></td>
							<td><div class="balloon2-left">
								<p>${shout.writing}</p></div></td>
						</tr>
					</c:forEach>
				</table>
					<input type="submit" value="キャンセル" name="btn" class="btn">
					<input type="submit" value="削除" name="btn" class="btn">
				</form>
			</div>
		</div>
	</body>
</html>
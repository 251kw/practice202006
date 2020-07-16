<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang ="ja">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Shouter-確認-</title>
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

		<div class="padding-y-5 text-center" >変更シャウト確認</div>

		<jsp:useBean id="searchShout" scope="request" type="dto.ShoutDTO" />
			<div style="width: 50%" class="container padding-y-5">
					<table class="table table-bordered">
						<tr>
							<td rowspan="2" class="text-center"><span class="${searchShout.icon} pe-3x pe-va"></span></td>
							<td>${searchShout.userName}</td>
						</tr>
						<tr>
							<td>シャウト確定を押した時間に更新されます</td>
						</tr>
						<tr>
							<td colspan="2" height="150">${searchShout.writing}</td>
						</tr>
					</table>
			</div>

		<div align="center">
			<form action="./updateShoutResultServlet" method="post">
				<input type="hidden" name="shoutsId" value="${searchShout.shoutsId}">
				<input type="hidden" name="shout" value="${searchShout.writing}">
				<input class="btn btn-grey btn-sm" type="submit" style="height:50px" value="シャウト確定" />
			</form>
		</div>

		<div align="center">
			<form action="./updateShoutFailureConfirm" method="post">
				<input type="hidden" name="shoutsId" value="${searchShout.shoutsId}">
				<input type="hidden" name="shout" value="${searchShout.writing}">
				<input class="btn btn-grey btn-sm" type="submit" style="height:50px" value="戻る" />
			</form>
		</div>

	</body>
</html>
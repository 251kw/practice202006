<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>削除内容確認</title>
	<link rel="stylesheet" href="./css/skyblue.css">
	<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
	<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="padding-y-5 text-center">
			<strong>本当に削除しますか？</strong>
		</div>
	</div>
	<div style="width: 40%" class="container padding-y-5">
		<table class="table table-striped table-bordered">
			<tr>
				<td rowspan="2" class="text-center">
					<span class="${icon} pe-3x pe-va"></span>
				</td>
				<td>${userName}</td>
			</tr>
			<tr>
				<td>${date}</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="mybox">
						${writing}
					</div>
				</td>
			</tr>
		</table>
		<form action='top.jsp' method='post'>
			<table border='1' class='table'>
				<tr>
					<td colspan='2' style='text-align: left'>
						<input type='submit'class="btn btn-sm" value='戻る'>
						<a href="./sdr?shoutsId=${shoutsId}&icon=${icon}&userName=${userName}&date=${date}&writing=${writing}">
							<button	type="button" class="btn btn-sm">OK</button>
						</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
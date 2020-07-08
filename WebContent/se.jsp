<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang ="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter-検索結果-</title>
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

<div class="padding-y-5 text-center" >一致する条件のユーザー情報はありません。</div>


<form action="./searchBack" method="post">
	<div align="center">
		<input class="btn" type="submit" value="検索条件に戻る" />
	</div>
</form>

<form action="./returnTop" method="post" id="check">
	<div align="center">
		<input class="btn btn-grey" type="submit" value="TOPに戻る" />
	</div>
</form>

</body>
</html>
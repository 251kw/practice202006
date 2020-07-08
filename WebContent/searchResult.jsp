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

		<script type="text/javascript">
			function Click_Sub1(check) {
			    for(i = 0; i < document.all.checked.length; i++){
			        document.all.checked[i].checked = check;
			    }
			}
		</script>

		<div class="bg-warning padding-y-5">
			<div class="padding-y-5 text-center">
				<strong>Shouter</strong>
				<span class="icon-speaker pe-2x pe-va"></span>
			</div>
		</div>

		<div class="padding-y-5 text-center" >検索結果</div>

		<jsp:useBean id="searchUser" scope="request" type="java.util.ArrayList<dto.UserDTO>" />
		<div class="padding-y-5">
			<div style="width: 75%" class="container padding-y-5">
				<form action="./multiDeleteUSerComfirmServlet" id="checks" method="post"></form>
				<form action="./updateUser" id="update" method="post"></form>
				<table class ="table table-bordered table-hover">
					<tr>
						<td>
							<input type="button" onclick="Click_Sub1(true)" value="全選択">
							<input type="button" onclick="Click_Sub1(false)" value="全解除">
						</td>
						<td class="color-main text-left">ログインID</td>
						<td class="color-main text-left">ユーザー名</td>
						<td class="color-main text-left">プロフィール</td>
						<td class="color-main text-left">アイコン</td>
						<td class="color-main text-left">更新</td>
					</tr>
					<c:forEach var="search" items="${searchUser}">
							<tr <c:if test="${search.d_flg == 1}">bgcolor="#E74C3C"</c:if>>
								<td>
									<c:choose>
			 							 <c:when test="${search.d_flg == 1}">削除されたユーザー</c:when>
			 							 <c:otherwise><input type="checkbox" name="checked" form="checks" value="${search.loginId}"  /></c:otherwise>
									</c:choose>
								</td>
								<td>${search.loginId}</td>
								<td>${search.userName}</td>
								<td>${search.profile}</td>
								<td><span class="${search.icon} pe-2x pe-va"></span></td>
								<td>
									<button class="btn btn-sm <c:if test="${search.d_flg == 1}">btn-light</c:if>" type="submit" form="update" name="loginId" value="${search.loginId}" <c:if test="${search.d_flg == 1}">disabled</c:if>>更 新</button>
								</td>
							</tr>
					 </c:forEach>
				</table>
			</div>
		</div>

		<div align="center">
			<input class="btn " type="submit" form="checks" value="選択したユーザーを削除" />
		</div>


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
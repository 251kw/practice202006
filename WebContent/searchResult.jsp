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

			document.cks.checked.checked = check;

			for (i = 0; i < document.cks.checked.length; i++) {
				document.cks.checked[i].checked = check;
			}
		}

		function myCheck(id) {
			var param = ("./updateUser?loginId=" + id);

			for (var i = 0; i < document.cks.checked.length; i++) {

				// i番目のチェックボックスがチェックされているかを判定
				if (document.cks.checked[i].checked) {
					param = (param + "&keep_cks=" + document.cks.checked[i].value);
				}
			}

			location.href = param;
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
			<form action="./multiDeleteUSerComfirmServlet" id="checks" method="post" name="cks"></form>
			<form action="./updateUser" id="update" method="post"></form>
			<table class ="table table-bordered table-hover">
				<tr>
					<td>
						<input type="button" class="btn btn-sm" onclick="Click_Sub1(true)" value="全選択">
						<input type="button" class="btn btn-sm" onclick="Click_Sub1(false)" value="全解除">
					</td>
					<td class="color-main text-left">ログインID</td>
					<td class="color-main text-left">ユーザー名</td>
					<td class="color-main text-left">プロフィール</td>
					<td class="color-main text-left">アイコン</td>
					<td class="color-main text-left">更新</td>
				</tr>
				<c:forEach var="search" items="${searchUser}">
						<tr>
							<td>
								<c:choose>
		 							 <c:when test="${search.d_flg == 1}">削除されたユーザー</c:when>
		 							 <c:otherwise>
		 							 <label class="fancy-checkbox">
		 							 	<input type="checkbox" name="checked" form="checks" value="${search.loginId}"
		 							 		 <c:forEach var="checks" items="${checkUsers}"><c:if test="${search.loginId == checks}">checked</c:if></c:forEach>
		 							 	/>
		 							 	<span></span>
		 							 </label>
		 							 </c:otherwise>
								</c:choose>
							</td>
							<td  <c:if test="${search.d_flg == 0}">class="color-main text-left"</c:if>>${search.loginId}</td>
							<td <c:if test="${search.d_flg == 0}">class="color-main text-left"</c:if>>${search.userName}</td>
							<td <c:if test="${search.d_flg == 0}">class="color-main text-left"</c:if>>${search.profile}</td>
							<td ><span class="${search.icon} pe-2x pe-va"></span></td>
							<td>
								<c:if test="${search.d_flg == 0}"><input type="button" class="btn btn-sm" value="更 新" onclick="myCheck('${search.loginId}');"></c:if>
								<c:if test="${search.d_flg == 1}"><input type="button" class="btn btn-sm btn-light" value="更 新" disabled></c:if>
							</td>
						</tr>
				 </c:forEach>
			</table>
		</div>
	</div>

	<div align="center">
		<c:if test="${requestScope.alert != null && requestScope.alert != ''}">
			<div class="color-error text-center">${requestScope.alert}</div>
		</c:if>
		<input class="btn" type="submit" form="checks" value="選択したユーザーを削除" />
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
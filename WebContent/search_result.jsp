<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>結果</title>
	<link rel="stylesheet" href="./css/skyblue.css">
	<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
	<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-10">
		<div class="text-center ">
			<h1>
				Shouter <span class="icon-speaker pe-2x pe-va"></span>
			</h1>
		</div>
	</div>
	<br>
	<%-- requestスコープにあるArrayLisst型のオブジェクトを参照 --%>
	<jsp:useBean id="users" scope="request" type="java.util.ArrayList<dto.UserDTO>" />
	<div class="padding-y-5">
		<div style="width: 70%" class="container padding-y-5">
			<form action="./udi" method="post">
				<table class="table table-striped table-bordered">
					<tr>
						<th scope="col" class="text-center"></th>
						<th scope="col" class="text-center">アイコン</th>
						<th scope="col" class="text-center">名前</th>
						<th scope="col" class="text-center">ログインID</th>
						<th scope="col" class="text-center">パスワード</th>
						<th scope="col" class="text-center">プロフィール</th>
						<th scope="col" class="text-center">削除</th>
						<th scope="col" class="text-center">更新</th>
					</tr>
					<%-- リストが空の時の処理 --%>
					<c:if test="${users==null || users.size()==0}">
						<tr>
							<td colspan="8" class="color-error text-center">検索結果がありませんでした</td>
						</tr>
					</c:if>
					<%-- リストにある要素の数だけ繰り返し --%>
					<c:forEach var="user" items="${users}">

						<tr>
							<td><input type="checkbox" name="select" value="${user.loginId}"></td>
							<td  class="text-center">
							<span class="${user.icon} pe-3x pe-va"></span></td>
							<td class="text-center">${user.userName}</td>
							<td  class="text-center">${user.loginId}</td>
							<td  class="text-center">${user.password}</td>
							<td class="text-center">${user.profile}</td>

							<td>
								<button class="btn btn-sm" type="submit" name="user"
								 value="${user.loginId},${user.password},${user.userName},${user.icon},${user.profile},削除">削除</button>
							</td>

							<td>
								<button class="btn btn-sm" type="submit" name="user"
								 value="${user.loginId},${user.password},${user.userName},${user.icon},${user.profile},更新">更新</button>
							</td>
						</tr>

					</c:forEach>
				</table>
			</form>
		</div>
	</div>
	<div style="width: 70%" class="container padding-y-5">
		<form action="search_input.jsp" method="post">
			<input class="btn" type="submit" value="戻る">
			<input type="hidden" name="loginId" value="${log}">
			<input type="hidden" name="userName" value="${Name}">
			<input type="hidden" name="icon" value="${Ic}">
			<input type="hidden" name="profile" value="${Pr}">
		</form>
	</div>
</body>
</html>
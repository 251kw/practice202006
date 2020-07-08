<%@page import="dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="controller.InsertCheckServlet"%>
<%@ page import="controller.SearchServlet"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="./css/skyblue.css">
		<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
		<link rel="stylesheet" href="./css/helper.css">
		<title>ユーザ検索結果画面</title>
	</head>
	<body>
		<div class="bg-accyell padding-y-5">
			<div class="padding-y-5 text-center">
				<font size="5"><span class="icon-diamond pe-2x pe-va"></span><strong>&nbsp;Shouter&nbsp;</strong>
				<span class="icon-diamond pe-2x pe-va"></span></font>
			</div>
		</div>
		<%request.setCharacterEncoding("UTF-8"); //文字化け防止%>
		<%-- requestスコープにあるArrayList型のオブジェクトを参照 --%>
		<jsp:useBean id="searchList" scope="request" type="java.util.ArrayList<UserDTO>" />
		<div class="container padding-y-5" style="width: 60%">
			<span class="color-main text-left padding-y-5"><font size="4">検索結果</font></span>
		</div>
		<div class="padding-y-5">
			<div style="width: 60%" class="container padding-y-5">
				<%-- リストにある要素の数だけ繰り返し --%>
				<table class="table table-striped table-bordered">
				<tr>
					<td class="color-main container padding-y-5"><font size="3">ログインID</font>
					<td class="color-main container padding-y-5"><font size="3">氏名</font>
					<td class="color-main container padding-y-5"><font size="3">アイコン</font>
					<td class="color-main container padding-y-5"><font size="3">プロフィール</font>
					<td class="color-main container padding-y-5"><font size="3">変更</font>
					<td class="color-main container padding-y-5"><font size="3">削除</font>
				</tr>
					<c:forEach var="search" items="${searchList}">
						<tr>
							<td>${search.loginId}</td>
							<td>${search.userName}</td>
							<td  class="container"><span class="${search.icon} pe-3x pe-va"></span></td>
							<td>${search.profile}</td>
							<td>
								<%-- ユーザ情報変更ボタン searchListに入っているパスワード情報も渡す --%>
								<a href="
									<c:url value="http://localhost:8080/c11/update.jsp" >
										<c:param name="loginId" value="${search.loginId}" />
										<c:param name="password" value="${search.password}" />
										<c:param name="userName" value="${search.userName}" />
										<c:param name="icon" value="${search.icon}" />
										<c:param name="profile" value="${search.profile}" />
									</c:url>">変更
								</a>
							</td>
							<td>
								<%-- ユーザ情報削除ボタン searchListに入っているパスワード情報も渡す --%>
								<a href="
									<c:url value="http://localhost:8080/c11/delete.jsp" >
										<c:param name="loginId" value="${search.loginId}" />
										<c:param name="password" value="${search.password}" />
										<c:param name="userName" value="${search.userName}" />
										<c:param name="icon" value="${search.icon}" />
										<c:param name="profile" value="${search.profile}" />
									</c:url>">削除
								</a>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%-- 検索結果が0件の場合はエラーメッセージを表示する --%>
				<c:choose>
					<c:when test="${searchList.isEmpty()}">
						<div class="color-error text-left"><font size="3">検索結果は0件です</font></div>
					</c:when>
					<c:otherwise>
						<div class="color-error text-left"><font size="3">上記の方がHitしました！</font></div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<%-- 入力情報保持したままsearch.jspに戻る --%>
		<form action="search.jsp" method="post">
			<table style="width: 60%" class="container padding-y-5 table">
				<tr>
					<td class="none text-right" colspan="2"><input class="btn" type="submit" value="戻る" /></td>
				</tr>
			</table>
			<input type="hidden" name="loginId" value="${param.loginId}">
			<input type="hidden" name="userName" value="${param.userName}">
			<input type="hidden" name="icon" value="${param.icon}">
			<input type="hidden" name="profile" value="${param.profile}">
		</form>
	</body>
</html>
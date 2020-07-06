<%@page import="dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="controller.InsertCheckServlet"%>
<%@ page import="controller.SearchServlet"%>
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
				</tr>
					<c:forEach var="search" items="${searchList}">
						<tr>
							<td>${search.loginId}</td>
							<td>${search.userName}</td>
							<td  class="container"><span class="${search.icon} pe-3x pe-va"></span></td>
							<td>${search.profile}</td>
						</tr>
					</c:forEach>
				</table>
				<%-- <c:if test="${searchList}">
					<div class="color-error text-left"><font size="3">上記の方がhitしました！</font></div>
				</c:if>
				--%>
				<c:if test="${search.isEmpty()}">
					<div class="color-error text-left"><font size="3">検索結果は0件です</font></div>
				</c:if>
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
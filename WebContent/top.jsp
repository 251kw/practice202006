<%@page import="com.sun.corba.se.impl.orb.ParserTable.TestAcceptor1"%>
<%@page import="sun.misc.Request"%>
<%@ page import="dto.ShoutDTO"%>
<%@ page import="controller.BbsServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<link rel="stylesheet" href="./css/skyblue.css">
			<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
			<link rel="stylesheet" href="./css/helper.css">
		<title>Insert title here</title>
	</head>
	<body>
		<div class="bg-main padding-y-5">
			<div class="padding-y-5 text-center">
				<font size="6"><strong>&nbsp;Shouter&nbsp;</strong><span class="icon-speaker pe-2x pe-va"></span></font>
			</div>
		</div>
		<%-- セッションスコープにあるUserDTO型のオブジェクトを参照 --%>
		<jsp:useBean id="user" scope="session" type="dto.UserDTO" />
		<div class="padding-y-5">
			<div style="width: 40%" class="container padding-y-5">
				<table class="table">
					<tr>
						<td class="none nowrap"><span class="color-main text-left"><font size="5">ログインユーザ情報</font></span></td>
					</tr>
				</table>
				<table class="table table-bordered">
		    		<tr>
		    			<td rowspan="2" class="text-centr table-cell"><span class="${user.icon} pe-3x pe-va"></span></td>
						<td class="middle" width="256">${user.userName}</td>
						<td>
							<form action="userSearch.jsp" method="post" >
								<input class="btn" type="submit" value="ユーザ検索" />
		            		</form>
		            		<form action="./logout" method="post" >
								<input class="btn" type="submit" value="ログアウト" />
		            		</form>
		        		</td>
		    		</tr>
		    		<tr>
						<td colspan="2">${user.profile}</td>
					</tr>
				</table>
			</div>
		</div>
		<%-- bbsServletに送信 --%>
		<form action="./bbs" method="post">
			<div class="container padding-y-5" style="width: 40%">
				<span class="color-main text-left container padding-y-5"><font size="5">今の気持ちを叫ぼう</font></span>
			</div>
			<table style="width: 40%" class="table container padding-y-5">
				<tr>
					<%-- 今の気持ち入力欄の名前はshout --%>
					<td class="none"><input class="form-control" type="text" name="shout" value="" size="60" id="focus" />
						<script type="text/javascript">
						    // 上記の入力欄にフォーカスを与える
						    document.getElementById('focus').focus();
						</script>
					</td>
					<td class="none"><input class="btn" type="submit" value="叫ぶ" /></td>

				</tr>
					<%-- 空の状態で叫ぶボタンが押された時のエラーメッセージを表示 --%>
					<%-- レスポンスにセットAttributeでぶち込んだアラートをrequestScope.alertで取り出す --%>
				<c:if test="${requestScope.alert != null && requestScope.alert != ''}">
					<%-- リクエストスコープのalertの値を出力 --%>
					<td colspan="2" class="color-error text-left"><c:out value="${requestScope.alert}" />
				</c:if>
			</table>
		</form>
		<%-- セッションスコープにあるArrayList型のオブジェクトを参照 --%>
		<jsp:useBean id="shouts" scope="session" type="java.util.ArrayList<dto.ShoutDTO>" />
		<%-- みんなの叫びを表示 --%>
		<div class="container padding-y-5" style="width: 40%">
			<span class="color-main text-left container padding-y-5"><font size="5">みんなの叫び</font></span>
		</div>
		<div class="padding-y-5">
			<div style="width: 40%" class="container padding-y-5">
				<%-- リストにある要素の数だけ繰り返し --%>
				<c:forEach var="shout" items="${shouts}">
					<table class="table table-striped table-bordered">
						<tr>
							<td rowspan="2" class="text-center"><span class="${shout.icon} pe-3x pe-va"></span></td>
							<td>${shout.userName}</td>
						</tr>
						<tr>
							<td>${shout.date}</td>
						</tr>
						<tr>
							<td colspan="2">
							<input id="sample" class="form-control" type="text" name="event" value="${shout.writing}" readonly>
						<%--<textarea readonly="readonly" rows="5" class="form-control">${shout.writing}</textarea></td>--%>
						</tr>
					</table>
				</c:forEach>
			</div>
		</div>
	</body>
</html>
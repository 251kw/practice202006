<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>検索結果</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
	<body>
		<div class="bg-success padding-y-5">
			<div class="padding-y-5 text-center">
				<strong>検索結果</strong>
			</div>
		</div>
		<jsp:useBean id="users" scope="request"
			type="java.util.ArrayList<dto.SearchDTO>" />
		<div class="padding-y-5">
			<div style="width: 80%" class="container padding-y-5">
				<%-- リストにある要素の数だけ繰り返し --%>
				<form action="./umdi" method="post" name="form">
					<%@ page import="javafx.scene.control.CheckBox"%>
					<%
						String[] hoge = (String[])request.getAttribute("hId");
						request.getAttribute("cbc");
						request.getAttribute("flag");
						request.getAttribute("dflag");
					%>
					<%
						//文字化け対策
						request.setCharacterEncoding("UTF-8");

						String[] icon = (String[]) request.getAttribute("icon");
						String loginId = (String) request.getAttribute("loginId");
						String userName = (String) request.getAttribute("userName");
						String profile = (String) request.getAttribute("profile");

						if(userName == null){
							userName = "";
						}
						if(loginId == null){
							loginId = "";
						}
						if(profile == null){
							profile = "";
						}
						request.setAttribute("icons", icon);

					%>
					<c:if test="${flag == 'on' }">
						<table  width="500" class="table table-striped table-bordered">
							<tr>
								<td width="50" height="8"><input type="button"  value="全部選択" onclick="allcheck(true);" class="btn btn-sm">
								<input type="button" value="全部解除" onclick="allcheck(false);" class="btn btn-sm"></td>
								<td width="50" height="10"><span class="icon-users pe-2x pe-va"></span></td>
								<td width="113" height="10">名前</td>
								<td width="120" height="10">ログインID</td>
								<td width="170" height="10">プロフィール</td>
								<td width="120" height="10">更新</td>
								<!-- <td width="120" height="10">削除</td>  -->
								<c:forEach var="users" items="${users}">
									<tr>
										<td width="50" height="10"><input type="checkbox" name="delloginId" value="${users.loginId}"  ${cbc.boxCheck(hId, users.loginId)} ></td>
										<td width="50" height="10"><span
											class="${users.icon} pe-2x pe-va"></span></td>
										<td width="130" height="10">${users.userName}</td>
										<td width="120" height="10">${users.loginId}</td>
										<td width="170" height="10">${users.profile}</td>
										<td><input type="button" onclick="location.href='./uui?loginId=${users.loginId}'"
										value="更新" class="btn"></td>
										<!--  <td><input type="button" onclick="location.href='./udi?loginId=${users.loginId}'"
										value="削除" class="btn"></td> -->
									</tr>
									<input type="hidden" name="hogeloginId" value="${users.loginId}">
								</c:forEach>
							</tr>
						</table>
					</c:if>
					<table>
						<%-- エラー検出 --%>
						<c:if test="${requestScope.alert != null && requestScope.alert != ''}">
							<tr>
								<%-- リクエストスコープのalertの値を出力 --%>
								<td colspan="2" class="color-error text-left"><c:out value="${requestScope.alert}" /></td>
							</tr>
						</c:if>
					</table>
					<c:if test="${flag == 'on' }">
						<table>
							<tr>
								<td colspan="2" class="text-left"><input type="submit" value="削除" class="btn"></td>
							</tr>
						</table>
					</c:if>
				</form>
				<script language="JavaScript" type="text/javascript">
					<!--
						function allcheck( tf ) {
						   var ElementsCount = document.form.delloginId.length; // チェックボックスの数
						   document.form.delloginId.checked = tf; // ON・OFFを切り替え
						   for( i=0 ; i<=ElementsCount - 1 ; i++ ) {
						      document.form.delloginId[i].checked = tf; // ON・OFFを切り替え
						   }
						}
					-->

				</script>
			</div>
		</div>
		<c:if test="${flag == 'on' }">
			<form action="./usib" method="post">
				<input type="hidden" name="suserName" value="<%=userName%>">
				<input type="hidden" name="sloginId" value="<%=loginId%>">
				<input type="hidden" name="sprofile" value="<%=profile%>">
				<c:choose>
					<c:when test="${length == 1}">
						<input type="hidden" name="sicon" value="<%=icon[0]%>">
						<input type="hidden" name="length" value="${length}">
						<input type="hidden" name="flag" value="<%="on"%>">
					</c:when>
					<c:when test="${length == 2}">
						<input type="hidden" name="sicon" value="<%=icon[0]%>">
						<input type="hidden" name="sicon2" value="<%=icon[1]%>">
						<input type="hidden" name="length" value="${length}">
						<input type="hidden" name="flag" value="<%="on"%>">
					</c:when>
				</c:choose>
				<table>
					<tr>
						<td colspan="2" class="text-left"><input type="submit" value="戻る" class="btn"></td>
					</tr>
				</table>
			</form>
		</c:if>
		<div class="padding-y-5">
			<div style="width: 80%" class="container padding-y-5">
				<c:if test="${dflag == 'on' }">
					<table   class="table table-striped table-bordered">
						<tr>
							<td class="text-center"><nobr>削除済みユーザー</nobr></td>
						</tr>
					</table>
					<table  width="500" class="table table-striped table-bordered">
						<tr>
							<td width="50" height="10"><span class="icon-users pe-2x pe-va"></span></td>
							<td width="113" height="10">名前</td>
							<td width="120" height="10">ログインID</td>
							<td width="170" height="10">プロフィール</td>
							<c:forEach var="dusers" items="${dusers}">
								<tr>
									<td width="50" height="10"><span class="${dusers.icon} pe-2x pe-va"></span></td>
									<td width="130" height="10">${dusers.userName}</td>
									<td width="120" height="10">${dusers.loginId}</td>
									<td width="170" height="10">${dusers.profile}</td>
								</tr>
							</c:forEach>
						</tr>
					</table>
				</c:if>
			</div>
		</div>
		<c:if test="${flag == 'off' }">
			<form action="./usib" method="post">
				<input type="hidden" name="suserName" value="<%=userName%>">
				<input type="hidden" name="sloginId" value="<%=loginId%>">
				<input type="hidden" name="sprofile" value="<%=profile%>">
				<c:choose>
					<c:when test="${length == 1}">
						<input type="hidden" name="sicon" value="<%=icon[0]%>">
						<input type="hidden" name="length" value="${length}">
						<input type="hidden" name="flag" value="<%="on"%>">
					</c:when>
					<c:when test="${length == 2}">
						<input type="hidden" name="sicon" value="<%=icon[0]%>">
						<input type="hidden" name="sicon2" value="<%=icon[1]%>">
						<input type="hidden" name="length" value="${length}">
						<input type="hidden" name="flag" value="<%="on"%>">
					</c:when>
				</c:choose>
				<table>
					<tr>
						<td colspan="2" class="text-left"><input type="submit" value="戻る" class="btn"></td>
					</tr>
				</table>
			</form>
		</c:if>
	</body>
</html>
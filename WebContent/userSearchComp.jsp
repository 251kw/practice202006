<%@page import="dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="controller.InsertCheckServlet"%>
<%@ page import="controller.SearchServlet"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
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
		<jsp:useBean id="checkedUserLogId" scope="request" class="java.util.ArrayList" type="java.util.ArrayList<String>" />
		<div class="container padding-y-5" style="width: 60%">
			<span class="color-main text-left padding-y-5"><font size="4">検索結果</font></span>
		</div>
		<div class="padding-y-5">
			<div style="width: 60%" class="container padding-y-5">
				<form name="form" action="./userMultiSelSrv" method="post">
					<table class="table table-striped table-bordered">
						<tr class="center">
							<td class="color-main center"><font size="3">ログインID</font>
							<td class="color-main container padding-y-5"><font size="3">氏名</font>
							<td class="color-main container padding-y-5"><font size="3">アイコン</font>
							<td class="color-main container padding-y-5"><font size="3">プロフィール</font>
							<td class="color-main container padding-y-5"><font size="3">変更</font>
							<td class="color-main container padding-y-5"><font size="3">チェック<br>
								<input type="checkbox" name="all" onClick="AllChecked();"></font>
							</td>
						</tr>
						<%-- リストにある要素の数だけ繰り返し --%>
						<c:forEach var="search" items="${searchList}">
							<tr>
								<td>${search.loginId}
									<c:if test="${search.dFlg == 1}">
										<div class="color-error text-left"><font size="3">※削除済みユーザ※</font></div>
									</c:if>
								</td>
								<td>${search.userName}</td>
								<td  class="container"><span class="${search.icon} pe-3x pe-va"></span></td>
								<td>${search.profile}</td>
								<td>
									<%-- ユーザ情報変更ボタン searchListに入っているパスワード情報も渡す --%>
									<a
										<c:if test="${search.dFlg == 0}">
											href="
											<c:url value="/userUpdate.jsp" >
												<c:param name="loginId" value="${search.loginId}" />
												<c:param name="password" value="${search.password}" />
												<c:param name="userName" value="${search.userName}" />
												<c:param name="icon" value="${search.icon}" />
												<c:param name="profile" value="${search.profile}" />
											</c:url>"
										</c:if>
										<c:if test="${search.dFlg == 1}">
											tabindex="-1"
										</c:if>
										>変更
									</a>
								</td>
								<td>
									<c:if test="${search.dFlg != 1}">
									<input type="checkbox" name="checkedLogId" value="${search.loginId}" onClick="DisChecked()"
									<c:forEach var="checkedLogId" items="${checkedUserLogId}">
										<c:if test="${checkedLogId == search.loginId}">
											checked="checked"
										</c:if>
									</c:forEach>
									>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</table>
					<table style="width: 100%" class="container padding-y-5">
						<%-- 検索結果が0件の場合はエラーメッセージを表示する --%>
						<c:if test="${not empty searchList}">
							<tr>
							<td nowrap><div class="color-error text-left"><font size="3">上記の方がHitしました！</font></div></td>
							<td></td>
							</tr>
						</c:if>
						<c:if test="${empty searchList}">
							<tr>
							<td nowrap><div class="color-error text-left"><font size="3">検索結果は0件です</font></div></td>
							<td></td>
							</tr>
						</c:if>
						<c:if test="${errCheckBoxMsg != null}">
							<tr>
							<td nowrap><div class="color-error text-right"><font size="3">${errCheckBoxMsg}</font></div></td>
							<td></td>
							</tr>
						</c:if>
						<tr>
							<%-- ./userMultiSelSrvへ処理を転送 --%>
							<td class="none text-right" colspan="2"><input class="btn" type="submit" value="チェック項目を削除する" /></td>
						</tr>
					</table>
				</form>
				<script type="text/javascript">
					// 「全て選択」チェックで全てにチェック付く
					function AllChecked(){
						var all = document.form.all.checked;
						for (var i=0; i<document.form.checkedLogId.length; i++){
							document.form.checkedLogId[i].checked = all;
				 		}
					}
					// チェック外す
					function DisChecked(){
						var checks = document.form.checkedLogId;
						var checksCount = 0;
						for (var i=0; i<checks.length; i++){
							if(checks[i].checked == false){
								document.form.all.checked = false;
							}else{
								checksCount += 1;
							if(checksCount == checks.length){
								document.form.all.checked = true;
								}
							}
						}
					}
				</script>
			</div>
		</div>
		<%-- 入力情報保持したままuserSearch.jspに戻る --%>
		<div style="width: 60%" class="container padding-y-5">
			<form action="userSearch.jsp" method="post">
				<table style="width: 100%" class="container padding-y-5">
					<tr>
						<td class="none text-right" colspan="2"><input class="btn" type="submit" value="戻る" /></td>
					</tr>
				</table>
				<%-- 更新/削除完了画面から./searchSrvを通ってtop.jspに戻るときまでhiddenで情報を保持する --%>
				<input type="hidden" name="reLoginId" value="<%= request.getAttribute("reLoginId") %>">
				<input type="hidden" name="rePassword" value="<%= request.getAttribute("rePassword") %>">
				<%-- userSearch.jspに、sessionの中に入れた検索条件を取得して返す --%>
				<input type="hidden" name="loginId" value="<%= (String)session.getAttribute("loginId") %>">
				<input type="hidden" name="userName" value="<%= (String)session.getAttribute("userName") %>">
				<input type="hidden" name="icon" value="<%= (String)session.getAttribute("icon") %>">
				<input type="hidden" name="profile" value="<%= (String)session.getAttribute("profile") %>">
			</form>
		</div>
	</body>
</html>
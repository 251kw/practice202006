<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="controller.InsertCheckServlet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="./css/skyblue.css">
		<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
		<link rel="stylesheet" href="./css/helper.css">
		<title>ユーザ情報削除確認画面</title>
	</head>
	<body>
		<div class="bg-accyell padding-y-5">
			<div class="padding-y-5 text-center">
				<font size="5"><span class="icon-diamond pe-2x pe-va"></span><strong>&nbsp;Shouter&nbsp;</strong><span
					class="icon-diamond pe-2x pe-va"></span></font>
			</div>
		</div>
		<%-- ./deleteSrvに飛ばす --%>
		<div class="padding-y-5">
			<form action="./deleteSrv" method="post">
				<table style="width: 40%" class="container padding-y-5 table">
					<jsp:useBean id="checkedUserlist" scope="request" type="java.util.ArrayList<dto.UserDTO>" />
					<tr>
						<td class="none" nowrap><span class="color-main text-left"><font size="4">
						ユーザ情報削除【確認画面】</font></span></td>
					</tr>

					<%-- リストにある要素の数だけ繰り返し --%>
					<c:forEach var="checkedUser" items="${checkedUserlist}">
						<table style="width: 40%" class="container padding-y-5 table table-striped table-bordered2">
							<tr>
								<%-- ログインID入力欄の名前はLoginId --%>
								<td class="color-main text-left example1">ログインID</td>
								<td class="text-left ">${checkedUser.loginId}</td>
							</tr>
							<tr>
								<%-- パスワード入力欄の名前はpassword --%>
								<td class="color-main text-left">パスワード</td>
								<td class="text-left">${checkedUser.password}</td>
							</tr>
							<tr>
								<%-- 名前入力欄の名前はuserName --%>
								<td class="color-main text-left">氏名</td>
								<td class="text-left">${checkedUser.userName}</td>
							</tr>
							<tr>
								<%-- icon入力欄の名前はicon --%>
								<td class="color-main text-left">アイコン</td>
								<td class="text-left">
								<span class="${checkedUser.icon} pe-2x pe-va"></span></td>
							</tr>
							<tr>
								<%-- プロフィール入力欄の名前はprofile --%>
								<td class="color-main text-left">プロフィール</td>
								<td class="text-left">${checkedUser.profile}</td>
							</tr>
						</table>
						<input type="hidden" name="checkedUserLogId" value="${checkedUser.loginId}">
					</c:forEach>
				</table>
				<table style="width: 40%" class="container padding-y-5 sample1">
					<tr>
						<%-- <td class="none" nowrap><span class="color-main text-left"><font
								size="4">新規会員登録</font></span></td> --%>
						<td class="color-error text-left"><font size="3">削除してよろしいでしょうか？
							<br>※ログインに使用したユーザ情報を削除すると<br>強制的にログアウトされます</font>
						</td>
						<td></td>
					</tr>
					<tr>
						<%-- ./deleteSrvへ処理を転送 --%>
						<td class="none text-right" colspan="2"><input class="btn" type="submit" value="削除する" /></td>
					</tr>
				</table>
			</form>
			<%--  取り消しの場合は./searchSrvを経由してuserSearchComp.jspに戻る --%>
			<form action="./searchSrv" method="post">
				<table style="width: 40%" class="container padding-y-5 table">
				<c:forEach var="checkedUser" items="${checkedUserlist}">
					<input type="hidden" name="checkedUserLogId" value="${checkedUser.loginId}">
				</c:forEach>
					<tr>
						<td class="none text-right" colspan="2"><input class="btn" type="submit" name="userMultiBtn" value="戻る" /></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
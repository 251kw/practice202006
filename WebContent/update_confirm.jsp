<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>更新内容確認</title>
	<link rel="stylesheet" href="./css/skyblue.css">
	<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
	<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<jsp:useBean id="user" scope="request" type="dto.UserDTO" />
	<div class="bg-success padding-y-5">
		<div class="padding-y-5 text-center">
			<strong>更新内容はこれで大丈夫？</strong>
		</div>
	</div>
	<div style="width: 40%" class="container padding-y-5">
		<form action='./uuc' method='post'>
			<TABLE >
				<%-- リクエストスコープのalertの値を出力 --%>
				<c:if test="${requestScope.alert != null && requestScope.alert != ' '}">
					<tr>
						<td colspan="2" class="color-error text-center">
							<c:out value="${requestScope.alert}" />
						</td>
					</tr>
				</c:if>
				<TR>
					<TD>
						<table border='1' class="table">
							<tr>
								<td colspan="2"class="text-center"><h5>変更前</h5>
							</tr>
							<tr>
								<th>ログインID</th>
								<td>${sessionScope.duser[0]}</td>
							</tr>
							<tr>
								<th>パスワード</th>
								<td>${sessionScope.duser[1]}</td>
							</tr>
							<tr>
								<th>名前</th>
								<td>${sessionScope.duser[2]}</td>
							</tr>
							<tr>
								<th>アイコン</th>
								<td><span class="${sessionScope.duser[3]} pe-2x pe-va"></span></td>
							</tr>
							<tr>
								<th>プロフィール</th>
								<td>${sessionScope.duser[4]}</td>
							</tr>
						</table>
					</TD>
					<TD>
						<table>
							<tr><td><h1>&nbsp;</h1></td></tr>
							<tr><td><h1> &nbsp;➡&nbsp; </h1></td></tr>
						</table>
					</TD>
					<TD>
						<table border='1' class="table">
							<tr>
								<td colspan="2" class="text-center"><h5>変更後</h5>
							</tr>
							<tr>
								<th>ログインID</th>
								<td>${user.loginId}</td>
							</tr>
							<tr>
								<th>パスワード</th>
								<td>${user.password}</td>
							</tr>
							<tr>
								<th>名前</th>
								<td>${user.userName}</td>
							</tr>
							<tr>
								<th>アイコン</th>
								<td><span class="${user.icon} pe-2x pe-va"></span></td>
							</tr>
							<tr>
								<th>プロフィール</th>
								<td>${user.profile}</td>
							</tr>
						</table>
					</TD>
				</TR>
				<TR>
					<TD colspan='3' style='text-align: center'>
						<button class="btn" type='submit' name="checks" value='OK'>OK</button>
						<input type="hidden" name="loginId" value="${user.loginId}">
						<input type="hidden" name="password" value="${user.password}">
						<input type="hidden" name="userName" value="${user.userName}">
						<input type="hidden" name="icon" value="${user.icon}">
						<input type="hidden" name="profile" value="${user.profile}">
					</TD>
				</TR>
				<TR>
					<TD>
						<button  type='submit' name="checks" value='戻る'>戻る</button>
					</TD>
				</TR>
			</TABLE>
		</form>
	</div>
</body>
</html>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>登録内容設定</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<% request.setCharacterEncoding("UTF-8");
	%>
	<div class="bg-success padding-y-5">
		<div class="padding-y-5 text-center">
			<strong>会員情報入力フォーム</strong>
		</div>
	</div>
	<div class="padding-y-5">
		<div style="width: 50%" class="padding-y-5 container ">
			<form action="./rst" method="post">
				<table border="1" class="table">
					<c:if
						test="${requestScope.alert != null && requestScope.alert != ' '}">
						<tr>
							<%-- リクエストスコープのalertの値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert}" /></td>
						</tr>
					</c:if>
					<tr>
						<th><label for="loginId"><span
								class="icon-smile pe-2x pe-va"></span>&nbsp;ログインID</label></th>
						<td><input type="text" pattern="^[0-9A-Za-z]+$" title="半角英数字"
							name="loginId" maxlength="15" id="loginId"
							value="${param.loginId}" class="form-control">

							</td>
					</tr>
					<tr>
						<th><label for="password"><span
								class="icon-key pe-2x pe-va"></span>&nbsp;パスワード</label></th>
						<td><input type="password" name="password" maxlength="15"
							id="pass" value="${param.password}" class="form-control"></td>
					</tr>

					<tr>
						<th><label for="userName"><span
								class="icon-note pe-2x pe-va"></span>&nbsp;名前</label></th>
						<td><input type="text" name="userName" id="userName"
							value="${param.userName}" class="form-control"></td>
					</tr>
					<tr>
						<th><span class="icon-users pe-2x pe-va"></span>&nbsp;アイコン</th>
						<td><input type="radio" name="icon" value="joy"
							<c:if test="${param.icon =='joy'}">checked</c:if>
							<c:if test="${param.icon ==null}">checked</c:if>>
							<span class="icon-joy pe-2x pe-va"></span>&nbsp; <input
							type="radio" name="icon" value="moon"
							<c:if test="${param.icon =='moon'}">checked</c:if>>
							<span class="icon-moon pe-2x pe-va"></span>&nbsp; <input
							type="radio" name="icon" value="smile"
							<c:if test="${param.icon =='smile'}">checked</c:if>>
							<span class="icon-smile pe-2x pe-va"></span>&nbsp; <input
							type="radio" name="icon" value="music"
							<c:if test="${param.icon =='music'}">checked</c:if>>
							<span class="icon-music pe-2x pe-va"></span>&nbsp; <input
							type="radio" name="icon" value="cloud"
							<c:if test="${param.icon =='cloud'}">checked</c:if>>
							<span class="icon-cloud pe-2x pe-va"></span>&nbsp; <input
							type="radio" name="icon" value="ball"
							<c:if test="${param.icon =='ball'}">checked</c:if>>
							<span class="icon-ball pe-2x pe-va"></span>&nbsp; <input
							type="radio" name="icon" value="coffee"
							<c:if test="${param.icon =='coffee'}">checked</c:if>>
							<span class="icon-coffee pe-2x pe-va"></span>&nbsp;</td>
					</tr>
					<tr>
						<th><span class="icon-note2 pe-2x pe-va"></span>&nbsp;プロフィール</th>
						<td><textarea rows="5" cols="20" name="profile"
								class="form-control">${param.profile}</textarea></td>
					</tr>
					<tr>
						<td colspan="2" class="text-left"><input class="btn"
							type="submit" value="送信"></td>
					</tr>

				</table>
			</form>
		</div>
	</div>
	<div class="container" style="width: 50%">
		<form action="index.jsp" method="post">
			<table class="table">
				<tr>
					<td><input class="btn" type="submit" value="戻る"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
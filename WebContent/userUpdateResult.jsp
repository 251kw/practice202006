<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>変更完了</title>
		<link rel="stylesheet" href="./css/skyblue.css">
		<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
		<link rel="stylesheet" href="./css/helper.css">
		</head>
	<body>
			<div class="bg-success padding-y-5">
				<div class="padding-y-5 text-center">
					<strong>更新完了</strong>
				</div>
			</div>
			<%
				//現在ログインしているユーザーのログインIDを取得
				String sloginId = (String)request.getAttribute("sloginId");
			%>
			<form action="./udb" method="post">
				<div class="padding-y-5">
					<div style="width: 40%" class="container padding-y-5">
						<table width="450"  class="table table-striped table-bordered">
							<tr>
								<td width="50" height="10"><span class="icon-users pe-2x pe-va"></span></td>
								<td width="113" height="10">名前</td>
								<td width="120" height="10">パスワード</td>
								<td width="170" height="10">プロフィール</td>
								<c:forEach var="update" items="${update}">
									<tr>
										<td width="50" height="10"><span
											class="${update.icon} pe-2x pe-va"></span></td>
										<td width="140" height="10">${update.userName}</td>
										<td width="120" height="10">${update.password}</td>
										<td width="170" height="10">${update.profile}</td>

									</tr>
								</c:forEach>
							</tr>
						</table>
						<input type="hidden" name="sloginId" value="<%=sloginId%>">
						<table   class="table table-striped table-bordered">
							<tr>
								<td class="color-main text-center"><nobr>上記の内容に変更されました</nobr></td>
							</tr>
						</table>
					</div>
				</div>
				<table>
					<tr>
						<td colspan="4" class="text-center"><input type="submit"value="戻る" class="btn"></td>
					</tr>
				</table>

			</form>
	</body>
</html>
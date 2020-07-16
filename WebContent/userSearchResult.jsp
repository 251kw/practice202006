<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ユーザー検索結果</title>
		<link rel="stylesheet" href="./css/skyblue.css">
		<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
		<link rel="stylesheet" href="./css/helper.css">
	</head>
	<body>
		<div class="bg-success padding-y-30">
			<div class="container padding-y-5">
				<h2 class="text-center">Shouter&nbsp;<span class="icon-speaker pe-2x pe-va"></span></h2>
			</div>
		</div>
		<a href="./RevivalUser?sloginId=${requestScope.sloginId}&suserName=${requestScope.suserName}&sicon=${requestScope.sicon}&sprofile=${requestScope.sprofile}" class="text-right hidelink" style="color: #FFFFEE; text-decoration: none;">復活の呪文</a>
		<h4 class="text-center">検索結果</h4>
		<br>
		<form action="./RelayFunction" method="post">
			<%-- リクエストスコープにあるArrayList型のオブジェクトを参照 --%>
			<jsp:useBean id="resultList" scope="request" type="java.util.ArrayList<dto.UserDTO>" />
			<%-- リストにある要素の数だけ繰り返し --%>
			<table style="width: 800px" class="table table-borderd container padding-y-5">
				<c:if test="${requestScope.notselectedalert != null && requestScope.notselectedalert != ''}">
					<tr>
						<td colspan="7" class="color-error text-center"><c:out value="${requestScope.notselectedalert}" /></td>
					</tr>
				</c:if>
				<tr class="bg-light">
					<th>
						<button type="submit" formaction="./AllSelected" name="checkall" value="${requestScope.checkall}">全選択</button>
					</th>
					<th class="text-center">ログインID</th><th class="text-center">ユーザー名</th>
					<th class="text-center">アイコン</th><th class="text-center">プロフィール</th>
					<th class="text-center">更新</th>
					<th class="text-center">削除</th>
				</tr>
				<c:forEach var="resultList" items="${resultList}">
					<tr>
						<%--チェックボックス(del_flag==0の時だけ) --%>
						<c:if test="${resultList.del_flag==0}">
							<%-- ↓チェックボックスにチェックがあるとき↓ --%>
							<c:if test="${!empty sessionScope.select}"><%-- この時は必ずcheckallは空 --%>
								<%-- チェックがあるユーザーに該当する場合 --%>
								<c:set var="endflag" value="zero" />
								<c:forEach var="sessionselect" items="${sessionScope.select}">
									<c:set var="hitflag" value="zero" />
									<c:if test="${sessionselect == resultList.loginId}">
										<td class="text-center">
											<input checked type="checkbox" name="select" value="${resultList.loginId}">
											<c:set var="hitflag" value="one" />
											<c:set var="endflag" value="one" />
										</td>
									</c:if>
								</c:forEach>

								<%-- チェックがあるユーザーに該当しない場合 --%>
								<c:if test="${hitflag == 'zero' && endflag != 'one'}">
									<td class="text-center"><input type="checkbox" name="select" value="${resultList.loginId}"></td>
								</c:if>
								<c:if test="${endflag == 'one'}">
									<c:set var="endflag" value="zero" />
								</c:if>
							</c:if>
							<%-- ↑チェックボックスにチェックがあるとき↑ --%>
							<%-- ↓チェックボックスにチェックがないとき↓ --%><%-- checkallが空じゃない可能性あり --%>
							<c:if test="${empty sessionScope.select}">
								<%-- 全選択が選択されている場合 --%>
								<c:if test="${!empty checkall}">
									<td class="text-center"><input checked type="checkbox" name="select" value="${resultList.loginId}"></td>
								</c:if>
								<%-- 全解除が選択されている場合 or 最初の検索時 or チェックを全て外した場合--%>
								<c:if test="${empty checkall}">
									<td class="text-center"><input type="checkbox" name="select" value="${resultList.loginId}"></td>
								</c:if>
							</c:if>
							<%-- ↑チェックボックスにチェックがないとき↑ --%>
							<td class="text-center">${resultList.loginId}</td>
							<td class="text-center">${resultList.userName}</td>
							<td class="text-center"><span class="${resultList.icon} pe-2x pe-va"></span></td>
							<td class="text-center">${resultList.profile}</td>
							<td class="text-center">
								<%-- 該当するログインIDを送る --%>
								<button type="submit" name="eloginId" value="${resultList.loginId}">更新</button>
							</td>
							<td class="text-center">
								<%-- 該当するログインIDを送る --%>
								<button type="submit" name="dloginId" value="${resultList.loginId}">削除</button>
							</td>
						</c:if>
						<c:if test="${resultList.del_flag==1}">
							<td class="text-center color-error">削除済み</td>
							<td class="text-center">${resultList.loginId}</td>
							<td class="text-center">${resultList.userName}</td>
							<td class="text-center"><span class="${resultList.icon} pe-2x pe-va"></span></td>
							<td class="text-center">${resultList.profile}</td>
							<td class="text-center">--</td>
							<td class="text-center">--</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			<table style="width: 800px" class="table container padding-y-5">
				<tr>
					<td colspan="4" class="text-right">
						<button type="submit" class="btn">選択したユーザーを削除</button>
						<%-- 入力情報を送る --%>
						<input type="hidden" name="sloginId" value="${requestScope.sloginId}">
						<input type="hidden" name="suserName" value="${requestScope.suserName}">
						<input type="hidden" name="sicon" value="${requestScope.sicon}">
						<input type="hidden" name="sprofile" value="${requestScope.sprofile}">
						<input type="hidden" name="checkall" value="${requestScope.checkall}">
					</td>
				</tr>
			</table>
		</form>
		<form action="./TurnSearchInput" method="post">
			<table style="width: 800px" class="table container padding-y-5">
				<tr>
					<td colspan="4" class="text-right">
						<input class="btn" type="submit" value="入力画面に戻る" />
						<input type="hidden" name="sloginId" value="${requestScope.sloginId}">
						<input type="hidden" name="suserName" value="${requestScope.suserName}">
						<input type="hidden" name="sicon" value="${requestScope.sicon}">
						<input type="hidden" name="sprofile" value="${requestScope.sprofile}">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
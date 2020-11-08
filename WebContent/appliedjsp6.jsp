<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.ArrayList,java.util.Date" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
 <html lang="ja">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>>JSPを使いこなす</title>
</head>
	<body>
		<%-- 変数宣言と値の代入 --%>
		<c:set var="score" value="50"/>

		<%-- 値の出力 --%>
		得点 : <c:out value="${score}"/><br>

		<%-- 条件の判断 --%>
		<c:if test="${score >= 0 && score <= 100 }">
		正しい値です<br>
		</c:if>

		<%-- 条件分岐 --%>
		<c:choose>
		<c:when test="${score >= 80}">合格です<br></c:when>
		<c:when test="${score >= 60}">補欠合格です<br></c:when>
		<c:when test="${score >= 30}">補修です<br></c:when>
		<c:otherwise>不合格です<br></c:otherwise>
		</c:choose>
		<hr>

		<%
			// 文字列型のリスト
			ArrayList<String> list = new ArrayList<String>();
			// リストに追加
			list.add("Hello");
			list.add("JSP");
			list.add("World!!");

			// リクエストオブジェクトに保存
			request.setAttribute("list", list);
		%>

		<%-- 繰り返し --%>
		<c:forEach var="greed" items="${list}" varStatus="status">
		${status.count} : ${greed} <br>
		</c:forEach>
		<hr>
		<c:set var="money" value="1234567"/>
		<c:set var="dol" value="3.25"/>

		<%-- 書式指定 --%>
		<fmt:formatNumber value="${money}" pattern="000,000" var="fmtMoney"/>
		<fmt:formatNumber value="${dol}" pattern="000.000" var="fmtdol"/>
		書式指定なし : ${money} 円<br>
		書式指定あり : ${fmtMoney}円 <br>
		書式指定なし : ${dol} 円<br>
		書式指定あり : ${fmtdol}円 <br>
		<hr>

		<%
			// 日付を管理するクラス
			Date date = new Date();

			// リクエストオブジェクトに保存
			request.setAttribute("date", date);
		%>

		<%-- 書式指定 --%>
		<fmt:formatDate value="${date}" pattern="y/MM/dd hh:m:s" var="fmtDate"/>
		書式指定なし : ${date}<br>
		書式指定あり : ${fmtDate} <br>
	</body>
</html>
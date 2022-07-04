<%-- nakaya --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter-ログイン-</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/Sample2.css">
</head>
<body>
<div class="bg-success padding-y-5">
	<div class="padding-y-5 text-center">
		<strong>Shouter</strong>
		<span class="icon-smile pe-2x pe-va"></span>
	</div>
</div>
<h4 class="text-center">ログインIDとパスワードを入力してください</h4>
<%-- action 属性にサーブレットを指定 --%>
<form action="./login2" method="post">
<div align=center>
  <table style="width: 400px"class="table">
    <tr>
      <%-- ログインID入力欄の名前はloginIs --%>
      	<td class="color-main text-left">ログインID</td>
      	<td class="text-left"><input class="form-control" type="text" name="loginId" value="" size="20"></td>
    </tr>
    <tr>
      <%-- パスワード入力欄の名前はpassword --%>
      <td class="color-main text-left">パスワード</td>
      <td class="text-left"><input class="form-control"
        type="password" name="password" value="" size="20" /></td>
    </tr>
    <tr>
      <td colspan="2" class="text-right"><input class="btn"
        type="submit" value="ログイン" /></td>
    </tr>
    <%-- リクエストスコープにalertがあれば --%>
    <c:if test="${requestScope.alert != null && requestScope.alert != ''}">
      <tr>
        <%-- リクエストスコープの alert の値を出力 --%>
        <td colspan="2" class="color-error text-left"><c:out value="${requestScope.alert}" /></td>
      </tr>
    </c:if>
  </table>
  </div>
</form>
</body>
</html>
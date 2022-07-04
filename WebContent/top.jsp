<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<h4 class="text-center">ログインユーザ情報</h4>
<%-- セッションスコープにある UserDTO型のオブジェクトを参照 --%>
<jsp:useBean id="user" scope="session" type="dto.UserDTO" />
<div class="padding-y-5">
  <div style="width: 40%" class="container padding-y-5">
    <%-- action 属性にサーブレットを指定 --%>
    <form action="./logout2" method="post">
      <table class="table table-bordered">
        <tr>
          <td rowspan="2" class="text-center"><span class="${user.icon} pe-3x pe-va"></span></td>
          <td width="256">${user.userName}</td>
          <td><input class="btn btn-light" type="submit" value="ログアウト" /></td>
        </tr>
        <tr>
          <td colspan="2">${user.profile}</td>
       </tr>
     </table>
   </form>
  </div>
</div>
<h4 class="text-center">今の気持ちを叫ぼう</h4>
<%-- action 属性にサーブレットを指定 --%>
<form action="./bbs2" method="post">
<div align=center>
  <table style="width: 400px" class="table">
      <tr>
      <%-- 今の気持ち入力欄の名前は shout --%>
      <td><input class="form-control" type="text" name="shout" value="" size="60" width="250" height="100" /></td>
      <td><input class="btn" type="submit" value="叫ぶ" /></td>
    </tr>
  </table>
  </div>
</form>
<h4 class="text-center">みんなの叫び</h4>
<%-- セッションスコープにあるArrayList型のオブジェクトを参照 --%>
<jsp:useBean id="shouts" scope="session" type="java.util.ArrayList<dto.ShoutDTO>" />
<div class="padding-y-5">
  <div style="width: 40%" class="container padding-y-5">
    <%-- リストにある要素の数だけ繰り返し --%>
    <c:forEach var="shout" items="${shouts}">
      <table class="table table-striped table-bordered">
       <tr>
          <td rowspan="2" class="text-center"><span class="${user.icon} pe-3x pe-va"></span></td>
          <td width="256">${user.userName}</td>
        </tr>
        <tr>
          <td><%= new Date() %></td>
        </tr>
        <tr>
          <td colspan="2"><textarea rows="5" class="form-control">${shout.writing}</textarea></td>
        </tr>
       </table>
     </c:forEach>
  </div>
</div>
</body>
</html>
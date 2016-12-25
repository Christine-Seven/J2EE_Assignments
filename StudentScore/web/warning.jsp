<%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 2016/12/19
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Warning Page</title>
</head>
<body>
<p>Warning!!! You need to take part in all tests TUT</p>
<%
    Integer onlineNum=(Integer)request.getServletContext().getAttribute("onlineNum");
    Integer touristNum=(Integer) request.getServletContext().getAttribute("touristNum");
    Integer loginNum=(Integer) request.getServletContext().getAttribute("loginNum");
%>
<p>在线人数：<%=onlineNum%></p>
<p>游客人数：<%=touristNum%></p>
<p>登录人数：<%=loginNum%></p>

<form action="/logout" method="post">
    <input type="submit" name="logout" placeholder="退出">
</form>

</body>
</html>

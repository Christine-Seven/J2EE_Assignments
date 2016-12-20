<%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 2016/12/19
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
    <h1>Ooops...不存在该学生账号QAQ</h1>
<%
    String student_id=(String) request.getAttribute("id");
    String password=(String) request.getAttribute("password");
%>
<p>id<%=student_id%></p>
<p>password<%=password%></p>
</body>
</html>

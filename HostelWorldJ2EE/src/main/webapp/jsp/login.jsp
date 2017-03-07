<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 2017/2/5
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
</head>
<body class="container">
    <div class="row" style="padding-top: 20%">
        <div class="col-md-4 col-md-offset-4">
            
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#">会员登录</a>
                </li>
                <li>
                    <a href="#">房主登录</a>
                </li>
            </ul>

            <form action="login" method="post">
                <div>
                    <s:textfield name="vipNum" placeholder="请输入会员编号"/>
                </div>
                <div>
                    <s:password name="password" placeholder="请输入密码"/>
                </div>
                <input type="submit" value="登录"/>
            </form>
        </div>
    </div>
</body>
<script src="js/bootstrap.min.js"></script>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 2016/12/18
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <div class="login-box-body">
    <p class="login-box-msg">欢迎登录</p>
    <form action="/login" method="post">
      <div>
        <label>学生ID：</label>
        <input type="text" id="id" placeholder="请输入学生id">
      </div>
      <div>
        <label>登录密码：</label>
        <input type="password" id="password" placeholder="请输入密码">
      </div>

      <input type="submit" name="submit" placeholder="登录">

    </form>
  </div>


  </body>
</html>

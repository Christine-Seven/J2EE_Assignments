<%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 2017/2/3
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form action="/DessertHouse/login" method="post" class="">
    <div class="">

      <s:textfield name="id" id="js-input" class="signin-input" placeholder="请输入销售员编号" />
      <label id="js-check-label"></label>
    </div>
    <div class="">
      <s:password name="password" class="signin-input" placeholder="请输入密码"/>
    </div>
    <input type="submit" class="button" value="登录" id="sign-btn"/>
  </form>
  </body>
</html>

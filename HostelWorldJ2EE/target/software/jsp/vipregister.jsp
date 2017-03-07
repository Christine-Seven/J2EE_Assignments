<%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 2017/2/15
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Member Register Page</title>
</head>
<body>
    <s:form action="register">
        <s:textfield name="vip.vip_name" label="会员名"></s:textfield>
        <s:textfield name="vip.vip_password" label="会员密码"></s:textfield>
        <s:select list="#{'1':'男','0':'女'}"
                  listKey="key" listValue="value"
                  name="vip.gender" label="性别" value="1"></s:select>
        <s:submit value="注册"></s:submit>
    </s:form>
</body>
</html>

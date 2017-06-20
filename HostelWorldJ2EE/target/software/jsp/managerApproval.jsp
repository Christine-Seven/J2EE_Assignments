<%@ page import="model.Hostel" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="util.ApprovalStateEnum" %>
<%@ page import="util.ApprovalVO" %>
<%@ page import="util.ApprovalType" %><%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 17/03/2017
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager Approval</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Hostel World</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" style="margin-left: -50px">Hostel World</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="main.action">首页</a></li>
                <li><a href="main.action">预订客栈</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="main.action">登录/注册</a></li>
                <li><a href="hostelRegister_hostelRegister.action">我要开店</a></li>
            </ul>

        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="navbar navbar-default navbar-fixed" style="width: 150px;height: 620px;top: 50px">
    <ul class="nav nav-pills nav-stacked" style="margin-top: 50px;">
        <li role="presentation" class="active"><a href="manager_getApply.action"><h5 style="padding-left: 20px">审批申请</h5></a></li>
        <li role="presentation"><a href="manager_getSettle.action"><h5 style="padding-left: 20px">客栈结算</h5></a></li>
        <li role="presentation"><a href="worldSta_getIndex.action"><h5 style="padding-left: 20px">指标分析</h5></a></li>
        <li role="presentation"><a href="worldSta_getMoney.action"><h5 style="padding-left: 20px">营业概览</h5></a></li>
        <li role="presentation"><a href="worldSta_getPeople.action"><h5 style="padding-left: 20px">成员管理</h5></a></li>
    </ul>
</div>


<fieldset style="position:absolute;top:100px;left:200px;width: 1000px;height: 620px">
    <legend>开店申请</legend>
    <table class="table" style="margin: 30px">
        <thead style="background-color: rgba(190, 188, 198, 0.67)">
        <tr>
            <td>#</td>
            <td>申请类型</td>
            <td>客栈编号</td>
            <td>客栈名称</td>
            <td>所在地址</td>
            <td>客栈等级</td>
            <td>客栈介绍</td>
            <td>申请日期</td>
            <td>审批状态</td>
            <td>是否通过</td>
        </tr>
        </thead>
        <tbody>
        <%
            List<ApprovalVO> approvalVOs = (ArrayList<ApprovalVO>) request.getAttribute("applyList");
            int index = 1;
            String applyType;
            for (ApprovalVO approvalVO : approvalVOs) {
                if(approvalVO.getApplyType().equals(ApprovalType.REGISTER.toString())){
                    applyType=" 申请开店";
                }else{
                    applyType="修改店铺";
                }
        %>
        <tr>
            <form id="approve_form" action="manager_managerApproval" method="post">
                <td><%=index%>
                </td>
                <td>
                    <%=applyType%>
                </td>
                <td><%=approvalVO.getHostelNum()%>
                </td>
                <td><%=approvalVO.getHostelName()%>
                </td>
                <td><%=approvalVO.getProvince()%><%=approvalVO.getCity()%><%=approvalVO.getAddress()%>
                </td>
                <td><%=approvalVO.getLevel()%>
                </td>
                <td><%=approvalVO.getHostelInfo()%>
                </td>
                <td><%=approvalVO.getApplyDate()%>
                </td>
                <td>待审批</td>
                <td>
                    <div class="row">
                        <input name="approvalNum" type="hidden" value="<%=approvalVO.getApprovalNum()%>">
                        <input name="hostelNum" type="hidden" value="<%=approvalVO.getHostelNum()%>">
                        <input type="hidden" value="1" id="btn" name="approve">
                        <button class="btn btn-default btn-primary btn-xs" type="submit"
                                onclick="this.form.btn.value='1'">
                            通过
                        </button>
                        <button class="btn btn-default btn-primary btn-xs" type="submit"
                                onclick="this.form.btn.value='-1'">
                            不通过
                        </button>
                    </div>
                </td>
            </form>
        </tr>


        <%
                index++;
            }
        %>

        </tbody>
    </table>
</fieldset>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>

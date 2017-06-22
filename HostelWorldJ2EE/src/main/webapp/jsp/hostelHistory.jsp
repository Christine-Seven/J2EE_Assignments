<%@ page import="model.Orders" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="util.PayMethod" %>
<%@ page import="util.OrderConditionEnum" %><%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 21/06/2017
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hostel Info</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/semantic.min.css" rel="stylesheet">
    <link href="css/dataTables.bootstrap.css" rel="stylesheet">

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
                <li><a href="hostelRegister_getInfo.action">客栈编号: ${id}</a></li>
                <li><a href="hostelRegister_hostelRegister.action">我要开店</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="navbar navbar-default navbar-fixed" style="width: 150px;height: 620px;top: 50px">
    <ul class="nav nav-pills nav-stacked" style="margin-top: 50px;">
        <li role="presentation"><a href="hostelRegister_getInfo.action"><h5
                style="padding-left: 20px">基本资料</h5></a></li>
        <li role="presentation"><a href="hostel_getPlan.action"><h5 style="padding-left: 20px">房间计划</h5></a></li>
        <li role="presentation"><a href="hostel_getCheckInfo.action"><h5 style="padding-left: 20px">入住登记</h5></a></li>
        <li role="presentation"  class="active"><a href="hostel_hostelHistory.action"><h5 style="padding-left: 20px">历史入住</h5></a></li>
        <li role="presentation"><a href="hostel_hostelSta.action"><h5 style="padding-left: 20px">营业概览</h5></a></li>
        <li role="presentation"><a href="hostel_hostelVip.action"><h5 style="padding-left: 20px">会员统计</h5></a></li>

    </ul>
</div>

<div style="position:absolute;top:80px;left:160px;width: 900px;height: 600px;">
    <div style="margin-left: 50px;margin-top: 30px">
        <legend>历史入住</legend>
        <table class="ui celled table" style="width: 800px;margin-left: 30px" id="historytable">
            <thead style="background-color: rgba(190, 188, 198, 0.67)">
            <tr>
                <td>#</td>
                <td>会员编号</td>
                <td>入住日期</td>
                <td>离店日期</td>
                <td>应付金额</td>
                <td>支付方式</td>
                <td>订单状态</td>
            </tr>
            </thead>
            <tbody>
            <%
                List<Orders> ordersMap = (ArrayList<Orders>) request.getAttribute("ordersList");
                int index = 1;
                for (Orders orders : ordersMap) {
                    String vipNum = orders.getVipNum();
                    String checkinDate = orders.getCheckinDate();
                    String checkoutDate = orders.getCheckoutDate();
                    Double requiredMoney = orders.getRequiredMoney();

                    String method = "";
                    PayMethod payMethod = PayMethod.valueOf(orders.getPayMethod());
                    switch (payMethod) {
                        case CASH:
                            method = "现金";
                            break;
                        case CARD:
                            method = "会员卡";
                            break;
                    }

                    String state = "";
                    OrderConditionEnum orderCondition = OrderConditionEnum.valueOf(orders.getOrderCondition());
                    switch (orderCondition) {
                        case BOOK:
                            state = "已预订";
                            break;
                        case VALID:
                            state = "已付款";
                            break;
                        case CHECKIN:
                            state = "已入住";
                            break;
                        case CHECKOUT:
                            state = "已离店";
                            break;
                        case OVERDUE:
                            state = "已过期";
                            break;
                        case CANCEL:
                            state = "已取消";
                            break;
                        case SETTLE:
                            state = "已结算";
                            break;
                        default:
                            state = "错误状态";
                            break;
                    }
            %>
            <tr>
                <td><%=index%>
                </td>
                <td><%=vipNum%>
                </td>
                <td><%=checkinDate%>
                </td>
                <td><%=checkoutDate%>
                </td>
                <td><%=requiredMoney%>
                </td>
                <td><%=method%>
                </td>
                <td><%=state%>
                </td>
            </tr>
            <%
                    index++;
                }
            %>
            </tbody>

        </table>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.dataTables.js"></script>

<script>
    $(document).ready(function() {
        $('#historytable').DataTable({
            pageTotal:15
        });
    });
</script>
</body>
</html>

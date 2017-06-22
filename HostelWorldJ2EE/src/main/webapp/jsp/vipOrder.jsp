<%@ page import="model.Orders" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.DoubleSummaryStatistics" %>
<%@ page import="util.OrderConditionEnum" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="util.PayMethod" %>
<%@ page import="util.OrderVO" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 13/03/2017
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vip Order</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
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
                <li><a href="searchHostel.action">预订客栈</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="vipInfo.action">会员名: ${id}</a></li>
                <li><a href="hostelRegister_hostelRegister.action">我要开店</a></li>
            </ul>

        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="navbar navbar-default navbar-fixed" style="width: 150px;height: 620px;top: 50px">
    <ul class="nav nav-pills nav-stacked" style="margin-top: 50px;">
        <li role="presentation"><a href="vipInfo.action"><h5 style="padding-left: 15px">我的资料</h5></a></li>
        <li role="presentation" class="active"><a href="vipOrder.action"><h5 style="padding-left: 15px">我的订单</h5></a></li>
        <li role="presentation"><a href="vipSta.action"><h5 style="padding-left: 15px">出行概览</h5></a></li>

    </ul>
</div>

<fieldset  style="position:absolute;top:100px;left:250px;width: 850px;height: 620px">
    <legend>我的订单</legend>
    <div class="col-md-offset-7 form-inline" style="margin-top: 20px">
        <label>入住日期:</label>
        <input type="date" class="form-control input-sm" style="width: 200px">
        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
        </input>
    </div>
    <table id="orderTable" class="table" style="margin: 30px">
        <thead style="background-color: rgba(190, 188, 198, 0.67)">
        <tr>
            <td>#</td>
            <td>酒店名称</td>
            <td>入住日期</td>
            <td>离店日期</td>
            <td>应付金额</td>
            <td>支付方式</td>
            <td>订单状态</td>
            <td>是否取消</td>
        </tr>
        </thead>
        <tbody>
        <%
            List<OrderVO> ordersMap=(ArrayList<OrderVO>) request.getAttribute("orders");
            int index=1;
            for(OrderVO orders:ordersMap){
                String hostelName=orders.getHostelName();
                String checkinDate=orders.getCheckinDate();
                String checkoutDate=orders.getCheckoutDate();
                Double requiredMoney= orders.getRequiredMoney();

                String method="";
                PayMethod payMethod=PayMethod.valueOf(orders.getPayMethod());
                switch (payMethod){
                    case CASH:method="现金";break;
                    case CARD:method="会员卡";break;
                }

                String state="";
                boolean cancel=false;
                OrderConditionEnum orderCondition=OrderConditionEnum.valueOf(orders.getOrderCondition());
                switch (orderCondition){
                    case BOOK:state="已预订";cancel=true;break;
                    case VALID:state="已付款";cancel=true;break;
                    case CHECKIN:state="已入住";break;
                    case CHECKOUT:state="已离店";break;
                    case OVERDUE:state="已过期";break;
                    case CANCEL:state="已取消";break;
                    default:state="错误状态";break;
                }

        %>
        <tr>
            <td><%=index%></td>
            <td><%=hostelName%></td>
            <td><%=checkinDate%></td>
            <td><%=checkoutDate%></td>
            <td><%=requiredMoney%></td>
            <td><%=method%></td>
            <td><%=state%></td>
            <%
                if(cancel){
            %>
            <td>
                <form action="cancelOrder" method="post">
                    <input type="hidden" name="orderNum" value=<%=orders.getOrderNum()%>>
                    <button class="btn btn-default btn-primary btn-xs" type="submit">
                        取消
                    </button>
                </form>

            </td>
            <%
                }else{
            %>
            <td>
                <button class="btn btn-default btn-primary btn-xs" disabled="disabled">
                    取消
                </button>
            </td>
            <%
                }
            %>
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
<script src="js/jquery.dataTables.js"></script>

<script>
    $(document).ready(function() {
        $('#orderTable').DataTable({
            pageTotal:15
        });
    });
</script>
</body>

</html>

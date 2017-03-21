<%@ page import="model.CheckInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Orders" %>
<%@ page import="util.OrderConditionEnum" %><%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 18/03/2017
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Check Info</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

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
        <li role="presentation" class="active"><a href="hostel_getCheckInfo.action"><h5 style="padding-left: 20px">
            入住登记</h5></a></li>
        <li role="presentation"><a href="hostel_hostelSta.action"><h5 style="padding-left: 20px">统计信息</h5></a></li>
    </ul>
</div>


<div style="position:absolute;top:50px;width: 1000px;height: 620px">
    <div class="col-md-12" style="position: absolute;margin-left:180px; width:1000px;height:500px">
        <div class="form-inline" style="margin-top: 50px;margin-left: 100px">
            <form action="hostel_getVipOrder" method="post">
                <div class="col-sm-offset-7 col-sm-5">
                    <label>会员编号:</label>
                    <input name="vipNum" class="form-control input-sm" style="width: 150px">
                    <button type="submit" aria-label="Search" style="border: 0;background-color: transparent">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                    </button>
                </div>
            </form>
        </div>
    </div>
    <div class="col-md-12" style="left: 180px;top:30px">
        <fieldset style="margin: 50px">
            <div class="form-inline">
                <legend style="font-size: 24px">入住登记</legend>
                <div class="col-md-offset-10">
                    <button type="button" class="btn btn-default btn-success" data-toggle="modal"
                            data-target="#checkin">
                        <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> 新增入住信息
                    </button>
                </div>
            </div>

            <table class="table" style="margin: 20px">
                <%
                    String type = String.valueOf(request.getAttribute("type"));
                    if (type.equals("vip")) {
                %>
                <thead>
                <tr style="background-color: rgba(190, 188, 198, 0.67)">
                    <td>#</td>
                    <td>会员编号</td>
                    <td>入住日期</td>
                    <td>离店日期</td>
                    <td>房间号</td>
                    <td>房间类型</td>
                    <td>已付金额</td>
                    <td>办理入住</td>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Orders> ordersList = (ArrayList<Orders>) request.getAttribute("vipOrder");
                    int index = 1;
                    for (Orders orders : ordersList) {

                %>
                <form action="hostel_vipCheckin" method="post">
                    <tr>
                        <td><%=index%>
                        </td>
                        <td>
                            <input type="hidden" value="<%=orders.getVipNum()%>" name="lodgerName">
                            <%=orders.getVipNum()%>
                        </td>
                        <td>
                            <input type="hidden" value="<%=orders.getCheckinDate()%>" name="checkinDate">
                            <%=orders.getCheckinDate()%>
                        </td>
                        <td>
                            <input type="hidden" name="checkoutDate" value="<%=orders.getCheckoutDate()%>">
                            <%=orders.getCheckoutDate()%>
                        </td>
                        <td>
                            <input name="roomNumber" class="form-control">
                        </td>
                        <td><%=orders.getRoomTypeId()%>
                        </td>
                        <%
                            if (orders.getOrderCondition().equals(OrderConditionEnum.VALID.toString())) {
                        %>
                        <td><%=orders.getPaidMoney()%>
                            <input type="hidden" name="paidMoney" value="<%=orders.getPaidMoney()%>">
                        </td>
                        <%
                        } else {
                        %>
                        <td>
                            <input name="paidMoney" class="form-control" value="<%=orders.getRequiredMoney()%>">
                        </td>
                        <%
                            }
                        %>
                        <td>
                            <input type="hidden" name="roomTypeId" value="<%=orders.getRoomTypeId()%>">
                            <input type="hidden" name="orderNum" value="<%=orders.getOrderNum()%>">
                            <button type="submit" class="btn btn-default btn-primary btn-xs">确认入住</button>
                        </td>
                    </tr>
                </form>
                <%
                        index++;
                    }
                %>

                </tbody>

                <%
                } else {
                %>

                <thead>
                <tr style="background-color: rgba(190, 188, 198, 0.67)">
                    <td>#</td>
                    <td>房间号</td>
                    <td>入住人姓名</td>
                    <td>入住日期</td>
                    <td>离店日期</td>
                    <td>消费金额</td>
                    <td>是否离店</td>
                </tr>
                </thead>
                <tbody>
                <%
                    List<CheckInfo> checkInfoList = (ArrayList<CheckInfo>) request.getAttribute("check");
                    int index=1;
                    for (CheckInfo checkInfo : checkInfoList) {
                %>
                <form action="hostel_checkout" method="post">
                    <tr>
                        <td><%=index%></td>
                        <td><%=checkInfo.getRoomNum()%></td>
                        <td>
                            <%=checkInfo.getLodgerName()%></td>
                        <td><%=checkInfo.getCheckinDate()%></td>
                        <td>
                            <%=checkInfo.getCheckoutDate()%></td>
                        <td><%=checkInfo.getPaidMoney()%></td>
                        <td>
                            <input type="hidden" name="checkNum" value="<%=checkInfo.getCheckNum()%>">
                            <button class="btn btn-default btn-primary btn-xs">确认离店</button>
                        </td>
                    </tr>
                </form>
                <%
                    }
                %>
                </tbody>
                <%
                    }
                %>

            </table>
        </fieldset>
    </div>
</div>

<!--提示框-->
<div class="modal fade" tabindex="-1" role="dialog" id="checkin" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">入住登记</h4>
            </div>
            <div class="modal-body">
                <form action="hostel_checkInfo" method="post">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td>
                                <label style="float: right">房间号:</label>
                            </td>
                            <td>
                                <input name="roomNum" class="input-sm form-control" style="width: 200px">
                            </td>
                        </tr>
                        <tr style="height: 30px">
                            <td>
                                <label style="float: right">房间类型:</label>
                            </td>
                            <td>
                                <select name="roomType" class="form-control" style="width: 200px">
                                    <option value="SingleRoom">单人间</option>
                                    <option value="DoubleRoom">双人间</option>
                                    <option value="KingsizeRoom">大床房</option>
                                </select>
                            </td>
                        </tr>
                        <tr style="height: 30px">
                            <td>
                                <label style="float: right">入住人姓名:</label>
                            </td>
                            <td>
                                <input name="lodgerName" class="form-control" style="width: 200px">
                            </td>
                        </tr>
                        <tr style="height: 30px">
                            <td>
                                <label style="float: right">入住日期:</label>
                            </td>
                            <td>
                                <input name="checkinDate" type="date" class="form-control" style="width: 200px">
                            </td>
                        </tr>
                        <tr style="height: 30px">
                            <td>
                                <label style="float: right">离店日期:</label>
                            </td>
                            <td>
                                <input name="checkoutDate" type="date" class="form-control" style="width: 200px">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label style="float: right">已付金额:</label>
                            </td>
                            <td>
                                <input name="paidMoney" class="form-control" style="width: 200px">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <button type="submit" class="btn btn-primary col-sm-offset-9">确认入住</button>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>

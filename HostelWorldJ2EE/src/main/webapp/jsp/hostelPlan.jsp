<%@ page import="model.RoomPlan" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="util.RoomPlanVO" %><%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 18/03/2017
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hostel Plan</title>
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
        <li role="presentation" class="active"><a href="hostel_getPlan.action"><h5 style="padding-left: 20px">房间计划</h5></a></li>
        <li role="presentation"><a href="hostel_getCheckInfo.action"><h5 style="padding-left: 20px">入住登记</h5></a></li>
        <li role="presentation"><a href="hostel_hostelHistory.action"><h5 style="padding-left: 20px">历史入住</h5></a></li>
        <li role="presentation"><a href="hostel_hostelSta.action"><h5 style="padding-left: 20px">营业概览</h5></a></li>
        <li role="presentation"><a href="hostel_hostelVip.action"><h5 style="padding-left: 20px">会员统计</h5></a></li>

    </ul>
</div>

<div style="position:absolute;top:50px;width: 900px;height: 620px;font-size: 16px">
    <div class="col-md-8" style="position: absolute;margin-left:180px; width:900px;height:500px">
        <fieldset style="margin: 50px">
            <legend>发布新计划</legend>
            <form action="hostel_hostelPlan" method="post">
                <div class="col-md-4">
                    <div class="row" style="margin: 20px;">
                        <label style="padding-right: 10px">房间类型:</label>
                        <select name="roomType">
                            <option value="SingleRoom">单人间</option>
                            <option value="DoubleRoom">双人间</option>
                            <option value="KingsizeRoom">大床间</option>
                        </select>
                    </div>
                    <div class="row" style="margin:20px;">
                        <label style="padding-right:10px;">开始日期:</label>
                        <input type="date" name="startDate">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="row" style="margin: 20px">
                        <label>房间数量:</label>
                        <input type="text" placeholder="请输入数字" name="roomNum">
                    </div>
                    <div class="row" style="margin:20px;">
                        <label style="padding-right:10px;">结束日期:</label>
                        <input type="date" name="endDate">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="row" style="margin: 20px">
                        <label style="padding-right:10px;">房间价格</label>
                        <input name="roomPrice">
                    </div>
                </div>
                <button class="btn btn-default btn-primary col-md-offset-11" type="submit" style="margin-top: 20px">发布计划</button>
            </form>
        </fieldset>
        <fieldset style="margin: 50px">
            <legend>历史计划</legend>

            <table class="table" style="margin: 20px">
                <thead>
                <tr style="background-color: rgba(190, 188, 198, 0.67)">
                    <td>#</td>
                    <td>发布日期</td>
                    <td>客栈编号</td>
                    <td>房间类型</td>
                    <td>房间数量</td>
                    <td>开始日期</td>
                    <td>结束日期</td>

                </tr>
                </thead>
                <tbody>
                <%
                    List<RoomPlanVO> roomPlans=(ArrayList<RoomPlanVO>)request.getAttribute("roomPlan");
                    int index=1;
                    for(RoomPlanVO roomPlan:roomPlans){
                %>
                <tr>
                    <td><%=index%></td>
                    <td><%=roomPlan.getDate()%></td>
                    <td><%=roomPlan.getHostelNum()%></td>
                    <td><%=roomPlan.getRoomType()%></td>
                    <td><%=roomPlan.getRoomNum()%></td>
                    <td><%=roomPlan.getStartDate()%></td>
                    <td><%=roomPlan.getEndDate()%></td>
                </tr>
                <%
                        index++;
                    }
                %>

                </tbody>

            </table>
        </fieldset>
    </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>

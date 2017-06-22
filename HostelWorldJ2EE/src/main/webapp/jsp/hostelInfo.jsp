<%@ page import="model.Hostel" %>
<%@ page import="util.ApprovalStateEnum" %><%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 17/03/2017
  Time: 12:25
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
        <li role="presentation" class="active"><a href="hostelRegister_getInfo.action"><h5
                style="padding-left: 20px">基本资料</h5></a></li>
            <li role="presentation"><a href="hostel_getPlan.action"><h5 style="padding-left: 20px">房间计划</h5></a></li>
            <li role="presentation"><a href="hostel_getCheckInfo.action"><h5 style="padding-left: 20px">入住登记</h5></a></li>
            <li role="presentation"><a href="hostel_hostelHistory.action"><h5 style="padding-left: 20px">历史入住</h5></a></li>
            <li role="presentation"><a href="hostel_hostelSta.action"><h5 style="padding-left: 20px">营业概览</h5></a></li>
        <li role="presentation"><a href="hostel_hostelVip.action"><h5 style="padding-left: 20px">会员统计</h5></a></li>

    </ul>
    </div>
    </ul>
</div>

<%
    Hostel hostel = (Hostel) session.getAttribute("hostel");
    if (hostel.getApprovalState().equals(ApprovalStateEnum.APPROVE.toString())) {
        String level = "";
        switch (hostel.getLevel()) {
            case 1:
                level = "一星级";
                break;
            case 2:
                level = "二星级";
                break;
            case 3:
                level = "三星级";
                break;
            case 4:
                level = "四星级";
                break;
            case 5:
                level = "五星级";
                break;
            default:
                level = "三星级";
                break;
        }

%>
<div style="position:absolute;top:50px;width: 500px;height: 620px;font-size: 16px">
    <div class="col-md-8" style="position: absolute;margin-left:180px; width:600px;height:500px">
        <fieldset style="margin: 50px">
            <legend><%=hostel.getHostelName()%>
            </legend>
            <a href="#" onclick="EditInfo()"
               style="color: #1ab7ea; float: right; margin-right: 60px;"><b>修改资料</b></a>

            <form action="hostelRegister_modify" method="post">
                <label style="margin: 20px">客栈编号:</label>
                <label><%=hostel.getHostelNum()%>
                </label>
                <br>
                <label style="margin: 20px">客栈名称:</label>
                <span id="hostelName"><%=hostel.getHostelName()%></span>
                <input id="name_input" name="hostelName" value="<%=hostel.getHostelName()%>"
                       style="position: absolute; left: 180px;margin-top: 20px; width: 200px; visibility: hidden;">
                <br>
                <label style="margin: 20px">客栈等级:</label>
                <span id="level"><%=level%></span>
                <input id="level_input" name="level" value="<%=hostel.getLevel()%>" placeholder="请输入整数"
                       style="position: absolute; left: 180px;margin-top: 20px; width: 200px; visibility: hidden;">
                <br>
                <div class="row" style="margin-top: 20px;margin-left: 5px">
                    <div class="col-xs-3">
                        <label>所在地址:</label>
                    </div>
                    <div class="col-xs-2">
                        <select id="province_input" name="province" style="visibility: hidden;position: absolute">
                            <option value="江苏省">江苏省</option>
                            <option value="上海">上海</option>
                        </select>
                        <span id="province" style="position:absolute"><%=hostel.getProvince()%></span>
                    </div>
                    <div class="col-xs-2">
                        <select id="city_input" name="city" style="visibility: hidden;position:absolute">
                            <option value="南京">南京市</option>
                            <option value="苏州">苏州市</option>
                            <option value="常州">常州市</option>
                        </select>
                        <span id="city" style="position:absolute"><%=hostel.getCity()%></span>

                    </div>
                    <div class="col-xs-5">
                        <input type="text" id="address_input" name="address"
                               style="position:absolute;width: 200px;visibility: hidden" placeholder="详细地址">
                        <span id="address" position="absolute"><%=hostel.getAddress()%></span>
                    </div>
                </div>
                <div style="margin-left: 20px;margin-top: 30px">
                    <label>客栈简介:</label>
                    <p id="hostelInfo" style="margin: 10px;position:absolute;"><%=hostel.getHostelInfo()%>
                    </p>
                    <textarea class="form-control" name="hostelInfo" id="info_input" rows="4" style="position: absolute;visibility: hidden"
                              placeholder="介绍一下你的客栈叭～"></textarea>

                    <button id="submit_btn" class="btn btn-default btn-primary col-md-offset-9" type="submit"
                                                                            style="position:absolute;margin-top: 150px;visibility: hidden">确认修改
                    </button>
                </div>

            </form>

        </fieldset>
    </div>

</div>
<%
} else if (hostel.getApprovalState().equals(ApprovalStateEnum.WAIT.toString())) {
%>
<div style="position:absolute;margin-left:180px;top:50px;width: 500px;height: 620px;font-size: 16px">
    <h3>客栈编号为<%=hostel.getHostelNum()%></h3>
    <h3>不要着急～总经理正在快马加鞭为您审批～</h3>
</div>
<%
} else {
%>
<div style="position:absolute;top:50px;margin-left:180px;width: 500px;height: 620px;font-size: 16px">
    <h3>Oooops...客栈信息没有通过审批...</h3>
</div>
<%
    }
%>
<script>
    function EditInfo() {
        document.getElementById("hostelName").style.visibility = "hidden";
        document.getElementById("level").style.visibility = "hidden";
        document.getElementById("province").style.visibility = "hidden";
        document.getElementById("city").style.visibility = "hidden";
        document.getElementById("address").style.visibility = "hidden";
        document.getElementById("hostelInfo").style.visibility = "hidden";
        document.getElementById("name_input").style.visibility = "";
        document.getElementById("level_input").style.visibility = "";
        document.getElementById("province_input").style.visibility = "";
        document.getElementById("city_input").style.visibility = "";
        document.getElementById("address_input").style.visibility = "";
        document.getElementById("info_input").style.visibility = "";
        document.getElementById("submit_btn").style.visibility = "";
    }
</script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>

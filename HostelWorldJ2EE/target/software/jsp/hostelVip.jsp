<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 18/03/2017
  Time: 12:27
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

<div class="navbar navbar-default navbar-fixed" style="width: 150px;height: 600px;top: 50px">
    <div class="navbar navbar-default navbar-fixed" style="width: 150px;height: 620px;top: 50px">
        <ul class="nav nav-pills nav-stacked" style="margin-top: 50px;">
            <li role="presentation"><a href="hostelRegister_getInfo.action"><h5
                    style="padding-left: 20px">基本资料</h5></a></li>
            <li role="presentation"><a href="hostel_getPlan.action"><h5 style="padding-left: 20px">房间计划</h5></a></li>
            <li role="presentation"><a href="hostel_getCheckInfo.action"><h5 style="padding-left: 20px">入住登记</h5></a></li>
            <li role="presentation"><a href="hostel_hostelHistory.action"><h5 style="padding-left: 20px">历史入住</h5></a></li>
            <li role="presentation"><a href="hostel_hostelSta.action"><h5 style="padding-left: 20px">营业概览</h5></a></li>
            <li role="presentation" class="active"><a href="hostel_hostelVip.action"><h5 style="padding-left: 20px">会员统计</h5></a></li>
        </ul>
    </div>
</div>
<%
    Map<Integer, Integer> vipByLevel = (Map<Integer, Integer>) request.getAttribute("vipByLevel");

%>
<div style="position:absolute;top:70px;left:160px;width: 900px;height: 600px;">
    <div style="margin-left:30px;margin-bottom: 80px">
        <legend>会员等级分布</legend>
        <div id="vipLevel" style="width:600px;height: 300px;margin-left: 50px"></div>
        <p style="font-size: 16px;font-family: 'Microsoft Sans Serif', sans-serif;color: #002a80;margin-left: 30px">
            <br>
            会员等级衡量标准: 以<b>积分</b>所在范围为指标<br><br>
            &emsp;1级:0-500分<br>
            &emsp;2级:500-1000分<br>
            &emsp;3级:1000-2000分 <br>
            &emsp;4级:2000-5000分<br>
            &emsp;5级:>5000分<br>
        </p>
        <%--<legend>会员消费水平</legend>--%>
        <%--<div id="vipPrice" style="width: 600px;height: 300px;margin-left: 50px"></div>--%>
    </div>
</div>

<script src="js/echarts.min.js"></script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script>
    var vipLevel = echarts.init(document.getElementById('vipLevel'));
    var viplevels = [];
    var levels = [];
    <%
        for(int level:vipByLevel.keySet()){
    %>
    levels.push('<%=level%>' + '级会员');
    viplevels.push({
        name: <%=level%>+'级会员',
        value:<%=vipByLevel.get(level)%>
    });
    <%
    }
    %>

    option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            data: levels
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {
                    show: true,
                    type: ['pie']
                },
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        series: [
            {
                name: '等级分布饼状图',
                type: 'pie',
                radius: [30, 110],
                center: ['50%', '40%'],
                roseType: 'area',
                data: viplevels
            }]
    };

    vipLevel.setOption(option);
</script>

</body>
</html>

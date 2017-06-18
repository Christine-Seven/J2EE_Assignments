<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="com.alibaba.fastjson.JSONPObject" %>
<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 19/03/2017
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vip Sta</title>
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
        <li role="presentation"><a href="vipOrder.action"><h5 style="padding-left: 15px">我的订单</h5></a></li>
        <li role="presentation" class="active"><a href="vipSta.action"><h5 style="padding-left: 15px">统计信息</h5></a></li>
    </ul>
</div>
<%
    Map<String, Double> priceByMonth = (HashMap<String,Double>) request.getAttribute("priceByMonth");
    Map<String,Integer> timeByMonth = (HashMap<String,Integer>) request.getAttribute("timeByMonth");
//    Map<String, Integer> timeByCity = (HashMap<String, Integer>) request.getAttribute("priceByCity");
//    Map<String, Map<String,Integer>> priceByCity = (HashMap<String, Map<String,Integer>>) request.getAttribute("priceByCity");

    int length=priceByMonth.size();
    String[] months=new String[length];
    double[] prices=new double[length];
    int[] times=new int[length];

    int i=0;
    for(String month:priceByMonth.keySet()){
//        System.out.println("month "+month);
        months[i]=month;
        prices[i]=priceByMonth.get(month);
        times[i]=timeByMonth.get(month);
        i++;
    }

%>
<div style="position: absolute;top:80px;left:160px;width: 900px;height: 600px;">

    <div class="col-md-6 col-md-offset-2"  style="top:100px;">
        <fieldset>
            <legend>出行消费</legend>
            <div id="timeAndPriceByMonth" style="width: 600px;height:400px;"></div>
        </fieldset>

    </div>

</div>


<script src="js/echarts.simple.min.js"></script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例

    var myChart=echarts.init(document.getElementById('timeAndPriceByMonth'));
    var prices=[];
    var times=[];
    var months=[];
    <%
        for(i=0;i<length;i++){
    %>
        months[<%=i%>] = '<%=months[i]%>';
        prices[<%=i%>] = '<%=prices[i]%>';
        times[<%=i%>] = '<%=times[i]%>';
    <%
    }
    %>

    option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data:['消费金额','出行次数']
        },
        xAxis: [
            {
                type: 'category',
                data: months.reverse(),
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '消费金额',
                min: 0,
                max: 1000,
                interval: 100,
                axisLabel: {
                    formatter: '{value} 元'
                }
            },
            {
                type: 'value',
                name: '出行次数',
                min: 0,
                max: 20,
                interval: 2,
                axisLabel: {
                    formatter: '{value} 次'
                }
            }
        ],
        series: [
            {
                name:'消费金额',
                type:'bar',
                data:prices
            },
            {
                name:'出行次数',
                type:'line',
                yAxisIndex: 1,
                data:times
            }
        ]
    };

    myChart.setOption(option);
</script>

</body>
</html>

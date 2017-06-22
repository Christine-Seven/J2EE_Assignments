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

<div class="navbar navbar-default navbar-fixed" style="width: 150px;height: 920px;top: 50px">
    <ul class="nav nav-pills nav-stacked" style="margin-top: 50px;">
        <li role="presentation"><a href="vipInfo.action"><h5 style="padding-left: 15px">我的资料</h5></a></li>
        <li role="presentation"><a href="vipOrder.action"><h5 style="padding-left: 15px">我的订单</h5></a></li>
        <li role="presentation" class="active"><a href="vipSta.action"><h5 style="padding-left: 15px">出行概览</h5></a></li>
    </ul>
</div>
<%
    Map<Integer, Double> priceByMonth = (HashMap<Integer, Double>) request.getAttribute("priceByMonth");
    Map<Integer, Integer> timeByMonth = (HashMap<Integer, Integer>) request.getAttribute("timeByMonth");
    Map<String, Integer> timeByCity = (HashMap<String, Integer>) request.getAttribute("timeByCity");
    Map<String, Map<String, Integer>> priceByCity = (HashMap<String, Map<String, Integer>>) request.getAttribute("priceByCity");

    int monthLength = priceByMonth.keySet().size();
    int[] months = new int[monthLength];
    double[] monthPrices = new double[monthLength];
    int[] monthTimes = new int[monthLength];

    int i = 0;
    for (int month : priceByMonth.keySet()) {
        months[i] = month;
        monthPrices[i] = priceByMonth.get(month);
        monthTimes[i] = timeByMonth.get(month);
        i++;
    }

    int cityLength = timeByCity.keySet().size();
    String[] cities = new String[cityLength];
    int[] cityTimes = new int[cityLength];
    List<Map<String, Integer>> cityPrices = new ArrayList<>();
    i = 0;
    for (String city : timeByCity.keySet()) {
        cities[i] = city;
        cityTimes[i] = timeByCity.get(city);
        cityPrices.add(priceByCity.get(city));
        i++;
    }

%>
<div style="position: absolute;top:10px;left:80px;width: 900px;height: 600px;">

    <div class="col-md-6 col-md-offset-2" style="top:100px;">
        <fieldset>
            <legend>出行消费</legend>
            <div id="timeAndPriceByMonth" style="width: 600px;height:400px;"></div>
        </fieldset>
        <fieldset>
            <legend>常去城市</legend>
            <div id="timeByCity" style="width: 600px;height:400px;"></div>
            <p>您在<label id="city"></label>的消费水平如下</p>
            <div id="priceByCity" style="width:600px;height:400px"></div>
        </fieldset>

    </div>

</div>


<script src="js/echarts.min.js"></script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例

    var myChart = echarts.init(document.getElementById('timeAndPriceByMonth'));
    var prices = [];
    var times = [];
    var months = [];
    <%
        for(i=0;i<monthLength;i++){
    %>
    months[<%=i%>] = '<%=months[i]%>';
    prices[<%=i%>] = '<%=monthPrices[i]%>';
    times[<%=i%>] = '<%=monthTimes[i]%>';
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
            data: ['消费金额', '出行次数']
        },
        xAxis: [
            {
                type: 'category',
                data: months,
                axisLabel: {
                    formatter: '{value}月份 '
                },
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
                max: 10000,
                interval: 500,
                axisLabel: {
                    formatter: '{value} 元'
                }
            },
            {
                type: 'value',
                name: '出行次数',
                min: 0,
                max: 30,
                interval: 3,
                axisLabel: {
                    formatter: '{value} 次'
                }
            }
        ],
        series: [
            {
                name: '消费金额',
                type: 'bar',
                data: prices
            },
            {
                name: '出行次数',
                type: 'line',
                yAxisIndex: 1,
                data: times
            }
        ]
    };

    myChart.setOption(option);
</script>

<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('timeByCity'));

    var cities = [];
    var times = [];
    var timeByCity = [];
    var priceByCity = new Array();

    <%
    for(i=0;i<cityLength;i++){
    %>
    cities[<%=i%>] = '<%=cities[i]%>';
    times[<%=i%>] =<%=cityTimes[i]%>;
    timeByCity.push({
        name: cities[<%=i%>],
        value: times[<%=i%>]
    });
    priceByCity[<%=i%>] = new Array();
    <%
    for(String range:cityPrices.get(i).keySet()){
    %>
    priceByCity[<%=i%>].push({
        name: '<%=range%>',
        value:<%=cityPrices.get(i).get(range)%>
    })
    <%
    }
    }
    %>

    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: cities
        },
        series: [
            {
                name: '出行次数',
                type: 'pie',
                selectedMode: 'single',
                radius: [0, '30%'],

                label: {
                    normal: {
                        position: 'inner',
                        show:false
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: timeByCity
            },
            {
                name: '出行次数',
                type: 'pie',
                radius: ['40%', '55%'],

                data: timeByCity

            }
        ]
    };

    myChart.setOption(option);
    myChart.on('click', function (param) {
        $('#city').text(param.name);
        console.log(param.dataIndex);
        priceByCityChart_init(param.dataIndex, priceByCity);

    })

    function priceByCityChart_init(cityIndex, priceByCity) {
        var priceByCityChart = echarts.init(document.getElementById('priceByCity'));

        var prices = priceByCity[cityIndex];
        var ranges = [];
        var numbers = [];
        var i = 0;
        for (; i < prices.length; i++) {
            item = prices[i];
            ranges[i] = item.name;
            numbers[i] = item.value;
            console.log(item.name + " " + item.value);
        }

        var option = {
            color: ['#c23531'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: ranges,
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '消费区间分布',
                    type: 'bar',
                    barWidth: '60%',
                    data: numbers
                }
            ]
        };
        priceByCityChart.setOption(option);
    }

</script>

</body>
</html>

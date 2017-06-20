<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 19/03/2017
  Time: 18:42
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
                <li><a href="main.action">登录/注册</a></li>
                <li><a href="hostelRegister_hostelRegister.action">我要开店</a></li>
            </ul>

        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="navbar navbar-default navbar-fixed" style="width: 150px;height: 900px;top: 50px">
    <ul class="nav nav-pills nav-stacked" style="margin-top: 50px;">
        <li role="presentation"><a href="manager_getApply.action"><h5 style="padding-left: 20px">审批申请</h5></a></li>
        <li role="presentation"><a href="manager_getSettle.action"><h5 style="padding-left: 20px">客栈结算</h5></a></li>
        <li role="presentation"><a href="worldSta_getIndex.action"><h5 style="padding-left: 20px">指标分析</h5></a></li>
        <li role="presentation" class="active"><a href="worldSta_getMoney.action"><h5 style="padding-left: 20px">
            营业概览</h5></a></li>
        <li role="presentation"><a href="worldSta_getPeople.action"><h5 style="padding-left: 20px">成员管理</h5></a></li>
    </ul>
</div>
<%
    //营业情况概览
    Map<Integer, Double> moneyByTime = (Map<Integer, Double>) request.getAttribute("moneyByTime");
    Map<String, Double> moneyByCity = (Map<String, Double>) request.getAttribute("moneyByCity");
    Map<Integer, Double> moneyByLevel = (Map<Integer, Double>) request.getAttribute("moneyByLevel");
    Map<Integer, Double> moneyByMonth = (Map<Integer, Double>) request.getAttribute("moneyByMonth");
    Map<Integer, Double> moneyBySeason = (Map<Integer, Double>) request.getAttribute("moneyBySeason");

%>
<div style="position: absolute;top:80px;left:160px;width: 1000px;height: 600px;">

    <div style="margin-left: 30px;margin-right: 50px">
        <legend>营业概览</legend>
        <div class="row">
            <div class="col-md-4" id="moneyByTime" style="width: 200px;height: 200px;margin-left:50px"></div>
            <div class="col-md-4" id="moneyByLevel" style="width: 200px;height: 200px;margin-left:50px"></div>
            <div class="col-md-4" id="moneyByCity" style="width: 200px;height: 200px;margin-left:50px"></div>
        </div>
        <legend style="margin-top: 10px">营业额走势</legend>
        <div class="btn-group" role="group" aria-label="..." style="margin-top: 10px;margin-left: 10px">
            <button type="button" class="btn btn-default" onclick="moneyByMonth()">月份</button>
            <button type="button" class="btn btn-default" onclick="moneyBySeason()">季度</button>
        </div>
        <div id="moneyLines" style="width: 600px;height: 400px;margin-left:50px;margin-top: 10px"></div>
    </div>

    <fieldset style="margin-top:50px;margin-left: 30px;margin-right: 50px">
        <legend>热门客栈</legend>
        <div class="col-md-8 col-md-offset-2" id="checkNums" style="height:300px;"></div>
    </fieldset>
</div>


<script src="js/echarts.min.js"></script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<script>
    var moneyByTimePie = echarts.init(document.getElementById("moneyByTime"));
    var times = [];
    var moneyByTime = [];

    <%
    for(int time:moneyByTime.keySet()){
    %>
    times.push('已加盟<%=time%>月');
    moneyByTime.push({
        name: '已加盟<%=time%>月',
        value:<%=moneyByTime.get(time)%>
    });
    <%
    }
    %>
    var moneyByTimeOption = {
        title: {
            text: '加盟时间与营业额',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            x: 'center',
            y: 'bottom',
            data: times
        },
        series: [{
            name: '加盟时间',
            type: 'pie',
            radius: ['30%', '45%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '10',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: moneyByTime
        }
        ]
    };
    moneyByTimePie.setOption(moneyByTimeOption);
    window.onresize = moneyByTimePie.resize;

    var moneyByLevelPie = echarts.init(document.getElementById("moneyByLevel"));
    var levels = [];
    var moneyByLevel = [];

    <%
    for(int level:moneyByLevel.keySet()){
    %>
    levels.push('<%=level%>星级');
    moneyByLevel.push({
        name: '<%=level%>星级',
        value:<%=moneyByLevel.get(level)%>
    });
    <%
    }
    %>
    var moneyByLevelOption = {
        title: {
            text: '客栈等级与营业额',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            x: 'center',
            y: 'bottom',
            data: levels
        },
        series: [
            {
                name: '客栈等级',
                type: 'pie',
                radius: ['30%', '45%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '10',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: moneyByLevel
            }]
    };
    moneyByLevelPie.setOption(moneyByLevelOption);
    window.onresize = moneyByLevelPie.resize;

    var moneyByCityPie = echarts.init(document.getElementById("moneyByCity"));
    var cities = [];
    var moneyByCity = [];
    <%
    for(String city:moneyByCity.keySet()){
    %>
    cities.push('<%=city%>');
    moneyByCity.push({
        name: '<%=city%>',
        value:<%=moneyByCity.get(city)%>
    });
    <%
    }
    %>
    var moneyByCityOption = {
        title: {
            text: '城市与营业额',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            x: 'center',
            y: 'bottom',
            data: cities
        },
        series: [
            {
                name: '地域分布',
                type: 'pie',
                radius: ['30%', '45%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '10',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: moneyByCity
            }
        ]
    };
    moneyByCityPie.setOption(moneyByCityOption);
    window.onresize = moneyByCityPie.resize;
</script>

<script>
    var moneyLines = echarts.init(document.getElementById("moneyLines"));
    var months = [];
    var moneyByMonths = [];
    <%
    for(int month:moneyByMonth.keySet()){
    %>
    months.push(<%=month%>);
    moneyByMonths.push(<%=moneyByMonth.get(month)%>);
    <%
    }
    %>
    var moneyByMonthOption = {
        title: {
            text: '每月营业额走势',
            x: 'center'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        toolbox: {
            show: true,
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            axisLabel: {
                formatter: '{value} 月'
            },
            data: months
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value} 元'
            },
            axisPointer: {
                snap: true
            }
        },
        visualMap: {
            show: false,
            dimension: 0,
            pieces: [{
                lte: 6,
                color: 'green'
            }, {
                gt: 6,
                lte: 8,
                color: 'red'
            }, {
                gt: 8,
                lte: 14,
                color: 'green'
            }, {
                gt: 14,
                lte: 17,
                color: 'red'
            }, {
                gt: 17,
                color: 'green'
            }]
        },
        series: [
            {
                name: '营业额',
                type: 'line',
                smooth: true,
                data: moneyByMonths,
                markArea: {
                    data: [[{
                        name: '暑期旺季',
                        xAxis: '6'
                    }, {
                        xAxis: '8'
                    }], [{
                        name: '国庆旺季',
                        xAxis: '10'
                    }, {
                        xAxis: '10.5'
                    }]]
                }
            }
        ]
    };
    moneyLines.setOption(moneyByMonthOption);

    var seasons = [];
    var moneyBySeasons = [];

    <%
    for(int season:moneyBySeason.keySet()){
    %>
    seasons.push(<%=season%>);
    moneyBySeasons.push(<%=moneyBySeason.get(season)%>);
    <%
    }
    %>
    var moneyBySeasonOption = {
        title: {
            text: '每季度营业额走势',
            x: 'center'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        toolbox: {
            show: true,
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            axisLabel: {
                formatter: '第{value}季度'
            },
            data: seasons
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value} 元'
            },
            axisPointer: {
                snap: true
            }
        },
        visualMap: {
            show: false,
            dimension: 0,
            pieces: [{
                lte: 6,
                color: 'green'
            }, {
                gt: 6,
                lte: 8,
                color: 'red'
            }, {
                gt: 8,
                lte: 14,
                color: 'green'
            }, {
                gt: 14,
                lte: 17,
                color: 'red'
            }, {
                gt: 17,
                color: 'green'
            }]
        },
        series: [
            {
                name: '营业额',
                type: 'line',
                smooth: true,
                data: moneyBySeasons,
            }
        ]
    };

    function moneyBySeason() {
        moneyLines.setOption(moneyBySeasonOption);
    }

    function moneyByMonth() {
        moneyLines.setOption(moneyByMonthOption);
    }

</script>

</body>
</html>

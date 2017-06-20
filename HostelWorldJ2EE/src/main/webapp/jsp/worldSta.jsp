<%@ page import="model.Orders" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="util.PayMethod" %>
<%@ page import="util.OrderConditionEnum" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
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

<div class="navbar navbar-default navbar-fixed" style="width: 150px;height: 620px;top: 50px">
    <ul class="nav nav-pills nav-stacked" style="margin-top: 50px;">
        <li role="presentation"><a href="manager_getApply.action"><h5 style="padding-left: 20px">审批申请</h5></a></li>
        <li role="presentation"><a href="manager_getSettle.action"><h5 style="padding-left: 20px">客栈结算</h5></a></li>
        <li role="presentation" class="active"><a href="worldSta_getIndex.action"><h5 style="padding-left: 20px">指标分析</h5></a></li>
        <li role="presentation"><a href="worldSta_getMoney.action"><h5 style="padding-left: 20px">营业概览</h5></a></li>
        <li role="presentation"><a href="worldSta_getPeople.action"><h5 style="padding-left: 20px">成员管理</h5></a></li>

    </ul>
</div>

    <fieldset style="margin-top: 10px;margin-right: 50px;margin-left: 30px">
        <legend>订单统计</legend>
        <table class="table" style="width: 1000px">
            <thead style="background-color: rgba(190, 188, 198, 0.67)">
            <tr>
                <td>#</td>
                <td>会员编号</td>
                <td>客栈编号</td>
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
                    String hostelNum = orders.getHostelNum();
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
                <td><%=hostelNum%>
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
                }
            %>
            </tbody>
        </table>
    </fieldset>




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
    var moneyByMonth = [];
    var moneyBySeason = [];
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

<%--<script type="text/javascript">--%>
<%--// 基于准备好的dom，初始化echarts实例--%>
<%--var myChart = echarts.init(document.getElementById('checkNums'));--%>
<%--var checkNums=<%=jsonObject%>;--%>
<%--var hostel=[];--%>
<%--for(var item in checkNums){--%>
<%--hostel.push({--%>
<%--name:item,--%>
<%--value:checkNums[item]--%>
<%--})--%>
<%--}--%>
<%--// 指定图表的配置项和数据--%>
<%--option = {--%>
<%--backgroundColor: '#ffffff',--%>

<%--title: {--%>
<%--text: '消费情况',--%>
<%--left: 'center',--%>
<%--top: 20,--%>
<%--textStyle: {--%>
<%--color: '#000000'--%>
<%--}--%>
<%--},--%>

<%--tooltip: {--%>
<%--trigger: 'item',--%>
<%--formatter: "{a} <br/>{b} : {c} ({d}%)"--%>
<%--},--%>

<%--visualMap: {--%>
<%--show: false,--%>
<%--min: 80,--%>
<%--max: 600,--%>
<%--inRange: {--%>
<%--colorLightness: [0, 1]--%>
<%--}--%>
<%--},--%>
<%--series: [--%>
<%--{--%>
<%--name: '客栈名称',--%>
<%--type: 'pie',--%>
<%--radius: '55%',--%>
<%--center: ['50%', '50%'],--%>
<%--data: hostel.sort(function (a, b) {--%>
<%--return a.value - b.value--%>
<%--}),--%>
<%--roseType: 'angle',--%>
<%--label: {--%>
<%--normal: {--%>
<%--textStyle: {--%>
<%--color: 'rgba(0,0,0, 0.3)'--%>
<%--}--%>
<%--}--%>
<%--},--%>
<%--labelLine: {--%>
<%--normal: {--%>
<%--lineStyle: {--%>
<%--color: 'rgba(0, 0, 0, 0.3)'--%>
<%--},--%>
<%--smooth: 0.2,--%>
<%--length: 10,--%>
<%--length2: 20--%>
<%--}--%>
<%--},--%>
<%--itemStyle: {--%>
<%--normal: {--%>
<%--color: '#c23531',--%>
<%--shadowBlur: 200,--%>
<%--shadowColor: 'rgba(0, 0, 0, 0.5)'--%>
<%--}--%>
<%--},--%>

<%--animationType: 'scale',--%>
<%--animationEasing: 'elasticOut',--%>
<%--animationDelay: function (idx) {--%>
<%--return Math.random() * 200;--%>
<%--}--%>
<%--}--%>
<%--]--%>

<%--};--%>

<%--// 使用刚指定的配置项和数据显示图表。--%>
<%--myChart.setOption(option);--%>
<%--</script>--%>
</body>

</html>
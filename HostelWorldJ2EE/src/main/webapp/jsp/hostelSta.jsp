<%@ page import="model.Orders" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="util.PayMethod" %>
<%@ page import="util.OrderConditionEnum" %>
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

<div class="navbar navbar-default navbar-fixed" style="width: 150px;height: 620px;top: 50px">
    <ul class="nav nav-pills nav-stacked" style="margin-top: 50px;">
        <li role="presentation"><a href="hostelRegister_getInfo.action"><h5
                style="padding-left: 20px">基本资料</h5></a></li>
        <li role="presentation"><a href="hostel_getPlan.action"><h5 style="padding-left: 20px">房间计划</h5></a></li>
        <li role="presentation"><a href="hostel_getCheckInfo.action"><h5 style="padding-left: 20px">入住登记</h5></a></li>
        <li role="presentation" class="active"><a href="hostel_hostelSta.action"><h5 style="padding-left: 20px">
            统计信息</h5></a></li>
    </ul>
</div>
<%
    //    totalProfit orderProfit ordersList
    Map<Integer, Double[]> adrByMonth = (Map<Integer, Double[]>) request.getAttribute("adrByMonth");
    Map<Integer, Double[]> occByMonth = (Map<Integer, Double[]>) request.getAttribute("occByMonth");
    Map<Integer, Double[]> revparByMonth = (Map<Integer, Double[]>) request.getAttribute("revparByMonth");
    Map<Integer, Integer> vipByLevel = (Map<Integer, Integer>) request.getAttribute("vipByLevel");
    Map<Integer, Integer[]> rangeByMonth = (Map<Integer, Integer[]>) request.getAttribute("rangeByMonth");
%>
<div style="position:absolute;top:80px;left:160px;width: 900px;height: 600px;">
    <div style="margin-left:80px;margin-bottom: 80px">
        <legend>营业情况</legend>
        <div id="hostelIndex" style="width: 800px;height: 400px;margin-left: 50px"></div>
        <legend>会员等级分布</legend>
        <div id="vipLevel" style="width:600px;height: 300px;margin-left: 50px"></div>
        <legend>会员消费水平</legend>
        <div id="vipPrice" style="width: 600px;height: 300px;margin-left: 50px"></div>
    </div>

    <div style="margin: 80px">
        <legend>历史入住</legend>
        <table class="table" style="width: 800px">
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
                }
            %>
            </tbody>
        </table>
    </div>

</div>

<script src="js/echarts.min.js"></script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<script>
    var hostelIndex = echarts.init(document.getElementById('hostelIndex'));
    var months = [];
    var adrs = [];
    var occs = [];
    var revpars = [];

    <%
    int i=0;
    for(int month:adrByMonth.keySet()){
        double adr=adrByMonth.get(month)[0];
        double occ=occByMonth.get(month)[0];
        double revpar=revparByMonth.get(month)[0];
    %>

    months[<%=i%>] = <%=month%>+'月';
    adrs[<%=i%>] =<%=adr%>;
    occs[<%=i%>] =<%=occ%>;
    revpars[<%=i%>] =<%=revpar%>;
    <%
        i++;
    }
    %>

    var colors = ['#5793f3', '#d14a61', '#675bba'];
    var option = {
        color: colors,
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        grid: {
            right: '20%'
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data: ['平均房价', '入住率', '平均每间可售房收入']
        },
        xAxis: [
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                data: months
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '入住率',
                min: 0,
                max: 1,
                interval: 0.1,
                position: 'right',
                axisLine: {
                    lineStyle: {
                        color: colors[0]
                    }
                },
                axisLabel: {
                    formatter: '{value} '
                }
            },
            {
                type: 'value',
                name: '平均房价',
                min: 0,
                max: 1000,
                position: 'right',
                offset: 80,
                axisLine: {
                    lineStyle: {
                        color: colors[1]
                    }
                },
                axisLabel: {
                    formatter: '{value} 元'
                }
            },
            {
                type: 'value',
                name: '平均每间可售房收入',
                min: 0,
                max: 2,
                position: 'left',
                axisLine: {
                    lineStyle: {
                        color: colors[2]
                    }
                },
                axisLabel: {
                    formatter: '{value} 元'
                }
            }
        ],
        series: [
            {
                name: '入住率',
                type: 'bar',
                data: occs
            },
            {
                name: '平均房价',
                type: 'bar',
                yAxisIndex: 1,
                data: adrs
            },
            {
                name: '平均每间可售房收入',
                type: 'line',
                yAxisIndex: 2,
                data: revpars
            }
        ]
    };
    hostelIndex.setOption(option);

</script>

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

<%--<script>--%>
    <%--var vipPrice = echarts.init(document.getElementById('vipPrice'));--%>
    <%--var months = [];--%>
    <%--var ranges = ['少于100', '100至200', '200至300', '300至500', '多于500'];--%>
    <%--var rangesByMax = [];--%>
    <%--var rangesByMonth = new Array();--%>
    <%--<%--%>
    <%--for(int j=0;i<5;j++){--%>
    <%--%>--%>
    <%--rangesByMax.push({--%>
        <%--name: ranges[<%=j%>],--%>
        <%--max: 10--%>
    <%--})--%>
    <%--<%--%>
    <%--}--%>
    <%--%>--%>

    <%--<%--%>
    <%--i=0;--%>
    <%--for(int month:rangeByMonth.keySet()){--%>
    <%--%>--%>
    <%--months.push(<%=month%> +'月消费');--%>
    <%--rangesByMonth[<%=i%>] = new Array();--%>
    <%--<%--%>
        <%--for(int j=0;j<5;j++){--%>
    <%--%>--%>
    <%--rangesByMonth[<%=i%>].push(<%=rangeByMonth.get(month)[j]%>);--%>
    <%--<%--%>
        <%--}--%>
        <%--i++;--%>
    <%--}--%>
    <%--%>--%>
    <%--var option = {--%>
        <%--title: {--%>
            <%--text: '会员消费水平'--%>
        <%--},--%>
        <%--tooltip: {},--%>
        <%--legend: {--%>
            <%--data: months--%>
        <%--},--%>
        <%--radar:--%>
            <%--<%--%>
            <%--for(i=0;i<rangeByMonth.keySet().size();i++){--%>
            <%--%>--%>
            <%--{--%>
                <%--indicator: rangesByMax,--%>
                <%--center: ['<%=(i%3*25)%>%', '<%=(i/3*25)%>%'],--%>
                <%--radius: 80--%>
            <%--},--%>
        <%--<%--%>
        <%--}--%>
        <%--%>--%>
        <%--series: [--%>
            <%--<%--%>
            <%--for(i=0;i<rangeByMonth.keySet().size();i++){--%>
            <%--%>--%>
            <%--{--%>
                <%--type: 'radar',--%>
                <%--tooltip: {--%>
                    <%--trigger: 'item'--%>
                <%--},--%>
                <%--itemStyle: {normal: {areaStyle: {type: 'default'}}},--%>
                <%--data: [--%>
                    <%--{--%>
                        <%--value: rangesByMonth[<%=i%>],--%>
                        <%--name: '消费区间'--%>
                    <%--}--%>
                <%--]--%>
            <%--},--%>
            <%--<%--%>
            <%--}--%>
            <%--%>--%>

    <%--}--%>
    <%--]--%>
    <%--}--%>
    <%--;--%>
    <%--vipPrice.setOption(option);--%>
<%--</script>--%>
</body>
</html>

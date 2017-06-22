<%@ page import="model.Orders" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="util.PayMethod" %>
<%@ page import="util.OrderConditionEnum" %>
<%@ page import="java.util.Map" %>
<%--
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

<div class="navbar navbar-default navbar-fixed" style="width: 150px;height: 900px;top: 50px">
    <div class="navbar navbar-default navbar-fixed" style="width: 150px;height: 620px;top: 50px">
        <ul class="nav nav-pills nav-stacked" style="margin-top: 50px;">
            <li role="presentation"><a href="hostelRegister_getInfo.action"><h5
                    style="padding-left: 20px">基本资料</h5></a></li>
            <li role="presentation"><a href="hostel_getPlan.action"><h5 style="padding-left: 20px">房间计划</h5></a></li>
            <li role="presentation"><a href="hostel_getCheckInfo.action"><h5 style="padding-left: 20px">入住登记</h5></a></li>
            <li role="presentation"><a href="hostel_hostelHistory.action"><h5 style="padding-left: 20px">历史入住</h5></a></li>
            <li role="presentation" class="active"><a href="hostel_hostelSta.action"><h5 style="padding-left: 20px">营业概览</h5></a></li>
            <li role="presentation"><a href="hostel_hostelVip.action"><h5 style="padding-left: 20px">会员统计</h5></a></li>

        </ul>
    </div>
</div>
<%
    //    totalProfit orderProfit ordersList
    Map<Integer, Double[]> adrByMonth = (Map<Integer, Double[]>) request.getAttribute("adrByMonth");
    Map<Integer, Double[]> occByMonth = (Map<Integer, Double[]>) request.getAttribute("occByMonth");
    Map<Integer, Double[]> revparByMonth = (Map<Integer, Double[]>) request.getAttribute("revparByMonth");
    Map<Integer, Integer[]> rangeByMonth = (Map<Integer, Integer[]>) request.getAttribute("rangeByMonth");
    Map<Integer,Double[]> indexByMonth=(Map<Integer,Double[]>) request.getAttribute("indexByMonth");
%>

<div style="position:absolute;top:70px;left:160px;width: 900px;height: 600px;">
    <div style="margin-left:30px;margin-bottom: 80px">
        <legend>营业情况</legend>
        <div id="hostelIndex" style="width: 800px;height: 400px;margin-left: 50px"></div>
        <p style="font-size: 16px;font-family: 'Microsoft Sans Serif', sans-serif;color: #002a80;margin-left: 50px">
            <br>
            下图为三个指标对应的细分市场指数统计，具体解释如下:<br>
            平均房价指数 : <b>高于100</b>说明达到细分市场整体水平，<b>低于100</b>说明未达到细分市场整体水平。<br>
            市场渗透指数 : 该指数用于衡量酒店<b>在某个细分市场所占有的份额</b>。<br>
            收入指数 : 该指数用于计算酒店在市场中<b>每间可售客房收入的应得市场份额</b>，<br>
            <b>高于100</b>说明达到应得市场份额。<br>
        </p>
        <div id="indexByMonth"style="width: 800px;height: 400px;margin-left: 40px;margin-top: 40px"></div>
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
    adrs[<%=i%>] =(<%=adr%>).toFixed(2);
    occs[<%=i%>] =(<%=occ*100%>).toFixed(2);
    revpars[<%=i%>] =(<%=revpar%>).toFixed(2);
    <%
        i++;
    }
    %>

    var colors = ['#5793f3', '#d14a61', '#675bba'];
    var option = {
        color: colors,
        title:{
          text:'客栈自身营业指标分析',
            x:'center'
        },
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
            data: ['平均房价', '入住率', '平均每间可售房收入'],
            y:'bottom'
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
                max: 10,
                interval: 0.5,
                position: 'right',
                axisLine: {
                    lineStyle: {
                        color: colors[0]
                    }
                },
                axisLabel: {
                    formatter: '{value}% '
                }
            },
            {
                type: 'value',
                name: '平均房价',
                min: 0,
                max: 500,
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
                max: 30,
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
    var indexByMonthLine=echarts.init(document.getElementById("indexByMonth"));
    var monthsIndex = [];
    var adrsIndex = [];
    var occsIndex = [];
    var revparsIndex = [];
    <%
    for(int month:indexByMonth.keySet()){
        double adrIndex=indexByMonth.get(month)[0];
        double occIndex=indexByMonth.get(month)[1];
        double revparIndex=indexByMonth.get(month)[2];
    %>
        monthsIndex.push(<%=month%>);
        adrsIndex.push((<%=adrIndex*100%>).toFixed(2));
        occsIndex.push((<%=occIndex*100%>).toFixed(2));
        revparsIndex.push((<%=revparIndex*100%>).toFixed(2));
    <%
    }
    %>
    var indexOption={
        color: colors,
        title:{
            text:'细分市场营业指标分析',
            x:'center'
        },
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
            show:false,
            data: ['平均房价指数', '市场渗透指数', '收入指数']
        },
        xAxis: [
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                data: monthsIndex
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '市场渗透指数',
                min: 0,
                max: 150,
                interval: 10,
                position: 'right',
                axisLine: {
                    lineStyle: {
                        color: colors[0]
                    }
                },
                axisLabel: {
                    formatter: '{value}% '
                }
            },
            {
                type: 'value',
                name: '平均房价指数',
                min: 0,
                max: 150,
                position: 'right',
                offset: 80,
                axisLine: {
                    lineStyle: {
                        color: colors[1]
                    }
                },
                axisLabel: {
                    formatter: '{value} %'
                }
            },
            {
                type: 'value',
                name: '收入指数',
                min: 0,
                max: 150,
                position: 'left',
                axisLine: {
                    lineStyle: {
                        color: colors[2]
                    }
                },
                axisLabel: {
                    formatter: '{value} %'
                }
            }
        ],
        series: [
            {
                name: '市场渗透指数',
                type: 'bar',
                data: occsIndex
            },
            {
                name: '平均房价指数',
                type: 'bar',
                yAxisIndex: 1,
                data: adrsIndex
            },
            {
                name: '收入指数',
                type: 'line',
                yAxisIndex: 2,
                data: revparsIndex
            }
        ]
    };

    indexByMonthLine.setOption(indexOption);
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

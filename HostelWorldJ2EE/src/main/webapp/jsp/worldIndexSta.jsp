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

<div class="navbar navbar-default navbar-fixed" style="width: 150px;height: 920px;top: 50px">
    <ul class="nav nav-pills nav-stacked" style="margin-top: 50px;">
        <li role="presentation"><a href="manager_getApply.action"><h5 style="padding-left: 20px">审批申请</h5></a></li>
        <li role="presentation"><a href="manager_getSettle.action"><h5 style="padding-left: 20px">客栈结算</h5></a></li>
        <li role="presentation" class="active"><a href="worldSta_getIndex.action"><h5 style="padding-left: 20px">
            指标分析</h5></a></li>
        <li role="presentation"><a href="worldSta_getMoney.action"><h5 style="padding-left: 20px">营业概览</h5></a></li>
        <li role="presentation"><a href="worldSta_getPeople.action"><h5 style="padding-left: 20px">成员管理</h5></a></li>

    </ul>
</div>
<%
    double totalProfit = (double) request.getAttribute("totalProfit");
    double hostelProfit = (double) request.getAttribute("hostelProfit");
    int vipCount = (int) request.getAttribute("vipCount");
    HashMap<String, Integer> checkNums = (HashMap<String, Integer>) request.getAttribute("checkNums");
    String jsonObject = JSONObject.toJSONString(checkNums);

    //指标分析
    Map<Integer, Double[]> adrByMonth = (Map<Integer, Double[]>) request.getAttribute("adrByMonth");
    Map<Integer, Double[]> occByMonth = (Map<Integer, Double[]>) request.getAttribute("occByMonth");
    Map<Integer, Double[]> revparByMonth = (Map<Integer, Double[]>) request.getAttribute("revparByMonth");
    Map<String, Double[]> adrByCity = (Map<String, Double[]>) request.getAttribute("adrByCity");
    Map<String, Double[]> occByCity = (Map<String, Double[]>) request.getAttribute("occByCity");
    Map<String, Double[]> revparByCity = (Map<String, Double[]>) request.getAttribute("revparByCity");
%>

<div style="position: absolute;top:80px;left:160px;width: 1000px;height: 600px;">

    <div class="row">
        <div class="col-md-4" style="padding-left: 30px">
            <div style="background: #337AB7;margin-right: 0.8em;box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);transition: 0.5s all;border-radius: 20px">
                <div class="col-md-8" style="padding-left: 30px;">
                    <h3 style="color: #fff;font-size: 2.5em;font-family: 'Carrois Gothic', sans-serif;">
                        ¥<%=totalProfit%>
                    </h3>
                    <h4 style="	font-size: 1.2em;color: #fff;margin: 0.3em 0em;font-family: 'Carrois Gothic', sans-serif;">
                        累计收益</h4>
                    <p style=" color: #fff;font-size: 0.8em;line-height: 1.8em;">赚了好多！</p>
                </div>
                <div class="col-md-4 market-update-right"
                     style="top:25px;font-size: 3em;color:#337AB7;width: 80px;height: 80px;background: #fff;text-align: center;border-radius: 49px;-o-border-radius:49px;line-height: 1.7em;">
                    <span class="glyphicon glyphicon-list-alt" aria-hidden="true" style="top:10px"></span>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>

        <div class="col-md-4">
            <div style="background: #337AB7;margin-right: 0.8em;box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);transition: 0.5s all;border-radius: 20px">
                <div class="col-md-8" style="padding-left: 30px;">
                    <h3 style="color: #fff;font-size: 2.5em;font-family: 'Carrois Gothic', sans-serif;">
                        ¥<%=hostelProfit%>
                    </h3>
                    <h4 style="	font-size: 1.2em;color: #fff;margin: 0.3em 0em;font-family: 'Carrois Gothic', sans-serif;">
                        客栈收益</h4>
                    <p style=" color: #fff;font-size: 0.8em;line-height: 1.8em;">表现不错！</p>
                </div>
                <div class="col-md-4 market-update-right"
                     style="top:25px;font-size: 3em;color:#337AB7;width: 80px;height: 80px;background: #fff;text-align: center;border-radius: 49px;-o-border-radius:49px;line-height: 1.7em;">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true" style="top:10px"></span>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>

        <div class="col-md-4">
            <div style="background: #337AB7;margin-right: 0.8em;box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);transition: 0.5s all;border-radius: 20px">
                <div class="col-md-8" style="padding-left: 30px;">
                    <h3 style="color: #fff;font-size: 2.5em;font-family: 'Carrois Gothic', sans-serif;"><%=vipCount%>
                        位</h3>
                    <h4 style="	font-size: 1.2em;color: #fff;margin: 0.3em 0em;font-family: 'Carrois Gothic', sans-serif;">
                        会员总计</h4>
                    <p style=" color: #fff;font-size: 0.8em;line-height: 1.8em;">表现不错！</p>
                </div>
                <div class="col-md-4 market-update-right"
                     style="top:25px;font-size: 3em;color:#337AB7;width: 80px;height: 80px;background: #fff;text-align: center;border-radius: 49px;-o-border-radius:49px;line-height: 1.7em;">
                    <span class="glyphicon glyphicon-arrow-up" aria-hidden="true" style="top:10px"></span>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
    <div style="margin-top:50px;margin-left: 30px;margin-right: 50px">
        <legend>指标分析</legend>
        <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-default" onclick="indexByMonth()">月份</button>
            <button type="button" class="btn btn-default" onclick="indexByCity()">城市</button>
        </div>
        <div id="worldIndex" style="width: 800px;height: 400px;margin-left: 50px"></div>
        <div>
            <p style="font-size: 16px;font-family: 'Microsoft Sans Serif', sans-serif;color: #002a80;margin-left: 30px">
                <br>
                平台平均房价指数(元) = 所有客栈总收入／所有客栈实际售出客房数<br>
                平台入住率(%) = 实际售出客房数量／可售房数量<br>
                平台平均每间可售房收入(元) = 所有客栈总收入／所有客栈可售房总数<br>
                这三个指标反映了 <b>平台总体的经营业绩水平</b><br>
            </p>
        </div>
    </div>
</div>

<script src="js/echarts.min.js"></script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<script>
    var worldIndex = echarts.init(document.getElementById('worldIndex'));
    var months = [];
    var adrsByMonths = [];
    var occsByMonths = [];
    var revparsByMonths = [];

    <%
    int i=0;
    double adr,occ,revpar;
    for(int month:adrByMonth.keySet()){
        adr=adrByMonth.get(month)[0];
        occ=occByMonth.get(month)[0];
        revpar=revparByMonth.get(month)[0];
    %>

    months[<%=i%>] = <%=month%>+'月';
    adrsByMonths[<%=i%>] =(<%=adr%>).toFixed(2);
    occsByMonths[<%=i%>] =(<%=occ*100%>).toFixed(2);
    revparsByMonths[<%=i%>] =(<%=revpar%>).toFixed(2);
    <%
        i++;
    }
    %>

    var cities = [];
    var adrsByCities = [];
    var occsByCities = [];
    var revparsByCities = [];

    <%
    i=0;
    for(String city:adrByCity.keySet()){
      adr=adrByCity.get(city)[0];
      occ=occByCity.get(city)[0];
      revpar=revparByCity.get(city)[0];
    %>

    cities[<%=i%>] = '<%=city%>';
    adrsByCities[<%=i%>] =(<%=adr%>).toFixed(2);
    occsByCities[<%=i%>] =(<%=occ*100%>).toFixed(2);
    revparsByCities[<%=i%>] =(<%=revpar%>).toFixed(2);
    <%
        i++;
    }
    %>

    var colors = ['#5793f3', '#d14a61', '#675bba'];

    var optionByMonth = {
        color: colors,
        title:{
            text:'平台月份营业指标分析',
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
                max: 30,
                interval: 5,
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
                max: 100,
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
                data: occsByMonths
            },
            {
                name: '平均房价',
                type: 'bar',
                yAxisIndex: 1,
                data: adrsByMonths
            },
            {
                name: '平均每间可售房收入',
                type: 'line',
                yAxisIndex: 2,
                data: revparsByMonths
            }
        ]
    };

    var optionByCity = {
        color: colors,
        title:{
            text:'平台地域营业指标分析',
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
                data: cities
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '入住率',
                min: 0,
                max: 8,
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
                data: occsByCities
            },
            {
                name: '平均房价',
                type: 'bar',
                yAxisIndex: 1,
                data: adrsByCities
            },
            {
                name: '平均每间可售房收入',
                type: 'line',
                yAxisIndex: 2,
                data: revparsByCities
            }
        ]
    };

    worldIndex.setOption(optionByMonth);

    function indexByMonth() {
        worldIndex.setOption(optionByMonth);
    }

    function indexByCity() {
        worldIndex.setOption(optionByCity);
    }

</script>

</body>
</html>

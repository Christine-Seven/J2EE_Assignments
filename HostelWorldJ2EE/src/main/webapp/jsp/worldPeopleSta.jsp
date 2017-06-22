<%@ page import="java.util.Set" %>
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
    <link href="css/semantic.min.css" rel="stylesheet">
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

<div class="navbar navbar-default navbar-fixed" style="width: 150px;height: 600px;top: 50px">
    <ul class="nav nav-pills nav-stacked" style="margin-top: 50px;">
        <li role="presentation"><a href="manager_getApply.action"><h5 style="padding-left: 20px">审批申请</h5></a></li>
        <li role="presentation"><a href="manager_getSettle.action"><h5 style="padding-left: 20px">客栈结算</h5></a></li>
        <li role="presentation"><a href="worldSta_getIndex.action"><h5 style="padding-left: 20px">指标分析</h5></a></li>
        <li role="presentation"><a href="worldSta_getMoney.action"><h5 style="padding-left: 20px">营业概览</h5></a></li>
        <li role="presentation" class="active"><a href="worldSta_getPeople.action"><h5 style="padding-left: 20px">
            成员管理</h5></a></li>

    </ul>
</div>
<%
    Map<Integer, Set<String>> activeByMonth = (Map<Integer, Set<String>>) request.getAttribute("activeByMonth");
    Map<String, Set<String>> activeByCity = (Map<String, Set<String>>) request.getAttribute("activeByCity");
    Map<String, Integer> cityByTime = (Map<String, Integer>) request.getAttribute("cityByTime");
    Map<String, Integer> checkNums = (Map<String, Integer>) request.getAttribute("checkNums");
%>

<div class="container">
    <div style="position:absolute;top:80px;left:160px;width: 1000px;height: 600px;">
        <div class="col-md-7" style="margin-left: 30px;margin-right: 10px;margin-top: 10px;">
            <legend>成员管理</legend>
            <div id="worldPeople" style="width: 600px;height: 350px;margin-left: 30px"></div>
            <div>
                <p style="font-family: 'Microsoft Sans Serif', sans-serif;font-size: 16px;color: #002a80">
                    <br>
                    活跃会员的标准: <b>在最近的一个季度内有消费／订单产生。</b><br>
                    <br>
                    上图展现了活跃会员在时间和地域两个维度的分布。
                    <br>
                    可以看到，<b>一月</b>和<b>四月</b>的活跃会员较多，而活跃会员最常去的城市为<b> 淮安 </b>
                    <br>
                </p>
            </div>
        </div>
        <div class="col-md-4" style="margin-left: 10px">
            <div style="margin-left: 30px;margin-top: 10px">
                <div style="margin-top:10px;margin-left: 10px;margin-right: 10px;">
                    <h4 style="font-size: 24px;font-family: 'Yuanti TC', sans-serif">热门城市</h4>
                    <div id="cityByTime">
                        <table class="ui very basic right aligned table">
                            <tbody>
                            <%
                                int i=0;
                                for(String city:cityByTime.keySet()){
                                    if(i>=5){
                                        break;
                                    }
                                    int times=cityByTime.get(city);
                            %>
                            <tr>
                                <td class="left aligned"><span style="background-color: #28a4c9;padding: 5px;border-radius: 1px"><%=i+1%></span></td>
                                <td class="left aligned"><h5 style="font-style: italic"><%=city%></h5></td>
                                <td class="right aligned"><h5 style="font-style: italic"><%=times%></h5></td>
                            </tr>
                            <%
                                    i++;
                                }
                            %>

                            </tbody>
                        </table>
                    </div>
                </div>

                <div style="margin-top:10px;margin-left: 10px;margin-right: 10px;">
                    <h4 style="font-size: 24px;font-family: 'Yuanti TC', sans-serif">热门客栈</h4>
                    <div id="checkNums">
                        <table class="ui very basic right aligned table">
                            <tbody>
                            <%
                                i=0;
                                for(String hostel:checkNums.keySet()){
                                    if(i>=5){
                                        break;
                                    }
                                    int times=checkNums.get(hostel);
                            %>
                            <tr>
                                <td class="left aligned"><span style="background-color: #28a4c9;padding: 5px;border-radius: 1px"><%=i+1%></span></td>
                                <td class="left aligned"><h5 style="font-style: italic"><%=hostel%></h5></td>
                                <td class="right aligned"><h5 style="font-style: italic"><%=times%></h5></td>
                            </tr>
                            <%
                                    i++;
                                }
                            %>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>



    </div>
</div>


<script src="js/echarts.min.js"></script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script src="js/semantic.min.js"></script>

<script>
    var worldPeople = echarts.init(document.getElementById("worldPeople"));
    var allNumberByMonth = 0;
    var allNumberByCity = 0;

    var months = [];
    var numberByMonth = [];
    var numberByCity = [];

    <%
    for(int month:activeByMonth.keySet()){
    %>
    months.push(<%=month%>);
    numberByMonth.push((<%=activeByMonth.get(month).size()%>));
    allNumberByMonth = allNumberByMonth +<%=activeByMonth.get(month).size()%>;
    <%
    }
    %>

    <%
    for(String city:activeByCity.keySet()){
    %>
    numberByCity.push({
        name: '<%=city%>',
        value:<%=activeByCity.get(city).size()%>
    });
    allNumberByCity = allNumberByCity +<%=activeByCity.get(city).size()%>;
    <%
    }
    %>

    var option = {
        backgroundColor: {
            type: 'pattern',
            repeat: 'repeat'
        },
        title: [{
            text: '月活跃会员数',
            subtext: '总计 ' + allNumberByMonth,
            x: '25%',
            textAlign: 'center'
        }, {
            text: '各地域活跃程度',
            subtext: '总计 ' + allNumberByCity,
            x: '75%',
            textAlign: 'center'
        }],
        xAxis: [{
            type: 'value',
            max: allNumberByMonth / 2,
            splitLine: {
                show: false
            }
        }],
        yAxis: [{
            type: 'category',
            data: months,
            axisLabel: {
                interval: 0,
                rotate: 30,
                formatter: '{value}月份 '

            },
            splitLine: {
                show: false
            }
        }],
        series: [{
            type: 'bar',
            stack: 'chart',
            z: 3,
            label: {
                normal: {
                    position: 'right',
                    show: true
                }
            },
            data: numberByMonth
        }, {
            type: 'pie',
            radius: [0, '30%'],
            center: ['75%', '35%'],
            data: numberByCity
        }]
    }

    worldPeople.setOption(option);

</script>
</body>
</html>

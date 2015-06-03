<%--
  Created by IntelliJ IDEA.
  User: 120378
  Date: 2015/3/05
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/page/share/taglib.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <title>录入信息</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="content－Type" content="text/html;charset=UTF-8">
    <meta http-equiv="window-target" content="_top">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>

    <link rel="stylesheet" href="<%=basePath%>css/base2.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <%--加载dwr--%>
    <script src='<%=basePath%>dwr/util.js' type="text/javascript"></script>
    <script src='<%=basePath%>dwr/engine.js' type="text/javascript"></script>
    <script src='<%=basePath%>dwr/interface/departmentService.js' type="text/javascript"></script>
    <%--自动补全/模糊搜索--%>
    <script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.autocomplete.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery.autocomplete.css"/>
    <script language="javascript" type="text/javascript"
            src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $.ajax({
                type: 'POST',
                url: "departmentAjax.action",
                data: {
                    depId: ${person.depId}
                },
                dataType: 'json',
                success: function (data) {
                    $("#dep").val(data.depName)
                }
            });
            departmentService.findAll(getResult);

            if (${person.type==0}) {
                $("#dep").css("display", "none");
                $("#depS").css("display", "");
            }
        });

        function getResult(departments) {
            var select_list = '<option value="" style="text-align: center">'
                    + '---------请选择---------' + '</option>';
            for (var i = 0; i < departments.length; i++) {
                select_list += '<option style="text-align: center" value="'
                        + departments[i].DEPTID + '">'
                        + departments[i].DEPTNAME + "</option>";
            }
            $("#depS").html(select_list);
        }

        function getData() {
            objects = []; //定义一个全局变量
            var depId;
            if (${person.type==0}) {
                depId = $("#depS option:selected").val();
            }
            else
                depId = ${person.depId};

            $.ajax({
                type: 'POST',
                url: "userInfoAjax!getUserInfo.action",
                data: {
                    dep: depId
                },
                dataType: 'json',
                success: function (data) {
                    // 组装json数据源对象objects
                    for (var i = 0; i < data.userinfos.length; i++) {
                        objects[i] = {
                            name: data.userinfos[i].BADGENUMBER + ":" + data.userinfos[i].NAME,
                            value: data.userinfos[i].BADGENUMBER
                        };
                    }
                    // 模糊匹配
                    $("#staffId").autocomplete(objects, {
                        delay: 50,
                        minChars: 1, // 表示在自动完成激活之前填入的最小字符
                        max: 200, // 表示列表里的条目数
                        matchContains: true, // 表示包含匹配,相当于模糊匹配
                        scrollHeight: 200, // 表示列表显示高度,默认高度为180

                        formatItem: function (row) {
                            return row.name;
                        },
                        formatMatch: function (row) {
                            return row.name;
                        },
                        formatResult: function (row) {
                            return row.value;
                        }
                    }).result(function (event, data, formatted) {
                        $("#userName").val(data.name.split(":")[1]);
                    });
                }
            });
        }
    </script>
</head>
<body>
<%@ include file="/page/share/menu.jsp" %>
<div id="content">
    <form action="OverTime.action" method="post">
        <table>
            <tr>
                <td colspan="2" align="center">
                    <span style="font-size: x-large">录入加班信息</span>
                </td>
            </tr>
            <tr>
                <td>部门：</td>
                <td>
                    <input readonly="readonly" type="text" id="dep">
                    <select id="depS" style="font-family: '微软雅黑';font-size: 16px;display: none"
                            onchange="getData()"></select>
                </td>
            </tr>
            <tr>
                <td>员工编号：</td>
                <td>
                    <input type="text" name="userinfo" id="staffId" onclick="getData()">
                </td>
            </tr>
            <tr>
                <td>员工姓名：</td>
                <td>
                    <input type="text" name="" id="userName" readonly="readonly">
                </td>
            </tr>
            <tr>
                <td>加班时间：</td>
                <td>
                    <input type="text" name="beginTime" class="Wdate"
                           onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                           style="width: 180px;height: 25px"/>
                    <span> ---- </span>
                    <input type="text" name="endTime" class="Wdate"
                           onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                           style="width: 180px;height: 25px"/>
                </td>
            </tr>
            <tr>
                <td>加班时长：</td>
                <td>
                    <input type="text" name="longTime" maxlength="3">
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="添加">
                    <input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </form>
</div>
<%@include file="/page/share/footer.jsp" %>
</body>
</html>

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
    <title>调岗</title>
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
    <script src='<%=basePath%>dwr/interface/userInfoService.js' type="text/javascript"></script>
    <%--自动补全/模糊搜索--%>
    <script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.autocomplete.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery.autocomplete.css"/>
    <script type="text/javascript">
        $(document).ready(function () {
            departmentService.findAll(getResult);
            userInfoService.findAll(getUser);
        });

        function getResult(departments) {
            var select_list = '<option value="" style="text-align: center">'
                    + '---------请选择---------' + '</option>';
            for (var i = 0; i < departments.length; i++) {
                select_list += '<option style="text-align: center" value="'
                        + departments[i].DEPTID + '">'
                        + departments[i].DEPTNAME + "</option>";
            }
            $("#dep").html(select_list);
        }

        function getUser(userInfos) {
            //alert("DDD");
            objects = []; //定义一个全局变量
            for (var i = 0; i < userInfos.length; i++) {
                objects[i] = {
                    name: userInfos[i].BADGENUMBER + ":" + userInfos[i].NAME,
                    value: userInfos[i].BADGENUMBER
                };
            }
            // 模糊匹配
            $(".research").autocomplete(objects, {
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
    </script>
</head>
<body>
<%@ include file="/page/share/menu.jsp" %>
<div id="content">
    <form action="UserInfo.action" method="post">
        <table>
            <tr>
                <td colspan="2" align="center">
                    <span style="font-size: x-large">用户调岗</span>
                </td>
            </tr>
            <tr>
                <td>员工工号:</td>
                <td><input type="text" name="USERID" id="userId" class="research"></td>
            </tr>
            <tr>
                <td>员工姓名:</td>
                <td><input type="text" name="name" id="userName" readonly="readonly"></td>
            </tr>
            <tr>
                <td>调岗部门：</td>
                <td>
                    <select name="depId" id="dep" style="font-family: '微软雅黑';font-size: 16px;"></select>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="调岗">
                    <input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </form>
</div>
<%@include file="/page/share/footer.jsp" %>
</body>
</html>

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
    <script type="text/javascript">
        $(document).ready(function () {
            departmentService.findAll(getResult);
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

        function getUser() {
            var userId = $('#userId')[0].value;
            $.ajax({
                type: 'POST',
                url: "UserInfo!userNameGet.action",
                data: {
                    user: userId
                },
                dataType: 'json',
                success: function (data) {
                    alert(data);
                }
            });
        }

    </script>
</head>
<body>
<%@ include file="/page/share/menu.jsp" %>
<div id="content">
    <form action="Login!addUser.action" method="post">
        <table>
            <tr>
                <td colspan="2" align="center">
                    <span style="font-size: x-large">用户调岗</span>
                </td>
            </tr>
            <tr>
                <td>员工工号</td>
                <td><input type="text" name="USERID" onchange="getUser()" id="userId"></td>
            </tr>
            <tr>
                <td>员工姓名:</td>
                <td><input type="text" name="name" id="userName" value=""></td>
            </tr>
            <tr>
                <td>调岗部门：</td>
                <td>
                    <select name="department" id="dep" style="font-family: '微软雅黑';font-size: 16px;"></select>
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

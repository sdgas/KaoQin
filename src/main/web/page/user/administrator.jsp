<%--
  Created by IntelliJ IDEA.
  User: 120378
  Date: 2014/7/25
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
    <title>部门考勤员</title>
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
    <script type="text/javascript" src="<%=basePath%>js/jquery.pstrength.js"></script>
    <%--加载dwr--%>
    <script src='<%=basePath%>dwr/util.js' type="text/javascript"></script>
    <script src='<%=basePath%>dwr/engine.js' type="text/javascript"></script>
    <script src='<%=basePath%>dwr/interface/departmentService.js' type="text/javascript"></script>
    <style type="text/css">
        tr {
            text-align: center;
        }

        td {
            width: 150px;
        }

        #content {
            margin-left: 350px;
        }
    </style>

    <script src="<%=basePath%>js/jquery.min.js"></script>
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
            $("#depS").html(select_list);
        }

        function sure() {
            var pwd = $("#pwd1")[0].value;
            if (pwd.length < 6) {
                alert("密码长度必须大于六位");
                return false;
            }
            else if ($('#pwd2')[0].value != pwd) {
                alert("两次输入的密码不一致");
                return false;
            }
        }

        function sure1() {
            var pwd = $("#pwd1")[0].value;
            if (pwd.length < 6) {
                alert("密码长度必须大于六位");
                return false;
            }
            else if ($('#pwd2')[0].value != pwd) {
                alert("两次输入的密码不一致");
                return false;
            } else {
                $('#MyForm')[0].submit();
                return true;
            }
        }

        function checkNum(userId) {
            if (!/^\d+$/.test(userId)) {
                alert("请输入正确的工号，全为数字");
            }
        }
    </script>
</head>
<body>
<%@ include file="/page/share/menu.jsp" %>
<div id="content">
    <form action="Login!addAdmin.action" method="post" id="MyForm">
        <table>
            <tr>
                <td colspan="2" align="center">
                    <span style="font-size: x-large">增加部门考勤员</span>
                </td>
            </tr>
            <tr>
                <td>工号：</td>
                <td>
                    <input name="USERID" type="text" onchange="checkNum(this.value)">
                </td>
            </tr>
            <tr>
                <td>用户名：</td>
                <td>
                    <input name="name" type="text">
                </td>
            </tr>
            <tr>
                <td>所在部门：</td>
                <td>
                    <select id="depS" name="depId" style="font-family: '微软雅黑';font-size: 16px;"></select>
                </td>
            </tr>
            <tr>
                <td>用户类型：</td>
                <td>
                    <input name="typeS" type="radio" value="0">超级管理员
                    <input name="typeS" type="radio" value="1" checked="checked">部门考勤员
                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <input type="password" name="pwd" id="pwd1" onchange="pwdLength()"
                           onKeyUp="pwStrength(this.value)" onBlur="pwStrength(this.value)">
                </td>
                <td style="height:6px;width: 40px" id="strength_L" bgcolor="#f5f5f5">弱</td>
                <td style="height:5px;width: 40px" id="strength_M" bgcolor="#f5f5f5">中</td>
                <td style="height:5px;width: 40px" id="strength_H" bgcolor="#f5f5f5">强</td>

            </tr>
            <tr>
                <td>确认密码：</td>
                <td>
                    <input type="password" name="pwd1" id="pwd2" onchange="sure()">
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="button" value="增加" onclick="return sure1();">
                    <input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </form>
</div>
<%@include file="/page/share/footer.jsp" %>
</body>
</html>

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
    <title>中晚班补贴</title>
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
</head>
<body>
<%@ include file="/page/share/menu.jsp" %>
<div id="content">
    <form action="File!createExcelBySch.action" method="post">
        <table>
            <tr>
                <td colspan="2" align="center">
                    <span style="font-size: x-large">一线员工中晚班补贴</span>
                </td>
            </tr>
            <tr>
                <td>部门：</td>
                <td>
                    <select id="depS" name="depS" style="font-family: '微软雅黑';font-size: 16px;">
                        <option value="" style="text-align: center">---------请选择---------</option>
                        <option style="text-align: center" value="生成运行部">生成运行部</option>
                        <option style="text-align: center" value="客户服务部">客户服务部</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>

                    <input type="submit" value="生成" class="input"/>

                </td>
            </tr>
        </table>
    </form>
</div>
<%@include file="/page/share/footer.jsp" %>
</body>
</html>

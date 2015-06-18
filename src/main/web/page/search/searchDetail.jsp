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
    <title>打卡明细查询</title>
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
            var select_list = '<option value="" style="text-align: center;">'
                    + '--请选择--' + '</option>';
            for (var i = 0; i < departments.length; i++) {
                select_list += '<option style="text-align: center" value="'
                        + departments[i].DEPTID + '">'
                        + departments[i].DEPTNAME + "</option>";
            }
            $("#depS").html(select_list);
        }

    </script>
</head>
<body>
<%@ include file="/page/share/menu.jsp" %>
<div id="content">
    <form action="#" method="post" onsubmit="comfer()">
        <table>
            <tr>
                <td colspan="12" align="center">
                    <span style="font-size: x-large">打卡明细查询</span>
                </td>
            </tr>
            <tr>
                <td style="width: 90px">部门：</td>
                <td>
                    <input readonly="readonly" type="text" id="dep">
                    <select id="depS" style="width: 100px;font-family: '微软雅黑';font-size: 16px;display: none"
                            onchange="getData()"></select>
                </td>
                <td style="width: 120px">员工姓名：</td>
                <td>
                    <input type="text" name="userInfo" id="userName" style="width: 100px">
                </td>
                <td style="width: 120px">查询时间：</td>
                <td>
                    <input type="text" name="month" class="Wdate"
                           onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})" style="width: 120px"/>
                </td>
                <td align="center" style="width: 80px">
                    <input type="submit" value="查询">
                </td>
            </tr>
        </table>
    </form>

</div>
<%@include file="/page/share/footer.jsp" %>
</body>
</html>

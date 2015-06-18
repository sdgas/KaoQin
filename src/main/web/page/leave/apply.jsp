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
    <%--<meta http-equiv="X-UA-Compatible" content="IE=9"/>--%>

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
    <script src='<%=basePath%>dwr/interface/vacationService.js' type="text/javascript"></script>
    <%--自动补全/模糊搜索--%>
    <script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.autocomplete.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery.autocomplete.css"/>
    <script language="javascript" type="text/javascript"
            src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            if (${person.type==0}) {
                $("#dep").css("display", "none");
                $("#depS").css("display", "");
            }
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
            vacationService.findAll(getVacation);
        });

        function getVacation(vacations) {
            var select_list = '<option value="" style="text-align: center">'
                    + '---------请选择---------' + '</option>';
            for (var i = 0; i < vacations.length; i++) {
                select_list += '<option style="text-align: center" value="'
                        + vacations[i].symbol + '">'
                        + vacations[i].vacation + "</option>";
            }
            $("#va").html(select_list);
        }

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
    </script>
</head>
<body>
<%@ include file="/page/share/menu.jsp" %>
<div id="content">
    <form action="VacationInfo.action" method="post">
        <table>
            <tr>
                <td colspan="2" align="center">
                    <span style="font-size: x-large">录入休假信息</span>
                </td>
            </tr>
            <tr>
                <td>部门：</td>
                <td>
                    <input readonly="readonly" type="text" id="dep" name="depId">
                    <select id="depS" name="depId" style="font-family: '微软雅黑';font-size: 16px;display: none"></select>
                </td>
            </tr>
            <tr>
                <td>员工姓名：</td>
                <td>
                    <input type="text" name="staff" id="userName">
                </td>
            </tr>
            <tr>
                <td>休假类型：</td>
                <td>
                    <select id="va" name="vacationId" style="font-family: '微软雅黑';font-size: 16px;"></select>
                </td>
            </tr>
            <tr>
                <td>休假时间：</td>
                <td>
                    <input type="text" name="begin" class="Wdate"
                           onfocus="WdatePicker({skin:'whyGreen'})" style="width: 120px"/>
                    <input type="radio" name="remarks" value="上午">上午
                    <input type="radio" name="remarks" value="下午">下午
                    <span> ---- </span>
                    <input type="text" name="end" class="Wdate"
                           onfocus="WdatePicker({skin:'whyGreen'})" style="width: 120px"/>
                    <input type="radio" name="remarks2" value="上午">上午
                    <input type="radio" name="remarks2" value="下午">下午
                </td>
            </tr>
            <tr>
                <td>休假天数：</td>
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

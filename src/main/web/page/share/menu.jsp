<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 14-3-20
  Time: 下午5:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/page/share/taglib.jsp" %>

<div id="head_line"></div>
<div id="head" style="width: 1050px">

    <a href="index.jsp" style="border: none;">
        <img src="<%=basePath%>img/logo.bmp" style="border: none;margin-right: 10px;width: 150px;height: 80px;"/>
    </a>

    <div style="width:400px;margin-top: 20px;margin-bottom: 0px;margin-left:340px;text-align: right">
        <c:if test="${session.person == null}">
            <%--<span style="font-size:15px; font-family:'微软雅黑'; color: #000000; position: absolute;top: 3px;right: 22%;">--%>
			<span style="font-size:15px; font-family:'微软雅黑'; color: #000000;">
				&nbsp&nbsp你好！&nbsp&nbsp <a href="<%=basePath%>login.jsp">登陆</a>
			</span>
        </c:if>
        <c:if test="${session.person != null}">
            <%--<span style="font-family:'微软雅黑'; color: #000000; position: absolute;top: 3px;right: 18%;">欢迎 ${person.position.positionName}&nbsp&nbsp${person.userName}&nbsp--%>
			<span style="font-family:'微软雅黑'; color: #000000;">欢迎
				${person.userName}&nbsp; <a
                        href="<%=basePath%>page/user/alterPwd.jsp">修改密码</a> <a
                        href="Login!loginOut.action">退出</a>
			</span>
        </c:if>
    </div>

    <%--导航菜单开始--%>
    <%--按钮1--%>
    <div class="btn-group" style="margin-top: 10px;">
        <%--btn-primary 颜色--%>
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            人员管理 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="<%=basePath%>page/user/person.jsp">员工调岗</a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="<%=basePath%>page/user/alterPwd.jsp">修改账号密码</a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="<%=basePath%>page/user/administrator.jsp">增加部门考勤员</a>
            </li>
        </ul>
    </div>
    <%--按钮2--%>
    <div class="btn-group" style="margin-top: 10px;">
        <%--btn-primary 颜色--%>
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            休假管理 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="<%=basePath%>page/leave/apply.jsp"> 录入休假信息 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="<%=basePath%>page/holiday/DataOperation.jsp"> 上传年度节假日信息 </a>
            </li>
        </ul>
    </div>
    <%--按钮3--%>
    <div class="btn-group" style="margin-top: 10px;">
        <%--btn-primary 颜色--%>
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            加班管理 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="<%=basePath%>page/ot/apply.jsp"> 录入加班信息 </a>
            </li>
        </ul>
    </div>

    <%--按钮4--%>
    <div class="btn-group" style="margin-top: 10px;">
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            排班管理<span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="<%=basePath%>page/scheduleInfo/DataOperation.jsp">每月排班</a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="<%=basePath%>page/scheduleInfo/apply.jsp">临时调班</a>
            </li>
        </ul>
    </div>

    <%--按钮5--%>
    <div class="btn-group" style="margin-top: 10px;">
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            年假管理<span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="<%=basePath%>page/annualLeave/DataOperation.jsp">导入年假信息</a>
            </li>

        </ul>
    </div>

    <%--按钮56--%>
    <div class="btn-group" style="margin-top: 10px;">
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            报表管理<span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="<%=basePath%>page/report/report.jsp">生成月报表</a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="<%=basePath%>page/report/subsidy.jsp">中晚班补贴</a>
            </li>
        </ul>
    </div>

    <%--按钮57--%>
    <div class="btn-group" style="margin-top: 10px;">
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            打卡记录管理<span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="<%=basePath%>page/search/searchDetail.jsp">打卡明细查询</a>
            </li>

        </ul>
    </div>
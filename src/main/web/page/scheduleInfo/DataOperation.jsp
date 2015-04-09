<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/page/share/taglib.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>导入排班信息</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>

    <link rel="stylesheet" href="<%=basePath%>css/base2.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
    <script type="text/javascript">
        //获取目录对应格式
        function verifyFileName() {
            //解决中文乱码问题的方法
            var uploadFile = document.forms[0].uploadFile.value;
            document.forms[0].fileName.value = uploadFile;
            if ("" != uploadFile) {
                uploadFile = uploadFile.substring(uploadFile.lastIndexOf(".") + 1)
                        .toLowerCase();
                if (uploadFile != "xls") {
                    document.forms[0].fileName.value = "";
                    alert("请上传excel文件");
                }
            }
        }
    </script>
</head>

<body>
<%@ include file="/page/share/menu.jsp" %>
<div id="content">
    <table>
        <tr>
            <td>
                <form action="File!recoverExcelByScheduleInfo.action" method="post"
                      enctype="multipart/form-data">
                    <input type="hidden" name="fileName"> 文件： <input
                        type="file" name="uploadFile" size="16"
                        onchange="verifyFileName()"> <input type="submit"
                                                            value="导入" class="input"/>
                </form>
            </td>
        </tr>
        <tr>
            <td>Excel文件样板</td>
        </tr>
        <tr>
            <td><img src="<%=basePath%>img/" alt="样例"></td>
        </tr>
    </table>
</div>
<%@ include file="/page/share/footer.jsp" %>
</body>
</html>

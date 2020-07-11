<%@ page import="java.util.ArrayList" %>
<%@ page import="vo.Student" %>
<%@ page import="vo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/jquery-ui-1.10.4.custom.min.css">
    <script src="../resources/js/jquery-1.10.2.js"></script>
    <script src="../resources/js/jquery-ui-1.10.4.custom.min.js"></script>
    <title>main</title>
    <link href="../resources/css/default.css" rel="stylesheet"/>
</head>
<body>
<%
	User teacher = (User) session.getAttribute("info");
    ArrayList<User> stus = (ArrayList<User>) session.getAttribute("onePageStudent");
    int sumIndex = (int) session.getAttribute("sumIndex");
%>
<div id="page" class="container">
    <div id="header">
        <div id="logo">
           <%--  <img src="../userImg/<%=teacher.getId()%>.jpeg"/> --%>
            <h1><%=teacher.getUsername()%>
            </h1>
        </div>
        <div id="menu">
            <ul>
                <!-- <li><a href="personal.jsp">个人信息</a></li> -->
                <li class="current_page_item"><a href="../one_page_student">用户管理</a></li>
                <li><a onclick="return confirm('确认退出?');" href="../exit">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>大学生信息管理系统</h2>
            <hr/>
            <button class="btn-add">添加用户信息</button>
            <div class="find">
                <form action="../one_page_student" method="post">
                    <input id="find-text" type="text" name="key" placeholder="输入用户名搜索信息">
                    <input class="find-btn" type="submit" value="搜索">
                </form>
            </div>
        </div>
        <div class="table">
            <table id="table" width="800" frame="box" align="center">
                <tr>
                    <th height="35">用户名</th>
                    <th>密码</th>
                   <!--  <th>性别</th>
                    <th>科目</th>
                    <th>日期</th>
                     <th>成绩</th> -->
                    <th>操作</th>
                </tr>
                <%
                    for (User stu : stus) {
                %>
                        <tr>
                            <form method="post" action="../update_student">
                                <%-- <td height="35"><%=stu.getUsername()%></td> --%>
                                <td><input value="<%=stu.getUsername()%>" name="username" class="table-input" style="width: 110px"></td> 
                                <td><input value="<%=stu.getPassword()%>" name="password" class="table-input" style="width: 110px"></td> 
                                <input value="<%=stu.getId()%>" name="stuno" type="hidden">
                                <td><input type="submit" class="update-btn" value="修改">&nbsp;<a class="btn-delete"
                                                                                           onclick="return confirm('确定要删除吗?');"
                                                                                           href=<%="'../delete_student?id=" + stu.getId() + "'"%>>删除</a>
                                </td>
                            </form>
                        </tr>
                <%
                    }
                %>
            </table>
        </div>
        <%
            if (sumIndex > 1){
        %>
                <div id="index">
                    <a href="../one_page_student?index=1">首页</a>
                    <%
                        for (int i=1; i<=sumIndex; i++){
                    %>
                            <a href="../one_page_student?index=<%=i%>">第<%=i%>页</a>
                    <%
                        }
                    %>
                    <a href="../one_page_student?index=<%=sumIndex%>">尾页</a>
                </div>
        <%
            }
        %>
    </div>
</div>

<%--添加学生信息对话框--%>
<div id="add-dialog" title="添加用户信息">
    <form id="add-form" method="post">
        用户名:<input name="username" type="text"><br>
        密码:<input name="password" type="text"><br>
        <hr>
        <input style="float: right" type="submit" value="取消" onclick="function x() {
          $('#add-dialog').dialog('close');
        }">
        <input style="float: right; margin-right: 25px" type="submit" value="确定"
               onclick="this.form.action='../add_student'">
    </form>
</div>

<style>
    .ui-dialog-titlebar-close {
        display: none
    }
</style>

<script>
    $('#add-dialog').dialog({
        width: 310,
        autoOpen: false,
        draggable: false,
        modal: true,
        resizable: false
    });
    $('.btn-add').click(function () {
        $('#add-dialog').dialog('open');
    });
</script>
</body>
</html>


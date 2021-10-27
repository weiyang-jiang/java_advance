<%@ page import="com.example.pojo.user" %><%--
  Created by IntelliJ IDEA.
  User: weiyang
  Date: 2021/9/25
  Time: 下午4:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功案例</title>
</head>
<body>
    <%
//        user u = (user) session.getAttribute("user");
//        String nickname = null;
//        if (u != null){
//            nickname = u.getNickname();
//        }
    %>
    <div style="color: red">登录成功, ${user.nickname}</div>
</body>
</html>

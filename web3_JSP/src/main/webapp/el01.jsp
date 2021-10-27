<%@ page import="com.example.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: weiyang
  Date: 2021/9/26
  Time: 下午12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        // 往域对象里面存值
        application.setAttribute("msg", "applicationValue");
        session.setAttribute("msg", "sessionValue");
        request.setAttribute("msg", "requestValue");

        Integer[] list = {1, 2, 3, 4};
        request.setAttribute("list", list);

        User user = new User();
        user.setAddress("dalian");

        request.setAttribute("age", 19);
        request.setAttribute("course", "java");


    %>

    msg=${applicationScope.get("msg")}<br>
    msg=${requestScope.get("msg")}<br>
    msg=${sessionScope.get("msg")}<br>
    msg = ${msg}<br>
    <%--    会首先获取request中的值，会最先输出范围小的--%>

    ${list[2]}<br>

    address = ${user.address}<br>
    ${empty list}<br>

    ${cookie.JSESSIONID.value}

    ${age >= 18 ? "已成年": "未成年"}

<%--    jstl的判断语句--%>
    <c:if test="${age >= 18}">已成年</c:if>
    <c:if test="${age < 18}">未成年</c:if>

    <c:choose>
<%--
一个when标签表示一个条件
--%>
        <c:when test="${course == 'java'}">java</c:when>
        <c:otherwise>不学习</c:otherwise>
    </c:choose><br>

    <c:forEach begin="0" end="9" step="2" var="i">
        ${i}
    </c:forEach><br>

    <c:forEach items="${list}" var="username">
        ${username}
    </c:forEach><br>

    <c:forEach items="${list}" var="username" varStatus="vst">
        ${vst.index}
<%--        索引--%>
        ${vst.count}
<%--        第几个--%>
        ${vst.current}
<%--        --%>
        ${vst.first}
    </c:forEach>
</body>
</html>

<%@ page import="com.example.utils.CookieUtils" %><%--
  Created by IntelliJ IDEA.
  User: weiyang
  Date: 2021/9/25
  Time: 下午3:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
</head>
<body>
<%
    // 从request域对象中取出msg
//    String msg = (String) request.getAttribute("msg");
//    if (msg == null){
//        msg = "";
//    }
    // 取出cookie
//    Cookie[] cookies = request.getCookies();
//    String username = CookieUtils.getCookieValue(cookies, "username");
//    if (username == null){
//        username = "";
//    }
%>
<div style="color: red">${msg}</div>
<form action="/web2/login" method="post">
    用户名<input type="text" name="username" value=${cookie.username.value}><br>
    密码<input type="text" name="password"><br>
    验证码<input type="text" name="checkCode"><br>
    <img src="/web2/checkCode" id="checkCodeImg">
    <a id="change" href="javascipt:;" onclick="changeCode()">换一换</a>
    <br>
    记住用户名<input type="checkbox" name="remember"><br>
    <input type="submit" value="登录">
</form>

<script>
    function changeCode(){
        // 这种会去找缓存去，需要让路径不同才可以访问
        document.querySelector("#checkCodeImg").setAttribute("src", "/web2/checkCode?abc=" + new Date())
    }
</script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Fanyi Xiao
  Date: 2020/7/15
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>

<body>
<form method="post" action="/web3/account">
    <table border="1px" width="500px" align="center">
        <tr>
            <td>付款方</td>
            <td><input type="text" name="from"></td>
        </tr>
        <tr>
            <td>收款方</td>
            <td><input type="text" name="to"></td>
        </tr>
        <tr>
            <td>金额</td>
            <td><input type="text" name="money"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"></td>
        </tr>
    </table>
</form>
</body>
</html>

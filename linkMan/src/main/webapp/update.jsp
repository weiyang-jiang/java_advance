<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
    <head>
    	<base href="<%=basePath%>"/>
        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>修改联系人</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/jquery-2.1.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
    </head>
    <body>
        <div class="container" style="width: 400px;">
        <h3 style="text-align: center;">修改联系人</h3>
        <form action="/linkMan" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${linkman.id}">
            <input type="hidden" name="currentPage" value="${currentPage}">
            <input type="hidden" name="pageSize" value="${pageSize}">

      <div class="form-group">
        <label for="name">姓名：</label>
        <input type="text" class="form-control" id="name" name="name"  readonly="readonly" placeholder="请输入姓名" value="${linkman.name}"/>
      </div>

      <div class="form-group">
        <label>性别：</label>
          <c:if test="${linkman.sex == '男'}">
              <input type="radio" name="sex" value="男"  checked/>男
              <input type="radio" name="sex" value="女"  />女
          </c:if>
          <c:if test="${linkman.sex == '女'}">
              <input type="radio" name="sex" value="男"  />男
              <input type="radio" name="sex" value="女"  checked/>女
          </c:if>

      </div>
      
      <div class="form-group">
        <label for="age">年龄：</label>
        <input type="text" class="form-control" id="age"  name="age" placeholder="请输入年龄" value="${linkman.age}"/>
      </div>

      <div class="form-group">
        <label for="address">籍贯：</label>
	     <select name="address" class="form-control" id="address">
             <c:if test="${linkman.address == '广东'}">
                 <option value="广东" selected>广东</option>
                 <option value="广西">广西</option>
                 <option value="湖南">湖南</option>
             </c:if>
             <c:if test="${linkman.address == '广西'}">
                 <option value="广东" >广东</option>
                 <option value="广西" selected>广西</option>
                 <option value="湖南">湖南</option>
             </c:if>
             <c:if test="${linkman.address == '湖南'}">
                 <option value="广东" >广东</option>
                 <option value="广西">广西</option>
                 <option value="湖南" selected>湖南</option>
             </c:if>

        </select>
      </div>
      
      <div class="form-group">
        <label for="qq">QQ：</label>
        <input type="text" class="form-control" value="${linkman.qq}" name="qq" placeholder="请输入QQ号码" id="qq"/>
      </div>

      <div class="form-group">
        <label for="email">Email：</label>
        <input type="text" value="${linkman.email}" class="form-control" name="email" placeholder="请输入邮箱地址" id="email"/>
      </div>

         <div class="form-group" style="text-align: center">
			<input class="btn btn-primary" type="submit" value="提交" />
			<input class="btn btn-default" type="reset" value="重置" />
			<input class="btn btn-default" type="button" value="返回"/>
         </div>
        </form>
        </div>
    </body>
</html>
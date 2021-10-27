---
typora-copy-images-to: img
---

# JSP&三层架构

# 教学目标

- [ ] 能够说出el表达式的作用
- [ ] 能够使用el表达式获取javabean的属性
- [ ] 能够使用jstl标签库的if标签
- [ ] 能够使用jstl标签库的foreach标签
- [ ] 能够使用三层架构模式完成显示用户案例
- [ ] 能够使用ThreadLocal
- [ ] 能够完成转账案例

# 第一章-EL表达式 

## 知识点-EL表达式概述

### 1.目标

+ 能够说出el表达式的作用

### 2.讲解

#### 2.1.什么是El表达式

​	Expression Language:表达式语言,  jsp2.0之后内置在jsp里面

​	目的：为了使JSP写起来更加简单,  取值(取的域对象里面存的值)更加简单。(代替脚本 <% %>)

#### 2.2. EL语法

​	${el表达式}

#### 2.3. EL表达式的用途 

​	1.获取数据. 获取的是**域(request,session,ServletContext)对象**中存储的数据

​	2.EL执行运算

### 3.小结

1. EL表达式:表达式语言
2. 语法:${el表达式}
3. 作用:1. 获取域对象里面的数据     2. 执行运算







## 知识点-El获取数据

### 1.目标

+ 能够使用el表达式域里面的数据(**先要把数据存到域对象里面**)

### 2.路径

+ 获取简单数据类型数据(基本类型,字符串)
+ 获取数组
+ 获取list
+ 获取Map
+ 获取bean

### 3.讲解

#### 3.1获取简单数据类型数据

​	语法:${requestScope|sessionScope|applicationScope.属性名}; 

​	快捷写法:==${属性名},  属性名就是存在域对象里面的key==



```jsp
<%--
  Created by IntelliJ IDEA.
  User: Fanyi Xiao
  Date: 2020/7/15
  Time: 8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>使用EL表达式获取域对象中的简单类型的数据</title>
</head>
<body>
    <%
        //往域对象存值
        application.setAttribute("msg","applicationValue");
        //session.setAttribute("msg","sessionValue");
        //request.setAttribute("msg","requestValue");

        //在我们开发过程中，是否会往多个域对象中存放同一个key??? 这是不会的
        //所以我们用el表达式获取域对象里面的数据，还可以简化成${key} 获取范围最小的域对象中的这个key对应的值
    %>
    获取的application域对象中的msg=${applicationScope.msg}<br>
    获取session域对象中的msg=${sessionScope.msg}<br>
    获取request域对象中的msg=${requestScope.msg}<br>
    获取存放在域对象中的msg=${msg}
</body>
</html>
```



#### 3.2获取数组

​	语法: ${key[下标]}    key就是域对象里面存的key

#### 3.3获取list

​	`语法:${list属性名[index]}或者${list属性名.get(index)};list属性名就是存入域对象里面的key`

#### 3.4获取Map

​	 `语法:${map属性名.键}或者${map属性名.get("键")},map属性名就是存入域对象里面的key`

#### 3.5 获取bean

​	==语法:${key.javabean属性}==

​	依赖getxxx()方法; eg: getPassword()---去掉get-->Password()----首字母小写--->password

```jsp
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.itheima.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: Fanyi Xiao
  Date: 2020/7/15
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>使用EL表达式获取存储在域对象中的复杂类型的数据</title>
</head>
<body>
    <%
        //往域对象中存放数组类型的数据
        String[] arr = {"张三","李四","王五","赵六","田七","狗娃"};
        request.setAttribute("arr", arr);

        //往域对象中存放一个集合
        List<String> list = new ArrayList<String>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("赵六");
        list.add("田七");
        request.setAttribute("list", list);

        //往域对象中存放一个map
        Map<String,String> map = new HashMap<String,String>();
        map.put("name", "张三");
        map.put("password", "123456");
        map.put("nickname", "张三丰");
        request.setAttribute("map", map);

        //往域对象中存放一个pojo对象
        User user = new User(1, "jay", "台湾省");
        request.setAttribute("user",user);
    %>
    获取存放在request域对象中的数组中的第三个元素:${arr[2]}<br>
    <%--
        在el表达式中，只要是根据下标获取元素，都可以写[index]
    --%>
    获取存放在request域对象中的集合中的第四个元素:${list[3]}<br>

    <%--
        在el表达式中，只要是根据对应的属性的get方法去获取数据，都可以写成".属性名"  或者 ["属性名"]
    --%>
    获取存储在request域对象中的map中的nickname:${map.nickname}<br>

    获取存放在request域对象中的user的address属性的值:${user.address}
</body>
</html>
```



### 4.小结

#### 4.1语法

1. 获得简单类型的

```
${key}
```

2. 获得数组类型的

```
${key[下标]}
```

3. 获得List类型的

```
${key.get(index)} 或者${key[index]}
```

4. 获得Map类型的

```
${key.get(键)} 或者${key.键} key是存到域对象里面的key
```

5. 获得JavaBean类型的

````
${key.javaBean属性}
````



## 知识点-EL执行运算

### 1.目标

+ 掌握EL执行运算

### 2.讲解

![img](img/tu_3.jpg)

#### 2.1算数运算

​	+,-,*,/

- +不能拼接字符串，如果+两端是字符串，那么会将字符串转换成数字之后再进行加法运算，如果+两端的字符串无法转换成数字，则会报错

#### 2.2逻辑运算

​	< >= <= != ==

#### 2.3关系运算

​	&& || !

#### 2.4非空判断【重点】

​	empty，==1. 判断一个对象是否为null,  2. 判断集合长度是否为0,==  3. 判断一个字符串是否为 ""

​	not empty

​	语法: ${empyt 属性名};属性名 就是域对象里面的key值

```jsp
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itheima.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: Fanyi Xiao
  Date: 2020/7/15
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>empty 运算符的介绍</title>
</head>
<body>
    <%
        //el表达式中的empty可以判断一个字符串是否为空字符串，一个对象是否为null，一个集合的长度是否为0
        List<String> list = new ArrayList<String>();
        list.add("张三丰");
        request.setAttribute("list", list);

        request.setAttribute("msg","requestValue");

        User user = new User();
        request.setAttribute("u",user);
    %>
    判断域对象中的list集合的长度是否为0: ${empty list}<br>
    判断域对象中的msg字符串是否为空字符串: ${empty msg}<br>
    判断域对象中的user是否为null : ${empty u}<br>
</body>
</html>
```

### 3.小结

1. 注意的地方: +只能做加法运算,不能拼接字符串	

2. empty【重点】

   + 语法
     + ${empty key}
     + ${not empty key}
+ 作用
     + 判断一个对象是否为null
     + 判断一个集合长度是否为0
     + 判断一个字符串是否是""
   + 注意
     + 如果是集合, 集合为null 是empty
     + 如果是集合, 集合不为null 但是长度为0 还是empty
   





## 知识点-使用EL表达式获取存放在cookie中的数据(扩展)

​	语法:${cookie.cookie的name.value}

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>使用el表达式获取存储在cookie中的数据</title>
</head>
<body>
    <%--
        jsp里面是内置session对象，有了session对象，那么浏览器就会携带一个名为"JSESSIONID"的cookie
        我们的目标就是获取"JSESSIONID"的值
    --%>
    <%
        Cookie[] cookies = request.getCookies();
        String cookieValue = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    cookieValue = cookie.getValue();
                }
            }
        }
    %>
    使用原始方式获取的JSESSIONID的值为: <%=cookieValue%><br>

    <%--
        ${cookie}表示获取这次请求中的所有cookie对象
        ${cookie.JSESSIONID}表示获取名为"JSESSIONID"的cookie对象
        ${cookie.JSESSIONID.value}表示获取名为"JSESSIONID"的cookie对象的value
    --%>
    使用EL表达式获取JSESSIONID的值为: ${cookie.JSESSIONID.value}
</body>
</html>
```



# 第二章-JSTL标签库  

## 知识点-JSTL标签库概述

### 1.目标

+ 掌握什么是JSTL标签库

### 2.讲解

#### 2.1 什么是JSTL标签库

​	JSTL（JSP Standard Tag Library，JSP标准标签库)是一个不断完善的开放源代码的JSP标签库，是由apache的jakarta小组来维护的。这个JSTL标签库没有集成到JSP的, 要使用的话, 需要导jar包.

#### 2.2 JSTL标签库的作用

​	为了简化在jsp页面上操作数据;  eg:  遍历数据 判断数据等

#### 2.3 JSTL标签库的类别

![img](img/tu_2.jpg)



### 3.小结

1. JSTL: JSP标准的标签库. 也就意味着我们可以在jsp里面使用除了html以外的其它的标签
2. 作用: 遍历, 判断.... 代替脚本





## 知识点-JSTL核心标签库

### 1.目标

+ 掌握if,foreach标签的使用

### 2.讲解

#### 2.1核心标签库使用步骤

1. 导入jar包 ![img](img/tu_12.png)

2. 在JSP页面上导入核心标签库`<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>`



#### 2.3if标签

![img](img/06.png)

- 语法

```
<c:if test="el表达式${..}">
</c:if>
```

- 实例

```jsp
<%--
  Created by IntelliJ IDEA.
  User: Fanyi Xiao
  Date: 2020/7/15
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jstl的if标签</title>
</head>
<body>
    <%
        //往域对象中存储age
        request.setAttribute("age",19);
    %>
    <%--
        判断age是否大于等于18，如果大于等于18则输出:已成年，否则输出未成年
    --%>
   <%--
      第一种方式:使用el表达式的三元运算符
      ${age >= 18 ? "已成年" : "未成年"}
   --%>

    <%--
        第二种方式：使用jstl的if标签
        jstl的使用步骤:
            1. 导入jar包
            2. 通过taglib指令:引入jstl
            3. 使用标签库中的标签

        if标签的test属性，表示判断表达式通常是和el表达式结合使用
    --%>
    <c:if test="${age >= 18}">
        已成年
    </c:if>
    <c:if test="${age < 18}">
        未成年
    </c:if>
</body>
</html>
```

+ 小结

  + 语法 

  ```
  <c:if test="${} "></c:if>
  ```

  + 特点
    + 如果test里面的是true, if标签体里面的就会执行
    + 如果test里面的是false, if标签体里面的就不会执行
    + 没有else的

#### 2.4choose标签

- 实例 

```jsp
<%--
  Created by IntelliJ IDEA.
  User: Fanyi Xiao
  Date: 2020/7/15
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jstl中的choose标签的使用介绍</title>
</head>
<body>
    <%
        request.setAttribute("course","PHP");
    %>
    <c:choose>
        <%--
            一个when标签表示一个条件
        --%>
        <c:when test="${course == 'Java'}">
            学习Java
        </c:when>

        <c:when test="${course == 'Android'}">
            学习Android
        </c:when>

        <c:when test="${course == 'C++'}">
            学习C++
        </c:when>

        <c:otherwise>
            学习，学个屁!!!
        </c:otherwise>
    </c:choose>
</body>
</html>
```

#### 2.5foreach标签

![img](img/07.png)		

- 简单的使用:

​	

```jsp
 <%--
        jstl中的forEach标签是用来代替for循环语句

        目标1: 在浏览器上显示0-9的数字
            begin属性: 从哪个下标开始遍历, 如果不写默认是从0开始
            end属性: 到哪个下标结束遍历，如果不写默认是遍历到集合/数组的最后一个元素
            step属性: 表示遍历时候的步长,默认步长是1
            var属性: 表示将遍历的结果存放进域对象时候的key
    --%>
<c:forEach begin="0" end="9" step="1" var="i">
	${i}
</c:forEach><br>
```

​			

- 复杂的使用遍历集合:

  ```jsp
  <%
      //往域对象存储一个集合
      List<String> list = new ArrayList<String>();
      list.add("张三");
      list.add("李四");
      list.add("王五");
      list.add("赵六");
      list.add("田七");
      request.setAttribute("list", list);
  %>
  
  <c:forEach begin="0" end="9" step="1" var="i">
      ${i}
  </c:forEach><br>
  
  	<%--
      通过items属性指定遍历域对象里面的list
      通过var属性指定遍历出来的每个数据存储到域对象时候的key
      --%>
  <c:forEach items="${list}" var="username">
      ${username}
  </c:forEach>
  ```

- c:forEach中的varStatus属性。

   	  指向一个字符串，该字符串引用一个对象。  map.put("vs",一个对象);

       	  这个对象记录着当前遍历的元素的一些信息：

         		   index:返回索引。从0开始

          		   count:返回计数。从1开始

         		    last:是否是最后一个元素

          		   first:是否是第一个元素	

```jsp
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Fanyi Xiao
  Date: 2020/7/15
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>forEach 标签的varStatus属性的介绍</title>
</head>
<body>
    <%
        //往域对象存储一个集合
        List<String> list = new ArrayList<String>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("赵六");
        list.add("田七");
        request.setAttribute("list", list);
    %>

    <%--
        forEach标签的varStatus属性:指定将遍历出来的每一个元素的状态存储进域对象时候的key
            遍历出来的每一个元素都有一些状态(属性)，比如:
                下标 index:
                计数 count:
                当前元素的值 current:
                是否是第一个元素:
                是否是最后一个元素
    --%>
    <table border="1" cellspacing="0" width="700" align="center">
        <tr>
            <th>下标</th>
            <th>计数</th>
            <th>姓名</th>
            <th>是否是第一个元素</th>
            <th>是否是最后一个元素</th>
        </tr>
        <c:forEach items="${list}" varStatus="vst">
            <tr>
                <td>${vst.index}</td>
                <td>${vst.count}</td>
                <td>${vst.current}</td>
                <td>${vst.first}</td>
                <td>${vst.last}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
```



### 3.小结

1. foreach标签

+ 简单使用

```
<c:foreach begin="从哪里开始" end="到哪里结束" var="每次遍历的赋值变量" step="步长">
	//每遍历一次 foreach里面就执行一次
</c:foreach>
```

+ 复杂使用

```
<c:foreach items="使用el从域对象里面取出集合" var="每次遍历的赋值变量" varStatus="遍历的状态">
	//每遍历一次 foreach里面就执行一次
</c:foreach>
```





# 第三章-综合案例和开发模式

## 案例-完成转账的案例v1 ##

### 一.需求 ###

- 当单击提交按钮，付款方向收款方安照输入的金额转账。

  


![img](img/tu_1-1574219049777.png)

### 二,分析

![image-20191212111935023](img/transferv1.jpg) 

### 三,实现

#### 1.案例的准备工作

+ 数据库的准备

  ```
  create database day27;
  use day27;
  create table account(
      id int primary key auto_increment,
      name varchar(20),
      money double
  );
  
  insert into account values (null,'jay',1000);
  insert into account values (null,'aobama',1000);
  insert into account values (null,'ww',1000);
  ```

+ 页面

  ```jsp
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
  </head>
  
  <body>
  <form method="post" action="">
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
  
  ```

+ jar包
+ 工具类
+ 配置文件

#### 2.代码实现

+ AccountServlet

```java
package com.itheima.web.servlet;

import com.itheima.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 包名:${PACKAGE_NAME}
 *
 * @author Leevi
 * 日期2020-07-15  10:55
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //1. 获取请求参数
        String fromName = request.getParameter("from");
        String toName = request.getParameter("to");
        Double money = Double.valueOf(request.getParameter("money"));

        //2. 执行转账的SQL语句
        //2.1 转出账户扣款
        QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
        String sql1 = "update account set money=money-? where name=?";

        try {
            queryRunner.update(sql1,money,fromName);

            //2.2 转入账户收款
            String sql2 = "update account set money=money+? where name=?";
            queryRunner.update(sql2,money,toName);

            //3. 转账成功
            response.getWriter().write("转账成功!!!");
        } catch (Exception e) {
            e.printStackTrace();
            //转账失败
            response.getWriter().write("转账失败!!!");
        }
    }
}
```

### 四.小结

1. 转账: 一个用户的钱减少, 一个用户的钱增加



## 知识点-开发模式  

### 1.目标

+ 理解模式二(MVC)和模式三(三层架构)

### 2.讲解

#### 2.1JSP的开发模式一【了解】

 	javaBean:实体类。特点：私有化的属性、公共的getter setter方法、无参的构造。



![img](img/tu_10.png)



#### 2.2 JSP的开发模式二

​	JSP + Servlet + JavaBean 称为MVC的开发模式.

​	**MVC:开发模式**

​	M：model 模型 （javaBean：封装数据）

​	V：View 视图  （JSP：展示数据）

​	C：controller 控制器 （Servlet：处理逻辑代码，做为控制器）



![img](img/tu_11.png)





#### 2.3模式三: 三层架构 



- 软件中分层：按照不同功能分为不同层，通常分为三层：表现层(web层)，业务层，持久(数据库)层。

  

  ![img](img/tu_12-1568797747077.png)

- 不同层次包名的命名

| 分层                 | 包名(公司域名倒写)  |
| -------------------- | ------------------- |
| 表现层(web层)        | com.itheima.web     |
| 业务层(service层)    | com.itheima.service |
| 持久层(数据库访问层) | com.itheima.dao     |
| JavaBean             | com.itheima.bean    |
| 工具类               | com.itheima.utils   |

- 分层的意义:
  1. 解耦：降低层与层之间的耦合性。 (以后面向接口编程)
  2. 可维护性：提高软件的可维护性，对现有的功能进行修改和更新时不会影响原有的功能。
  3. 可扩展性：提升软件的可扩展性，添加新的功能的时候不会影响到现有的功能。
  4. 可重用性：不同层之间进行功能调用时，相同的功能可以重复使用。



![img](img/tu_9.png)



### 3.小结

1. 模式一: JSP+JavaBean【了解】

2. 模式二: MVC
   + M model  JavaBean
   + V  View    JSP
   + C Controller  Servlet
   
3. 三层架构
  
   + WEB层
     + 获得请求参数
     + 调用业务
     + 响应
   + 业务层
     + 处理业务
     + 调用Dao
   + 持久层
     + 操作数据库
   + 三层架构中的包名:
     + 表现层: web
     + 业务层: service
     + 数据访问层/持久层: dao
   
   



## 案例-完成转账的案例v2 ##

### 一.需求 ###

- 使用三层架构改写转账案例

  


![img](img/tu_1-1574219049777.png)

### 二,分析

![image-20191212144132818](img/transferv2.jpg) 

### 三,实现

+ UserServlet

```java
package com.itheima.web.servlet;

import com.itheima.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 包名:${PACKAGE_NAME}
 *
 * @author Leevi
 * 日期2020-07-15  10:55
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //1. 获取请求参数
        String fromName = request.getParameter("from");
        String toName = request.getParameter("to");
        Double money = Double.valueOf(request.getParameter("money"));

        try {
            //2. 调用业务层的AccountService的方法处理请求执行转账
            AccountService accountService = new AccountService();
            accountService.transfer(fromName,toName,money);

            //3. 转账成功
            response.getWriter().write("转账成功!!!");
        } catch (Exception e) {
            e.printStackTrace();
            //转账失败
            response.getWriter().write("转账失败!!!");
        }
    }
}
```



+ AccountService

```java
package com.itheima.service;

import com.itheima.dao.AccountDao;

import java.sql.SQLException;

/**
 * 包名:com.itheima.service
 *
 * @author Leevi
 * 日期2020-07-15  11:19
 */
public class AccountService {
    private AccountDao accountDao = new AccountDao();
    public void transfer(String fromName,String toName,Double money) throws SQLException {
        //2.1 调用AccountDao的方法进行转出账户扣款
        accountDao.updateAccount(fromName,-money);
        //2.2 调用AccountDao的方法进行转入账户收款
        accountDao.updateAccount(toName,money);
    }
}
```

+ UserDao

```java
package com.itheima.dao;

import com.itheima.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

/**
 * 包名:com.itheima.dao
 *
 * @author Leevi
 * 日期2020-07-15  11:19
 */
public class AccountDao {
    private QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
    /**
     * 修改账户信息
     */
    public void updateAccount(String name,Double money) throws SQLException {
        String sql = "update account set money=money+? where name=?";
        queryRunner.update(sql,money,name);
    }
}
```

### 四.小结

+ WEB层    com.itheima.web
  + 获得请求参数
  + 调用业务
  + 响应
+ 业务层     com.itheima.service    xxService
  + 处理业务
  + 调用Dao
+ 持久层     com.itheima.dao          xxDao
  + 操作数据库



## 案例-完成转账的案例v3 ##

### 一.需求 ###

- 当单击提交按钮，付款方向收款方安照输入的金额转账。 使用手动事务进行控制

  


![img](img/tu_1-1574219049777.png)

### 二,分析


#### 1.DBUtils实现事务管理

| API                                                          | 说明                                    |
| ------------------------------------------------------------ | --------------------------------------- |
| QueryRunner()                                                | 创建QueryRunner对象. 手动提交事务时使用 |
| query(connection，String sql, Object[] params, ResultSetHandler<T> rsh) | 查询(需要传入Connection)                |
| update(connection，String sql, Object... params)             | 更新                                    |

#### 2.思路

<img src="img/transferv3.jpg" alt="image-20191212153634611" style="zoom:120%;" /> 

### 三,实现

+ UserService

```java
package com.itheima.service;

import com.itheima.dao.AccountDao;
import com.itheima.utils.DruidUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 包名:com.itheima.service
 *
 * @author Leevi
 * 日期2020-07-15  11:19
 */
public class AccountService {
    private AccountDao accountDao = new AccountDao();
    public void transfer(String fromName,String toName,Double money) {
        Connection conn = null;
        try {
            //逻辑操作开始之前开启事务： connection.setAutoCommit(false)
            conn = DruidUtil.getDataSource().getConnection();
            conn.setAutoCommit(false);

            //2.1 调用AccountDao的方法进行转出账户扣款
            accountDao.updateAccount(conn,fromName, -money);

            //模拟转账过程中出现异常
            int num = 10 / 0;

            //2.2 调用AccountDao的方法进行转入账户收款
            accountDao.updateAccount(conn,toName, money);

            //逻辑操作执行完毕没有异常，提交事务: connection.commit()
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //逻辑操作执行过程中遇到异常，则在catch里面回滚事务: connection.rollback()
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("转账失败");
        }
    }
}
```

+ UserDao

```java
package com.itheima.dao;

import com.itheima.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 包名:com.itheima.dao
 *
 * @author Leevi
 * 日期2020-07-15  11:19
 */
public class AccountDao {
    private QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
    /**
     * 修改账户信息
     * 指定使用某个连接Connection执行SQL语句
     */
    public void updateAccount(Connection connection,String name, Double money) throws SQLException {
        String sql = "update account set money=money+? where name=?";
        queryRunner.update(connection,sql,money,name);
    }
}
```



### 四.小结

1.  思想1: service层将异常try起来了，怎么才能让servlet还能够获取异常呢?

   ```
   在catch里面抛运行时异常
   ```

2. 思想2: 如果在service和dao共享一个Connection对象

   ```
   通过调用方法的时候将connection作为参数传递给Dao
   ```

3. 技术点1 : 怎么开启、提交、回滚事务

   ```
   connection.setAutoCommit(false)开启事务
   connection.commit()提交事务
   connection.rollback()回滚事务
   注意: 开启事务的连接和执行SQL语句的连接要是同一个
   ```

4. 技术点2 : 在使用DBUtils执行SQL语句的时候，怎么才能指定使用哪个连接呢?

   ```
   调用queryRunner对象的update或者query方法的时候，可以传入connection
   ```





## 案例-完成转账的案例v4 ##

### 一.需求 ###

- 当单击提交按钮，付款方向收款方安照输入的金额转账。 使用事务进行控制

  


![img](img/tu_1-1574219049777.png)

### 二,分析

#### 1.ThreadLocal

​	在“事务传递参数版”中，我们必须修改方法的参数个数，传递链接，才可以完成整个事务操作。如果不传递参数，是否可以完成？在JDK中给我们提供了一个工具类：ThreadLocal，此类可以在一个线程中共享数据。

​	java.lang.ThreadLocal,该类提供了线程局部 (thread-local) 变量，用于在当前线程中共享数据。ThreadLocal工具类底层就是一个Map，key存放的当前线程，value存放需要共享的数据

```java
package com.itheima;

/**
 * 包名:com.itheima
 *
 * @author Leevi
 * 日期2020-07-15  14:58
 * ThreadLocal是线程本地变量，它的作用是用于在保证同一个线程中的对象使用的是同一份数据
 */
public class TestMain {
    public static void main(String[] args) {
        //ThreadLocal的使用
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        //在主线程中往ThreadLocal对象中存储一个字符串"jay"
        threadLocal.set("jay");
        //在主线称重往ThreadLocal中存入一个字符串"aobama"
        threadLocal.set("aobama");

        //新线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //在新线程中，往ThreadLocal中存入"jay"
                threadLocal.set("jay");

                //在新线程中获取ThreadLocal中的值
                String s = threadLocal.get();
                System.out.println("在新线程中获取ThreadLocal中的值为:" + s);
            }
        }).start();


        //在主线程中取出ThreadLocal中的值
        String str = threadLocal.get();
        System.out.println("在主线程中获取ThreadLocal对象中的值:" + str);
    }
}
```

#### 2.思路

<img src="img/transferv4.jpg" alt="image-20191212155332778" style="zoom:120%;" />

### 三,代码

+ DruidUtil

```java
package com.itheima.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 包名:com.itheima.utils
 *
 * @author Leevi
 * 日期2020-07-06  11:45
 */
public class DruidUtil {
    private static DataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    static {
        try {
            //1. 创建Properties对象
            Properties properties = new Properties();
            //2. 将配置文件转换成字节输入流
            InputStream is = DruidUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            //3. 使用properties对象加载is
            properties.load(is);
            //druid底层是使用的工厂设计模式，去加载配置文件，创建DruidDataSource对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DataSource getDataSource(){
        return dataSource;
    }

    public static Connection getConnectionFromThreadLocal() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection == null) {
            //说明ThreadLocal中还没有连接对象
            connection = dataSource.getConnection();
            //将connection存入到ThreadLocal
            threadLocal.set(connection);
        }

        return connection;
    }
}
```

+ AccountService

```java
package com.itheima.service;

import com.itheima.dao.AccountDao;
import com.itheima.utils.DruidUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 包名:com.itheima.service
 *
 * @author Leevi
 * 日期2020-07-15  11:19
 */
public class AccountService {
    private AccountDao accountDao = new AccountDao();
    public void transfer(String fromName,String toName,Double money) {
        Connection conn = null;
        try {
            //从ThreadLocal中获取连接对象
            //逻辑操作开始之前开启事务
            conn = DruidUtil.getConnectionFromThreadLocal();
            conn.setAutoCommit(false);

            //2.1 调用AccountDao的方法进行转出账户扣款
            accountDao.updateAccount(fromName, -money);

            //模拟转账过程中出现异常
            //int num = 10 / 0;

            //2.2 调用AccountDao的方法进行转入账户收款
            accountDao.updateAccount(toName, money);

            //逻辑操作执行完毕没有异常，提交事务: connection.commit()
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //逻辑操作执行过程中遇到异常，则在catch里面回滚事务: connection.rollback()
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("转账失败");
        }
    }
}
```

+ AccountDao

```java
package com.itheima.dao;

import com.itheima.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 包名:com.itheima.dao
 *
 * @author Leevi
 * 日期2020-07-15  11:19
 */
public class AccountDao {
    private QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
    /**
     * 修改账户信息
     * 指定使用某个连接Connection执行SQL语句
     */
    public void updateAccount(String name, Double money) throws SQLException {
        //从ThreadLocal对象中获取一个连接
        Connection connection = DruidUtil.getConnectionFromThreadLocal();
        String sql = "update account set money=money+? where name=?";
        queryRunner.update(connection,sql,money,name);
    }
}
```



### 四.小结

1. TheadLocal: jdk提供的一个对象. 只要是在同一个线程里面, 是可以共用的. 
2. 抽取了DruidUtil的getConnectionFromThreadLocal()方法, service和Dao里面的Connection都是从getConnectionFromThreadLocal()方法获取



## 补充案例:显示所有用户

### 目标

在list.jsp页面中显示user表中的所有用户的信息

### 分析

![](img/showAll.jpg)



### 实现

1. 拷贝jar、配置文件、工具类
2. 创建index.jsp和list.jsp
3. 创建包结构、pojo类
4. 创建ShowAllServlet、UserService、UserDao

**index.jsp代码**

```jsp
<%--
  Created by IntelliJ IDEA.
  User: Fanyi Xiao
  Date: 2020/7/15
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
    <a href="/showAll">查看所有用户信息</a>
</body>
</html>
```

​	**ShowAllServlet代码**	

```java
package com.itheima.web.servlet;

import com.itheima.pojo.User;
import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 包名:${PACKAGE_NAME}
 *
 * @author Leevi
 * 日期2020-07-15  12:08
 */
@WebServlet("/showAll")
public class ShowAllServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1. 调用业务层的方法，处理查询所有用户的请求
            UserService userService = new UserService();
            List<User> userList = userService.findAll();

            //2. 将user的集合存储到request域对象中
            request.setAttribute("list",userList);

            //3. 跳转到list.jsp页面
            request.getRequestDispatcher("/list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
```

**UserService代码**

```java
package com.itheima.service;

import com.itheima.dao.UserDao;
import com.itheima.pojo.User;

import java.sql.SQLException;
import java.util.List;

/**
 * 包名:com.itheima.service
 *
 * @author Leevi
 * 日期2020-07-15  12:09
 */
public class UserService {
    private UserDao userDao = new UserDao();
    public List<User> findAll() throws SQLException {
        //调用dao对象的findAll()方法查询所有用户信息
        List<User> userList = userDao.findAll();
        return userList;
    }
}
```

**UserDao代码**

```java
package com.itheima.dao;

import com.itheima.pojo.User;
import com.itheima.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 包名:com.itheima.dao
 *
 * @author Leevi
 * 日期2020-07-15  12:09
 */
public class UserDao {
    private QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
    public List<User> findAll() throws SQLException {
        String sql = "select * from user";
        List<User> userList = queryRunner.query(sql, new BeanListHandler<>(User.class));
        return userList;
    }
}
```

**list.jsp代码**

```jsp
<%--
  Created by IntelliJ IDEA.
  User: Fanyi Xiao
  Date: 2020/7/15
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>展示页面</title>
</head>
<body>
    <table border="1" cellspacing="0" width="800px" align="center">
        <tr>
            <th>序号</th>
            <th>用户名</th>
            <th>密码</th>
            <th>地址</th>
            <th>昵称</th>
            <th>性别</th>
            <th>邮箱</th>
        </tr>
        <%--
            遍历出域对象里面的list中的每一个user
        --%>
        <c:forEach items="${list}" var="user" varStatus="vst">
            <tr>
                <td>${vst.count}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.address}</td>
                <td>${user.nickname}</td>
                <td>${user.gender}</td>
                <td>${user.email}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
```



## 注册登录案例改成三层架构

**RegisterServlet代码**

```java
package com.itheima.web.servlet;

import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 包名:${PACKAGE_NAME}
 *
 * @author Leevi
 * 日期2020-07-12  16:15
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //在最前面解决乱码问题:请求参数的中文乱码，响应的中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //1. 获取所有的请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();

        //2. 使用BeanUtils 将parameterMap中的数据，存储到User对象中
        User user = new User();

        //设置默认的status为"0"
        user.setStatus("0");

        try {
            BeanUtils.populate(user,parameterMap);
            //3. 调用业务层的方法处理注册请求
            UserService userService = new UserService();
            userService.register(user);

            //如果存储的时候没有出现问题，则说明注册成功，使用重定向跳转到登录页面
            response.sendRedirect("/userDemo/login.jsp");
        } catch (Exception e) {
            e.printStackTrace();

            //如果注册失败，则向浏览器响应一句"注册失败"
            response.getWriter().write("注册失败");
        }
    }
}
```

**LoginServlet代码**

```java
package com.itheima.web.servlet;

import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * 包名:${PACKAGE_NAME}
 *
 * @author Leevi
 * 日期2020-07-12  16:44
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1. 解决乱码
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            //2. 获取请求参数username和password
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            //获取浏览器传入的验证码(用户输入的验证码)
            String checkCode = request.getParameter("checkCode");
            //获取是否记住用户名,等待登录成功的时候再判断是否需要记住用户名
            String remember = request.getParameter("remember");

            //获取服务器生成的验证码,从session里面根据key "code"取出
            HttpSession session = request.getSession();
            String code = (String) session.getAttribute("code");
            //3. 校验验证码
            if (code.equalsIgnoreCase(checkCode)) {
                //验证码校验通过
                //调用业务层校验登录
                UserService userService = new UserService();
                User user = userService.login(username, password);

                //判断是否登录成功
                if (user != null) {
                    //登录成功
                    //判断是否记住用户名
                    Cookie cookie = CookieUtil.createAndSetCookie("username", username, 7 * 24 * 60 * 60, request.getContextPath());
                    if (remember == null) {
                        //不需要记住用户名
                        cookie.setMaxAge(0);
                    }
                    response.addCookie(cookie);
                    //将user存储到session中
                    session.setAttribute("user",user);
                    //跳转到成功页面success.jsp
                    response.sendRedirect("/userDemo/success.jsp");
                }else {
                    //往request域对象中存放"用户名或密码错误"
                    request.setAttribute("msg","用户名或密码错误");
                    //请求转发跳转到login.jsp页面
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            }else {
                //往request域对象中存放"验证码错误"
                request.setAttribute("msg","验证码错误");
                //请求转发跳转到login.jsp页面
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //往request域对象中存放"服务器异常请稍后再试"
            request.setAttribute("msg","服务器异常请稍后再试");
            //请求转发跳转到login.jsp页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
```

**UserService代码**

```java
package com.itheima.service;

import com.itheima.dao.UserDao;
import com.itheima.pojo.User;

import java.sql.SQLException;

/**
 * 包名:com.itheima.service
 *
 * @author Leevi
 * 日期2020-07-15  11:41
 */
public class UserService {
    private UserDao userDao = new UserDao();
    public void register(User user) throws SQLException {
        //调用dao层的方法，添加用户信息
        userDao.addUser(user);
    }

    public User login(String username,String password) throws SQLException {
        //调用dao层的方法进行用户名和密码的校验
        User user = userDao.findUser(username,password);
        return user;
    }
}
```

**UserDao代码**

```java
package com.itheima.dao;

import com.itheima.pojo.User;
import com.itheima.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * 包名:com.itheima.dao
 *
 * @author Leevi
 * 日期2020-07-15  11:41
 */
public class UserDao {
    public void addUser(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
        String sql = "insert into user values (null,?,?,?,?,?,?,?)";

        queryRunner.update(sql,user.getUsername(),user.getPassword(),user.getAddress(),
                user.getNickname(),user.getGender(),user.getEmail(),user.getStatus());
    }

    public User findUser(String username, String password) throws SQLException {
        //连接数据库校验用户名和密码，也就是执行查询的SQL语句
        QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
        String sql = "select * from user where username=? and password=?";
        //执行查询，查询一条数据，封装到User中
        User user = queryRunner.query(sql, new BeanHandler<>(User.class), username, password);
        return user;
    }
}
```


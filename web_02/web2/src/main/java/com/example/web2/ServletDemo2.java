package com.example.web2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-08 17:40:50
 */

@WebServlet("/demo2")
public class ServletDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 设置响应状态码为302, 重定向, 这个会立即跳转到百度当中
        response.setStatus(302);
        response.setHeader("Location", "http://www.baidu.com");

        response.setStatus(302);
        response.setHeader("Location", "/web2/index.html"); // 可以跳转到任意一个项目里面

        response.sendRedirect("/web2/index.html"); // 重定向简便写法
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

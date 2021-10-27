package com.example.web2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-08 18:05:13
 */

@WebServlet("/demo3")
public class ServletDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 设置服务器响应的字符集为UTF-8
        // 设置了Content-Type响应头的信息为"text/html;charset=UTF-8"
        response.setContentType("text/html;charset=UTF-8");
        // 这种属于字符流
        PrintWriter writer = response.getWriter(); // 要想输出响应体的信息，需要通过流来操作。
        writer.write("hello world"); // 只接收字符串
        writer.print(88); // 可以输出int
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

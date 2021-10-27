package com.example.web2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-20 10:21:18
 */

@WebServlet("/cookieDemo1")
public class ServletCookie1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String str = "姜维洋";
        Cookie cookie = new Cookie("username", str);
        cookie.setMaxAge(60*60); // 设置cookie的有效期为一小时
        cookie.setPath(request.getContextPath()); // 需要使用绝对路径 标记有效范围
        response.addCookie(cookie); // 发送进去了cookie

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

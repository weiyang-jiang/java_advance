package com.example.web2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-20 14:44:32
 */

@WebServlet("/cookieDemo3")
public class ServletCookie3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // 往浏览器里面再添加一个同名同路径的cookie，但是有效期为0
        Cookie cookie = new Cookie("username", "姜维洋");
        cookie.setPath(request.getContextPath());
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

package com.example.web2;

import com.example.utils.CookieUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-20 17:38:16
 */

@WebServlet("/remember")
public class RememberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        Cookie[] cookies = request.getCookies();
        String lastTime = CookieUtils.getCookieValue(cookies, "lastTime");
        String currentTime = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss").format(new Date());
        Cookie cookie = CookieUtils.createAndSetCookie("lastTime", currentTime, 60 * 60, request.getContextPath());
        response.addCookie(cookie);
        if (lastTime != null) {
            response.getWriter().write("上次访问时间"+lastTime);
        }else {
            response.getWriter().write("当前是第一次访问");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

package com.example.web2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-21 10:46:10
 */

@WebServlet("/Session1")
public class ServletSession1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // session就是一个域对象
        String str = "姜维洋";
        HttpSession session = request.getSession();
        session.setAttribute("username", str);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

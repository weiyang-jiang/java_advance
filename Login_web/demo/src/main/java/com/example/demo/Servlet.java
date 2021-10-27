package com.example.demo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Servlet", value = "/Servlet")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        servletContext.getResourceAsStream("mm.jpg"); // 获取图片字节流

        Object attribute = servletContext.getAttribute(""); // 获取其他servlet传输过来的数据
        servletContext.setAttribute("dsad",1); // 给context里面添加公共数据


    }
}

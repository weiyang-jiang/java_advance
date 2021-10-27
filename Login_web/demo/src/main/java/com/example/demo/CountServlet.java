package com.example.demo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/count")
public class CountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        Object count = servletContext.getAttribute("count");
        if (count == null){
            servletContext.setAttribute("count", 1);
        }else {
            count = (int) count + 1;
            servletContext.setAttribute("count", count);
        }
        response.getWriter().write("Welcome!!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

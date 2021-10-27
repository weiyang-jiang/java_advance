package com.example.web_01;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/demo1")
public class ServletDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod(); // 获取请求方法
        String remoteAddr = request.getRemoteAddr(); // 获取远程的ip地址值
        String contextPath = request.getContextPath(); // 获取部署路径
        StringBuffer requestURL = request.getRequestURL(); // 获取到路径，统一资源定位符
        String requestURI = request.getRequestURI(); // 统一资源标识符 在url的基础上省略了前面的地址和端口号


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}

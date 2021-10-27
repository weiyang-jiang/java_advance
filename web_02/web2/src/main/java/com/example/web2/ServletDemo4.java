package com.example.web2;

import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;

/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-08 20:44:20
 */

@WebServlet("/demo4")
public class ServletDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        ServletContext servletContext = getServletContext();
        InputStream is = servletContext.getResourceAsStream("1.jpeg");

        ServletOutputStream outputStream = response.getOutputStream();

//        byte[] buffer = new byte[1024];
//        int len = 0;
//        while ((len = is.read(buffer)) != -1){
//            outputStream.write(buffer, 0, len);
//        }
        IOUtils.copy(is, outputStream);
        outputStream.close();
        is.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

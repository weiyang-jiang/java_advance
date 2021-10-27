package com.example.web2;

import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-18 16:05:37
 */

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String filename = request.getParameter("filename");
        String encodefilename = URLEncoder.encode(filename, "UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ encodefilename); // 让浏览器下载文件
        ServletContext servletContext = getServletContext();
        InputStream is = servletContext.getResourceAsStream("file/" + filename);
        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(is, os);
        is.close();
        os.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

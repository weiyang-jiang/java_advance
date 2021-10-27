package com.example.web2;

import cn.dsna.util.images.ValidateCode;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-21 12:20:54
 */

@WebServlet("/checkCode")
public class ServletCheck extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
        ValidateCode validateCode = new ValidateCode(200, 50, 4, 20);
        ServletOutputStream outputStream = response.getOutputStream();
        validateCode.write(outputStream);
        // 将验证码上面的文字存储到session里面
        request.getSession().setAttribute("checkCode", validateCode.getCode());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

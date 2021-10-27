package com.example.web2;

import com.example.pojo.user;
import com.example.utils.CookieUtils;
import com.example.utils.DruidUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-19 23:52:09
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            String password = request.getParameter("password");
            String username = request.getParameter("username");
            String checkCode = request.getParameter("checkCode");
            String remember = request.getParameter("remember");
            HttpSession session = request.getSession();
            String code = (String) session.getAttribute("checkCode");
            if (checkCode.equalsIgnoreCase(code)){
                if (username != null || password != null){
                    System.out.println(password + "---" + username);
                    String sql = "select * from user where username = ? and password = ?";
                    QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
//                Map<String, Object> u = queryRunner.query(sql, new MapHandler(), username, password);
                    user u = queryRunner.query(sql, new BeanHandler<>(user.class), username, password);
                    if (u == null){
//                        response.getWriter().write("账号密码错误");
                        request.setAttribute("msg", "账号密码错误");
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }else {
                        Cookie cookie = CookieUtils.createAndSetCookie("username", username, 60 * 60, request.getContextPath());
                        if (remember == null){
                            cookie.setMaxAge(0);
                        }
                        response.addCookie(cookie);
//                        response.getWriter().write(u.getNickname());
                        session.setAttribute("user", u);
                        response.sendRedirect("/web2/success.jsp");


                    }
                }
            }else {
//                response.getWriter().write("验证码错误");
                request.setAttribute("msg", "验证码错误");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            response.getWriter().write("登录失败");
            request.setAttribute("msg", "服务器异常");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

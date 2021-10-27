package com.example.web2;

import com.example.pojo.user;
import com.example.utils.DruidUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-19 22:54:09
 */

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        user User = new user();
        User.setStatus("0");
        try {
            BeanUtils.populate(User, parameterMap);
            QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
            String sql = "insert into user values (null, ?, ?, ?, ?, ?, ?, ?)";
            queryRunner.update(sql, User.getUsername(), User.getPassword(), User.getAddress(), User.getNickname()
            , User.getGender(), User.getEmail(), User.getStatus());
            response.sendRedirect("/web2/login.html");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("注册失败");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

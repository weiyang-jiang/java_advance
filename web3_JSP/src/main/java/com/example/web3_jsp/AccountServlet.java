package com.example.web3_jsp;

import com.example.service.AccountService;
import com.example.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-27 09:09:40
 */

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String fromName = request.getParameter("from");
        String toName = request.getParameter("to");
        Double money = Double.valueOf(request.getParameter("money"));

        try {
            AccountService accountService = new AccountService();
            accountService.transfer(money, fromName, toName);
            response.getWriter().write("转账成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("转账失败");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

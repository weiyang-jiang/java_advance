package com.example.web.servlet;

import com.example.entry.PageBean;
import com.example.pojo.LinkMan;
import com.example.service.linkManService;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/*
 * @Author: Weiyang Jiang
 * @Date: 2021-10-04 16:56:17
 */

@WebServlet("/linkMan")
public class LinkManServlet extends HttpServlet {

    private linkManService linkManService = new linkManService();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        Class clazz = this.getClass();
        Method declaredMethod = clazz.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
        declaredMethod.invoke(this, request, response);

    }

    private void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");

        try {
            LinkMan linkMan = linkManService.findById(id);
            request.setAttribute("linkman", linkMan);
            request.getRequestDispatcher("/update.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("数据回显失败");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");

        try {
            linkManService.delete(id);
            response.sendRedirect("/linkMan?action=findAll");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("删除联系人错误0");
        }
    }

    private void findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long currentPage = Long.valueOf(request.getParameter("currentPage"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));

        PageBean<LinkMan> pageBean = null;
        try {
            pageBean = linkManService.findByPage(currentPage, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("page", pageBean);

        request.getRequestDispatcher("/list_page.jsp").forward(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String[]> parameterMap = request.getParameterMap();
        LinkMan linkMan = new LinkMan();
        BeanUtils.populate(linkMan, parameterMap);
        try {
            linkManService.add(linkMan);
            response.sendRedirect("/linkMan?action=findAll");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("添加联系人失败");
        }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            List<LinkMan> list = linkManService.findAll();
            // 存储到域对象中
            request.setAttribute("list", list);
            request.getRequestDispatcher("/list.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("查询所有联系人失败");
        }
    }

    /**
     * 修改联系人
     * @param request
     * @param response
     * @throws IOException
     */
    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            LinkMan linkMan = new LinkMan();
            BeanUtils.populate(linkMan, parameterMap);
            linkManService.update(linkMan);
            response.sendRedirect("/linkMan?action=findAll");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("修改联系人失败");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    

}

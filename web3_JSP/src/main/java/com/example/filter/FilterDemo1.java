package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-10-02 09:32:17
 */

@WebFilter(value = "/FilterDemo1", dispatcherTypes = DispatcherType.REQUEST)
public class FilterDemo1 implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);
    }
}

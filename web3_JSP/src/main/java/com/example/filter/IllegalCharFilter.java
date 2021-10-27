package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-10-02 14:04:16
 */

@WebFilter("/*")
public class IllegalCharFilter implements Filter {
    private String[] strList = {"你大爷","尼玛"};
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        ClassLoader classLoader = req.getClass().getClassLoader();
        HttpServletRequest proxyRequest = (HttpServletRequest) Proxy.newProxyInstance(classLoader, new Class[]{HttpServletRequest.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if (method.getName().equals("getParameter")) {
                    String value = (String) method.invoke(req, args);
                    for (String str : strList) {
                        if (value.contains(str)){
                            String start = "";
                            for (int i = 0; i < str.length(); i++) {
                                start += "*";
                            }
                            value = value.replace(str, start);

                        }
                    }
                    return value;
                }
                return method.invoke(req, args);
            }
        });
        chain.doFilter(proxyRequest, response);
    }
}

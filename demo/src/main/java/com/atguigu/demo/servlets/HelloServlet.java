package com.atguigu.demo.servlets;

import java.io.*;
import java.lang.reflect.Field;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello.do",loadOnStartup = 1)
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String servletPath = request.getServletPath();
//        servletPath.substring(1,servletPath.lastIndexOf(".do"));
//        response.setContentType("text/html");
//        System.out.println("=====");
//        System.out.println(request.getSession().isNew());
//        System.out.println(1/0);
//        // Hello
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + message + "</h1>");
//        out.println("</body></html>");
//        Cookie cookie = new Cookie("key1", "value1");
//        cookie.setMaxAge(30);
//        Field[] declaredFields = HelloServlet.class.getDeclaredFields();
//        for (Field declaredField : declaredFields) {
//            declaredField.getName();
//        }
//
//        response.addCookie(cookie);
//        request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("<script language='javascript'>alert('验证码不正确');</script>");
//        response.sendRedirect("/demo");
    }

    public void destroy() {
    }
}
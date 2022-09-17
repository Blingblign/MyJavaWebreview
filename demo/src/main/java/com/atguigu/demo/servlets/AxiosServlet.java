package com.atguigu.demo.servlets;

import com.atguigu.demo.Parent;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author bling
 * @create 2022-09-17 11:59
 */
@WebServlet("/axios")
public class AxiosServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //获取普通参数并返回
//        String uname = req.getParameter("uname");
//        String pwd = req.getParameter("pwd");
//        String returnValue = uname + "_" + pwd;
//        System.out.println("returnValue = " + returnValue );
//        resp.setContentType("text/html,charset=UTF-8");
//        resp.getWriter().write(returnValue);
//        throw new RuntimeException("程序出错啦！！");
        BufferedReader reader = req.getReader();
        StringBuffer buffer = new StringBuffer();
        String str = "";
        while ((str = reader.readLine()) != null) {
            buffer.append(str);
        }
        str = buffer.toString();
        System.out.println("收到的json字符串："+str);
        Parent parent = new Parent("张三丰", 13, 1003.123);
        String jsonStr = new Gson().toJson(parent);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json,charset=UTF-8");
        resp.getWriter().write(jsonStr);

    }
}

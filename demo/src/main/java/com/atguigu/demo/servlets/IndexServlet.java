package com.atguigu.demo.servlets;

import com.atguigu.demo.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author bling
 * @create 2022-09-14 11:39
 */
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取隐藏域的值
        String oper = req.getParameter("oper");
        //当前操作为关键词查询操作
        String keyword = null;
        if (Objects.equals(oper,"search")) {
            keyword = req.getParameter("keyword");

        }

    }
}

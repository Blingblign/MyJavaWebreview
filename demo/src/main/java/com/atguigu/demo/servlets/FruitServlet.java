package com.atguigu.demo.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author bling
 * @create 2022-09-14 16:59
 */

public class FruitServlet extends ViewBaseServlet {
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        super.processTemplate("Index",req,resp);
    }
}
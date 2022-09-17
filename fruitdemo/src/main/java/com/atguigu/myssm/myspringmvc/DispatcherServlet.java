package com.atguigu.myssm.myspringmvc;

import com.atguigu.myssm.factory.impl.ClassPathXmlApplicationContext;
import com.atguigu.myssm.util.StringUtil;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author bling
 * @create 2022-09-15 9:59
 */
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet{
    private ClassPathXmlApplicationContext classPathXmlApplicationContext;
    @Override
    public void init() throws ServletException {
        super.init();
        //新建bean容器
        classPathXmlApplicationContext = (ClassPathXmlApplicationContext)getServletContext().getAttribute("classPathXmlApplicationContext");
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String operate = req.getParameter("operate");
        if(StringUtil.isEmpty(operate)){
            operate = "index" ;
        }
        //1.解析servletPath:   /fruit.do-->FruitController
        String servletPath = req.getServletPath();
        String controllerName = servletPath.substring(1, servletPath.lastIndexOf(".do"));
        Object beanObj = classPathXmlApplicationContext.getBean(controllerName);
        //2.通过反射获取Controller中的方法，形参名，形参类型,给参数赋值，调用方法
        Method[] beanMethods = beanObj.getClass().getDeclaredMethods();
        Method realMethod = null;
        for (Method beanMethod : beanMethods) {
            String methodName = beanMethod.getName();
            if (operate.equals(methodName)) {
                realMethod = beanMethod;
                break;
            }
        }
        Parameter[] parameters = realMethod.getParameters();
        //声明一个数组用于存放方法的参数值
        Object[] parameterValues = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {

            String parameterName = parameters[i].getName();
            switch (parameterName) {
                case "request":
                    parameterValues[i] = req;
                    break;
                case "response":
                    parameterValues[i] = resp;
                    break;
                case "session":
                    parameterValues[i] = req.getSession();
                    break;
                default:
                    //获得request中相应参数值
                    String parameterValue = req.getParameter(parameterName);
                    Class<?> parameterType = parameters[i].getType();
                    if (Integer.class == parameterType) {
                        int intValue = Integer.parseInt(parameterValue);
                        parameterValues[i] = intValue;
                    }else {
                        parameterValues[i] = parameterValue;
                    }
            }
        }
        realMethod.setAccessible(true);
        try {
            String returnValue = (String)realMethod.invoke(beanObj,parameterValues);
            //3.视图处理 "redirect:fruit.do"
            if (StringUtils.isNullOrEmpty(returnValue)) {
                return;
            }
                if (returnValue.startsWith("redirect:")) {
                    String redirectUrl = returnValue.substring("redirect:".length());
                    resp.sendRedirect(redirectUrl);
                }else if (returnValue.startsWith("json:")) {
                    String jsonStr= returnValue.substring("json:".length());
                    resp.setCharacterEncoding("UTF-8");
                    resp.setContentType("text/json,charset=UTF-8");
                    PrintWriter writer = resp.getWriter();
                    writer.write(jsonStr);
                    writer.flush();
                }
                else {
                    super.processTemplate(returnValue,req,resp);
                }

        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}

package com.atguigu.myssm.factory.impl;

import com.atguigu.myssm.factory.Beanfactory;
import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bling
 * @create 2022-09-15 10:10
 */
public class ClassPathXmlApplicationContext implements Beanfactory {
    private final Map<String,Object> beanMap = new HashMap<>();
    private static final String applicationPath = "applicationContext.xml";
    public ClassPathXmlApplicationContext() {
        this(applicationPath);
    }
    public ClassPathXmlApplicationContext(String applicationPath) {

        //读取xml文件
        InputStream resourceAsStream = ClassPathXmlApplicationContext.class.getClassLoader().getResourceAsStream(applicationPath);
        DocumentBuilderFactory documentBuilderFactory = new DocumentBuilderFactoryImpl();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();

            //解析xml文件
            Document document = documentBuilder.parse(resourceAsStream);
            NodeList beanNodeList = document.getElementsByTagName("bean");
            //控制反转，向map容器中添加bean对象
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String beanClassName = beanElement.getAttribute("name");
                    Class<?> beanClazz = Class.forName(beanClassName);
                    Object beanObj = beanClazz.newInstance();
                    beanMap.put(beanId,beanObj);
                }
            }
            //组装bean的依赖关系
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    NodeList beanChildNodes = beanElement.getChildNodes();
                    //遍历bean的子节点得到property节点
                    for (int j = 0; j < beanChildNodes.getLength(); j++) {
                        Node beanChildNode = beanChildNodes.item(j);
                        if ("property".equals(beanChildNode.getNodeName())) {
                            Element beanProperty = (Element) beanChildNode;
                            //得到property节点的name，ref属性
                            String beanPropertyName = beanProperty.getAttribute("name");
                            String beanPropertyRef = beanProperty.getAttribute("ref");
                            //得到依赖的bean对象
                            Object beanObjRef = beanMap.get(beanPropertyRef);
                            //依赖注入
                            Object beanObj = beanMap.get(beanId);
                            Field beanField = beanObj.getClass().getDeclaredField(beanPropertyName);
                            beanField.setAccessible(true);
                            beanField.set(beanObj,beanObjRef);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


     @Override
    public Object getBean(String beanId) {
        return beanMap.get(beanId);
    }
}

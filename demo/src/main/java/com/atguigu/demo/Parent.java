package com.atguigu.demo;

import com.google.gson.Gson;
import org.junit.Test;
import sun.nio.cs.ext.GBK;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.util.*;

/**
 * @author bling
 * @create 2022-09-09 15:41
 */
public class Parent {
    private String name;
    private int age = 1;
    private double money;

    public Parent(String name, int age, double money) {
        this.name = name;
        this.age = age;
        this.money = money;
    }

    static {
        System.out.println(1);
    }
    {
        System.out.println(4);
    }
    public Parent() {
        get();
    }
    public void service() {
        System.out.println("service...");
        get();
    }
    public void get() {
        System.out.println("父类的get方法");
    }
    @Test
    public void test1() {
        Parent child = new Child();
//        child.service();
    }

}
class Child extends Parent {
    static {
        System.out.println(2);
    }
    {
        System.out.println(3);
    }
    @Override
    public void get() {
        System.out.println("子类的get方法");
    }
}
class Test1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Parent child = new Child();
//        new Child();
//        byte b = 11;
//        String s = "abc";
//        byte[] b1 = {86,66,67};
//        int i = 67;
//        System.out.println(new String(b1,StandardCharsets.UTF_8));
//        Path file = Files.createFile(Paths.get("E://fileTest//file1.txt"));
//        OutputStream outputStream = Files.newOutputStream(file);
//        outputStream.write(new String("你好啊啊啊中国！").getBytes("GBK"));
//        FileReader fileReader = new FileReader("E://fileTest//file1.txt");
//        String encoding = fileReader.getEncoding();
//
//        System.out.println(encoding);
//        char[] chars = new char[20];
//        int len;
//        while ((len = fileReader.read(chars)) != -1) {
//            System.out.println(Arrays.toString(chars));
//        }
//        Child child = new Child();
//        Class<? extends Child> clazz = child.getClass();
//        Class<Parent> parentClass = Parent.class;
//        HashMap<String, Integer> map = new HashMap<>();
//        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
//        List<? super Child> list = new ArrayList<Parent>();
//        list.add(new Child());

        Class<Child> fatherClass = Child.class;
        Class.forName("com.atguigu.demo.Father");
        System.out.println(URLEncoder.encode("中文.jpg", "UTF-8"));
        Gson gson = new Gson();


    }
}
class Father {
    static int b = 2;
    static {
        System.out.println("父类被加载");
    }
}
class A extends Father {
    static {
        System.out.println("子类被加载");
        m = 300;
    }
    static int m = 100;
    static final int M = 1;
}



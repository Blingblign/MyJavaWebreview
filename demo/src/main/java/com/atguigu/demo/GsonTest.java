package com.atguigu.demo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bling
 * @create 2022-09-13 19:49
 */
public class GsonTest {
    static class Person {
        private int age;
        private String name;
        public Person() {
        // TODO Auto-generated constructor stub
        }
        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return "Person [age=" + age + ", name=" + name + "]";
        }
    }
    // 要把复杂的 json 字符串转换成为 java 对象。需要继承 TypeToken 类。// 并把返回的类型当成 TypeToken 的泛型注入
    static class PersonType extends TypeToken<List<Person>> {
    }
    public static void main(String[] args) {
        // json 操作，一定要先 new 一个 gson 对象。
        Gson gson = new Gson();
        // java 对象--json
        Person person = new Person(12, "wzg168");
        // 把对象转成为 json 字符串
        String personjson = gson.toJson(person);
        String str = "{\"age\":12,\"name\":\"wzg11\"}";
        System.out.println(str);
        System.out.println(personjson);
        // 把 json 字符串转换成为 java 对象
        Person p = gson.fromJson(personjson, Person.class);
        System.out.println(p);
        System.out.println("------------------------------------------");
        // 2、java 对象 list 集合和 json 的转换
        List<Person> list = new ArrayList<Person>();
        for (int i = 0; i < 3; i++) {
            list.add(new Person(10 * i, "name-" + i));
        }
        String jsonListString = gson.toJson(list);
        System.out.println(jsonListString);
        // 把 json 数组转换成为 List 对象
// List<Person> ps = gson.fromJson(jsonListString, new PersonType().getType());// 我们也可以使用匿名内部类
        List<Person> ps = gson.fromJson(jsonListString, new TypeToken<List<Person>>(){}.getType());
        System.out.println(ps);
        System.out.println("------------------------------------------");
        // 3、map 对象和 json 的转换
        Map<String, Person> mapPerson = new HashMap<String, Person>();// 添加 person 到 map 中
        mapPerson.put("p1", new Person(1, "person-1"));
        mapPerson.put("p2", new Person(2, "person-2"));
        // 把 map 转换成为 json 对象
        String jsonMapString = gson.toJson(mapPerson);
        System.out.println(jsonMapString);
        // 通过使用匿名内部类的方式
        Map<String, Person> map = gson.fromJson(jsonMapString,
                new TypeToken<HashMap<String, Person>>() {}.getType());
        System.out.println(map);
    }
}

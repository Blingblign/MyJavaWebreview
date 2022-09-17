import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author bling
 * @create 2022-09-14 18:44
 */
public class MyTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        Child child = new Child();
        child.test();
        System.out.println(child.getName());
        Method initMethod = Parent.class.getDeclaredMethod("getResult", String.class,Integer.class);
        Parameter[] parameters = initMethod.getParameters();
        for (Parameter parameter : parameters) {
            System.out.println(parameter.getName());
        }
        Integer age = null;
        Field nameField = Parent.class.getDeclaredField("name");
        nameField.setAccessible(true);
        Parent parent = new Parent();
        nameField.set(parent,"aaa");
//        age.intValue();
//        System.out.println(age-1);
    }
}


class Parent {
    private String name;
    public String age;

    public void init(String name) {
        this.name = name + "123";
//        this.init("Z");
    }

    public void getResult(String str,Integer age) {
        System.out.println("父类getResult方法");
    }
}


class Child extends Parent{
    private String name;

//    public void init(String name) {
//        this.name = name;
//        super.init(name);
//    }
    public static void test() {
//        init("abc");
//        age = "123";
    }

    public void getResult() {
        System.out.println("子类getResult方法");
    }

    public String getName() {
        return name;
    }
}


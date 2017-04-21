package com.interview4j.ob;

import java.io.*;
import java.util.Date;

/**
 * java 实例化对象的五种方法
 * @author Zhaky
 * @version V1.0
 * @create 2017-04-21 下午4:10
 */
public class ObjectDemo {
    /**
     * 1、用new语句创建对象，这是最常见的创建对象的方法。
     * 2、通过工厂方法返回对象，如：String str = String.valueOf(23); 
     * 3、运用反射手段,调用java.lang.Class或者java.lang.reflect.Constructor类的newInstance()实例方法。如：Object obj = Class.forName("java.lang.Object").newInstance(); 
     * 4、调用对象的clone()方法。
     * 5、通过I/O流（包括反序列化），如运用反序列化手段，调用java.io.ObjectInputStream对象的 readObject()方法。
     */
    public void objectCreate() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        //1、new 创建
        String obj1 = new String();
        //2、工厂方法
        // 底层 return Integer.toString(i);
        String obj2 = String.valueOf(23);
        //3、反射方法
        /*
        *通过反射对对象进行初始化
        *注意必须有无参数的Constructor
        *实例化Class类然后调用newInstance()方法
        *
        */
        Object obj6 = Class.forName("java.lang.Object").newInstance();
        //4、克隆对象 clone方法是protected的所以子类要重写这个方法
        Person p1 = new Person("王豪博", 25);
        System.out.println(p1);
        Person p2 = null;
        try {
            p2 = (Person) p1.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p2.setName("春香");
        p2.setAge(24);
        System.out.println(p2);

        //5、通过I/O流
        ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream("D:/objectFile.swf"));
        //序列化对象
        Person p3 = new Person("王豪博", 25);
        out.writeObject("你好!");
        out.writeObject(new Date());
        out.writeObject(p3);
        out.writeInt(123); //写入基本类型数据
        out.close();
        //反序列化对象
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:/objectFile.swf"));
        System.out.println("obj1=" + (String) in.readObject());
        System.out.println("obj2=" + (Date) in.readObject());
        Person obj3 = (Person) in.readObject();
        System.out.println("obj3=" + obj3);
        int obj4 = in.readInt();
        System.out.println("obj4=" + obj4);
        in.close();
    }


    /**
     * @author Zhaky
     * @version V1.0
     * @create 2017-04-21 下午4:32
     * *测试Cloneable接口的使用
     * 包含第一种和第三种方法clone()
     * 不过要注意在clone()中深复制和潜复制的理解
     * 实例化对象
     */
    public class Person implements Cloneable, Serializable {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
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
        public Object clone() throws CloneNotSupportedException {
            // TODO Auto-generated method stub
            return super.clone();
        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return "姓名是：" + name + "; 年龄是：" + age;
        }

    }

}



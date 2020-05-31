package com.springbootdemo.demo1test;

import com.springbootdemo.demo1.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class Demo1test {
    //获取配置文件中的age
    @Value("${info.age}")
    private int age;

    //获取配置文件中的name
    @Value("${info.name}")
    private String name;

    @Value("${info.id}")
    private int id;


    /**
     * 输出测试类中的属性值, 属性直接从配置文件自动装配
     */
    @Test
    public void getInfo() {
        System.out.println(name+age);
    }

    @Autowired
    private UserService userService;

    /**
     * 输出 IoC 容器对象属性的值, 对象自动装配.
     * 属性使用 @Value 注解获取配置文件的内容
     */
    @Test
    public void getUser(){
        userService.init_user();
        System.out.println(userService.getId() + userService.getName());
        userService.destroy_user();
    }

    @Autowired
    private GetPersonInfoProperties getPersonInfoProperties;

    /**
     * 输出 IoC 容器对象的属性值, 对象自动装配
     * 属性使用 @ConfigurationProperties 注解获取配置文件内容
     */
    @Test
    public void getPersonproperties() {
        System.out.println(getPersonInfoProperties.getName()+getPersonInfoProperties.getAge());
    }


    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void IoCTestByClass(){
//        通过 class 方式获取 bean 对象
        UserService user = applicationContext.getBean(UserService.class);
        System.out.println(user.getId() + user.getName());
    }

    @Test
    public void IocTestByName(){
        //        通过 Name 的方式获取 bean 对象
        UserService user1 = (UserService) applicationContext.getBean("user1");
        System.out.println(user1.getId() + user1.getName());
    }

}
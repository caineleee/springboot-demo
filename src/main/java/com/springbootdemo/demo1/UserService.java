package com.springbootdemo.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @program: JavaReview
 * @description:
 * @author: Mr.Lee
 * @create: 2020-05-17 14:23
 **/

@Component
public class UserService {

    @Value("${info.id}")
    int id;
    @Value("${info.name}")
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Autowired
    public UserService(){
        super();
        System.out.println("------User对象创建 (NoParams)------");
    }


    public void init_user() {
        System.out.println("创建对象之后，初始化");
    }
    public void destroy_user() {
        System.out.println("IOC容器销毁，user对象回收!");
    }

}


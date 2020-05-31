package com.springbootdemo.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @program: springboot-demo
 * @description:
 * @author: Mr.Lee
 * @create: 2020-05-31 21:13
 **/

@Component
public class UserBean {

    @Bean("user1")
    @Scope("prototype")
    private UserService user(){
        return new UserService();
    }
}

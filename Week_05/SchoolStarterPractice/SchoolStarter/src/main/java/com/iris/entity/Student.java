package com.iris.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@ConfigurationProperties(prefix = "spring.data.student")
public class Student implements Serializable, InitializingBean {
    
    private int id;
    private String name;
    
    public void init(){
        System.out.println("hello...........");
    }
    
    public Student create(){
        return new Student(101,"KK101");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet -- 初始化Student:" + id + "\tname: " + name);
    }
}

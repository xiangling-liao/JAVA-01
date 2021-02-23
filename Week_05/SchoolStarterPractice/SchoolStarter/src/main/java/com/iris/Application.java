package com.iris;

import com.iris.entity.ISchool;
import com.iris.entity.School;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        System.out.println("--->2. java-based configuration...");
        ApplicationContext annotationContext = new AnnotationConfigApplicationContext("com.iris.autoconfigure.school", "com.iris.entity");
        ISchool school = (School) annotationContext.getBean("school");
        school.ding();
    }
}

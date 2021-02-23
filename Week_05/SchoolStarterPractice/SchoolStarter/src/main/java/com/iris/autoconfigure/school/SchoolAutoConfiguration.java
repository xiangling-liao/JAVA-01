package com.iris.autoconfigure.school;

import com.iris.entity.ISchool;
import com.iris.entity.Klass;
import com.iris.entity.School;
import com.iris.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class SchoolAutoConfiguration {

    @Bean("student01")
    public Student student01() {
        System.out.println("init student01");
        return new Student(1, "nova");
    }

    @Bean("student02")
    public Student student02() {
        System.out.println("init student02");
        return new Student(2, "nori");
    }

    @Bean
    public Klass getKlass() {
        System.out.println("init klass");
        List<Student> studentList = new ArrayList<>();
        studentList.add(student01());
        studentList.add(student02());

        return new Klass(studentList);
    }

    @Bean("school")
    public ISchool getSchool() {
        System.out.println("init school");
        return new School(getKlass(), student01());
    }

}

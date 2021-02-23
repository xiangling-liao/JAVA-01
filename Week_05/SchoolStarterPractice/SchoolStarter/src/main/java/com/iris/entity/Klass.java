package com.iris.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Klass { 
    
    List<Student> students;
    
    public void dong(){
        System.out.println(this.getStudents());
    }
    
}

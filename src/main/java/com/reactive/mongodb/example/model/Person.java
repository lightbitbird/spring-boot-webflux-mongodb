package com.reactive.mongodb.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;

//@Document
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//public class Person {
//    @Id
//    private String id;
//    private String name;
//    private int age;
//
////    public Person(final String id, final String name, final int age) {
////        this.id = id;
////        this.name = name;
////        this.age = age;
////    }
//
//}

@Document(collection = "person")
public class Person implements Serializable {
    private static final long serialVersionUID = -5836738964486511156L;

    @Id
    private String id;
    private String name;
    private int age;

    public Person() {
    }

    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
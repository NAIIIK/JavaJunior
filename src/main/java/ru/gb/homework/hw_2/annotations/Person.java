package ru.gb.homework.hw_2.annotations;

import java.util.Date;

public class Person {
    @Random
    private int age;

    @RandomDate
    private Date date;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}

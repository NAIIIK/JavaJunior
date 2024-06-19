package ru.gb.homework.hw_1;

import java.util.Objects;

public class Person {
    private String name;
    private int age;
    private double salary;
    private Department department;

    public Person(String name, int age, double salary, Department department) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.department = department;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Double.compare(salary, person.salary) == 0 && Objects.equals(name, person.name) && Objects.equals(department, person.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, salary, department);
    }

    @Override
    public String toString() {
        return "Person [" +
                "Name: " + name +
                ", age: " + age +
                ", salary: " + salary +
                ", department: " + department +
                ']' + "\n";
    }
}

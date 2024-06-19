package ru.gb.homework.hw_1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Department> departments = List.of(new Department("Finance"),
                new Department("Marketing"),
                new Department("Accounting"),
                new Department("HR"),
                new Department("Sales"));

        List<Person> employees = List.of(new Person("Michael", 35, 100_000.0, departments.get(1)),
                new Person("Hanna", 28, 65_000.0, departments.get(3)),
                new Person("George", 44, 95_000.0, departments.getFirst()),
                new Person("John", 22, 40_000.0, departments.get(2)),
                new Person("Barbara", 31, 70_000.0, departments.get(4)),
                new Person("Chris", 39, 90_000, departments.get(1)),
                new Person("Hanna", 25, 60_000.0, departments.get(3)),
                new Person("Kenny", 27, 50_000.0, departments.get(2)),
                new Person("Harvey", 34, 90_000.0, departments.getFirst()),
                new Person("Liam", 40, 110_000, departments.get(4)));

        System.out.println(OfficeService.findYoungestPerson(employees));
        System.out.println("************************************************");
        System.out.println(OfficeService.cheapPersonsInDepartment(employees));
        System.out.println("************************************************");
        System.out.println(OfficeService.findMostExpensiveDepartment(employees));
        System.out.println("************************************************");
        System.out.println(OfficeService.getDepartmentOldestPerson(employees));
        System.out.println("************************************************");
        System.out.println(OfficeService.groupByDepartment(employees));
        System.out.println("************************************************");
        System.out.println(OfficeService.groupByDepartmentName(employees));
    }
}

package ru.gb.homework.hw_1;

import java.util.*;
import java.util.stream.Collectors;

public class OfficeService {
    /**
     * Найти самого молодого сотрудника
     */
    static Optional<Person> findYoungestPerson(List<Person> people) {
        return people.stream()
                .min(Comparator.comparingInt(Person::getAge));
    }

    /**
     * Найти департамент, в котором работает сотрудник с самой большой зарплатой
     */
    static Optional<Department> findMostExpensiveDepartment(List<Person> people) {
        return people.stream().max(Comparator.comparingDouble(Person::getSalary)).map(Person::getDepartment);

    }

    /**
     * Сгруппировать сотрудников по департаментам
     */
    static Map<Department, List<Person>> groupByDepartment(List<Person> people) {
        return people.stream().collect(Collectors.groupingBy(Person::getDepartment));
    }

    /**
     * Сгруппировать сотрудников по названиям департаментов
     */
    static Map<String, List<Person>> groupByDepartmentName(List<Person> people) {
        return people.stream().collect(Collectors.groupingBy(p -> p.getDepartment().getName()));
    }

    /**
     * В каждом департаменте найти самого старшего сотрудника
     */
    static Map<String, Person> getDepartmentOldestPerson(List<Person> people) {
        return people.stream().collect(Collectors.toMap(
                p -> p.getDepartment().getName(),
                p -> p,
                (a, b) -> {
                    if (a.getSalary() > b.getSalary()) return a;
                    return b;
                }
        ));
    }

    /**
     * *Найти сотрудников с минимальными зарплатами в своем отделе
     * (прим. можно реализовать в два запроса)
     */
    static List<Person> cheapPersonsInDepartment(List<Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(Person::getDepartment))
                .values()
                .stream()
                .map(p -> p.stream()
                        .min(Comparator.comparingDouble(Person::getSalary))
                        .get())
                .collect(Collectors.toList());
    }
}

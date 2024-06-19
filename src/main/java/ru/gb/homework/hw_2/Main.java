package ru.gb.homework.hw_2;

import ru.gb.homework.hw_2.annotations.Person;
import ru.gb.homework.hw_2.creator.ObjectCreator;

public class Main {
    public static void main(String[] args) {
        Person person = ObjectCreator.createObject(Person.class);

        System.out.println(person.getAge());
        System.out.println(person.getDate());
    }
}

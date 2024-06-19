package ru.gb.homework.hw_2.creator;

import ru.gb.homework.hw_2.annotations.RandomAnnotationProcessor;

import java.lang.reflect.Constructor;

public class ObjectCreator {
    public static <T> T createObject(Class<T> tClass) {
        try {
            Constructor<T> constructor = tClass.getDeclaredConstructor();

            T obj = constructor.newInstance();
            RandomAnnotationProcessor.processAnnotation(obj);
            return obj;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }
}

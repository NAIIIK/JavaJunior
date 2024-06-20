package ru.gb.homework.hw_2.annotations;

import java.lang.reflect.Field;
import java.util.Date;

public class RandomAnnotationProcessor {

    public static void processAnnotation(Object object) {
        java.util.Random random = new java.util.Random();
        Class<?> objClass = object.getClass();

        for (Field field: objClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Random.class) && field.getType().isAssignableFrom(int.class)) {
                Random randomAnnotation = field.getAnnotation(Random.class);
                try {
                    field.setAccessible(true); // To change final fields
                    field.set(object, random.nextInt(randomAnnotation.min(), randomAnnotation.max()));
                } catch (IllegalAccessException e) {
                    System.err.println("Unable to change the" + field.getName() + "field: " + e.getMessage());
                }
            }
            if (field.isAnnotationPresent(RandomDate.class) && field.getType().isAssignableFrom(Date.class)) {
                RandomDate randomDateAnnotation = field.getAnnotation(RandomDate.class);
                try {
                    field.setAccessible(true);
                    if (randomDateAnnotation.min() < randomDateAnnotation.max()) {
                        field.set(object, new Date(random.nextLong(randomDateAnnotation.min(), randomDateAnnotation.max())));
                    } else System.err.println("min value can not be less than max value");
                } catch (IllegalAccessException e) {
                    System.err.println("Unable to change the" + field.getName() + "field: " + e.getMessage());
                }
            }
        }
    }
}

package ru.gb.homework.hw_2.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Random {

    int min() default 0;

    int max() default 100;

    /*
    primitives
    String
    enum
    class
    arrays of all the above
     */
}
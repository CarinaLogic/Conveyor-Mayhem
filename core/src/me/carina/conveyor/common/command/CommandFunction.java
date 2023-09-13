package me.carina.conveyor.common.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CommandFunction {
    String[] altNames() default {};
    boolean suppressOriginalName() default false;
}

package com.classical.aono.classicalcat.common;

/**
 * Created by gotha on 2017/9/27.
 */


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface ConverterName
{
    String value() default "gson";
}
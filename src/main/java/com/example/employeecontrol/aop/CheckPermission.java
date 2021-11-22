package com.example.employeecontrol.aop;

import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface CheckPermission {
    String permission();
    String  permission1();
}

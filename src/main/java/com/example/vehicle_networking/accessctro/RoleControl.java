package com.example.vehicle_networking.accessctro;





import com.example.vehicle_networking.enums.RoleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/3/29 19:40
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleControl {
    RoleEnum role();
}

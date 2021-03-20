package com.code.monotor.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/20 19:14
 */
@Component
public class Decode {

    public static Object decoder(Class clazz, String message) {
        try {
            clazz.newInstance();
            Arrays.stream(clazz.getFields()).forEach(field -> {
                field.getName().equals("");
            });

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return new Object();
    }

}

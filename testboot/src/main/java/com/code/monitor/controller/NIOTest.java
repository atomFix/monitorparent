package com.code.monitor.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/3 21:06
 */
public class NIOTest {
    public static void main(String[] args) {

        try (FileOutputStream output = new FileOutputStream("");
             FileInputStream input = new FileInputStream("")) {
            int len = -1;
            byte[] bytes = new byte[1024 * 1024];
            while ((len = input.read(bytes)) != -1) {
                output.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

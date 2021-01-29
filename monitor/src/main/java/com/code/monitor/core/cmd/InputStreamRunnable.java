package com.code.monitor.core.cmd;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author codedorado
 * @date 21/1/26
 */
public class InputStreamRunnable implements Runnable {
    private BufferedReader bReader = null;

    InputStreamRunnable(InputStream is, String type) {
        try {
            bReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(is), StandardCharsets.UTF_8));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        String line;
        int num = 1;
        try {
            while ((line = bReader.readLine()) != null) {
                //System.out.println("---->"+String.format("%02d",num++)+" "+line);
            }
            bReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

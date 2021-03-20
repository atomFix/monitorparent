package com.code.monitor.core.order.impl;

import com.code.monitor.core.constant.JavaOptionConstant;
import com.code.monitor.core.order.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author codedorado
 * @date 2021/01/26
 */
public class JavaVersionOrder implements Order {

    static final JavaVersionOrder JAVA_VERSION_ORDER = new JavaVersionOrder();

    public static Order getInstance() {
        return JAVA_VERSION_ORDER;
    }

    private String version(){
        StringBuilder sb;
        try {
            Process p = Runtime.getRuntime().exec(new String[]{JavaOptionConstant.JAVA, JavaOptionConstant.VERSION});
            InputStreamReader inputStreamReader = new InputStreamReader(p.getErrorStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            bufferedReader.close();
            p.destroy();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取 T 对象的信息
     *
     * @return T
     */
    @Override
    public Object getInfo() throws Exception {
        return version();
    }

    @Override
    public String getOrderName() {
        return null;
    }
}

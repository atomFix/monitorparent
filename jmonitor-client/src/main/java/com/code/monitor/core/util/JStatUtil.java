package com.code.monitor.core.util;

import com.code.monitor.core.cmd.ExecuteCmd;
import com.code.monitor.core.constant.JavaOptionConstant;
import com.code.monitor.core.entity.KVEntity;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/1/26 22:31
 */
public class JStatUtil {
    /**
     * JStat 模板方法
     *
     * @param strings 命令
     * @return 集合
     */
    public static List<KVEntity> jStat(String[] strings) throws Exception {
        List<KVEntity> list = new ArrayList<>();
        String s = ExecuteCmd.execute(strings);
        assert s != null;
        BufferedReader reader = new BufferedReader(new StringReader(s));
        String[] keys = ArrayUtil.trim(reader.readLine().split("\\s+|\t"));
        String[] values = ArrayUtil.trim(reader.readLine().split("\\s+|\t"));
        // 特殊情况
        if (JavaOptionConstant.COMPILER.equals(strings[1])) {
            for (int i = 0; i < 4; i++) {
                list.add(new KVEntity(keys[i], values[i]));
            }
            return list;
        }
        // 正常流程
        for (int i = 0; i < keys.length; i++) {
            list.add(new KVEntity(keys[i], values[i]));
        }
        return list;
    }
}

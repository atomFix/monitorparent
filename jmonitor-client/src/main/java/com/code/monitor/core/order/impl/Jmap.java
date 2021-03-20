package com.code.monitor.core.order.impl;


import com.code.monitor.core.cmd.ExecuteCmd;
import com.code.monitor.core.constant.ProcessConstant;
import com.code.monitor.core.util.PathUtil;
import com.code.monitor.exception.DumpException;

import java.io.File;
import java.io.IOException;

/**
 * @author codedorado
 * @date 2021/01/26
 */
public class Jmap {

    /**
     * 导出堆快照
     *
     * @return path
     */
    public static String dump() throws IOException {
        String id = ProcessConstant.PID;
        //检验dump目录是否存在
        File dump = new File(PathUtil.getRootPath("dump/"));
        if (!dump.exists()) {
            dump.mkdirs();
        }
        //若有已经存在的快照文件则删除
        String path = PathUtil.getRootPath("dump/" + id + "_heap.hprof");
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        //生成快照文件
        ExecuteCmd.execute(new String[]{"jmap", "-dump:format=b,file=" + path, id});
        if (!file.exists()) {
            throw new DumpException(id);
        }
        return path;
    }

}

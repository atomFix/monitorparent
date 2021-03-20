package com.code.monitor.core.util;


import java.io.File;

/**
 * 在windows和linux系统下均可正常使用
 *
 * @author 86178
 */
public class PathUtil {
    /**
     * 获取项目的根路径
     */
    public final static String classPath;

    static {
        //获取的是classpath路径，适用于读取resources下资源
        //classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        classPath = System.getProperty("user.dir");
    }

    /**
     * 项目根目录
     */
    public static String getRootPath() {
        return rootPath("");
    }

    /**
     * 自定义追加路径
     */
    public static String getRootPath(String u_path) {
        return rootPath("/" + u_path);
    }

    /**
     * 私有处理方法
     */
    private static String rootPath(String u_path) {
        String rootPath = "";
        //windows下
        if ("\\".equals(File.separator)) {
            rootPath = classPath + u_path;
            rootPath = rootPath.replaceAll("/", "\\\\");
            if (rootPath.startsWith("\\")) {
                rootPath = rootPath.substring(1);
            }
        }
        //linux下
        if ("/".equals(File.separator)) {
            rootPath = classPath + u_path;
            rootPath = rootPath.replaceAll("\\\\", "/");
        }
        return rootPath;
    }

    //更多扩展方法任你发挥

}

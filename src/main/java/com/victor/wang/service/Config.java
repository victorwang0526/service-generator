package com.victor.wang.service;

import java.util.HashMap;
import java.util.Map;

public class Config {
    public static String DirPath = "f:\\workspace_java";

    public static String Group = "com.victor.wang";

    public static String ProjectName = "Test"; //will add Service auto, the project name will be TestService

    public static String projectPath = DirPath + "\\" + ProjectName + "Service";

    public static String pkg = Group + "." + ProjectName.toLowerCase();

    public static Map<String, Object> root = new HashMap<>();

    static {
        root.put("group", Group);
        root.put("pkg", pkg);
        root.put("projectName", ProjectName.substring(0,1).toUpperCase() + ProjectName.substring(1).toLowerCase());
        root.put("projectNameLow", ProjectName.toLowerCase());
    }
}

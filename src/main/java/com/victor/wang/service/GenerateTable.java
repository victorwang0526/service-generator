package com.victor.wang.service;

import freemarker.template.Configuration;

import static com.victor.wang.service.Config.ProjectName;
import static com.victor.wang.service.Config.Group;
import static com.victor.wang.service.Config.DirPath;


public class GenerateTable {

    public static void main(String[] args) {
        try{
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

            cfg.setClassForTemplateLoading(GenerateTable.class, "/");
            // Set the preferred charset template files are stored in. UTF-8 is
            // a good choice in most applications:
            cfg.setDefaultEncoding("UTF-8");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

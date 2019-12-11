package com.victor.wang.service;

import com.victor.wang.service.jsm.JDBCUtils;
import com.victor.wang.service.jsm.JsmColumn;
import freemarker.template.Configuration;

import java.util.List;

import static com.victor.wang.service.Config.*;
import static com.victor.wang.service.util.GeneratorUtil.generateForModel;


public class GenerateTable {

    public static void main(String[] args) {
        try{
            new GenerateTable().generateAll("t_book", "Book");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * table name should be like t_carrot
     *
     * @param tableName like "t_carrot"
     * @param modelName like "Carrot"
     */
    public void generateAll(String tableName, String modelName) {

        try{
            if(tableName == null || tableName.equals("")){
                return;
            }
            List<JsmColumn> columns = JDBCUtils.getJsmColumn(tableName);

            root.put("tableName", tableName);
            root.put("modelName", modelName);
            root.put("modelNameL", modelName.substring(0,1).toLowerCase() + modelName.substring(1));
            root.put("modelNameU", modelName.toUpperCase());
            root.put("columns", columns);
            root.put("hasDate", columns.stream().filter(c -> c.getJavaType().equals("Date")).toArray().length > 0);

            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

            cfg.setClassForTemplateLoading(GenerateProject.class, "/");
            // Set the preferred charset template files are stored in. UTF-8 is
            // a good choice in most applications:
            cfg.setDefaultEncoding("UTF-8");

            // dao
            generateForModel(cfg, packagePath + "\\dao", "dao", "Dao.java.flt", modelName);

            // exception
            generateForModel(cfg, packagePath + "\\exception", "exception", "NotFoundException.java.flt", modelName);

            // model
            generateForModel(cfg, packagePath + "\\model", "model", ".java.flt", modelName);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

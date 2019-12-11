package com.victor.wang.service.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static com.victor.wang.service.Config.root;

public class GeneratorUtil {

    /**
     * generate java api service
     */
    public static void generateForProject(Configuration cfg, String dirPath, String templatePath, String templateName) throws IOException, TemplateException {
        generate(cfg, dirPath, "templates", templatePath, templateName, "");
    }

    /**
     * generate model/dao/mapper...
     */
    public static void generateForModel(Configuration cfg, String dirPath, String templatePath, String templateName, String modelName) throws IOException, TemplateException {
        generate(cfg, dirPath, "service-model", templatePath, templateName, modelName);
    }

    /**
     * @param cfg
     * @param dirPath
     * @param templates templates/service-model
     * @param templatePath
     * @param templateName the template name, like a.flt
     * @throws IOException
     */
    public static void generate(Configuration cfg, String dirPath, String templates, String templatePath, String templateName, String filePrefix) throws IOException, TemplateException {
        String fileName = filePrefix + templateName.replace(".flt", "");
        Template template = cfg.getTemplate(templates + "/" + templatePath + "/" + templateName);

        File projectDir = new File(dirPath);
        mkdirs(projectDir);

        Path path = Paths.get(projectDir.getPath() + "\\" + fileName);
        Writer writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"),
                StandardOpenOption.CREATE, StandardOpenOption.CREATE);

        template.process(root, writer);

        writer.flush();
        writer.close();
    }

    public static void mkdirs(File file) {

        if(!file.getParentFile().exists()) {
            mkdirs(file.getParentFile());
        }
        if (!file.exists()) {
            file.mkdir();
        }
    }
}

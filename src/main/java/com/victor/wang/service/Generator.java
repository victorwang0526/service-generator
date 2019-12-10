package com.victor.wang.service;

import freemarker.template.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Generator {

    public static String dirPath = "f:\\workspace_java";

    public static String group = "com.victor.wang";

    public static String projectName = "Test"; //will add Service auto, the project name will be TestService

    public static String pkg = group + "." + projectName.toLowerCase();

    public static Map<String, Object> root = new HashMap<>();

    static {
        root.put("group", group);
        root.put("pkg", pkg);
        root.put("projectName", projectName.substring(0,1).toUpperCase() + projectName.substring(1).toLowerCase());
        root.put("projectNameLow", projectName.toLowerCase());
    }

    public static void main(String[] args) {

        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

            cfg.setClassForTemplateLoading(Generator.class, "/");
            // Set the preferred charset template files are stored in. UTF-8 is
            // a good choice in most applications:
            cfg.setDefaultEncoding("UTF-8");

            String projectPath = dirPath + "\\" + projectName + "Service";
            // project file
            generator(cfg, projectPath, "", "build.gradle.flt");
            // generator(cfg, dirPath, "codestyle.jar");
            generator(cfg, projectPath, "", "README.md.flt");
            generator(cfg, projectPath, "", "settings.gradle.flt");

            // DBMigration
            String dbMigrationPath = projectPath + "\\DBMigration";
            generator(cfg, dbMigrationPath, "DBMigration", "build.gradle.flt");
            generator(cfg, dbMigrationPath + "\\liquibase", "DBMigration/liquibase", "changelog.xml.flt");
            generator(cfg, dbMigrationPath + "\\liquibase\\database", "DBMigration/liquibase/database", "20180322144001_create_carrot.sql.flt");

            // Service
            String ServicePath = projectPath + "\\" + projectName + "Service";
            generator(cfg, ServicePath, "Service", "build.gradle.flt");
            // main
            String packagePath = ServicePath + "\\src\\main\\java";
            for(String item : pkg.split("\\.")) {
                packagePath += "\\" + item;
            }
            String serviceTemplatePath = "Service/src/main/java/com/victor/wang/service";
            generator(cfg, packagePath, serviceTemplatePath, "AppConfig.java.flt");
            generator(cfg, packagePath, serviceTemplatePath, "Application.java.flt");
            generator(cfg, packagePath, serviceTemplatePath, "JerseyConfig.java.flt");

            // main - dao
            String daoPath = packagePath + "\\dao";
            String daoTemplatePath = serviceTemplatePath + "/dao";
            generator(cfg, daoPath, daoTemplatePath, "CarrotDao.java.flt");
            generator(cfg, daoPath, daoTemplatePath, "GenericDao.java.flt");
            generator(cfg, daoPath, daoTemplatePath, "QueryBuilder.java.flt");

            // main - exception
            String exceptionPath = packagePath + "\\exception";
            String exceptionTemplatePath = serviceTemplatePath + "/exception";
            generator(cfg, exceptionPath, exceptionTemplatePath, "CarrotNotFoundException.java.flt");

            // main - exception - base
            String exceptionBasePath = exceptionPath + "\\base";
            String exceptionBaseTemplatePath = exceptionTemplatePath + "/base";
            generator(cfg, exceptionBasePath, exceptionBaseTemplatePath, "ApplicationException.java.flt");
            generator(cfg, exceptionBasePath, exceptionBaseTemplatePath, "BadRequestException.java.flt");
            generator(cfg, exceptionBasePath, exceptionBaseTemplatePath, "BaseException.java.flt");
            generator(cfg, exceptionBasePath, exceptionBaseTemplatePath, "ConflictException.java.flt");
            generator(cfg, exceptionBasePath, exceptionBaseTemplatePath, "DatabaseOperationConflictException.java.flt");
            generator(cfg, exceptionBasePath, exceptionBaseTemplatePath, "FieldValidationFailure.java.flt");
            generator(cfg, exceptionBasePath, exceptionBaseTemplatePath, "HttpStatusCodeConstants.java.flt");
            generator(cfg, exceptionBasePath, exceptionBaseTemplatePath, "NotFoundException.java.flt");
            generator(cfg, exceptionBasePath, exceptionBaseTemplatePath, "NumericErrorCodes.java.flt");
            generator(cfg, exceptionBasePath, exceptionBaseTemplatePath, "ValidationException.java.flt");

            // main - manager
            String managerPath = packagePath + "\\manager";
            String managerTemplatePath = serviceTemplatePath + "/manager";
            generator(cfg, managerPath, managerTemplatePath, "CarrotManager.java.flt");

            // main - model
            String modelPath = packagePath + "\\model";
            String modelTemplatePath = serviceTemplatePath + "/model";
            generator(cfg, modelPath, modelTemplatePath, "Carrot.java.flt");

            // main - model - base
            String modelBasePath = modelPath + "\\base";
            String modelBaseTemplatePath = modelTemplatePath + "/base";
            generator(cfg, modelBasePath, modelBaseTemplatePath, "AuditedMysqlEntity.java.flt");
            generator(cfg, modelBasePath, modelBaseTemplatePath, "BaseEntity.java.flt");

            // main - resource
            String resourcePath = packagePath + "\\resource";
            String resourceTemplatePath = serviceTemplatePath + "/resource";
            generator(cfg, resourcePath, resourceTemplatePath, "CarrotResource.java.flt");

            // main - resource
            String sharedObjectPath = packagePath + "\\sharedObject";
            String sharedObjectTemplatePath = serviceTemplatePath + "/sharedObject";
            generator(cfg, sharedObjectPath, sharedObjectTemplatePath, "CarrotCreate.java.flt");
            generator(cfg, sharedObjectPath, sharedObjectTemplatePath, "CarrotInfo.java.flt");
            generator(cfg, sharedObjectPath, sharedObjectTemplatePath, "CarrotUpdate.java.flt");
            generator(cfg, sharedObjectPath, sharedObjectTemplatePath, "PaginatedAPIResult.java.flt");
            generator(cfg, sharedObjectPath, sharedObjectTemplatePath, "PaginationContext.java.flt");

            // main - util
            String utilPath = packagePath + "\\util";
            String utilTemplatePath = serviceTemplatePath + "/util";
            generator(cfg, utilPath, utilTemplatePath, "UniqueString.java.flt");

            // main - util - dao
            String utilDaoPath = utilPath + "\\dao";
            String utilDaoTemplatePath = utilTemplatePath + "/dao";
            generator(cfg, utilDaoPath, utilDaoTemplatePath, "DaoHelper.java.flt");
            generator(cfg, utilDaoPath, utilDaoTemplatePath, "UniqueString.java.flt");

            // main - util - exception
            String utilExceptionPath = utilPath + "\\exception";
            String utilExceptionTemplatePath = utilTemplatePath + "/exception";
            generator(cfg, utilExceptionPath, utilExceptionTemplatePath, "ApplicationExceptionMapper.java.flt");
            generator(cfg, utilExceptionPath, utilExceptionTemplatePath, "ErrorEntity.java.flt");
            generator(cfg, utilExceptionPath, utilExceptionTemplatePath, "ExceptionMapperUtils.java.flt");

            // main - util - exception - oval
            String utilExceptionOvalPath = utilExceptionPath + "\\oval";
            String utilExceptionOvalTemplatePath = utilExceptionTemplatePath + "/oval";
            generator(cfg, utilExceptionOvalPath, utilExceptionOvalTemplatePath, "BaseEntityValidatorInterceptor.java.flt");
            generator(cfg, utilExceptionOvalPath, utilExceptionOvalTemplatePath, "StandardExceptionTranslator.java.flt");
            generator(cfg, utilExceptionOvalPath, utilExceptionOvalTemplatePath, "ValidatorHolder.java.flt");

            // main - util - exception - oval - annotations
            String utilExceptionOvalAnnotationsPath = utilExceptionOvalPath + "\\annotations";
            String utilExceptionOvalAnnotationsTemplatePath = utilExceptionOvalTemplatePath + "/annotations";
            generator(cfg, utilExceptionOvalAnnotationsPath, utilExceptionOvalAnnotationsTemplatePath, "ParamName.java.flt");
            generator(cfg, utilExceptionOvalAnnotationsPath, utilExceptionOvalAnnotationsTemplatePath, "ParamNameParameterNameResolver.java.flt");

            // main - util - mapper
            String utilMapperPath = utilPath + "\\mapper";
            String utilMapperTemplatePath = utilTemplatePath + "/mapper";
            generator(cfg, utilMapperPath, utilMapperTemplatePath, "MapperFacadeFactoryBean.java.flt");
            generator(cfg, utilMapperPath, utilMapperTemplatePath, "MappingConfigurer.java.flt");


            // resources
            String resourcesPath = ServicePath + "\\src\\main\\resources";
            String resourcesTemplatePath = "Service/src/main/resources";
            generator(cfg, resourcesPath, resourcesTemplatePath, "application.yaml.flt");

            //resources - mapper
            String resourcesMapperPath = resourcesPath + "\\mapper";
            String resourcesMapperTemplatePath = resourcesTemplatePath + "/mapper";
            generator(cfg, resourcesMapperPath, resourcesMapperTemplatePath, "Carrot.xml.flt");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param cfg
     * @param dirPath
     * @param templatePath
     * @param templateName the template name, like a.flt
     * @throws IOException
     */
    public static void generator(Configuration cfg, String dirPath, String templatePath, String templateName) throws IOException, TemplateException {
        String fileName = templateName.replace(".flt", "");
        Template template = cfg.getTemplate("templates/" + templatePath + "/" + templateName);

        File projectDir = new File(dirPath);
        mkdirs(projectDir);

        Path path = Paths.get(projectDir.getPath() + "\\" + fileName);
        Writer writer = Files.newBufferedWriter(path, Charset.defaultCharset(),
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

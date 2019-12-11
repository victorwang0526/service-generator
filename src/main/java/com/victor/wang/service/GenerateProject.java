package com.victor.wang.service;

import freemarker.template.*;

import static com.victor.wang.service.Config.*;
import static com.victor.wang.service.util.GeneratorUtil.generateForProject;

public class GenerateProject {

    public static void main(String[] args) {

        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

            cfg.setClassForTemplateLoading(GenerateProject.class, "/");
            // Set the preferred charset template files are stored in. UTF-8 is
            // a good choice in most applications:
            cfg.setDefaultEncoding("UTF-8");

            // project file
            generateForProject(cfg, projectPath, "", "build.gradle.flt");
            // generateForProject(cfg, DirPath, "codestyle.jar");
            generateForProject(cfg, projectPath, "", "README.md.flt");
            generateForProject(cfg, projectPath, "", "settings.gradle.flt");

            // DBMigration
            String dbMigrationPath = projectPath + "\\DBMigration";
            generateForProject(cfg, dbMigrationPath, "DBMigration", "build.gradle.flt");
            generateForProject(cfg, dbMigrationPath + "\\liquibase", "DBMigration/liquibase", "changelog.xml.flt");
            generateForProject(cfg, dbMigrationPath + "\\liquibase\\database", "DBMigration/liquibase/database", "2020_01_01_create_user_and_token.sql.flt");

            // Service
            String ServicePath = projectPath + "\\" + ProjectName + "Service";
            generateForProject(cfg, ServicePath, "Service", "build.gradle.flt");
            // main
            String packagePath = ServicePath + "\\src\\main\\java";
            for(String item : pkg.split("\\.")) {
                packagePath += "\\" + item;
            }
            String serviceTemplatePath = "Service/src/main/java/com/victor/wang/service";
            generateForProject(cfg, packagePath, serviceTemplatePath, "AppConfig.java.flt");
            generateForProject(cfg, packagePath, serviceTemplatePath, "Application.java.flt");
            generateForProject(cfg, packagePath, serviceTemplatePath, "JerseyConfig.java.flt");

            // main - dao
            String daoPath = packagePath + "\\dao";
            String daoTemplatePath = serviceTemplatePath + "/dao";
            generateForProject(cfg, daoPath, daoTemplatePath, "CarrotDao.java.flt");
            generateForProject(cfg, daoPath, daoTemplatePath, "GenericDao.java.flt");
            generateForProject(cfg, daoPath, daoTemplatePath, "QueryBuilder.java.flt");
            generateForProject(cfg, daoPath, daoTemplatePath, "TokenDao.java.flt");
            generateForProject(cfg, daoPath, daoTemplatePath, "UserDao.java.flt");

            // main - exception
            String exceptionPath = packagePath + "\\exception";
            String exceptionTemplatePath = serviceTemplatePath + "/exception";
            generateForProject(cfg, exceptionPath, exceptionTemplatePath, "CarrotNotFoundException.java.flt");
            generateForProject(cfg, exceptionPath, exceptionTemplatePath, "UserNotFoundException.java.flt");
            generateForProject(cfg, exceptionPath, exceptionTemplatePath, "TokenNotFoundException.java.flt");

            // main - exception - base
            String exceptionBasePath = exceptionPath + "\\base";
            String exceptionBaseTemplatePath = exceptionTemplatePath + "/base";
            generateForProject(cfg, exceptionBasePath, exceptionBaseTemplatePath, "ApplicationException.java.flt");
            generateForProject(cfg, exceptionBasePath, exceptionBaseTemplatePath, "BadRequestException.java.flt");
            generateForProject(cfg, exceptionBasePath, exceptionBaseTemplatePath, "BaseException.java.flt");
            generateForProject(cfg, exceptionBasePath, exceptionBaseTemplatePath, "ConflictException.java.flt");
            generateForProject(cfg, exceptionBasePath, exceptionBaseTemplatePath, "DatabaseOperationConflictException.java.flt");
            generateForProject(cfg, exceptionBasePath, exceptionBaseTemplatePath, "FieldValidationFailure.java.flt");
            generateForProject(cfg, exceptionBasePath, exceptionBaseTemplatePath, "HttpStatusCodeConstants.java.flt");
            generateForProject(cfg, exceptionBasePath, exceptionBaseTemplatePath, "NotFoundException.java.flt");
            generateForProject(cfg, exceptionBasePath, exceptionBaseTemplatePath, "NumericErrorCodes.java.flt");
            generateForProject(cfg, exceptionBasePath, exceptionBaseTemplatePath, "ValidationException.java.flt");

            // main - filter
            String filterPath = packagePath + "\\filter";
            String filterTemplatePath = serviceTemplatePath + "/filter";
            generateForProject(cfg, filterPath, filterTemplatePath, "AuthorizationInterceptor.java.flt");
            generateForProject(cfg, filterPath, filterTemplatePath, "AuthorizationManager.java.flt");
            generateForProject(cfg, filterPath, filterTemplatePath, "CORSResponseFilter.java.flt");

            // main - manager
            String managerPath = packagePath + "\\manager";
            String managerTemplatePath = serviceTemplatePath + "/manager";
            generateForProject(cfg, managerPath, managerTemplatePath, "CarrotManager.java.flt");
            generateForProject(cfg, managerPath, managerTemplatePath, "TokenManager.java.flt");
            generateForProject(cfg, managerPath, managerTemplatePath, "UserManager.java.flt");

            // main - model
            String modelPath = packagePath + "\\model";
            String modelTemplatePath = serviceTemplatePath + "/model";
            generateForProject(cfg, modelPath, modelTemplatePath, "Carrot.java.flt");
            generateForProject(cfg, modelPath, modelTemplatePath, "Token.java.flt");
            generateForProject(cfg, modelPath, modelTemplatePath, "User.java.flt");

            // main - model - base
            String modelBasePath = modelPath + "\\base";
            String modelBaseTemplatePath = modelTemplatePath + "/base";
            generateForProject(cfg, modelBasePath, modelBaseTemplatePath, "AuditedMysqlEntity.java.flt");
            generateForProject(cfg, modelBasePath, modelBaseTemplatePath, "BaseEntity.java.flt");

            // main - resource
            String resourcePath = packagePath + "\\resource";
            String resourceTemplatePath = serviceTemplatePath + "/resource";
            generateForProject(cfg, resourcePath, resourceTemplatePath, "CarrotResource.java.flt");
            generateForProject(cfg, resourcePath, resourceTemplatePath, "UserResource.java.flt");

            // main - resource
            String sharedObjectPath = packagePath + "\\sharedObject";
            String sharedObjectTemplatePath = serviceTemplatePath + "/sharedObject";
            generateForProject(cfg, sharedObjectPath, sharedObjectTemplatePath, "CarrotCreate.java.flt");
            generateForProject(cfg, sharedObjectPath, sharedObjectTemplatePath, "CarrotInfo.java.flt");
            generateForProject(cfg, sharedObjectPath, sharedObjectTemplatePath, "CarrotUpdate.java.flt");
            generateForProject(cfg, sharedObjectPath, sharedObjectTemplatePath, "PaginatedAPIResult.java.flt");
            generateForProject(cfg, sharedObjectPath, sharedObjectTemplatePath, "PaginationContext.java.flt");
            generateForProject(cfg, sharedObjectPath, sharedObjectTemplatePath, "UserCreate.java.flt");
            generateForProject(cfg, sharedObjectPath, sharedObjectTemplatePath, "UserInfo.java.flt");
            generateForProject(cfg, sharedObjectPath, sharedObjectTemplatePath, "UserToken.java.flt");
            generateForProject(cfg, sharedObjectPath, sharedObjectTemplatePath, "UserUpdate.java.flt");

            // main - util
            String utilPath = packagePath + "\\util";
            String utilTemplatePath = serviceTemplatePath + "/util";
            generateForProject(cfg, utilPath, utilTemplatePath, "UniqueString.java.flt");
            generateForProject(cfg, utilPath, utilTemplatePath, "MD5Util.java.flt");

            // main - util - dao
            String utilDaoPath = utilPath + "\\dao";
            String utilDaoTemplatePath = utilTemplatePath + "/dao";
            generateForProject(cfg, utilDaoPath, utilDaoTemplatePath, "DaoHelper.java.flt");
            generateForProject(cfg, utilDaoPath, utilDaoTemplatePath, "UniqueString.java.flt");

            // main - util - exception
            String utilExceptionPath = utilPath + "\\exception";
            String utilExceptionTemplatePath = utilTemplatePath + "/exception";
            generateForProject(cfg, utilExceptionPath, utilExceptionTemplatePath, "ApplicationExceptionMapper.java.flt");
            generateForProject(cfg, utilExceptionPath, utilExceptionTemplatePath, "ErrorEntity.java.flt");
            generateForProject(cfg, utilExceptionPath, utilExceptionTemplatePath, "ExceptionMapperUtils.java.flt");

            // main - util - exception - oval
            String utilExceptionOvalPath = utilExceptionPath + "\\oval";
            String utilExceptionOvalTemplatePath = utilExceptionTemplatePath + "/oval";
            generateForProject(cfg, utilExceptionOvalPath, utilExceptionOvalTemplatePath, "BaseEntityValidatorInterceptor.java.flt");
            generateForProject(cfg, utilExceptionOvalPath, utilExceptionOvalTemplatePath, "StandardExceptionTranslator.java.flt");
            generateForProject(cfg, utilExceptionOvalPath, utilExceptionOvalTemplatePath, "ValidatorHolder.java.flt");

            // main - util - exception - oval - annotations
            String utilExceptionOvalAnnotationsPath = utilExceptionOvalPath + "\\annotations";
            String utilExceptionOvalAnnotationsTemplatePath = utilExceptionOvalTemplatePath + "/annotations";
            generateForProject(cfg, utilExceptionOvalAnnotationsPath, utilExceptionOvalAnnotationsTemplatePath, "ParamName.java.flt");
            generateForProject(cfg, utilExceptionOvalAnnotationsPath, utilExceptionOvalAnnotationsTemplatePath, "ParamNameParameterNameResolver.java.flt");

            // main - util - mapper
            String utilMapperPath = utilPath + "\\mapper";
            String utilMapperTemplatePath = utilTemplatePath + "/mapper";
            generateForProject(cfg, utilMapperPath, utilMapperTemplatePath, "MapperFacadeFactoryBean.java.flt");
            generateForProject(cfg, utilMapperPath, utilMapperTemplatePath, "MappingConfigurer.java.flt");


            // resources
            String resourcesPath = ServicePath + "\\src\\main\\resources";
            String resourcesTemplatePath = "Service/src/main/resources";
            generateForProject(cfg, resourcesPath, resourcesTemplatePath, "application.yaml.flt");
            generateForProject(cfg, resourcesPath, resourcesTemplatePath, "logback.xml.flt");

            //resources - mapper
            String resourcesMapperPath = resourcesPath + "\\mapper";
            String resourcesMapperTemplatePath = resourcesTemplatePath + "/mapper";
            generateForProject(cfg, resourcesMapperPath, resourcesMapperTemplatePath, "Carrot.xml.flt");
            generateForProject(cfg, resourcesMapperPath, resourcesMapperTemplatePath, "Token.xml.flt");
            generateForProject(cfg, resourcesMapperPath, resourcesMapperTemplatePath, "User.xml.flt");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

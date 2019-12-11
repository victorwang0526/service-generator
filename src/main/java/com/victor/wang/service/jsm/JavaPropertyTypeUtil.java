package com.victor.wang.service.jsm;

public class JavaPropertyTypeUtil {

    /**
     * get java property type from db column data type
     * @param dataType
     * @return
     */
    public static String getPropertyType(String dataType) {

        String rtn = "String";
        switch (dataType.toLowerCase()) {
            case "char":
            case "varchar":
            case "tinytext":
            case "text":
            case "mediumtext":
            case "longtext":
            case "json":
                rtn = "String";
                break;
            case "tinyint":
            case "smallint":
            case "mediumint":
            case "int":
            case "bigint":
                rtn = "int";
                break;
            case "decimal":
                rtn = "Decimal";
                break;
            case "float":
                rtn = "float";
                break;
            case "double":
                rtn = "double";
                break;


            case "date":
            case "datetime":
            case "timestamp":
            case "time":
            case "year":
                rtn = "Date";
                break;
            default:
                rtn = "String";
        }
        return rtn;
    }
}

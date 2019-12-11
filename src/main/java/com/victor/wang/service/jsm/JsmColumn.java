package com.victor.wang.service.jsm;

import java.util.Arrays;
import java.util.stream.Collectors;

public class JsmColumn
{
	private String tableName;
	/**
	 * the column name should be like 'column_name_1'
	 */
	private String columnName;

	/**
	 * the property name generate by column name, will be columnName1
	 */
	private String propertyName;

	/**
	 * the Upper Case property name, will be ColumnName1
	 */
	private String propertyNameU;

	/**
	 * column db data type
	 */
	private String dataType;

	private String javaType;
	private String comments;

	public JsmColumn(String tableName, String columnName, String dataType, String comments){
		this.tableName = tableName;
		this.columnName = columnName;

		this.propertyNameU = Arrays.stream(columnName.split("_")).map(col -> {
			return col.substring(0,1).toUpperCase() + col.substring(1).toLowerCase();
		}).collect(Collectors.joining(""));

		this.propertyName = this.propertyNameU.substring(0, 1).toLowerCase() + this.propertyNameU.substring(1);

		this.dataType = dataType;
		this.javaType = JavaPropertyTypeUtil.getPropertyType(dataType);

		this.comments = comments;
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}

	public String getComments()
	{
		return comments;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyNameU() {
		return propertyNameU;
	}

	public void setPropertyNameU(String propertyNameU) {
		this.propertyNameU = propertyNameU;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getJavaTypeInfo() {
		if(this.javaType.equals("Date")) {
			return "DateTime";
		}else {
			return this.javaType;
		}
	}
}

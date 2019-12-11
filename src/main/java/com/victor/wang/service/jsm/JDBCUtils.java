package com.victor.wang.service.jsm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtils
{
	public static List<JsmColumn> getJsmColumn(String tableName){
		Connection connection= getConnection();
		Statement stm=null;
		ResultSet rs=null;
		List<JsmColumn> jsmColumns = new ArrayList<>();
		try {
			stm= connection.createStatement();
			String sql="select " +
					" TABLE_NAME AS TABLE_NAME, " +
					" COLUMN_NAME AS COLUMN_NAME, " +
					" DATA_TYPE AS DATA_TYPE, " +
					" COLUMN_COMMENT AS COMMENTS " +
					" FROM INFORMATION_SCHEMA.COLUMNS " +
					" WHERE TABLE_NAME = '"+tableName.toLowerCase()+"'" +
					" ORDER BY ORDINAL_POSITION";
			rs=stm.executeQuery(sql);
			while(rs.next()){
				jsmColumns.add(new JsmColumn(rs.getString(1),
						rs.getString(2), rs.getString(3),rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			release(connection, stm, rs);
		}
		return jsmColumns;
	}
	public static Connection getConnection(){
		Connection connection=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://139.224.113.208:3306/carrot?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
			String username="carrot";
			String password="carrot";
			connection= DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void release(Connection con, Statement statement, ResultSet rs){
		if(null!=rs){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs=null;
			}
			if(null!=statement){
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					statement=null;
				}
			}
			if(null!=con){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					con=null;
				}
			}
		}
	}
}

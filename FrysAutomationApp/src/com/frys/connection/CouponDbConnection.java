package com.frys.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.frys.service.MainConstants;

public class CouponDbConnection {

	private Connection connection = null;
	private Connection pervConnection = null;

	/**
	 * 
	 * @return the connection
	 */
	public Connection getBv1Connection(String driver,String url,String username,String password) {
		System.out.println("CouponDbConnection.getConnection(): GETTING ORACLE BV1 CONNECTION");
		if (connection == null) {
			System.out.println("In getConnection(): We are making the connection here");
			try {
				Class.forName(driver);
				System.out.println("Driver loaded" + driver);
				connection = DriverManager.getConnection(url, username,password);
				System.out.println("CouponDbConnection.getConnection(): SUCCESSFULLY GOT ORACLE BV1 CONNECTION");
				
			} catch (Exception exp) {
				System.out.println("CouponDbConnection.getConnection............................()"+ exp);
				return null;
			}
		}
		return connection;
	}
	/**
	 * 
	 * @return the connection
	 */
	public Connection getPervasiveConnection(String driver,String url,String username,String password) {
		System.out.println("CouponDbConnection.getPervasiveConnection(): need to get the connection");
		if (pervConnection == null) {
			System.out.println("In getPervasiveConnection(): We are making the connection here");
			try {
				Class.forName(driver);
				System.out.println("Driver loaded" + driver);
				pervConnection = DriverManager.getConnection(url, username,password);
			} catch (Exception exp) {
				System.out.println("CouponDbConnection.getPervasiveConnection............................()"+ exp);
				return null;
			}
		}
		return pervConnection;
	}
	public static void main(String[] args) {
		CouponDbConnection db = new CouponDbConnection();
		db.getPervasiveConnection(MainConstants.PERV_DRIVER	, MainConstants.PERV_URL, MainConstants.PERV_USERNAME, MainConstants.PERV_PASSWORD);
	}
	/**
	 * release the resources 
	 * @param connection
	 * @param rs
	 * @param stmt
	 */
	public static void releaseResources(Connection connection, ResultSet rs,PreparedStatement stmt) {
		try {
			if (rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception exp) {
			System.out.println("CouponDbConnection.releaseResources()" + exp);
		}
	}
}

package com.sdet.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * This class provides utility methods for establishing and releasing database
 * connection. It provides methods to release various resources used in database
 * connection.
 */
public class DBUtil {

	/**
	 * 
	 * @return database connection
	 * @throws SQLException 
	 * @throws DBUtilityException
	 *             This method is meant to establish database connection, and
	 *             returns the Connection object after successfully establishing
	 *             the connection.
	 */
	public DBUtil(){}
	public static Connection getConnection() throws SQLException 
	{
	//	Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","Welcome123");

		
		/*
		 * Code to specify driver and establish database connection, return the
		 * instance of java.sql.Connection class on establishing the connection
		 */

		return connection;
	}

	/**
	 * 
	 * @param con
	 * @throws SQLException 
	 * @throws DBUtilityException
	 *             This method is meant to release the instance of Connection
	 *             class.
	 */
	public static void releaseResource(Connection conn) throws SQLException
	{
		/*
		 * Code to release resource
		 */
		conn.close();

	}

}

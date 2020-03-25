/**
 * 
 *
 */
package com.connectionpool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author PhucTV7
 *
 */
public class TestConnectionPool {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		getCon();
	}

	/**
	 * @return
	 * @throws IOException
	 */
	public static Connection getCon() throws IOException {
		Connection connection = null;
		ConnectionPool connectionPool;

		Properties properties = new Properties();
		properties.load(TestConnectionPool.class.getResourceAsStream("/dbCOnfig.properties"));

		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");

		try {
			connectionPool = BasicConnectionPool.create(url, user, password);
			connection = connectionPool.getConnection();
			System.out.println("Success!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}

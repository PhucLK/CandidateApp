package com.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author PhucTV7
 * 
 */
public class JdbcConection {

	private static JdbcConection instance;
	private Connection connection;

	private JdbcConection() {
		Properties properties = new Properties();

		try {
			properties.load(JdbcConection.class.getResourceAsStream("/dbCOnfig.properties"));

			String driver = properties.getProperty("driver");
			String url = properties.getProperty("url");
			String userName = properties.getProperty("user");
			String password = properties.getProperty("password");

			Class.forName(driver);

			connection = DriverManager.getConnection(url, userName, password);

		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the connection from the instance
	 * 
	 * @return {@link Connection}
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Create new instance which connects with the database.
	 * 
	 * @return DatabaseConnection
	 * @throws SQLException if connection false.
	 */
	public static JdbcConection getInstance() throws SQLException {
		if (instance == null || instance.getConnection().isClosed()) {
			instance = new JdbcConection();
		}
		return instance;
	}

}

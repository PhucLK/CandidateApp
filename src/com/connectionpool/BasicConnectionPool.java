/**
 * 
 *
 */
package com.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author PhucTV7
 *
 */
public class BasicConnectionPool implements ConnectionPool {

	private String url;
	private String user;
	private String password;
	private List<Connection> connectionPool;
	private List<Connection> usedConnections = new ArrayList<>();
	private static int INITIAL_POOL_SIZE = 10;

	// standard constructors

	/**
	 * 
	 */
	public BasicConnectionPool() {
		super();
	}

	/**
	 * @param url
	 * @param user
	 * @param password
	 * @param connectionPool
	 * @param usedConnections
	 */
	public BasicConnectionPool(String url, String user, String password, List<Connection> connectionPool) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
		this.connectionPool = connectionPool;
	}

	/**
	 * @param url
	 * @param user
	 * @param password
	 * @return new BasicConnectionPool
	 * @throws SQLException
	 */
	public static BasicConnectionPool create(String url, String user, String password) throws SQLException {

		List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
		for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
			pool.add(createConnection(url, user, password));
		}
		return new BasicConnectionPool(url, user, password, pool);
	}

	@Override
	public Connection getConnection() {
		Connection connection = connectionPool.remove(connectionPool.size() - 1);
		usedConnections.add(connection);
		return connection;
	}

	@Override
	public boolean releaseConnection(Connection connection) {
		connectionPool.add(connection);
		return usedConnections.remove(connection);
	}

	private static Connection createConnection(String url, String user, String password) throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * @return size of connection pool
	 */
	public int getSize() {
		return connectionPool.size() + usedConnections.size();
	}

	/**
	 * @return the connectionPool
	 */
	public List<Connection> getConnectionPool() {
		return connectionPool;
	}

	/**
	 * @param connectionPool the connectionPool to set
	 */
	public void setConnectionPool(List<Connection> connectionPool) {
		this.connectionPool = connectionPool;
	}

	/**
	 * @return the usedConnections
	 */
	public List<Connection> getUsedConnections() {
		return usedConnections;
	}

	/**
	 * @param usedConnections the usedConnections to set
	 */
	public void setUsedConnections(List<Connection> usedConnections) {
		this.usedConnections = usedConnections;
	}

	/**
	 * @return the iNITIAL_POOL_SIZE
	 */
	public static int getINITIAL_POOL_SIZE() {
		return INITIAL_POOL_SIZE;
	}

	/**
	 * @param iNITIAL_POOL_SIZE the iNITIAL_POOL_SIZE to set
	 */
	public static void setINITIAL_POOL_SIZE(int iNITIAL_POOL_SIZE) {
		INITIAL_POOL_SIZE = iNITIAL_POOL_SIZE;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return url;
	}

	@Override
	public String getUser() {
		// TODO Auto-generated method stub
		return user;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

}



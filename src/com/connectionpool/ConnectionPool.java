/**
 * 
 *
 */
package com.connectionpool;

import java.sql.Connection;

/**
 * @author PhucTV7
 *
 */
public interface ConnectionPool {
	
	Connection getConnection();

	boolean releaseConnection(Connection connection);

	String getUrl();

	String getUser();

	String getPassword();
}

package com.micmiu.tutorial.jdbc.demo1;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 8/12/2015
 * Time: 10:50
 */
public interface ConnectionPool {

	/**
	 * 设置数据库连接池的最大连接存放数目
	 *
	 * @param numConnections 连接存放数目
	 */
	public void setMaxConns(int numConnections);

	/**
	 * 设定打开或者关闭连接池
	 *
	 * @param onOrOff
	 * @throws Exception
	 */
	public void setConnWitch(String onOrOff) throws Exception;

	/**
	 * 设定连接
	 *
	 * @throws SQLException
	 */
	public void initConnPool() throws SQLException;

	/**
	 * 从连接池中获取连接
	 *
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException;

	/**
	 * 将连接返回连接池
	 *
	 * @throws SQLException
	 */
	public void returnConnection() throws SQLException;

	/**
	 * 销毁连接池
	 *
	 * @throws SQLException
	 */
	public void destroyConnPool() throws SQLException;
}



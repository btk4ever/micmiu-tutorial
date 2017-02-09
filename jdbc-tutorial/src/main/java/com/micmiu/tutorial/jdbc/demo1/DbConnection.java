package com.micmiu.tutorial.jdbc.demo1;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 8/12/2015
 * Time: 10:57
 */
public interface DbConnection {

	/**
	 * 获取数据库连接
	 *
	 * @param obj
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Connection getConnection(Object obj) throws SQLException, ClassNotFoundException;

	/**
	 * 进行事务处理
	 *
	 * @param obj
	 * @throws SQLException
	 */
	public void beginTransaction(Object obj) throws SQLException;

	/**
	 * 提交数据操作
	 *
	 * @param obj
	 * @throws SQLException
	 */
	public void commit(Object obj) throws SQLException;

	/**
	 * 回复数据操作
	 *
	 * @param obj
	 * @throws SQLException
	 */
	public void rollback(Object obj) throws SQLException;

	/**
	 * 关闭数据库连接
	 *
	 * @param obj
	 * @throws SQLException
	 */
	public void close(Object obj) throws SQLException;
}

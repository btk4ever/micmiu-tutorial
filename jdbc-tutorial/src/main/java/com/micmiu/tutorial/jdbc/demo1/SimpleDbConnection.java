package com.micmiu.tutorial.jdbc.demo1;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 8/12/2015
 * Time: 10:59
 */
public class SimpleDbConnection implements DbConnection {

	// String DB_DRIVER = "com.mysql.jdbc.Driver";
	//
	// String DB_User = "root";
	//
	// String DB_PASSWD = "";
	//
	// String DATASRC_URL = "jdbc:mysql://localhost:3306/myApp";

	private Connection conn;

	private Object objId;

	private boolean isClosed = false;

	/**
	 * 默认构造函数
	 */
	public SimpleDbConnection(Object obj) {
		this.objId = obj;
	}

	/**
	 * 进行事务操作
	 */
	public void beginTransaction(Object obj) throws SQLException {
		try {
			if (this.objId == obj && this.conn != null && !this.conn.isClosed()) {
				this.conn.setAutoCommit(false);
			}
		} catch (SQLException e) {
			throw new SQLException("beginTransaction 连接失败 "
					+ obj.getClass().getName() + e);
		}

	}

	/**
	 * 关闭数据库连接
	 */
	public void close(Object obj) throws SQLException {
		try {
			if (this.objId == obj && this.conn != null && !this.conn.isClosed()) {
				// this.conn.close();
				DbConnectionManager.returnConnection("mysql", conn);
				isClosed = true;
			}
		} catch (SQLException ex) {
			throw new SQLException("关闭数据库连接失败 " + obj.getClass().getName() + ex);
		}

	}

	/**
	 * 提交数据操作
	 */
	public void commit(Object obj) throws SQLException {
		try {
			if (this.objId == obj && this.conn != null && !this.conn.isClosed()) {
				this.conn.commit();
			}
		} catch (SQLException ex) {
			throw new SQLException("提交数据操作失败 " + obj.getClass().getName() + ex);
		}
	}

	/**
	 * 用来获取数据库连接
	 */
	public Connection getConnection(Object obj) throws SQLException,
			ClassNotFoundException {
		if (this.objId == obj && this.conn != null && !this.isClosed()) {
			return conn;
		} else {
			// Class.forName(DB_DRIVER);
			// conn =
			// DriverManager.getConnection(DATASRC_URL,DB_User,DB_PASSWD);
			conn = DbConnectionManager.getConnection("mysql");

		}
		return conn;
	}

	/**
	 * 回复数据操作
	 */
	public void rollback(Object obj) throws SQLException {
		try {
			if (this.objId == obj && this.conn != null && !this.conn.isClosed()) {
				this.conn.rollback();
			}
		} catch (SQLException ex) {
			throw new SQLException("rollback连接失败" + obj.getClass().getName()
					+ ex);
		}

	}

	/**
	 *
	 * @return
	 */
	public boolean isClosed() {
		return isClosed;
	}
}

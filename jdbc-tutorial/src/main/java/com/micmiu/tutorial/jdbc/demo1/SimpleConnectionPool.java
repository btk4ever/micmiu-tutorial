package com.micmiu.tutorial.jdbc.demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 8/12/2015
 * Time: 10:52
 */
public class SimpleConnectionPool implements ConnectionPool {

	private String DB_DRIVER = "com.mysql.jdbc.Driver";

	private String DB_User = "root";

	private String DB_PASSWD = "";

	private String DATASRC_URL = "jdbc:mysql://localhost/myApp";

	// 默认的连接大小
	private final int defaultMaxConnections = 10;

	// 存放目前空闲的连接,空闲池
	private List<Connection> freeConnections;

	// 存放正在使用的池,繁忙池
	private Map<Thread, Connection> busyConnections;

	// 设定连接池的大小
	private int maxConnections;

	/**
	 * 构造函数
	 */
	public SimpleConnectionPool() {
		maxConnections = defaultMaxConnections;
		freeConnections = null;
		busyConnections = null;
	}

	/**
	 * 构造函数
	 *
	 * @param numConnections
	 */
	public SimpleConnectionPool(int numConnections) {
		maxConnections = numConnections;
		freeConnections = null;
		busyConnections = null;
	}

	/**
	 * 销毁全部连接
	 */
	public void destroyConnPool() throws SQLException {
		// 假如还有正在使用的连接
		if (busyConnections != null) {
			Set set = busyConnections.entrySet();
			Iterator iterator = set.iterator();
			// 销毁正在使用的连接
			while (iterator.hasNext()) {
				Map.Entry map = (Map.Entry) iterator.next();
				Connection conn = (Connection) map.getValue();
				conn.close();
			}
			busyConnections = null;
		}

		// 假如还有空闲的连接
		if (freeConnections != null) {
			// 销毁空闲的连接
			for (int i = 0; i < freeConnections.size(); i++) {
				Connection conn = (Connection) freeConnections.get(i);
				conn.close();
			}
			freeConnections = null;
		}
	}

	/**
	 * 从连接池中提取一个连接
	 */
	public synchronized Connection getConnection() throws SQLException {
		if (freeConnections == null) {
			throw new SQLException("连接池还没有创建");
		}
		try {
			// 暂时没有空闲的连接,则等待
			if (freeConnections.size() == 0) {
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 从空闲池中获取一个连接
		Connection conn = (Connection) freeConnections.get(0);
		// 在空闲池中销毁已经创建的连接
		freeConnections.remove(0);
		// 将已经获取的连接放到繁忙池之中
		busyConnections.put(Thread.currentThread(), conn);
		return conn;
	}

	/**
	 * 初始化连接池
	 *
	 * @throws SQLException
	 */
	public void initConnPool() throws SQLException {
		try {
			freeConnections = new ArrayList<Connection>(maxConnections);
			busyConnections = new HashMap<Thread, Connection>(maxConnections);
			// 创建连接,并放入数据库连接池中
			Class.forName(DB_DRIVER);
			for (int i = 0; i < maxConnections; i++) {
				freeConnections.add(DriverManager.getConnection(DATASRC_URL,
						DB_User, DB_PASSWD));
			}
		} catch (Exception e) {
			freeConnections = null;
			busyConnections = null;
			throw new SQLException(e.toString());
		}
	}

	/**
	 * 从繁忙池中销毁已经返回的连接,并将它重新放入空闲池中
	 */
	public synchronized void returnConnection() throws SQLException {
		Connection conn = (Connection) busyConnections.get(Thread
				.currentThread());
		if (conn == null)
			throw new SQLException("没有发现繁忙池中有连接");
		busyConnections.remove(Thread.currentThread());
		// 将已经返回的连接重新放入空闲池中
		freeConnections.add(conn);
		// 重新唤起,如果空闲池中已经没有空闲连接
		notify();
	}

	/**
	 * 设定打开或者关闭连接池
	 */
	public void setConnWitch(String onOrOff) throws Exception {
		try {
			// 如果为"ON",则表示初始化连接
			if ("ON".equalsIgnoreCase(onOrOff)) {
				initConnPool();
			} else if ("OFF".equalsIgnoreCase(onOrOff)) {
				// 如果为OFF,则表示销毁连接
				destroyConnPool();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置连接最大数
	 */
	public void setMaxConns(int numConnections) {
		this.maxConnections = numConnections;

	}

	/**
	 * @return busyConnections
	 */
	public Map<Thread, Connection> getBusyConnections() {
		return busyConnections;
	}

	/**
	 * @param busyConnections 要设置的 busyConnections
	 */
	public void setBusyConnections(Map<Thread, Connection> busyConnections) {
		this.busyConnections = busyConnections;
	}

	/**
	 * @return dATASRC_URL
	 */
	public String getDATASRC_URL() {
		return DATASRC_URL;
	}

	/**
	 * @param datasrc_url 要设置的 dATASRC_URL
	 */
	public void setDATASRC_URL(String datasrc_url) {
		DATASRC_URL = datasrc_url;
	}

	/**
	 * @return dB_DRIVER
	 */
	public String getDB_DRIVER() {
		return DB_DRIVER;
	}

	/**
	 * @param db_driver 要设置的 dB_DRIVER
	 */
	public void setDB_DRIVER(String db_driver) {
		DB_DRIVER = db_driver;
	}

	/**
	 * @return dB_PASSWD
	 */
	public String getDB_PASSWD() {
		return DB_PASSWD;
	}

	/**
	 * @param db_passwd 要设置的 dB_PASSWD
	 */
	public void setDB_PASSWD(String db_passwd) {
		DB_PASSWD = db_passwd;
	}

	/**
	 * @return dB_User
	 */
	public String getDB_User() {
		return DB_User;
	}

	/**
	 * @param user 要设置的 dB_User
	 */
	public void setDB_User(String user) {
		DB_User = user;
	}

	/**
	 * @return defaultMaxConnections
	 */
	public int getDefaultMaxConnections() {
		return defaultMaxConnections;
	}

	/**
	 * @return freeConnections
	 */
	public List<Connection> getFreeConnections() {
		return freeConnections;
	}

	/**
	 * @param freeConnections 要设置的 freeConnections
	 */
	public void setFreeConnections(List<Connection> freeConnections) {
		this.freeConnections = freeConnections;
	}

	/**
	 * @return maxConnections
	 */
	public int getMaxConnections() {
		return maxConnections;
	}

	/**
	 * @param maxConnections 要设置的 maxConnections
	 */
	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}
}

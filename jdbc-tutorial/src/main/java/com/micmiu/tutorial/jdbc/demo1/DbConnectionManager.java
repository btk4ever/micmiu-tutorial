package com.micmiu.tutorial.jdbc.demo1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 8/12/2015
 * Time: 11:01
 */
public class DbConnectionManager {

	private static final String CONF_FILE_NAME = "jdbc.properties";
	private static DbConnectionManager instance; //单例模式
	private static Map<String, ConnectionPool> connPool = new HashMap<String, ConnectionPool>();
	private PrintWriter log;

	/**
	 * 返回唯一实例,如果是第一次调用此方法,则创建实例
	 * @return
	 */
	public static synchronized DbConnectionManager getInstance(){
		if(instance == null){
			instance = new DbConnectionManager();
		}
		return instance;
	}

	/**
	 * 私有构造方法,防止其他对象创建本例的实例
	 *
	 */
	private DbConnectionManager(){
		init();
	}

	/**
	 * 初始化工作
	 */
	private void init() {
		InputStream is = getClass().getResourceAsStream(CONF_FILE_NAME);
		Properties jdbcProp = new Properties();
		try {
			jdbcProp.load(is);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("读取属性文件发送一次， " + "请确认当前CLASSPATH目录下存在配置文件: " + CONF_FILE_NAME);
			return;
		}
		String logFile = jdbcProp.getProperty("logfile", "DBConnectionManager.log");
		try {
			log = new PrintWriter(new FileWriter(logFile, true), true);
		} catch (IOException e) {
			System.err.println("无法打开日志文件: " + logFile);
			log = new PrintWriter(System.err);
		}
		//创建连接
		createPools(jdbcProp);
	}

	/**
	 * 创建连接池
	 */
	private void createPools(Properties props) {
		ConnectionPool pool = new SimpleConnectionPool();
		//设定连接池的大小
		pool.setMaxConns(10);
		try{
			//表示创建连接池,并初始化连接池
			pool.setConnWitch("ON");
			//将创建后的连接放到Map中,用mysql表示是用于连接MySQL数据库
			connPool.put("mysql", pool);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 关闭所有连接
	 *
	 */
	public synchronized void release(){
		try{
			Set set = connPool.entrySet();
			Iterator iterator = set.iterator();
			while(iterator.hasNext()){
				Map.Entry map = (Map.Entry) iterator.next();
				ConnectionPool pool = (ConnectionPool) map.getValue();
				//关闭连接池
				pool.setConnWitch("OFF");
			}
			connPool = null;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 将连接返回给连接池
	 * @param name
	 * @param conn
	 */
	public static void returnConnection(String name,Connection conn){
		try{
			ConnectionPool pool = (SimpleConnectionPool)connPool.get(name);
			if(pool != null)
				pool.returnConnection();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 根据名称从Map中得到连接
	 * @param name
	 * @return
	 */
	@SuppressWarnings("finally")
	public static Connection getConnection(String name){
		Connection conn = null;
		try{
			//从连接池中获取连接
			ConnectionPool pool = (SimpleConnectionPool)connPool.get(name);
			if(pool != null)
				conn = pool.getConnection();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return conn;
		}
	}
}

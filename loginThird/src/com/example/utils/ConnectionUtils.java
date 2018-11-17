package com.example.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * ���ӹ�����
 */
public class ConnectionUtils {
	/**
	 * ���ȶ�ȡdb.properties
	 */
	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;
	private static Properties props = new Properties();
	
	/**
	 * ʹ���̳߳أ����̳߳��л�ȡ���ӣ���ֹ�����������
	 */
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	static { 
		try{
			//���������ȡ�ļ�
			InputStream in = 
				ConnectionUtils.class.getClassLoader().getResourceAsStream("db.properties");
			
			props.load(in);
			 
			driver = props.getProperty("jdbc.Driver");
			url = props.getProperty("jdbc.url");
			username = props.getProperty("jdbc.username");
			password = props.getProperty("jdbc.password");
			//�������һ��Ҫ�У�����ᱨdriver���ر���δ������
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡ����
	 */
	public static Connection getConn() throws Exception{
		Connection conn = tl.get();
		if(conn == null){
			conn = DriverManager.getConnection(url,username,password);
			tl.set(conn);
		}
		return conn;
	}
	
	/**
	 * �ر�����
	 */
	public static void closeConn() throws Exception{
		Connection conn = tl.get();
		if(conn != null){
			conn.close();
		}
		tl.set(null);
	}
	
	
	
	
}

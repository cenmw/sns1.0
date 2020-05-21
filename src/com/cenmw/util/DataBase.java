package com.cenmw.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
	private Connection conn;
	private Statement stmt;
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	String url = "jdbc:sqlserver://122.70.156.197:2460;databaseName=100t1";

	String name = "sa";

	String pwd = "!Q@W#E$R";
	private ResultSet rs;

	public DataBase() {
		setDriver(driver);
		setConnection(url, name, pwd);
	}

	private void setDriver(String driver) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void setConnection(String url, String name, String pwd) {
		try {
			conn = DriverManager.getConnection(url, name, pwd);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet findQuery(String sql) {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int sendUpdate(String sql) {
		try {
			stmt = conn.createStatement();
			return stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql==" + sql);
			return -1;
		} finally {
			// close();
		}
	}

	public boolean sendUpdate(String[] sql) {
		boolean t = true;
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			for (int i = 0; i < sql.length; i++) {
				stmt.addBatch(sql[i]);
			}
			stmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
				t = false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return t;
	}

	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws SQLException, IOException {
		
	}
	
}

package server;

import java.sql.DriverManager;
import java.sql.ResultSet;
import com.mysql.jdbc.Statement;

public class DB {
	/*
	 * Connect the database
	 * Use singletone pattern
	 */
	private static DB db;
	final static String ip ="jdbc:mysql://localhost?characterEncoding=utf-8";
	final static String id ="root";
	final static String pw ="12345";
	java.sql.Connection con = null;
	ResultSet rs;
	Statement stmt = null;

	private DB() {
		connectDB();
	}
	private void connectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(ip, id, pw);
			stmt = (Statement) con.createStatement();
			rs = stmt.executeQuery("use mysql");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DB getInstance() {
		if (db == null) {
			synchronized(DB.class) {
				if (db == null) {
					db = new DB();
				}
			}
		}
		return db;
	}
}

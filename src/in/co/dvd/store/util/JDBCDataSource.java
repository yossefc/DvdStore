package in.co.dvd.store.util;

import in.co.dvd.store.exception.ApplicationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCDataSource {

	private JDBCDataSource() {
	}

	private static final String DB_DRIVER_CLASS = "driver";
	private static final String DB_USERNAME = "username";
	private static final String DB_PASSWORD = "password";
	private static final String DB_URL = "url";

	private static JDBCDataSource jdbcDatasourse=null;
	public static Connection connection = null;

	private static JDBCDataSource getInstance() {
		try {
			ResourceBundle properties = ResourceBundle.getBundle("in.co.dvd.store.bundle.database");
			Class.forName(properties.getString(DB_DRIVER_CLASS));
			jdbcDatasourse.connection = DriverManager.getConnection(properties.getString(DB_URL),
					properties.getString(DB_USERNAME), properties.getString(DB_PASSWORD));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jdbcDatasourse; 
	}
	
	public static Connection getConnection() throws Exception {
		return getInstance().connection;
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
			}
		}
	}

	public static void trnRollback(Connection connection) throws ApplicationException {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new ApplicationException(ex.toString());
			}
		}
	}
}

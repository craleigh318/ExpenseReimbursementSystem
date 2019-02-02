package com.revature.craleigh318.ers.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataHandler implements AutoCloseable {
	
	private static final String PROPERTIES_FILENAME = "oracle_database.properties";
	private static final String DB_ENDPOINT = "endpoint";
	private static final String DB_PORT = "port";
	private static final String DB_NAME = "dbName";
	private static final String UNFORMATTED_URL = "jdbc:oracle:thin:@%s:%s:%s";
	
	private static DataHandler dataHandler = null;
	
	public static DataHandler getInstance() {
		if (dataHandler == null) {
			dataHandler = new DataHandler();
		}
		return dataHandler;
	}
	
	private Connection connection = null;
	private InputStream inputStream = null;
	private Properties properties = null;
	private String url = null;
	
	private DataHandler() { }
	
	@Override
	public void close() throws SQLException, IOException {
		closeConnection();
		clearProperties();
		closeInputStream();
		clearStrings();
	}
	
	public Connection getConnection() throws SQLException, IOException {
		if (connection == null) {
			connection = DriverManager.getConnection(getUrl(), getProperties());
		}
		return connection;
	}
	
	private String getUrl() throws IOException {
		if (url == null) {
			Properties prop = getProperties();
			url = String.format(UNFORMATTED_URL, prop.getProperty(DB_ENDPOINT), prop.getProperty(DB_PORT), prop.getProperty(DB_NAME));
		}
		return url;
	}
	
	private Properties getProperties() throws IOException {
		if (properties == null) {
			properties = new Properties();
			properties.load(getInputStream());
		}
		return properties;
	}
	
	private InputStream getInputStream() throws FileNotFoundException {
		if (inputStream == null) {
			inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_FILENAME);
		}
		return inputStream;
	}
	
	private void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}
	
	private void clearProperties() {
		properties = null;
	}
	
	private void clearStrings() {
		url = null;
	}
	
	private void closeInputStream() throws IOException {
		if (inputStream != null) {
			inputStream.close();
		}
	}
}

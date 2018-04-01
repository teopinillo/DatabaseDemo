package me.theofrancisco;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.Statement;

/*
 * Connect the Diver
 */
public class Connector {
	
	Connection connection;
	Statement statement;
	
	static String url, database, username, password, hostname, port, driver;
	
	public Connector (Properties properties, String pass) {
		database = properties.getProperty("database");
		username = properties.getProperty("user");
		password = pass;
		hostname = properties.getProperty("host");
		port = properties.getProperty("port");
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://"+hostname + ":" + port +"/" + database;
		
		
	}
	
	public boolean openConnection () {
		try {
			DriverManager.registerDriver((java.sql.Driver) Class.forName(driver).newInstance());
			connection = DriverManager.getConnection(url,username,password);
			statement = connection.createStatement();
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
			return false;
		}
		if (connection==null) return false;
		
		System.out.println("Connection successful");
		return true;
	}

	public ResultSet executeQuery(String s) throws SQLException {		 
		return statement.executeQuery(s);
	}

	public void executeUpdate(String s) throws SQLException {
		statement.executeUpdate(s);
	}
}

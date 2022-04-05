package net.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//a factory is responsible for generating an object and returning it
//singleton is a design pattern where we only create one instance of a class, 
//saves memory so we're not re-instantiating
//it
public class ConnectionFactory {
	// private - only this class can directly access
	//static - it belongs to the class, rather than a specific instance (singleton design pattern)
	
	private static ConnectionFactory connFactory = null;
	private static Properties properties;
	
	
	private ConnectionFactory() {
		// using the input stream we are using the credentials needed to log-in to our database
		InputStream stream = ConnectionFactory.class.getClassLoader().getResourceAsStream("postgres.properties");
		try {
			properties = new Properties();
			properties.load(stream);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static ConnectionFactory getConnectionFactory() {
		if(connFactory == null) connFactory = new ConnectionFactory();
		return connFactory;
	}
	
	public Connection getConnection() {
		// return our connection to the database
		Connection connection = null;
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		
		// try connecting to our database
		try {
			// get the connection
			connection = DriverManager.getConnection(url, username, password);
			
		}catch(SQLException e){
			
			// if something goes wrong print the stack trace
			e.printStackTrace();
		}
		return connection;
	}

}



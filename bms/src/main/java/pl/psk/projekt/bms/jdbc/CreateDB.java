package pl.psk.projekt.bms.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
	
	String create;
	
	 public CreateDB(Connection connection) throws SQLException {
	        try (Statement statement = connection.createStatement()) {
	            
	            create = 	"CREATE TABLE Workers ("+
	              			"workerId int AUTO_INCREMENT PRIMARY KEY,"+
	              			"accountType varchar(255),"+
	              			"username varchar(255),"+
	              			"password varchar(255),"+
	              			"possition varchar(255),"+
	              			"mobile varchar(255),"+
	              			"address varchar(255),"+
	              			"birthday varchar(255),"+
	              			"salary double );";
	            
	            statement.execute(create);
	            
	            create = 		"CREATE TABLE Bus ("+
	            				"busID int AUTO_INCREMENT PRIMARY KEY,"+
	            				"busName int varchar(255) NOT NULL,"+
	            				"seat int NOT NULL );"; 
	            statement.execute(create);
	            
	            create = 	"CREATE TABLE BusLine ("+
              				"busLineID int AUTO_INCREMENT PRIMARY KEY,"+
              				"busLineName varchar(255),"+
              				"busLineType varchar(255),"+
              				"startStation varchar(255),"+
              				"endStation varchar(255),"+
              				"price int);";
            
	            statement.execute(create);
	            
	            create =    	"CREATE TABLE BusLine ("+
          						"busLineID int AUTO_INCREMENT PRIMARY KEY,"+
          						"busLineName varchar(255),"+
          						"busLineType varchar(255),"+
          						"IdBus int FOREIGN KEY REFERENCES Bus(busID),"+
          						"BusLine int FOREIGN KEY REFERENCES BusLine(busLineID),"+
          						"IdDriver int FOREIGN KEY REFERENCES Workers(workerId));";
	            statement.execute(create);
	            
	        }
	    }
	
}

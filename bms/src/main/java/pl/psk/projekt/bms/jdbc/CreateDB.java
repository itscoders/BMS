package pl.psk.projekt.bms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import pl.psk.projekt.bms.dbobjects.Workers;

public class CreateDB {
	
	
	
	 public CreateDB() throws SQLException {
		 	String create;
	        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "root", "toor");
	        Statement   statement = connect.createStatement();  
	       
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
	            				"busName varchar(255) NOT NULL,"+
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
	            
	            create =    	"CREATE TABLE Scheduler ("+
          						"schedulerID int AUTO_INCREMENT PRIMARY KEY,"+
          						"depertureTime varchar(255),"+
          						"arrivalTime varchar(255),"+
          						"IdBus int,"+
          						"IdBusLine int,"+
          						"IdDriver int,"+
          						"FOREIGN KEY (IdBus) REFERENCES Bus(busID),"+
          						"FOREIGN KEY (IdBusLine) REFERENCES BusLine(busLineID),"+
          						"FOREIGN KEY (IdDriver) REFERENCES Workers(workerId));";
	            statement.execute(create);
	            
	           
	 }
	    
	 
	    public static void main( String[] args ) throws SQLException
	    {
	    	
	    	new CreateDB();
	    	
	    	WorkersJDBC wj = new WorkersJDBC();
	    	
	         Workers w = new Workers("Admin", "admin", "admin", "admin", "admin", "admin", "2136455558588", "Kielce", "25-07-1968", 2563);
	           
	            wj.createWorker(w);
	            
	    }
	
}

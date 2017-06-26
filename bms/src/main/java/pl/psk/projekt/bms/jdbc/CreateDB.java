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
	       
	            create = 	"CREATE TABLE IF NOT EXISTS Workers ("+
	              			"workerId int AUTO_INCREMENT PRIMARY KEY,"+
	              			"accountType varchar(255),"+
	              			"username varchar(255) UNIQUE,"+
	              			"password varchar(255),"+
	              			"name varchar(255),"+
	              			"surname varchar(255),"+
	              			"possition varchar(255),"+
	              			"mobile varchar(255),"+
	              			"address varchar(255),"+
	              			"birthday varchar(255),"+
	              			"salary double );";
	            
	            statement.execute(create);
	       
	            
	 
					
	            
	            create = 		"CREATE TABLE IF NOT EXISTS Bus ("+
	            				"busID int AUTO_INCREMENT PRIMARY KEY,"+
	            				"busName varchar(255) NOT NULL,"+
	            				"seat int NOT NULL );"; 
	            statement.execute(create);
	            
	            create = 	"CREATE TABLE IF NOT EXISTS BusLine ("+
              				"busLineID int AUTO_INCREMENT PRIMARY KEY,"+
              				"busLineName varchar(255),"+
              				"busLineType varchar(255),"+
              				"startStation varchar(255),"+
              				"endStation varchar(255),"+
              				"priceOneWay DECIMAL(5,2),"+
              				"priceMonthly DECIMAL(5,2));";
            
	            statement.execute(create);
	            
	            create =    	"CREATE TABLE IF NOT EXISTS Scheduler ("+
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
	            
	            create =	"CREATE TABLE IF NOT EXISTS Buyer ("+
          					"buyerId int AUTO_INCREMENT PRIMARY KEY,"+
          					"name varchar(255),"+
          					"surname varchar(255),"+
          					"birthday varchar(255),"+
          					"email varchar(255),"+
          					"mobile varchar(255),"+
          					"street varchar(255),"+
          					"houseNumber varchar(255),"+
          					"postCode varchar(255),"+
          					"city varchar(255),"+
          					"insuranceNumber varchar(255));";
	            statement.execute(create);
                
	            
	            create = 	"CREATE TABLE IF NOT EXISTS Transaction ("+
							"transactionId int AUTO_INCREMENT PRIMARY KEY,"+
							"discount varchar(255),"+
							"payment varchar(255),"+
							"date varchar(255),"+
							"IdScheduler int,"+
							"IdSeller int,"+
							"IdBuyer int,"+
							"FOREIGN KEY (IdScheduler) REFERENCES Scheduler(schedulerID),"+ 
							"FOREIGN KEY (IdSeller) REFERENCES Workers(workerId),"+
							"FOREIGN KEY (IdBuyer) REFERENCES Buyer(buyerId));"; 
        statement.execute(create);
	            
					}
	 
	    
		public static void main(String[] args) {
			
			try {
				new CreateDB();
				new InsertDB();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	
}

package pl.psk.projekt.bms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropDB {

	public DropDB() throws SQLException {
	 	String drop;
        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "root", "toor");
        Statement   statement = connect.createStatement();  
       
        	drop = 		"DROP TABLE IF EXISTS Transaction";
            statement.execute(drop);
            
        	drop =   	"DROP TABLE IF EXISTS Scheduler";
        	statement.execute(drop);
        
            drop = 		"DROP TABLE IF EXISTS Workers";
            statement.execute(drop);
            
            drop = 		"DROP TABLE IF EXISTS Bus"; 
            statement.execute(drop);
           
            drop = 		"DROP TABLE IF EXISTS Buyer";
            statement.execute(drop);
            
            
            drop = 		"DROP TABLE IF EXISTS BusLine";
            statement.execute(drop);
                  
            
 }
	
	
	public static void main(String[] args) {
		
		try {
			new DropDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

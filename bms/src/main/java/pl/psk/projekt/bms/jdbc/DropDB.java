package pl.psk.projekt.bms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/** KLASA DropDB zawiera w swoim ciele konstruktor w którego zawartości występuje łączenie do bazy i wykonywanie usunięcia tabel.
 * 
 * @author Paweł Pawelec i Kamil Świąder
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * @see java.sql.Statement
 * @see java.sql.SQLException
 */


public class DropDB {

	public DropDB() throws SQLException {
		/** Zmienna do której zapisywane są polecenia SQL */
	 	String drop;
	 	/** Zmienna przechowująca połączenie z baza danych*/
        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "root", "toor");
        
        /** Zmienna wykorzystująca połączenie z bazą do wykonania zapytania. */
        Statement   statement = connect.createStatement();  
       
        	drop = 		"DROP TABLE IF EXISTS Transaction";
        	/** Wywołanie wysłania wykonania do bazy*/
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
	
}

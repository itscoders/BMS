package pl.psk.projekt.bms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import pl.psk.projekt.bms.dbobjects.Bus;

/** KLASA BusJDBC - Zawiera w swoim ciele metody umożliwiające połączenie oraz dodanie, usunięcie, edytowanie rekordów bazy 
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * @see java.sql.Statement
 * @see java.sql.SQLException
 * @see java.sql.PreparedStatement
*/

public class BusJDBC {
	
	/** Definicja obiektu klasy Statement. */
    Statement statement;
    /** Definicja obiektu klasy PreparedStatement. */
    PreparedStatement  preparedStatement;
    /** Definicja obiektu klasy Connection. */
    Connection connect;
    
    
	/**
	 * Konstruktor bezparametrowy klasy BusJDBC dokonanuje połączenia z bazą
	 * 
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.Statement
	 * @see java.sql.SQLException
	 */
    
    public BusJDBC() throws SQLException{    
        String url = "jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
        String username = "root";
        String password = "toor";
        connect = DriverManager.getConnection(url,username,password);
        statement = connect.createStatement();            
    }
    
    
	/**
	 * Metoda createBus wykorzystywana do dodawania rekordów w tabeli bus
	 * 
	 * @param b obiekt klasy {@link Bus} przechowywujący dane do inserta
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 */
    
    public void createBus(Bus b)throws SQLException{
    String sql="INSERT INTO BUS (busName,seat) values(?,?)";
    preparedStatement=connect.prepareStatement(sql);
    preparedStatement.setString(1,b.getBusName());
    preparedStatement.setInt(2,b.getSeat());
    preparedStatement.executeUpdate();

    }
    
	/**
	 * Metoda deleteBus wykorzystywana do usuwania rekordów w tabeli bus
	 * 
	 * @param b obiekt klasy {@link Bus} przechowywujący dane do delete
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 */
    
    public void deleteBus(Bus b)throws SQLException{
        String sql ="DELETE FROM BUS WHERE busID=?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1,b.getBusID());
        preparedStatement.executeUpdate();
        
    }
    
	/**
	 * Metoda updateBus wykorzystywana do aktualizacji rekordów w tabeli bus
	 * 
	 * @param b obiekt klasy {@link Bus} przechowywujący dane do delete
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 */
    
    public void updateBus(Bus b) throws SQLException{
    String sql="update bus set busName=?, seat=?  where busID=? ";
    preparedStatement=connect.prepareStatement(sql);
    preparedStatement.setString(1,b.getBusName());
    preparedStatement.setInt(2,b.getSeat());
    preparedStatement.setInt(3,b.getBusID());
   
       preparedStatement.executeUpdate();
   
    }
	
	
}



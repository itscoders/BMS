package pl.psk.projekt.bms.jdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import pl.psk.projekt.bms.dbobjects.Bus;
import pl.psk.projekt.bms.dbobjects.BusLine;


/** KLASA BusLineJDBC - Zawiera w swoim ciele metody umożliwiające połączenie oraz dodanie, usunięcie, edytowanie rekordów bazy 
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * @see java.sql.Statement
 * @see java.sql.SQLException
 * @see java.sql.PreparedStatement
*/

public class BusLineJDBC {

	/** Definicja obiektu klasy Statement. */
    Statement statement;
    /** Definicja obiektu klasy PreparedStatement. */
    PreparedStatement  preparedStatement;
    /** Definicja obiektu klasy Connection. */
    Connection connect;
    
    /**
	 * Konstruktor bezparametrowy klasy BusLineJDBC dokonanuje połączenia z bazą
	 * 
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.Statement
	 * @see java.sql.SQLException
	 */
    
    public BusLineJDBC() throws SQLException{    
        String url = "jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
        String username = "root";
        String password = "toor";
        connect = DriverManager.getConnection(url,username,password);
        statement = connect.createStatement();            
    }
    
    
	/**
	 * Metoda createBusLine wykorzystywana do dodawania rekordów w tabeli BusLine
	 * 
	 * @param b obiekt klasy {@link BusLine} przechowywujący dane do inserta
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 */
    
    public void createBusLine(BusLine b)throws SQLException{
    String sql="INSERT INTO BusLine (busLineName, busLineType, startStation, endStation, priceOneWay, priceMonthly) values(?,?,?,?,?,?)";
    preparedStatement=connect.prepareStatement(sql);
    preparedStatement.setString(1,b.getBusLineName());
    preparedStatement.setString(2,b.getBusLineType());
    preparedStatement.setString(3,b.getStartStation());
    preparedStatement.setString(4,b.getEndStation());
    preparedStatement.setDouble(5,b.getPriceOneWay());
    preparedStatement.setDouble(6,b.getPriceMonthly());
    preparedStatement.executeUpdate();

    }
    
	/**
	 * Metoda deleteBusLine wykorzystywana do usuwania rekordów w tabeli BusLine
	 * 
	 * @param b obiekt klasy {@link BusLine} przechowywujący dane do delete
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 */
    
    public void deleteBusLine(BusLine b) throws SQLException{
        String sql ="DELETE FROM BusLine WHERE busLineID=?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1,b.getBusLineID());
        preparedStatement.executeUpdate();
       
    }
    
	/**
	 * Metoda updateBusLine wykorzystywana do aktualizacji rekordów w tabeli BusLine
	 * 
	 * @param b obiekt klasy {@link BusLine} przechowywujący dane do delete
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 */
    
    public void updateBusLine(BusLine b) throws SQLException{
    String sql="update BusLine set busLineName=?, busLineType=?, startStation=?, endStation=?, priceOneWay=?, priceMonthly=? where busLineID=? ";
    preparedStatement=connect.prepareStatement(sql);
    preparedStatement.setString(1,b.getBusLineName());
    preparedStatement.setString(2,b.getBusLineType());
    preparedStatement.setString(3,b.getStartStation());
    preparedStatement.setString(4,b.getEndStation());
    preparedStatement.setDouble(5,b.getPriceOneWay());
    preparedStatement.setDouble(6,b.getPriceMonthly());
    preparedStatement.setInt(7,b.getBusLineID());
   
        preparedStatement.executeUpdate();
      
    }
	
}



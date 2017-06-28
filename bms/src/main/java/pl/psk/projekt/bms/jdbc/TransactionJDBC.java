package pl.psk.projekt.bms.jdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import pl.psk.projekt.bms.dbobjects.Buyer;
import pl.psk.projekt.bms.dbobjects.Scheduler;
import pl.psk.projekt.bms.dbobjects.Transaction;


/** KLASA TransactionJDBC - Zawiera w swoim ciele metody umożliwiające połączenie oraz dodanie, usunięcie, edytowanie rekordów bazy 
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
*/


public class TransactionJDBC {
	
	/** Definicja obiektu klasy Statement. */
	Statement statement;
	/** Definicja obiektu klasy PreparedStatement. */
	PreparedStatement preparedStatement;
	/** Definicja obiektu klasy Connection. */
	Connection connect;
    
	
	/**
	 * Konstruktor bezparametrowy klasy TransactionJDBC dokonanuje połączenia z bazą
	 * 
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.Statement
	 * @see java.sql.SQLException
	 */
	
    public TransactionJDBC() throws SQLException{    
    	  String url = "jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
          String username = "root";
          String password = "toor";
          connect = DriverManager.getConnection(url,username,password);
          statement = connect.createStatement();            
      }
    
	/**
	 * Metoda addTransaction wykorzystywana do dodawania rekordów w tabeli Transaction
	 * 
	 * @param sc obiekt klasy {@link Transaction} przechowywujący dane do inserta
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 */
        
    public void addTransaction(Transaction sc)throws SQLException{
        String sql = "insert into Transaction (discount, payment, date, IdScheduler, IdSeller, IdBuyer) values(?,?,?,?,?,?)";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setString(1,sc.getDiscount());
        preparedStatement.setString(2,sc.getPayment());
        preparedStatement.setString(3,sc.getDate());
        preparedStatement.setInt(4,sc.getIdScheduler());
        preparedStatement.setInt(5,sc.getIdSeller());
        preparedStatement.setInt(6,sc.getIdBuyer());
        
        preparedStatement.executeUpdate();
              
            
        
    }
    
    
	/**
	 * Metoda deletetransaction wykorzystywana do usuwania rekordów w tabeli Transaction
	 * 
	 * @param index przechowujacy id wykorzystywane w zapytaniu delete
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 */
    
    public void deletetransaction(int index)throws SQLException{
        String sql = "delete from Transaction where transactionId ="+index;
        preparedStatement.executeUpdate(sql);
 
    }    
    
	/**
	 * Metoda updateTransaction wykorzystywana do aktualizacji rekordów w tabeli Transaction
	 * 
	 * @param s obiekt klasy {@link Transaction} przechowywujący dane do update
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 */
    
    public void updateTransaction(Transaction s)throws SQLException{
        String sql = "update Transaction set "+"',discount='"+s.getDiscount()+"',payment='"+s.getPayment()+"',date = '"+s.getDate()+"',IdScheduler='"+s.getIdScheduler()+"',IdSeller='"+s.getIdSeller()+"',IdBuyer='"+s.getIdBuyer()+"' where transactionId='"+s.getTransactionId()+"'";
        preparedStatement.executeUpdate(sql);      
        
    }
    
        
}

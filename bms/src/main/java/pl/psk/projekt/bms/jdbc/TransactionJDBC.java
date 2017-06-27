package pl.psk.projekt.bms.jdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


import pl.psk.projekt.bms.dbobjects.Transaction;


/** KLASA TransactionJDBC - Zawiera w swoim ciele metody umożliwiające połączenie oraz dodanie, usunięcie, edytowanie rekordów bazy 
* @see Connection
* @see DriverManager
* @see DriverManager.getConnection()
* @see PreparedStatement
* @see Statement
*/


public class TransactionJDBC {
	
	Statement statement;
    PreparedStatement  preparedStatement;
    Connection connect;
    
    public TransactionJDBC() throws SQLException{    
    	  String url = "jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
          String username = "root";
          String password = "toor";
          connect = DriverManager.getConnection(url,username,password);
          statement = connect.createStatement();            
      }
    
   
        
    public boolean addTransaction(Transaction sc)throws SQLException{
        String sql = "insert into Transaction (discount, payment, date, IdScheduler, IdSeller, IdBuyer) values(?,?,?,?,?,?)";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setString(1,sc.getDiscount());
        preparedStatement.setString(2,sc.getPayment());
        preparedStatement.setString(3,sc.getDate());
        preparedStatement.setInt(4,sc.getIdScheduler());
        preparedStatement.setInt(5,sc.getIdSeller());
        preparedStatement.setInt(6,sc.getIdBuyer());
        
        int result= preparedStatement.executeUpdate();
              
              if (result > 0)
                   return true;
              else
                  return false;
        
    }
    
    public boolean deletetransaction(int index)throws SQLException{
        String sql = "delete from Transaction where transactionId ="+index;
        int result = preparedStatement.executeUpdate(sql);
        
        
        if (result > 0)
            return true;
        else
            return false;
        
    }    
    
    public boolean updateTransaction(Transaction s)throws SQLException{
        String sql = "update Transaction set "+"',discount='"+s.getDiscount()+"',payment='"+s.getPayment()+"',date = '"+s.getDate()+"',IdScheduler='"+s.getIdScheduler()+"',IdSeller='"+s.getIdSeller()+"',IdBuyer='"+s.getIdBuyer()+"' where transactionId='"+s.getTransactionId()+"'";
        int result = preparedStatement.executeUpdate(sql);
        
        if (result > 0)
            return true;
        else
            return false;        
        
    }
    
        
}

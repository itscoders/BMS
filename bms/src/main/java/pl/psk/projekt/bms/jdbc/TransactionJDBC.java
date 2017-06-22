package pl.psk.projekt.bms.jdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


import pl.psk.projekt.bms.dbobjects.Transaction;


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
        String sql = "insert into scheduler(discount, number, price, payment, IdScheduler, IdTicket, IdBuyer) values(?,?,?,?,?,?)";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1,sc.getTransactionId());
        preparedStatement.setInt(2,sc.getNumber());
        preparedStatement.setDouble(3,sc.getPrice());
        preparedStatement.setString(4,sc.getPayment());
        preparedStatement.setString(5,sc.getIdScheduler());
        preparedStatement.setString(6,sc.getIdTicket());
        preparedStatement.setString(7,sc.getIdBuyer());
        
        int result= preparedStatement.executeUpdate();
              
              if (result > 0)
                   return true;
              else
                  return false;
        
    }
    
    public boolean deletetransaction(String transactionId)throws SQLException{
        String sql = "delete from Transaction where transactionId ='"+transactionId+"'";
        int result = preparedStatement.executeUpdate(sql);
        
        
        if (result > 0)
            return true;
        else
            return false;
        
    }    
    
    public boolean updateTransaction(Transaction s)throws SQLException{
        String sql = "update Transaction set "+"',discount='"+s.getDiscount()+"',number='"+s.getNumber()+"',price='"+s.getPrice()+"',payment='"+s.getPayment()+"',IdScheduler='"+s.getIdScheduler()+"',IdTicket='"+s.getIdTicket()+"',IdBuyer='"+s.getIdBuyer()+"' where transactionId='"+s.getTransactionId()+"'";
        int result = preparedStatement.executeUpdate(sql);
        
        if (result > 0)
            return true;
        else
            return false;        
        
    }
    
        
}

package pl.psk.projekt.bms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import pl.psk.projekt.bms.dbobjects.Ticket;


public class TicketJDBC {

    Statement statement;
    PreparedStatement  preparedStatement;
    Connection connect;
    
    
    public TicketJDBC() throws SQLException{    
        String url = "jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
        String username = "root";
        String password = "toor";
        connect = DriverManager.getConnection(url,username,password);
        statement = connect.createStatement();            
    }
    
    
    public boolean createTicket(Ticket t)throws SQLException{
    String sql="INSERT INTO Ticket (type, price, IdBusLine) values(?,?,?)";
    preparedStatement=connect.prepareStatement(sql);
    preparedStatement.setString(1,t.getType());
    preparedStatement.setDouble(2,t.getPrice());
    preparedStatement.setString(3,t.getIdLine());
    int result = preparedStatement.executeUpdate();
        if(result > 0)
            return true;
        else
            return false;
    }
    
    public boolean deleteTicket(Ticket t)throws SQLException{
        String sql ="DELETE FROM Ticket WHERE ticketId=?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1,t.getTicketId());
        int result = preparedStatement.executeUpdate();
        if(result > 0)
            return true;
        else
            return false; //latest deleted worked
    }
    
    public boolean updateTicket(Ticket t) throws SQLException{
    String sql="update Ticket set type=?, price=?, IdBusLine=? where ticketId=? ";
    preparedStatement=connect.prepareStatement(sql);
    preparedStatement.setString(1,t.getType());
    preparedStatement.setDouble(2,t.getPrice());
    preparedStatement.setString(3,t.getIdLine());
       int result = preparedStatement.executeUpdate();
        if(result > 0)
            return true;
        else
            return false; //success
    }
	
}



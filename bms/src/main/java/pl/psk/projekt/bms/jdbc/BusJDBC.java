package pl.psk.projekt.bms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import pl.psk.projekt.bms.dbobjects.Bus;



public class BusJDBC {

    Statement statement;
    PreparedStatement  preparedStatement;
    Connection connect;
    
    
    public BusJDBC() throws SQLException{    
        String url = "jdbc:mysql://localhost:3306/bms_db";
        String username = "root";
        String password = "toor";
        connect = DriverManager.getConnection(url,username,password);
        statement = connect.createStatement();            
    }
    
    
    public boolean createBus(Bus b)throws SQLException{
    String sql="INSERT INTO BUS (busName,seat) values(?,?,?)";
    preparedStatement=connect.prepareStatement(sql);
    preparedStatement.setString(1,b.getBusName());
    preparedStatement.setInt(2,b.getSeat());
    int result = preparedStatement.executeUpdate();
        if(result > 0)
            return true;
        else
            return false;
    }
    
    public boolean deleteBus(Bus b)throws SQLException{
        String sql ="DELETE FROM BUS WHERE busName=?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setString(1,b.getBusName());
        int result = preparedStatement.executeUpdate();
        if(result > 0)
            return true;
        else
            return false; //latest deleted worked
    }
    
    public boolean updateBus(Bus b) throws SQLException{
    String sql="update bus set busName=?, seat=?  where busID=? ";
    preparedStatement=connect.prepareStatement(sql);
    preparedStatement.setString(1,b.getBusName());
    preparedStatement.setInt(2,b.getSeat());
    preparedStatement.setString(3,b.getBusID());
   
        int result = preparedStatement.executeUpdate();
        if(result > 0)
            return true;
        else
            return false; //success
    }
	
	
}



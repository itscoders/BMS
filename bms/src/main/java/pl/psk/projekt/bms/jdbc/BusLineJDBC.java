package pl.psk.projekt.bms.jdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import pl.psk.projekt.bms.dbobjects.BusLine;


public class BusLineJDBC {

    Statement statement;
    PreparedStatement  preparedStatement;
    Connection connect;
    
    
    public BusLineJDBC() throws SQLException{    
        String url = "jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
        String username = "root";
        String password = "toor";
        connect = DriverManager.getConnection(url,username,password);
        statement = connect.createStatement();            
    }
    
    
    public boolean createBusLine(BusLine b)throws SQLException{
    String sql="INSERT INTO BusLine (busLineName, busLineType, startStation, endStation, price) values(?,?,?,?,?)";
    preparedStatement=connect.prepareStatement(sql);
    preparedStatement.setString(1,b.getBusLineName());
    preparedStatement.setString(2,b.getBusLineType());
    preparedStatement.setString(3,b.getBusLineName());
    preparedStatement.setString(4,b.getBusLineType());
    preparedStatement.setInt(5,b.getPrice());
    int result = preparedStatement.executeUpdate();
        if(result > 0)
            return true;
        else
            return false;
    }
    
    public boolean deleteBusLine(BusLine b)throws SQLException{
        String sql ="DELETE FROM BusLine WHERE busLineID=?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1,b.getBusLineID());
        int result = preparedStatement.executeUpdate();
        if(result > 0)
            return true;
        else
            return false; //latest deleted worked
    }
    
    public boolean updateBusLine(BusLine b) throws SQLException{
    String sql="update BusLine set busLineName=?, busLineType=?, startStation=?, endStation=? where busLineID=? ";
    preparedStatement=connect.prepareStatement(sql);
    preparedStatement.setString(1,b.getBusLineName());
    preparedStatement.setString(2,b.getBusLineType());
    preparedStatement.setString(3,b.getBusLineName());
    preparedStatement.setString(4,b.getBusLineType());
    preparedStatement.setInt(5,b.getBusLineID());
   
        int result = preparedStatement.executeUpdate();
        if(result > 0)
            return true;
        else
            return false; //success
    }
	
}



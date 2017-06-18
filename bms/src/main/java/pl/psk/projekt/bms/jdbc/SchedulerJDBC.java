package pl.psk.projekt.bms.jdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pl.psk.projekt.bms.dbobjects.Scheduler;


public class SchedulerJDBC {
	
	Statement statement;
    PreparedStatement  preparedStatement;
    Connection connect;
    
    public SchedulerJDBC() throws SQLException{    
    	  String url = "jdbc:mysql://localhost:3306/bms_db";
          String username = "root";
          String password = "toor";
          connect = DriverManager.getConnection(url,username,password);
          statement = connect.createStatement();            
      }
    
   
        
    public boolean addSchedulerRecord(Scheduler sc)throws SQLException{
        String sql = "insert into scheduler(scheID,scheDate,dest,departure,busID,driverID) values(?,?,?,?,?,?)";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1,sc.getSchedulerRecordID());
        preparedStatement.setString(2,sc.getDepertureTime());
        preparedStatement.setString(3,sc.getArrivalTime());
        preparedStatement.setString(4,sc.getIdBus());
        preparedStatement.setString(5,sc.getIdDriver());
        preparedStatement.setString(6,sc.getIdLine());
        
        int result= preparedStatement.executeUpdate();
              
              if (result > 0)
                   return true;
              else
                  return false;
        
    }
    
    public boolean deleteSchedulerRecord(String SchedulerRecordID)throws SQLException{
        String sql = "delete from scheduler where SchedulerRecordID ='"+SchedulerRecordID+"'";
        int result = preparedStatement.executeUpdate(sql);
        
        
        if (result > 0)
            return true;
        else
            return false;
        
    }    
    
    public boolean updateSchedulerRecord(Scheduler s)throws SQLException{
        String sql = "update scheduler set "+"',depertureTime='"+s.getDepertureTime()+"',arrivalTime='"+s.getArrivalTime()+"',IdBus='"+s.getIdBus()+"',IdLine='"+s.getIdLine()+"',driverID='"+s.getIdDriver()+"' where scheID='"+s.getSchedulerRecordID()+"'";
        int result = preparedStatement.executeUpdate(sql);
        
        if (result > 0)
            return true;
        else
            return false;        
        
    }
    
        
}

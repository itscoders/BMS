package pl.psk.projekt.bms.jdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pl.psk.projekt.bms.dbobjects.Workers;


public class WorkersJDBC {

    Statement statement;
    PreparedStatement  preparedStatement;
    Connection connect;
    
    
    public WorkersJDBC() throws SQLException{    
        String url = "jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
        String username = "root";
        String password = "toor";
        connect = DriverManager.getConnection(url,username,password);
        statement = connect.createStatement();            
    }
    
    
    public boolean workersLog(Workers w)throws SQLException{
        
    	String sql = "SELECT * FROM WORKERS WHERE USERNAME =? AND PASSWORD =?";
    	
    	preparedStatement = connect.prepareStatement(sql);
    	preparedStatement.setString(1,w.getUsername());
    	preparedStatement.setString(2,w.getPassword());
        ResultSet rs = preparedStatement.executeQuery();
        
        while(rs.next()){
        	
            w.setWorkerId(rs.getInt("workerId"));
            w.setPossition(rs.getString("possition"));
            w.setAddress(rs.getString("address"));
            w.setBirthday(rs.getString("birthday"));
            w.setMobile(rs.getString("mobile"));
            w.setSalary(rs.getDouble("salary"));
            w.setAccountType(rs.getString("accountType"));
            //w.setName(rs.getString(""));
            
        }
        return true;
                
    }
    
    public String workersLog(String username,String password)throws Exception{
        
        Workers w = new Workers();
        w.setUsername(username);
        w.setPassword(password);
        
        boolean success = this.workersLog(w);
        if(success)
            return w.getName() + "" + w.getSurname();
        else
            return null;
        
    }
    
 
    public Workers passRecover(Workers w)throws SQLException{
        String sql = "SELECT PASSWORD FROM WORKERS WHERE USERNAME =? ";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setString(1,w.getUsername());
        
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            w.setPassword(rs.getString("password"));
        }
        return w;   //recovery successfully
    }
    
    

    
    public boolean createWorker(Workers w)throws SQLException{
    String sql="INSERT INTO WORKERS (accountType,username, password, possition, mobile, address, birthday, salary) values(?,?,?,?,?,?,?,?)";
    preparedStatement=connect.prepareStatement(sql);
  
    preparedStatement.setString(1,w.getAccountType());
    preparedStatement.setString(2,w.getUsername());
    preparedStatement.setString(3,w.getPassword());
    preparedStatement.setString(4,w.getPossition());
    preparedStatement.setString(5,w.getMobile());
    preparedStatement.setString(6,w.getAddress());
    preparedStatement.setString(7,w.getBirthday());
    preparedStatement.setDouble(8,w.getSalary());
    
        
    int result = preparedStatement.executeUpdate();
        if(result > 0)
            return true;
        else
            return false;
            
    }
    
    
    public boolean updateWorker(Workers w)throws SQLException{
    String sql ="UPDATE WORKERS SET ACCOUNTTYPE=?, PASSWORD=?, POSSITION=?, MOBILE=?, ADDRESS=?, BIRTHDAY=?,SALARY=? WHERE USERNAME=?";
    preparedStatement=connect.prepareStatement(sql);
    
    preparedStatement.setString(1,w.getAccountType());
    preparedStatement.setString(2,w.getPassword());
    preparedStatement.setString(3,w.getPossition());
    preparedStatement.setString(4,w.getMobile());
    preparedStatement.setString(5,w.getAddress());
    preparedStatement.setString(6,w.getBirthday());
    preparedStatement.setDouble(7,w.getSalary());
    preparedStatement.setInt(8,w.getWorkerId());
        
    int result = preparedStatement.executeUpdate();
        if(result > 0)
            return true;
        else
            return false;
    }
    

    
    public boolean deleteWorker(Workers w) throws SQLException{
              String sql = "DELETE FROM WORKERS WHERE USERNAME=?";
              preparedStatement = connect.prepareStatement(sql);
              preparedStatement.setString(1,w.getUsername());
              int result = preparedStatement.executeUpdate();
              if (result > 0)
                  return true;
              else
                  return false;
    }
    
}

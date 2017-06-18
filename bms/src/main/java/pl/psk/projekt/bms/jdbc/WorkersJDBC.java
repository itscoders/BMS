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
        String url = "jdbc:mysql://localhost:3306/bms_db";
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
        	
            w.setWorkerId(rs.getString("workerId"));
            w.setPossition(rs.getString("possition"));
            w.setAddress(rs.getString("address"));
            w.setBirthday(rs.getString("birthday"));
            w.setMobile(rs.getString("mobile"));
            w.setSalary(rs.getDouble("salary"));
            w.setAccountType(rs.getString("accountType"));
            
        }
        return true;
                
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
    preparedStatement.setString(8,w.getWorkerId());
        
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
    
    public static void main( String[] args ) throws SQLException
    {
    	
    	
    	WorkersJDBC wj = new WorkersJDBC();
    	String create;
        
          create = 	"CREATE TABLE Workers ("+
            			"workerId int AUTO_INCREMENT PRIMARY KEY,"+
            			"accountType varchar(255),"+
            			"username varchar(255),"+
            			"password varchar(255),"+
            			"possition varchar(255),"+
            			"mobile varchar(255),"+
            			"address varchar(255),"+
            			"birthday varchar(255),"+
            			"salary double );";
            wj.statement.execute(create);
    	
            Workers w = new Workers("Admin43", "admin12", "admin", "admin", "2136455558588", "Kielce", "25-07-1968", 2563);
            Workers w1 = new Workers("Admin23", "admin22", "admin", "admin", "2136455558588", "Kielce", "25-07-1968", 2563);
            wj.createWorker(w);
            wj.createWorker(w1);
            w.setPossition("seller");
            wj.updateWorker(w);
            Workers w2 = new Workers("Admin224", "admin32", "admin", "admin", "2136455558588", "Kielce", "25-07-1968", 2563);
            wj.createWorker(w2);
            wj.deleteWorker(w1);
            wj.createWorker(w1);
            
            create = "DROP TABLE WORKERS;";
            //wj.statement.execute(create);
    }
}

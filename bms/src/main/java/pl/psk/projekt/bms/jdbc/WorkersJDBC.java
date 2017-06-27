package pl.psk.projekt.bms.jdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pl.psk.projekt.bms.dbobjects.Workers;


/** KLASA WorkersJDBC - Zawiera w swoim ciele metody umożliwiające połączenie oraz dodanie, usunięcie, edytowanie rekordó bazy oraz wyciąganie iformaci przy logowanie
* @see Connection
* @see DriverManager
* @see DriverManager.getConnection()
* @see PreparedStatement
* @see Statement
*/



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
    
    
    public Workers workersLog(Workers w)throws SQLException{
        
    	String sql = "SELECT * FROM WORKERS WHERE USERNAME=? AND PASSWORD =?";
    	preparedStatement = connect.prepareStatement(sql);
    	preparedStatement.setString(1,w.getUsername());
    	preparedStatement.setString(2,w.getPassword());
        ResultSet rs = preparedStatement.executeQuery();
        
        while(rs.next()){
        	
            w.setWorkerId(rs.getInt("workerId"));
            w.setEmail(rs.getString("email"));
            w.setAddress(rs.getString("address"));
            w.setBirthday(rs.getString("birthday"));
            w.setMobile(rs.getString("mobile"));
            w.setSalary(rs.getDouble("salary"));
            w.setAccountType(rs.getString("accountType"));
            w.setName(rs.getString("name"));
            w.setSurname(rs.getString("surname"));
            
        }
        return w;
                
    }
    
    public Workers workersLog(String username,String password)throws Exception{
        
        Workers w = new Workers();
        w.setUsername(username);
        w.setPassword(password);
        
        
        w = this.workersLog(w);
        if(w.getName() != null)
            return w;
        else
            return null;
        
    }
    
 
    public Workers passRecover(Workers w)throws SQLException{
        String sql = "SELECT PASSWORD, NAME, EMAIL FROM WORKERS WHERE USERNAME =? ";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setString(1,w.getUsername());
        
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            w.setPassword(rs.getString("password"));
            w.setName(rs.getString("name"));
            w.setEmail(rs.getString("email"));
        }
        return w;   //recovery successfully
    }
    
    

    
    public boolean createWorker(Workers w)throws SQLException{
    String sql="INSERT INTO WORKERS (accountType, username, password, name, surname, email, mobile, address, birthday, salary) values(?,?,?,?,?,?,?,?,?,?)";
    preparedStatement=connect.prepareStatement(sql);
  
    preparedStatement.setString(1,w.getAccountType());
    preparedStatement.setString(2,w.getUsername());
    preparedStatement.setString(3,w.getPassword());
    preparedStatement.setString(4,w.getName());
    preparedStatement.setString(5,w.getSurname());
    preparedStatement.setString(6,w.getEmail());
    preparedStatement.setString(7,w.getMobile());
    preparedStatement.setString(8,w.getAddress());
    preparedStatement.setString(9,w.getBirthday());
    preparedStatement.setDouble(10,w.getSalary());
    
        
    int result = preparedStatement.executeUpdate();
        if(result > 0)
            return true;
        else
            return false;
            
    }

    
    
    public boolean updateWorker(Workers w)throws SQLException{
    String sql ="UPDATE WORKERS SET USERNAME=?, ACCOUNTTYPE=?, PASSWORD=?, NAME=?, SURNAME=?, EMAIL=?, MOBILE=?, ADDRESS=?, BIRTHDAY=?,SALARY=? WHERE WORKERID=?";
    preparedStatement=connect.prepareStatement(sql);
    
    preparedStatement.setString(1,w.getAccountType());
    preparedStatement.setString(2,w.getUsername());
    preparedStatement.setString(3,w.getPassword());
    preparedStatement.setString(4,w.getName());
    preparedStatement.setString(5,w.getSurname());
    preparedStatement.setString(6,w.getEmail());
    preparedStatement.setString(7,w.getMobile());
    preparedStatement.setString(8,w.getAddress());
    preparedStatement.setString(9,w.getBirthday());
    preparedStatement.setDouble(10,w.getSalary());
    preparedStatement.setInt(11,w.getWorkerId());
        
    int result = preparedStatement.executeUpdate();
        if(result > 0)
            return true;
        else
            return false;
    }
    

    
    public boolean deleteWorker(Workers w) throws SQLException{
              String sql = "DELETE FROM WORKERS WHERE WORKERID=?";
              preparedStatement = connect.prepareStatement(sql);
              preparedStatement.setInt(1,w.getWorkerId());
              int result = preparedStatement.executeUpdate();
              if (result > 0)
                  return true;
              else
                  return false;
    }
    
    
}

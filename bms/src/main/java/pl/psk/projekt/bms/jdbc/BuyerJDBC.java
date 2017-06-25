package pl.psk.projekt.bms.jdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pl.psk.projekt.bms.dbobjects.Buyer;


public class BuyerJDBC {

    Statement statement;
    PreparedStatement  preparedStatement;
    Connection connect;
    
    
    public BuyerJDBC() throws SQLException{    
        String url = "jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
        String username = "root";
        String password = "toor";
        connect = DriverManager.getConnection(url,username,password);
        statement = connect.createStatement();            
    }
    
    
    public boolean createBuyer(Buyer b)throws SQLException{
    String sql="INSERT INTO Buyer (name, surname, birthday, email, mobile, street, houseNumber, postCode, city) values(?,?,?,?,?,?,?,?,?)";
    preparedStatement=connect.prepareStatement(sql);
  
    preparedStatement.setString(1,b.getName());
    preparedStatement.setString(2,b.getSurname());
    preparedStatement.setString(3,b.getBirthday());
    preparedStatement.setString(4,b.getEmail());
    preparedStatement.setString(5,b.getMobile());
    preparedStatement.setString(6,b.getStreet());
    preparedStatement.setString(7,b.getHouseNumber());
    preparedStatement.setString(8,b.getPostCode());
    preparedStatement.setString(9,b.getCity());
    
        
    int result = preparedStatement.executeUpdate();
        if(result > 0)
            return true;
        else
            return false;
            
    }

    
    
    public boolean updateBuyer(Buyer b)throws SQLException{
    String sql ="UPDATE Buyer SET NAME=?, SURNAME=?, BIRTHDAY=?, EMAIL=?, MOBILE=?, STREET=?, HOUSENUMBER=?, POSTCODE=?, CITY=? WHERE BUYERID=?";
    preparedStatement=connect.prepareStatement(sql);
    
    preparedStatement.setString(1,b.getName());
    preparedStatement.setString(2,b.getSurname());
    preparedStatement.setString(3,b.getBirthday());
    preparedStatement.setString(4,b.getEmail());
    preparedStatement.setString(5,b.getMobile());
    preparedStatement.setString(6,b.getStreet());
    preparedStatement.setString(7,b.getHouseNumber());
    preparedStatement.setString(8,b.getPostCode());
    preparedStatement.setString(9,b.getCity());
    preparedStatement.setInt(10,b.getBuyerId());
        
    int result = preparedStatement.executeUpdate();
        if(result > 0)
            return true;
        else
            return false;
    }
    

    
    public boolean deleteBuyer(Buyer b) throws SQLException{
              String sql = "DELETE FROM BUYER WHERE WHERE BUYERID=?";
              preparedStatement = connect.prepareStatement(sql);
              preparedStatement.setInt(1,b.getBuyerId());
              int result = preparedStatement.executeUpdate();
              if (result > 0)
                  return true;
              else
                  return false;
    }
    
    
}

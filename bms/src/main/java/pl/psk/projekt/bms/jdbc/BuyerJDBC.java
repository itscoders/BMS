package pl.psk.projekt.bms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import pl.psk.projekt.bms.dbobjects.BusLine;
import pl.psk.projekt.bms.dbobjects.Buyer;

/**
 * KLASA BuyerJDBC - Zawiera w swoim ciele metody umożliwiające połączenie oraz
 * dodanie, usunięcie, edytowanie rekordów bazy
 * 
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
 */

public class BuyerJDBC {

	/** Definicja obiektu klasy Statement. */
	Statement statement;
	/** Definicja obiektu klasy PreparedStatement. */
	PreparedStatement preparedStatement;
	/** Definicja obiektu klasy Connection. */
	Connection connect;

	/**
	 * Konstruktor bezparametrowy klasy BuyerJDBC dokonanuje połączenia z bazą
	 * 
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.Statement
	 * @see java.sql.SQLException
	 */

	public BuyerJDBC() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
		String username = "root";
		String password = "toor";
		connect = DriverManager.getConnection(url, username, password);
		statement = connect.createStatement();
	}

	/**
	 * Metoda createBuyer wykorzystywana do dodawania rekordów w tabeli Buyer
	 * 
	 * @param b
	 *            obiekt klasy {@link Buyer} przechowywujący dane do inserta
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 */

	public void createBuyer(Buyer b) throws SQLException {
		String sql = "INSERT INTO Buyer (name, surname, birthday, email, mobile, street, houseNumber, postCode, city, insuranceNumber) values(?,?,?,?,?,?,?,?,?,?)";
		preparedStatement = connect.prepareStatement(sql);

		preparedStatement.setString(1, b.getName());
		preparedStatement.setString(2, b.getSurname());
		preparedStatement.setString(3, b.getBirthday());
		preparedStatement.setString(4, b.getEmail());
		preparedStatement.setString(5, b.getMobile());
		preparedStatement.setString(6, b.getStreet());
		preparedStatement.setString(7, b.getHouseNumber());
		preparedStatement.setString(8, b.getPostCode());
		preparedStatement.setString(9, b.getCity());
		preparedStatement.setString(10, b.getInsuranceNumber());

		preparedStatement.executeUpdate();

	}
	
	/**
	 * Metoda updateBuyer wykorzystywana do aktualizacji rekordów w tabeli Buyer
	 * 
	 * @param b obiekt klasy {@link Buyer} przechowywujący dane do delete
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 */

	public void updateBuyer(Buyer b) throws SQLException {
		String sql = "UPDATE Buyer SET NAME=?, SURNAME=?, BIRTHDAY=?, EMAIL=?, MOBILE=?, STREET=?, HOUSENUMBER=?, POSTCODE=?, CITY=?, INSURANCENUMBER=? WHERE BUYERID=?";
		preparedStatement = connect.prepareStatement(sql);

		preparedStatement.setString(1, b.getName());
		preparedStatement.setString(2, b.getSurname());
		preparedStatement.setString(3, b.getBirthday());
		preparedStatement.setString(4, b.getEmail());
		preparedStatement.setString(5, b.getMobile());
		preparedStatement.setString(6, b.getStreet());
		preparedStatement.setString(7, b.getHouseNumber());
		preparedStatement.setString(8, b.getPostCode());
		preparedStatement.setString(9, b.getCity());
		preparedStatement.setString(10, b.getInsuranceNumber());
		preparedStatement.setInt(11, b.getBuyerId());

		preparedStatement.executeUpdate();

	}
	
	/**
	 * Metoda deleteBuyer wykorzystywana do usuwania rekordów w tabeli Buyer
	 * 
	 * @param b obiekt klasy {@link Buyer} przechowywujący dane do delete
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 */
	
	public void deleteBuyer(Buyer b) throws SQLException {
		String sql = "DELETE FROM BUYER WHERE WHERE BUYERID=?";
		preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setInt(1, b.getBuyerId());
		preparedStatement.executeUpdate();
		
		
	}

}

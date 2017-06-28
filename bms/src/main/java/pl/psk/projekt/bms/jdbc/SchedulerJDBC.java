package pl.psk.projekt.bms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import pl.psk.projekt.bms.dbobjects.Buyer;
import pl.psk.projekt.bms.dbobjects.Scheduler;

/**
 * KLASA SchedulerJDBC - Zawiera w swoim ciele metody umożliwiające połączenieoraz dodanie, usunięcie, edytowanie rekordów bazy
 * 
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
 */

public class SchedulerJDBC {

	/** Definicja obiektu klasy Statement. */
	Statement statement;
	/** Definicja obiektu klasy PreparedStatement. */
	PreparedStatement preparedStatement;
	/** Definicja obiektu klasy Connection. */
	Connection connect;

	/**
	 * Konstruktor bezparametrowy klasy SchedulerJDBC dokonanuje połączenia z
	 * bazą
	 * 
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.Statement
	 * @see java.sql.SQLException
	 */

	public SchedulerJDBC() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
		String username = "root";
		String password = "toor";
		connect = DriverManager.getConnection(url, username, password);
		statement = connect.createStatement();
	}

	/**
	 * Metoda addSchedulerRecord wykorzystywana do dodawania rekordów w tabeli Scheduler
	 * 
	 * @param sc obiekt klasy {@link Scheduler} przechowywujący dane do inserta
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 */

	public void addSchedulerRecord(Scheduler sc) throws SQLException {
		String sql = "INSERT INTO SCHEDULER (depertureTime,arrivalTime,IdBus,IdBusLine,IdDriver) values(?,?,?,?,?)";
		
		preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setString(1, sc.getDepertureTime());
		preparedStatement.setString(2, sc.getArrivalTime());
		preparedStatement.setInt(3, sc.getIdBus());
		preparedStatement.setInt(4, sc.getIdLine());
		preparedStatement.setInt(5, sc.getIdDriver());

		preparedStatement.executeUpdate();

	}
	
	/**
	 * Metoda deleteSchedulerRecord wykorzystywana do usuwania rekordów w tabeli scheduler
	 * 
	 * @param SchedulerRecordID przechowujacy id wykorzystywane w zapytaniu delete
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 */
	
	public void deleteSchedulerRecord(int SchedulerRecordID) throws SQLException {
		String sql = "delete from scheduler where schedulerID =" + SchedulerRecordID;
		preparedStatement = connect.prepareStatement(sql);
		preparedStatement.executeUpdate(sql);

	}
	
	/**
	 * Metoda updateSchedulerRecord wykorzystywana do aktualizacji rekordów w tabeli Scheduler
	 * 
	 * @param sc obiekt klasy {@link Scheduler} przechowywujący dane do update
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 */
	
	public void updateSchedulerRecord(Scheduler sc) throws SQLException {
		String sql = "update scheduler set depertureTime='" + sc.getDepertureTime() + "',arrivalTime='"
				+ sc.getArrivalTime() + "',IdBus=" + sc.getIdBus() + ",IdBusLine=" + sc.getIdLine() + ",IdDriver="
				+ sc.getIdDriver() + " where schedulerID =" + sc.getSchedulerRecordID();
		statement = connect.createStatement();

		statement.execute(sql);

	}

}

package pl.psk.projekt.bms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * KLASA CreateDB służy do tworzenia zawartości bazy danych
 *
 * 
 * @author Paweł Pawelec i Kamil Świąder
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * @see java.sql.Statement
 * @see java.sql.SQLException
 */

public class CreateDB {

	/**
	 * Konstruktor bezparametrowy klasy CreateDB odpowiedzialny za inicializację
	 * tabel w bazie danych
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.Statement
	 * @see java.sql.SQLException
	 */

	public CreateDB() throws SQLException {
		/** Zmienna do której zapisywane są polecenia SQL */
		String create;
		/** Zmienna przechowująca połączenie z baza danych*/
		Connection connect = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
				"root", "toor");
		/** Zmienna wykorzystująca połączenie z bazą do wykonania zapytania. */
		Statement statement = connect.createStatement();

		create = "CREATE TABLE IF NOT EXISTS Workers (" + "workerId int AUTO_INCREMENT PRIMARY KEY,"
				+ "accountType varchar(255)," + "username varchar(255) UNIQUE," + "password varchar(255),"
				+ "name varchar(255)," + "surname varchar(255)," + "email varchar(255)," + "mobile varchar(255),"
				+ "address varchar(255)," + "birthday varchar(255)," + "salary double );";

		statement.execute(create);

		create = "CREATE TABLE IF NOT EXISTS Bus (" + "busID int AUTO_INCREMENT PRIMARY KEY,"
				+ "busName varchar(255) NOT NULL," + "seat int NOT NULL );";
		statement.execute(create);

		create = "CREATE TABLE IF NOT EXISTS BusLine (" + "busLineID int AUTO_INCREMENT PRIMARY KEY,"
				+ "busLineName varchar(255)," + "busLineType varchar(255)," + "startStation varchar(255),"
				+ "endStation varchar(255)," + "priceOneWay DECIMAL(5,2)," + "priceMonthly DECIMAL(5,2));";

		statement.execute(create);

		create = "CREATE TABLE IF NOT EXISTS Scheduler (" + "schedulerID int AUTO_INCREMENT PRIMARY KEY,"
				+ "depertureTime varchar(255)," + "arrivalTime varchar(255)," + "IdBus int," + "IdBusLine int,"
				+ "IdDriver int," + "FOREIGN KEY (IdBus) REFERENCES Bus(busID),"
				+ "FOREIGN KEY (IdBusLine) REFERENCES BusLine(busLineID),"
				+ "FOREIGN KEY (IdDriver) REFERENCES Workers(workerId));";
		statement.execute(create);

		create = "CREATE TABLE IF NOT EXISTS Buyer (" + "buyerId int AUTO_INCREMENT PRIMARY KEY," + "name varchar(255),"
				+ "surname varchar(255)," + "birthday varchar(255)," + "email varchar(255)," + "mobile varchar(255),"
				+ "street varchar(255)," + "houseNumber varchar(255)," + "postCode varchar(255)," + "city varchar(255),"
				+ "insuranceNumber varchar(255));";
		statement.execute(create);

		create = "CREATE TABLE IF NOT EXISTS Transaction (" + "transactionId int AUTO_INCREMENT PRIMARY KEY,"
				+ "discount varchar(255)," + "payment varchar(255)," + "date varchar(255)," + "IdScheduler int,"
				+ "IdSeller int," + "IdBuyer int," + "FOREIGN KEY (IdScheduler) REFERENCES Scheduler(schedulerID),"
				+ "FOREIGN KEY (IdSeller) REFERENCES Workers(workerId),"
				+ "FOREIGN KEY (IdBuyer) REFERENCES Buyer(buyerId));";
		statement.execute(create);

	}
	
	
	/**
	 * Konstruktor parametrowy klasy CreateDB odpowiedzialny za inicializację
	 * tabel w bazie danych oraz utworzenie samej bazy danych
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @param value przechowuje decyzje użytkownika wypływającą na wykonanie
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.Statement
	 * @see java.sql.SQLException
	 */
	
	public CreateDB(int value) throws SQLException {

		if (value == 0) {
			/** Zmienna do której zapisywane są polecenia SQL */
			String create;
			/** Zmienna wykorzystująca połączenie z bazą do wykonania zapytania. */
			Statement statement;
			/** Zmienna przechowująca połączenie z bazom danych*/
			Connection conn;
			try {
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");
				statement = conn.createStatement();
				statement.executeUpdate("DROP DATABASE IF EXISTS bms_db");
				statement.executeUpdate("CREATE DATABASE bms_db");

				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");
				statement = conn.createStatement();

				create = "CREATE TABLE IF NOT EXISTS Workers (" + "workerId int AUTO_INCREMENT PRIMARY KEY,"
						+ "accountType varchar(255)," + "username varchar(255) UNIQUE," + "password varchar(255),"
						+ "name varchar(255)," + "surname varchar(255)," + "email varchar(255),"
						+ "mobile varchar(255)," + "address varchar(255)," + "birthday varchar(255),"
						+ "salary double );";

				statement.execute(create);

				create = "CREATE TABLE IF NOT EXISTS Bus (" + "busID int AUTO_INCREMENT PRIMARY KEY,"
						+ "busName varchar(255) NOT NULL," + "seat int NOT NULL );";
				statement.execute(create);

				create = "CREATE TABLE IF NOT EXISTS BusLine (" + "busLineID int AUTO_INCREMENT PRIMARY KEY,"
						+ "busLineName varchar(255)," + "busLineType varchar(255)," + "startStation varchar(255),"
						+ "endStation varchar(255)," + "priceOneWay DECIMAL(5,2)," + "priceMonthly DECIMAL(5,2));";

				statement.execute(create);

				create = "CREATE TABLE IF NOT EXISTS Scheduler (" + "schedulerID int AUTO_INCREMENT PRIMARY KEY,"
						+ "depertureTime varchar(255)," + "arrivalTime varchar(255)," + "IdBus int," + "IdBusLine int,"
						+ "IdDriver int," + "FOREIGN KEY (IdBus) REFERENCES Bus(busID),"
						+ "FOREIGN KEY (IdBusLine) REFERENCES BusLine(busLineID),"
						+ "FOREIGN KEY (IdDriver) REFERENCES Workers(workerId));";
				statement.execute(create);

				create = "CREATE TABLE IF NOT EXISTS Buyer (" + "buyerId int AUTO_INCREMENT PRIMARY KEY,"
						+ "name varchar(255)," + "surname varchar(255)," + "birthday varchar(255),"
						+ "email varchar(255)," + "mobile varchar(255)," + "street varchar(255),"
						+ "houseNumber varchar(255)," + "postCode varchar(255)," + "city varchar(255),"
						+ "insuranceNumber varchar(255));";
				statement.execute(create);

				create = "CREATE TABLE IF NOT EXISTS Transaction (" + "transactionId int AUTO_INCREMENT PRIMARY KEY,"
						+ "discount varchar(255)," + "payment varchar(255)," + "date varchar(255)," + "IdScheduler int,"
						+ "IdSeller int," + "IdBuyer int,"
						+ "FOREIGN KEY (IdScheduler) REFERENCES Scheduler(schedulerID),"
						+ "FOREIGN KEY (IdSeller) REFERENCES Workers(workerId),"
						+ "FOREIGN KEY (IdBuyer) REFERENCES Buyer(buyerId));";
				statement.execute(create);

				create = "INSERT INTO `workers` (`accountType`, `username`, `password`, `name`, `surname`, `email`, `mobile`, `address`, `birthday`, `salary`) VALUES"
						+ "('Administrator', 'admin', 'admin', 'Admin', 'Admin', 'admin@gmail.com', '000000000', 'Siedziba', '16-Jun-1995', '25000')";

				statement.execute(create);

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

	}

}

package pl.psk.projekt.bms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pl.psk.projekt.bms.dbobjects.Transaction;
import pl.psk.projekt.bms.dbobjects.Workers;

/**
 * KLASA WorkersJDBC - Zawiera w swoim ciele metody umożliwiające połączenie
 * oraz dodanie, usunięcie, edytowanie rekordów bazy oraz wyciąganie iformacji
 * przy logowanie
 * 
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * @see java.sql.Statement
 * @see java.sql.SQLException
 * @see java.sql.ResultSet
 * @see java.sql.PreparedStatement
 */

public class WorkersJDBC {

	/** Definicja obiektu klasy Statement. */
	Statement statement;
	/** Definicja obiektu klasy PreparedStatement. */
	PreparedStatement preparedStatement;
	/** Definicja obiektu klasy Connection. */
	Connection connect;

	/**
	 * Konstruktor bezparametrowy klasy WorkersJDBC dokonanuje połączenia z bazą
	 * 
	 * @throws SQLException
	 *             SQL Exception w razie problemó z połączeniem, wykonaniem
	 *             zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.Statement
	 * @see java.sql.SQLException
	 */

	public WorkersJDBC() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
		String username = "root";
		String password = "toor";
		connect = DriverManager.getConnection(url, username, password);
		statement = connect.createStatement();
	}

	/**
	 * Metoda workersLog wykorzystywana do sprawdzenia czy pracownik istnieje i
	 * pobranie jego danych z bazy
	 * 
	 * @param w
	 *            obiekt klasy {@link Workers} przechowywujący dane do
	 *            porównania z bazą
	 * @return zwraca obiekt klasy Workers
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 * @see java.sql.ResultSet
	 */

	public Workers workersLog(Workers w) throws SQLException {

		String sql = "SELECT * FROM WORKERS WHERE USERNAME=? AND PASSWORD =?";
		preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setString(1, w.getUsername());
		preparedStatement.setString(2, w.getPassword());
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {

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

	/**
	 * Metoda workersLog wykorzystywana do wywołania przeciążonej metody
	 * {@link #workersLog(Workers)}
	 * 
	 * @param username
	 *            string przechowujący nazwe konta
	 * @param password
	 *            string przechowujący hasło konta
	 * @return zwraca obiekt klasy Workers jeżeli jego pole name nie jest nullem
	 *         w pozostałym wypadku zwaca null
	 * @throws SQLException
	 *             SQL Exception w razie problemó z połączeniem, wykonaniem
	 *             zapytania etc.
	 */

	public Workers workersLog(String username, String password) throws Exception {

		Workers w = new Workers();
		w.setUsername(username);
		w.setPassword(password);

		w = this.workersLog(w);
		if (w.getName() != null)
			return w;
		else
			return null;

	}

	/**
	 * Metoda passRecover wykorzystywana do odzyskiwania hasłą usera
	 * 
	 * @param w
	 *            obiekt klasy {@link Workers} przechowywujący dane do
	 *            porównania z bazą
	 * @return zwraca obiekt klasy Workers
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 * @see java.sql.ResultSet
	 */

	public Workers passRecover(Workers w) throws SQLException {
		String sql = "SELECT PASSWORD, NAME, EMAIL FROM WORKERS WHERE USERNAME =? ";
		preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setString(1, w.getUsername());

		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			w.setPassword(rs.getString("password"));
			w.setName(rs.getString("name"));
			w.setEmail(rs.getString("email"));
		}
		return w;
	}

	/**
	 * Metoda createWorker wykorzystywana do dodawania rekordów w tabeli WORKERS
	 * 
	 * @param w
	 *            obiekt klasy {@link Workers} przechowywujący dane do inserta
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 */

	public void createWorker(Workers w) throws SQLException {
		String sql = "INSERT INTO WORKERS (accountType, username, password, name, surname, email, mobile, address, birthday, salary) values(?,?,?,?,?,?,?,?,?,?)";
		preparedStatement = connect.prepareStatement(sql);

		preparedStatement.setString(1, w.getAccountType());
		preparedStatement.setString(2, w.getUsername());
		preparedStatement.setString(3, w.getPassword());
		preparedStatement.setString(4, w.getName());
		preparedStatement.setString(5, w.getSurname());
		preparedStatement.setString(6, w.getEmail());
		preparedStatement.setString(7, w.getMobile());
		preparedStatement.setString(8, w.getAddress());
		preparedStatement.setString(9, w.getBirthday());
		preparedStatement.setDouble(10, w.getSalary());

		preparedStatement.executeUpdate();

	}

	/**
	 * Metoda updateWorker wykorzystywana do edycji rekordów w tabeli WORKERS
	 * 
	 * @param w
	 *            obiekt klasy {@link Workers} przechowywujący dane do update
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 */

	public void updateWorker(Workers w) throws SQLException {
		String sql = "UPDATE WORKERS SET USERNAME=?, ACCOUNTTYPE=?, PASSWORD=?, NAME=?, SURNAME=?, EMAIL=?, MOBILE=?, ADDRESS=?, BIRTHDAY=?,SALARY=? WHERE WORKERID=?";
		preparedStatement = connect.prepareStatement(sql);

		preparedStatement.setString(1, w.getAccountType());
		preparedStatement.setString(2, w.getUsername());
		preparedStatement.setString(3, w.getPassword());
		preparedStatement.setString(4, w.getName());
		preparedStatement.setString(5, w.getSurname());
		preparedStatement.setString(6, w.getEmail());
		preparedStatement.setString(7, w.getMobile());
		preparedStatement.setString(8, w.getAddress());
		preparedStatement.setString(9, w.getBirthday());
		preparedStatement.setDouble(10, w.getSalary());
		preparedStatement.setInt(11, w.getWorkerId());

		preparedStatement.executeUpdate();

	}

	/**
	 * Metoda deleteWorker wykorzystywana do usuwania rekordów w tabeli WORKERS
	 * 
	 * @param w
	 *            obiekt klasy {@link Workers} przechowywujący dane do delete
	 * @throws SQLException SQL Exception w razie problemó z połączeniem, wykonaniem zapytania etc.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.SQLException
	 */

	public void deleteWorker(Workers w) throws SQLException {
		String sql = "DELETE FROM WORKERS WHERE WORKERID=?";
		preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setInt(1, w.getWorkerId());
		preparedStatement.executeUpdate();

	}

}

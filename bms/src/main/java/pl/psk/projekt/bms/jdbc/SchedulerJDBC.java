package pl.psk.projekt.bms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import pl.psk.projekt.bms.dbobjects.Scheduler;

public class SchedulerJDBC {

	Statement statement;
	PreparedStatement preparedStatement;
	Connection connect;

	public SchedulerJDBC() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
		String username = "root";
		String password = "toor";
		connect = DriverManager.getConnection(url, username, password);
		statement = connect.createStatement();
	}

	public boolean addSchedulerRecord(Scheduler sc) throws SQLException {
		String sql = "INSERT INTO SCHEDULER (depertureTime,arrivalTime,IdBus,IdBusLine,IdDriver) values(?,?,?,?,?)";
		System.err.println("tukurwo!");
		preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setString(1, sc.getDepertureTime());
		preparedStatement.setString(2, sc.getArrivalTime());
		preparedStatement.setInt(3, sc.getIdBus());
		preparedStatement.setInt(4, sc.getIdLine());
		preparedStatement.setInt(5, sc.getIdDriver());

		int result = preparedStatement.executeUpdate();

		if (result > 0)
			return true;
		else
			return false;

	}

	public boolean deleteSchedulerRecord(int SchedulerRecordID) throws SQLException {
		String sql = "delete from scheduler where schedulerID =" + SchedulerRecordID;
		preparedStatement = connect.prepareStatement(sql);
		int result = preparedStatement.executeUpdate(sql);

		if (result > 0)
			return true;
		else
			return false;

	}

	public boolean updateSchedulerRecord(Scheduler sc) throws SQLException {
		String sql = "update scheduler set depertureTime='" + sc.getDepertureTime() + "',arrivalTime='"
				+ sc.getArrivalTime() + "',IdBus=" + sc.getIdBus() + ",IdBusLine=" + sc.getIdLine() + ",IdDriver="
				+ sc.getIdDriver() + " where schedulerID =" + sc.getSchedulerRecordID();
		statement = connect.createStatement();
		
		boolean result = statement.execute(sql);

		
			return result;


	}

}

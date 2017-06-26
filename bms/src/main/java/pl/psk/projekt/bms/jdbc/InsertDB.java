package pl.psk.projekt.bms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class InsertDB  {

	public InsertDB() throws SQLException  {
		
		String create;
        Connection connect;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "root", "toor");
			Statement   statement = connect.createStatement();
			
			
			create = "INSERT INTO `workers` (`accountType`, `username`, `password`, `name`, `surname`, `possition`, `mobile`, `address`, `birthday`, `salary`) VALUES"+
					"('Administrator', 'admin', 'admin', 'Admin', 'Admin', 'Administrator', '000000000', 'Siedziba', '16-Jun-1995', '25000'),"+
					"('Administrator', 'pjanuszek', 'pj', 'Pawel', 'Januszek', 'Administrator', '895412369', 'Warszawa, ul.Marszalkowska', '16-Jun-1995', '25000'),"+
					"('Administrator', 'zjanuszek', 'zj', 'Zygfryd', 'Januszek', 'Administrator', '897892369', 'Warszawa, ul.Marszalkowska', '20-Jun-1975', '25000'),"+
					"('Driver', 'pjan', 'pj', 'Pawel', 'Jan', 'Driver', '895356869', 'Radom, ul.Kielecka', '26-Jun-1985', '5000'),"+
					"('Driver', 'kjuras', 'kj', 'Krzysztof', 'Juras', 'Driver', '898512369', 'Kielce, ul.Marszalkowska', '06-Jun-1985', '5000'),"+
					"('Driver', 'zjuras', 'zj', 'Zygmunt', 'Juras', 'Driver', '815634369', 'Kielce, ul.Jerozolimy', '26-Jun-1985', '5000'),"+
					"('Driver', 'mslusarczyk', 'ms', 'Michal', 'Slusarczyk', 'Driver', '741547896', 'Pepice 21', '28-Jun-1995', '5000'),"+
					"('Driver', 'mwachala', 'mw', 'Michal', 'Wachala', 'Driver', '874256354', 'Kielce, ul.Sady', '16-Jun-1995', '5000'),"+
					"('Driver', 'pkuchar', 'pk', 'Pawel', 'Kuchar', 'Driver', '895456369', 'Kielce, ul.Marszalkowska', '16-Jun-1995', '5000'),"+
					"('Seller', 'jzarym', 'jz', 'Joanna', 'Zarym', 'Seller', '987145329', 'Warszawa, ul.Marszalkowska', '16-Jun-1995', '5000'),"+
					"('Seller', 'mjanuszek', 'mj', 'Monika', 'Januszek', 'Seller', '895412369', 'Warszawa, ul.Marszalkowska', '16-Jun-1995', '5000')";

					
					statement.execute(create);
			
			create = "INSERT INTO `bus` (`busName`, `seat`) VALUES"+ 
					 "('1', '25'),"+
					 "('2', '25'),"+
					 "('3', '35'),"+
					 "('4', '35'),"+
					 "('5', '35'),"+
					 "('6', '35'),"+
					 "('7', '60'),"+
					 "('8', '60'),"+
					 "('9', '60')";
			
			statement.execute(create);
			
			create = "INSERT INTO `busline` (`busLineName`, `busLineType`, `startStation`, `endStation`, `priceOneWay`, `priceMonthly`) VALUES"+
			"('Radom - Kielce', 'normalny', 'Radom', 'Kielce', '10.00', '100.00'),"+
			"('Kielce - Radom', 'normalny', 'Kielce', 'Radom', '10.00', '100.00'),"+
			"('Krakow - Kielce', 'normalny', 'Krakow', 'Kielce', '15.00', '150.00'),"+
			"('Kielce - Krakow', 'normalny', 'Kielce', 'Krakow', '15.00', '150.00'),"+
			"('Warszawa - Kielce', 'normalny', 'Warszawa', 'Kielce', '25.00', '250.00'),"+
			"('Kielce - Warszawa', 'normalny', 'Kielce', 'Warszawa', '25.00', '250.00'),"+
			"('Rzeszow - Kielce', 'normalny', 'Rzeszow', 'Kielce', '20.00', '200.00'),"+
			"('Kielce - Rzeszow', 'normalny', 'Kielce', 'Rzeszow', '20.00', '200.00'),"+
			"('Lublin - Kielce', 'normalny', 'Lublin', 'Kielce', '20.00', '200.00'),"+
			"('Kielce - Lublin', 'normalny', 'Kielce', 'Lublin', '20.00', '200.00')";
			
			statement.execute(create);
			
			create = "INSERT INTO `scheduler` (`depertureTime`, `arrivalTime`, `IdBus`, `IdBusLine`, `IdDriver`) VALUES"+
			"('05:00:00', '07:00:00', '1', '2', '4'),"+
			"('07:15:00', '09:15:00', '1', '1', '4'),"+
			"('09:30:00', '11:30:00', '1', '2', '4'),"+
			"('11:45:00', '13:45:00', '1', '1', '4'),"+
			"('14:00:00', '16:00:00', '1', '2', '4'),"+
			"('16:15:00', '18:15:00', '1', '1', '4'),"+
			"('18:30:00', '20:30:00', '1', '2', '4'),"+
			"('20:45:00', '22:45:00', '1', '1', '4'),"+
			"('05:00:00', '07:00:00', '2', '4', '5'),"+
			"('07:15:00', '09:15:00', '2', '3', '5'),"+
			"('09:30:00', '11:30:00', '2', '4', '5'),"+
			"('11:45:00', '13:45:00', '2', '3', '5'),"+
			"('14:00:00', '16:00:00', '2', '4', '5'),"+
			"('16:15:00', '18:15:00', '2', '3', '5'),"+
			"('18:30:00', '20:30:00', '2', '4', '5'),"+
			"('20:45:00', '22:45:00', '3', '5', '6'),"+
			"('05:00:00', '07:00:00', '3', '6', '6'),"+
			"('07:15:00', '09:15:00', '3', '5', '6'),"+
			"('09:30:00', '11:30:00', '3', '6', '6'),"+
			"('11:45:00', '13:45:00', '3', '5', '6'),"+
			"('14:00:00', '16:00:00', '3', '6', '6'),"+
			"('16:15:00', '18:15:00', '3', '5', '6'),"+
			"('18:30:00', '20:30:00', '3', '6', '6'),"+
			"('20:45:00', '22:45:00', '3', '5', '6')";
			
			statement.execute(create);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        
	}

	public static void main(String[] args) {
		
		try {
			new InsertDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
}
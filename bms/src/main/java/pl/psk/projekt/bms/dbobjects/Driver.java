package pl.psk.projekt.bms.dbobjects;

public class Driver extends Workers {

	private String driverID;
	private String idBus;
	private String idBusLine;
	
	public Driver(String workerId, String accountType, String username, String password, String name, String surname, String possition, String mobile, String address, String birthday, double salary, String driverID, String idBus, String idBusLine) {
		super(workerId, accountType, username, password, name, surname, possition, mobile, address, birthday, salary);
		this.driverID = driverID;
		this.idBus = idBus;
		this.idBusLine = idBusLine;
	}

	public String getDriverID() {
		return driverID;
	}

	public void setDriverID(String driverID) {
		this.driverID = driverID;
	}

	public String getIdBus() {
		return idBus;
	}

	public void setIdBus(String idBus) {
		this.idBus = idBus;
	}

	public String getIdBusLine() {
		return idBusLine;
	}

	public void setIdBusLine(String idBusLine) {
		this.idBusLine = idBusLine;
	}
	


}

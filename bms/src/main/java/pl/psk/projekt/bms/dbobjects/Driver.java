package pl.psk.projekt.bms.dbobjects;

public class Driver extends Workers {

	private int driverID;
	private String idBus;
	private String idBusLine;
	
	public Driver(int workerId, String accountType, String username, String password, String name, String surname, String possition, String mobile, String address, String birthday, double salary, int driverID, String idBus, String idBusLine) {
		super(workerId, accountType, username, password, name, surname, possition, mobile, address, birthday, salary);
		this.driverID = driverID;
		this.idBus = idBus;
		this.idBusLine = idBusLine;
	}

	public int getDriverID() {
		return driverID;
	}

	public void setDriverID(int driverID) {
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

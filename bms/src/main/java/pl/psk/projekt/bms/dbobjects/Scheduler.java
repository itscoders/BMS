package pl.psk.projekt.bms.dbobjects;

public class Scheduler {
	
	private int SchedulerRecordID;
    private String depertureTime;
    private String arrivalTime; 
    private String IdBus;
    private String IdDriver;
    private String IdLine;
    
	public Scheduler(int schedulerRecordID, String idBus, String idDriver, String idLine, String depertureTime, String arrivalTime) {
		super();
		SchedulerRecordID = schedulerRecordID;
		IdBus = idBus;
		IdDriver = idDriver;
		IdLine = idLine;
		this.depertureTime = depertureTime;
		this.arrivalTime = arrivalTime;
	}

	public int getSchedulerRecordID() {
		return SchedulerRecordID;
	}

	public void setSchedulerRecordID(int schedulerRecordID) {
		SchedulerRecordID = schedulerRecordID;
	}

	public String getIdBus() {
		return IdBus;
	}

	public void setIdBus(String idBus) {
		IdBus = idBus;
	}

	public String getIdDriver() {
		return IdDriver;
	}

	public void setIdDriver(String idDriver) {
		IdDriver = idDriver;
	}

	public String getIdLine() {
		return IdLine;
	}

	public void setIdLine(String idLine) {
		IdLine = idLine;
	}

	public String getDepertureTime() {
		return depertureTime;
	}

	public void setDepertureTime(String depertureTime) {
		this.depertureTime = depertureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
    
    

}

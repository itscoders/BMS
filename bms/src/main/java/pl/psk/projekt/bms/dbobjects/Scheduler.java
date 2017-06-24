package pl.psk.projekt.bms.dbobjects;

public class Scheduler {
	
	private int schedulerRecordID;
    private String depertureTime;
    private String arrivalTime; 
    private int idBus;
    private int idDriver;
    private int idLine;
    
	public Scheduler(int idBus, int idDriver, int idLine, String depertureTime, String arrivalTime) {
		super();
		
		this.idBus = idBus;
		this.idDriver = idDriver;
		this.idLine = idLine;
		this.depertureTime = depertureTime;
		this.arrivalTime = arrivalTime;
	}

	public Scheduler() {}

	public int getSchedulerRecordID() {
		return schedulerRecordID;
	}

	public void setSchedulerRecordID(int schedulerRecordID) {
		this.schedulerRecordID = schedulerRecordID;
	}

	public int getIdBus() {
		return idBus;
	}

	public void setIdBus(int idBus) {
		this.idBus = idBus;
	}

	public int getIdDriver() {
		return idDriver;
	}

	public void setIdDriver(int idDriver) {
		this.idDriver = idDriver;
	}

	public int getIdLine() {
		return idLine;
	}

	public void setIdLine(int idLine) {
		this.idLine = idLine;
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

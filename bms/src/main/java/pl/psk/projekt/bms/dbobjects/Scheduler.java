package pl.psk.projekt.bms.dbobjects;

public class Scheduler {
	/** Definicja  pola id rozkładu */
	private int schedulerRecordID;
	/** Definicja  pola czas odjazdu */
    private String depertureTime;
    /** Definicja  pola czas przyjazdu */
    private String arrivalTime; 
    /** Definicja  pola id busa */
    private int idBus;
    /** Definicja  pola id kierowcy */
    private int idDriver;
    /** Definicja  pola id linii */
    private int idLine;
    
    
    /**
     * Konstruktor  parametrowy bazujący na polach klasy
     * @param idBus przyjmuje id busa
     * @param idDriver przyjmuje id kierowcay
     * @param idLine przyjmuje id linii
     * @param depertureTime przyjmuje czas odjazdu
     * @param arrivalTime przyjmuje czas przyjazdu
     * 
     */
    
	public Scheduler(int idBus, int idDriver, int idLine, String depertureTime, String arrivalTime) {
		super();
		
		this.idBus = idBus;
		this.idDriver = idDriver;
		this.idLine = idLine;
		this.depertureTime = depertureTime;
		this.arrivalTime = arrivalTime;
	}
	
	/**
     * Konstruktor bez parametrowy  BusLine Bus używany w metodach gdzie nie potrzebujemy wypełniać całego obiektu do działania
     */
	
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

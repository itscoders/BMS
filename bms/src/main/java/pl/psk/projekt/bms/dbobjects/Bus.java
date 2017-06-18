package pl.psk.projekt.bms.dbobjects;

public class Bus {

    private String busID;
    private String busName;
    private int seat;
    
    
	public Bus(String busID, String busName, int seat) {
		
		this.busID = busID;
		this.busName = busName;
		this.seat = seat;
	}


	public String getBusID() {
		return busID;
	}


	public void setBusID(String busID) {
		this.busID = busID;
	}


	public String getBusName() {
		return busName;
	}


	public void setBusName(String busName) {
		this.busName = busName;
	}


	public int getSeat() {
		return seat;
	}


	public void setSeat(int seat) {
		this.seat = seat;
	}

	
}

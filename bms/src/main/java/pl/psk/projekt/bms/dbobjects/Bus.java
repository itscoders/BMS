package pl.psk.projekt.bms.dbobjects;

public class Bus {

    private int busID;
    private String busName;
    private int seat;
    
    public Bus(){}
    
	public Bus(String busName, int seat) {
		
		this.busName = busName;
		this.seat = seat;
	}


	public int getBusID() {
		return busID;
	}


	public void setBusID(int busID) {
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

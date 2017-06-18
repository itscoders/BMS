package pl.psk.projekt.bms.dbobjects;

public class BusLine {

    private int busLineID;
    private String busLineName;
    private String busLineType;
    private String startStation;
    private String endStation;
    private int  price;
    
	public BusLine(int busLineID, String busLineName, String busLineType, String startStation, String endStation, int price) {
		
		this.busLineID = busLineID;
		this.busLineName = busLineName;
		this.busLineType = busLineType;
		this.startStation = startStation;
		this.endStation = endStation;
		this.price = price;
	}

	public int getBusLineID() {
		return busLineID;
	}

	public void setBusLineID(int busLineID) {
		this.busLineID = busLineID;
	}

	public String getBusLineName() {
		return busLineName;
	}

	public void setBusLineName(String busLineName) {
		this.busLineName = busLineName;
	}

	public String getBusLineType() {
		return busLineType;
	}

	public void setBusLineType(String busLineType) {
		this.busLineType = busLineType;
	}

	public String getStartStation() {
		return startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	public String getEndStation() {
		return endStation;
	}

	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
    
	
    

    
    
	
}

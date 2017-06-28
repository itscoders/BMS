package pl.psk.projekt.bms.dbobjects;

/** KLASA BusLine - Zawiera w swoim ciele odpowiednik definicji tabeli BusLine z bazy danych
*/

public class BusLine {
	/** Definicja  pola id lini busa */
    private int busLineID;
    /** Definicja  pola nazwy lini */
    private String busLineName;
    /** Definicja  pola typu lini */
    private String busLineType;
    /** Definicja  pola stacji/miasta poczatkowego */
    private String startStation;
    /** Definicja  pola stacji/miasta końcowego */
    private String endStation;
    /** Definicja  pola ceny miesiecznej biletu */
    private double  priceMonthly;
    /** Definicja  pola ceny biletu jednikierunkowego */
    private double  priceOneWay;
    
    
    
    /**
     * Konstruktor  parametrowy bazujący na polach klasy
     * @param busLineName przyjmuje String z nazwa lini
     * @param busLineType przyjmuje String z typem lini
     * @param startStation przyjmuje String z stacją/miastem poczatkowym
     * @param endStation przyjmuje String z stacją/miastem końcowym
     * @param priceOneWay double z ceną biletu jednikierunkoweg
     * @param priceMonthly doublez z ceną miesieczną biletu
     * 
     */
    
	public BusLine(String busLineName, String busLineType, String startStation, String endStation,  double priceOneWay, double priceMonthly) {
		super();
		
		this.busLineName = busLineName;
		this.busLineType = busLineType;
		this.startStation = startStation;
		this.endStation = endStation;
		this.priceMonthly = priceMonthly;
		this.priceOneWay = priceOneWay;
	}
	
    /**
     * Konstruktor bez parametrowy  BusLine Bus używany w metodach gdzie nie potrzebujemy wypełniać całego obiektu do działania
     */
	public BusLine() {}
	
	
	/**
	 * 
	 * @return Metoda zwracająca id Lini
	 */
	public int getBusLineID() {
		return busLineID;
	}
	/**
	 * Metoda ustawiająca id Lini
	 * @param busLineID przyjmuje wartosc id do ustawienia
	 */
	public void setBusLineID(int busLineID) {
		this.busLineID = busLineID;
	}
	/**
	 * 
	 * @return Metoda zwracająca  nazwe Lini
	 */
	public String getBusLineName() {
		return busLineName;
	}
	/**
	 * Metoda ustawiająca nazwe Lini
	 * @param busLineName przyjmuje wartosc id do ustawienia
	 */
	public void setBusLineName(String busLineName) {
		this.busLineName = busLineName;
	}
	/**
	 * 
	 * @return Metoda zwracająca typ lini
	 */
	public String getBusLineType() {
		return busLineType;
	}
	/**
	 * Metoda ustawiająca typ lini
	 * @param busLineType przyjmuje wartosc z typem lini
	 */
	public void setBusLineType(String busLineType) {
		this.busLineType = busLineType;
	}
	/**
	 * 
	 * @return Metoda zwracająca startStation
	 */
	public String getStartStation() {
		return startStation;
	}
	/**
	 * Metoda ustawiająca startStation
	 * @param startStation przyjmuje wartosc ze stacją początkową 
	 */
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	/**
	 * 
	 * @return Metoda zwracająca startStation
	 */
	public String getEndStation() {
		return endStation;
	}
	/**
	 * Metoda ustawiająca endStation
	 * @param endStation przyjmuje wartosc ze stacja koncowa
	 */
	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}
	/**
	 * 
	 * @return Metoda zwracająca priceMonthly
	 */
	public double getPriceMonthly() {
		return priceMonthly;
	}
	/**
	 * Metoda ustawiająca priceMonthly
	 * @param priceMonthly przyjmuje wartosc do ustawienia ceny miesiecznej
	 */
	public void setPriceMonthly(double priceMonthly) {
		this.priceMonthly = priceMonthly;
	}
	/**
	 * 
	 * @return Metoda zwracająca priceOneWay
	 */
	public double getPriceOneWay() {
		return priceOneWay;
	}
	/**
	* Metoda ustawiająca priceOneWay
	 * @param priceOneWay przyjmuje wartosc do ustawienia ceny jednorazowej
	 */
	public void setPriceOneWay(double priceOneWay) {
		this.priceOneWay = priceOneWay;
	}
    
	
    

    
    
	
}

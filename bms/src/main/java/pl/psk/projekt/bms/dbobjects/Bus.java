package pl.psk.projekt.bms.dbobjects;

/** KLASA Bus - Zawiera w swoim ciele odpowiednik definicji tabeli Bus z bazy danych
*/

public class Bus {
	
	
	/** Definicja  pola id busa */
    private int busID;
    /** Definicja  pola id nazwy */
    private String busName;
    /** Definicja  pola z iloscia miejsc w busie*/
    private int seat;
    
    
    /**
     * Konstruktor bez parametrowy  KLASY Bus używany w metodach gdzie nie potrzebujemy wypełniać całego obiektu do działania
     */
    public Bus(){}
    
    /**
     * Konstruktor  parametrowy bazujący na polach klasy
     * @param busName przyjmuje String z nazwa busa
     * @param seat przyjmuje int z iloscia miejsc
     */
    
	public Bus(String busName, int seat) {
		
		this.busName = busName;
		this.seat = seat;
	}

	/**
	 * 
	 * @return Metoda zwracająca id Busa
	 */
	public int getBusID() {
		return busID;
	}

	/**
	 * Metoda ustawiająca id busa
	 * @param busID przyjmuje wartosc id do ustawienia
	 */
	public void setBusID(int busID) {
		this.busID = busID;
	}

	/**
	 * 
	 * @return Metoda zwracająca nazwe busa
	 */
	public String getBusName() {
		return busName;
	}

	/**
	 * Metoda ustawiająca nazwe busa
	 * @param busName przyjmuje wartosc String z nazwa
	 */
	public void setBusName(String busName) {
		this.busName = busName;
	}

	/**
	 * 
	 * @return Metoda zwracająca ilosc miejsc w busie
	 */
	
	public int getSeat() {
		return seat;
	}

	/**
	 * Metoda ustawiająca ilosc miejsc
	 * @param seat przyjmuje wartosc int z iloscia miejsc
	 */
	public void setSeat(int seat) {
		this.seat = seat;
	}

	
}

package pl.psk.projekt.bms.dbobjects;

public class Transaction {
	/** Definicja  pola id transakcji */
	private int transactionId;
	/** Definicja  pola kwoty transakcji */
    private String discount;
    /** Definicja  pola sposob platnosci */
    private String payment;
    /** Definicja  pola data */
    private String date;
    /** Definicja  pola id rozkladu */
    private int idScheduler;
    /** Definicja  pola id sprzedawcy*/
    private int idSeller;
    /** Definicja  pola id kupujacego */
    private int idBuyer;
    
    
    /**
     * Konstruktor  parametrowy bazujący na polach klasy
     * @param transactionId przyjmuje id transakcji
     * @param discount przyjmuje cene biletu
     * @param payment przyjmuje informacjie o typie płatności
     * @param date przyjmuje date płatności
     * @param idScheduler przyjmuje id rozkładu
     * @param idSeller przyjmuje id sprzedawcy
     * @param idBuyer przyjmuje id kupującego
     * 
     */
    
	public Transaction(int transactionId, String discount, String payment, String date, int idScheduler, int idSeller,
			int idBuyer) {
		super();
		this.transactionId = transactionId;
		this.discount = discount;
		this.payment = payment;
		this.date = date;
		this.idScheduler = idScheduler;
		this.idSeller = idSeller;
		this.idBuyer = idBuyer;
	}
	
    /**
     * Konstruktor  parametrowy bazujący na polach klasy
     * @param discount przyjmuje cene biletu
     * @param payment przyjmuje informacjie o typie płatności
     * @param date przyjmuje date płatności
     * @param idScheduler przyjmuje id rozkładu
     * @param idSeller przyjmuje id sprzedawcy
     * @param idBuyer przyjmuje id kupującego
     * 
     */
	
	public Transaction(String discount, String payment, String date, int idScheduler, int idSeller, int idBuyer) {
		super();
		this.discount = discount;
		this.payment = payment;
		this.date = date;
		this.idScheduler = idScheduler;
		this.idSeller = idSeller;
		this.idBuyer = idBuyer;
	}
	
	 /**
     * Konstruktor bez parametrowy  BusLine Bus używany w metodach gdzie nie potrzebujemy wypełniać całego obiektu do działania
     */
	public Transaction() {
		super();
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getIdScheduler() {
		return idScheduler;
	}
	public void setIdScheduler(int idScheduler) {
		this.idScheduler = idScheduler;
	}
	public int getIdSeller() {
		return idSeller;
	}
	public void setIdSeller(int idSeller) {
		this.idSeller = idSeller;
	}
	public int getIdBuyer() {
		return idBuyer;
	}
	public void setIdBuyer(int idBuyer) {
		this.idBuyer = idBuyer;
	}
    
    



}

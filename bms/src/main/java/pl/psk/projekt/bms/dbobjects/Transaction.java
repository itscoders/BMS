package pl.psk.projekt.bms.dbobjects;

public class Transaction {
	
	private int transactionId;
    private String discount;
    private String payment;
    private String date;
    private int idScheduler;
    private int idSeller;
    private int idBuyer;
    
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
	public Transaction(String discount, String payment, String date, int idScheduler, int idSeller, int idBuyer) {
		super();
		this.discount = discount;
		this.payment = payment;
		this.date = date;
		this.idScheduler = idScheduler;
		this.idSeller = idSeller;
		this.idBuyer = idBuyer;
	}
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

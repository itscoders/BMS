package pl.psk.projekt.bms.dbobjects;

public class Transaction {
	
	private int transactionId;
    private String discount;
    private int number; 
    private double price;
    private String payment;
    private String idScheduler;
    private String idTicket;
    private String idBuyer;
    


	public Transaction() {
		
	}
	

	public Transaction(int transactionId, String discount, int number, double price, String payment,
			String idScheduler, String idTicket, String idBuyer) {
		super();
		this.transactionId = transactionId;
		this.discount = discount;
		this.number = number;
		this.price = price;
		this.payment = payment;
		this.idScheduler = idScheduler;
		this.idTicket = idTicket;
		this.idBuyer = idBuyer;
	}
	
	
	public Transaction(String discount, int number, double price, String payment, String idScheduler, String idTicket, String idBuyer) {
		super();
		this.discount = discount;
		this.number = number;
		this.price = price;
		this.payment = payment;
		this.idScheduler = idScheduler;
		this.idTicket = idTicket;
		this.idBuyer = idBuyer;
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





	public int getNumber() {
		return number;
	}





	public void setNumber(int number) {
		this.number = number;
	}





	public double getPrice() {
		return price;
	}





	public void setPrice(double price) {
		this.price = price;
	}





	public String getPayment() {
		return payment;
	}





	public void setPayment(String payment) {
		this.payment = payment;
	}





	public String getIdScheduler() {
		return idScheduler;
	}





	public void setIdScheduler(String idScheduler) {
		this.idScheduler = idScheduler;
	}


	public String getIdTicket() {
		return idTicket;
	}


	public void setIdTicket(String idTicket) {
		this.idTicket = idTicket;
	}


	public String getIdBuyer() {
		return idBuyer;
	}


	public void setIdBuyer(String idBuyer) {
		this.idBuyer = idBuyer;
	}
	
	

}

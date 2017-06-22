package pl.psk.projekt.bms.dbobjects;

public class Ticket {
	
	private int ticketId;
    private String type; 
    private double price;
    private String idLine;
    
    
	public Ticket() {
		
	}
	

	public Ticket(String type, double price, String idLine) {
		super();
		this.type = type;
		this.price = price;
		this.idLine = idLine;
	}



	public Ticket(int ticketId, String type, double price, String idLine) {
		super();
		this.ticketId = ticketId;
		this.type = type;
		this.price = price;
		this.idLine = idLine;
	}


	public int getTicketId() {
		return ticketId;
	}


	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getIdLine() {
		return idLine;
	}


	public void setIdLine(String idLine) {
		this.idLine = idLine;
	}


}

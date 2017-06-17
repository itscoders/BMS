package pl.psk.projekt.bms.dbobjects;

public class Workers {

	
	private String userName;
	private String password;
	private String accountType;
	private String workerId;
	
	
	public Workers() {
		super();
	}

	public Workers(String workerId, String userName, String password, String accountType){
		this.workerId = workerId ;
		this.userName = userName;
		this.accountType = accountType;
		this.password = password;
	}
	
	public String getWorkerId() {
		return workerId;
	}

	public String getUserName(){
		return userName;
	}
	
	public String getAccountType(){
		return accountType;
	}
	
	public String getPassword(){
		return password;
	}
	
}
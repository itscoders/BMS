package pl.psk.projekt.bms.dbobjects;

public class Workers {

	private String workerId;
	private String accountType;
	private String username;
	private String password;
	private String possition;
	private String mobile;
	private String address;
	private String birthday;
    private double salary;
    


	public Workers( String accountType, String username, String password, String possition,String mobile, String address, String birthday, double salary) {
		super();
		this.accountType = accountType;
		this.username = username;
		this.password = password;
		this.possition = possition;
		this.mobile = mobile;
		this.address = address;
		this.birthday = birthday;
		this.salary = salary;
	}

	public String getWorkerId() {
		return workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPossition() {
		return possition;
	}

	public void setPossition(String possition) {
		this.possition = possition;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
    
	
}
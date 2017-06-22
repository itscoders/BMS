package pl.psk.projekt.bms.dbobjects;

public class Buyer {

	private int buyerId;
	private String name;
	private String surname;
	private String birthday;
	private String email;
	private String mobile;
	private String street;
	private String houseNumber;
	private String postCode;
	private String city;

	
	public Buyer() {
		
	}
	
	

	public Buyer(String name, String surname, String birthday, String email, String mobile, String street,
			String houseNumber, String postCode, String city) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.email = email;
		this.mobile = mobile;
		this.street = street;
		this.houseNumber = houseNumber;
		this.postCode = postCode;
		this.city = city;
	}


	public Buyer(int buyerId, String name, String surname, String birthday, String email, String mobile, String street,
			String houseNumber, String postCode, String city) {
		super();
		this.buyerId = buyerId;
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.email = email;
		this.mobile = mobile;
		this.street = street;
		this.houseNumber = houseNumber;
		this.postCode = postCode;
		this.city = city;
	}



	public int getBuyerId() {
		return buyerId;
	}



	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public String getBirthday() {
		return birthday;
	}



	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public String getHouseNumber() {
		return houseNumber;
	}



	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}



	public String getPostCode() {
		return postCode;
	}



	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}
	
	
	
}
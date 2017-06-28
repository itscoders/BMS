package pl.psk.projekt.bms.dbobjects;

/** KLASA Buyer - Zawiera w swoim ciele odpowiednik definicji tabeli Buyer z bazy danych
*/


public class Buyer {
	/** Definicja  pola id kupujacego*/
	private int buyerId;
	/** Definicja  pola imie kupujacego*/
	private String name;
	/** Definicja  pola  nazwisko kupujacego */
	private String surname;
	/** Definicja  pola  data urodzenia kupujacego */
	private String birthday;
	/** Definicja  pola mail kupujacego */
	private String email;
	/** Definicja  pola telefon kupujacego*/
	private String mobile;
	/** Definicja  pola ulica */
	private String street;
	/** Definicja  pola numer domu */
	private String houseNumber;
	/** Definicja  pola kodu pocztowego */
	private String postCode;
	/** Definicja  pola miasta */
	private String city;
	/** Definicja  pola numeru identyfikującego */
	private String insuranceNumber;

    /**
     * Konstruktor bez parametrowy  KLASY Buyer używany w metodach gdzie nie potrzebujemy wypełniać całego obiektu do działania
     */
	public Buyer() {
		
	}
	
    /**
     * Konstruktor  parametrowy bazujący na polach klasy
     * @param name przyjmuje String z imieniem kupujacego
     * @param surname przyjmuje String z nazwiskiem kupujacego
     * @param birthday przyjmuje String z data urodzenia kupujacego
     * @param email przyjmuje String z mailem kupujacego
     * @param mobile przyjmuje String z numerem telefonu kupujacego
     * @param street przyjmuje String z ulicą
     * @param houseNumber przyjmuje String z numerem domu
     * @param postCode przyjmuje String z Kodem Pocztowym
     * @param city przyjmuje String z miastem
     * @param insuranceNumber przyjmuje String z peselem/numerem idetyfikacyjnym
     * 
     */

	public Buyer(String name, String surname, String birthday, String email, String mobile, String street,
			String houseNumber, String postCode, String city, String insuranceNumber) {
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
		this.insuranceNumber = insuranceNumber;
	}

	
    /**
     * Konstruktor  parametrowy bazujący na polach klasy
     * @param buyerId przyjmuje id kupującego
     * @param name przyjmuje String z imieniem kupujacego
     * @param surname przyjmuje String z nazwiskiem kupujacego
     * @param birthday przyjmuje String z data urodzenia kupujacego
     * @param email przyjmuje String z mailem kupujacego
     * @param mobile przyjmuje String z numerem telefonu kupujacego
     * @param street przyjmuje String z ulicą
     * @param houseNumber przyjmuje String z numerem domu
     * @param postCode przyjmuje String z Kodem Pocztowym
     * @param city przyjmuje String z miastem
     * @param insuranceNumber przyjmuje String z peselem/numerem idetyfikacyjnym
     * 
     */
	public Buyer(int buyerId, String name, String surname, String birthday, String email, String mobile, String street,
			String houseNumber, String postCode, String city, String insuranceNumber) {
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
		this.insuranceNumber = insuranceNumber;
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



	public String getInsuranceNumber() {
		return insuranceNumber;
	}



	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}
	
	
	
}
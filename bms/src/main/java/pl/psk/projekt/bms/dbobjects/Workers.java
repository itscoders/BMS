package pl.psk.projekt.bms.dbobjects;

/** KLASA Workers - Zawiera w swoim ciele odpowiednik definicji tabeli Workers z bazy danych
*/

public class Workers {
	/** Definicja  pola id pracownika*/
	private int workerId;
	/** Definicja  pola typu pracownika*/
	private String accountType;
	/** Definicja  pola nazwy usera pracownika */
	private String username;
	/** Definicja  pola hasła do konta pracownika*/
	private String password;
	/** Definicja  pola imienia pracownika*/
	private String name;
	/** Definicja  pola nazwiska pracownika*/
	private String surname;
	/** Definicja  pola maila pracownika*/
	private String email;
	/** Definicja  pola telefonu*/
	private String mobile;
	/** Definicja  pola adresu pracownika*/
	private String address;
	/** Definicja  pola daty urodzenia*/
	private String birthday;
	/** Definicja  pola wyplaty pracownika*/
	private double salary;
	
    /**
     * Konstruktor bez parametrowy  KLASY Buyer używany w metodach gdzie nie potrzebujemy wypełniać całego obiektu do działania
     */
	public Workers() {

	}
	
	
    /**
     * Konstruktor  parametrowy bazujący na polach klasy
     * @param accountType przyjmuje typ konta/stanowisko
     * @param username	przyjmuje login do konta
     * @param password  przyjmuje hasło do konta
     * @param name przyjmuje String z imieniem pracownika
     * @param surname przyjmuje String z nazwiskiem pracownika
     * @param birthday przyjmuje String z data urodzenia pracownika
     * @param email przyjmuje String z mailem pracownika
     * @param mobile przyjmuje String z numerem telefonu pracownika
     * @param address przyjmuje adress pracownika
     * @param salary pensja pracownika
     * 
     */
	
	public Workers(String accountType, String username, String password, String name, String surname, String email,
			String mobile, String address, String birthday, double salary) {
		super();
		this.accountType = accountType;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.birthday = birthday;
		this.salary = salary;

	}
	
	
	
    /**
     * Konstruktor  parametrowy bazujący na polach klasy
     * @param workerId przyjmuje id pracownika
     * @param accountType przyjmuje typ konta/stanowisko
     * @param username	przyjmuje login do konta
     * @param password  przyjmuje hasło do konta
     * @param name przyjmuje String z imieniem pracownika
     * @param surname przyjmuje String z nazwiskiem pracownika
     * @param birthday przyjmuje String z data urodzenia pracownika
     * @param email przyjmuje String z mailem pracownika
     * @param mobile przyjmuje String z numerem telefonu pracownika
     * @param address przyjmuje adress pracownika
     * @param salary pensja pracownika
     * 
     */
	
	public Workers(int workerId, String accountType, String username, String password, String name, String surname,
			String email, String mobile, String address, String birthday, double salary) {
		super();
		this.workerId = workerId;
		this.accountType = accountType;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.birthday = birthday;
		this.salary = salary;
	}

	public int getWorkerId() {
		return workerId;
	}

	public void setWorkerId(int workerId) {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
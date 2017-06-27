package pl.psk.projekt.bms.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Component;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;
import pl.psk.projekt.bms.dbobjects.Buyer;
import pl.psk.projekt.bms.jdbc.BuyerJDBC;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Klasa NewBuerWindow zawiera komponenty do stworzenia UI okna startowego
 * aplikacji oraz metody obsługujące akcje występujące w oknie. Klasa
 * StartWindow dziedziczy po klasie {@link javax.swing.JFrame} celem stworzenia
 * obiektu okna. Ponadto rozszerzona jest ona poprzez interfejs
 * {@link java.awt.event.ActionListener} celem zdefiniowania akcji po wciśnięciu
 * przycisku.
 * 
 * @author Paweł Pawelec i Kamil Świąder
 * @see javax.swing.JFrame
 * @see java.awt.event.ActionListener
 */
public class NewBuyerWindow extends JFrame implements ActionListener {

	/** Zmienna określająca unikalny numer w celu serializacji. */
	private static final long serialVersionUID = 1L;

	/** Deklaracja obiektu klasy JPanel. */
	private JPanel contentPane;
	/** Deklaracja obiektu klasy JTextField. */
	private JTextField nameField;
	/** Deklaracja obiektu klasy JTextField. */
	private JTextField postCodeField;
	/** Deklaracja obiektu klasy JTextField. */
	private JTextField emailField;
	/** Deklaracja obiektu klasy JTextField. */
	private JTextField mobilePhoneField;
	/** Deklaracja obiektu klasy JTextField. */
	private JTextField streetField;
	/** Deklaracja obiektu klasy JTextField. */
	private JTextField cityField;
	/** Deklaracja obiektu klasy JTextField. */
	private JTextField surnameField;
	/** Deklaracja obiektu klasy JTextField. */
	private JTextField houseNumberField;
	/** Deklaracja obiektu klasy JTextField. */
	private JTextField textFieldInsuranceNumber;
	/** Deklaracja obiektu klasy JLabel. */
	private JLabel labelCity;
	/** Deklaracja obiektu klasy JLabel. */
	private JLabel labelInsuranceNumber;
	 /** Deklaracja obiektu klasy JTable. */
	private JTable tableFilter;
	/** Deklaracja obiektu klasy JDefaultTableModel. */
	private DefaultTableModel modelFilter;
	/** Deklaracja obiektu klasy JButton. */
	private JButton addButton;
	/** Deklaracja obiektu klasy JDateChooser. */
	private JDateChooser birthdayField;

	/** Deklaracja obiektu klasy PreparedStatement. */
	private PreparedStatement preparedStatement;
	/** Deklaracja obiektu klasy Connection. */
	private Connection connect;
	/** Deklaracja obiektów klasy ResultSet. */
	private ResultSet rs;


	/**
	 * Konstruktor klasy NewBuyerWindow odpowiedzialny za
	 * inicializację komponentów biblioteki Swing. Komponenty definiowane:
	 * Jlabel, JButton, JPanel, JDateChooser, JTextField, JTable, JFrame - dla tych
	 * komponentów ustawiane są wymiary, fonty, kolory. Dodatkowo dla komponentu JTable zastosowany jest model tabeli - DefaultTableModel, 
	 * Komponenty zostały rozmieszczone przy pomocy GroupLayout.
	 * W konstruktorze przy pomocy zmiennej connect nawiązywane jest połączenie z bazą bms_db, 
	 * preparedStatement pozwala na wykonanie zapytania do bazy, a 'rs' na wyświetlenie wyników zapytania.
	 * 
	 * @see JDateChooser
	 * @see JTextField
	 * @see JTable
	 * @see JPanel
	 * @see JButton
	 * @see JFrame
	 * @see JLabel
	 */
	public NewBuyerWindow() {
		setResizable(false);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Add New Buyer - Bus Management/Transaction");
		setBounds(new Rectangle(100, 100, 700, 500));
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setFocusable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		nameField = new JTextField();
		nameField.setColumns(10);

		JLabel labelName = new JLabel("Name:");

		JLabel labelBirthday = new JLabel("Birthday");

		JLabel labelMobilePhone = new JLabel("Mobile Phone:");

		JLabel labelHouseNumber = new JLabel("Apt/House Number:");

		JLabel labelSurname = new JLabel("Surname:");

		JLabel labelEmail = new JLabel("Email:");

		postCodeField = new JTextField();
		postCodeField.setColumns(10);

		JLabel labelStreet = new JLabel("Street:");

		JLabel labelPostCode = new JLabel("Post Code:");

		emailField = new JTextField();
		emailField.setColumns(10);

		labelCity = new JLabel("City:");

		houseNumberField = new JTextField();
		houseNumberField.setColumns(10);

		addButton = new JButton("Add New Buyer");
		addButton.setBackground(Color.LIGHT_GRAY);
		addButton.addActionListener(this);

		JScrollPane scrollPane = new JScrollPane();

		birthdayField = new JDateChooser();

		mobilePhoneField = new JTextField();
		mobilePhoneField.setColumns(10);

		streetField = new JTextField();
		streetField.setColumns(10);

		cityField = new JTextField();
		cityField.setColumns(10);

		surnameField = new JTextField();
		surnameField.setColumns(10);

		labelInsuranceNumber = new JLabel("Insurance Number:");

		textFieldInsuranceNumber = new JTextField();
		textFieldInsuranceNumber.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane
				.createParallelGroup(
						Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_contentPane
												.createSequentialGroup().addComponent(scrollPane,
														GroupLayout.PREFERRED_SIZE, 668, GroupLayout.PREFERRED_SIZE)
												.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane
												.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
														.createSequentialGroup().addGroup(gl_contentPane
																.createParallelGroup(Alignment.LEADING)
																.addComponent(labelName).addComponent(labelBirthday))
														.addGap(18)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addComponent(
																		nameField, GroupLayout.PREFERRED_SIZE, 202,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(birthdayField, GroupLayout.DEFAULT_SIZE,
																		202, Short.MAX_VALUE)))
												.addGroup(gl_contentPane.createSequentialGroup()
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addComponent(labelHouseNumber).addComponent(labelCity)
																.addComponent(labelMobilePhone))
														.addGap(18)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addComponent(cityField, GroupLayout.DEFAULT_SIZE, 202,
																		Short.MAX_VALUE)
																.addComponent(mobilePhoneField,
																		GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
																.addComponent(houseNumberField, 202, 202, 202))))
										.addGap(28)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addComponent(labelPostCode).addComponent(labelSurname)
																.addComponent(labelEmail).addComponent(labelStreet))
														.addGap(18)
														.addGroup(gl_contentPane
																.createParallelGroup(Alignment.LEADING, false)
																.addComponent(surnameField).addComponent(streetField)
																.addComponent(emailField).addComponent(postCodeField)))
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(labelInsuranceNumber, GroupLayout.PREFERRED_SIZE,
																95, GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(textFieldInsuranceNumber,
																GroupLayout.PREFERRED_SIZE, 202,
																GroupLayout.PREFERRED_SIZE)))
										.addGap(30))))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(280)
						.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(304, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(labelName)
								.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(labelSurname).addComponent(surnameField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(labelBirthday).addComponent(labelEmail).addComponent(emailField,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(birthdayField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
								.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(labelStreet).addComponent(streetField, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(labelPostCode)
										.addComponent(postCodeField, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(houseNumberField, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(labelInsuranceNumber).addComponent(textFieldInsuranceNumber,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(labelMobilePhone).addComponent(mobilePhoneField,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
										.addGap(18).addComponent(labelHouseNumber).addGap(24)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(labelCity).addComponent(cityField,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))))
						.addGap(18).addComponent(addButton).addGap(18)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE).addContainerGap()));
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL,
				new Component[] { nameField, postCodeField, emailField, houseNumberField });
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] { labelName, labelBirthday, labelMobilePhone,
				labelHouseNumber, labelSurname, labelEmail, labelStreet, labelPostCode, labelCity });

		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			preparedStatement = connect.prepareStatement("SELECT * FROM Buyer");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rs = preparedStatement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		modelFilter = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);

		tableFilter = new JTable();
		tableFilter.setModel(modelFilter);

		scrollPane.setViewportView(tableFilter);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * Przesłonięta metoda służąca do określania zachowania aplikacji po
	 * kliknięciu na dany komponent przez użytkownika. W metodzie tej określono
	 * działanie dla przycisków znajdujących się w oknie do dodawania nowego kupującego. 
	 * W przypadku kliknięcia na przycisk 'addButton' pobierane są dane z pól formularza okna NewBuyerWindow, 
	 * a następnie zostaje dodany rekord do bazy danych bms_db z pobranymi danymi.
	 * Tworzony jest obiekt klasy BuyerJDBC oraz klasy Buyer. 
	 * Na obiekcie klasy BuyerJDBC wykonywana jest metoda 'createBuyer'. 
	 * Następnie wywoływana jest metoda do odświerzenia tabeli JTable - updateTable. 
	 * Na końcu wyłączaane są przyciski do edytowania i usuwania rekordów z tabeli. 
	 * W przypadku kliknięcia na przycisk 'editButton' pobierane są dane z pól formularza okna NewBuyerWindow, 
	 * a następnie zostaje zaktualizowany rekord w bazie danych bms_db z pobranymi danymi.
	 * Tworzony jest obiekt klasy BuyerJDBC oraz klasy Buyer. 
	 * Na obiekcie klasy BuyerJDBC wykonywana jest metoda 'updateBuyer'. 
	 * Następnie wywoływana jest metoda do odświerzenia tabeli JTable - updateTable. 
	 * Na końcu wyłączaane są przyciski do edytowania i usuwania rekordów z tabeli. 
	 * W przypadku kliknięcia na przycisk 'deleteButton' pobierane są dane z pól formularza okna NewBuyerWindow, 
	 * a następnie zostaje usunięty rekord z bazy danych bms_db na podtsawie pobranych danych. 
	 * Tworzony jest obiekt klasy BuyerJDBC oraz klasy Buyer. 
	 * Na obiekcie klasy BuyerJDBC wykonywana jest metoda 'deleteBuyer'. 
	 * Następnie wywoływana jest metoda do odświerzenia tabeli JTable - updateTable. 
	 * Na końcu wyłączaane są przyciski do edytowania i usuwanai rekordów z tabeli. 
	 * Do informowani użytkownika oraz wyświetlania okien dialogowych
	 * wykorzystane zostały komponenty JOptionPane.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addButton) {
			String name = nameField.getText();
			String surname = surnameField.getText();
			String birthday = ((JTextField) birthdayField.getDateEditor().getUiComponent()).getText();
			String email = emailField.getText();
			String mobilePhone = mobilePhoneField.getText();
			String street = streetField.getText();
			String houseNumber = houseNumberField.getText();
			String postCode = postCodeField.getText();
			String city = cityField.getText();
			String in = textFieldInsuranceNumber.getText();

			boolean valid = true;
			String invalid = "";

			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
				Date dd = sdf.parse(birthday);
				Calendar cal = Calendar.getInstance();
				String today = sdf.format(cal.getTime());
				Date tod = sdf.parse(today);

				if (dd.after(tod))
					valid = false;
				invalid = "Birthday Cannot be furture date";
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Fail to compare Date");
			}


			if (mobilePhone.length() != 9) {
				valid = false;
				invalid = "Handphone number should have 9 number";
			}
			for (int a = 0; a < mobilePhone.length(); a++) {
				char temp1 = mobilePhone.charAt(a);
				if (Character.isLetter(temp1) == true) {
					valid = false;
					invalid = "Please insert proper handphone number";
				}
			}

			if (valid) {
				try {
					try {
						BuyerJDBC wj = new BuyerJDBC();
						Buyer w = new Buyer(name, surname, birthday, email, mobilePhone, street, houseNumber, postCode,
								city, in);
						wj.createBuyer(w);
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(this, "New Buyer: " + name + surname + " had added.");

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage());
				}
			} else
				JOptionPane.showMessageDialog(null, invalid);

			Update_table();
		}

	}

	/** Metoda odpowiedzialna za odświeżanie tabeli Jtable z danymi.
	 * 
	 * Metoda jest typu void - nie zwraca żadnej wartości. 
	 */
	private void Update_table() {
		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");

			preparedStatement = connect.prepareStatement("SELECT * FROM Buyer");
			rs = preparedStatement.executeQuery();
			tableFilter.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {

			try {
				rs.close();
				preparedStatement.close();

			} catch (Exception e) {

			}
		}
	}


}

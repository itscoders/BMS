package pl.psk.projekt.bms.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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
import javax.swing.RowFilter;
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
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;
import pl.psk.projekt.bms.dbobjects.Buyer;
import pl.psk.projekt.bms.jdbc.BuyerJDBC;


import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Klasa BuyerWindow zawiera komponenty do stworzenia UI okna startowego
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
public class BuyerWindow extends JFrame implements ActionListener {

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
    private JTextField filterField;
    /** Deklaracja obiektu klasy JTextField. */
	private JTextField houseNumberField;
	/** Deklaracja obiektu klasy JTextField. */
    private JTextField textFieldInsuranceNumber;
    /** Deklaracja obiektu klasy JLabel. */
	private JLabel labelCity;
	/** Deklaracja obiektu klasy JLabel. */
    private JLabel label;
    /** Deklaracja obiektu klasy JLabel. */
    private JLabel labelInsuranceNumber;
    /** Deklaracja obiektu klasy JTable. */
	private JTable tableFilter;
	/** Deklaracja obiektu klasy JDefaultTableModel. */
    private DefaultTableModel modelFilter;
    /** Deklaracja obiektu klasy JButton. */
	private JButton addButton;
	/** Deklaracja obiektu klasy JButton. */
	private JButton editButton;
	/** Deklaracja obiektu klasy JButton. */
	private JButton deleteButton;
	/** Deklaracja obiektu klasy JDateChooser. */
	private JDateChooser birthdayField;
	
	/** Deklaracja obiektu klasy PreparedStatement. */
	private PreparedStatement  preparedStatement;
	/** Deklaracja obiektu klasy Connection. */
	private Connection connect;
	/** Deklaracja obiektów klasy ResultSet. */
	private ResultSet rs;


	/**
	 * Konstruktor klasy BuyerWindow odpowiedzialny za
	 * inicializację komponentów biblioteki Swing. Komponenty definiowane:
	 * Jlabel, JButton, JPanel, JDateChooser, JTextField, JTable, JFrame - dla tych
	 * komponentów ustawiane są wymiary, fonty, kolory. Dodatkowo dla komponentu JTable zastosowany jest model tabeli - DefaultTableModel, 
	 * Dla JTable dodana jest niego metoda 'keyReleased' służąca do określania zachowania po zwolnieniuu klawisza, gdzie wywoływana jest metoda'filter' 
	 * oraz metoda 'mouseClicked' służąca do określania zachowania po kliknięciu myszką, gdzie wywoływana jest metoda 'tableFilterMouseClicked'.
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
	public BuyerWindow() {
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
		setTitle("Buyer - Bus Management");
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

		addButton = new JButton("Add");
		addButton.setBackground(Color.LIGHT_GRAY);
		addButton.addActionListener(this);

		editButton = new JButton("Edit");
		editButton.setBackground(Color.LIGHT_GRAY);
		editButton.addActionListener(this);
		editButton.setEnabled(false);

		deleteButton = new JButton("Delete");
		deleteButton.setBackground(Color.LIGHT_GRAY);
		deleteButton.addActionListener(this);
		deleteButton.setEnabled(false);

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
		
		filterField = new JTextField();
		filterField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = filterField.getText();
				filter(query);
			}
		});
		filterField.setColumns(10);
		
		label = new JLabel("Search Buyer:");
		
		textFieldInsuranceNumber = new JTextField();
		textFieldInsuranceNumber.setColumns(10);
		
		labelInsuranceNumber = new JLabel("Insurance Number :");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(addButton)
							.addGap(18)
							.addComponent(editButton)
							.addGap(18)
							.addComponent(deleteButton)
							.addGap(219))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(labelName)
										.addComponent(labelBirthday))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
										.addComponent(birthdayField, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(labelHouseNumber)
										.addComponent(labelCity)
										.addComponent(labelMobilePhone))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(cityField, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
										.addComponent(mobilePhoneField, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
										.addComponent(houseNumberField, 202, 202, 202))))
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(labelPostCode)
									.addComponent(labelSurname)
									.addComponent(labelEmail)
									.addComponent(labelStreet))
								.addComponent(labelInsuranceNumber, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(surnameField)
									.addComponent(streetField)
									.addComponent(emailField)
									.addComponent(postCodeField))
								.addComponent(textFieldInsuranceNumber, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
							.addGap(30))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(filterField, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
							.addGap(194))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelName)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelSurname)
						.addComponent(surnameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(labelBirthday)
							.addComponent(labelEmail)
							.addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(birthdayField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelStreet)
								.addComponent(streetField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelPostCode)
								.addComponent(postCodeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(houseNumberField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldInsuranceNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelInsuranceNumber)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelMobilePhone)
								.addComponent(mobilePhoneField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(labelHouseNumber)
							.addGap(24)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelCity)
								.addComponent(cityField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(addButton)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(editButton)
							.addComponent(deleteButton)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(label))
						.addComponent(filterField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {labelName, labelBirthday, labelMobilePhone, labelHouseNumber, labelSurname, labelEmail, labelStreet, labelPostCode, labelCity});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {nameField, postCodeField, emailField, houseNumberField});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {addButton, editButton, deleteButton});

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

		tableFilter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableFilterMouseClicked(e);
			}
		});
		tableFilter.setModel(modelFilter);
		
		scrollPane.setViewportView(tableFilter);
		scrollPane.setHorizontalScrollBarPolicy(
	                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		contentPane.setLayout(gl_contentPane);
	}


	/**
	 * Metoda służąca do określania zachowania aplikacji po
	 * kliknięciu na rekord w tabeli JTable. W metodzie tej ustawiono przycisk do edycji oraz usuwania rekordów z bazy na widoczne. 
	 * W przypadku kliknięcia na rekord w tabeli JTable dane z tego rekordu ustawiane są w polach formularzu okna BuyerWindow.
	 * @param e MouseEvent
	 */
	private void tableFilterMouseClicked(MouseEvent e) {
		editButton.setEnabled(true);
		deleteButton.setEnabled(true);

		int index = tableFilter.getSelectedRow();

		String name = tableFilter.getValueAt(index, 1).toString();
		String surname = tableFilter.getValueAt(index, 2).toString();
		String birthday = tableFilter.getValueAt(index, 3).toString();
		String email = tableFilter.getValueAt(index, 4).toString();
		String mobilePhone = tableFilter.getValueAt(index, 5).toString();
		String street = tableFilter.getValueAt(index, 6).toString();
		String houseNumber = tableFilter.getValueAt(index, 7).toString();
		String postCode = tableFilter.getValueAt(index, 8).toString();
		String city = tableFilter.getValueAt(index, 9).toString();
		String in = tableFilter.getValueAt(index, 10).toString();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(birthday);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		nameField.setText(name);
		surnameField.setText(surname);
		birthdayField.setDate(date);
		emailField.setText(email);
		mobilePhoneField.setText(mobilePhone);
		streetField.setText(street);
		houseNumberField.setText(houseNumber);
		postCodeField.setText(postCode);
		cityField.setText(city);
		textFieldInsuranceNumber.setText(in);
	}

	/**
	 * Przesłonięta metoda służąca do określania zachowania aplikacji po
	 * kliknięciu na dany komponent przez użytkownika. W metodzie tej określono
	 * działanie dla przycisków znajdujących się w oknie do zarządzania kupującymi. 
	 * W przypadku kliknięcia na przycisk 'addButton' pobierane są dane z pól formularza okna BuyerWindow, 
	 * a następnie zostaje dodany rekord do bazy danych bms_db z pobranymi danymi.
	 * Tworzony jest obiekt klasy BuyerJDBC oraz klasy Buyer. 
	 * Na obiekcie klasy BuyerJDBC wykonywana jest metoda 'createBuyer'. 
	 * Następnie wywoływana jest metoda do odświerzenia tabeli JTable - updateTable. 
	 * Na końcu wyłączaane są przyciski do edytowania i usuwania rekordów z tabeli. 
	 * W przypadku kliknięcia na przycisk 'editButton' pobierane są dane z pól formularza okna BuyerWindow, 
	 * a następnie zostaje zaktualizowany rekord w bazie danych bms_db z pobranymi danymi.
	 * Tworzony jest obiekt klasy BuyerJDBC oraz klasy Buyer. 
	 * Na obiekcie klasy BuyerJDBC wykonywana jest metoda 'updateBuyer'. 
	 * Następnie wywoływana jest metoda do odświerzenia tabeli JTable - updateTable. 
	 * Na końcu wyłączaane są przyciski do edytowania i usuwania rekordów z tabeli. 
	 * W przypadku kliknięcia na przycisk 'deleteButton' pobierane są dane z pól formularza okna BuyerWindow, 
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
						Buyer w = new Buyer(name, surname, birthday, email, mobilePhone, street, houseNumber, postCode, city,in);
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
		
			updateTable();
			editButton.setEnabled(false);
			deleteButton.setEnabled(false);
		}

		if (e.getSource() == editButton) {
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
				int value = Integer.parseInt(tableFilter.getValueAt(tableFilter.getSelectedRow(), 0).toString());
				try {
					try {
						BuyerJDBC wj = new BuyerJDBC();
						Buyer w = new Buyer(name, surname, birthday, email, mobilePhone, street, houseNumber, postCode, city, in);
						w.setBuyerId(value);
						wj.updateBuyer(w);
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(this, "New Buyer: " + name + surname + " was edited.");

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage());
				}
			} else
				JOptionPane.showMessageDialog(null, invalid);
		
			updateTable();
			editButton.setEnabled(false);
			deleteButton.setEnabled(false);
		}

		if (e.getSource() == deleteButton) {

			String name = nameField.getText();
			String surname = surnameField.getText();
			int value = Integer.parseInt(tableFilter.getValueAt(tableFilter.getSelectedRow(), 0).toString());

			try {
				BuyerJDBC wj = new BuyerJDBC();
				Buyer w = new Buyer();
				w.setBuyerId(value);
				wj.deleteBuyer(w);
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "Buyer: " + name + surname + " was deleted.");

			updateTable();
			editButton.setEnabled(false);
			deleteButton.setEnabled(false);

		}

	}
	
	
	/** Metoda odpowiedzialna za odświeżanie tabeli Jtable z danymi.
	 * 
	 * Metoda jest typu void - nie zwraca żadnej wartości. 
	 */
	private void updateTable() {
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


	/** Metoda odpowiedzialna za filtrowanie danych w tabeli Jtable.
	 * 
	 * @param query - parametr String - tekst jako wyznacznik filtrowania.
	 * 
	 * Metoda jest typu void - nie zwraca żadnej wartości. 
	 */
	private void filter(String query) {

		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(modelFilter);
		tableFilter.setRowSorter(trs);

		trs.setRowFilter(RowFilter.regexFilter(query));
	}
    
}


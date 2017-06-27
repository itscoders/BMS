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
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import net.proteanit.sql.DbUtils;
import pl.psk.projekt.bms.component.ComboKeyHandler;
import pl.psk.projekt.bms.dbobjects.Scheduler;
import pl.psk.projekt.bms.jdbc.SchedulerJDBC;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.SpinnerDateModel;
import java.awt.ComponentOrientation;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * Klasa ScheduleWindow zawiera komponenty do stworzenia UI okna startowego
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
public class ScheduleWindow extends JFrame implements ActionListener {

	/** Zmienna określająca unikalny numer w celu serializacji. */
	private static final long serialVersionUID = 1L;

	/** Deklaracja obiektu klasy JPanel. */
	private JPanel contentPane;
	 /** Deklaracja obiektu klasy JTable. */
	private JTable tableFilter;
	/** Deklaracja obiektu klasy JButton. */
	private JButton addButton;
	/** Deklaracja obiektu klasy JButton. */
	private JButton editButton;
	/** Deklaracja obiektu klasy JButton. */
	private JButton deleteButton;
	/** Deklaracja obiektu klasy JTextField. */
	private JTextField filterField;
	/** Deklaracja obiektu klasy JDefaultTableModel. */
	private DefaultTableModel modelFilter;
	/** Deklaracja obiektu klasy JComboBox. */
	private JComboBox<String> comboBoxDriverId;
	/** Deklaracja obiektu klasy JComboBox. */
	private JComboBox<String> comboBoxBusId; 
	/** Deklaracja obiektu klasy JComboBox. */
	private JComboBox<String> comboBoxLineId;
	/** Deklaracja obiektu klasy DefaultComboBoxModel. */
	private DefaultComboBoxModel<String> comboModelDriverIdD;
	/** Deklaracja obiektu klasy DefaultComboBoxModel. */
	private DefaultComboBoxModel<String> comboModelBusLineD;
	/** Deklaracja obiektu klasy DefaultComboBoxModel. */
	private DefaultComboBoxModel<String> comboModelBusD;
	/** Deklaracja obiektu klasy JSpinner. */
	private JSpinner spinnerDepartureTimeStart;
	/** Deklaracja obiektu klasy JSpinner. */
	private JSpinner spinnerArrivalTimeStop;
	/** Deklaracja obiektu klasy JSpinner. */
	private JSpinner.DateEditor de_spinnerArrivalTime;
	/** Deklaracja obiektu klasy JSpinner. */
	private JSpinner.DateEditor de_spinnerDepartureTime;
	
	/** Deklaracja obiektu klasy PreparedStatement. */
	private PreparedStatement preparedStatement;
	/** Deklaracja obiektu klasy Connection. */
	private Connection connect;
	/** Deklaracja obiektów klasy ResultSet. */
	private ResultSet rs;
	

	/**
	 * Konstruktor klasy BuyerWindow odpowiedzialny za
	 * inicializację komponentów biblioteki Swing. Komponenty definiowane:
	 * Jlabel, JButton, JPanel, JComboBox, JSpinner JTextField, JTable, JFrame - dla tych
	 * komponentów ustawiane są wymiary, fonty, kolory. Dodatkowo dla komponentu JTable zastosowany jest model tabeli - DefaultTableModel, 
	 * Dla JTable dodana jest niego metoda 'keyReleased' służąca do określania zachowania po zwolnieniuu klawisza, gdzie wywoływana jest metoda'filter'.
	 * Dla komponentu JSpinner wykorzystana została klasa ComboKeyHandler pozwalająca na podpowiadanie sugestti podczas wpisywania teksu.
	 * Komponenty zostały rozmieszczone przy pomocy GroupLayout.
	 * W konstruktorze przy pomocy zmiennej connect nawiązywane jest połączenie z bazą bms_db, 
	 * preparedStatement pozwala na wykonanie zapytania do bazy, a 'rs' na wyświetlenie wyników zapytania.
	 * 
	 * @see JSpinner
	 * @see JComboBox
	 * @see JTextField
	 * @see JTable
	 * @see JPanel
	 * @see JButton
	 * @see JFrame
	 * @see JLabel
	 */
	public ScheduleWindow() {
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
		setTitle("Schedule - Bus Management");
		setBounds(new Rectangle(100, 100, 700, 400));
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setFocusable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel labelDepartureTime = new JLabel("Departure Time");

		JLabel labelIdBus = new JLabel("Bus ID:");

		JLabel labelIdDriver = new JLabel("Driver ID:");

		JLabel labelArrivalTime = new JLabel("Arrival Time:");

		JLabel labelIdLine = new JLabel("Line ID:");
		
		fillComboBoxDriver();
		comboBoxDriverId = new JComboBox<String>();
		comboBoxDriverId.setModel(comboModelDriverIdD);
		JTextField textDriver = (JTextField) comboBoxDriverId.getEditor().getEditorComponent();
		textDriver.setText("");
		textDriver.addKeyListener(new ComboKeyHandler(comboBoxDriverId));
		
		
		fillComboBoxBus();
		comboBoxBusId = new JComboBox<String>();
		comboBoxBusId.setModel(comboModelBusD);
		JTextField textBus = (JTextField) comboBoxBusId.getEditor().getEditorComponent();
		textBus.setText("");
		textBus.addKeyListener(new ComboKeyHandler(comboBoxBusId));
		
		fillComboBoxBusLine();
		comboBoxLineId = new JComboBox<String>();
		comboBoxLineId.setModel(comboModelBusLineD);
		JTextField textLine = (JTextField) comboBoxLineId.getEditor().getEditorComponent();
		textLine.setText("");
		textLine.addKeyListener(new ComboKeyHandler(comboBoxLineId));

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

		Date startDate = new Date();
		SpinnerDateModel sm = new SpinnerDateModel(startDate, null, null, Calendar.HOUR_OF_DAY);
		spinnerDepartureTimeStart = new JSpinner(sm);
		spinnerDepartureTimeStart.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		de_spinnerDepartureTime = new JSpinner.DateEditor(spinnerDepartureTimeStart, "HH:mm:ss");
		spinnerDepartureTimeStart.setEditor(de_spinnerDepartureTime);

		Date stopDate = new Date();
		SpinnerDateModel sm2 = new SpinnerDateModel(stopDate, null, null, Calendar.HOUR_OF_DAY);
		spinnerArrivalTimeStop = new JSpinner(sm2);
		de_spinnerArrivalTime = new JSpinner.DateEditor(spinnerArrivalTimeStop, "HH:mm:ss");
		spinnerArrivalTimeStop.setEditor(de_spinnerArrivalTime);
		
		filterField = new JTextField();
		filterField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = filterField.getText();
				filter(query);
			}
		});
		filterField.setColumns(10);
		
		
		
		JLabel lblSearchSchedule = new JLabel("Search Schedule:");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(240, Short.MAX_VALUE)
					.addComponent(addButton)
					.addGap(18)
					.addComponent(editButton)
					.addGap(18)
					.addComponent(deleteButton)
					.addGap(219))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(173)
					.addComponent(lblSearchSchedule, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(filterField, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(203, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(labelDepartureTime)
										.addComponent(labelIdBus))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(spinnerDepartureTimeStart, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
										.addComponent(comboBoxBusId, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(labelIdDriver)
									.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
									.addComponent(comboBoxDriverId, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelArrivalTime)
								.addComponent(labelIdLine))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(spinnerArrivalTimeStop, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
								.addComponent(comboBoxLineId, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(30))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelDepartureTime)
						.addComponent(labelArrivalTime)
						.addComponent(spinnerDepartureTimeStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinnerArrivalTimeStop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelIdBus)
						.addComponent(labelIdLine)
						.addComponent(comboBoxBusId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxLineId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelIdDriver)
						.addComponent(comboBoxDriverId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(addButton)
						.addComponent(editButton)
						.addComponent(deleteButton))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(filterField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblSearchSchedule)))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {comboBoxDriverId, comboBoxBusId});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {labelDepartureTime, labelIdBus, labelIdDriver, labelArrivalTime, labelIdLine});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {addButton, editButton, deleteButton});
		
		try {
			connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");
			preparedStatement = connect.prepareStatement("SELECT * FROM SCHEDULER");
			rs = preparedStatement.executeQuery();
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}

		
		modelFilter = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);
		
		tableFilter = new JTable();

		tableFilter.setModel(modelFilter);
		scrollPane.setViewportView(tableFilter);
		contentPane.setLayout(gl_contentPane);
	}
	

	/**
	 * Przesłonięta metoda służąca do określania zachowania aplikacji po
	 * kliknięciu na dany komponent przez użytkownika. W metodzie tej określono
	 * działanie dla przycisków znajdujących się w oknie do zarządzania rozkładem. 
	 * W przypadku kliknięcia na przycisk 'addButton' wywołane zostają metody comparDriver, 
	 * comparBus, comparBusLine oraz pobierane są dane z pól formularza okna ScheduleWindow, 
	 * a następnie zostaje dodany rekord do bazy danych bms_db z pobranymi danymi.
	 * Tworzony jest obiekt klasy SchedulerJDBC oraz klasy Scheduler. 
	 * Na obiekcie klasy SchedulerJDBC wykonywana jest metoda 'addSchedulerRecord'. 
	 * Następnie wywoływana jest metoda do odświerzenia tabeli JTable - updateTable. 
	 * Na końcu wyłączaane są przyciski do edytowania i usuwania rekordów z tabeli. 
	 * W przypadku kliknięcia na przycisk 'editButton' wywołane zostają metody comparDriver, 
	 * comparBus, comparBusLine oraz pobierane są dane z pól formularza okna ScheduleWindow, 
	 * a następnie zostaje zaktualizowany rekord w bazie danych bms_db z pobranymi danymi.
	 * Tworzony jest obiekt klasy SchedulerJDBC oraz klasy Scheduler. 
	 * Na obiekcie klasy SchedulerJDBC wykonywana jest metoda 'updateSchedulerRecord'. 
	 * Następnie wywoływana jest metoda do odświerzenia tabeli JTable - updateTable. 
	 * Na końcu wyłączaane są przyciski do edytowania i usuwania rekordów z tabeli. 
	 * W przypadku kliknięcia na przycisk 'deleteButton' pobierane są dane z pól formularza okna ScheduleWindow, 
	 * a następnie zostaje usunięty rekord z bazy danych bms_db na podtsawie pobranych danych. 
	 * Tworzony jest obiekt klasy SchedulerJDBC oraz klasy Scheduler. 
	 * Na obiekcie klasy SchedulerJDBC wykonywana jest metoda 'deleteSchedulerRecord'. 
	 * Następnie wywoływana jest metoda do odświerzenia tabeli JTable - updateTable. 
	 * Na końcu wyłączaane są przyciski do edytowania i usuwanai rekordów z tabeli. 
	 * Do informowani użytkownika oraz wyświetlania okien dialogowych
	 * wykorzystane zostały komponenty JOptionPane.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addButton) {
			
			int driver = comparDriver();
			int bus = comparBus();
			int line = comparBusLine();
			String startTime = de_spinnerDepartureTime.getFormat().format(spinnerDepartureTimeStart.getValue());
			String stopTime = de_spinnerArrivalTime.getFormat().format(spinnerArrivalTimeStop.getValue());
			
			
			try {
				SchedulerJDBC sj = new SchedulerJDBC();
				Scheduler s = new Scheduler(bus,driver,line, startTime,stopTime);
				sj.addSchedulerRecord(s);
				updateTable();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "New Schedule had added.");
			editButton.setEnabled(false);
			deleteButton.setEnabled(false);
			updateTable();
			
		}

		if (e.getSource() == editButton) {
			
				
				int value = Integer.parseInt(tableFilter.getValueAt(tableFilter.getSelectedRow(), 0).toString());
				
				int driver = comparDriver();
				int bus = comparBus();
				int line = comparBusLine();
				String startTime = de_spinnerDepartureTime.getFormat().format(spinnerDepartureTimeStart.getValue());
				String stopTime = de_spinnerArrivalTime.getFormat().format(spinnerArrivalTimeStop.getValue());
				
				
				try {
					SchedulerJDBC sj = new SchedulerJDBC();
					Scheduler s = new Scheduler(bus,driver,line,startTime,stopTime);
					s.setSchedulerRecordID(value);
					
					sj.updateSchedulerRecord(s);
					updateTable();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(this, "Schedule was edited.");
				editButton.setEnabled(false);
				deleteButton.setEnabled(false);
				updateTable();
			
		}

		if (e.getSource() == deleteButton) {
			
			int value = Integer.parseInt(tableFilter.getValueAt(tableFilter.getSelectedRow(), 0).toString());
			
			try {
				SchedulerJDBC sj = new SchedulerJDBC();
				sj.deleteSchedulerRecord(value);
				updateTable();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "Schedule was deleted.");
			editButton.setEnabled(false);
			deleteButton.setEnabled(false);
			updateTable();
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

				preparedStatement = connect.prepareStatement("SELECT * FROM SCHEDULER");
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
		
		
		/** Metoda odpowiedzialna za wypełnianie danymi kierowców z bazy danych bms_db komponentu JComboBox przez model DefaultComboBoxModel.
		 * 
		 * Metoda jest typu void - nie zwraca żadnej wartości. 
		 */
		private void fillComboBoxDriver() {
			try {
				connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");

				preparedStatement = connect.prepareStatement("SELECT * FROM Workers WHERE accountType = 'Driver'");
				rs = preparedStatement.executeQuery();
				comboModelDriverIdD = new DefaultComboBoxModel<String>();
				while (rs.next()) {
					String name ="id:"+" "+ rs.getString("workerId")+"  "+ rs.getString("name") + " - " + rs.getString("surname");
					comboModelDriverIdD.addElement(name);
				}
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
		
		
		/** Metoda odpowiedzialna za pobranie ID kierowcy rekordu z wybranego pola formularzu okna ScheduleWindow, gdzie ID jest kluczem obcym tabeli Schedule w bazie.
		 * Wynik zwraca na podstawaie porównywania danych z bazy danych bms_db.
		 * 
		 * @return 	numer ID kierowcy jeśli porównane dane są takie same
		 */
		private int comparDriver() {
			try {
				connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");

				preparedStatement = connect.prepareStatement("SELECT * FROM Workers WHERE accountType = 'Driver'");
				rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					String name ="id:"+" "+ rs.getString("workerId")+"  "+ rs.getString("name") + " - " + rs.getString("surname");
					if (name.equals(comboModelDriverIdD.getSelectedItem().toString())) {
						return Integer.parseInt(rs.getString("workerId"));
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			} finally {

				try {
					rs.close();
					preparedStatement.close();

				} catch (Exception e) {

				}
			}
			return 0;
		}
		
		/** Metoda odpowiedzialna za wypełnianie danymi busów z bazy danych bms_db komponentu JComboBox przez model DefaultComboBoxModel.
		 * 
		 * Metoda jest typu void - nie zwraca żadnej wartości. 
		 */
		private void fillComboBoxBus() {
			try {
				connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");

				preparedStatement = connect.prepareStatement("SELECT * FROM BUS");
				rs = preparedStatement.executeQuery();
				comboModelBusD = new DefaultComboBoxModel<String>();
				while (rs.next()) {
					String name ="id:"+" "+ rs.getString("busID")+" - "+ rs.getString("busName");
					comboModelBusD.addElement(name);
				}
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
		
		/** Metoda odpowiedzialna za pobranie ID busa rekordu z wybranego pola formularzu okna ScheduleWindow, gdzie ID jest kluczem obcym tabeli Schedule w bazie.
		 * Wynik zwraca na podstawaie porównywania danych z bazy danych bms_db.
		 * 
		 * @return 	numer ID busa jeśli porównane dane są takie same
		 */
		private int comparBus() {
			try {
				connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");

				preparedStatement = connect.prepareStatement("SELECT * FROM Bus");
				rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					String name ="id:"+" "+ rs.getString("busID")+" - "+ rs.getString("busName");
				
					if (name.equals(comboModelBusD.getSelectedItem().toString())) {
						return Integer.parseInt(rs.getString("busID"));
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			} finally {

				try {
					rs.close();
					preparedStatement.close();

				} catch (Exception e) {

				}
			}
			return 0;
		}
		
		
		/** Metoda odpowiedzialna za wypełnianie danymi linii busów z bazy danych bms_db komponentu JComboBox przez model DefaultComboBoxModel.
		 * 
		 * Metoda jest typu void - nie zwraca żadnej wartości. 
		 */
		private void fillComboBoxBusLine() {
			try {
				connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");

				preparedStatement = connect.prepareStatement("SELECT * FROM BUSLINE");
				rs = preparedStatement.executeQuery();
				comboModelBusLineD = new DefaultComboBoxModel<String>();
				while (rs.next()) {
					String name = "id:"+" "+ rs.getString("busLineID")+ " "+rs.getString("busLineName") + " - " + rs.getString("busLineType");
					comboModelBusLineD.addElement(name);
				}
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
		
		/** Metoda odpowiedzialna za pobranie ID linii busa rekordu z wybranego pola formularzu okna ScheduleWindow, gdzie ID jest kluczem obcym tabeli Schedule w bazie.
		 * Wynik zwraca na podstawaie porównywania danych z bazy danych bms_db.
		 * 
		 * @return 	numer ID linii busa jeśli porównane dane są takie same
		 */
		private int comparBusLine() {
			try {
				connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");

				preparedStatement = connect.prepareStatement("SELECT * FROM BUSLINE");
				rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					String name = "id:"+" "+ rs.getString("busLineID")+" "+rs.getString("busLineName") + " - " + rs.getString("busLineType");
					
					if (name.equals(comboModelBusLineD.getSelectedItem().toString())) {
						return Integer.parseInt(rs.getString("busLineID"));
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			} finally {

				try {
					rs.close();
					preparedStatement.close();

				} catch (Exception e) {

				}
			}
			return 0;
		}
}

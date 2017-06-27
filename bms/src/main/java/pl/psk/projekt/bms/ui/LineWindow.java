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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import net.proteanit.sql.DbUtils;
import pl.psk.projekt.bms.dbobjects.BusLine;
import pl.psk.projekt.bms.jdbc.BusLineJDBC;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Klasa LineWindow zawiera komponenty do stworzenia UI okna startowego
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
public class LineWindow extends JFrame implements ActionListener {

	/** Zmienna określająca unikalny numer w celu serializacji. */
	private static final long serialVersionUID = 1L;

	/** Deklaracja obiektu klasy JPanel. */
	private JPanel contentPane;
	/** Deklaracja obiektu klasy JTextField. */
	private JTextField lineNameField;
	/** Deklaracja obiektu klasy JTextField. */
	private JTextField startStationField;
	/** Deklaracja obiektu klasy JTextField. */
	private JTextField stopStationField;
	/** Deklaracja obiektu klasy JTextField. */
    private JTextField textFieldPirceOneWay;
    /** Deklaracja obiektu klasy JTextField. */
    private JTextField textFieldPriceMonthly;
    /** Deklaracja obiektu klasy JTextField. */
    private JTextField filterField;
    /** Deklaracja obiektu klasy JTable. */
	private JTable tableFilter;
	/** Deklaracja obiektu klasy JButton. */
	private JButton addButton;
	/** Deklaracja obiektu klasy JButton. */
	private JButton editButton;
	/** Deklaracja obiektu klasy JButton. */
	private JButton deleteButton;
	/** Deklaracja obiektu klasy JDefaultTableModel. */
    private DefaultTableModel modelFilter;
    /** Deklaracja obiektu klasy JLabel. */
    private JLabel lblSearchLine;
    /** Deklaracja obiektu klasy JComboBox. */
	private JComboBox<String> comboBoxType;
	
	/** Deklaracja obiektu klasy PreparedStatement. */
	private PreparedStatement  preparedStatement;
	/** Deklaracja obiektu klasy Connection. */
	private Connection connect;
	/** Deklaracja obiektów klasy ResultSet. */
	private ResultSet rs;


	/**
	 * Konstruktor klasy LineWindow odpowiedzialny za
	 * inicializację komponentów biblioteki Swing. Komponenty definiowane:
	 * Jlabel, JButton, JPanel, JComboBox, JTextField, JTable, JFrame - dla tych
	 * komponentów ustawiane są wymiary, fonty, kolory. Dodatkowo dla komponentu JTable zastosowany jest model tabeli - DefaultTableModel, 
	 * Dla JTable dodana jest niego metoda 'keyReleased' służąca do określania zachowania po zwolnieniuu klawisza, gdzie wywoływana jest metoda'filter' 
	 * oraz metoda 'mouseClicked' służąca do określania zachowania po kliknięciu myszką, gdzie wywoływana jest metoda 'tableFilterMouseClicked'.
	 * Komponenty zostały rozmieszczone przy pomocy GroupLayout.
	 * W konstruktorze przy pomocy zmiennej connect nawiązywane jest połączenie z bazą bms_db, 
	 * preparedStatement pozwala na wykonanie zapytania do bazy, a 'rs' na wyświetlenie wyników zapytania.
	 * 
	 * @see JComboBox
	 * @see JTextField
	 * @see JTable
	 * @see JPanel
	 * @see JButton
	 * @see JFrame
	 * @see JLabel
	 */
	public LineWindow() {
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
		
		setTitle("Line - Bus Management");
		setBounds(new Rectangle(100, 100, 700, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		lineNameField = new JTextField();
		lineNameField.setColumns(10);

		JLabel labelLineName = new JLabel("Line Name:");

		JLabel labelStartStation = new JLabel("Start Station:");

		startStationField = new JTextField();
		startStationField.setColumns(10);

		JLabel labelType = new JLabel("Type:");

		JLabel labelStopStation = new JLabel("Stop Station:");

		stopStationField = new JTextField();
		stopStationField.setColumns(10);

		 comboBoxType = new JComboBox<String>();
		 String e ="normal";
		 comboBoxType.addItem(e);
		 e ="express";
		 comboBoxType.addItem(e);

		addButton = new JButton("Add");
		addButton.addActionListener(this);

		editButton = new JButton("Edit");
		editButton.addActionListener(this);

		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		
		editButton.setEnabled(false);
		deleteButton.setEnabled(false);

		JScrollPane scrollPane = new JScrollPane();
		
		filterField = new JTextField();
		filterField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = filterField.getText();
				filter(query);
			}
		});
		filterField.setColumns(10);
		
		lblSearchLine = new JLabel("Search Line:");
		
		textFieldPirceOneWay = new JTextField();
		textFieldPirceOneWay.setColumns(10);
		
		textFieldPriceMonthly = new JTextField();
		textFieldPriceMonthly.setColumns(10);
		
		JLabel labelPriceMonthly = new JLabel("Price Monthly:");
		
		JLabel labelPirceOneWay = new JLabel("Pirce OneWay:");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelLineName)
								.addComponent(labelStartStation)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(labelPirceOneWay, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lineNameField, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
									.addComponent(startStationField, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
								.addComponent(textFieldPirceOneWay, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelType)
								.addComponent(labelStopStation)
								.addComponent(labelPriceMonthly, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldPriceMonthly, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(comboBoxType, 0, 212, Short.MAX_VALUE)
									.addComponent(stopStationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addGap(32))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(182, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblSearchLine, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(filterField, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(addButton)
							.addGap(18)
							.addComponent(editButton)
							.addGap(18)
							.addComponent(deleteButton)))
					.addGap(231))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelLineName)
						.addComponent(lineNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelType)
						.addComponent(comboBoxType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(startStationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelStartStation)
						.addComponent(labelStopStation)
						.addComponent(stopStationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldPriceMonthly, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelPriceMonthly)
						.addComponent(labelPirceOneWay)
						.addComponent(textFieldPirceOneWay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(addButton)
						.addComponent(editButton)
						.addComponent(deleteButton))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSearchLine)
						.addComponent(filterField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {lineNameField, startStationField, stopStationField});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {labelLineName, labelStartStation, labelType, labelStopStation});
		
		try {
			connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");
				preparedStatement = connect.prepareStatement("SELECT * FROM BUSLINE");
				rs = preparedStatement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		modelFilter = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);
		
		tableFilter = new JTable();
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
	 * W przypadku kliknięcia na rekord w tabeli JTable dane z tego rekordu ustawiane są w polach formularzu okna LineWindow.
	 */
	private void tableFilterMouseClicked(MouseEvent e) {
		int index = tableFilter.getSelectedRow();
		
		editButton.setEnabled(true);
		deleteButton.setEnabled(true);
		
		String busLineName = tableFilter.getValueAt(index, 1).toString();
		String busLineType = tableFilter.getValueAt(index, 2).toString();
		String startStation = tableFilter.getValueAt(index, 3).toString();
		String stopStation = tableFilter.getValueAt(index, 4).toString();
		String pirceOneWay = tableFilter.getValueAt(index, 5).toString();
		String priceMonthly = tableFilter.getValueAt(index, 6).toString();
				
		lineNameField.setText(busLineName);
		comboBoxType.setSelectedItem(busLineType);
		startStationField.setText(startStation);
		stopStationField.setText(stopStation);
		textFieldPirceOneWay.setText(pirceOneWay);
		textFieldPriceMonthly.setText(priceMonthly);	
	}
	
	
	/**
	 * Przesłonięta metoda służąca do określania zachowania aplikacji po
	 * kliknięciu na dany komponent przez użytkownika. W metodzie tej określono
	 * działanie dla przycisków znajdujących się w oknie do zarządzania liniami busów. 
	 * W przypadku kliknięcia na przycisk 'addButton' pobierane są dane z pól formularza okna LineWindow, 
	 * a następnie zostaje dodany rekord do bazy danych bms_db z pobranymi danymi.
	 * Tworzony jest obiekt klasy BusLineJDBC oraz klasy BusLine. 
	 * Na obiekcie klasy BusLineJDBC wykonywana jest metoda 'createBusLine'. 
	 * Następnie wywoływana jest metoda do odświerzenia tabeli JTable - updateTable. 
	 * Na końcu wyłączaane są przyciski do edytowania i usuwania rekordów z tabeli. 
	 * W przypadku kliknięcia na przycisk 'editButton' pobierane są dane z pól formularza okna LineWindow, 
	 * a następnie zostaje zaktualizowany rekord w bazie danych bms_db z pobranymi danymi.
	 * Tworzony jest obiekt klasy BusLineJDBC oraz klasy BusLine. 
	 * Na obiekcie klasy BusLineJDBC wykonywana jest metoda 'updateBusLine'. 
	 * Następnie wywoływana jest metoda do odświerzenia tabeli JTable - updateTable. 
	 * Na końcu wyłączaane są przyciski do edytowania i usuwania rekordów z tabeli. 
	 * W przypadku kliknięcia na przycisk 'deleteButton' pobierane są dane z pól formularza okna LineWindow, 
	 * a następnie zostaje usunięty rekord z bazy danych bms_db na podtsawie pobranych danych. 
	 * Tworzony jest obiekt klasy BusLineJDBC oraz klasy BusLine. 
	 * Na obiekcie klasy BusLineJDBC wykonywana jest metoda 'deleteBusLine'. 
	 * Następnie wywoływana jest metoda do odświerzenia tabeli JTable - updateTable. 
	 * Na końcu wyłączaane są przyciski do edytowania i usuwanai rekordów z tabeli. 
	 * Do informowani użytkownika oraz wyświetlania okien dialogowych
	 * wykorzystane zostały komponenty JOptionPane.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addButton) {
			String busLineName = lineNameField.getText();
			String startStation = startStationField.getText();
			String stopStation = stopStationField.getText();
			double pirceOneWay = Double.parseDouble(textFieldPirceOneWay.getText());
			double priceMonthly = Double.parseDouble(textFieldPriceMonthly.getText());
			String busLineType = (String) comboBoxType.getSelectedItem();
			
			
			try {
				BusLineJDBC bj = new BusLineJDBC();
				BusLine b = new BusLine(busLineName, busLineType, startStation, stopStation, pirceOneWay, priceMonthly);
				bj.createBusLine(b);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "New line :" + busLineName + " had added.");
			
			updateTable();
			editButton.setEnabled(false);
			deleteButton.setEnabled(false);
		}

		if (e.getSource() == editButton) {
			
			int value = Integer.parseInt(tableFilter.getValueAt(tableFilter.getSelectedRow(), 0).toString());
			String busLineName = lineNameField.getText();
			String startStation = startStationField.getText();
			String stopStation = stopStationField.getText();
			double pirceOneWay = Double.parseDouble(textFieldPirceOneWay.getText());
			double priceMonthly = Double.parseDouble(textFieldPriceMonthly.getText());
			String busLineType = (String) comboBoxType.getSelectedItem();
			
			try {
				BusLineJDBC bj = new BusLineJDBC();
				BusLine b = new BusLine(busLineName, busLineType, startStation, stopStation, pirceOneWay, priceMonthly);
				b.setBusLineID(value);
				bj.updateBusLine(b);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "Line :" + busLineName + " was edited.");
			updateTable();
			editButton.setEnabled(false);
			deleteButton.setEnabled(false);
		}

		if (e.getSource() == deleteButton) {
			String busLineName = lineNameField.getText();
			int value = Integer.parseInt(tableFilter.getValueAt(tableFilter.getSelectedRow(), 0).toString());
			
			try {
				BusLineJDBC bj = new BusLineJDBC();
				BusLine b = new BusLine();
				b.setBusLineID(value);
				bj.deleteBusLine(b);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "Line :" + busLineName + " was deleted.");
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

				preparedStatement = connect.prepareStatement("SELECT * FROM BUSLINE");
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

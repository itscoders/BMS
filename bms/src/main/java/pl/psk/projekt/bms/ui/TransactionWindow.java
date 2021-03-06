package pl.psk.projekt.bms.ui;

import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Rectangle;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Component;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.JSpinner;
import java.awt.ComponentOrientation;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import pl.psk.projekt.bms.component.ComboKeyHandler;
import pl.psk.projekt.bms.component.Mail;
import pl.psk.projekt.bms.dbobjects.Transaction;
import pl.psk.projekt.bms.dbobjects.Workers;
import pl.psk.projekt.bms.jdbc.TransactionJDBC;

import javax.swing.DefaultComboBoxModel;

/**
 * Klasa TransactionWindow zawiera komponenty do stworzenia UI okna startowego
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
public class TransactionWindow extends JFrame implements ActionListener {

	/** Zmienna określająca unikalny numer w celu serializacji. */
	private static final long serialVersionUID = 1L;

	/** Deklaracja obiektu klasy JPanel. */
	private JPanel contentPane;
	/** Deklaracja obiektu klasy JButton. */
	private JButton addButton;
	/** Deklaracja obiektu klasy JButton. */
	private JButton deleteButton;
	/** Deklaracja obiektu klasy JButton. */
	private JButton newBuyerButton;
	/** Deklaracja obiektu klasy JSpinner. */
	private JSpinner spinnerDepartureTime;
	/** Deklaracja obiektu klasy JSpinner. */
	private JSpinner.DateEditor de_spinnerDepartureTime;
	 /** Deklaracja obiektu klasy JTable. */
	private JTable tableFilter;
	/** Deklaracja obiektu klasy JDefaultTableModel. */
	private DefaultTableModel modelFilter;
	/** Deklaracja obiektu klasy JComboBox. */
	private JComboBox<String> comboBoxBuyer;
	/** Deklaracja obiektu klasy JComboBox. */
	private JComboBox<String> comboBoxBusLine;
	/** Deklaracja obiektu klasy JComboBox. */
	private JComboBox<String> comboBoxTicketType;
	/** Deklaracja obiektu klasy JComboBox. */
	private JComboBox<String> comboBoxDiscount;
	/** Deklaracja obiektu klasy JComboBox. */
	private JComboBox<String> comboBoxScheduler;
	/** Deklaracja obiektu klasy DefaultComboBoxModel. */
	private DefaultComboBoxModel<String> comboModelBuyerD;
	/** Deklaracja obiektu klasy DefaultComboBoxModel. */
	private DefaultComboBoxModel<String> comboModelBusLineD;
	/** Deklaracja obiektu klasy DefaultComboBoxModel. */
	private DefaultComboBoxModel<String> comboBoxDiscountD;
	/** Deklaracja obiektu klasy DefaultComboBoxModel. */
	private DefaultComboBoxModel<String> comboBoxSchedulerD;
	/** Deklaracja obiektu klasy JTextField. */
	private JTextField textBuyer;
	/** Deklaracja obiektu klasy JTextField. */
	private JTextField textBusLine;
	/** Deklaracja obiektu klasy JTextField. */
	private JTextField textScheduler;
	/** Deklaracja obiektu klasy JTextField. */
	private JTextField textDiscount;
	/** Deklaracja obiektu klasy JTextField. */
	private JTextField filterField;
	/** Pole klasy Workers. */
	Workers w;
	
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
	 * @param w - parametr Workers - dane pracownika
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
	public TransactionWindow(Workers w) {
		
		this.w = w;
		
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
		setTitle("Transaction - Bus Management");
		setBounds(new Rectangle(100, 100, 700, 500));
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setFocusable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		addButton = new JButton("Add");
		addButton.setBackground(Color.LIGHT_GRAY);
		addButton.addActionListener(this);

		deleteButton = new JButton("Delete");
		deleteButton.setBackground(Color.LIGHT_GRAY);
		deleteButton.addActionListener(this);
		

		JScrollPane scrollPane = new JScrollPane();

		JLabel labelBuyer = new JLabel("Search Transaction:");

		JLabel lblTicketType = new JLabel("Ticket Type:");

		JLabel labelTicketType = new JLabel("Discount:");

		Date startDate = new Date();
		SpinnerDateModel sm = new SpinnerDateModel(startDate, null, null, Calendar.HOUR_OF_DAY);
		spinnerDepartureTime = new JSpinner(sm);
		spinnerDepartureTime.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		de_spinnerDepartureTime = new JSpinner.DateEditor(spinnerDepartureTime, "HH:mm:ss");
		spinnerDepartureTime.setEditor(de_spinnerDepartureTime);
		spinnerDepartureTime.setEnabled(true);

		JLabel lblBusLine = new JLabel("Bus Line:");

		JLabel label_4 = new JLabel("Departure Time:");

		filterField = new JTextField();
		filterField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = filterField.getText();
				filter(query);
			}
		});
		filterField.setColumns(10);

		fillComboBoxBuyer();
		comboBoxBuyer = new JComboBox<String>();
		comboBoxBuyer.setModel(comboModelBuyerD);
		comboBoxBuyer.setEditable(true);
		textBuyer = (JTextField) comboBoxBuyer.getEditor().getEditorComponent();
		textBuyer.setText("");
		textBuyer.addKeyListener(new ComboKeyHandler(comboBoxBuyer));

		JLabel labelChooseBuyer = new JLabel("Choose Buyer:");

		newBuyerButton = new JButton("New Buyer");
		newBuyerButton.addActionListener(this);

		fillComboBoxBusLine();
		comboBoxBusLine = new JComboBox<String>();
		comboBoxBusLine.setModel(comboModelBusLineD);
		textBusLine = (JTextField) comboBoxBusLine.getEditor().getEditorComponent();
		textBusLine.setText("");
		comboBoxBusLine.addActionListener(this);
		textBusLine.addKeyListener(new ComboKeyHandler(comboBoxBusLine));


		String[] ticketType = { "Monthly", "One-way" };

		comboBoxTicketType = new JComboBox<String>(ticketType);
		comboBoxTicketType.addActionListener(this);
		comboBoxTicketType.setEnabled(false);

		comboBoxDiscount = new JComboBox<String>();
		comboBoxDiscount.setEnabled(false);

		comboBoxScheduler = new JComboBox<String>();
		comboBoxScheduler.setEnabled(false);

		JLabel labelScheduler = new JLabel("Scheduler:");

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelChooseBuyer)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBoxBuyer, 0, 327, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(newBuyerButton)
					.addGap(163))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblBusLine, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(comboBoxBusLine, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(lblTicketType, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(comboBoxTicketType, 0, 235, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addGap(4))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(labelScheduler, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addGap(18)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboBoxScheduler, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(spinnerDepartureTime, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
							.addGap(28)
							.addComponent(labelTicketType, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(comboBoxDiscount, 0, 234, Short.MAX_VALUE)))
					.addGap(20))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 636, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(186, Short.MAX_VALUE)
					.addComponent(labelBuyer)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(addButton)
							.addGap(18)
							.addComponent(deleteButton))
						.addComponent(filterField, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
					.addGap(186))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(labelChooseBuyer)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboBoxBuyer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(newBuyerButton)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(3)
								.addComponent(lblBusLine))
							.addComponent(comboBoxBusLine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBoxTicketType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblTicketType))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(label_4))
						.addComponent(spinnerDepartureTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelTicketType)
								.addComponent(comboBoxDiscount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxScheduler, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelScheduler))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(addButton)
						.addComponent(deleteButton))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(filterField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelBuyer))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {addButton, deleteButton});

		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			preparedStatement = connect.prepareStatement("SELECT * FROM TRANSACTION");
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

		contentPane.setLayout(gl_contentPane);
	}



	/** Metoda bezparametrowa odpowiedzialna za czyszczenie pól forumlarza okna TransactionWindow.
	 * 
	 * Metoda jest typu void - nie zwraca żadnej wartości. 
	 */
	private void clearFields() {
		textBuyer.setText("");
		comboBoxBusLine.setSelectedIndex(0);
		comboBoxDiscount.setSelectedIndex(0);
		comboBoxScheduler.setSelectedIndex(0);
		comboBoxTicketType.setSelectedIndex(0);
		
	}

	/** Metoda parametrowa odpowiedzialna za generowanie biletu do pliku .pdf dla kupującego i zapisywania do wybranej lokalizacji na dysku.
	 * W pliku zapisywane są takie informacje jak informacje o kupującym, cena biletu oraz wszystkie informacje dotyczące przejazdu.
	 * Opcjonalnie plik z potwierdzeniem wysyłany jest jako załącznik na email kupującego lub samo potwierdzenie na SMS.
	 * 
	 * Metoda jest typu void - nie zwraca żadnej wartości. 
	 * 
	 * @param discount przechowuje ciąg znakó z kwotą bilteu
	 * @param date	przechowuje date do biletu
	 * @param buyer przekazuje id kupującego
	 * @param email decyzja uzytkownika o wysylce jesli 0 wysylany zostaje mail jezeli 1 nie
	 * @param sms	decyzja uzytkownika o wysylce jesli 0 wysylany zostaje sms jezeli 1 nie
	 * 
	 */
	private void generateSlip(String discount, String date, int buyer, int email, int sms) {

		String name = "", surname="", mail="", numberPhone="";
		String nameLine = comboBoxBusLine.getSelectedItem().toString();

		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");
			preparedStatement = connect.prepareStatement("SELECT * FROM Buyer Where buyerId="+ buyer);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
			name = rs.getString("name");
			surname = rs.getString("surname");
			mail = rs.getString("email");
			numberPhone = rs.getString("mobile");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JFileChooser dialog = new JFileChooser();
		dialog.setSelectedFile(new File(name +"_"+ surname + "_salary_slip_" + date + ".pdf"));
		int dialogResult = dialog.showSaveDialog(null);
		if (dialogResult == JFileChooser.APPROVE_OPTION) {
			String filePath = dialog.getSelectedFile().getPath();


				try {
					Document myDocument = new Document();
					PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
				myDocument.open();

				myDocument.add(new Paragraph("BILL SLIP", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
				myDocument.add(new Paragraph(date));
				myDocument.add(new Paragraph(
						"-------------------------------------------------------------------------------------------"));
				myDocument.add(
						(new Paragraph("BUYER DETAILS", FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD))));
				myDocument.add((new Paragraph("Buyer: " + name + " " + surname,
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
				myDocument.add(new Paragraph(
						"-------------------------------------------------------------------------------------------"));
				myDocument.add(new Paragraph("SALARY", FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD)));
				myDocument.add(new Paragraph("Salary: $" + discount,
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
				myDocument.add(new Paragraph(
						"-------------------------------------------------------------------------------------------"));
				myDocument.add(new Paragraph("BUS LINE", FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD)));
				myDocument.add(new Paragraph("Bus Line Trace: " + nameLine,
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
				if(comboBoxTicketType.getSelectedItem().toString().equals("One-way")){
				myDocument.add(new Paragraph("Bus Line Times: " + comboBoxScheduler.getSelectedItem().toString(),
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));}
				myDocument.add(new Paragraph(
						"-------------------------------------------------------------------------------------------"));
				myDocument.add(new Paragraph("TICKET", FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD)));
				myDocument.add(new Paragraph("Type Of Ticket: " + comboBoxTicketType.getSelectedItem().toString(),
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
				myDocument.add(new Paragraph(
						"-------------------------------------------------------------------------------------------"));

				myDocument.newPage();
				myDocument.close();
				
				Mail m = new Mail();
				
				if(email == 0) m.sendMail(mail, "BILL SLIP FOR "+ nameLine + " TICKET", "In the attachment you will find a receipt for your ticket", filePath, name +"_"+ surname + "_salary_slip_" + date + ".pdf");
				if(sms == 0) m.sendSMS(numberPhone, "BILL SLIP FOR "+ nameLine + " TICKET");
				
				JOptionPane.showMessageDialog(null, "Report was successfully generated");
} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		}

	}
	
	
	/** Metoda parametrowa odpowiedzialna za wyświetlenie komunikatów dotyczących wysłania rodzaju powiadomienia dla kupującego na email lub SMS.
	 * 
	 * Metoda jest typu void - nie zwraca żadnej wartości. Zbiera decyzje użytkownika a następnie wywołuje metode {link {@link #generateSlip(String, String, int, int, int)}.
	 * {link {@link #generateSlip(String, String, int, int, int)}
	 * @param discount przechowuje ciąg znakó z kwotą bilteu
	 * @param today	przechowuje date do biletu
	 * @param buyer przekazuje id kupującego
	 * @see javax.swing.JOptionPane
	 * 
	 */
	public void ask(String discount, String today, int buyer){
		int mail = JOptionPane.showConfirmDialog(null, "The Buyer want to get bill/ ticket on mail ?", "Question Box", JOptionPane.YES_NO_OPTION);
        if (mail == JOptionPane.YES_OPTION) {
          JOptionPane.showMessageDialog(null, "OK. We send bill/ticket to buyer mail!");
         
        }
        else {
           JOptionPane.showMessageDialog(null, "Ok. We not send a mail!");
        }
        
		int sms = JOptionPane.showConfirmDialog(null, "The Buyer want to get information about payment on His/Her mobilephon?", "Question Box", JOptionPane.YES_NO_OPTION);
        if (sms == JOptionPane.YES_OPTION) {
        	
          JOptionPane.showMessageDialog(null, "OK. We send sms to buyer mobile!");
        }
        else {
        	
           JOptionPane.showMessageDialog(null, "Ok. We not send sms to buyer mobile!");
        }
		
       generateSlip(discount, today, buyer, mail, sms);
	}

	
	/**
	 * Przesłonięta metoda służąca do określania zachowania aplikacji po
	 * kliknięciu na dany komponent przez użytkownika. W metodzie tej określono
	 * działanie dla przycisków oraz komponentów JComboBox znajdujących się w oknie do zarządzania transakcjami. 
	 * W przypadku kliknięcia na przycisk 'addButton' wywołane zostają metody comparBuyer, 
	 * comparScheduler, comparBusLine oraz pobierane są dane z pól formularza okna TransactionWindow, 
	 * a następnie zostaje dodany rekord do bazy danych bms_db z pobranymi danymi.
	 * Tworzony jest obiekt klasy TransactionJDBC oraz klasy Transaction. 
	 * Na obiekcie klasy TransactionJDBC wykonywana jest metoda 'addTransaction'. 
	 * Następnie wywoływana jest metoda do odświerzenia tabeli JTable - updateTable oraz do czyszczenia pól - clearFields. 
	 * Na końcu wyłączaane są przyciski do edytowania i usuwania rekordów z tabeli. 
	 * W przypadku kliknięcia na przycisk 'deleteButton' pobierane są dane z pól formularza okna TransactionWindow, 
	 * a następnie zostaje usunięty rekord z bazy danych bms_db na podtsawie pobranych danych. 
	 * Tworzony jest obiekt klasy TransactionJDBC oraz klasy Transaction. 
	 * Na obiekcie klasy TransactionJDBC wykonywana jest metoda 'deletetransaction'. 
	 * Następnie wywoływana jest metoda do odświerzenia tabeli JTable - updateTable oraz do czyszczenia pól - clearFields. 
	 * Na końcu wyłączaane są przyciski do edytowania i usuwanai rekordów z tabeli. 
	 * W przypadku kliknięcia na pole 'comboBoxBusLine' sprawdzane są odpowiednio warunki powodujące uaktywnianie oraz 
	 * deaktywowanie kolejnych pól formularza okna TransactionWindow.
	 * W przypadku kliknięcia na pole 'comboBoxTicketType' sprawdzane są odpowiednio warunki powodujące uaktywnianie oraz 
	 * deaktywowanie kolejnych pól formularza okna TransactionWindow. Wywoływane są metody: 'fillComboBoxDiscount' oraz 'fillComboBoxScheduler'
	 * W przypadku kliknięcia na pole 'newBuyerButton' uruchamiane jest okno do dodawania nowego kupującego. 
	 * Tworzony jest obiekt klasy NewBuyerWindow, ustawiany na widoczny. 
	 * Do informowani użytkownika oraz wyświetlania okien dialogowych
	 * wykorzystane zostały komponenty JOptionPane.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addButton) {

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			Calendar cal = Calendar.getInstance();
			String today = sdf.format(cal.getTime());
			int buyer = comparBuyer();
			int scheduler = comparScheduler();
			String discount;
			if(buyer != 0 || scheduler!=0){
			if (comboBoxTicketType.getSelectedItem().toString().equals("Monthly"))
				discount = comboBoxDiscount.getItemAt(1);
			else
				discount = comboBoxDiscount.getItemAt(0);
	
			try {
				TransactionJDBC wj = new TransactionJDBC();
				Transaction w = new Transaction(discount, "cash", today, scheduler, this.w.getWorkerId(), buyer);
				wj.addTransaction(w);
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			
	        ask(discount, today, buyer);
	        
			}
			clearFields();
			updateTable();
		}

		if (e.getSource() == deleteButton) {
		
			try {
				int index = Integer.parseInt(tableFilter.getValueAt(tableFilter.getSelectedRow(), 0).toString());
				
				TransactionJDBC wj = new TransactionJDBC();
				wj.deletetransaction(index);
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			
			clearFields();
			updateTable();
		}

		if (e.getSource() == comboBoxBusLine) {

			if (comboBoxBusLine.getSelectedItem() != null)
				comboBoxTicketType.setEnabled(true);
			else
				comboBoxTicketType.setEnabled(false);

			if (comboBoxBusLine.getSelectedItem() == null)
				comboBoxTicketType.setEnabled(false);
		}

		if (e.getSource() == comboBoxTicketType) {

			if (comboBoxTicketType.getSelectedIndex() == 1) {

				int i = comparBusLine();

				fillComboBoxScheduler(i, de_spinnerDepartureTime.getFormat().format(spinnerDepartureTime.getValue()));
				comboBoxScheduler.setModel(comboBoxSchedulerD);
				textScheduler = (JTextField) comboBoxScheduler.getEditor().getEditorComponent();
				textScheduler.setText("");
				textScheduler.addKeyListener(new ComboKeyHandler(comboBoxScheduler));
				comboBoxScheduler.setEnabled(true);

				fillComboBoxDiscount(i);
				comboBoxDiscount.setModel(comboBoxDiscountD);
				textDiscount = (JTextField) comboBoxDiscount.getEditor().getEditorComponent();
				textDiscount.setText("");
				textDiscount.addKeyListener(new ComboKeyHandler(comboBoxDiscount));
				comboBoxDiscount.setEnabled(false);
				comboBoxDiscount.setSelectedIndex(0);

			} else {

				int i = comparBusLine();

				fillComboBoxDiscount(i);
				comboBoxDiscount.setModel(comboBoxDiscountD);
				textDiscount = (JTextField) comboBoxDiscount.getEditor().getEditorComponent();
				textDiscount.setText("");
				textDiscount.addKeyListener(new ComboKeyHandler(comboBoxDiscount));
				comboBoxDiscount.setEnabled(false);
				comboBoxDiscount.setSelectedIndex(1);
				comboBoxScheduler.setEnabled(false);

				
				textScheduler = (JTextField) comboBoxScheduler.getEditor().getEditorComponent();
				textScheduler.setText("");
				textScheduler.addKeyListener(new ComboKeyHandler(comboBoxScheduler));
				comboBoxScheduler.setEnabled(false);

			}

		}

		if (e.getSource() == newBuyerButton) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						NewBuyerWindow nbw = new NewBuyerWindow();
						nbw.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

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

			preparedStatement = connect.prepareStatement("SELECT * FROM TRANSACTION");
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

	
	/** Metoda odpowiedzialna za wypełnianie danymi kupujących z bazy danych bms_db komponentu JComboBox przez model DefaultComboBoxModel.
	 * 
	 * Metoda jest typu void - nie zwraca żadnej wartości. 
	 */
	private void fillComboBoxBuyer() {
		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");

			preparedStatement = connect.prepareStatement("SELECT * FROM Buyer");
			rs = preparedStatement.executeQuery();
			comboModelBuyerD = new DefaultComboBoxModel<String>();
			while (rs.next()) {
				String name = rs.getString("name") + " " + rs.getString("surname") + " | "
						+ rs.getString("insuranceNumber");
				comboModelBuyerD.addElement(name);
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


	/** Metoda odpowiedzialna za pobranie ID kupującego rekordu z wybranego pola formularzu okna ScheduleWindow, gdzie ID jest kluczem obcym tabeli Transaction w bazie.
	 * Wynik zwraca na podstawaie porównywania danych z bazy danych bms_db.
	 * 
	 * @return 	numer ID kupującego jeśli porównane dane są takie same
	 */
	private int comparBuyer() {
		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");

			preparedStatement = connect.prepareStatement("SELECT * FROM Buyer");
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name") + " " + rs.getString("surname") + " | "
						+ rs.getString("insuranceNumber");

				if (name.equals(comboBoxBuyer.getSelectedItem())) {
					return Integer.parseInt(rs.getString("buyerId"));
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
				String name = rs.getString("busLineName") + " - " + rs.getString("busLineType");
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

	
	/** Metoda odpowiedzialna za pobranie ID linii busa rekordu z wybranego pola formularzu okna ScheduleWindow, gdzie ID jest kluczem obcym tabeli Transaction w bazie.
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
				String name = rs.getString("busLineName") + " - " + rs.getString("busLineType");

				if (name.equals(comboModelBusLineD.getSelectedItem())) {
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

	/** Metoda odpowiedzialna za wypełnianie danymi rozkładów z bazy danych bms_db komponentu JComboBox przez model DefaultComboBoxModel.
	 * 
	 * Metoda jest typu void - nie zwraca żadnej wartości. 
	 * @param i przechowuje id busa
	 * @param string przechowuje String z czasem
	 */
	private void fillComboBoxScheduler(int i, String string) {
		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");

			preparedStatement = connect.prepareStatement(
					"SELECT * FROM SCHEDULER WHERE IdBusLine = " + i + " AND depertureTime >='" + string + "'");
			rs = preparedStatement.executeQuery();
			comboBoxSchedulerD = new DefaultComboBoxModel<String>();
			while (rs.next()) {
				String name = "id: " + " " + rs.getString("schedulerID") + " |  " + rs.getString("depertureTime")
						+ " - " + rs.getString("arrivalTime");
				comboBoxSchedulerD.addElement(name);
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

	
	/** Metoda odpowiedzialna za pobranie ID rozkładu rekordu z wybranego pola formularzu okna ScheduleWindow, gdzie ID jest kluczem obcym tabeli Transaction w bazie.
	 * Wynik zwraca na podstawaie porównywania danych z bazy danych bms_db.
	 * 
	 * @return 	numer ID rozkładu jeśli porównane dane są takie same
	 */
	private int comparScheduler() {
		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");

			preparedStatement = connect.prepareStatement("SELECT * FROM SCHEDULER");
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = "id: " + " " + rs.getString("schedulerID") + " |  " + rs.getString("depertureTime")
						+ " - " + rs.getString("arrivalTime");

				if (name.equals(comboBoxSchedulerD.getSelectedItem())) {
					return Integer.parseInt(rs.getString("schedulerID"));
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

	/** Metoda odpowiedzialna za wypełnianie cenami linii busów z bazy danych bms_db komponentu JComboBox przez model DefaultComboBoxModel.
	 * 
	 * @param i - parametr int - ID linii busa
	 * 
	 * Metoda jest typu void - nie zwraca żadnej wartości. 
	 */
	private void fillComboBoxDiscount(int i) {
		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");

			preparedStatement = connect.prepareStatement("SELECT * FROM BUSLINE WHERE busLineID = " + i);
			rs = preparedStatement.executeQuery();
			comboBoxDiscountD = new DefaultComboBoxModel<String>();
			while (rs.next()) {
				String name = rs.getString("priceOneWay");
				comboBoxDiscountD.addElement(name);
				name = rs.getString("priceMonthly");
				comboBoxDiscountD.addElement(name);
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
}

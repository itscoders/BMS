package pl.psk.projekt.bms.ui;

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

import net.proteanit.sql.DbUtils;
import pl.psk.projekt.bms.dbobjects.Workers;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;

/**
 * Klasa DriverWindow zawiera komponenty do stworzenia UI okna startowego
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
public class DriverWindow extends JFrame implements ActionListener {

	/** Zmienna określająca unikalny numer w celu serializacji. */
	private static final long serialVersionUID = 1L;

	/** Deklaracja obiektu klasy JPanel. */
	private JPanel contentPane;
	/** Deklaracja obiektu klasy JButton. */
	private JButton printSchedulerButton;
	/** Deklaracja obiektu klasy JScrollPane. */
	private JScrollPane scrollPane;
	/** Deklaracja obiektu klasy JTable. */
	private JTable tableFilter;
	/** Deklaracja obiektu klasy JDefaultTableModel. */
	private DefaultTableModel modelFilter;
	/** Deklaracja obiektu klasy JTextField. */
	private JTextField textField;
	/** Pole klasy Workers. */
	private Workers w;
	/** Deklaracja obiektu klasy JButton. */
	private JButton logoutButton;
	/** Deklaracja obiektu klasy JLabel. */
	private JLabel logLabel;

	/** Deklaracja obiektu klasy PreparedStatement. */
	private PreparedStatement preparedStatement;
	/** Deklaracja obiektu klasy Connection. */
	private Connection connect;
	/** Deklaracja obiektów klasy ResultSet. */
	private ResultSet rs, rs2;


	/**
	 * Konstruktor klasy DriverWindow odpowiedzialny za
	 * inicializację komponentów biblioteki Swing. Komponenty definiowane:
	 * Jlabel, JButton, JPanel, JScrollPane, JTextField, JTable, JFrame - dla tych
	 * komponentów ustawiane są wymiary, fonty, kolory. Dodatkowo dla komponentu JTable zastosowany jest model tabeli - DefaultTableModel, 
	 * a także dodana dla niego metoda 'keyReleased' służąca do określania zachowania po zwolnieniuu klawisza, gdzie wywoływana jest metoda'filter'.
	 * Komponenty zostały rozmieszczone przy pomocy GroupLayout.
	 * Wywoływana jest metoda welcomeLabel. W konstruktorze przy pomocy zmiennej connect nawiązywane jest połączenie z bazą bms_db, 
	 * preparedStatement pozwala na wykonanie zapytania do bazy, a 'rs' na wyświetlenie wyników zapytania.
	 * 
	 * @param w - parametr Workers - dane zalagowanego użytkownika
	 * 
	 * @see JScrollPane
	 * @see JTextField
	 * @see JTable
	 * @see JPanel
	 * @see JButton
	 * @see JFrame
	 * @see JLabel
	 */
	public DriverWindow(Workers w) {

		this.w = w;

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
		setTitle("Driver Secheduler System");
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(new Rectangle(50, 100, 600, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		printSchedulerButton = new JButton("Print Your Scheduler");
		printSchedulerButton.setBounds(423, 110, 156, 23);
		printSchedulerButton.setBackground(Color.LIGHT_GRAY);
		printSchedulerButton.addActionListener(this);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 151, 564, 204);

		textField = new JTextField();
		textField.setBounds(137, 110, 199, 23);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = textField.getText();
				filter(query);
			}
		});
		textField.setColumns(10);

		JLabel lebelSearch = new JLabel("Search in your schedule:");
		lebelSearch.setBounds(15, 110, 118, 23);

		logoutButton = new JButton("Logout");
		logoutButton.setBounds(490, 13, 89, 29);
		logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logout.png")));
		logoutButton.addActionListener(this);

		logLabel = new JLabel();
		logLabel.setBounds(15, 11, 341, 25);
		logLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		welcomeLabel();

		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");
			preparedStatement = connect.prepareStatement("SELECT * FROM SCHEDULER WHERE IdDriver=" + w.getWorkerId());
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
		contentPane.setLayout(null);
		contentPane.add(scrollPane);
		contentPane.add(lebelSearch);
		contentPane.add(textField);
		contentPane.add(logLabel);
		contentPane.add(logoutButton);
		contentPane.add(printSchedulerButton);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(this.getClass().getResource("/worker.png")));
		background.setBounds(0, 0, 594, 371);
		contentPane.add(background);
	}

	/**
	 * Przesłonięta metoda służąca do określania zachowania aplikacji po
	 * kliknięciu na dany komponent przez użytkownika. W metodzie tej określono
	 * działanie dla przycisków znajdujących się w oknie dla Kierowcy. 
	 * W przypadku kliknięcia na przycisk 'printSchedulerButton' generowany jest plik .pdf w którym zapisywany jest rozkład dla danego kierowcy. Wywoływana jest funkcja: 'generateFileWithScheduler'.
	 * W przypadku kliknięcia na przycisk 'logoutButton' następuje wylogowanie użytkownika i uruchamiane jest okno logowania. Tworzony jest nowy wątek, w którym zaś tworzony jest obiekt klasy LoginWindow, ustawiany na widoczny, a aktualny niszczony. 
	 *    
	 * Do informowani użytkownika oraz wyświetlania okien dialogowych
	 * wykorzystane zostały komponenty JOptionPane.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == printSchedulerButton) {

			generateFileWithScheduler();

		}

		if (e.getSource() == logoutButton) {
			String username = w.getUsername();
			JOptionPane.showMessageDialog(this, "Worker: " + username + " was successfully logged out.");
			LoginWindow lw = new LoginWindow();
			lw.setVisible(true);
			this.dispose();
		}

	}

	/** Metoda bezparametrowa odpowiedzialna za wyświetlenie imienia i nazwiska aktualnie zalogowanego użytkownika.
	 * Metoda jest typu void - nie zwraca żadnej wartości. 
	 */
	public void welcomeLabel() {
		String name = w.getName();
		String surname = w.getSurname();
		logLabel.setText("Welcome " + name + " " + surname);

	}

	/** Metoda bezparametrowa odpowiedzialna za generowanie rozkładu do pliku .pdf dla danego kierowcy i zapisywania do wybranej lokalizacji na dysku.
	 * W pliku zapisywane są takie informacje jak data, informacje o kierowcy i rozkład.
	 * 
	 * Metoda jest typu void - nie zwraca żadnej wartości. 
	 */
	private void generateFileWithScheduler() {

		String row = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar cal = Calendar.getInstance();
		String date = sdf.format(cal.getTime());

		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");
			preparedStatement = connect.prepareStatement("SELECT * FROM SCHEDULER WHERE IdDriver=" + w.getWorkerId());
			rs = preparedStatement.executeQuery();

			JFileChooser dialog = new JFileChooser();
			dialog.setSelectedFile(new File(w.getName() + "_" + w.getSurname() + "_scheduler_" + date + ".pdf"));
			int dialogResult = dialog.showSaveDialog(null);
			if (dialogResult == JFileChooser.APPROVE_OPTION) {
				String filePath = dialog.getSelectedFile().getPath();

				Document myDocument = new Document();
				PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
				myDocument.open();

				myDocument.add(
						new Paragraph("PRINT SCHEDULER", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
				myDocument.add(new Paragraph("GENERATE DATE : " + date));
				myDocument.add(new Paragraph(
						"-------------------------------------------------------------------------------------------"));
				myDocument.add((new Paragraph("DRIVER DETAILS:",
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD))));
				myDocument.add((new Paragraph("Name: " + w.getName() + " Surname: " + w.getSurname(),
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
				myDocument.add((new Paragraph("Driver ID: " + w.getWorkerId(),
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
				myDocument.add(new Paragraph(
						"-------------------------------------------------------------------------------------------"));
				while (rs.next()) {
					row = "";
					for (int i = 2; i <= rs.getMetaData().getColumnCount() - 1; i++) {
						if (i == 5) {
							preparedStatement = connect.prepareStatement("SELECT DISTINCT * FROM BUSLINE WHERE busLineID=" + rs.getInt(i));
							rs2 = preparedStatement.executeQuery();
							while (rs2.next()) {
								row += rs2.getString(2) + ", "+ rs2.getString(3) + ", ";
							}
						} else
							row += rs.getString(i) + ", ";
					}

					myDocument.add(new Paragraph(row, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
				}
				myDocument.add(new Paragraph(
						"-------------------------------------------------------------------------------------------"));

				myDocument.newPage();
				myDocument.close();

				JOptionPane.showMessageDialog(null, "Report was successfully generated");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

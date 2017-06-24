package pl.psk.projekt.bms.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

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
import pl.psk.projekt.bms.component.ComboKeyHandler;
import javax.swing.DefaultComboBoxModel;

public class TransactionWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton newBuyerButton;
	private JSpinner spinnerDepartureTime;
	PreparedStatement preparedStatement;
	Connection connect;
	ResultSet rs;
	private JTable tableFilter;
	private JTextField textField;
	private JTextField filterField;
	private DefaultTableModel modelFilter;
	JComboBox<String> comboBoxBuyer;
	JComboBox<String> comboBoxBusLine;
	JComboBox<String> comboBoxTicketType;
	JComboBox<String> comboBoxDiscount;
	JComboBox<String> comboBoxScheduler;
	private DefaultComboBoxModel<String> comboModelBuyerD;
	private DefaultComboBoxModel<String> comboModelBusLineD;
	private DefaultComboBoxModel<String> comboBoxSchedulerD;
	double monthlyPrice;
	double onewayPrice;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransactionWindow frame = new TransactionWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TransactionWindow() {
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

		editButton = new JButton("Edit");
		editButton.setBackground(Color.LIGHT_GRAY);
		editButton.addActionListener(this);

		deleteButton = new JButton("Delete");
		deleteButton.setBackground(Color.LIGHT_GRAY);
		deleteButton.addActionListener(this);

		JScrollPane scrollPane = new JScrollPane();

		JLabel labelBuyer = new JLabel("Search Transaction:");

		JLabel lblTicketType = new JLabel("Ticket Type:");

		JLabel labelTicketType = new JLabel("Discount:");

		textField = new JTextField();
		textField.setColumns(10);

		Date startDate = new Date();
		SpinnerDateModel sm = new SpinnerDateModel(startDate, null, null, Calendar.HOUR_OF_DAY);
		spinnerDepartureTime = new JSpinner(sm);
		spinnerDepartureTime.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		JSpinner.DateEditor de_spinnerDepartureTime = new JSpinner.DateEditor(spinnerDepartureTime, "HH:mm:ss");
		spinnerDepartureTime.setEditor(de_spinnerDepartureTime);
		spinnerDepartureTime.setEnabled(false);

		JLabel lblBusLine = new JLabel("Bus Line:");

		JLabel label_4 = new JLabel("Departure Time:");

		JLabel label_5 = new JLabel("Number:");

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
		JTextField textBuyer = (JTextField) comboBoxBuyer.getEditor().getEditorComponent();
		textBuyer.setText("");
		textBuyer.addKeyListener(new ComboKeyHandler(comboBoxBuyer));

		JLabel labelChooseBuyer = new JLabel("Choose Buyer:");

		newBuyerButton = new JButton("New Buyer");

		fillComboBoxBusLine();
		comboBoxBusLine = new JComboBox<String>();
		comboBoxBusLine.setModel(comboModelBusLineD);
		JTextField textBusLine = (JTextField) comboBoxBusLine.getEditor().getEditorComponent();
		textBusLine.setText("");
		comboBoxBusLine.addActionListener(this);
		textBusLine.addKeyListener(new ComboKeyHandler(comboBoxBuyer));

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
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(labelChooseBuyer).addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(comboBoxBuyer,
								GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(newBuyerButton).addGap(163))
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblBusLine, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(comboBoxBusLine, GroupLayout.PREFERRED_SIZE, 225,
										GroupLayout.PREFERRED_SIZE)
								.addGap(28)
								.addComponent(lblTicketType, GroupLayout.PREFERRED_SIZE, 65,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(comboBoxTicketType, 0, 237, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addGap(4)
								.addComponent(spinnerDepartureTime, GroupLayout.PREFERRED_SIZE, 226,
										GroupLayout.PREFERRED_SIZE)
								.addGap(28)
								.addComponent(labelTicketType, GroupLayout.PREFERRED_SIZE, 65,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(comboBoxDiscount, 0, 237, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
								.addComponent(labelScheduler, GroupLayout.PREFERRED_SIZE, 65,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(comboBoxScheduler, GroupLayout.PREFERRED_SIZE, 237,
										GroupLayout.PREFERRED_SIZE)))
						.addGap(20))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(28)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 636, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(188, Short.MAX_VALUE)
						.addComponent(labelBuyer).addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(filterField, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
						.addGap(186))
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(252, Short.MAX_VALUE)
						.addComponent(addButton).addGap(18).addComponent(editButton).addGap(18)
						.addComponent(deleteButton).addGap(209)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(labelChooseBuyer)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxBuyer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(newBuyerButton)))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(3).addComponent(lblBusLine))
								.addComponent(comboBoxBusLine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxTicketType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(lblTicketType))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(3).addComponent(label_4))
						.addComponent(spinnerDepartureTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(3)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(labelTicketType).addComponent(comboBoxDiscount,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(6).addComponent(label_5))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxScheduler, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(labelScheduler)))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(addButton)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(editButton)
								.addComponent(deleteButton)))
				.addGap(43)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(filterField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(labelBuyer))
				.addGap(18).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
				.addContainerGap()));
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] { addButton, editButton, deleteButton });

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

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addButton) {
			/*
			 * String name = nameField.getText(); String surname =
			 * surnameField.getText(); String birthday = ((JTextField)
			 * birthdayField.getDateEditor().getUiComponent()).getText(); String
			 * email = emailField.getText(); String mobilePhone =
			 * mobilePhoneField.getText(); String street =
			 * streetField.getText(); String houseNumber =
			 * houseNumberField.getText(); String postCode =
			 * postCodeField.getText(); String city = cityField.getText();
			 * 
			 * boolean valid = true; String invalid = "";
			 * 
			 * try { SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			 * Date dd = sdf.parse(birthday); Calendar cal =
			 * Calendar.getInstance(); String today = sdf.format(cal.getTime());
			 * Date tod = sdf.parse(today);
			 * 
			 * if (dd.after(tod)) valid = false; invalid =
			 * "Birthday Cannot be furture date"; } catch (Exception ex) {
			 * JOptionPane.showMessageDialog(this, "Fail to compare Date"); }
			 * 
			 * 
			 * // Handphone validation if (mobilePhone.length() != 9) { valid =
			 * false; invalid = "Handphone number should have 9 number"; } for
			 * (int a = 0; a < mobilePhone.length(); a++) { char temp1 =
			 * mobilePhone.charAt(a); if (Character.isLetter(temp1) == true) {
			 * valid = false; invalid = "Please insert proper handphone number";
			 * } }
			 * 
			 * if (valid) { try { try { BuyerJDBC wj = new BuyerJDBC(); Buyer w
			 * = new Buyer(name, surname, birthday, email, mobilePhone, street,
			 * houseNumber, postCode, city); wj.createBuyer(w); } catch
			 * (SQLException e1) {
			 * 
			 * e1.printStackTrace(); } JOptionPane.showMessageDialog(this,
			 * "New Buyer: " + name + surname + " had added."); // dispose(); //
			 * staffSelect ss = new staffSelect(); // ss.setVisible(true); }
			 * catch (Exception ex) { JOptionPane.showMessageDialog(this,
			 * ex.getMessage()); } } else JOptionPane.showMessageDialog(null,
			 * invalid);
			 * 
			 * Update_table();
			 */
		}

		if (e.getSource() == editButton) {

			// startWindow.setVisible(true);
		}

		if (e.getSource() == deleteButton) {

			// startWindow.setVisible(true);
		}
		
		if(e.getSource() == comboBoxBusLine){
			
			if(comboBoxBusLine.getSelectedItem() != null) comboBoxTicketType.setEnabled(true);
				else comboBoxTicketType.setEnabled(false);
			
			if(comboBoxBusLine.getSelectedItem() == null) comboBoxTicketType.setEnabled(false);
		}
		
		if(e.getSource() == comboBoxTicketType){
			
		
			if(comboBoxTicketType.getSelectedIndex() == 1 ){ 
				
				spinnerDepartureTime.setEnabled(true);
				
				try {
					connect = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
							"root", "toor");

					preparedStatement = connect.prepareStatement("SELECT * FROM BusLine WHERE busLineName = "+ comboBoxBusLine.getSelectedItem() +"AND busLineType ="+comboBoxBusLine.getSelectedItem());
					rs = preparedStatement.executeQuery();
					comboModelBuyerD = new DefaultComboBoxModel<String>();
					while (rs.next()) {
						String name = rs.getString("name") + " " + rs.getString("surname");
						comboModelBuyerD.addElement(name);
					}
				} catch (Exception ey) {
					JOptionPane.showMessageDialog(null, ey);
				} finally {

					try {
						rs.close();
						preparedStatement.close();

					} catch (Exception en) {

					}
				}
			
			}
				else{ 
					
					spinnerDepartureTime.setEnabled(false);
					//comboBoxDiscount.addItem();
				
				}
				
			
		}
	}

	// METODA ODŚWIERZAJĄCA TABELE JTABLE
	// MUSI ZOSTAĆ WYWOŁANA ZAWSZE NA KOŃCU W PRZYCISKACH: ADD, EDIT, DELETE
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

	// METODA DO DYNAMICZNEGO WYSZUKIWANIA W TABELI
	private void filter(String query) {

		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(modelFilter);
		tableFilter.setRowSorter(trs);

		trs.setRowFilter(RowFilter.regexFilter(query));
	}

	// METODA WYPEŁNIAJĄCA COMBOBOX DANYMI Z BAZY
	private void fillComboBoxBuyer() {
		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");

			preparedStatement = connect.prepareStatement("SELECT * FROM Buyer");
			rs = preparedStatement.executeQuery();
			comboModelBuyerD = new DefaultComboBoxModel<String>();
			while (rs.next()) {
				String name = rs.getString("name") + " " + rs.getString("surname");
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

	// METODA UMOŻLIWIAJĄCA PORÓWNYWANIE WYBRANEGO Z COMBOBOX TEKSTU DO WARTOSCI
	// W BAZIE W CELU POBRANIA OBCEGO ID
	private String comparBuyer() {
		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");

			preparedStatement = connect.prepareStatement("SELECT * FROM Buyer");
			rs = preparedStatement.executeQuery();
			comboModelBuyerD = new DefaultComboBoxModel<String>();
			while (rs.next()) {
				String name = rs.getString("name") + " " + rs.getString("surname");

				if (name.equals(comboBoxBuyer.getSelectedItem())) {
					return rs.getString("buyerId");
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
		return null;
	}

	// METODA WYPEŁNIAJĄCA COMBOBOX DANYMI Z BAZY
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

	// METODA UMOŻLIWIAJĄCA PORÓWNYWANIE WYBRANEGO Z COMBOBOX TEKSTU DO WARTOSCI
	// W BAZIE W CELU POBRANIA OBCEGO ID
	private String comparBusLine() {
		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");

			preparedStatement = connect.prepareStatement("SELECT * FROM BUSLINE");
			rs = preparedStatement.executeQuery();
			comboModelBuyerD = new DefaultComboBoxModel<String>();
			while (rs.next()) {
				String name = rs.getString("busLineName") + " - " + rs.getString("busLineType");

				if (name.equals(comboBoxBuyer.getSelectedItem())) {
					return rs.getString("busLineID");
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
		return null;
	}
}

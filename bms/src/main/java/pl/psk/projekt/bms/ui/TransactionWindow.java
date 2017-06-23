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
import javax.swing.JToggleButton;
import pl.psk.projekt.bms.component.ComboKeyHandler;
import javax.swing.DefaultComboBoxModel;

public class TransactionWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;

	PreparedStatement preparedStatement;
	Connection connect;
	ResultSet rs;
	private JTable table;
	private JTextField textField;
	private JTextField filterField;
	private JTable tableFilter;
	DefaultTableModel modelFilter;
	JComboBox<String> comboBoxBuyer;
	private DefaultComboBoxModel<String> comboModel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkerWindow frame = new WorkerWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TransactionWindow() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
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

		JLabel labelBuyer = new JLabel("Search Buyer:");

		JComboBox comboBox = new JComboBox();

		JComboBox comboBox_2 = new JComboBox();

		JLabel label = new JLabel("Stop Station:");

		JLabel labelTicketType = new JLabel("Ticket Type:");

		JComboBox comboBox_3 = new JComboBox();

		JLabel label_2 = new JLabel("Discount:");

		textField = new JTextField();
		textField.setColumns(10);

		Date startDate = new Date();
		SpinnerDateModel sm = new SpinnerDateModel(startDate, null, null, Calendar.HOUR_OF_DAY);
		JSpinner spinnerDepartureTime = new JSpinner(sm);
		spinnerDepartureTime.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		JSpinner.DateEditor de_spinnerDepartureTime = new JSpinner.DateEditor(spinnerDepartureTime, "HH:mm:ss");
		spinnerDepartureTime.setEditor(de_spinnerDepartureTime);

		JComboBox comboBox_4 = new JComboBox();

		JLabel label_3 = new JLabel("Start Station:");

		JLabel label_4 = new JLabel("Departure Time:");

		JLabel label_5 = new JLabel("Number:");

		JScrollPane scrollPane_1 = new JScrollPane();

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
		comboBoxBuyer.setModel(comboModel);
		comboBoxBuyer.setEditable(true);
		JTextField text = (JTextField) comboBoxBuyer.getEditor().getEditorComponent();
		text.setText("");
		text.addKeyListener(new ComboKeyHandler(comboBoxBuyer));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(18).addComponent(labelBuyer).addGap(18)
								.addComponent(filterField, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
								.addGap(50).addComponent(comboBoxBuyer, GroupLayout.PREFERRED_SIZE, 283,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(18).addComponent(scrollPane,
								GroupLayout.PREFERRED_SIZE, 636, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(20).addGroup(gl_contentPane
								.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
										.createSequentialGroup()
										.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 65,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, 225,
												GroupLayout.PREFERRED_SIZE)
										.addGap(28)
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(comboBox, 0, 225, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 78,
														GroupLayout.PREFERRED_SIZE)
												.addGap(4)
												.addComponent(spinnerDepartureTime, GroupLayout.PREFERRED_SIZE, 226,
														GroupLayout.PREFERRED_SIZE)
												.addGap(28)
												.addComponent(labelTicketType, GroupLayout.PREFERRED_SIZE, 65,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(comboBox_2, 0, 225, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 65,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(textField, GroupLayout.PREFERRED_SIZE, 225,
														GroupLayout.PREFERRED_SIZE)
												.addGap(28)
												.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 45,
														GroupLayout.PREFERRED_SIZE)
												.addGap(38).addComponent(comboBox_3, 0, 225, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED, 220, GroupLayout.PREFERRED_SIZE)
										.addComponent(addButton).addGap(18).addComponent(editButton).addGap(18)
										.addComponent(deleteButton).addGap(199))))
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(scrollPane_1,
								GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(labelBuyer)
						.addComponent(filterField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxBuyer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(13).addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(3).addComponent(label_3))
								.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(label))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(3).addComponent(label_4))
						.addComponent(spinnerDepartureTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(3)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(labelTicketType).addComponent(comboBox_2,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(6).addComponent(label_5))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(3).addComponent(label_2))
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(addButton)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(editButton)
								.addComponent(deleteButton)))
				.addGap(18).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] { addButton, editButton, deleteButton });
		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			preparedStatement = connect.prepareStatement("SELECT * FROM Buyer");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelFilter = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);
		tableFilter = new JTable();
		tableFilter.setModel(modelFilter);
		scrollPane_1.setViewportView(tableFilter);

		table = new JTable();
		scrollPane.setViewportView(table);

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
			dispose();
			// startWindow.setVisible(true);
		}

		if (e.getSource() == deleteButton) {
			dispose();
			// startWindow.setVisible(true);
		}

	}

	private void Update_table() {
		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");

			preparedStatement = connect.prepareStatement("SELECT * FROM Buyer");
			rs = preparedStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
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

	private void filter(String query) {

		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(modelFilter);
		tableFilter.setRowSorter(trs);

		trs.setRowFilter(RowFilter.regexFilter(query));
	}

	private void fillComboBoxBuyer() {
		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");

			preparedStatement = connect.prepareStatement("SELECT * FROM Buyer");
			rs = preparedStatement.executeQuery();
			comboModel = new DefaultComboBoxModel<String>();
			while(rs.next()) {
				String name = rs.getString("name") + " " + rs.getString("surname");
				comboModel.addElement(name);
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
	
	private String compare() {
		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");

			preparedStatement = connect.prepareStatement("SELECT * FROM Buyer");
			rs = preparedStatement.executeQuery();
			comboModel = new DefaultComboBoxModel<String>();
			while(rs.next()) {
				String name = rs.getString("name") + " " + rs.getString("surname");
				
				if(name.equals(comboBoxBuyer.getSelectedItem())) {
					return  rs.getString("buyerId");
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

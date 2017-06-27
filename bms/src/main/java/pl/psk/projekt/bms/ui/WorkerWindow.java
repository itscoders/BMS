package pl.psk.projekt.bms.ui;

import java.awt.EventQueue;
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
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Component;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;
import pl.psk.projekt.bms.dbobjects.Workers;
import pl.psk.projekt.bms.jdbc.SchedulerJDBC;
import pl.psk.projekt.bms.jdbc.WorkersJDBC;

import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Klasa WorkerWindow zawiera komponenty do stworzenia UI okna startowego
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
public class WorkerWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField userNameField;
	private JTextField nameField;
	private JTextField mobilePhoneField;
	private JTextField surnameField;
	private JLabel labelAdress;
	private JLabel labelSalary;
	private JLabel labelUserName;
	private JLabel labelName;
	private JLabel lblNewLabel_2;
	private JLabel labelBirthday;
	private JLabel labelPassword;
	private JLabel labelSurname;
	private JTextField salaryField;
	private JLabel labelEmail;
	private JLabel labelMobilePhone;
	private JPasswordField passwordField;
	private JTable tableFilter;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JComboBox<String> comboBoxType;
	private JDateChooser birthdayField;
	private JTextArea adressField;

	PreparedStatement preparedStatement;
	Connection connect;
	ResultSet rs;
	private JTextField filterField;
	private DefaultTableModel modelFilter;
	private JTextField emailField;

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

	public WorkerWindow() {
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
		setTitle("Worker - Bus Management");
		setBounds(new Rectangle(100, 100, 700, 500));
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setFocusable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		userNameField = new JTextField();
		userNameField.setColumns(10);

		labelUserName = new JLabel("User Name:");

		labelName = new JLabel("Name:");

		nameField = new JTextField();
		nameField.setColumns(10);

		lblNewLabel_2 = new JLabel("Type:");

		labelBirthday = new JLabel("Birthday:");

		labelPassword = new JLabel("Password:");

		labelSurname = new JLabel("Surname:");

		mobilePhoneField = new JTextField();
		mobilePhoneField.setColumns(10);

		labelEmail = new JLabel("Email:");

		labelMobilePhone = new JLabel("Mobile Phone:");

		surnameField = new JTextField();
		surnameField.setColumns(10);

		labelAdress = new JLabel("Adress:");

		labelSalary = new JLabel("Salary:");

		salaryField = new JTextField();
		salaryField.setColumns(10);

		passwordField = new JPasswordField();

		comboBoxType = new JComboBox<String>();
		comboBoxType.setModel(new DefaultComboBoxModel<String>(new String[] {"Administrator", "Driver", "Seller"}));

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

		JScrollPane scrollPane_1 = new JScrollPane();

		JLabel labelFilter = new JLabel("Search Worker:");

		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			preparedStatement = connect.prepareStatement("SELECT * FROM Workers");
			rs = preparedStatement.executeQuery();
			
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		modelFilter = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);

		filterField = new JTextField();
		filterField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = filterField.getText();
				filter(query);
			}
		});
		filterField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(240, Short.MAX_VALUE)
					.addComponent(addButton)
					.addGap(18)
					.addComponent(editButton)
					.addGap(18)
					.addComponent(deleteButton)
					.addGap(219))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(labelUserName)
										.addComponent(labelName))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
										.addComponent(userNameField, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(labelBirthday)
										.addComponent(labelAdress)
										.addComponent(lblNewLabel_2))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
										.addComponent(birthdayField, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
										.addComponent(comboBoxType, 0, 214, Short.MAX_VALUE))))
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelMobilePhone)
								.addComponent(labelPassword)
								.addComponent(labelSurname)
								.addComponent(labelSalary)
								.addComponent(labelEmail))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(emailField)
								.addComponent(salaryField)
								.addComponent(surnameField)
								.addComponent(passwordField, 103, 103, Short.MAX_VALUE)
								.addComponent(mobilePhoneField))))
					.addGap(30))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(199, Short.MAX_VALUE)
					.addComponent(labelFilter, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(filterField, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addGap(188))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelUserName)
						.addComponent(userNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelName)
						.addComponent(labelSurname)
						.addComponent(surnameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelEmail)
								.addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelMobilePhone)
								.addComponent(mobilePhoneField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelSalary)
								.addComponent(salaryField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(comboBoxType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelBirthday)
								.addComponent(birthdayField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelAdress))))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(addButton)
						.addComponent(editButton)
						.addComponent(deleteButton))
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelFilter)
						.addComponent(filterField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {addButton, editButton, deleteButton});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {userNameField, nameField, mobilePhoneField, surnameField, salaryField, passwordField});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {labelUserName, labelName, lblNewLabel_2, labelBirthday, labelPassword, labelSurname, labelEmail, labelMobilePhone, labelAdress, labelSalary});

		adressField = new JTextArea();
		adressField.setColumns(20);
		adressField.setRows(4);
		scrollPane_1.setViewportView(adressField);

		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");
			preparedStatement = connect.prepareStatement("SELECT * FROM Workers");
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
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		contentPane.setLayout(gl_contentPane);
	}
	
	
	private void tableFilterMouseClicked(MouseEvent e) {
		deleteButton.setEnabled(true);
		editButton.setEnabled(true);
		int index = tableFilter.getSelectedRow();
		
		String username = tableFilter.getValueAt(index, 2).toString();
		String password = tableFilter.getValueAt(index, 3).toString();
		String name = tableFilter.getValueAt(index, 4).toString();
		String surname = tableFilter.getValueAt(index, 5).toString();
		String type = tableFilter.getValueAt(index, 1).toString();
		String email = tableFilter.getValueAt(index, 6).toString();
		String birthday = tableFilter.getValueAt(index, 9).toString();
		String mobilePhone = tableFilter.getValueAt(index, 7).toString();
		String adress = tableFilter.getValueAt(index, 8).toString();
		String salaryy = tableFilter.getValueAt(index, 10).toString();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(birthday);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
						
		userNameField.setText(username);
		passwordField.setText(password);
		nameField.setText(name);
		surnameField.setText(surname);
		comboBoxType.setSelectedItem(type);
		emailField.setText(email);
		birthdayField.setDate(date);
		mobilePhoneField.setText(mobilePhone);
		adressField.setText(adress);
		salaryField.setText(salaryy);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addButton) {
			String username = userNameField.getText();
			String password = passwordField.getText();
			String name = nameField.getText();
			String surname = surnameField.getText();
			String type = (String) comboBoxType.getSelectedItem();
			String email = emailField.getText();
			String birthday = ((JTextField) birthdayField.getDateEditor().getUiComponent()).getText();
			String mobilePhone = mobilePhoneField.getText();
			String adress = adressField.getText();
			String salaryy = salaryField.getText();
			

			boolean valid = true;
			String invalid = "";

			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
				Date dd = sdf.parse(birthday);
				Calendar cal = Calendar.getInstance();
				String today = sdf.format(cal.getTime());
				Date tod = sdf.parse(today);

				if (dd.compareTo(tod) > 0){
					valid = false;
				invalid = "Birthday Cannot be furture date";
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, invalid );
				
			}

			for (int i = 0; i < salaryy.length(); i++) {
				char temp = salaryy.charAt(i);
				if (Character.isLetter(temp) == true) {
					valid = false;
					invalid = "Please insert number only for salary";
				}
			}

			// Handphone validation
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
					double salary = Double.parseDouble(salaryy);
					try {
						WorkersJDBC wj = new WorkersJDBC();
						Workers w = new Workers(type, username, password, name, surname, email, mobilePhone, adress,birthday, salary);
						wj.createWorker(w);
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(this, "New worker :" + username + " had added.");
					updateTable();
					editButton.setEnabled(false);
					deleteButton.setEnabled(false);
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage());
				}
			} else
				JOptionPane.showMessageDialog(null, invalid);
			
			updateTable();
		}

		if (e.getSource() == editButton) {
			
			
			String username = userNameField.getText();
			String password = passwordField.getText();
			String name = nameField.getText();
			String surname = surnameField.getText();
			String type = (String) comboBoxType.getSelectedItem();
			String email = emailField.getText();
			String birthday = ((JTextField) birthdayField.getDateEditor().getUiComponent()).getText();
			String mobilePhone = mobilePhoneField.getText();
			String adress = adressField.getText();
			String salary = salaryField.getText();

			
			boolean valid = true;
			String invalid = "";

			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
				Date dd = sdf.parse(birthday);
				Calendar cal = Calendar.getInstance();
				String today = sdf.format(cal.getTime());
				Date tod = sdf.parse(today);
				if (dd.compareTo(tod) > 0) {
					valid = false;
				invalid = "Birthday Cannot be furture date";
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Fail to compare Date");
			}


			for (int i = 0; i < salary.length(); i++) {
				char temp = salary.charAt(i);
				if (Character.isLetter(temp) == true) {
					valid = false;
					invalid = "Please insert number only for salary";
				}
			}

			// Handphone validation
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
					int value = Integer.parseInt(tableFilter.getValueAt(tableFilter.getSelectedRow(), 0).toString());
					System.out.println(value);
					double dSalary = Double.parseDouble(salary);
					try {
						WorkersJDBC wj = new WorkersJDBC();
						Workers w = new Workers(type, username, password, name, surname, email, mobilePhone, adress,birthday, dSalary);
						w.setWorkerId(value);
						wj.updateWorker(w);
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(this, "Worker: " + username + " was edited.");
					updateTable();
					editButton.setEnabled(false);
					deleteButton.setEnabled(false);
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage());
				}
			} else
				JOptionPane.showMessageDialog(null, invalid);
			updateTable();
		}

		if (e.getSource() == deleteButton) {
			
			String username = userNameField.getText();
			int value = Integer.parseInt(tableFilter.getValueAt(tableFilter.getSelectedRow(), 0).toString());
			
			try {
				WorkersJDBC wj = new WorkersJDBC();
				Workers w = new Workers();
				w.setWorkerId(value);
				wj.deleteWorker(w);
				updateTable();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "Worker: " + username + " was deleted.");
			editButton.setEnabled(false);
			deleteButton.setEnabled(false);
			updateTable();
		}

	}


	private void updateTable() {
		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");

			preparedStatement = connect.prepareStatement("SELECT * FROM Workers");
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


	private void filter(String query) {

		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(modelFilter);
		tableFilter.setRowSorter(trs);

		trs.setRowFilter(RowFilter.regexFilter(query));
	}
}

package pl.psk.projekt.bms.ui;

import java.awt.EventQueue;
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

import com.toedter.calendar.JDateChooser;

import pl.psk.projekt.bms.dbobjects.Workers;
import pl.psk.projekt.bms.jdbc.WorkersJDBC;

import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class WorkerWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField userNameField;
	private JTextField nameField;
	private JTextField mobilePhoneField;
	private JTextField surnameField;
	private JLabel labelAdress;
	private JLabel labelSalary;
	private JTextField salaryField;
	private JPasswordField passwordField;
	private JTable table;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JComboBox<String> comboBoxType;
	private JComboBox<String> comboBoxPosition;
	private JDateChooser birthdayField;
	private JTextArea adressField;
	
	PreparedStatement  preparedStatement;
    Connection connect;

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
		setTitle("Worker - Bus Management");
		setBounds(new Rectangle(100, 100, 700, 500));
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setFocusable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		userNameField = new JTextField();
		userNameField.setColumns(10);

		JLabel labelUserName = new JLabel("User Name:");

		JLabel labelName = new JLabel("Name:");

		nameField = new JTextField();
		nameField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Type:");

		JLabel labelBirthday = new JLabel("Birthday:");

		JLabel labelPassword = new JLabel("Password:");

		JLabel labelSurname = new JLabel("Surname:");

		mobilePhoneField = new JTextField();
		mobilePhoneField.setColumns(10);

		JLabel labelPosition = new JLabel("Position:");

		JLabel labelMobilePhone = new JLabel("Mobile Phone:");

		surnameField = new JTextField();
		surnameField.setColumns(10);

		labelAdress = new JLabel("Adress:");

		labelSalary = new JLabel("Salary:");

		salaryField = new JTextField();
		salaryField.setColumns(10);

		passwordField = new JPasswordField();

		String[] typeString = { "Administrator", "Driver", "Seller" };

		comboBoxType = new JComboBox<String>(typeString);

		String[] positionString = { "Administrator", "Driver", "Seller" };

		comboBoxPosition = new JComboBox<String>(positionString);

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

		birthdayField = new JDateChooser();

		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane
								.createSequentialGroup().addGap(
										40)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 604,
												Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
												.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addComponent(labelUserName).addComponent(labelName))
														.addGap(18)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
																.addComponent(userNameField, GroupLayout.PREFERRED_SIZE,
																		202, GroupLayout.PREFERRED_SIZE)
																.addComponent(nameField, GroupLayout.PREFERRED_SIZE,
																		202, GroupLayout.PREFERRED_SIZE)))
												.addGroup(gl_contentPane.createSequentialGroup()
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addComponent(labelBirthday).addComponent(labelAdress)
																.addComponent(lblNewLabel_2))
														.addGap(18)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE,
																		204, Short.MAX_VALUE)
																.addComponent(birthdayField, GroupLayout.DEFAULT_SIZE,
																		204, Short.MAX_VALUE)
																.addComponent(comboBoxType, 0, 204, Short.MAX_VALUE))))
												.addGap(28)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(labelMobilePhone).addComponent(labelPassword)
														.addComponent(labelSurname).addComponent(labelSalary)
														.addComponent(labelPosition))
												.addGap(18)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
														.addComponent(salaryField).addComponent(surnameField)
														.addComponent(passwordField, 103, 103, Short.MAX_VALUE)
														.addComponent(mobilePhoneField).addComponent(comboBoxPosition,
																0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
								.addGap(30))
						.addGroup(Alignment.TRAILING,
								gl_contentPane.createSequentialGroup().addContainerGap(254, Short.MAX_VALUE)
										.addComponent(addButton).addGap(18).addComponent(editButton).addGap(18)
										.addComponent(deleteButton).addGap(219)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(labelUserName)
								.addComponent(userNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(labelPassword).addComponent(passwordField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(labelName).addComponent(labelSurname).addComponent(surnameField,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(labelPosition).addComponent(comboBoxPosition,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(labelMobilePhone).addComponent(mobilePhoneField,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPane
												.createParallelGroup(Alignment.LEADING).addComponent(labelSalary)
												.addComponent(salaryField, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_2).addComponent(comboBoxType,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(labelBirthday).addComponent(birthdayField,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 63,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(labelAdress))))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(addButton)
								.addComponent(editButton).addComponent(deleteButton))
						.addGap(18).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
						.addContainerGap()));
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] { addButton, editButton, deleteButton });
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] { userNameField, nameField, mobilePhoneField,
				surnameField, salaryField, passwordField });
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL,
				new Component[] { labelUserName, labelName, lblNewLabel_2, labelBirthday, labelPassword, labelSurname,
						labelPosition, labelMobilePhone, labelAdress, labelSalary });

		adressField = new JTextArea();
		adressField.setColumns(20);
		adressField.setRows(4);
		scrollPane_1.setViewportView(adressField);

		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[] {"ID", "User Name", "Name", "Surname",
				"Type", "Position", "Birthday", "Mobile Phone", "Adress", "Salary" });
		table = new JTable();
		table.setSelectionForeground(Color.WHITE);
		table.setBackground(Color.WHITE);
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		scrollPane.setViewportView(table);
		scrollPane.setHorizontalScrollBarPolicy(
	                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		String workerId = "";
		String username = "";
		String password = "";
		String name = "";
		String surname = "";
		String type = "";
		String position = "";
		String birthday = "";
		String mobilePhone = "";
		String adress = "";
		String salaryy = "";
     
        try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "root", "toor");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        
        
        try {
        	preparedStatement = connect.prepareStatement("SELECT * FROM Workers");
        	ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
            	workerId = rs.getString("workerId");
            	username = rs.getString("username");
    			name = rs.getString("name");
    			surname = rs.getString("surname");
    			type = rs.getString("accountType");
    			position = rs.getString("possition");
    			birthday = rs.getString("birthday");
    			mobilePhone = rs.getString("mobile");
    			adress = rs.getString("address");
    			salaryy = rs.getString("salary");
                model.addRow(new Object[]{workerId, username, name, surname, type, position, birthday, mobilePhone, adress, salaryy});
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (i == 1) {
                System.out.println(i + " Record Found");
            } else {
                System.out.println(i + " Records Found");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addButton) {
			String username = userNameField.getText();
			String password = passwordField.getText();
			String name = nameField.getText();
			String surname = surnameField.getText();
			String type = (String) comboBoxType.getSelectedItem();
			String position = (String) comboBoxPosition.getSelectedItem();
			String birthday = birthdayField.getDateFormatString();
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

				if (dd.after(tod))
					valid = false;
				invalid = "Birthday Cannot be furture date";
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Fail to compare Date");
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
						Workers w = new Workers(type, username, password, name, surname, position, mobilePhone, adress,
								birthday, salary);
						;
						wj.createWorker(w);
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(this, "New worker ID:" + username + " had added.");
					// dispose();
					// staffSelect ss = new staffSelect();
					// ss.setVisible(true);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage());
				}
			} else
				JOptionPane.showMessageDialog(null, invalid);
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
}

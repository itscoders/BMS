package pl.psk.projekt.bms.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

public class WorkerWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField userNameField;
	private JTextField nameField;
	private JTextField mobilePhoneField;
	private JTextField textField_6;
	private JLabel lblNewLabel_4;
	private JTextField textField_8;
	private JLabel labelSalary;
	private JTextField salaryField;
	private JPasswordField passwordField;
	private JTable table;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;

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
		setTitle("Worker - Bus Management");
		setBounds(new Rectangle(100, 100, 700, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		userNameField = new JTextField();
		userNameField.setColumns(10);

		JLabel labelAdress = new JLabel("User Name:");

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

		textField_6 = new JTextField();
		textField_6.setColumns(10);

		lblNewLabel_4 = new JLabel("Adress:");

		textField_8 = new JTextField();
		textField_8.setColumns(10);

		labelSalary = new JLabel("Salary:");

		salaryField = new JTextField();
		salaryField.setColumns(10);

		passwordField = new JPasswordField();

		JComboBox comboBox = new JComboBox();

		JComboBox comboBox_1 = new JComboBox();

		JButton addButton = new JButton("Add");
		addButton.addActionListener(this);

		JButton editButton = new JButton("Edit");
		editButton.addActionListener(this);

		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);

		JScrollPane scrollPane = new JScrollPane();

		JDateChooser birthdayField = new JDateChooser();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(40, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(addButton).addGap(18)
										.addComponent(editButton).addGap(18).addComponent(deleteButton).addGap(231))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(
														scrollPane, Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_contentPane.createSequentialGroup()
																		.addGroup(gl_contentPane
																				.createParallelGroup(Alignment.LEADING)
																				.addComponent(labelAdress)
																				.addComponent(labelName))
																		.addGap(18)
																		.addGroup(gl_contentPane
																				.createParallelGroup(Alignment.TRAILING)
																				.addComponent(userNameField,
																						GroupLayout.PREFERRED_SIZE, 202,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(nameField,
																						GroupLayout.PREFERRED_SIZE, 202,
																						GroupLayout.PREFERRED_SIZE)))
																.addGroup(gl_contentPane.createSequentialGroup()
																		.addGroup(gl_contentPane
																				.createParallelGroup(Alignment.LEADING)
																				.addComponent(labelBirthday)
																				.addComponent(lblNewLabel_4)
																				.addComponent(lblNewLabel_2))
																		.addGap(18)
																		.addGroup(gl_contentPane
																				.createParallelGroup(Alignment.LEADING,
																						false)
																				.addComponent(birthdayField,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(textField_8,
																						Alignment.TRAILING, 141, 141,
																						Short.MAX_VALUE)
																				.addComponent(comboBox, 0,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE))))
														.addGap(30)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addComponent(labelMobilePhone)
																.addComponent(labelPassword).addComponent(labelSurname)
																.addComponent(labelSalary).addComponent(labelPosition))
														.addGap(18)
														.addGroup(gl_contentPane
																.createParallelGroup(Alignment.LEADING, false)
																.addComponent(salaryField).addComponent(textField_6)
																.addComponent(passwordField, 103, 103, Short.MAX_VALUE)
																.addComponent(mobilePhoneField).addComponent(comboBox_1,
																		0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
										.addGap(30)))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(labelAdress)
								.addComponent(userNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(labelPassword).addComponent(passwordField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(labelName).addComponent(labelSurname).addComponent(textField_6,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(labelPosition).addComponent(comboBox_1,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(labelMobilePhone)
												.addComponent(mobilePhoneField, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(labelSalary).addComponent(salaryField,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_2).addComponent(comboBox,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(labelBirthday).addGap(24)
														.addComponent(lblNewLabel_4))
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(birthdayField, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(textField_8,
																GroupLayout.PREFERRED_SIZE, 64,
																GroupLayout.PREFERRED_SIZE)))))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(addButton)
								.addComponent(editButton).addComponent(deleteButton))
						.addGap(34).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
						.addContainerGap()));
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL,
				new Component[] { labelAdress, labelName, lblNewLabel_2, labelBirthday, labelPassword, labelSurname,
						labelPosition, labelMobilePhone, lblNewLabel_4, labelSalary });
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] { passwordField, userNameField, nameField,
				mobilePhoneField, textField_6, textField_8, salaryField });

		table = new JTable();
		table.setSelectionForeground(Color.WHITE);
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "User Name", "Name", "Surname",
				"Type", "Position", "Birthday", "Mobile Phone", "Adress", "Salary" }));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addButton) {
			dispose();
			// WorkerWindow wd = new Worker();
			// wd.setVisible(true);
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

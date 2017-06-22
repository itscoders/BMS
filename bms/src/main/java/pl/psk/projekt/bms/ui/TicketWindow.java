package pl.psk.projekt.bms.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class TicketWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	
	PreparedStatement  preparedStatement;
    Connection connect;
    ResultSet rs;
    private JTable table;

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

	public TicketWindow() {
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
		contentPane = new JPanel();
		contentPane.setFocusable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel labelStartStation = new JLabel("Start Station:");

		JLabel labelType = new JLabel("Type:");

		JLabel labelStopStation = new JLabel("Stop Station:");

		JLabel labePrice = new JLabel("Price:");

		addButton = new JButton("Add");
		addButton.setBackground(Color.LIGHT_GRAY);
		addButton.addActionListener(this);

		editButton = new JButton("Edit");
		editButton.setBackground(Color.LIGHT_GRAY);
		editButton.addActionListener(this);

		deleteButton = new JButton("Delete");
		deleteButton.setBackground(Color.LIGHT_GRAY);
		deleteButton.addActionListener(this);
		
		JComboBox comboBoxStartStation = new JComboBox();
		
		JComboBox comboBoxStopStation = new JComboBox();
		
		JComboBox comboBoxType = new JComboBox();
		
		JSpinner spinnerPrice = new JSpinner();
		spinnerPrice.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(6)));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelStartStation)
								.addComponent(labelType))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(comboBoxStartStation, 0, 202, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(comboBoxType, 0, 202, Short.MAX_VALUE)))
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelStopStation)
								.addComponent(labePrice))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxStopStation, 0, 202, Short.MAX_VALUE)
								.addComponent(spinnerPrice, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
							.addGap(30))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(addButton)
							.addGap(18)
							.addComponent(editButton)
							.addGap(18)
							.addComponent(deleteButton)
							.addGap(219))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 636, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelStartStation)
						.addComponent(labelStopStation)
						.addComponent(comboBoxStartStation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxStopStation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelType)
						.addComponent(labePrice)
						.addComponent(comboBoxType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinnerPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(addButton)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(editButton)
							.addComponent(deleteButton)))
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {labelStartStation, labelType, labelStopStation, labePrice});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {addButton, editButton, deleteButton});

		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addButton) {
			/*String name = nameField.getText();
			String surname = surnameField.getText();
			String birthday = ((JTextField) birthdayField.getDateEditor().getUiComponent()).getText();
			String email = emailField.getText();
			String mobilePhone = mobilePhoneField.getText();
			String street = streetField.getText();
			String houseNumber = houseNumberField.getText();
			String postCode = postCodeField.getText();
			String city = cityField.getText();

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
					try {
						BuyerJDBC wj = new BuyerJDBC();
						Buyer w = new Buyer(name, surname, birthday, email, mobilePhone, street, houseNumber, postCode, city);
						wj.createBuyer(w);
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(this, "New Buyer: " + name + surname + " had added.");
					// dispose();
					// staffSelect ss = new staffSelect();
					// ss.setVisible(true);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage());
				}
			} else
				JOptionPane.showMessageDialog(null, invalid);
		
			Update_table();*/
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
 try{
     try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "root", "toor");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	 preparedStatement = connect.prepareStatement("SELECT * FROM Buyer");
 	 rs = preparedStatement.executeQuery();
     table.setModel(DbUtils.resultSetToTableModel(rs));
 }
 catch(Exception e){
 JOptionPane.showMessageDialog(null, e);
 }
 finally {
         
         try{
             rs.close();
             preparedStatement.close();
             
         }
         catch(Exception e){
             
         }
     }
 }
}


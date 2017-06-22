package pl.psk.projekt.bms.ui;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.Color;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import pl.psk.projekt.bms.dbobjects.Workers;
import pl.psk.projekt.bms.jdbc.WorkersJDBC;

import java.awt.Font;
import java.awt.Rectangle;

public class LoginWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JButton prevButton = new JButton("Prev");
	JButton loginButton = new JButton("Login");
	JButton forgetButton = new JButton("Forget Password");
	JTextField userNameField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JLabel labelUserName = new JLabel("User Name:");
	JLabel labelPassword = new JLabel("Password:");

	StartWindow startWindow;

	public LoginWindow(StartWindow startWindow) {
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
		
		setTitle("Login - Bus Management");

		this.startWindow = startWindow;

		setSize(500, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		labelUserName.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelUserName.setForeground(Color.GRAY);
		labelPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelPassword.setForeground(Color.GRAY);
		loginButton.setBackground(Color.LIGHT_GRAY);
		loginButton.addActionListener(this);
		prevButton.setBackground(Color.LIGHT_GRAY);
		prevButton.addActionListener(this);
		forgetButton.setBackground(Color.LIGHT_GRAY);
		forgetButton.addActionListener(this);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(61)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(labelUserName, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(userNameField, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(labelPassword, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(prevButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
										.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(38)
										.addComponent(forgetButton, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)))
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE))))
					.addGap(87))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(91)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelUserName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(userNameField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelPassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(prevButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(forgetButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(91))
		);
		getContentPane().setLayout(groupLayout);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == loginButton) {
			
			String username = userNameField.getText();
	         String password = passwordField.getText();
	          
	         try{
	             WorkersJDBC wj = new WorkersJDBC();
	             
	             
	             String name = wj.workersLog(username,password);
                 if (name == null){
                     JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                 } else {
                     JOptionPane.showMessageDialog(this, "Login Success\n\n Worker: "+ username);
                     dispose();
                     ManagementWindow mw = new ManagementWindow();
                     mw.setVisible(true);
	                 }
	                 
	             } catch (Exception ex) {
	                 JOptionPane.showMessageDialog(this, ex.getMessage());
	             }
			/*try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "root", "toor");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from workers where ");

			} catch (SQLException g) {
				JOptionPane.showMessageDialog(this, "Wrong password");
			} catch (Exception eq) {
				JOptionPane.showMessageDialog(this, "Wrong password");
			}*/

		}

		if (e.getSource() == prevButton) {
			this.setVisible(false);
			startWindow.setVisible(true);
		}

		if (e.getSource() == forgetButton) {
			this.setVisible(false);
			RetrieveWindow rw = new RetrieveWindow(this);
			rw.setVisible(true);
		}

	}
}
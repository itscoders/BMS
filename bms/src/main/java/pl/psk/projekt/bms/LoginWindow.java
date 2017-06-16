package pl.psk.projekt.bms;


import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.Color;


public class LoginWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	JButton prevButton = new JButton("Prev");
	JButton loginButton = new JButton("Login");
	JTextField userNameField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JLabel labelUserName = new JLabel("User Name");
	JLabel labelPassword = new JLabel("Password");

	StartWindow startWindow;

	public LoginWindow(StartWindow startWindow) {

		this.startWindow = startWindow;

		setSize(600, 450);
		setVisible(true);
		setLayout(null);
		setLocationRelativeTo(null);

		labelUserName.setBounds(30, 65, 75, 50);
		labelUserName.setForeground(Color.GRAY);
		add(labelUserName);
		labelPassword.setBounds(30, 140, 75, 50);
		labelPassword.setForeground(Color.GRAY);
		add(labelPassword);
		userNameField.setBounds(125, 75, 257, 32);
		add(userNameField);
		passwordField.setBounds(125, 150, 257, 32);
		add(passwordField);
		loginButton.setBounds(300, 250, 90, 30);
		add(loginButton);
		loginButton.setBackground(Color.GRAY);
		loginButton.addActionListener(this);
		prevButton.setBounds(100, 250, 90, 30);
		prevButton.setBackground(Color.GRAY);
		prevButton.addActionListener(this);
		add(prevButton);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == loginButton) {

			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms_db","root", "");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from workers where ");
				

			
	
			} catch (SQLException g) {
				JOptionPane.showMessageDialog(this, "Wrong password");
			} catch (Exception eq) {
				JOptionPane.showMessageDialog(this, "Wrong password");
			}

		}

		if (e.getSource() == prevButton) {
			this.setVisible(false);
			startWindow.setVisible(true);
		}

	}
}
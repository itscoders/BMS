package pl.psk.projekt.bms.ui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import pl.psk.projekt.bms.dbobjects.Workers;
import pl.psk.projekt.bms.jdbc.WorkersJDBC;

import java.awt.Font;

public class LoginWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JButton prevButton = new JButton("Prev");
	JButton loginButton = new JButton("Login");
	JButton forgetButton = new JButton("Forget Password");
	JTextField userNameField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JLabel labelUserName = new JLabel("User Name:");
	JLabel labelPassword = new JLabel("Password:");
	

	public LoginWindow() {
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

		setTitle("Login - Bus Management");

		setSize(500, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		labelUserName.setBounds(49, 106, 75, 14);
		labelUserName.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelUserName.setForeground(Color.WHITE);
		labelPassword.setBounds(49, 144, 75, 14);
		labelPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelPassword.setForeground(Color.WHITE);
		loginButton.setBounds(295, 178, 90, 30);
		loginButton.setBackground(Color.LIGHT_GRAY);
		loginButton.addActionListener(this);
		prevButton.setBounds(128, 178, 90, 30);
		prevButton.setBackground(Color.LIGHT_GRAY);
		prevButton.addActionListener(this);
		forgetButton.setBounds(126, 219, 168, 30);
		forgetButton.setBackground(Color.LIGHT_GRAY);
		forgetButton.addActionListener(this);
		getContentPane().setLayout(null);
		getContentPane().add(labelUserName);
		userNameField.setBounds(128, 97, 257, 32);
		getContentPane().add(userNameField);
		getContentPane().add(labelPassword);
		getContentPane().add(prevButton);
		getContentPane().add(loginButton);
		passwordField.setBounds(128, 135, 257, 32);
		getContentPane().add(passwordField);
		getContentPane().add(forgetButton);

		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(this.getClass().getResource("/login.png")));
		background.setBounds(0, 0, 494, 371);
		getContentPane().add(background);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == loginButton) {

			String username = userNameField.getText();
			String password = passwordField.getText();

			try {
				WorkersJDBC wj = new WorkersJDBC();

				Workers w = wj.workersLog(username, password);
				if (w.getName() == null) {
					JOptionPane.showMessageDialog(this, "Invalid Username or Password");
				} else {
					JOptionPane.showMessageDialog(this,
							"Login Success\n\n Worker: " + w.getName() + " " + w.getSurname());
					dispose();

					System.err.println(w.getAccountType());
					if (w.getAccountType().equals("Administrator")) {
						ManagementWindow mw = new ManagementWindow(w);
						mw.setVisible(true);
					}
					if (w.getAccountType().equals("Driver")) {
						DriverWindow dw = new DriverWindow(w);
						dw.setVisible(true);
					}

					if (w.getAccountType().equals("Seller")) {
						SellerWindow sw = new SellerWindow(w);
						sw.setVisible(true);
					}
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Wrong password");
			}

		}

		if (e.getSource() == prevButton) {
			StartWindow st = new StartWindow();
			st.setVisible(true);
			this.dispose();
		}

		if (e.getSource() == forgetButton) {
			this.setVisible(false);
			RetrieveWindow rw = new RetrieveWindow();
			rw.setVisible(true);
		}

	}
}
package pl.psk.projekt.bms.ui;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.EventQueue;

import pl.psk.projekt.bms.dbobjects.Workers;
import pl.psk.projekt.bms.jdbc.CreateDB;
import pl.psk.projekt.bms.jdbc.WorkersJDBC;

import java.awt.Font;

/**
 * Klasa LoginWindow zawiera komponenty do stworzenia UI okna startowego
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
public class LoginWindow extends JFrame implements ActionListener {

	/** Zmienna określająca unikalny numer w celu serializacji. */
	private static final long serialVersionUID = 1L;

	/** Inicjalizacja obiektu klasy JButton. */
	private JButton prevButton = new JButton("Prev");
	/** Inicjalizacja obiektu klasy JButton. */
	private JButton loginButton = new JButton("Login");
	/** Inicjalizacja obiektu klasy JButton. */
	private JButton forgetButton = new JButton("Forget Password");
	/** Inicjalizacja obiektu klasy JButton. */
	private JButton buttonCreateDB = new JButton("Create DataBase");
	/** Inicjalizacja obiektu klasy JTextField. */
	private JTextField userNameField = new JTextField();
	/** Inicjalizacja obiektu klasy JPasswordField. */
	private JPasswordField passwordField = new JPasswordField();
	/** Inicjalizacja obiektu klasy JLabel. */
	private JLabel labelUserName = new JLabel("User Name:");
	/** Inicjalizacja obiektu klasy JLabel. */
	private JLabel labelPassword = new JLabel("Password:");
	/** Pole klasy Workers. */
	Workers w;

	/**
	 * Konstruktor bezparametrowy klasy LoginWindow odpowiedzialny za
	 * inicializację komponentów biblioteki Swing. Komponenty definiowane:
	 * Jlabel, JButton, JTextField, JPasswordField JPanel, JFrame - dla tych
	 * komponentów ustawiane są wymiary, fonty, kolory.
	 * 
	 * @see JTextField
	 * @see JPasswordField
	 * @see JPanel
	 * @see JButton
	 * @see JFrame
	 * @see JLabel
	 */
	public LoginWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		forgetButton.setBounds(172, 219, 168, 30);
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
		buttonCreateDB.setBackground(Color.LIGHT_GRAY);
		buttonCreateDB.setBounds(172, 261, 168, 30);

		getContentPane().add(buttonCreateDB);

		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(this.getClass().getResource("/login.png")));
		background.setBounds(0, 0, 494, 371);
		getContentPane().add(background);

	}

	/**
	 * Przesłonięta metoda służąca do określania zachowania aplikacji po
	 * kliknięciu na dany komponent przez użytkownika. W metodzie tej określono
	 * działanie dla przycisków obsługujących logowanie. W przypadku kliknięcia
	 * na przycisk 'loginButton' porównane zostają dane - nazwa użytkownika i
	 * hasło z danymi w bazi. Jeśli istnieją w bazie zostaje otwarte nowe okno
	 * poprzez stworzenie obiektu klasy ManagementWindow, DriverWindow lub
	 * SellerWindow w zależności do jakiego typu pracownik należy -
	 * Administrator, Kierowca lub Sprzedawca, a następnie zniczenie aktualnego
	 * okna. Po kliknięciu na przycisk 'prevButton' otwierane jest poprzednie
	 * okno aplikacji. Tworzony jest obiekt klasy StartWindow, ustawiony na
	 * widoczny i zniszczenie aktualnego. Po kliknięciu przycisku 'forgetButton'
	 * uruchamiane jest okno do odzyskiwania hasła użytkownika. Tworzony jest
	 * obiekt klasy ForgetWindow, ustawiany na widoczny, a aktualny niszczony Po
	 * klinięciu przycisku 'buttonCreateDB' w celu stworzenia bazy danych.
	 * Przycisk ten stworzony jest w celu rozpoczęcia korzystania z aplikacji.
	 * Do informowani użytkownika oraz wyświetlania okien dialogowych
	 * wykorzystane zostały komponenty JOptionPane.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == loginButton) {

			String username = userNameField.getText();
			String password = passwordField.getText();

			try {
				WorkersJDBC wj = new WorkersJDBC();

				w = wj.workersLog(username, password);
				if (w.getName() == null) {
					JOptionPane.showMessageDialog(this, "Invalid Username or Password");
				} else {

					JOptionPane.showMessageDialog(this,
							"Login Success\n\n Worker: " + w.getName() + " " + w.getSurname());
					dispose();

					if (w.getAccountType().equals("Administrator")) {

						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									ManagementWindow mw = new ManagementWindow(w);
									mw.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
					if (w.getAccountType().equals("Driver")) {

						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									DriverWindow dw = new DriverWindow(w);
									dw.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}

					if (w.getAccountType().equals("Seller")) {

						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									SellerWindow sw = new SellerWindow(w);
									sw.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
				}

			} catch (Exception ex) {
				ex.printStackTrace();
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

		if (e.getSource() == buttonCreateDB) {

			int value = JOptionPane.showConfirmDialog(null,
					"You want create new DB? IF You choose yes curently DB (IF EXIST) been dealete.", "Question Box",
					JOptionPane.YES_NO_OPTION);
			if (value == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "OK. Start Create ");
				try {
					new CreateDB(value);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Ok. Create DB Not Start");
			}
		}

	}
}
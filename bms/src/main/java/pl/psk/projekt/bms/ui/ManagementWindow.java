package pl.psk.projekt.bms.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pl.psk.projekt.bms.dbobjects.Workers;
import pl.psk.projekt.bms.jdbc.CreateDB;
import pl.psk.projekt.bms.jdbc.DropDB;
import pl.psk.projekt.bms.jdbc.InsertDB;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.Font;

/**
 * Klasa ManagementWindow zawiera komponenty do stworzenia UI okna startowego
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
public class ManagementWindow extends JFrame implements ActionListener {

	/** Zmienna określająca unikalny numer w celu serializacji. */
	private static final long serialVersionUID = 1L;

	/** Deklaracja obiektu klasy JPanel. */
	private JPanel contentPane;
	/** Deklaracja obiektu klasy JPanel. */
	private JPanel panel;
	/** Deklaracja obiektu klasy JButton. */
	private JButton logoutButton;
	/** Deklaracja obiektu klasy JButton. */
	private JButton scheduleButton;
	/** Deklaracja obiektu klasy JButton. */
	private JButton busButton;
	/** Deklaracja obiektu klasy JButton. */
	private JButton lineButton;
	/** Deklaracja obiektu klasy JButton. */
	private JButton workerButton;
	/** Deklaracja obiektu klasy JButton. */
	private JButton transactionButton;
	/** Deklaracja obiektu klasy JButton. */
	private JButton buyerButton;
	/** Deklaracja obiektu klasy JButton. */
	private JButton databaseDropButton;
	/** Deklaracja obiektu klasy JButton. */
	private JButton databaseCreateButton;
	/** Deklaracja obiektu klasy JButton. */
	private JButton databaseInsertButton;
	/** Deklaracja obiektu klasy JLabel. */
	private JLabel logLabel;
	/** Pole klasy Workers. */
	private Workers w;
	
	/**
	 * Konstruktor klasy ManagementWindow odpowiedzialny za
	 * inicializację komponentów biblioteki Swing. Komponenty definiowane:
	 * Jlabel, JButton, JPanel, JFrame - dla tych
	 * komponentów ustawiane są wymiary, fonty, kolory.
	 * Komponenty zostały rozmieszczone przy pomocy GroupLayout.
	 * wywoływana jest metoda welcomeLabel.
	 * 
	 * @param w - parametr Workers - dane zalagowanego użytkownika
	 *            
	 * @see JPanel
	 * @see JButton
	 * @see JFrame
	 * @see JLabel
	 */
	public ManagementWindow(Workers w) {
		
		this.w=w;

		setVisible(true);

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
		setTitle("Management - Bus Management");
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(new Rectangle(50, 100, 1000, 650));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setOpaque(true);
		panel.setBackground(new Color(0, 0, 0, 0));
		panel.setBounds(0, 0, 1000, 620);
		contentPane.add(panel);

		logoutButton = new JButton("Logout");
		logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logout.png")));
		logoutButton.addActionListener(this);

		scheduleButton = new JButton("Schedule");
		scheduleButton.addActionListener(this);
		scheduleButton.setBackground(Color.LIGHT_GRAY);

		busButton = new JButton("Bus");
		busButton.addActionListener(this);
		busButton.setBackground(Color.LIGHT_GRAY);

		lineButton = new JButton("Line");
		lineButton.addActionListener(this);
		lineButton.setBackground(Color.LIGHT_GRAY);

		workerButton = new JButton("Worker");
		workerButton.addActionListener(this);
		workerButton.setBackground(Color.LIGHT_GRAY);

		transactionButton = new JButton("Transaction");
		transactionButton.addActionListener(this);
		transactionButton.setBackground(Color.LIGHT_GRAY);

		buyerButton = new JButton("Buyer");
		buyerButton.addActionListener(this);
		buyerButton.setBackground(Color.LIGHT_GRAY);

		databaseCreateButton = new JButton("Database Create");
		databaseCreateButton.addActionListener(this);
		databaseCreateButton.setBackground(Color.LIGHT_GRAY);

		databaseDropButton = new JButton("Database Drop");
		databaseDropButton.addActionListener(this);
		databaseDropButton.setBackground(Color.LIGHT_GRAY);
		
		logLabel = new JLabel();
		logLabel.setForeground(Color.WHITE);
		logLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		welcomeLabel();
		
		databaseInsertButton = new JButton("Database Insert");
		databaseInsertButton.addActionListener(this);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(databaseCreateButton)
								.addGap(18)
								.addComponent(databaseDropButton))
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addComponent(workerButton, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
									.addComponent(busButton, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
									.addComponent(buyerButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(18)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lineButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(transactionButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(scheduleButton, Alignment.TRAILING)))
							.addComponent(databaseInsertButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(logLabel)
							.addPreferredGap(ComponentPlacement.RELATED, 532, Short.MAX_VALUE)
							.addComponent(logoutButton)))
					.addGap(28))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(logoutButton)
						.addComponent(logLabel))
					.addPreferredGap(ComponentPlacement.RELATED, 321, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(scheduleButton)
						.addComponent(workerButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lineButton)
						.addComponent(busButton))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(transactionButton)
						.addComponent(buyerButton))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(databaseDropButton)
						.addComponent(databaseCreateButton))
					.addGap(18)
					.addComponent(databaseInsertButton)
					.addGap(18))
		);
		gl_panel.linkSize(SwingConstants.VERTICAL, new Component[] {scheduleButton, busButton, lineButton, workerButton, transactionButton, buyerButton, databaseCreateButton, databaseDropButton, databaseInsertButton});
		gl_panel.linkSize(SwingConstants.HORIZONTAL, new Component[] {scheduleButton, busButton, lineButton, workerButton, transactionButton, buyerButton, databaseCreateButton, databaseDropButton});
		panel.setLayout(gl_panel);

		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(this.getClass().getResource("/managment.png")));
		background.setBounds(0, 0, 1000, 620);
		contentPane.add(background);
	}

	/**
	 * Przesłonięta metoda służąca do określania zachowania aplikacji po
	 * kliknięciu na dany komponent przez użytkownika. W metodzie tej określono
	 * działanie dla przycisków znajdujących się w oknie dla Administratora. 
	 * W przypadku kliknięcia na przycisk 'workerButton' uruchamiane jest okno do zarządzania pracownikami. Tworzony jest nowy wątek, w którym zaś tworzony jest obiekt klasy WorkerWindow, ustawiany na widoczny, a aktualny niszczony.
	 * W przypadku kliknięcia na przycisk 'scheduleButton' uruchamiane jest okno do zarządzania rozkładem. Tworzony jest nowy wątek, w którym zaś tworzony jest obiekt klasy ScheduleWindow, ustawiany na widoczny, a aktualny niszczony. 
	 * W przypadku kliknięcia na przycisk 'busButton' uruchamiane jest okno do zarządzania busami. Tworzony jest nowy wątek, w którym zaś tworzony jest obiekt klasy BusWindow, ustawiany na widoczny, a aktualny niszczony. 
	 * W przypadku kliknięcia na przycisk 'lineButton' uruchamiane jest okno do zarządzania liniami busów. Tworzony jest nowy wątek, w którym zaś tworzony jest obiekt klasy LineWindow, ustawiany na widoczny, a aktualny niszczony. 
	 * W przypadku kliknięcia na przycisk 'buyerButton' uruchamiane jest okno do zarządzania kupującymi. Tworzony jest nowy wątek, w którym zaś tworzony jest obiekt klasy BuyerWindow, ustawiany na widoczny, a aktualny niszczony. 
	 * W przypadku kliknięcia na przycisk 'transactionButton' uruchamiane jest okno do zarządzania transakcjami. Tworzony jest nowy wątek, w którym zaś tworzony jest obiekt klasy TransactionWindow, ustawiany na widoczny, a aktualny niszczony. 
	 * W przypadku kliknięcia na przycisk 'logoutButton' następuje wylogowanie użytkownika i uruchamiane jest okno logowania. Tworzony jest nowy wątek, w którym zaś tworzony jest obiekt klasy LoginWindow, ustawiany na widoczny, a aktualny niszczony. 
	 * W przypadku kliknięcia na przycisk 'databaseCreateButton' tworzona jest baza danych.
	 * W przypadku kliknięcia na przycisk 'databaseDropButton' usuwana  jest baza danych.
	 * W przypadku kliknięcia na przycisk 'databaseInsertButton' następuje załadowanie do bazy danych przygotowanych danych - insert`ów.
	 *    
	 * Do informowani użytkownika oraz wyświetlania okien dialogowych
	 * wykorzystane zostały komponenty JOptionPane.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == workerButton) {
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						WorkerWindow wd = new WorkerWindow();
						wd.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

		}
		

		if (e.getSource() == scheduleButton) {
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ScheduleWindow sw = new ScheduleWindow();
						sw.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		if (e.getSource() == busButton) {
			
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						BusWindow bw = new BusWindow();
						bw.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		}

		if (e.getSource() == lineButton) {
			
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						LineWindow lw = new LineWindow();
						lw.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		}

		if (e.getSource() == buyerButton) {
			
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						BuyerWindow bw = new BuyerWindow();
						bw.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		}

		if (e.getSource() == transactionButton) {
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TransactionWindow tw = new TransactionWindow(w);
						tw.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		}
		
		if (e.getSource() == logoutButton) {
			String username = w.getUsername();
			JOptionPane.showMessageDialog(this, "Worker: " + username + " was successfully logged out.");
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						LoginWindow lw = new LoginWindow();
						lw.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			this.dispose();
		}

		if (e.getSource() == databaseCreateButton) {
			try {
				new CreateDB();
				new InsertDB();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}

		if (e.getSource() == databaseDropButton) {
			try {
				new DropDB();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}
		
		if (e.getSource() == databaseInsertButton) {
			try {
				new InsertDB();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}
	}
	
	/** Metoda bezparametrowa odpowiedzialna za wyświetlenie imienia i nazwiska aktualnie zalogowanego użytkownika.
	 * Metoda jest typu void - nie zwraca żadnej wartości. 
	 */
	public void welcomeLabel() {
		String name = w.getName();
		String surname = w.getSurname();
		logLabel.setText("Welcome " + name + " " + surname);
		
	}
}

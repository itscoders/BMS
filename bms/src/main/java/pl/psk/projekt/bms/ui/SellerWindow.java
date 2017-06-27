package pl.psk.projekt.bms.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import net.proteanit.sql.DbUtils;
import pl.psk.projekt.bms.dbobjects.Workers;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

/**
 * Klasa SellerWindow zawiera komponenty do stworzenia UI okna startowego
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
public class SellerWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton buyerButton;
	private JButton transactionButton;
	private JScrollPane scrollPane;
	private JTable tableFilter;
	private DefaultTableModel modelFilter;
	private JTextField textField;
	private JButton logoutButton;
	private JLabel logLabel;
	private Workers w;
	PreparedStatement preparedStatement;
	Connection connect;
	ResultSet rs;
	private JLabel background;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workers w = new Workers();
					SellerWindow frame = new SellerWindow(w);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SellerWindow(Workers w) {
		
		this.w=w;

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
		setTitle("Sell - Ticket Seller System");
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(new Rectangle(50, 100, 600, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		buyerButton = new JButton("Buyer");
		buyerButton.setBounds(383, 107, 89, 23);
		buyerButton.addActionListener(this);
		buyerButton.setBackground(Color.LIGHT_GRAY);

		transactionButton = new JButton("Transaction");
		transactionButton.setBounds(490, 107, 89, 23);
		transactionButton.addActionListener(this);
		transactionButton.setBackground(Color.LIGHT_GRAY);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 148, 564, 207);

		textField = new JTextField();
		textField.setBounds(149, 107, 199, 23);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = textField.getText();
				filter(query);
			}
		});
		textField.setColumns(10);

		JLabel lebelSearch = new JLabel("Search in your transaction:");
		lebelSearch.setBounds(15, 107, 130, 23);

		logoutButton = new JButton("Logout");
		logoutButton.setBounds(490, 18, 89, 29);
		logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logout.png")));
		logoutButton.addActionListener(this);

		logLabel = new JLabel();
		logLabel.setBounds(15, 16, 341, 25);
		logLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		welcomeLabel();
		
		
		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");
			preparedStatement = connect.prepareStatement("SELECT * FROM Transaction WHERE IdSeller=" + w.getWorkerId());
			rs = preparedStatement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		modelFilter = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);
		contentPane.setLayout(null);

		tableFilter = new JTable();
		tableFilter.setModel(modelFilter);
		
		scrollPane.setViewportView(tableFilter);
		contentPane.add(scrollPane);
		contentPane.add(logLabel);
		contentPane.add(logoutButton);
		contentPane.add(lebelSearch);
		contentPane.add(textField);
		contentPane.add(buyerButton);
		contentPane.add(transactionButton);
		
		background = new JLabel("");
		background.setIcon(new ImageIcon(this.getClass().getResource("/worker.png")));
		background.setBounds(0, 0, 594, 371);
		contentPane.add(background);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

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
			LoginWindow lw = new LoginWindow();
			lw.setVisible(true);
			this.dispose();

		}

	}
	
	public void welcomeLabel() {
		String name = w.getName();
		String surname = w.getSurname();
		logLabel.setText("Welcome " + name + " " + surname);
		
	}
	
	private void filter(String query) {

		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(modelFilter);
		tableFilter.setRowSorter(trs);

		trs.setRowFilter(RowFilter.regexFilter(query));
	}
}

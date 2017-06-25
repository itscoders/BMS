package pl.psk.projekt.bms.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class SellerWindow  extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton buyerButton;
	private JButton raportButton;
	private JButton transactionButton;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SellerWindow frame = new SellerWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SellerWindow() {

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
		setBounds(new Rectangle(50, 100, 400, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		buyerButton = new JButton("Buyer");
		buyerButton.addActionListener(this);
		buyerButton.setBackground(Color.LIGHT_GRAY);

		raportButton = new JButton("Raport");
		raportButton.setBackground(Color.LIGHT_GRAY);
		raportButton.addActionListener(this);
		
		transactionButton = new JButton("Transaction");
		transactionButton.addActionListener(this);
		transactionButton.setBackground(Color.LIGHT_GRAY);
		
		scrollPane = new JScrollPane();
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lebelSearch = new JLabel("Search in your transaction:");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(buyerButton)
					.addGap(45)
					.addComponent(transactionButton)
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addComponent(raportButton)
					.addGap(23))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(91)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(94, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(127)
					.addComponent(lebelSearch)
					.addContainerGap(127, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(buyerButton)
						.addComponent(transactionButton)
						.addComponent(raportButton))
					.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
					.addComponent(lebelSearch)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {buyerButton, raportButton, transactionButton});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {buyerButton, raportButton, transactionButton});
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {


		if (e.getSource() == buyerButton) {
			BuyerWindow bw = new BuyerWindow();
			bw.setVisible(true);
		}
		
		if (e.getSource() == transactionButton) {
			TransactionWindow tw = new TransactionWindow();
			tw.setVisible(true);
		}

		if (e.getSource() == raportButton) {
			
		}


	}
}

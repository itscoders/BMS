package pl.psk.projekt.bms.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;

public class RetrieveWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField userNameField;

	 /*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { RetrieveWindow frame = new
	 * RetrieveWindow(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */
	LoginWindow loginWindow;
	private JButton prevButton;

	public RetrieveWindow(LoginWindow loginWindow) {

		this.loginWindow = loginWindow;

		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setResizable(false);
		setTitle("Retrieve Password - Bus Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel labelUserName = new JLabel("User Name:");

		userNameField = new JTextField();
		userNameField.setColumns(10);

		prevButton = new JButton("Prev");
		prevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				RetrieveWindow.this.setVisible(false);
				loginWindow.setVisible(true);
			}
		});
		prevButton.setBackground(Color.LIGHT_GRAY);
		// prevButton.addActionListener(this);

		JButton retrieveButton = new JButton("Retrieve Password");
		retrieveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String username = userNameField.getText();
				/*
				try {
					Member m = mt.passRecover(username);
					if (m == null) {
						JOptionPane.showMessageDialog(this, "Incorrect Information, Fail to retrieve password.");
					} else {
						JOptionPane.showMessageDialog(this,
								"Username :" + m.getUsername() + "\nPassword :" + m.getPassword());
						dispose();
						loginWindow.setVisible(true);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage());
				}
				*/
			}
			

		});
		retrieveButton.setBackground(Color.LIGHT_GRAY);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(49).addComponent(labelUserName)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING, false).addGroup(
										gl_contentPane.createSequentialGroup().addComponent(prevButton)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(retrieveButton))
								.addComponent(userNameField, GroupLayout.PREFERRED_SIZE, 247,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(68, Short.MAX_VALUE)));
		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_contentPane.createSequentialGroup().addContainerGap(85, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE, false)
										.addComponent(labelUserName).addComponent(userNameField,
												GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
								.addGap(34).addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(prevButton).addComponent(retrieveButton))
								.addGap(76)));
		contentPane.setLayout(gl_contentPane);
	}

}

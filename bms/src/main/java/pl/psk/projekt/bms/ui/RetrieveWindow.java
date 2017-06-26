package pl.psk.projekt.bms.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pl.psk.projekt.bms.component.Mail;
import pl.psk.projekt.bms.dbobjects.Workers;
import pl.psk.projekt.bms.jdbc.WorkersJDBC;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

public class RetrieveWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField userNameField;
	private JButton retrieveButton;
	private JButton prevButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RetrieveWindow frame = new RetrieveWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RetrieveWindow() {
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
		prevButton.setBackground(Color.LIGHT_GRAY);
		prevButton.addActionListener(this);

		retrieveButton = new JButton("Retrieve Password");
		retrieveButton.addActionListener(this);
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

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == prevButton) {
			LoginWindow lw = new LoginWindow();
			lw.setVisible(true);
			this.dispose();

		}

		if (e.getSource() == retrieveButton) {

			String username = userNameField.getText();

			try {
				WorkersJDBC wj = new WorkersJDBC();
				Workers w = new Workers();
				w.setUsername(username);
				w = wj.passRecover(w);
				String email = w.getEmail();
				String name = w.getName();
				String password = w.getPassword();
				if (w == null) {
					JOptionPane.showMessageDialog(this, "Incorrect Information, Fail to retrieve password.");
				} else {
					Mail m = new Mail();
					String contentMail = "HI " + name + "\nYou recently requested to recover your password for BMS SYSTEM account.\n" + "Your Password:" + password + "\n" + "Thanks,\n" + "ITS CODERS & PabloIT from BMS team.";
					m.sendMail(email, "Recover password in BMS SYSTEM", contentMail);
					JOptionPane.showMessageDialog(this, "The password has been sent to the email: " + w.getEmail());
					LoginWindow lw = new LoginWindow();
					lw.setVisible(true);
					this.dispose();
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}

		}
	}
}

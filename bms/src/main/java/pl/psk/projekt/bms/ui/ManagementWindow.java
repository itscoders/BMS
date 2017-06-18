package pl.psk.projekt.bms.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pl.psk.projekt.bms.jdbc.CreateDB;

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

public class ManagementWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton workerButton;
	private JButton scheduleButton;
	private JButton busButton;
	private JButton btnLine;
	private JButton ticketButton;
	private JButton userButton;
	private JButton raportButton;
	private JButton databaseButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagementWindow frame = new ManagementWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ManagementWindow() {

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
		setTitle("Management - Bus Management");
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(new Rectangle(100, 100, 750, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		workerButton = new JButton("Worker");
		workerButton.addActionListener(this);
		workerButton.setBackground(Color.LIGHT_GRAY);

		scheduleButton = new JButton("Schedule");
		scheduleButton.addActionListener(this);
		scheduleButton.setBackground(Color.LIGHT_GRAY);

		busButton = new JButton("Bus");
		busButton.addActionListener(this);
		busButton.setBackground(Color.LIGHT_GRAY);

		ticketButton = new JButton("Ticket");
		ticketButton.addActionListener(this);
		ticketButton.setBackground(Color.LIGHT_GRAY);

		userButton = new JButton("User");
		userButton.addActionListener(this);
		userButton.setBackground(Color.LIGHT_GRAY);

		raportButton = new JButton("Raport");
		raportButton.setBackground(Color.LIGHT_GRAY);
		raportButton.addActionListener(this);

		btnLine = new JButton("Line");
		btnLine.setBackground(Color.LIGHT_GRAY);
		btnLine.addActionListener(this);
		
		databaseButton = new JButton("Database");
		databaseButton.addActionListener(this);
		databaseButton.setBackground(Color.LIGHT_GRAY);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(42, Short.MAX_VALUE)
						.addComponent(workerButton).addGap(18).addComponent(scheduleButton).addGap(18)
						.addComponent(busButton).addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(databaseButton)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnLine).addGap(18)
										.addComponent(ticketButton).addGap(18).addComponent(userButton).addGap(18)
										.addComponent(raportButton)))
						.addGap(31)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(workerButton)
								.addComponent(scheduleButton).addComponent(busButton).addComponent(btnLine)
								.addComponent(ticketButton).addComponent(userButton).addComponent(raportButton))
						.addGap(18).addComponent(databaseButton).addContainerGap(386, Short.MAX_VALUE)));
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] { workerButton, scheduleButton, busButton,
				ticketButton, userButton, raportButton, btnLine, databaseButton });
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] { workerButton, scheduleButton, busButton,
				ticketButton, userButton, raportButton, btnLine, databaseButton });
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == workerButton) {
			WorkerWindow wd = new WorkerWindow();
			wd.setVisible(true);
		}

		if (e.getSource() == scheduleButton) {
			ScheduleWindow sw = new ScheduleWindow();
			sw.setVisible(true);
		}

		if (e.getSource() == busButton) {
			BusWindow bw = new BusWindow();
			bw.setVisible(true);
		}

		if (e.getSource() == btnLine) {
			LineWindow lw = new LineWindow();
			lw.setVisible(true);
		}

		if (e.getSource() == ticketButton) {
			dispose();
			// startWindow.setVisible(true);
		}

		if (e.getSource() == userButton) {
			dispose();
			// startWindow.setVisible(true);
		}

		if (e.getSource() == raportButton) {
			dispose();
			// startWindow.setVisible(true);
		}

		if (e.getSource() == databaseButton) {
			try {
				CreateDB c = new CreateDB();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}

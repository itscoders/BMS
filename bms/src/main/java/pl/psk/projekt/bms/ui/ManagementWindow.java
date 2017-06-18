package pl.psk.projekt.bms.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ManagementWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JButton workerButton;
	private JButton scheduleButton;
	private JButton busButton;
	private JButton lineButton;
	private JButton ticketButton;
	private JButton userButton;
	private JButton raportButton;

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
		setTitle("Management - Bus Management");
		setResizable(false);
		setBounds(new Rectangle(100, 100, 700, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton workerButton = new JButton("Worker");
		workerButton.addActionListener(this);
		workerButton.setBackground(Color.LIGHT_GRAY);

		JButton scheduleButton = new JButton("Schedule");
		scheduleButton.addActionListener(this);
		scheduleButton.setBackground(Color.LIGHT_GRAY);

		JButton busButton = new JButton("Bus");
		busButton.addActionListener(this);
		busButton.setBackground(Color.LIGHT_GRAY);

		JButton ticketButton = new JButton("Ticket");
		ticketButton.addActionListener(this);
		ticketButton.setBackground(Color.LIGHT_GRAY);

		JButton userButton = new JButton("User");
		userButton.addActionListener(this);
		userButton.setBackground(Color.LIGHT_GRAY);

		JButton raportbutton = new JButton("Raport");
		raportbutton.setBackground(Color.LIGHT_GRAY);
		raportbutton.addActionListener(this);
		
		JButton btnLine = new JButton("Line");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(31)
					.addComponent(workerButton)
					.addGap(18)
					.addComponent(scheduleButton)
					.addGap(18)
					.addComponent(busButton)
					.addGap(18)
					.addComponent(btnLine)
					.addGap(18)
					.addComponent(ticketButton)
					.addGap(18)
					.addComponent(userButton)
					.addGap(18)
					.addComponent(raportbutton)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(workerButton)
						.addComponent(scheduleButton)
						.addComponent(busButton)
						.addComponent(btnLine)
						.addComponent(ticketButton)
						.addComponent(userButton)
						.addComponent(raportbutton))
					.addContainerGap(427, Short.MAX_VALUE))
		);
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {workerButton, scheduleButton, busButton, ticketButton, userButton, raportbutton, btnLine});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {workerButton, scheduleButton, busButton, ticketButton, userButton, raportbutton, btnLine});
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == workerButton) {
			dispose();
			//WorkerWindow wd = new Worker();
			//wd.setVisible(true);
		}
		
		if (e.getSource() == scheduleButton) {
			dispose();
			//startWindow.setVisible(true);
		}
		
		if (e.getSource() == busButton) {
			dispose();
			//startWindow.setVisible(true);
		}
		
		if (e.getSource() == lineButton) {
			dispose();
			//startWindow.setVisible(true);
		}
		
		if (e.getSource() == ticketButton) {
			dispose();
			//startWindow.setVisible(true);
		}
		
		if (e.getSource() == userButton) {
			dispose();
			//startWindow.setVisible(true);
		}
		
		if (e.getSource() == raportButton) {
			dispose();
			//startWindow.setVisible(true);
		}
		
		
	}
}

package pl.psk.projekt.bms.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class BusWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField userNameField;
	private JTable table;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BusWindow frame = new BusWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BusWindow() {
		setTitle("Bus - Bus Management");
		setBounds(new Rectangle(100, 100, 700, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		userNameField = new JTextField();
		userNameField.setColumns(10);

		JLabel labelBusName = new JLabel("Bus Name:");

		JLabel labelSeat = new JLabel("Seat:");

		JComboBox comboBoxSeat = new JComboBox();

		JButton addButton = new JButton("Add");
		addButton.addActionListener(this);

		JButton editButton = new JButton("Edit");
		editButton.addActionListener(this);

		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(242, Short.MAX_VALUE)
					.addComponent(addButton)
					.addGap(18)
					.addComponent(editButton)
					.addGap(18)
					.addComponent(deleteButton)
					.addGap(231))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(labelBusName)
							.addGap(18)
							.addComponent(userNameField, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(labelSeat)
							.addGap(18)
							.addComponent(comboBoxSeat, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelBusName)
						.addComponent(userNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelSeat)
						.addComponent(comboBoxSeat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(addButton)
						.addComponent(editButton)
						.addComponent(deleteButton))
					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addGap(33))
		);
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {labelBusName, labelSeat});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {userNameField, comboBoxSeat});

		table = new JTable();
		table.setSelectionForeground(Color.WHITE);
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Bus Name", "Seat"
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addButton) {
			dispose();
			// WorkerWindow wd = new Worker();
			// wd.setVisible(true);
		}

		if (e.getSource() == editButton) {
			dispose();
			// startWindow.setVisible(true);
		}

		if (e.getSource() == deleteButton) {
			dispose();
			// startWindow.setVisible(true);
		}

	}
}

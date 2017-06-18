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
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

public class LineWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField lineNameField;
	private JTextField startStationField;
	private JTextField stopStationField;
	private JTable table;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LineWindow frame = new LineWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LineWindow() {
		setTitle("Line - Bus Management");
		setBounds(new Rectangle(100, 100, 700, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		lineNameField = new JTextField();
		lineNameField.setColumns(10);

		JLabel labelLineName = new JLabel("Line Name:");

		JLabel labelStartStation = new JLabel("Start Station:");

		startStationField = new JTextField();
		startStationField.setColumns(10);

		JLabel labelType = new JLabel("Type:");

		JLabel labelStopStation = new JLabel("Stop Station");

		stopStationField = new JTextField();
		stopStationField.setColumns(10);

		JComboBox comboBoxType = new JComboBox();

		JButton addButton = new JButton("Add");
		addButton.addActionListener(this);

		JButton editButton = new JButton("Edit");
		editButton.addActionListener(this);

		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(44, Short.MAX_VALUE)
					.addComponent(addButton)
					.addGap(18)
					.addComponent(editButton)
					.addGap(18)
					.addComponent(deleteButton)
					.addGap(231))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelLineName)
								.addComponent(labelStartStation))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lineNameField, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
								.addComponent(startStationField, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelType)
								.addComponent(labelStopStation))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxType, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(stopStationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(32))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelLineName)
						.addComponent(lineNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelType)
						.addComponent(comboBoxType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(startStationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelStartStation)
						.addComponent(labelStopStation)
						.addComponent(stopStationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(addButton)
						.addComponent(editButton)
						.addComponent(deleteButton))
					.addGap(31)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {lineNameField, startStationField, stopStationField});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {labelLineName, labelStartStation, labelType, labelStopStation});

		table = new JTable();
		table.setSelectionForeground(Color.WHITE);
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "User Name", "Name", "Surname",
				"Type", "Position", "Birthday", "Mobile Phone", "Adress", "Salary" }));
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

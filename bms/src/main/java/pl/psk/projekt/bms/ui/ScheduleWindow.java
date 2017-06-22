package pl.psk.projekt.bms.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.SpinnerDateModel;
import java.awt.ComponentOrientation;

public class ScheduleWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable table;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleWindow frame = new ScheduleWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ScheduleWindow() {
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
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Schedule - Bus Management");
		setBounds(new Rectangle(100, 100, 700, 400));
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setFocusable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel labelDepartureTime = new JLabel("Departure Time");

		JLabel labelIdBus = new JLabel("Bus ID:");

		JLabel labelIdDriver = new JLabel("Driver ID:");

		JLabel labelArrivalTime = new JLabel("Arrival Time:");

		JLabel labelIdLine = new JLabel("Line ID:");

		JComboBox comboBoxDriverId = new JComboBox();

		JButton addButton = new JButton("Add");
		addButton.setBackground(Color.LIGHT_GRAY);
		addButton.addActionListener(this);

		JButton editButton = new JButton("Edit");
		editButton.setBackground(Color.LIGHT_GRAY);
		editButton.addActionListener(this);

		JButton deleteButton = new JButton("Delete");
		deleteButton.setBackground(Color.LIGHT_GRAY);
		deleteButton.addActionListener(this);

		JScrollPane scrollPane = new JScrollPane();

		Date startDate = new Date();
		SpinnerDateModel sm = new SpinnerDateModel(startDate, null, null, Calendar.HOUR_OF_DAY);
		JSpinner spinnerDepartureTime = new JSpinner(sm);
		spinnerDepartureTime.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		JSpinner.DateEditor de_spinnerDepartureTime = new JSpinner.DateEditor(spinnerDepartureTime, "HH:mm:ss");
		spinnerDepartureTime.setEditor(de_spinnerDepartureTime);

		Date stopDate = new Date();
		SpinnerDateModel sm2 = new SpinnerDateModel(stopDate, null, null, Calendar.HOUR_OF_DAY);
		JSpinner spinnerArrivalTime = new JSpinner(sm2);
		JSpinner.DateEditor de_spinnerArrivalTime = new JSpinner.DateEditor(spinnerArrivalTime, "HH:mm:ss");
		spinnerArrivalTime.setEditor(de_spinnerArrivalTime);

		JComboBox comboBoxBusId = new JComboBox();

		JComboBox comboBoxLineId = new JComboBox();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(230, Short.MAX_VALUE)
						.addComponent(addButton).addGap(18).addComponent(editButton).addGap(18)
						.addComponent(deleteButton).addGap(219))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addGap(40).addGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane
										.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
												.createSequentialGroup()
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(labelDepartureTime).addComponent(labelIdBus))
												.addGap(18)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
														.addComponent(spinnerDepartureTime).addComponent(comboBoxBusId,
																0, 190, Short.MAX_VALUE)))
										.addGroup(gl_contentPane.createSequentialGroup().addComponent(labelIdDriver)
												.addGap(18).addComponent(comboBoxDriverId, 0, 190, Short.MAX_VALUE)))
								.addGap(28)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(labelArrivalTime).addComponent(labelIdLine))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(spinnerArrivalTime, GroupLayout.DEFAULT_SIZE, 202,
												Short.MAX_VALUE)
										.addComponent(comboBoxLineId, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addGap(30)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(labelDepartureTime)
						.addComponent(labelArrivalTime)
						.addComponent(spinnerDepartureTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(spinnerArrivalTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(labelIdBus)
						.addComponent(labelIdLine)
						.addComponent(comboBoxBusId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxLineId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(labelIdDriver)
						.addComponent(comboBoxDriverId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(addButton)
						.addComponent(editButton).addComponent(deleteButton))
				.addGap(18).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
				.addContainerGap()));
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL,
				new Component[] { labelDepartureTime, labelIdBus, labelIdDriver, labelArrivalTime, labelIdLine });
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] { addButton, editButton, deleteButton });

		table = new JTable();
		table.setSelectionForeground(Color.WHITE);
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Departure Time", "Arrival Time", "Bus ID", "Line ID", "Driver ID" }));
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

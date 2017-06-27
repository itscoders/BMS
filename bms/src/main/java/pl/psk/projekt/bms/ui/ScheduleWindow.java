package pl.psk.projekt.bms.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.table.TableRowSorter;

import net.proteanit.sql.DbUtils;
import pl.psk.projekt.bms.component.ComboKeyHandler;
import pl.psk.projekt.bms.dbobjects.Scheduler;
import pl.psk.projekt.bms.jdbc.SchedulerJDBC;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.SpinnerDateModel;
import java.awt.ComponentOrientation;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ScheduleWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable tableFilter;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JTextField filterField;
	private DefaultTableModel modelFilter;
	private JComboBox<String> comboBoxDriverId;
	private JComboBox<String> comboBoxBusId; 
	private JComboBox<String> comboBoxLineId;
	PreparedStatement preparedStatement;
	Connection connect;
	ResultSet rs;

	private DefaultComboBoxModel<String> comboModelDriverIdD;
	private DefaultComboBoxModel<String> comboModelBusLineD;
	private DefaultComboBoxModel<String> comboModelBusD;
	private JSpinner spinnerDepartureTimeStart;
	private JSpinner spinnerArrivalTimeStop;
	JSpinner.DateEditor de_spinnerArrivalTime;
	JSpinner.DateEditor de_spinnerDepartureTime;
	
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
		setResizable(false);
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
		
		fillComboBoxDriver();
		comboBoxDriverId = new JComboBox<String>();
		comboBoxDriverId.setModel(comboModelDriverIdD);
		JTextField textDriver = (JTextField) comboBoxDriverId.getEditor().getEditorComponent();
		textDriver.setText("");
		textDriver.addKeyListener(new ComboKeyHandler(comboBoxDriverId));
		
		
		fillComboBoxBus();
		comboBoxBusId = new JComboBox<String>();
		comboBoxBusId.setModel(comboModelBusD);
		JTextField textBus = (JTextField) comboBoxBusId.getEditor().getEditorComponent();
		textBus.setText("");
		textBus.addKeyListener(new ComboKeyHandler(comboBoxBusId));
		
		fillComboBoxBusLine();
		comboBoxLineId = new JComboBox<String>();
		comboBoxLineId.setModel(comboModelBusLineD);
		JTextField textLine = (JTextField) comboBoxLineId.getEditor().getEditorComponent();
		textLine.setText("");
		textLine.addKeyListener(new ComboKeyHandler(comboBoxLineId));

		addButton = new JButton("Add");
		addButton.setBackground(Color.LIGHT_GRAY);
		addButton.addActionListener(this);

		editButton = new JButton("Edit");
		editButton.setBackground(Color.LIGHT_GRAY);
		editButton.addActionListener(this);
		editButton.setEnabled(false);

		deleteButton = new JButton("Delete");
		deleteButton.setBackground(Color.LIGHT_GRAY);
		deleteButton.addActionListener(this);
		deleteButton.setEnabled(false);

		JScrollPane scrollPane = new JScrollPane();

		Date startDate = new Date();
		SpinnerDateModel sm = new SpinnerDateModel(startDate, null, null, Calendar.HOUR_OF_DAY);
		spinnerDepartureTimeStart = new JSpinner(sm);
		spinnerDepartureTimeStart.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		de_spinnerDepartureTime = new JSpinner.DateEditor(spinnerDepartureTimeStart, "HH:mm:ss");
		spinnerDepartureTimeStart.setEditor(de_spinnerDepartureTime);

		Date stopDate = new Date();
		SpinnerDateModel sm2 = new SpinnerDateModel(stopDate, null, null, Calendar.HOUR_OF_DAY);
		spinnerArrivalTimeStop = new JSpinner(sm2);
		de_spinnerArrivalTime = new JSpinner.DateEditor(spinnerArrivalTimeStop, "HH:mm:ss");
		spinnerArrivalTimeStop.setEditor(de_spinnerArrivalTime);
		
		filterField = new JTextField();
		filterField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = filterField.getText();
				filter(query);
			}
		});
		filterField.setColumns(10);
		
		
		
		JLabel lblSearchSchedule = new JLabel("Search Schedule:");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(240, Short.MAX_VALUE)
					.addComponent(addButton)
					.addGap(18)
					.addComponent(editButton)
					.addGap(18)
					.addComponent(deleteButton)
					.addGap(219))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(173)
					.addComponent(lblSearchSchedule, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(filterField, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(203, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(labelDepartureTime)
										.addComponent(labelIdBus))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(spinnerDepartureTimeStart, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
										.addComponent(comboBoxBusId, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(labelIdDriver)
									.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
									.addComponent(comboBoxDriverId, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelArrivalTime)
								.addComponent(labelIdLine))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(spinnerArrivalTimeStop, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
								.addComponent(comboBoxLineId, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(30))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelDepartureTime)
						.addComponent(labelArrivalTime)
						.addComponent(spinnerDepartureTimeStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinnerArrivalTimeStop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelIdBus)
						.addComponent(labelIdLine)
						.addComponent(comboBoxBusId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxLineId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelIdDriver)
						.addComponent(comboBoxDriverId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(addButton)
						.addComponent(editButton)
						.addComponent(deleteButton))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(filterField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblSearchSchedule)))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {comboBoxDriverId, comboBoxBusId});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {labelDepartureTime, labelIdBus, labelIdDriver, labelArrivalTime, labelIdLine});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {addButton, editButton, deleteButton});
		
		try {
			connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");
			preparedStatement = connect.prepareStatement("SELECT * FROM SCHEDULER");
			rs = preparedStatement.executeQuery();
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}

		
		modelFilter = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);
		
		tableFilter = new JTable();

		tableFilter.setModel(modelFilter);
		scrollPane.setViewportView(tableFilter);
		contentPane.setLayout(gl_contentPane);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addButton) {
			
			int driver = comparDriver();
			int bus = comparBus();
			int line = comparBusLine();
			String startTime = de_spinnerDepartureTime.getFormat().format(spinnerDepartureTimeStart.getValue());
			String stopTime = de_spinnerArrivalTime.getFormat().format(spinnerArrivalTimeStop.getValue());
			
			
			try {
				SchedulerJDBC sj = new SchedulerJDBC();
				Scheduler s = new Scheduler(bus,driver,line, startTime,stopTime);
				sj.addSchedulerRecord(s);
				updateTable();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "New Schedule had added.");
			editButton.setEnabled(false);
			deleteButton.setEnabled(false);
			updateTable();
			
		}

		if (e.getSource() == editButton) {
			
				
				int value = Integer.parseInt(tableFilter.getValueAt(tableFilter.getSelectedRow(), 0).toString());
				
				int driver = comparDriver();
				int bus = comparBus();
				int line = comparBusLine();
				String startTime = de_spinnerDepartureTime.getFormat().format(spinnerDepartureTimeStart.getValue());
				String stopTime = de_spinnerArrivalTime.getFormat().format(spinnerArrivalTimeStop.getValue());
				
				
				try {
					SchedulerJDBC sj = new SchedulerJDBC();
					Scheduler s = new Scheduler(bus,driver,line,startTime,stopTime);
					s.setSchedulerRecordID(value);
					
					sj.updateSchedulerRecord(s);
					updateTable();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(this, "Schedule was edited.");
				editButton.setEnabled(false);
				deleteButton.setEnabled(false);
				updateTable();
			
		}

		if (e.getSource() == deleteButton) {
			
			int value = Integer.parseInt(tableFilter.getValueAt(tableFilter.getSelectedRow(), 0).toString());
			
			try {
				SchedulerJDBC sj = new SchedulerJDBC();
				sj.deleteSchedulerRecord(value);
				updateTable();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "Schedule was deleted.");
			editButton.setEnabled(false);
			deleteButton.setEnabled(false);
			updateTable();
		}

	}
	
	
		private void updateTable() {
			try {
				connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");

				preparedStatement = connect.prepareStatement("SELECT * FROM SCHEDULER");
				rs = preparedStatement.executeQuery();
				tableFilter.setModel(DbUtils.resultSetToTableModel(rs));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			} finally {

				try {
					rs.close();
					preparedStatement.close();

				} catch (Exception e) {

				}
			}
		}


		private void filter(String query) {

			TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(modelFilter);
			tableFilter.setRowSorter(trs);

			trs.setRowFilter(RowFilter.regexFilter(query));
		}
		
		
		private void fillComboBoxDriver() {
			try {
				connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");

				preparedStatement = connect.prepareStatement("SELECT * FROM Workers WHERE accountType = 'Driver'");
				rs = preparedStatement.executeQuery();
				comboModelDriverIdD = new DefaultComboBoxModel<String>();
				while (rs.next()) {
					String name ="id:"+" "+ rs.getString("workerId")+"  "+ rs.getString("name") + " - " + rs.getString("surname");
					comboModelDriverIdD.addElement(name);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			} finally {

				try {
					rs.close();
					preparedStatement.close();

				} catch (Exception e) {

				}
			}
		}
		
		private int comparDriver() {
			try {
				connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");

				preparedStatement = connect.prepareStatement("SELECT * FROM Workers WHERE accountType = 'Driver'");
				rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					String name ="id:"+" "+ rs.getString("workerId")+"  "+ rs.getString("name") + " - " + rs.getString("surname");
					if (name.equals(comboModelDriverIdD.getSelectedItem().toString())) {
						return Integer.parseInt(rs.getString("workerId"));
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			} finally {

				try {
					rs.close();
					preparedStatement.close();

				} catch (Exception e) {

				}
			}
			return 0;
		}
		
		private void fillComboBoxBus() {
			try {
				connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");

				preparedStatement = connect.prepareStatement("SELECT * FROM BUS");
				rs = preparedStatement.executeQuery();
				comboModelBusD = new DefaultComboBoxModel<String>();
				while (rs.next()) {
					String name ="id:"+" "+ rs.getString("busID")+" - "+ rs.getString("busName");
					comboModelBusD.addElement(name);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			} finally {

				try {
					rs.close();
					preparedStatement.close();

				} catch (Exception e) {

				}
			}
		}
		
		private int comparBus() {
			try {
				connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");

				preparedStatement = connect.prepareStatement("SELECT * FROM Bus");
				rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					String name ="id:"+" "+ rs.getString("busID")+" - "+ rs.getString("busName");
				
					if (name.equals(comboModelBusD.getSelectedItem().toString())) {
						return Integer.parseInt(rs.getString("busID"));
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			} finally {

				try {
					rs.close();
					preparedStatement.close();

				} catch (Exception e) {

				}
			}
			return 0;
		}
		
		
		private void fillComboBoxBusLine() {
			try {
				connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");

				preparedStatement = connect.prepareStatement("SELECT * FROM BUSLINE");
				rs = preparedStatement.executeQuery();
				comboModelBusLineD = new DefaultComboBoxModel<String>();
				while (rs.next()) {
					String name = "id:"+" "+ rs.getString("busLineID")+ " "+rs.getString("busLineName") + " - " + rs.getString("busLineType");
					comboModelBusLineD.addElement(name);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			} finally {

				try {
					rs.close();
					preparedStatement.close();

				} catch (Exception e) {

				}
			}
		}
		
		private int comparBusLine() {
			try {
				connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");

				preparedStatement = connect.prepareStatement("SELECT * FROM BUSLINE");
				rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					String name = "id:"+" "+ rs.getString("busLineID")+" "+rs.getString("busLineName") + " - " + rs.getString("busLineType");
					
					if (name.equals(comboModelBusLineD.getSelectedItem().toString())) {
						return Integer.parseInt(rs.getString("busLineID"));
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			} finally {

				try {
					rs.close();
					preparedStatement.close();

				} catch (Exception e) {

				}
			}
			return 0;
		}
}

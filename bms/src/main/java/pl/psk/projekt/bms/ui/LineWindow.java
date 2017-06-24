package pl.psk.projekt.bms.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import net.proteanit.sql.DbUtils;
import pl.psk.projekt.bms.dbobjects.BusLine;
import pl.psk.projekt.bms.jdbc.BusLineJDBC;
import javax.swing.LayoutStyle.ComponentPlacement;


public class LineWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField lineNameField;
	private JTextField startStationField;
	private JTextField stopStationField;
	private JTable tableFilter;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	JComboBox<String> comboBoxType;
	PreparedStatement  preparedStatement;
    Connection connect;
    ResultSet rs;
    private JTextField filterField;
    private DefaultTableModel modelFilter;
    private JLabel lblSearchLine;
    private JTextField textFieldPirceOneWay;
    private JTextField textFieldPriceMonthly;

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
		
		setTitle("Line - Bus Management");
		setBounds(new Rectangle(100, 100, 700, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		JLabel labelStopStation = new JLabel("Stop Station:");

		stopStationField = new JTextField();
		stopStationField.setColumns(10);

		 comboBoxType = new JComboBox<String>();
		 String e ="normalny";
		 comboBoxType.addItem(e);
		 e ="pośpieszny";
		 comboBoxType.addItem(e);

		addButton = new JButton("Add");
		addButton.addActionListener(this);

		editButton = new JButton("Edit");
		editButton.addActionListener(this);

		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);

		JScrollPane scrollPane = new JScrollPane();
		
		filterField = new JTextField();
		filterField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = filterField.getText();
				filter(query);
			}
		});
		filterField.setColumns(10);
		
		lblSearchLine = new JLabel("Search Line:");
		
		textFieldPirceOneWay = new JTextField();
		textFieldPirceOneWay.setColumns(10);
		
		textFieldPriceMonthly = new JTextField();
		textFieldPriceMonthly.setColumns(10);
		
		JLabel labelPriceMonthly = new JLabel("Price Monthly:");
		
		JLabel labelPirceOneWay = new JLabel("Pirce OneWay:");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelLineName)
								.addComponent(labelStartStation)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(labelPirceOneWay, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lineNameField, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
									.addComponent(startStationField, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
								.addComponent(textFieldPirceOneWay, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelType)
								.addComponent(labelStopStation)
								.addComponent(labelPriceMonthly, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldPriceMonthly, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(comboBoxType, 0, 212, Short.MAX_VALUE)
									.addComponent(stopStationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addGap(32))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(182, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblSearchLine, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(filterField, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(addButton)
							.addGap(18)
							.addComponent(editButton)
							.addGap(18)
							.addComponent(deleteButton)))
					.addGap(231))
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
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldPriceMonthly, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelPriceMonthly)
						.addComponent(labelPirceOneWay)
						.addComponent(textFieldPirceOneWay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(addButton)
						.addComponent(editButton)
						.addComponent(deleteButton))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSearchLine)
						.addComponent(filterField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {lineNameField, startStationField, stopStationField});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {labelLineName, labelStartStation, labelType, labelStopStation});
		
		try {
			connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			preparedStatement = connect.prepareStatement("SELECT * FROM BUSLINE");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rs = preparedStatement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		modelFilter = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);
		
		tableFilter = new JTable();
		tableFilter.setModel(modelFilter);
		
		scrollPane.setViewportView(tableFilter);
		scrollPane.setHorizontalScrollBarPolicy(
	                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
         /*String busLineID = "";
         String busLineName = "";
         String busLineType = "";
         String startStation = "";
         String endStation = "";
         String  price = "";
     
        try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "root", "toor");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        
        
        try {
        	preparedStatement = connect.prepareStatement("SELECT * FROM busline");
        	ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
            	busLineID = rs.getString("busLineID");
            	System.out.println(busLineID);
            	busLineName = rs.getString("busLineName");
            	System.out.println(busLineName);
            	busLineType = rs.getString("busLineType");
            	System.out.println(busLineType);
            	startStation = rs.getString("startStation");
            	System.out.println(startStation);
            	endStation = rs.getString("endStation");
            	System.out.println(endStation);
            	price = rs.getString("price");
            	System.out.println(price);
                model.addRow(new Object[]{busLineID, busLineName, busLineType, startStation, endStation, price});
                
                i++;
            };
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (i == 1) {
                System.out.println(i + " Record Found");
            } else {
                System.out.println(i + " Records Found");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }*/
		
		
		
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addButton) {
			String busLineName = lineNameField.getText();
			String startStation = startStationField.getText();
			String stopStation = startStationField.getText();
			double pirceOneWay = Double.parseDouble(textFieldPirceOneWay.getText());
			double priceMonthly = Double.parseDouble(textFieldPriceMonthly.getText());
			String busLineType = (String) comboBoxType.getSelectedItem();
			
			
			try {
				BusLineJDBC bj = new BusLineJDBC();
				BusLine b = new BusLine(busLineName, busLineType, startStation, stopStation, pirceOneWay, priceMonthly);
				bj.createBusLine(b);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			updateTable();
		}

		if (e.getSource() == editButton) {
			
			int value = Integer.parseInt((String) tableFilter.getValueAt(tableFilter.getSelectedRow(), tableFilter.getSelectedColumn()));
			String busLineName = lineNameField.getText();
			String startStation = startStationField.getText();
			String stopStation = startStationField.getText();
			double pirceOneWay = Double.parseDouble(textFieldPirceOneWay.getText());
			double priceMonthly = Double.parseDouble(textFieldPriceMonthly.getText());
			String busLineType = (String) comboBoxType.getSelectedItem();
			
			try {
				BusLineJDBC bj = new BusLineJDBC();
				BusLine b = new BusLine(busLineName, busLineType, startStation, stopStation, pirceOneWay, priceMonthly);
				b.setBusLineID(value);
				bj.updateBusLine(b);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			updateTable();
		}

		if (e.getSource() == deleteButton) {
			int value = Integer.parseInt((String) tableFilter.getValueAt(tableFilter.getSelectedRow(), tableFilter.getSelectedColumn()));
			
			
			try {
				BusLineJDBC bj = new BusLineJDBC();
				BusLine b = new BusLine();
				b.setBusLineID(value);
				bj.deleteBusLine(b);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		updateTable();

	}
	
		// METODA ODŚWIERZAJĄCA TABELE JTABLE
		// MUSI ZOSTAĆ WYWOŁANA ZAWSZE NA KOŃCU W PRZYCISKACH: ADD, EDIT, DELETE
		private void updateTable() {
			try {
				connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");

				preparedStatement = connect.prepareStatement("SELECT * FROM BUSLINE");
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

		//METODA DO DYNAMICZNEGO WYSZUKIWANIA W TABELI
		private void filter(String query) {

			TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(modelFilter);
			tableFilter.setRowSorter(trs);

			trs.setRowFilter(RowFilter.regexFilter(query));
		}
}

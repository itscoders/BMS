package pl.psk.projekt.bms.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Component;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import pl.psk.projekt.bms.dbobjects.BusLine;
import pl.psk.projekt.bms.jdbc.BusLineJDBC;

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
	JComboBox<String> comboBoxType;
	PreparedStatement  preparedStatement;
    Connection connect;
    ResultSet rs;

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
		
		setTitle("Line - Bus Management");
		setBounds(new Rectangle(100, 100, 700, 400));
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

		JLabel labelStopStation = new JLabel("Stop Station");

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

		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[] {"ID", "Line Name", "Line Type", "Start Station", "End Station", "Price"});
		table = new JTable();
		table.setSelectionForeground(Color.WHITE);
		table.setBackground(Color.WHITE);
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		scrollPane.setViewportView(table);
		scrollPane.setHorizontalScrollBarPolicy(
	                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
         String busLineID = "";
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
        }
		
		
		
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addButton) {
			String busLineName = lineNameField.getText();
			String startStation = startStationField.getText();
			String stopStationField = startStationField.getText();
			
			String busLineType = (String) comboBoxType.getSelectedItem();
			
			try {
				BusLineJDBC bj = new BusLineJDBC();
				BusLine b = new BusLine(busLineName, busLineType, startStation, stopStationField, 20);
				bj.createBusLine(b);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			Update_table();
		}

		if (e.getSource() == editButton) {
			
			int value = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
			String busLineName = lineNameField.getText();
			String startStation = startStationField.getText();
			String stopStationField = startStationField.getText();
			
			String busLineType = (String) comboBoxType.getSelectedItem();
			
			try {
				BusLineJDBC bj = new BusLineJDBC();
				BusLine b = new BusLine(busLineName, busLineType, startStation, stopStationField, 20);
				b.setBusLineID(value);
				bj.updateBusLine(b);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			Update_table();
		}

		if (e.getSource() == deleteButton) {
			int value = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
			
			
			try {
				BusLineJDBC bj = new BusLineJDBC();
				BusLine b = new BusLine();
				b.setBusLineID(value);
				bj.deleteBusLine(b);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		Update_table();

	}
	
    private void Update_table() {
 try{
	 preparedStatement = connect.prepareStatement("SELECT * FROM BusLine");
 	 rs = preparedStatement.executeQuery();
     table.setModel(DbUtils.resultSetToTableModel(rs));
 }
 catch(Exception e){
 JOptionPane.showMessageDialog(null, e);
 }
 finally {
         
         try{
             rs.close();
             preparedStatement.close();
             
         }
         catch(Exception e){
             
         }
     }
 }
}

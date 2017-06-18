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

import pl.psk.projekt.bms.dbobjects.Bus;
import pl.psk.projekt.bms.jdbc.BusJDBC;

import javax.swing.LayoutStyle.ComponentPlacement;

public class BusWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField busNameField;
	private JTable table;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JComboBox<String> comboBoxSeat;
	PreparedStatement  preparedStatement;
    Connection connect;

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
		
		setTitle("Bus - Bus Management");
		setBounds(new Rectangle(100, 100, 700, 400));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		busNameField = new JTextField();
		busNameField.setColumns(10);

		JLabel labelBusName = new JLabel("Bus Name:");

		JLabel labelSeat = new JLabel("Seat:");

		comboBoxSeat = new JComboBox<String>();
		String e ="25";
		comboBoxSeat.addItem(e);
		 e ="35";
		comboBoxSeat.addItem(e);
		 e ="60";
		comboBoxSeat.addItem(e);

		addButton = new JButton("Add");
		addButton.addActionListener(this);

		editButton = new JButton("Edit");
		editButton.addActionListener(this);

		deleteButton = new JButton("Delete");
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
							.addComponent(busNameField, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(busNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {busNameField, comboBoxSeat});
		
		
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[] {"ID", "Bus Name", "Seat"});
		table = new JTable();
		table.setSelectionForeground(Color.WHITE);
		table.setBackground(Color.WHITE);
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
        String busID = "";
        String busName = "";
        String seat = "";
     
        try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "root", "toor");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        
        
        try {
        	preparedStatement = connect.prepareStatement("SELECT * FROM bus");
        	ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
            	busID = rs.getString("busID");
            	busName = rs.getString("busName");
            	seat = rs.getString("seat");
                model.addRow(new Object[]{busID, busName, seat});
                i++;
            }
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
			String busName = busNameField.getText();
			int seat = Integer.parseInt((String) comboBoxSeat.getSelectedItem());
			
			try {
				BusJDBC bj = new BusJDBC();
				Bus b = new Bus(busName, seat);
				bj.createBus(b);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
		}

		if (e.getSource() == editButton) {
			
			if (e.getSource() == addButton) {
				
			}
				String busName = busNameField.getText();
				int seat = Integer.parseInt((String) comboBoxSeat.getSelectedItem());
				int value = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
				
				try {
					BusJDBC bj = new BusJDBC();
					Bus b = new Bus(busName, seat);
					b.setBusID(value);
					bj.updateBus(b);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			
		}

		if (e.getSource() == deleteButton) {
			
			int value = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
			try {
				BusJDBC bj = new BusJDBC();
				Bus b = new Bus();
				b.setBusID(value);
				bj.deleteBus(b);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}

	}
}

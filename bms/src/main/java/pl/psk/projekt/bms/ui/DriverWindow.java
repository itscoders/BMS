package pl.psk.projekt.bms.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import pl.psk.projekt.bms.dbobjects.Workers;

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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class DriverWindow  extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton raportButton;
	private JScrollPane scrollPane;
	private JTable tableFilter;
	private DefaultTableModel modelFilter;
	private JTextField textField;
	private Workers w;
	
	PreparedStatement  preparedStatement;
    Connection connect;
    ResultSet rs;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workers w = new Workers();
					w.setWorkerId(4);
					DriverWindow frame = new DriverWindow(w);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DriverWindow(Workers w) {
			
		this.w = w;
			
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
		setTitle("Driver Secheduler System");
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(new Rectangle(50, 100, 600, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		raportButton = new JButton("Raport");
		raportButton.setBackground(Color.LIGHT_GRAY);
		raportButton.addActionListener(this);
		
		scrollPane = new JScrollPane();
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lebelSearch = new JLabel("Search in your schedule:");
		
		JButton buttonEditYourData = new JButton("Edit Your Data");
		buttonEditYourData.setBackground(Color.LIGHT_GRAY);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(lebelSearch)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addComponent(buttonEditYourData)
					.addGap(27)
					.addComponent(raportButton)
					.addGap(20))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(raportButton)
						.addComponent(lebelSearch)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonEditYourData))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
		);
		
		try {
			connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
						"root", "toor");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			preparedStatement = connect.prepareStatement("SELECT * FROM SCHEDULER WHERE IdDriver="+w.getWorkerId());
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
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == raportButton) {
			
		}


	}
}


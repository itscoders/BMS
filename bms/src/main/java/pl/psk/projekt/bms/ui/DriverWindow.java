package pl.psk.projekt.bms.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import net.proteanit.sql.DbUtils;
import pl.psk.projekt.bms.component.Mail;
import pl.psk.projekt.bms.dbobjects.Workers;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;

public class DriverWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton printSchedulerButton;
	private JScrollPane scrollPane;
	private JTable tableFilter;
	private DefaultTableModel modelFilter;
	private JTextField textField;
	private Workers w;
	private JButton logoutButton;
	private JLabel logLabel;

	PreparedStatement preparedStatement;
	Connection connect;
	ResultSet rs, rs2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workers w = new Workers();
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

		printSchedulerButton = new JButton("Print Your Scheduler");
		printSchedulerButton.setBounds(423, 110, 156, 23);
		printSchedulerButton.setBackground(Color.LIGHT_GRAY);
		printSchedulerButton.addActionListener(this);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 151, 564, 204);

		textField = new JTextField();
		textField.setBounds(137, 110, 199, 23);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = textField.getText();
				filter(query);
			}
		});
		textField.setColumns(10);

		JLabel lebelSearch = new JLabel("Search in your schedule:");
		lebelSearch.setBounds(15, 110, 118, 23);

		logoutButton = new JButton("Logout");
		logoutButton.setBounds(490, 13, 89, 29);
		logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logout.png")));
		logoutButton.addActionListener(this);

		logLabel = new JLabel();
		logLabel.setBounds(15, 11, 341, 25);
		logLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		welcomeLabel();

		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");
			preparedStatement = connect.prepareStatement("SELECT * FROM SCHEDULER WHERE IdDriver=" + w.getWorkerId());
			rs = preparedStatement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		modelFilter = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);

		tableFilter = new JTable();
		tableFilter.setModel(modelFilter);
		
		

		scrollPane.setViewportView(tableFilter);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.setLayout(null);
		contentPane.add(scrollPane);
		contentPane.add(lebelSearch);
		contentPane.add(textField);
		contentPane.add(logLabel);
		contentPane.add(logoutButton);
		contentPane.add(printSchedulerButton);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(this.getClass().getResource("/worker.png")));
		background.setBounds(0, 0, 594, 371);
		contentPane.add(background);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == printSchedulerButton) {

			generateFileWithScheduler();

		}

		if (e.getSource() == logoutButton) {
			String username = w.getUsername();
			JOptionPane.showMessageDialog(this, "Worker: " + username + " was successfully logged out.");
			LoginWindow lw = new LoginWindow();
			lw.setVisible(true);
			this.dispose();
		}

	}

	public void welcomeLabel() {
		String name = w.getName();
		String surname = w.getSurname();
		logLabel.setText("Welcome " + name + " " + surname);

	}

	private void generateFileWithScheduler() {

		String row = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		String date = sdf.format(cal.getTime());

		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms_db?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					"root", "toor");
			preparedStatement = connect.prepareStatement("SELECT * FROM SCHEDULER WHERE IdDriver=" + w.getWorkerId());
			rs = preparedStatement.executeQuery();

			JFileChooser dialog = new JFileChooser();
			dialog.setSelectedFile(new File(w.getName() + "_" + w.getSurname() + "_scheduler_" + date + ".pdf"));
			int dialogResult = dialog.showSaveDialog(null);
			if (dialogResult == JFileChooser.APPROVE_OPTION) {
				String filePath = dialog.getSelectedFile().getPath();

				Document myDocument = new Document();
				PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
				myDocument.open();

				myDocument.add(
						new Paragraph("PRINT SCHEDULER", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
				myDocument.add(new Paragraph("GENERATE DATE : " + date));
				myDocument.add(new Paragraph(
						"-------------------------------------------------------------------------------------------"));
				myDocument.add((new Paragraph("DRIVER DETAILS:",
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD))));
				myDocument.add((new Paragraph("Name: " + w.getName() + " Surname: " + w.getSurname(),
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
				myDocument.add((new Paragraph("Driver ID: " + w.getWorkerId(),
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
				myDocument.add(new Paragraph(
						"-------------------------------------------------------------------------------------------"));
				while (rs.next()) {
					row = "";
					for (int i = 2; i <= rs.getMetaData().getColumnCount() - 1; i++) {
						if (i == 5) {
							preparedStatement = connect.prepareStatement("SELECT DISTINCT * FROM BUSLINE WHERE busLineID=" + rs.getInt(i));
							rs2 = preparedStatement.executeQuery();
							while (rs2.next()) {
								row += rs2.getString(2) + ", "+ rs2.getString(3) + ", ";
							}
						} else
							row += rs.getString(i) + ", ";
					}

					myDocument.add(new Paragraph(row, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
				}
				myDocument.add(new Paragraph(
						"-------------------------------------------------------------------------------------------"));

				myDocument.newPage();
				myDocument.close();

				JOptionPane.showMessageDialog(null, "Report was successfully generated");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void filter(String query) {

		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(modelFilter);
		tableFilter.setRowSorter(trs);

		trs.setRowFilter(RowFilter.regexFilter(query));
	}
}

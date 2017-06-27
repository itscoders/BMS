package pl.psk.projekt.bms.ui;


import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


public class StartWindow extends JFrame implements ActionListener
{   
  
	private static final long serialVersionUID = 1L;
	
	JLabel labelWindow;
	JButton move;
	JFrame frame = new JFrame();
		
	
	public StartWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		setTitle("Bus Management");

		
		setSize(600,600);
		setVisible(true);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		labelWindow=new JLabel("<html><center>Welcome To Bus Management System<br>\r\nITS CODERS & PABLO IT</center></html>");
		labelWindow.setToolTipText("");
		labelWindow.setHorizontalAlignment(SwingConstants.CENTER);
	

		labelWindow.setBounds(95,29,403,120);
		getContentPane().add(labelWindow);
		labelWindow.setPreferredSize(new Dimension(150, 100));
		labelWindow.setFont(new Font("Courier New", Font.BOLD, 20));
		labelWindow.setForeground(Color.DARK_GRAY);
		
		move =new JButton("Move forward");
		move.setBounds(204,350,175,50);
		move.addActionListener(this);
		getContentPane().add(move);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(this.getClass().getResource("/start.png")));
		background.setBounds(0, 0, 594, 571);
		getContentPane().add(background);
		

		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == move)
		{
			
			LoginWindow l = new LoginWindow();
			l.setVisible(true);
			this.dispose();
		}
			
		
		
	}
}
package pl.psk.projekt.bms;


import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


public class StartWindow extends JFrame implements ActionListener
{   
  
	private static final long serialVersionUID = 1L;
	
	JLabel labelWindow=new JLabel("Welcome To Bus Management System by ITS CODERS & PABLO IT ");
	JButton move =new JButton("Move forward");
	JFrame frame = new JFrame();
		
	
	public StartWindow()
	{

		
		setSize(600,600);
		setVisible(true);
		setLayout(null);
		setLocationRelativeTo(null);
	

		labelWindow.setBounds(125,100,325,50);
		add(labelWindow);
		labelWindow.setPreferredSize(new Dimension(150, 100));
		labelWindow.setFont(new Font("Courier New", Font.BOLD, 20));
		labelWindow.setForeground(Color.DARK_GRAY);
		
		
		move.setBounds(200,350,175,50);
		add(move);
		move.setBackground(Color.GRAY);
		move.addActionListener(this);

		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == move)
		{
			
			LoginWindow l = new LoginWindow(this);
			this.setVisible(false);
		}
			
		
		
	}
	
	
}
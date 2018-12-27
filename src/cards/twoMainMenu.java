package cards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import database.Login;
import executable.BasicStructure;

/**
 * This class creates and manages the main menu
 * @author Cathal
 */
public class twoMainMenu extends JPanel implements ActionListener
{
	
	static JLabel error , blank;
	JButton users, books, members, transactions ,exit ,logout;
	
	GridBagConstraints gbc = new GridBagConstraints();
	
	/**
	 * This method is the constructor for the main menu JPanel. 
	 */
	public twoMainMenu()
	{
		
		setLayout(new GridBagLayout());
		Border raisedBorder1 = BorderFactory.createLineBorder(new Color(211,211,211),3);
   		this.setBorder(raisedBorder1);
		gbc.insets = new Insets(5,0,5,0);
		
		Font font1 = new Font("Courier", Font.BOLD,20);
		Font font2 = new Font("Courier", Font.BOLD,17);
		
   		JLabel welcome = new JLabel("Welcome to the Main Menu");
   		welcome.setFont(font1);
   		welcome.setHorizontalAlignment(SwingConstants.CENTER);
   		gbc.gridx = 2;
   		gbc.gridy = 1;
   		gbc.gridwidth = 4;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		add(welcome,gbc);
   		
   		error = new JLabel("");
   		error.setForeground(Color.blue);
   		error.setFont(font2);
   		gbc.gridx = 2;
   		gbc.gridy = 2;
   		gbc.gridwidth = 4;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		error.setHorizontalAlignment(SwingConstants.CENTER);
   		add(error,gbc);
   		
   		users = new JButton("Manage Users (Admin Only) ");
   		users.setToolTipText("Click here to display the \"Manage Users Menus (Admins Only)\"");
   		users.setFont(font2);
   		users.setHorizontalAlignment(SwingConstants.CENTER);
   		gbc.gridx = 2;
   		gbc.gridy = 3;
   		gbc.gridwidth = 4;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		add(users,gbc);
   		users.addActionListener(this);
   		
   		books = new JButton("Manage Books");
   		books.setToolTipText("Click here to display the \"Manage Books Menus\"");
   		books.setFont(font2);
   		books.setHorizontalAlignment(SwingConstants.CENTER);
   		gbc.gridx = 2;
   		gbc.gridy = 4;
   		gbc.gridwidth = 4;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		add(books,gbc);
   		books.addActionListener(this);
   		
   		members = new JButton("Manage Members");
   		members.setToolTipText("Click here to display the \"Manage members Menus\"");
   		members.setFont(font2);
   		members.setHorizontalAlignment(SwingConstants.CENTER);
   		gbc.gridx = 2;
   		gbc.gridy = 5;
   		gbc.gridwidth = 4;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		add(members,gbc);
   		members.addActionListener(this);
   		
   		transactions = new JButton("Manage transactions");
   		transactions.setToolTipText("Click here to display the \"Manage Transactions Menus\"");
   		transactions.setFont(font2);
   		transactions.setHorizontalAlignment(SwingConstants.CENTER);
   		gbc.gridx = 2;
   		gbc.gridy = 6;
   		gbc.gridwidth = 4;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		add(transactions,gbc);
   		transactions.addActionListener(this);
   		
   		gbc.insets = new Insets(5,0,5,0);
   		exit  = new JButton ("  Exit  ");
   		exit.setToolTipText("Click here to exit the program");
   		exit.setFont(font2);
   		gbc.gridx = 1;
   		gbc.gridy = 7;
   		gbc.gridwidth = 2;
		exit.addActionListener(this);
   		add(exit,gbc);
   		exit.addActionListener(this);
   		
   		gbc.insets = new Insets(5,120,5,0);
   		logout  = new JButton ("Logout");
   		logout.setToolTipText("Click here to logout of the program");
   		logout.setFont(font2);
   		gbc.gridx = 3;
   		gbc.gridy = 7;
   		gbc.gridwidth = 2;
   		logout.addActionListener(this);
   		add(logout,gbc);
   		logout.addActionListener(this);
   		this.setBackground(new Color(6,152,114));
	
	}
	
	/**
	 * this method sets the welcome jlabel to tell the specific user welcome.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void changeErrorText() throws ClassNotFoundException, SQLException
	{
		
		error.setText("Welcome " + Login.getName(oneLoginPanel.loginDetails[0]) + "!");
	
	}
	
	/**
	 * this method handles Action Events
	 */
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource() == exit)
		{
			
			System.exit(0);
		
		}
		else if(e.getSource() == logout)
		{
			
			BasicStructure.gotoCard("1");
			oneLoginPanel.usernameField.setText("");
			oneLoginPanel.passwordField.setText("");
		
		}
		else if(e.getSource() == users)
		{
			
			if(oneLoginPanel.loginDetails[1] == 1)
			{
				
				BasicStructure.gotoCard("3");
				
				try {
					
					threeUsersPanel.changeErrorText();
	
				} catch (ClassNotFoundException | SQLException e1) 
				{
					
					e1.printStackTrace();
				
				}
			}
			else
			{
				
				JOptionPane.showMessageDialog(null, "You must be an Admin to edit Users", "ERROR", JOptionPane.ERROR_MESSAGE);
			
			}
		}
		else if(e.getSource() == books)
		{
			
			BasicStructure.gotoCard("4");
		
		}
		else if(e.getSource() == members)
		{
			
			BasicStructure.gotoCard("5");
		
		}
		else if(e.getSource() == transactions)
		{
			
			BasicStructure.gotoCard("6");
		
		}		
	}
}

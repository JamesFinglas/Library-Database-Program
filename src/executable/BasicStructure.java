package executable;

import database.setupDatabase;
import java.awt.CardLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import cards.oneLoginPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Dimension;

/**
 * This is the Main Class
 * @author Cathal Murphy & James Finglas
 * This program is for Educational & Interview Purposes Only
 */
public class BasicStructure 
{
	
	static JMenuBar menuBar;
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Double ResY = screenSize.getHeight();
	int resY = ResY.intValue();
	Double ResX = screenSize.getWidth();
	int resX = ResX.intValue();
	setupDatabase databaseInstance = new setupDatabase();
	{
		
		try 
		{
			
			setupDatabase.createDatabase();
		
		} catch (ClassNotFoundException | SQLException e)
		{
			
			e.printStackTrace();
		
		}
	}
	
	static CardLayout cl = new CardLayout();
	public static JFrame frame = new JFrame("Auto Index Structure");
	public static JPanel deck = new JPanel();
	static JPanel oneLoginCard = new cards.oneLoginPanel();
	JPanel twoMainCard = new cards.twoMainMenu();
	JPanel threeUserCard = new cards.threeUsersPanel();
	JPanel fourBookCard = new cards.fourBooksPanel();
	JPanel fiveMemberCard = new cards.fiveMembersPanel();
	JPanel sixTransactionCard = new cards.sixTransactionsPanel();
	static int x = 500;
	static int y = 400;
	
	/**
	 * Main JFrame Constructor
	 */
	public BasicStructure()
	{
		
		menuBar = new JMenuBar();
		JMenu menu = new JMenu("Navigate");
		menu.setToolTipText("Navigation menu");
		JMenuItem menuItem1 = new JMenuItem("Main Menu");
		menuItem1.setToolTipText("Click here to display the main menu");
		menuItem1.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e)
			{
				
				gotoCard("2");		
				
			}
		});
			
		JMenuItem menuItem2 = new JMenuItem("Manage Users (Admin Only)");
		menuItem2.setToolTipText("Click here to display the \"Manage Users Menus\"");
		menuItem2.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e)
			{
				
				if(oneLoginPanel.loginDetails[1] == 1)
				{
					
					gotoCard("3");

				}
				else
				{
					
					JOptionPane.showMessageDialog(null, "You must be an Admin to edit Users", "ERROR", JOptionPane.ERROR_MESSAGE);
				
				}
			}
		});
		
		JMenuItem menuItem3 = new JMenuItem("Manage Books");
		menuItem3.setToolTipText("Click here to display the \"Manage Books menu\"");
		menuItem3.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e)
			{
				
				gotoCard("4");
			
			}
		});
		
		JMenuItem menuItem4 = new JMenuItem("Manage Members");
		menuItem4.setToolTipText("Click here to display the \"Manage Memebers menu\"");
		menuItem4.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e)
			{
				
				gotoCard("5");
			
			}
		});
		
		JMenuItem menuItem5 = new JMenuItem("Manage Transactions");
		menuItem5.setToolTipText("Click here to display the \"Manage Transactions Menus\"");
		menuItem5.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e)
			{
				
				gotoCard("6");
			
			}
		});
		
		JMenuItem menuItem6 = new JMenuItem("Logout");
		menuItem6.setToolTipText("Click here to log out");
		menuItem6.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e)
			{
				
				oneLoginPanel.usernameField.setText("");
				oneLoginPanel.passwordField.setText("");
				gotoCard("1");
			
			}
		});
		
		JMenuItem menuItem7 = new JMenuItem("Exit");
		menuItem7.setToolTipText("Click here to exit the program");
		menuItem7.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e)
			{
				
				System.exit(0);
			
			}
		});

		menuBar.add(menu);
		menu.add(menuItem1);
		menu.add(menuItem2);
		menu.add(menuItem3);
		menu.add(menuItem4);
		menu.add(menuItem5);
		menu.addSeparator();
		menu.addSeparator();
		menu.add(menuItem6);
		menu.add(menuItem7);
				
		deck.setLayout(cl);
		deck.add(oneLoginCard, "1");
		deck.add(twoMainCard, "2");
		deck.add(threeUserCard, "3");
		deck.add(fourBookCard, "4");
		deck.add(fiveMemberCard, "5");
		deck.add(sixTransactionCard, "6");
		cl.show(deck,"1");
		frame.add(deck);
		frame.pack();
		frame.setResizable(false);
		deck.setBorder(new EmptyBorder(200, 200, 200, 200));
		deck.setBackground(new Color(23,98,131));

		Double width = frame.getSize().getWidth();
		int aWidth = width.intValue();
		Double height = frame.getSize().getHeight();
		int aHeight = height.intValue();
		
		int loc1 = resX/2 - (aWidth/2);
		int loc2 = resY/2 - (aHeight/2);
				
		frame.setLocation((loc1),(loc2));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ToolTipManager.sharedInstance().setInitialDelay(5);
				
	}
	
	/**
	 * This is the Main Method
	 * @param args 
	 */
	public static void main(String args[])
	{
		
		BasicStructure instance = new BasicStructure();
		getScreenSize();
		
	}
	
	/**
	 * This method handles Card calls and changing of panels
	 * @param x is the card number to be called.
	 */
	public static void gotoCard(String x)
	{
		
		if(x == "1")
		{
			
			disableMenuBar();
			deck.setBorder(new EmptyBorder(200, 200, 200, 200));
		
		}
		else if(x == "2")
		{
			
			enableMenuBar();
			deck.setBorder(new EmptyBorder(150, 200, 150, 200));

		}
		else if(x == "3")
		{
			
			deck.setBorder(new EmptyBorder(50, 50, 50, 50));

		}
		else if(x == "4")
		{
			
			deck.setBorder(new EmptyBorder(50, 50, 50, 50));

		}
		else if(x == "5")
		{
			
			deck.setBorder(new EmptyBorder(50, 50, 50, 50));

		}
		else if(x == "6")
		{
			
			deck.setBorder(new EmptyBorder(50, 50, 50, 50));

		}
		
		cl.show(deck, x);
	
	}
	
	/**
	 * This method places the JFrame in the center of the screen for all screen sizes.
	 * @return position of JFrame.
	 */
	public static Dimension getScreenSize()
	{
		
		Double width = frame.getSize().getWidth();
		Double height = frame.getSize().getHeight();	
		width = (width - 200) / 2 ;
		Double anotherWidth = width - (2/8 * width);
		height = (height - 200)/2;
		Double anotherHeight = height -(2/6 * height);
		
		int finalWidth = anotherWidth.intValue();
		int finalHeight = anotherHeight.intValue();
		
		return new Dimension(finalWidth,finalHeight);
	
	}
	
	/**
	 * Enables JMenuBar when logged in
	 */
	public static void enableMenuBar()
	{
		
		frame.setJMenuBar(menuBar);
	
	}
		
	/**
	 * disables JMenubar when logged out. Only Called on loginscreen.
	 */
	public static void disableMenuBar()
	{
		
		frame.setJMenuBar(null);
	
	}
}

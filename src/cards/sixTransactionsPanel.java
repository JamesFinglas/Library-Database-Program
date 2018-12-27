package cards;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import tables.TransactionsTable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import database.DatabaseConnection;
import database.Login;
import executable.BasicStructure;
import tables.UserTableBuilder;
import tables.UsersById;
import tables.UsersByName;
import tables.BooksByAuthor;
import tables.MemberById;
import tables.MemberByName;
import tables.BooksByPublisher;
import tables.BooksByTitle;
import tables.MemberTableBuilder;
import tables.MembersTable;
import tables.TransactionTableBuilder;
import tables.TransactionsByBookId;
import tables.TransactionsByMemberId;
import tables.TransactionsByTransId;
import tables.UserTable;

/**
 * this class creates and manages the transaction panel
 * @author Cathal Murphy & James Finglas
 */
public class sixTransactionsPanel extends JPanel implements ActionListener
{
	
	static JLabel error , searchResults;
	JButton searchTransactions, addMembers, editMembers, deleteMembers, logoutButton, exitButton, menuButton, refreshButton;
	String memberId, memberName, memberAddress, membermemberFine, cardId, memberFine;
	JPanel panel;
	JPanel southContainer ;
	JPanel table;
	public static TransactionTableBuilder builderInstance = new TransactionTableBuilder();
	
	GridBagConstraints gbc = new GridBagConstraints();
	GridBagConstraints tablegbc = new GridBagConstraints();
	
	/**
	 * this method is the constructor that creates the transaction panel
	 */
	public sixTransactionsPanel() 
	{
		
		setLayout(new GridLayout(2,1));
		Border raisedBorder1 = BorderFactory.createLineBorder(new Color(211,211,211),3);
   		this.setBorder(raisedBorder1);
		
		Font font1 = new Font("Courier", Font.BOLD,20);
		Font font2 = new Font("Courier", Font.BOLD,17);
		Font font3 = new Font("Courier", Font.BOLD,32);
			
		JPanel northContainer = new JPanel(new GridBagLayout());
		gbc.insets = new Insets(20,0,0,0);
   		JLabel welcome = new JLabel("Manage Transactions");
   		welcome.setFont(font1);
   		welcome.setHorizontalAlignment(SwingConstants.CENTER);
   		gbc.gridx = 0;
   		gbc.gridy = 0;
   		gbc.gridwidth = 4;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(welcome,gbc);
   		
   		error = new JLabel("");
   		error.setForeground(Color.blue);
   		error.setFont(font2);
   		gbc.gridx = 0;
   		gbc.gridy = 1;
   		gbc.gridwidth = 4;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		gbc.insets = new Insets(20,0,20,0);
   		error.setHorizontalAlignment(SwingConstants.CENTER);
   		northContainer.add(error,gbc);
   		
   		searchTransactions = new JButton("Search Transactions");
   		searchTransactions.setToolTipText("Click to search for a transaction");
   		searchTransactions.setFont(font2);
   		searchTransactions.addActionListener(this);
   		searchTransactions.setToolTipText("Click here to search transactions");
   		gbc.gridx = 0;
   		gbc.gridy = 2;
   		gbc.gridwidth = 1;
   		gbc.insets = new Insets(5,20,5,-5);
   		northContainer.add(searchTransactions,gbc);
   		

   		refreshButton = new JButton("Refresh Table");
   		refreshButton.setToolTipText("Click here refresh the transaction table");
   		refreshButton.addActionListener(this);
   		refreshButton.setToolTipText("Click here to refresh users table");
   		refreshButton.setFont(font2);
   		gbc.gridx = 1;
   		gbc.gridy = 2;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(refreshButton,gbc);
   		
   		menuButton = new JButton("Main Menu");
   		menuButton.setToolTipText("Click here to view the main menu");
   		menuButton.addActionListener(this);
   		menuButton.setToolTipText("Click here to return to Main Menu");
   		menuButton.setFont(font2);
   		gbc.gridx = 0;
   		gbc.gridy = 3;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(menuButton,gbc);
   		
   		exitButton = new JButton("Exit");
   		exitButton.setToolTipText("Click here to exit the program");
   		exitButton.addActionListener(this);
   		exitButton.setToolTipText("Click here to Exit Program");
   		exitButton.setFont(font2);
   		gbc.gridx = 1;
   		gbc.gridy = 3;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(exitButton,gbc);
   		
   		logoutButton = new JButton("Logout");
   		logoutButton.setToolTipText("Click here to logout of the program");
   		logoutButton.addActionListener(this);
   		logoutButton.setToolTipText("Click here to logout of Program & return to Main Menu");
   		logoutButton.setFont(font2);
   		gbc.gridx = 0;
   		gbc.gridy = 4;
   		gbc.gridwidth = 2;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(logoutButton,gbc);
	
   		southContainer = new JPanel(new GridBagLayout());
   		searchResults = new JLabel("Search Results");
   		searchResults.setHorizontalAlignment(SwingConstants.CENTER);
   		searchResults.setFont(font1);
   		gbc.gridx = 0;
   		gbc.gridy = 0;
   		gbc.gridwidth = 3;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		gbc.insets = new Insets(0,0,5,0);
   		southContainer.setBackground(new Color(6,152,114));
   		southContainer.add(searchResults, gbc);
		   		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.weighty = 1;
		gbc.insets = new Insets(5,0,50,0);
		 
		table = new TransactionsTable();
		southContainer.add(table,gbc);
	   	
   		northContainer.setBackground(new Color(6,152,114));
   		this.add(northContainer);
	  	this.add(southContainer);	
   		this.setBackground(new Color(6,152,114));
   		
	}
	
	/**
	 * this method changes the welcome label to tell the specific user welcome.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void changeErrorText() throws ClassNotFoundException, SQLException
	{
		
		error.setText("Welcome " + Login.getName(oneLoginPanel.loginDetails[0]) + "!");
	
	}

	/**
	 * This method handles action events
	 */
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource() == searchTransactions)
		{
			
			Object[] options1 = {"Search by Transaction ID",
                    "Search by Member ID","Search by Book ID",
                    "Quit"};
			
			int choice =	JOptionPane.showOptionDialog(null,
					"How would you like to search:",
					"Search Options",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					null,
					options1,
					null);
		
		if(choice == 0) // Transaction Id
		{

			String transId = "";
			boolean success = false;
			boolean cancelled = false;
			
			while(transId !=null && success == false  ) // until gets cancelled or passess successfully will loop.
			{
				
				success = true;
				
				transId = JOptionPane.showInputDialog(this, "Please enter a Transaction ID to be searched");
				
				if(transId == null)
				{
				
					cancelled = true;
				
				}
				else if(transId.isEmpty() && transId != null)
				{
					
					JOptionPane.showMessageDialog(null, "Id can't be left blank","ERROR", JOptionPane.ERROR_MESSAGE);	
					success = false;
				}
				else if(transId != null)	// if not cancelled
				{
				
					char[] testArray = transId.toCharArray();
					for(int i = 0 ; i < testArray.length ; i++)
					{
						
						if( testArray[i] < 48 || testArray[i] > 58 ) // is it not between 1-9
						{
							
							success = false;
						
						}	
					}
					
					if (!success)
					{
						
						JOptionPane.showMessageDialog(null, "Id must be a number greater than 0","ERROR", JOptionPane.ERROR_MESSAGE);	
					
					}
				}

				if(success && !cancelled)
				{
					
					southContainer.remove(searchResults);
					southContainer.remove(table);
					gbc.gridx = 0;
			   		gbc.gridy = 0;
			   		gbc.gridwidth = 3;
			   		gbc.fill = GridBagConstraints.HORIZONTAL;
			   		gbc.insets = new Insets(0,0,5,0);
					southContainer.add(searchResults,gbc);
					gbc.gridx = 0;
					gbc.gridy = 1;
					gbc.gridwidth = 3;
					gbc.weighty = 1;
					gbc.insets = new Insets(5,0,50,0);

					builderInstance.setTransId(Integer.parseInt(transId));
					table = new TransactionsByTransId();
					southContainer.add(table,gbc);
					revalidate();
					repaint();
				
				}
				
				cancelled = false;
			
			}
		}
		
		if(choice == 1)	// Member Id
		{
			
			String memberId = "";
			boolean success = false;
			boolean cancelled = false;
			
			while(memberId !=null && success == false  ) // until gets cancelled or passes successfully will loop.
			{
				
				success = true;
				
				memberId = JOptionPane.showInputDialog(this, "Please enter a Member Id to be searched");				
				
				if(memberId == null)
				{
				
					cancelled = true;
				
				}
				else if(memberId.length()>40)
				{
					
					JOptionPane.showMessageDialog(null, "Id can't be more than 40 characters","ERROR", JOptionPane.ERROR_MESSAGE);	
					success = false;
				
				}
				else if(memberId.isEmpty() && memberId != null)
				{
					
					JOptionPane.showMessageDialog(null, "Id can't be left blank","ERROR", JOptionPane.ERROR_MESSAGE);	
					success = false;
				
				}
				else if(memberId != null)	// if not cancelled
				{
				
					char[] testArray = memberId.toCharArray();
					for(int i = 0 ; i < testArray.length ; i++)
					{
						
						if( testArray[i] < 48 || testArray[i] > 58 ) // is it not between 1-9
						{
							
							success = false;
						
						}	
					}
					
					if (!success)
					{
						
						JOptionPane.showMessageDialog(null, "Id must be a number greater than 0","ERROR", JOptionPane.ERROR_MESSAGE);	
					
					}
				}

				if(success && !cancelled)
				{
					
					southContainer.remove(searchResults);
					southContainer.remove(table);
					gbc.gridx = 0;
			   		gbc.gridy = 0;
			   		gbc.gridwidth = 3;
			   		gbc.fill = GridBagConstraints.HORIZONTAL;
			   		gbc.insets = new Insets(0,0,5,0);
					southContainer.add(searchResults,gbc);
					gbc.gridx = 0;
					gbc.gridy = 1;
					gbc.gridwidth = 3;
					gbc.weighty = 1;
					gbc.insets = new Insets(5,0,50,0);

					builderInstance.setMemberId(Integer.parseInt(memberId));
					table = new TransactionsByMemberId();
					southContainer.add(table,gbc);
					revalidate();
					repaint();
				}
				
				cancelled = false;
			
			}
		}
		
		if(choice == 2)	// book Id
		{
			
			String bookId = "";
			boolean success = false;
			boolean cancelled = false;
			
			while(bookId !=null && success == false  ) // until gets cancelled or passess successfully will loop.
			{
				
				success = true;
				bookId = JOptionPane.showInputDialog(this, "Please enter a Book Id to be searched");
				
				if(bookId == null)
				{
				
					cancelled = true;
				
				}
				else if(bookId.isEmpty() && bookId != null)
				{
					
					JOptionPane.showMessageDialog(null, "Id can't be left blank","ERROR", JOptionPane.ERROR_MESSAGE);	
					success = false;
				
				}
				else if(bookId != null)	// if not cancelled
				{
			
					char[] testArray = bookId.toCharArray();
					for(int i = 0 ; i < testArray.length ; i++)
					{
						
						if( testArray[i] < 48 || testArray[i] > 58 ) // is it not between 1-9
						{
							
							success = false;
						
						}	
					}
					
					if (!success)
					{
						
						JOptionPane.showMessageDialog(null, "Id must be a number greater than 0","ERROR", JOptionPane.ERROR_MESSAGE);	
					
					}
				}

				if(success && !cancelled)
				{
					southContainer.remove(searchResults);
					southContainer.remove(table);
					gbc.gridx = 0;
				   	gbc.gridy = 0;
				   	gbc.gridwidth = 3;
				   	gbc.fill = GridBagConstraints.HORIZONTAL;
				   	gbc.insets = new Insets(0,0,5,0);
					southContainer.add(searchResults,gbc);
					gbc.gridx = 0;
					gbc.gridy = 1;
					gbc.gridwidth = 3;
					gbc.weighty = 1;
					gbc.insets = new Insets(5,0,50,0);
	
					builderInstance.setBookId(Integer.parseInt(bookId));
					table = new TransactionsByBookId();
					southContainer.add(table,gbc);
					revalidate();
					repaint();
					
				}
					
					cancelled = false;
				}
			}
		}
		else if(e.getSource() == refreshButton)
		{
			
			southContainer.remove(searchResults);
			southContainer.remove(table);
			gbc.gridx = 0;
	   		gbc.gridy = 0;
	   		gbc.gridwidth = 3;
	   		gbc.fill = GridBagConstraints.HORIZONTAL;
	   		gbc.insets = new Insets(0,0,5,0);
			southContainer.add(searchResults,gbc);
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridwidth = 3;
			gbc.weighty = 1;
			gbc.insets = new Insets(5,0,50,0);
	
			table = new TransactionsTable();
			southContainer.add(table,gbc);
			revalidate();
			repaint();
		
		}
		else if(e.getSource() == logoutButton)
		{
			BasicStructure.gotoCard("1");
			oneLoginPanel.usernameField.setText("");
			oneLoginPanel.passwordField.setText("");
		}
		else if(e.getSource() == exitButton)
		{
			System.exit(0);
		}
		else  if(e.getSource() == menuButton)
		{
			BasicStructure.gotoCard("2");
		}
	}
}

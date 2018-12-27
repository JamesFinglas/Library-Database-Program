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
import tables.UserTable;

/**
 * this class creates and manages the users panel
 * @author Cathal Murphy & James Finglas
 */
public class threeUsersPanel extends JPanel implements ActionListener
{
	
	static JLabel error , searchResults;
	JButton searchUsers, addUsers, editUsers, deleteUsers, searchButton, logoutButton, exitButton, menuButton, refreshButton;
	String userId, userName, password, privilege;
	JPanel panel;
	JPanel southContainer ;
	JPanel table;
	public static UserTableBuilder instance = new UserTableBuilder();
	
	GridBagConstraints gbc = new GridBagConstraints();
	GridBagConstraints tablegbc = new GridBagConstraints();
	
	/**
	 * this method constructs the users panel
	 */
	public threeUsersPanel() 
	{
		
		setLayout(new GridLayout(2,1));
		Border raisedBorder1 = BorderFactory.createLineBorder(new Color(211,211,211),3);
   		this.setBorder(raisedBorder1);
		
		Font font1 = new Font("Courier", Font.BOLD,20);
		Font font2 = new Font("Courier", Font.BOLD,17);
		Font font3 = new Font("Courier", Font.BOLD,32);
			
		JPanel northContainer = new JPanel(new GridBagLayout());
		gbc.insets = new Insets(20,0,0,0);
   		JLabel welcome = new JLabel("Manage Users");
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
   		
   		searchUsers = new JButton("Search User");
   		searchUsers.setToolTipText("Click here to search for a user"); 
   		searchUsers.setFont(font2);
   		searchUsers.addActionListener(this);
   		searchUsers.setToolTipText("Click here to search users");
   		gbc.gridx = 0;
   		gbc.gridy = 2;
   		gbc.gridwidth = 1;
   		gbc.insets = new Insets(5,20,5,-5);
   		northContainer.add(searchUsers,gbc);
   		
   		addUsers = new JButton("Add User");
   		addUsers.setToolTipText("Click here to add a user");
   		addUsers.addActionListener(this);
   		addUsers.setToolTipText("Click here to add a user");
   		addUsers.setFont(font2);
   		addUsers.setToolTipText("Click here to add users to the database");
   		gbc.gridx = 1;
   		gbc.gridy = 2;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(addUsers,gbc);
   		
   		editUsers = new JButton(" Edit User ");
   		editUsers.setToolTipText("Click here to edit a user");
   		editUsers.addActionListener(this);
   		editUsers.setToolTipText("Click here to edit a user");
   		editUsers.setFont(font2);
   		gbc.gridx = 2;
   		gbc.gridy = 2;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(editUsers,gbc);
   		
   		deleteUsers = new JButton("Delete User");
   		deleteUsers.setToolTipText("Click here to delete a user");
   		deleteUsers.addActionListener(this);
   		deleteUsers.setToolTipText("Click here to delete user");
   		deleteUsers.setFont(font2);
   		gbc.gridx = 3;
   		gbc.gridy = 2;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(deleteUsers,gbc);
   		
   		refreshButton = new JButton("Refresh Table");
   		refreshButton.setToolTipText("Click here to refresh the user table");
   		refreshButton.addActionListener(this);
   		refreshButton.setToolTipText("Click here to refresh users table");
   		refreshButton.setFont(font2);
   		gbc.gridx = 0;
   		gbc.gridy = 3;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(refreshButton,gbc);
   		
   		menuButton = new JButton("Main Menu");
   		menuButton.setToolTipText("Click here to view the main menu");
   		menuButton.addActionListener(this);
   		menuButton.setToolTipText("Click here to return to Main Menu");
   		menuButton.setFont(font2);
   		gbc.gridx = 1;
   		gbc.gridy = 3;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(menuButton,gbc);
   		
   		exitButton = new JButton("Exit");
   		exitButton.setToolTipText("Click here to exit the program");
   		exitButton.addActionListener(this);
   		exitButton.setToolTipText("Click here to Exit Program");
   		exitButton.setFont(font2);
   		gbc.gridx = 2;
   		gbc.gridy = 3;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(exitButton,gbc);
   		
   		logoutButton = new JButton("Logout");
   		logoutButton.setToolTipText("Click here to logout of the program");
   		logoutButton.addActionListener(this);
   		logoutButton.setToolTipText("Click here to logout of Program & return to Main Menu");
   		logoutButton.setFont(font2);
   		gbc.gridx = 3;
   		gbc.gridy = 3;
   		gbc.gridwidth = 1;
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
		 
		table = new UserTable();
		southContainer.add(table,gbc);
	   	
   		northContainer.setBackground(new Color(6,152,114));
   		this.add(northContainer);
	  	this.add(southContainer);
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
	 * this method handles action events
	 */
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource() == searchUsers)
		{
			
			Object[] options1 = {"Search by ID",
                    "Search by Username",
                    "Quit"};
			
			int choice =	JOptionPane.showOptionDialog(null,
					"How would you like to search:",
					"Search Options",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					null,
					options1,
					null);
		
		if(choice == 0)
		{
			
			String id = "";
			boolean success = false;
			boolean cancelled = false;
			
			while(id !=null && success == false  ) // until gets cancelled or passess successfully will loop.
			{
				success = true;
				id = JOptionPane.showInputDialog(this, "Please enter a User ID to be searched:");
				
				if(id == null)
				{
					
					cancelled = true;
				
				}
				else if(id.isEmpty() && id != null)
				{
					
					JOptionPane.showMessageDialog(null, "Id can't be left blank","ERROR", JOptionPane.ERROR_MESSAGE);	
					success = false;
				
				}
				else if( id.length() > 40  )
				{
					
					success = false;
					JOptionPane.showMessageDialog(null, "Id can't be more than 40 characters","ERROR", JOptionPane.ERROR_MESSAGE);	
				
				}
				else if(id != null)	// if not cancelled
				{
					
					char[] testArray = id.toCharArray();
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

					instance.setId(id);
					table = new UsersById();
					southContainer.add(table,gbc);
					revalidate();
					repaint();
				
				}
				
				cancelled = false;
			
			} 
		}
		
		if(choice == 1)
		{
			
			String username = "";
			boolean success = false;
			boolean cancelled = false;
			
			while(username !=null && success == false  ) // until gets cancelled or passes successfully will loop.
			{
				
				success = true;
				username = JOptionPane.showInputDialog(this, "Please enter a username to be searched:");				
				
				if(username == null)
				{
					
					cancelled = true;
				
				}
				else if(username.isEmpty() && username != null)
				{
					
					JOptionPane.showMessageDialog(null, "ERROR: Username can't be left blank.","ERROR", JOptionPane.ERROR_MESSAGE);	
					success = false;
				
				}
				else if(username.length()> 40 )
				{
					
					JOptionPane.showMessageDialog(null, "ERROR: Username cannot be more than 40 characters please try again.","ERROR", JOptionPane.ERROR_MESSAGE);	
					success = false;
				
				}
				
				boolean doesExist = false;

				if(success && !cancelled)
				{
					
					Statement stmt;
					
					try 
					{
						
						stmt = DatabaseConnection.startConnection();
						String doesUserExist =  "Select * from Users where user_Name = " + "'" + username + "'";
						ResultSet rset = stmt.executeQuery(doesUserExist);
						while(rset.next())
						{
							
							doesExist = true;
						
						}
						
						if(!doesExist)
						{
							
							success = false;
							JOptionPane.showMessageDialog(null, "ERROR: No user with UserName " + username + ". Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);
	
						}
					} catch (ClassNotFoundException e1) 
					{
						
						e1.printStackTrace();
					
					} catch (SQLException e1) 
					{
						
						e1.printStackTrace();
					
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

					instance.setUsername(username);
					table = new UsersByName();
					southContainer.add(table,gbc);
					revalidate();
					repaint();
				
				}
				
				cancelled = false;
			
			}
		}
	}
		
		//################################################# ADD USERNAME #############################################

		else if(e.getSource() == addUsers)
		{
			
			int globalGaurd = 1;
			
			try {
				
				Statement stmt = DatabaseConnection.startConnection();

				if(globalGaurd == 1)
				{
					
					boolean success = false;
					boolean cancelled = false;
					
					while(!success && !cancelled)
					{
						
						success = true;
						userName= JOptionPane.showInputDialog("Please enter a username for the new user:");
				        
				        if(userName == null) // Cancelled
						{
				        	
							success = false;
							cancelled = true;
							globalGaurd = 0;
							
						}
						else if(userName.length() <= 0 && !cancelled)
						{
							
							success = false;
							JOptionPane.showMessageDialog(null, "ERROR: UserName cannot be blank. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);	
							
						}
						else if(userName.length() > 40)
						{
							
							success = false;
							JOptionPane.showMessageDialog(null, "ERROR: UserName more than 40 characters. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);	
						
						}
				        
				        if(globalGaurd == 1 && !cancelled && success)
				        {
					        	    
					        try
							{
					        	
								String strSelect = "select * from Users WHERE user_Name =" + "'" + userName + "'" ;
								ResultSet rset = stmt.executeQuery(strSelect);
								int userCount = 0;
								
								while(rset.next())
								{
									
									userCount++;
								
								}
								
								if(userCount> 0)
								{
									
									JOptionPane.showMessageDialog(null, "ERROR: The Username " + userName + " is taken. Please try again","ERROR", JOptionPane.ERROR_MESSAGE);	
									success = false;
								
								}
							} catch(SQLException e1)
							{
								
								e1.printStackTrace();
							
							}
				        }
					}
				}
									
				//################################################# ADD PASSWORD #############################################

				if(globalGaurd == 1)
				{
					
					boolean success = false;
					boolean cancelled = false;
					
					while(!success && !cancelled)
					{
						
						success = true;	
				        password= JOptionPane.showInputDialog("Please enter a password for the new user:");
				        
				        if(password == null) // Cancelled
						{
				        	
				        	success = false;
				        	cancelled = true;
							globalGaurd = 0;
						
						}
						else if(password.length() <= 0 && !cancelled)
						{
							
							 success = false;
							JOptionPane.showMessageDialog(null, "ERROR: Password cannot be blank. Please try again","ERROR", JOptionPane.ERROR_MESSAGE);	

						}
						else if(password.length()> 40 && !cancelled)
						{
							
							success  = false;
							JOptionPane.showMessageDialog(null, "ERROR: Password cannot be more than 40 characters. Please try again. Please try again","ERROR", JOptionPane.ERROR_MESSAGE);	

						}
					}
				}
		        
				//############################################### ADD PRIVILEGE ##############################################
				
				if(globalGaurd == 1)
				{
					
					boolean success = false;
					boolean cancelled = false;
					
					while(!success && !cancelled)
					{
						
						success = true;
				        privilege= JOptionPane.showInputDialog("Please enter a privilege for the new user(0 for user and 1 for admin):");
				        
				        if(privilege == null) // Cancelled
						{
				        	
				        	success = false;
				        	cancelled = true;
							globalGaurd = 0;
						
						}
						else if(privilege.length() <= 0 && !cancelled)
						{
							
							success = false;
							JOptionPane.showMessageDialog(null, "ERROR: Privilege cannot be blank. Please try again. Please try again","ERROR", JOptionPane.ERROR_MESSAGE);	

						}
						else if(!privilege.equals("0") && !privilege.equals("1") && !cancelled)
						{
							
							success = false;
							JOptionPane.showMessageDialog(null, "ERROR: Privilege must be 0 or 1. Please try again. Please try again","ERROR", JOptionPane.ERROR_MESSAGE);	

						}
					}
				}
				
				if(globalGaurd == 1)
				{
					
			        try
					{
			        	
						String sqlInsert = "insert into Users" + " (user_Name,user_Password,user_Privilege) values (" + "'"+userName+"'" +","+ "'"+password+"'" +","+ ""+privilege+"" + ")";
						int countInserted = stmt.executeUpdate(sqlInsert);																 
						
					} catch(SQLException e1)
					{
						
						e1.printStackTrace();
					
					} finally
			        {
						
			        	stmt.close();
			        
			        }
				}
			} catch (ClassNotFoundException e1) 
			{
				
				e1.printStackTrace();
			
			} catch (SQLException e1) 
			{
				
				e1.printStackTrace();
			
			}
			
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

			table = new UserTable();
			southContainer.add(table,gbc);
			revalidate();
			repaint();
	        
		}
		
		//################################################# EDIT ID #############################################
		
		else if(e.getSource() == editUsers)
		{
			
			int greaterGaurd = 1;
			
				try {
					
					Statement stmt = DatabaseConnection.startConnection();
	
					int idGaurd = 1;
					int globalGaurd = 1;
					char[] testArray = null;
				
					String userId = "";
					boolean success = false;
					boolean cancelled = false;
				
					while(userId!=null && success == false)
					{
					
						userId= JOptionPane.showInputDialog(null, "Please enter the Id of the User you'd like to change."); // 2
						if(userId != null && userId.length() > 0)
						{
							
							testArray = userId.toCharArray();
					
						}
						
						if(userId == null) // Cancelled
						{
						
							cancelled = true;
							globalGaurd = 0;
					
						}
						else if(!cancelled && userId.length() <= 0)
						{
						
							success = false;
							JOptionPane.showMessageDialog(null, "User Id can't be left blank","ERROR", JOptionPane.ERROR_MESSAGE);	
					
						}
					
						if(userId != null && userId.length() > 0)
						{
							success = true;
							for(int i = 0 ; i < testArray.length ; i++)
							{
							
								if( testArray[i] < 48 || testArray[i] > 58 ) // is it not between 1-9
								{
								
									success = false;
							
								}	
							}
						
						if(!success)
						{
							
							JOptionPane.showMessageDialog(null, "User Id must be a positive Integer.","ERROR", JOptionPane.ERROR_MESSAGE);	
						
						}
					}
					
					if(!cancelled && userId.length() > 40 )
					{
						
						success = false;
						JOptionPane.showMessageDialog(null, "User Id must be no longer than 40 characters","ERROR", JOptionPane.ERROR_MESSAGE);	
						
					}
							
					if(!cancelled && globalGaurd == 1 && success)
					{
					
						String str = "Select * from users where user_Id =" + "'"+ userId + "'" ;
						ResultSet rset = stmt.executeQuery(str);
						
						while(rset.next())
						{
							
							success = true;
							idGaurd = 0;
						
						}
						if(idGaurd != 0)
						{
							
							success = false;
							JOptionPane.showMessageDialog(null, "No user with such Id","ERROR", JOptionPane.ERROR_MESSAGE);	
						
						}
					}
				}
				
				//################################################# EDIT USERNAME #############################################
				
				if(globalGaurd == 1)
				{
					int nameGaurd = 1;
					success = false;
					cancelled = false;
				
					while(!success && !cancelled)
					{
						
						success = true;
				        userName= JOptionPane.showInputDialog("Please enter a new username");
				        
				        if(userName == null) // Cancelled
						{
				        	
							success = false;
							cancelled = true;
							globalGaurd = 0;
							
						}
						else if(userName.length() <= 0)
						{
							
							success = false;
							JOptionPane.showMessageDialog(null, "ERROR: UserName cannot be blank. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);	

						}
						else if(userName.length() > 40)
						{
							
							success = false;
							JOptionPane.showMessageDialog(null, "ERROR: UserName cannot be more than 40 Characters. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);	
						
						}
				        
				        if(globalGaurd == 1 && !cancelled && success)
				        {
					        try
							{
							
					        	String strSelect = "select * from Users WHERE user_Name =" + "'" + userName + "'" ;
								ResultSet rset = stmt.executeQuery(strSelect);
								
								while(rset.next())
								{
									
									if(!(rset.getString("user_Id").equals(userId)))
									{
										
										success = false;
									
									}
								}
								
								if(!success)
								{
									
									JOptionPane.showMessageDialog(null, "ERROR: The Username " + userName + " is taken. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);	
								
								}	
							} catch(SQLException e1)
							{
								
								e1.printStackTrace();
							
							}    
				        }
					}
				}
				
				//################################################# EDIT PASSWORD #############################################
	
				if(globalGaurd == 1)
				{
					
					success = false;
					cancelled = false;
				
					while(!success && !cancelled)
					{
						
						success = true;
				        password= JOptionPane.showInputDialog("Please enter a password for this user");
				        
				        if(password == null) // Cancelled
						{
				        	
				        	cancelled = true;
				        	success = false;
							globalGaurd = 0;
						
						}
						else if(password.length() <= 0 && !cancelled)
						{
							
							success = false;
							JOptionPane.showMessageDialog(null, "ERROR: Password cannot be blank. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);	

						}
						else if(password.length() > 40 && !cancelled)
						{
							
							success = false;
							JOptionPane.showMessageDialog(null, "ERROR: Password cannot be more than 40 characters please try again.","ERROR", JOptionPane.ERROR_MESSAGE);	
						
						}
					}
				}
		        
				//############################################### EDIT PRIVILEGE ##############################################
				
				if(globalGaurd == 1)
				{
					
					success = false;
					cancelled = false;
				
					while(!success && !cancelled)
					{
						
						success = true;
				        privilege= JOptionPane.showInputDialog("Please enter a new privilege for this user.");
				        
				        if(privilege == null) // Cancelled
						{
				        	
				        	success = false;
				        	cancelled = true;
							globalGaurd = 0;
						
						}
						else if(privilege.length() <= 0 && !cancelled)
						{
							
							success = false;
							JOptionPane.showMessageDialog(null, "ERROR: Privilege cannot be blank. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);	

						}
						else if(!privilege.equals("0") && !privilege.equals("1") && !cancelled)
						{
							
							success = false;
							JOptionPane.showMessageDialog(null, "ERROR: Privilege must be either 1 or 0. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);	

						}
						else if(userId.equals("1") && !privilege.equals("1"))
						{
							
							success = false;
							JOptionPane.showMessageDialog(null, "ERROR: Default admin user privilege can not be altered. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);
							
						}
					}
				}
				
				if(globalGaurd == 1)
				{
					
			        try
					{
			        	
			        	String strUpdate = "update Users set user_Name = " + "'"+userName+"'" +", user_Password = "+ "'"+password+"'" +", user_Privilege ="+ ""+privilege+"  " + "WHERE user_Id = " + "'"+userId+"'" + ";"  ;
			    		int countUpdated = stmt.executeUpdate(strUpdate);
						
					} catch(SQLException e1)
					{
						
						e1.printStackTrace();
					
					}
			        finally
			        {
			        	
			        	stmt.close();
			        
			        }
				}
			} catch (ClassNotFoundException e1) 
			{
				
				e1.printStackTrace();
			
			} catch (SQLException e1) 
			{
				
				e1.printStackTrace();
			
			}
			
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
	
			table = new UserTable();
			southContainer.add(table,gbc);
			revalidate();
			repaint();
	
		}
		
		//################################################# DELETE USER #############################################

		else if(e.getSource() == deleteUsers)
		{			
			try {
				
				Statement stmt = DatabaseConnection.startConnection();
				
				int globalGaurd = 1;
				boolean success = false;
				boolean cancelled = false;
				while(!success && !cancelled)
				{
					success = true;
					userId= JOptionPane.showInputDialog(null, "Please enter the Id of the User you'd like to delete.");
					
					if(userId == null) // Cancelled
					{
						
						success = false;
						cancelled = true;
						globalGaurd = 0;
					
					}
					else if(userId.length() <= 0)
					{
						
						success = false;
						JOptionPane.showMessageDialog(null, "ERROR: User Id cannot be blank. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);

					}
					else if(userId.length() > 40)
					{
						
						success = false;
						JOptionPane.showMessageDialog(null, "ERROR: User Id cannot be more than 40 characters. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);
						
					}
					else if(userId.equals("1"))
					{
						
						success = false;
						JOptionPane.showMessageDialog(null, "ERROR: Super Admin cannot be deleted. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);
						
					}
					
					boolean doesExist = false;

					if(success && !cancelled)
					{
						
						String doesUserExist =  "Select * from Users where user_Id = " + userId;
						ResultSet rset = stmt.executeQuery(doesUserExist);
						
						while(rset.next())
						{
							
							doesExist = true;
						
						}
						
						if(!doesExist)
						{
							
							success = false;
							JOptionPane.showMessageDialog(null, "ERROR: No user with id " + userId + ". Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);

						}
					}
					
					if(!cancelled && success)
					{
						
						char[] testArray = userId.toCharArray();

						for(int i = 0 ; i < testArray.length ; i++)
						{
							
							if( testArray[i] < 48 || testArray[i] > 58 ) // is it not between 1-9
							{
								
								success = false;
							
							}	
						}
						
						if(!success)
						{
							
							JOptionPane.showMessageDialog(null, "User Id must be a positive Integer.","ERROR", JOptionPane.ERROR_MESSAGE);	
						
						}
					}
				}
				
				if(globalGaurd == 1)
				{
					
			        try
					{
			        	
			        	String sqlDelete = "delete from Users where user_Id = " + userId;
						stmt.executeUpdate(sqlDelete);
						
					} catch(SQLException e1)
					{
						
						e1.printStackTrace();
					
					} finally
			        {
						
			        	stmt.close();
			        
			        }
				}
			} catch (ClassNotFoundException e1) 
			{
				
				e1.printStackTrace();
			
			} catch (SQLException e1) 
			{
				
				e1.printStackTrace();
			
			}
				
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
	
			table = new UserTable();
			southContainer.add(table,gbc);
			revalidate();
			repaint();
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

			table = new UserTable();
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
	
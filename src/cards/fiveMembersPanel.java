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
import tables.BooksByAuthor;
import tables.MemberById;
import tables.MemberByName;
import tables.BooksByPublisher;
import tables.BooksByTitle;
import tables.MemberTableBuilder;
import tables.MembersTable;
import tables.UserTable;

/**
 * this class creates and manages the members panel
 * @author Cathal Murphy & James Finglas
 */
public class fiveMembersPanel extends JPanel implements ActionListener

{
	
	static JLabel error , searchResults;
	JButton searchMembers, addMembers, editMembers, deleteMembers, searchButton, logoutButton, exitButton, menuButton, refreshButton, fineButton;
	String memberId, memberName, memberAddress, membermemberFine, cardId, memberFine;
	JPanel panel;
	JPanel southContainer ;
	JPanel table;
	public static MemberTableBuilder memberInstance = new MemberTableBuilder();
	GridBagConstraints gbc = new GridBagConstraints();
	GridBagConstraints tablegbc = new GridBagConstraints();
	
	/**
	 * this method is the constructor for the members panel
	 */
	public fiveMembersPanel() 
	{
		
		setLayout(new GridLayout(2,1));
		Border raisedBorder1 = BorderFactory.createLineBorder(new Color(211,211,211),3);
   		this.setBorder(raisedBorder1);
		
		Font font1 = new Font("Courier", Font.BOLD,20);
		Font font2 = new Font("Courier", Font.BOLD,17);
		Font font3 = new Font("Courier", Font.BOLD,32);
			
		JPanel northContainer = new JPanel(new GridBagLayout());
		gbc.insets = new Insets(20,0,0,0);
   		JLabel welcome = new JLabel("Manage Members");
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
   		
   		searchMembers = new JButton("Search Member");
   		searchMembers.setFont(font2);
   		searchMembers.addActionListener(this);
   		searchMembers.setToolTipText("Click here to search members");
   		gbc.gridx = 0;
   		gbc.gridy = 2;
   		gbc.gridwidth = 1;
   		gbc.insets = new Insets(5,20,5,-5);
   		northContainer.add(searchMembers,gbc);
   		
   		addMembers = new JButton("Add Member");
   		addMembers.addActionListener(this);
   		addMembers.setToolTipText("Click here to add a member");
   		addMembers.setFont(font2);
   		gbc.gridx = 1;
   		gbc.gridy = 2;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(addMembers,gbc);
   		
   		editMembers = new JButton(" Edit Member ");
   		editMembers.addActionListener(this);
   		editMembers.setToolTipText("Click here to edit a user");
   		editMembers.setFont(font2);
   		gbc.gridx = 2;
   		gbc.gridy = 2;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(editMembers,gbc);
   		
   		deleteMembers = new JButton("Delete Member");
   		deleteMembers.addActionListener(this);
   		deleteMembers.setToolTipText("Click here to delete a Member");
   		deleteMembers.setFont(font2);
   		gbc.gridx = 3;
   		gbc.gridy = 2;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(deleteMembers,gbc);
   		
   		refreshButton = new JButton("Refresh Table");
   		refreshButton.addActionListener(this);
   		refreshButton.setToolTipText("Click here to refresh users table");
   		refreshButton.setFont(font2);
   		gbc.gridx = 0;
   		gbc.gridy = 3;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(refreshButton,gbc);
   		
   		menuButton = new JButton("Main Menu");
   		menuButton.addActionListener(this);
   		menuButton.setToolTipText("Click here to return to Main Menu");
   		menuButton.setFont(font2);
   		gbc.gridx = 1;
   		gbc.gridy = 3;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(menuButton,gbc);
   		
   		exitButton = new JButton("Exit");
   		exitButton.addActionListener(this);
   		exitButton.setToolTipText("Click here to Exit Program");
   		exitButton.setFont(font2);
   		gbc.gridx = 2;
   		gbc.gridy = 3;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(exitButton,gbc);
   		
   		logoutButton = new JButton("Logout");
   		logoutButton.addActionListener(this);
   		logoutButton.setToolTipText("Click here to logout of Program & return to Main Menu");
   		logoutButton.setFont(font2);
   		gbc.gridx = 3;
   		gbc.gridy = 3;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(logoutButton,gbc);
   		
   		fineButton = new JButton("Pay Fine");
   		fineButton.addActionListener(this);
   		fineButton.setToolTipText("Click here to clear fine");
   		fineButton.setFont(font2);
   		gbc.gridx = 1;
   		gbc.gridy = 4;
   		gbc.gridwidth = 2;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(fineButton,gbc);
	
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
		 
		table = new MembersTable();
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
	 * this method handles action events
	 */
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource() == searchMembers)
		{
			
			Object[] options1 = {"Search by ID",
                    "Search by Name",
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
			
			id = JOptionPane.showInputDialog(this, "Please enter a Member ID to be searched:");
			
			if(id == null)
			{
				
				cancelled = true;
			
			}
			else if(id.isEmpty() && id != null)
			{
				
				JOptionPane.showMessageDialog(null, "Id can't be left blank","ERROR", JOptionPane.ERROR_MESSAGE);	
				success = false;
			
			}
			else if(id.length() > 40)
			{
				
				JOptionPane.showMessageDialog(null, "Id can't be more than 40 characters","ERROR", JOptionPane.ERROR_MESSAGE);	
				success = false;
			
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

				memberInstance.setId(id);
				table = new MemberById();
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
			
			while(username !=null && success == false  ) // until gets cancelled or passess successfully will loop.
			{
				
				success = true;
				
				username = JOptionPane.showInputDialog(this, "Please enter a Name to be searched:");
				
				if(username == null)
				{
					
					cancelled = true;
				
				}
				else if (username.length() > 40 )
				{
					
					JOptionPane.showMessageDialog(null, "Id can't be more than 40 characters","ERROR", JOptionPane.ERROR_MESSAGE);	
					success = false;
				
				}
				else if(username.isEmpty() && username != null)
				{
					
					JOptionPane.showMessageDialog(null, "Name can't be left blank","ERROR", JOptionPane.ERROR_MESSAGE);	
					success = false;
				
				}
				else if(username != null)	// if not cancelled
				{
					
					success = true;
				
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

					memberInstance.setMemberName(username);
					table = new MemberByName();
					southContainer.add(table,gbc);
					revalidate();
					repaint();
				
				}
				
				cancelled = false;
			
			}	
		}
	}
		
		//################################################# ADD MEMBER #############################################

		else if(e.getSource() == addMembers)
		{
			
			try {
				
					Statement stmt = DatabaseConnection.startConnection();

					int globalGaurd = 1;
					int memberGaurd = 0;

			        while(memberGaurd == 0 && globalGaurd == 1)
					{
			        	
				        memberName= JOptionPane.showInputDialog("Please enter a Member Name for the new member:");

						if(memberName == null) // Cancelled
						{
							
							memberGaurd = 1;
							globalGaurd = 0;
	
						}
						else if(memberName.length() <= 0)
						{
							
							JOptionPane.showMessageDialog(null, "Name can't be left blank","ERROR", JOptionPane.ERROR_MESSAGE);	
							memberGaurd = 0;
						
						}
						else if(memberName.length() > 40)
						{
							
							JOptionPane.showMessageDialog(null, "Name can't longer than 40 characters","ERROR", JOptionPane.ERROR_MESSAGE);	
							memberGaurd = 0;

						}
						else
						{
							
							memberGaurd = 1;
						
						}
					}
			        
				//################################################# ADD ADDRESS #############################################

			        if(globalGaurd == 1)
					{
			        	
						int addressGuard = 0;
	
				        while(addressGuard == 0 && globalGaurd == 1)
						{
				        	
					        memberAddress= JOptionPane.showInputDialog("Please enter an address for the new member:");
	
							if(memberAddress == null) // Cancelled
							{
								
								addressGuard = 1;
								globalGaurd = 0;
		
							}
							else if(memberAddress.length() <= 0)
							{
								
								JOptionPane.showMessageDialog(null, "Address can't be left blank","ERROR", JOptionPane.ERROR_MESSAGE);	
								addressGuard = 0;
							
							}
							else if(memberAddress.length() > 40)
							{
								
								JOptionPane.showMessageDialog(null, "Address can't longer than 40 characters","ERROR", JOptionPane.ERROR_MESSAGE);	
								addressGuard = 0;
	
							}
							else
							{
								
								addressGuard = 1;
							
							}
						}
					}
			       			        
				if(globalGaurd == 1)		 
				{
					
			        try
					{
			        	
			        	String sqlInsert = "insert into Members" + " (member_Name,member_Address,member_Fine, card_Id) values (" + "'"+memberName+"'" +","+ "'"+memberAddress+"'" + ", "+ 0 + ", "+ 0 +" ) ";
						stmt.executeUpdate(sqlInsert);
						
						String sqlSelect = "SELECT last_insert_id() From Members";
						ResultSet rs = stmt.executeQuery(sqlSelect);
						rs.next();
						String lastInsertedID = rs.getString(1);
						
						int newCardId = Integer.parseInt(lastInsertedID);
						
						String sqlInsert2 = "insert into Cards" + " (member_Id,member_Name,member_Address,member_Fine) values (" + newCardId +"," + "'" + memberName + "'" + "," + "'" + memberAddress + "'" + "," + 0+ ")";  
						stmt.executeUpdate(sqlInsert2);
						
						String sqlInsert3 = "update Members set card_Id= " + newCardId + " WHERE member_Id =" + newCardId + ";" ;
						stmt.executeUpdate(sqlInsert3);

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

			table = new MembersTable();
			southContainer.add(table,gbc);
			revalidate();
			repaint();
	        
		}
		
		//################################################# EDIT ID #############################################

		else if(e.getSource() == editMembers)
		{
			
			try {
				
				Statement stmt = DatabaseConnection.startConnection();

				int memberGaurd = 0;
				int globalGaurd = 1;
				
				if(globalGaurd == 1)
				
				{
					
			        while(memberGaurd == 0 && globalGaurd == 1)
					{
			        	
			        	boolean success = true;
						memberId= JOptionPane.showInputDialog(null, "Please enter the Id of the Member you'd like to change.");

						if(memberId == null) // Cancelled
						{
							
							memberGaurd = 0;
							globalGaurd = 0;
						
						}
						else if(memberId.length() <= 0)
						{
							
							JOptionPane.showMessageDialog(null, "Member Id can't be left blank","ERROR", JOptionPane.ERROR_MESSAGE);	
							memberGaurd = 0;
						
						}
						else if(memberId.length() > 40)
						{
							
							JOptionPane.showMessageDialog(null, "Member Id can't longer than 40 characters","ERROR", JOptionPane.ERROR_MESSAGE);	
							memberGaurd = 0;
						
						}
						else if(memberId != null)	// if not cancelled
						{
							
							char[] testArray = memberId.toCharArray();
							for(int i = 0 ; i < testArray.length ; i++)
							{
								
								if( testArray[i] < 48 || testArray[i] > 58 ) // is it not between 1-9
								{
									
									memberGaurd = 0;
									success = false;
								
								}	
								else
								{
									
									memberGaurd = 1;
								
								}
							}
							
							if (!success)
							{
								
								JOptionPane.showMessageDialog(null, "Id must be a number greater than 0","ERROR", JOptionPane.ERROR_MESSAGE);	
							
							}
						}
						
						if (memberGaurd == 1)
						{
							
							String strSelect = "Select * from members where member_Id = " + "'"+ memberId+ "'";
							ResultSet rset = stmt.executeQuery(strSelect);
							
							boolean doesExist = false;
							
							while(rset.next())
							{
							
								doesExist = true;
							
							}
							
							if(doesExist == false)
							{
								
								JOptionPane.showMessageDialog(null, "No member with such Id","ERROR", JOptionPane.ERROR_MESSAGE);	
								memberGaurd = 0;
								success = false;

							}
							else
							{
								
								success = true;
								memberGaurd = 1;
							
							}
						}
					}
				}
				
				
			//################################################# EDIT MEMBER #############################################
			
			if(globalGaurd == 1)
			{
				
				int nameGaurd = 0;
					
		        while(nameGaurd == 0 && globalGaurd == 1)
				{
		        	
			        memberName= JOptionPane.showInputDialog("Please enter a new name");

					if(memberName == null) // Cancelled
					{
						
						nameGaurd = 1;
						globalGaurd = 0;
	
					}
					else if(memberName.length() <= 0)
					{
						
						JOptionPane.showMessageDialog(null, "Member name can't blank","ERROR", JOptionPane.ERROR_MESSAGE);	
						nameGaurd = 0;
					
					}
					else if(memberName.length()>40)
					{
						
						JOptionPane.showMessageDialog(null, "Name cannot be longer than 40 characters","ERROR", JOptionPane.ERROR_MESSAGE);	
						nameGaurd = 0;
					
					}
					else
					{
						
						nameGaurd = 1;
					
					}
				}
			}
			
			//################################################# EDIT ADDRESS #############################################

			if(globalGaurd == 1)
			{
				int addressGaurd = 0;
				    
		        while(addressGaurd == 0 && globalGaurd == 1)
				{
		        	
			        memberAddress= JOptionPane.showInputDialog("Please enter an Address for this member");

					if(memberAddress == null) // Cancelled
					{
						
						addressGaurd = 1;
						globalGaurd = 0;
					
					}
					else if(memberAddress.length() <= 0)
					{
						
						JOptionPane.showMessageDialog(null, "Address cannot be empty","ERROR", JOptionPane.ERROR_MESSAGE);	
						addressGaurd = 0;
					
					}
					else if(memberAddress.length()>40)
					{
						
						JOptionPane.showMessageDialog(null, "Address cannot be longer than 40 characters","ERROR", JOptionPane.ERROR_MESSAGE);	
						addressGaurd = 0;
					
					}
					else
					{
						
						addressGaurd = 1;
					
					}
				}
			}
			
	        //############################################### EDIT memberFine ##############################################
			
			if(globalGaurd == 1)
			{
				
				int memberFineGaurd = 1;
				
				while(memberFineGaurd == 1)
				{
					
					char[]testArray = null;
			        memberFine= JOptionPane.showInputDialog("Please enter a new Fine for this member.");
					boolean check2 = true;
					
					if(memberFine.length() <= 0 && memberFine != null)
					{
						
						JOptionPane.showMessageDialog(null, "Fine cannot be blank");	
			        	check2  = false;

					}
					else if(memberFine != null)
			        {
						
				        testArray = memberFine.toCharArray();
			        	check2  = true;

			        }
			        else if(memberFine == null) // Cancelled
					{
			        	
						globalGaurd = 0;
						memberFineGaurd = 0;
			        	check2  = false;

					}
					
					else if(Integer.parseInt(memberFine) < 0)
					{
						
						JOptionPane.showMessageDialog(null, "Fine cannot be less than 0");	
						memberFineGaurd = 1;
			        	check2  = false;

					}
			   
			        
					while(check2)						
					{
						
						for(int i = 0; i < testArray.length; i++)
						{
							
					    	if(  testArray[i] >=  48 && testArray[i] <= 57  )
					    	{
					    		
					    		check2 = false;
					    		memberFineGaurd = 0;
					        }
					    	else
					    	{
					    		
						        memberFine= JOptionPane.showInputDialog("Please enter a new Fine for this member (CANNOT BE LESS THAN ZERO):");
					    		testArray = memberFine.toCharArray();
					    		
					    	}
						}
					}
				}
			}
			
			if(globalGaurd == 1)
			{
				
		        try
				{
		        	
		        	String strUpdate = "update Members set member_Name = " + "'"+memberName+"'" +", member_Address = "+ "'"+memberAddress+"'" +", member_Fine ="+ ""+memberFine+"  " + "WHERE member_Id = " + "'"+memberId+"'" + ";"  ;
		    		int countUpdated = stmt.executeUpdate(strUpdate);
		    		String strUpdate2 = "update Cards set member_Id = " + "" + " , member_Name = " + "'"+memberName+"'" +", member_Address = "+ "'"+memberAddress+"' "+ " WHERE card_Id = " + memberId + ";" ;
						
				} catch(SQLException e1)
				{
					
					e1.printStackTrace();
				
				}
		        finally
		        {
		        	
		        	if(stmt != null)
		        	{
		        		
			        	stmt.close();
		        	
		        	}
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

		table = new MembersTable();
		southContainer.add(table,gbc);
		revalidate();
		repaint();
        
	}
		
		//################################################# DELETE MEMBER #############################################

		else if(e.getSource() == deleteMembers)
		{		
				
			Statement stmt = null;
			try {
				
				stmt = DatabaseConnection.startConnection();
			
			} catch (ClassNotFoundException e2) 
			{
				
				e2.printStackTrace();
			
			} 
			catch (SQLException e2) 
			{
				
				e2.printStackTrace();
			
			}
			
			memberId = "";
			boolean success = false;
			boolean cancelled = false;
			
			while(memberId !=null && success == false  ) // until gets cancelled or passess successfully will loop.
			{
				
				success = true;
				memberId= JOptionPane.showInputDialog(null, "Please enter the Id of the Member you'd like to delete.");
				
				if(memberId == null)
				{
					
					cancelled = true;
				
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
					
					String sqlDelete = "delete from Members where member_Id = " + memberId;
					try {
						
						stmt.executeUpdate(sqlDelete);
					
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
			
					table = new MembersTable();
					southContainer.add(table,gbc);
					revalidate();
					repaint();
				}
				
				cancelled = false;
			
			}	
		}
		
		//################################################# SEARCH MEMBER #############################################
		else if (e.getSource() == searchButton) 	
		{
			{
				Object[] options1 = {"Search by ID",
						"Search by Name",
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
				
				String id = JOptionPane.showInputDialog(this, "Please enter a member ID to be searched:");
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

				memberInstance.setId(id);
				table = new MemberById();
				southContainer.add(table,gbc);
				revalidate();
				repaint();
				choice = 2;
			}
			
			if(choice == 1)
			{
				
				String name = JOptionPane.showInputDialog(this, "Please enter a Member Name to be searched:");
				
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

				memberInstance.setMemberName(name);
				table = new MemberByName();
				southContainer.add(table,gbc);
				revalidate();
				repaint();
				choice = 2;
				memberInstance.setMemberName(null);
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
	
			table = new MembersTable();
			southContainer.add(table,gbc);
			revalidate();
			repaint();
		
		}
		else if(e.getSource() == fineButton) 
		{
				
			try {
				
				Statement stmt = DatabaseConnection.startConnection();
				
				try {

					String memberId = "";
					boolean success = false;
					boolean cancelled = false;
					
					while(memberId !=null && success == false  ) // until gets cancelled or passess successfully will loop.
					{
						
						success = true;
						memberId = JOptionPane.showInputDialog(this, "Please enter the Member Id of who's fine data is to be retrieved");
					
						if(memberId == null)
						{
							
							cancelled = true;
						
						}
						else if(memberId.isEmpty() && memberId != null)
						{
							
							JOptionPane.showMessageDialog(null, "Id can't be left blank","ERROR", JOptionPane.ERROR_MESSAGE);	
							success = false;
						
						}
						else if( memberId.length() > 40  )
						{
							
							success = false;
							JOptionPane.showMessageDialog(null, "Id can't be more than 40 characters","ERROR", JOptionPane.ERROR_MESSAGE);	
						
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
		
						String strSelect = " SELECT * from members where member_Id = "+ "'"+ memberId + "'";
						stmt.executeQuery(strSelect);
						ResultSet rset = stmt.executeQuery(strSelect);
						int fine = 0;
						int count = 0 ;
						
						while(rset.next())
						{
							
							 fine = rset.getInt("member_Fine"); 
							 count ++;
						
						}
						
						if(fine == 0 && count == 1)
						{
							
							success = false;
							JOptionPane.showMessageDialog(null, "This user currently has no fine due.", "Message", JOptionPane.INFORMATION_MESSAGE);
							count = 0;
						
						}
						else if(fine == 0 && count == 0)
						{
							
							JOptionPane.showMessageDialog(null, "No user with id  " + memberId +"", "Message", JOptionPane.INFORMATION_MESSAGE);
							success = false;
						
						}
						
						else
						{

							String amountPaidString = "";
							int amountPaid = 0;
							boolean success2 = false;
							boolean cancelled2 = false;
							
							while(amountPaidString !=null && success2 == false  ) // until gets cancelled or passess successfully will loop.
							{
								
								success2 = true;
								amountPaidString = JOptionPane.showInputDialog(this, "This Member has an unpaid fine of: €"+ fine + ". Please enter the amount to be deducted: ");

								if(amountPaidString == null)
								{
									
									cancelled2 = true;
								
								}
								else if(amountPaidString.isEmpty() && amountPaidString != null)
								{
									
									JOptionPane.showMessageDialog(null, "Id can't be left blank","ERROR", JOptionPane.ERROR_MESSAGE);	
									success2 = false;
								
								}
								else if( amountPaidString.length() > 40  )
								{
									
									success2 = false;
									JOptionPane.showMessageDialog(null, "Id can't be more than 40 characters","ERROR", JOptionPane.ERROR_MESSAGE);	
								
								}
								else if(amountPaidString != null)	// if not cancelled
								{
									
									char[] testArray = amountPaidString.toCharArray();
									for(int i = 0 ; i < testArray.length ; i++)
									{
										
										if( testArray[i] < 48 || testArray[i] > 58 ) // is it not between 1-9
										{
											
											success2 = false;
										
										}	
									}
									
									if (!success2 )
									{
										
										JOptionPane.showMessageDialog(null, "Id must be a number greater than 0","ERROR", JOptionPane.ERROR_MESSAGE);	
									
									}
								}
								
								if(success2 && amountPaidString != null)
								{
									
									amountPaid = Integer.parseInt(amountPaidString);
								
								}

								if(amountPaid < 0 || amountPaid > fine && success2)
								{
									
									JOptionPane.showMessageDialog(null, "Error: Fine cannot be less than 0 or greater than current fine due.", "Error", JOptionPane.ERROR_MESSAGE);
									amountPaidString = JOptionPane.showInputDialog(this, "This Member has an unpaid fine of: €"+ fine + ". Please enter the amount to be deducted: ");
									amountPaid = Integer.parseInt(amountPaidString);
									success2 = false;
								
								}
	
								if(success2 && !cancelled2)
								{
									
									int newFine = fine - amountPaid;
									
									String sqlEdit = "update Members set member_Fine= " + newFine + " WHERE member_Id =" + memberId + ";" ;
									stmt.executeUpdate(sqlEdit);
									
									JOptionPane.showMessageDialog(null, "This members current fine balance is now: "+ newFine, "Fine Updated", JOptionPane.PLAIN_MESSAGE);
									
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
							
									table = new MembersTable();
									southContainer.add(table,gbc);
									revalidate();
									repaint();
								
								}
							}
						}
					}
				}
			} catch (SQLException e1)
			{
			
				e1.printStackTrace();
				
			}
			finally
			{
				
				stmt.close();
				
			}	
			} catch (ClassNotFoundException e1) 
			{
	
				e1.printStackTrace();
			
			} catch (SQLException e1) 
			{
			
				e1.printStackTrace();
			
			}
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
		else if(e.getSource() == logoutButton)
		{
			
			BasicStructure.gotoCard("1");
			oneLoginPanel.usernameField.setText("");
			oneLoginPanel.passwordField.setText("");
		
		}
		else  if(e.getSource() == menuButton)
		{
			
			BasicStructure.gotoCard("2");
		
		}
	}
}

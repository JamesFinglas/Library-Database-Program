package cards;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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

import database.ManageTransactions;
import database.DatabaseConnection;
import database.Login;
import executable.BasicStructure;
import tables.UserTableBuilder;
import tables.BooksByAuthor;
import tables.BooksById;
import tables.BooksByPublisher;
import tables.BooksByTitle;
import tables.MembersTable;
import tables.UsersById;
import tables.UsersByName;
import tables.BookTable;
import tables.BookTableBuilder;
import tables.UserTable;


/**
 * this class creates and manages the books panel
 * @author Cathal Murphy & James Finglas
 */
public class fourBooksPanel extends JPanel implements ActionListener
{
	
	static JLabel error , searchResults;
	JButton searchBooks, addBooks, editBooks, deleteBooks, searchButton, logoutButton, exitButton, menuButton, refreshButton, loanButton;
	String bookId, title, author, publisher, onLoan;
	JPanel southContainer ;
	JPanel table;
	String memberId, userId, memberName, memberAddress, card_Id, fine, dueDate ;
	int choice ;
	public static BookTableBuilder bookInstance = new BookTableBuilder();
	
	GridBagConstraints gbc = new GridBagConstraints();
	GridBagConstraints tablegbc = new GridBagConstraints();
	
	/**
	 * This method is the constructor for the books panel
	 */
	public fourBooksPanel() 
	{
		
		setLayout(new GridLayout(2,1));
		Border raisedBorder1 = BorderFactory.createLineBorder(new Color(211,211,211),3);
   		this.setBorder(raisedBorder1);
		
		Font font1 = new Font("Courier", Font.BOLD,20);
		Font font2 = new Font("Courier", Font.BOLD,17);
		Font font3 = new Font("Courier", Font.BOLD,32);
			
		JPanel northContainer = new JPanel(new GridBagLayout());
		gbc.insets = new Insets(20,0,0,0);
   		JLabel welcome = new JLabel("Manage Books");
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
   		
   		searchBooks = new JButton("Search Books");
   		searchBooks.setToolTipText("Click here to search for a book");
   		searchBooks.setFont(font2);
   		searchBooks.addActionListener(this);
   		searchBooks.setToolTipText("Click here to search users");
   		gbc.gridx = 0;
   		gbc.gridy = 2;
   		gbc.gridwidth = 1;
   		gbc.insets = new Insets(5,20,5,-5);
   		northContainer.add(searchBooks,gbc);
   		
   		addBooks = new JButton("Add Book");
   		addBooks.setToolTipText("Click here to add a book");
   		addBooks.addActionListener(this);
   		addBooks.setToolTipText("Click here to add a user");
   		addBooks.setFont(font2);
   		addBooks.setToolTipText("Click here to add users to the database");
   		gbc.gridx = 1;
   		gbc.gridy = 2;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(addBooks,gbc);
   		
   		editBooks = new JButton(" Edit Book ");
   		editBooks.setToolTipText("Click here to edit a book");
   		editBooks.addActionListener(this);
   		editBooks.setToolTipText("Click here to edit a user");
   		editBooks.setFont(font2);
   		gbc.gridx = 2;
   		gbc.gridy = 2;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(editBooks,gbc);
   		
   		deleteBooks = new JButton("Delete Book");
   		deleteBooks.setToolTipText("Click here to delete a book");
   		deleteBooks.addActionListener(this);
   		deleteBooks.setToolTipText("Click here to delete user");
   		deleteBooks.setFont(font2);
   		gbc.gridx = 3;
   		gbc.gridy = 2;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(deleteBooks,gbc);
   		
   		refreshButton = new JButton("Refresh Table");
   		refreshButton.setToolTipText("Click here to refresh the table");
   		refreshButton.addActionListener(this);
   		refreshButton.setToolTipText("Click here to refresh users table");
   		refreshButton.setFont(font2);
   		gbc.gridx = 0;
   		gbc.gridy = 3;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(refreshButton,gbc);
   		
   		menuButton = new JButton("Main Menu");
   		menuButton.setToolTipText("Click here to view the main enu");
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
   		logoutButton.setToolTipText("Click here to lockout of the program");
   		logoutButton.addActionListener(this);
   		logoutButton.setToolTipText("Click here to logout of Program & return to Main Menu");
   		logoutButton.setFont(font2);
   		gbc.gridx = 3;
   		gbc.gridy = 3;
   		gbc.gridwidth = 1;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(logoutButton,gbc);
   		
   		loanButton = new JButton("Loans and Returns");
   		loanButton.setToolTipText("Click here to issue or return a book");
   		loanButton.addActionListener(this);
   		loanButton.setToolTipText("Click here to make a loan or return");
   		loanButton.setFont(font2);
   		gbc.gridx = 1;
   		gbc.gridy = 4;
   		gbc.gridwidth = 2;
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		northContainer.add(loanButton,gbc);
	
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
		 
		table = new BookTable();
		southContainer.add(table,gbc);
	   	
   		northContainer.setBackground(new Color(6,152,114));
   		this.add(northContainer);
	  	this.add(southContainer);
	  	this.setBackground(new Color(6,152,114));
   		
	}

	//################################################# SEARCH BOOK #############################################
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == searchBooks)
		{
			
			Object[] options1 = {"Search by ID",
                    "Search by Title","Search by Author","Search by Publisher",
                    "Quit"};
			
			int choice =	JOptionPane.showOptionDialog(null,
                "How would you like to search:",
                "Search Options",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options1,
                null);
		
		if(choice == 0)// id
		{
			
			String id = "";
			boolean success = false;
			boolean cancelled = false;
			
			while(id !=null && success == false  ) // until gets cancelled or passess successfully will loop.
			{
				
				success = true;
				
				id = JOptionPane.showInputDialog(this, "Please enter a Book ID to be searched:");
				
				if(id == null)
				{
					
					cancelled = true;
				
				}
				else if(id.isEmpty() && id != null)
				{
					
					JOptionPane.showMessageDialog(null, "ERROR: Id can't be left blank. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);	
					success = false;
				
				}
				else if(id.length() > 40)
				{
					
					success = false;
					JOptionPane.showMessageDialog(null, "ERROR: Id can't be more than 40 characters. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);	
					
				}
				else if(id != null)	
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

					bookInstance.setId(id);
					table = new BooksById();
					southContainer.add(table,gbc);
					revalidate();
					repaint();
				
				}
				
				cancelled = false;
			
			}
		}
			
			if(choice == 1)	// Title	
			{
				
				String title = "";
				boolean successTitle = false;
				boolean cancelledTitle = false;
				
				while(title !=null && successTitle == false  ) // until gets cancelled or passess successfully will loop.
				{
					
					successTitle = true;
					
					title = JOptionPane.showInputDialog(this, "Please enter a Title to be searched:");
					
					if(title == null)
					{
						
						cancelledTitle = true;
					
					}
					else if(title.isEmpty() && title != null)
					{
						
						JOptionPane.showMessageDialog(null, "ERROR: Title can't be left blank. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);	
						successTitle = false;
					
					}
					else if(title.length() > 40)
					{
						
						successTitle = false;
						JOptionPane.showMessageDialog(null, "ERROR: Title can't be more than 40 characters. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);	
						
					}
					
					if(successTitle && !cancelledTitle)
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

						bookInstance.setTitle(title);
						table = new BooksByTitle();
						southContainer.add(table,gbc);
						revalidate();
						repaint();
					
					}
					
					cancelledTitle = false;
				}
			}
			
			if(choice == 2)	// AUTHOR	
			{
				
				String author = "";
				boolean successAuthor = false;
				boolean cancelledAuthor = false;
				
				while(author !=null && successAuthor == false  ) // until gets cancelled or passess successfully will loop.
				{
					
					successAuthor = true;
					
					author = JOptionPane.showInputDialog(this, "Please enter an Author to be searched:");
					
					if(author == null)
					{
						
						cancelledAuthor = true;
					
					}
					else if(author.isEmpty() && author != null)
					{
						
						JOptionPane.showMessageDialog(null, "ERROR: Author can't be left blank. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);	
						successAuthor = false;
					
					}
					else if(author.length() > 40)
					{
						
						successAuthor = false;
						JOptionPane.showMessageDialog(null, "ERROR: Author can't be more than 40 characters. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);	
						
					}
					
					if(successAuthor && !cancelledAuthor)
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

						bookInstance.setAuthor(author);
						table = new BooksByAuthor();
						southContainer.add(table,gbc);
						revalidate();
						repaint();
					}
					
					cancelledAuthor = false;
				
				}
			}
				
				if(choice == 3)	// Publisher	
				{
					
					String publisher = "";
					boolean successPublisher = false;
					boolean cancelledPublisher = false;
					
					while(publisher !=null && successPublisher == false  ) // until gets cancelled or passess successfully will loop.
					{
						
						successPublisher = true;
						
						publisher = JOptionPane.showInputDialog(this, "Please enter a Publisher to be searched:");
						
						if(publisher == null)
						{
							
							cancelledPublisher = true;
						
						}
						else if(publisher.isEmpty() && publisher != null)
						{
							
							JOptionPane.showMessageDialog(null, "ERROR: Publisher can't be left blank. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);	
							successPublisher = false;
				
						}
						else if(publisher.length() > 40)
						{
							
							successPublisher = false;
							JOptionPane.showMessageDialog(null, "ERROR: Publisher can't be more than 40 characters. Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);	
							
						}
						

						if(successPublisher && !cancelledPublisher)
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

							bookInstance.setPublisher(publisher);
							table = new BooksByPublisher();
							southContainer.add(table,gbc);
							revalidate();
							repaint();
						}
						
						cancelledPublisher = false;
					
					}
			
					choice = 5;
		
				}
		}
		
		if(choice == 2)
		{
			
			author = "";
			boolean success = false;
			boolean cancelled = false;
			
			while(author !=null && success == false  )// until gets cancelled or passes successfully will loop.
			{
				
				success = true;
				
				author = JOptionPane.showInputDialog(this, "Please enter an author to be searched:");
				
				if(author == null)
				{
					
					cancelled = true;
				
				}
				else if(author.isEmpty() && author != null)
				{
					
					JOptionPane.showMessageDialog(null, "Author can't be left blank","ERROR", JOptionPane.ERROR_MESSAGE);	
					success = false;
				
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

				bookInstance.setAuthor(author);
				table = new BooksByAuthor();
				southContainer.add(table,gbc);
				revalidate();
				repaint();
				choice = 5;
			
			}
				
			cancelled = false;
		
		}
	
		if(choice == 3)
		{
					
			publisher = "";
			boolean success = false;
			boolean cancelled = false;
			
			while(publisher !=null && success == false  )// until gets cancelled or passes successfully will loop.
			{
				
				success = true;
				
				publisher = JOptionPane.showInputDialog(this, "Please enter a publisher to be searched:");
				
				if(publisher == null)
				{
					
					cancelled = true;
				
				}
				else if(publisher.isEmpty() && publisher != null)
				{
					
					JOptionPane.showMessageDialog(null, "Publisher can't be left blank","ERROR", JOptionPane.ERROR_MESSAGE);	
					success = false;
				
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

				bookInstance.setPublisher(publisher);
				table = new BooksByPublisher();
				southContainer.add(table,gbc);
				revalidate();
				repaint();
				choice = 5;
			
			}
				
			cancelled = false;
			
		}

		//################################################# ADD BOOK #############################################
		
		else if(e.getSource() == addBooks)
		{
			
			try {
				
				Statement stmt = DatabaseConnection.startConnection();
				int globalGaurd = 1;
				
				if(globalGaurd == 1)
				{
					
					int bookGaurd = 1;
					title = JOptionPane.showInputDialog("Please enter a title of the new book:");
			        
			        if(title == null) // Cancelled
					{
			        	
						globalGaurd = 0;
					
					}
					else if(title.length() <= 0)
					{
						
						bookGaurd = 0;
					
					}

		        while(bookGaurd == 0 && globalGaurd == 1)
				{
		        	
		        	title= JOptionPane.showInputDialog(null, "ERROR: title cannot be blank please try again.");
					if(title == null) // Cancelled
					{
						
						bookGaurd = 1;
						globalGaurd = 0;

					}
					else if(title.length() <= 0)
					{
						
						bookGaurd = 0;
					
					}
					else
					{
						
						bookGaurd = 1;
					
					}
				}
			}
				
				//################################################# ADD AUTHOR #############################################

				if(globalGaurd == 1)
				{
					
					int authorGaurd = 1;
					
			        author= JOptionPane.showInputDialog("Please enter an author for the new book");
			        
			        if(author == null) // Cancelled
					{
			        	
			        	authorGaurd = 0;
						globalGaurd = 0;
					
					}
					else if(author.length() <= 0)
					{
						
						authorGaurd = 0;
					
					}
			        
			        while(authorGaurd == 0 && globalGaurd == 1)
					{
			        	
			        	author= JOptionPane.showInputDialog(null, "ERROR: author cannot be blank please try again.");
						if(author == null) // Cancelled
						{
							
							authorGaurd = 1;
							globalGaurd = 0;
						
						}
						else if(author.length() <= 0)
						{
							
							authorGaurd = 0;
						
						}
						else
						{
							
							authorGaurd = 1;
						
						}
					}
				}
		        
				//############################################### ADD PUBLISHER ##############################################
				
				if(globalGaurd == 1)
				{
					
					int publisherGaurd = 1;
					
			        publisher= JOptionPane.showInputDialog("Please enter a publisher for this book:");
			        
			        if(publisher == null) // Cancelled
					{
			        	
			        	publisherGaurd = 0;
						globalGaurd = 0;
					
					}
					else if(publisher.length() <= 0)
					{
						
						publisherGaurd = 0;
					
					}
					
			        while(publisherGaurd == 0 && globalGaurd == 1)
					{
			        	
			        	publisher= JOptionPane.showInputDialog(null, "Publisher cannot be blank(N/A if unavailable)");
						if(publisher == null) // Cancelled
						{
							
							publisherGaurd= 1;
							globalGaurd = 0;
						
						}
						else if(publisher.length() <= 0)
						{
							
							publisherGaurd = 0;
						
						}
						else
						{
							
							publisherGaurd = 1;
						
						}
					}
				}
				
				if(globalGaurd == 1)
				{
					
			        try
					{
			        	
						String sqlInsert = "insert into Books" + " (title,author,publisher,onLoan) values (" + "'"+title+"'" +","+ "'"+author+"'" +","+ "'"+publisher+"'" + "," +"'false'"+ ")";
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

			table = new BookTable();
			southContainer.add(table,gbc);
			revalidate();
			repaint();
			
		}
		else if(e.getSource() == editBooks)
		{
				
			try {
				
				Statement stmt = DatabaseConnection.startConnection();

				int bookGaurd = 0;
				int globalGaurd = 1;
				
				if(globalGaurd == 1)
				{
					
			        while(bookGaurd == 0 && globalGaurd == 1)
					{
			        	
			        	boolean success = true;
						bookId= JOptionPane.showInputDialog(null, "Please enter the Id of the Book you'd like to edit.");

						if(bookId == null) // Cancelled
						{
							
							bookGaurd = 0;
							globalGaurd = 0;
						
						}
						else if(bookId.length() <= 0)
						{
							
							JOptionPane.showMessageDialog(null, "Book Id can't be left blank","ERROR", JOptionPane.ERROR_MESSAGE);	
							bookGaurd = 0;
						
						}
						else if(bookId.length() > 40)
						{
							
							JOptionPane.showMessageDialog(null, "Book Id can't be longer than 40 characters","ERROR", JOptionPane.ERROR_MESSAGE);	
							bookGaurd = 0;
						
						}
						else if(bookId != null)	// if not cancelled
						{
							
							char[] testArray = bookId.toCharArray();
							for(int i = 0 ; i < testArray.length ; i++)
							{
								
								if( testArray[i] < 48 || testArray[i] > 58 ) // is it not between 1-9
								{
									
									bookGaurd = 0;
									success = false;
								
								}	
								else
								{
									
									bookGaurd = 1;
								
								}
							}
							
							if (!success)
							{
								
								JOptionPane.showMessageDialog(null, "Id must be a number greater than 0","ERROR", JOptionPane.ERROR_MESSAGE);	
							
							}
						}
						
						if (bookGaurd == 1)
						{
							
							String strSelect = "Select * from books where book_Id = " + "'"+bookId+ "'";
							ResultSet rset = stmt.executeQuery(strSelect);
							
							boolean doesExist = false;
							
							while(rset.next())
							{
								
								doesExist = true;
							}
							
							if(doesExist == false)
							{
								
								JOptionPane.showMessageDialog(null, "No member with such Id","ERROR", JOptionPane.ERROR_MESSAGE);	
								bookGaurd = 0;
								success = false;

							}
							else
							{
								
								success = true;
								bookGaurd = 1;
							
							}		
						}
					}
				}
			
			//################################################# EDIT TITLE #############################################
			
			if(globalGaurd == 1)
			{
					
				int nameGaurd = 0;
					
		        while(nameGaurd == 0 && globalGaurd == 1)
				{
		        	
			        title= JOptionPane.showInputDialog("Please enter a title name");

					if(title == null) // Cancelled
					{
						
						nameGaurd = 1;
						globalGaurd = 0;
	
					}
					else if(title.length() <= 0)
					{
						
						JOptionPane.showMessageDialog(null, "title can't blank","ERROR", JOptionPane.ERROR_MESSAGE);	
						nameGaurd = 0;
					
					}
					else if(title.length()>40)
					{
						
						JOptionPane.showMessageDialog(null, "title cannot be longer than 40 characters","ERROR", JOptionPane.ERROR_MESSAGE);	
						nameGaurd = 0;
					
					}
					else
					{
						
						nameGaurd = 1;
					
					}
				}	
			}
			
			//################################################# EDIT AUTHOR #############################################

			if(globalGaurd == 1)
			{
				int authorGaurd = 0;
					
		        while(authorGaurd == 0 && globalGaurd == 1)
				{
		        	
			         author= JOptionPane.showInputDialog("Please enter an author");

					if(author == null) // Cancelled
					{
						
						authorGaurd = 1;
						globalGaurd = 0;
					
					}
					else if(author.length() <= 0)
					{
						
						JOptionPane.showMessageDialog(null, "author can't blank","ERROR", JOptionPane.ERROR_MESSAGE);	
						authorGaurd = 0;
					
					}
					else if(author.length()>40)
					{
						
						JOptionPane.showMessageDialog(null, "author cannot be longer than 40 characters","ERROR", JOptionPane.ERROR_MESSAGE);	
						authorGaurd = 0;
					
					}
					else
					{
						
						authorGaurd = 1;
					
					}
				}
			}
			
	        //############################################### EDIT PUBLISHER ##############################################
			
			if(globalGaurd == 1)
			{
				
				int publisherGaurd = 0;
					   
		        while(publisherGaurd == 0 && globalGaurd == 1)
				{
		        	
			         publisher= JOptionPane.showInputDialog("Please enter a publisher");

					if(publisher == null) // Cancelled
					{
						
						publisherGaurd = 1;
						globalGaurd = 0;
					
					}
					else if(publisher.length() <= 0)
					{
						
						JOptionPane.showMessageDialog(null, "publisher can't blank","ERROR", JOptionPane.ERROR_MESSAGE);	
						publisherGaurd = 0;
					
					}
					else if(publisher.length()>40)
					{
						
						JOptionPane.showMessageDialog(null, "publisher cannot be longer than 40 characters","ERROR", JOptionPane.ERROR_MESSAGE);	
						publisherGaurd = 0;
					
					}
					else
					{
						
						publisherGaurd = 1;
					}
				}
		     }
			
			if(globalGaurd == 1)
			{
				
		        try
				{
		        	
		        	String strUpdate = "update Books set title = " + "'"+title+"'" +", author = "+ "'"+author+"'" +", publisher = "+"'"+publisher+"'" + "WHERE book_Id = " + ""+bookId+"" + ";"  ;
		    		int countUpdated = stmt.executeUpdate(strUpdate);

				} catch(SQLException e1)
				{
					
					e1.printStackTrace();
				
				}
			}
		} catch (ClassNotFoundException e1) 
		{
			
			e1.printStackTrace();
		
		} catch (SQLException e1) 
		{
			
			e1.printStackTrace();
		
		} finally 
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

			table = new BookTable();
			southContainer.add(table,gbc);
			revalidate();
			repaint();
		}		        
	}
		
		else if(e.getSource() == deleteBooks)
		{			

			try {
				
				int globalGaurd = 1;
				Statement stmt = null;
				
				try {
					
					stmt = DatabaseConnection.startConnection();
				
				} catch (ClassNotFoundException e2) 
				{
					
					e2.printStackTrace();
				
				} catch (SQLException e2) 
				{
					
					e2.printStackTrace();
				
				}

				bookId = "";
				boolean success = false;
				boolean cancelled = false;
				
				while(bookId !=null && success == false  ) // until gets cancelled or passess successfully will loop.
				{
					
					success = true;
					bookId= JOptionPane.showInputDialog(null, "Please enter the Id of the Book you'd like to delete.");
										
					if(bookId == null)
					{
						
						cancelled = true;
						globalGaurd = 0;
					
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
						
						cancelled = false;
						String strSelect = "SELECT * from Books where book_Id = " + bookId;
						stmt.executeQuery(strSelect);
						boolean bookOnLoan = false;
						ResultSet rset = stmt.executeQuery(strSelect);
			
						String onLoan = null;
			
						while(rset.next())
						{
							onLoan = rset.getString("onLoan");
						}
			
						if(onLoan.equals("true"))
						{
				
							bookOnLoan = true;
							String strSelect2 = "SELECT * from Transactions where book_Id = " + bookId;
							stmt.executeQuery(strSelect2);
							ResultSet rset2 = stmt.executeQuery(strSelect2);
				
							int latestTransaction = 1;
					
							while(rset2.next())
							{
								
								int transactionOne = rset2.getInt("transaction_Id");
					
								if(transactionOne > latestTransaction)
								{
						
									latestTransaction = transactionOne;
						
								}
							}
				
							String strSelect20 = "SELECT * from transactions where transaction_Id = " + latestTransaction;
							stmt.executeQuery(strSelect20);
							ResultSet rset20 = stmt.executeQuery(strSelect20);
				
				while (rset20.next())
				{
					
					dueDate = rset20.getString("due_Date");
					
				}

				int fine2 = 0;
				
				try {
					
					SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
					Date date = new Date();
					long fineAmount = 1;
				    java.util.Date date2 =  myFormat.parse(dueDate);
				    java.util.Date date1 =  myFormat.parse(myFormat.format(date));
				    long diff = date2.getTime() - date1.getTime();
				    long dateDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				    
				    if(dateDiff < 0)
				    {
				    	
				    	long fine = dateDiff * -fineAmount;
				    	fine2 = (int) fine;

				    }
				    
				} catch (ParseException ee) 
				{
					
				    ee.printStackTrace();
				
				}
				
				if (JOptionPane.showConfirmDialog(null, "This Book is currently out on loan. It's Transaction ID is " + latestTransaction + " and its overdue fee is €" + fine2 +". Are you sure you want to delete it?", "WARNING",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				{
					
					try
					{
			        	
			        	String sqlDelete = "delete from Books where book_Id = " + bookId;
						stmt.executeUpdate(sqlDelete);
	
					} catch(SQLException e1)
					{
						
						e1.printStackTrace();
					
					} finally
			        {
						
			        	stmt.close();
			        
			        }
				}
				else 
				{
					
					globalGaurd = 0 ;
				
				}
			}
			
			if(globalGaurd == 1 && bookOnLoan == false)
			{
				
		        try
				{
		        	
		        	String sqlDelete = "delete from books where book_Id = " + bookId;
					stmt.executeUpdate(sqlDelete);
	
				} catch(SQLException e1)
				{
					
					e1.printStackTrace();
				
				} finally
		        {
					
		        	stmt.close();
		        
		        }
			}
		}
	}
			}catch (SQLException e1) 
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

			table = new BookTable();
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

			table = new BookTable();
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
		
        //############################################### Loans and Returns ##############################################

		else if(e.getSource() == loanButton)
		{
			
			Object[] options1 = {"Loan a Book",
                    "Return a Book",
                    "Quit"};
			
			int choice =	JOptionPane.showOptionDialog(null,
                "Would you like to put a book on loan or return a book?",
                "Search Options",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options1,
                null);
		
			if(choice == 0)
			{
			
				int gaurd = 0;
				while(gaurd == 0)
			
			{
			
				Statement stmt;
				stmt = null;
				
				try {
					
					stmt = DatabaseConnection.startConnection();
				
				} catch (ClassNotFoundException e2) 
				{
					
					e2.printStackTrace();
				
				} catch (SQLException e2) 
				{
				
					e2.printStackTrace();
				
				}

				 memberId = JOptionPane.showInputDialog(this, "Please enter a Member Id:"); // member_Id 
				
				 if(memberId != null)
				 {
				
					 try {
	
						 String strSelect = "select * from Members WHERE member_Id =" + "" + memberId + "" ;
						 ResultSet rset = stmt.executeQuery(strSelect);
						 int G1 = 0;

						 while(rset.next())
						 {
						
							 G1 = 1;
							 memberName = rset.getString("member_Name");
							 memberAddress = rset.getString("member_Address");
							 card_Id = rset.getString("card_Id");
							 fine = rset.getString("member_Fine");
						
							 if(!fine.equals("0")) // else
							 {
							
								 JOptionPane.showMessageDialog(null, "This member has an unpaid fine of €"+ fine + ". Book cannot be issued until fine is paid. ");	
							
								 memberId = null;
								 gaurd = 1;
						
							 }
					}
					
					gaurd = 1;
					
					if(G1 == 0)
					{
						
						boolean notNumber = false;
						
						char[] testArray = memberId.toCharArray();
						for(int i = 0 ; i < testArray.length ; i++)
						{
							
							if( testArray[i] < 48 || testArray[i] > 58 ) // is it not between 1-9
							{
									 
								notNumber = true;
								JOptionPane.showMessageDialog(null, "Id must be a number greater than 0","ERROR", JOptionPane.ERROR_MESSAGE);	

								}	
							}
							
							if (!notNumber)
							{
								
								JOptionPane.showMessageDialog(null, "No member with this Id found, please try again.","ERROR", JOptionPane.ERROR_MESSAGE);
							
							}
				
							memberId = null;
							gaurd = 0;
						}
					} catch(SQLException e1)
					{
						
						e1.printStackTrace();
					
					} finally
					{
						
			        	try {
							
			        		stmt.close();
						
			        	} catch (SQLException e1) 
			        	{
			        		
							e1.printStackTrace();
						
			        	}
					}
			}
			else
			{
				gaurd = 1;
				
			}
		}
		
			if(memberId != null)
			{
				gaurd = 0; 
					
				while(gaurd == 0)
				{
					
					 userId = JOptionPane.showInputDialog(this, "Please enter your User Id:"); // user_Id
					
					 if(userId != null)
					 {
						
						try 
						{
							
							Statement stmt = DatabaseConnection.startConnection();
							String strSelect = "select * from Users WHERE user_Id =" + "'" + userId + "'" ;
							ResultSet rset = stmt.executeQuery(strSelect);
							
							int G1 = 0;
							
							while(rset.next())
							{
								
								G1 = 1;
								gaurd = 1;

							}
							if(G1 == 0)
							{
								
								userId = null;
								JOptionPane.showMessageDialog(null, "No user with this Id found, please try again.");	
								
							}
						} catch(SQLException e1)
						{
							
							userId = null;
		
						} catch (ClassNotFoundException e1) 
						{
							
							userId = null;
						
						}
					}
					else
					{
						
						gaurd = 1;
						
					}
				}
			}
			
			if(memberId !=null && userId !=null )
			{
				
				gaurd = 0; 
				
				while(gaurd == 0)
				{
					
					bookId = JOptionPane.showInputDialog(this, "Please enter a book Id to be loaned to " + memberId + " :"); // book_Id
					Statement stmt = null;
					
					try {
						
							stmt = DatabaseConnection.startConnection();
					
					} catch (ClassNotFoundException e2) 
					{
						
						e2.printStackTrace();
					
					} catch (SQLException e2) 
					{
					
						e2.printStackTrace();
					
					}

				if(bookId != null)
				{
					
					try 
					{
						
						String strSelect = "select * from Books WHERE book_Id =" + "'" + bookId + "'" ;
						ResultSet rset = stmt.executeQuery(strSelect);
						String loanStatus = "false";
						boolean bookExistsGaurd = false;
						boolean bookOnLoan = false;
						
						while(rset.next())
						{
							
							loanStatus = rset.getString("onLoan");
							bookExistsGaurd = true;
							gaurd = 1;
						
						}
						if(bookExistsGaurd == false)
						{
							
							loanStatus = "false";
							JOptionPane.showMessageDialog(null, "No book with that ID");	
							bookId = null;
							gaurd = 0;
						
						}
					
						if(loanStatus.equals("true"))
						{
							
							JOptionPane.showMessageDialog(null, "This book is already on loan");	
							bookId = null;
							gaurd = 0;
						
						}
					} catch(SQLException e1)
					{
						
						bookId = null;
	
					} finally
					{
						try{
							
							stmt.close();
						
						} catch (SQLException e1) 
						{
							
							e1.printStackTrace();
						
						}
					}
				}
				else
				{
					
					gaurd = 1;
				
				}
			}
		}
			
			if(memberId !=null && userId !=null && bookId !=null )
			{
				
				int transactionGaurd = 0;
				
				try {
					
					 transactionGaurd = insertTransaction(bookId, memberId,userId);
					
				} catch (ClassNotFoundException | SQLException e1) 
				{
					
					e1.printStackTrace();
				
				}
				
				if(transactionGaurd == 1)
				{
					
					Statement stmt = null;
					
					try {
						
						stmt = DatabaseConnection.startConnection();
						
						String strUpdate = "update Books set onLoan = 'true' WHERE book_Id= " + bookId;
			    		int countUpdated = stmt.executeUpdate(strUpdate);
			    		
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
	
						table = new BookTable();
						southContainer.add(table,gbc);
						revalidate();
						repaint();
	
					} catch (ClassNotFoundException | SQLException e1) 
					{
						
						e1.printStackTrace();
					
					} finally
					{
						
						try {
							
							stmt.close();
						
						} catch (SQLException e1) 
						{
							
							e1.printStackTrace();
						
						}
					}
				}
			}		
		}
		
		if(choice == 1) // User wants to return a book
		{
			
			try 
			{
				
				String bookId = "";
				boolean success = false;
				boolean cancelled = false;
				
				while(bookId !=null && success == false  ) // until gets cancelled or passess successfully will loop.
				{
					
					success = true;
					bookId = JOptionPane.showInputDialog(this, "Please enter a book Id to be returned:");
				
					if(bookId == null)
					{
						
						cancelled = true;
					
					}
					else if(bookId.isEmpty() && bookId != null)
					{
						
						JOptionPane.showMessageDialog(null, "Id can't be left blank","ERROR", JOptionPane.ERROR_MESSAGE);	
						success = false;
					
					}
					else if( bookId.length() > 40  )
					{
						
						success = false;
						JOptionPane.showMessageDialog(null, "Id can't be more than 40 characters","ERROR", JOptionPane.ERROR_MESSAGE);	
					
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
	
						boolean doesBookExist,bookOnLoan;
						doesBookExist = false;
						bookOnLoan = true;
		
						Statement stmt = DatabaseConnection.startConnection();
						String strSelect = "select * from Books WHERE book_Id =" + "" + bookId + "" ;
						ResultSet rset;
						rset = stmt.executeQuery(strSelect);
							
						while(rset.next()) 
						{
							
							String loanStatus = rset.getString("onLoan");
							
							if(loanStatus.equals("false"))
							{
								
								bookOnLoan = false;
								success = false;
							
							}
							
							doesBookExist = true;
	
						}
						
						if(bookOnLoan == false)
						{
							
							JOptionPane.showMessageDialog(null, "ERROR: This book is not on loan","ERROR",JOptionPane.ERROR_MESSAGE);
						
						}
						else if(doesBookExist && bookOnLoan && success )
						{
							
							String strUpdate = "update Books set onLoan = 'false' where book_Id ="+ bookId+ ";";
							stmt.executeUpdate(strUpdate);

							String strSelect2 = "SELECT * from Transactions WHERE book_Id = " + bookId;							
							ResultSet rset2 = stmt.executeQuery(strSelect2);
							int latestTransaction = 1;
							
							while(rset2.next())
							{
								
								int transactionOne = rset2.getInt("transaction_Id");
								
								if(transactionOne > latestTransaction)
								{
									
									latestTransaction = transactionOne;
									
								}
							}
							
							String strSelect3 = "SELECT * from Transactions WHERE transaction_Id = " + latestTransaction;
							SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
							ResultSet rset3 = stmt.executeQuery(strSelect3);
							Date dateGaurd = new Date();
							String dueDate = myFormat.format(dateGaurd);
							
							while(rset3.next()) 
							{
								
								dueDate = rset3.getString("due_Date");
								
							}

							try {
								
								Date date = new Date();
								long fineAmount = 1;
							    java.util.Date date2 =  myFormat.parse(dueDate);
							    java.util.Date date1 =  myFormat.parse(myFormat.format(date));
							    long diff = date2.getTime() - date1.getTime();
							    long dateDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
							    
							    String returnDate = date1.toString();
							    
							    if(dateDiff < 0)
							    {
							    	
							    	long fine = dateDiff * -fineAmount;
							    	int fine2 = (int) fine;
							    	
							    	String strUpdate6 = "update Transactions set fees = "+ fine2 + " WHERE transaction_Id = "+ latestTransaction ;
							    	String strUpdate7 = "update Transactions set return_Date = "+ "'" + returnDate + "'" + " WHERE transaction_Id = "+ latestTransaction ;
							    	
							    	stmt.executeUpdate(strUpdate6);
							    	stmt.executeUpdate(strUpdate7);
		
									String strSelect4 = "select * from Members where member_Id=" + "'"+memberId+"'";
									ResultSet rset4 = stmt.executeQuery(strSelect4);
									int newFine = 0;
									int fine3 = 0;
		
									while(rset4.next())
									{
										
										fine3 = rset4.getInt("member_Fine");
										newFine = fine3 + fine2;
									
									}
		
									String strUpdate5 = "update Members set member_Fine = "+ newFine + " WHERE member_Id = " + memberId + ";";
									stmt.executeUpdate(strUpdate5);
		
							    }
							} catch (ParseException ee) 
							{
								
							    ee.printStackTrace();
							
							} finally
							{
								
								stmt.close();
							
							}
						}
						else if(doesBookExist == false)
						{
							
							success = false;
							JOptionPane.showMessageDialog(null, "No book with this Id","Message",JOptionPane.INFORMATION_MESSAGE);
		
						}
					}
					
					cancelled = false;
				
				}
			} catch (SQLException | ClassNotFoundException e1) 
			{
				
				e1.printStackTrace();
			
			} finally
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

				table = new BookTable();
				southContainer.add(table,gbc);
				revalidate();
				repaint();
				choice = 5;
			
				}
			}
		}
	}
	
	/**
	 * This method inserts transactions into the database using given data 
	 * @param book_Id
	 * @param member_Id
	 * @param user_Id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int insertTransaction(String book_Id, String member_Id, String user_Id) throws ClassNotFoundException, SQLException
	{
		
		Statement stmt = DatabaseConnection.startConnection();
		String due_Date = "";
		String borrow_Date ="";
		int fees = 0;
		int gaurd = 0;
		
		borrow_Date = JOptionPane.showInputDialog(this, "Please enter the current Date in this format (dd mm yyyy)"); 
		
		while(borrow_Date != null && borrow_Date.length() < 1)
		{
			
			borrow_Date = JOptionPane.showInputDialog(this, "Date cannot be empty. Please enter the current Date in this format (dd mm yyyy)"); 
		
		}
		
		if(borrow_Date !=null)
		{
		
			gaurd = 1;
			char[] testArray2 =borrow_Date.toCharArray();
			testArray2 = arrayCheck(testArray2);
			
			boolean check3 = true;
			while(check3)
			{
				
				for(int i = 0; i < testArray2.length; i++)
				{
					
			    	if(testArray2[i] >=  48 && testArray2[i] <= 57 || testArray2[i] ==  32)
			    	{	 
			    		check3 = false;
			    		
			    	}
				}
				
				if(check3)
				{
					
					JOptionPane.showMessageDialog(null, "Incorrect Date Format","ERROR",JOptionPane.ERROR_MESSAGE);	
					
					borrow_Date = JOptionPane.showInputDialog(this, "Please enter the current Date in this format (dd mm yyyy)"); 
		    		testArray2 = borrow_Date.toCharArray();
		    		testArray2 = arrayCheck(testArray2);
				
				}
			}
		}
			
		if(borrow_Date !=null)
		{
			
				due_Date = JOptionPane.showInputDialog(this, "Please enter the due Date in this format (dd mm yyyy)"); 
				
				while(due_Date.length() <1)
				{
					
					due_Date = JOptionPane.showInputDialog(this, "Date cannot be empty. Please enter the due Date in this format (dd mm yyyy)"); 
	
				}
				
				char[] testArray = due_Date.toCharArray();
				testArray = arrayCheck(testArray);
				boolean check2 = true;
				
				while(check2)
				{
					
					for(int i = 0; i < testArray.length; i++)
					{
						
				    	if(testArray[i] >=  48 && testArray[i] <= 57 || testArray[i] ==  32)
				    	{
				    		
				    		check2 = false;
				        
				    	}
					}
					
					if(check2)
					{
						
						JOptionPane.showMessageDialog(null, "Incorrect Date Format","ERROR",JOptionPane.ERROR_MESSAGE);	
						due_Date = JOptionPane.showInputDialog(this, "Please enter the due Date in this format (dd mm yyyy)"); 
			    		testArray = due_Date.toCharArray();
			    		testArray = arrayCheck(testArray);
					
					}
				}
			}
		
			if(gaurd == 1)
			{
				
			  try
				{
				  
					String sqlInsert = "insert into Transactions" + " (book_Id,user_Id,member_Id,fees,borrow_Date,due_Date) values (" + book_Id + "," + user_Id + ","+ member_Id+"," +fees+ "," + "'" + borrow_Date+ "'" + ","+ "'" + due_Date + "'"+ ")";
					stmt.executeUpdate(sqlInsert);
				
				} catch(SQLException e)
				{
					
					e.printStackTrace();
				
				} finally
			    {
					
			    	stmt.close();
			    
			    }
			  	
			  return 1; 
		
			}
			else
			{
			
				return 0;
			}
		}

	/**
	 * this method checks the array to see if its in the correct date format
	 * @param testArray
	 * @return
	 */	
	public char[] arrayCheck(char[] testArray)
	{
		
		String date;
		
		while(testArray.length < 10)
		{
			
			date = JOptionPane.showInputDialog(this, "Invalid input please enter date in numerical form in this format(dd mm yyyy)"); 
			
			if(date != null)
			{
				
				testArray = date.toCharArray();
			
			}	
		}
		
		boolean check = true;
		
		while(check)
		{
			
			if((testArray[0] < 48 || testArray[0] > 57) || (testArray[1] < 48 || testArray[1] > 57) || testArray[2] != 32 || (testArray[3] < 48 || testArray[3] > 57) || (testArray[4] < 48 || testArray[4] > 57) || testArray[5] !=32 || (testArray[6] < 48 || testArray[6] > 57) || (testArray[6] < 48 || testArray[6] > 57) || (testArray[7] < 48 || testArray[7] > 57) || (testArray[9] < 48 || testArray[9] > 57))
			{
				
				date = JOptionPane.showInputDialog(this, "Invalid input please enter date in numerical form in this format(dd mm yyyy)"); 
	    		testArray = date.toCharArray();
			
			}
			else 
			{
				
				check = false;
			
			}
		}
		
		return testArray;
	
	}
	
	/**
	 * This method handles refreshing the table
	 */
	public void refreshTable()
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

		table = new BookTable();
		southContainer.add(table,gbc);
		revalidate();
		repaint();
		choice = 5;
	
	}	
}

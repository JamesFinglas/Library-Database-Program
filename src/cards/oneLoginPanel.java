package cards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.awt.CardLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import database.Login;
import executable.BasicStructure;

/**
 * this class creates and manages the login panel
 * @author Cathal Murphy & James FinglasS
 */
public class oneLoginPanel extends JPanel implements ActionListener
{
	
	String username;
	public static int[] loginDetails;
	JLabel error;
	public static JTextField usernameField;
	public static JPasswordField passwordField;
	JButton exitButton,loginButton, resetButton;
	JLabel blank;
	
	GridBagConstraints gbc = new GridBagConstraints();
	
	/**
	 * This method is the constructor for the login JPanel. 
	 */
	public oneLoginPanel()
	{
		
		setLayout(new GridBagLayout());
		Border raisedBorder1 = BorderFactory.createLineBorder(new Color(211,211,211),3);
   		this.setBorder(raisedBorder1);	
		
		Font font1 = new Font("Courier", Font.BOLD,20);
		Font font2 = new Font("Courier", Font.BOLD,15); 
		
   		JLabel welcome = new JLabel("Library Database");
   		welcome.setFont(font1);
   		welcome.setHorizontalAlignment(SwingConstants.CENTER);
   		
   		gbc.gridx = 0;
   		gbc.gridy = 0;
   		gbc.gridwidth = 3;
   		gbc.insets = new Insets(5,5,5,5);
   		gbc.fill = GridBagConstraints.HORIZONTAL;
   		add(welcome,gbc);
   		
   		error = new JLabel("");
   		error.setForeground(Color.blue);
   		error.setFont(font2);
   		gbc.gridx = 0;
   		gbc.gridy = 1;
   		gbc.gridwidth = 3;
   		error.setHorizontalAlignment(SwingConstants.CENTER);
   		add(error,gbc);
  		
   		JLabel welcome2 = new JLabel("Please Login");
   		gbc.gridx = 0;
   		gbc.gridy = 2;
   		gbc.gridwidth = 3;
		welcome2.setHorizontalAlignment(SwingConstants.CENTER);
		welcome2.setFont(font2);
   		add(welcome2,gbc);
   
   		JLabel loginLabel  = new JLabel ("Username: ");
   		gbc.gridx = 0;
   		gbc.gridy = 3;
   		gbc.gridwidth = 1;
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
   		add(loginLabel,gbc);
   		 
   		usernameField  = new JTextField (10);
   		usernameField.setToolTipText("Enter username here");
   		gbc.gridx = 1;
   		gbc.gridy = 3;
   		gbc.gridwidth = 2;
   		add(usernameField,gbc);
   		
   		JLabel loginLabel2  = new JLabel ("Password: ");
   		gbc.gridx = 0;
   		gbc.gridy = 4;
   		gbc.gridwidth = 1;
   		add(loginLabel2,gbc);
   		
   		passwordField  = new JPasswordField (10);
   		passwordField.setEchoChar('*');
   		passwordField.setToolTipText("Enter password here");
   		gbc.gridx = 1;
   		gbc.gridy = 4;
   		gbc.gridwidth = 2;
   		add(passwordField,gbc);
   		
   		exitButton  = new JButton ("Exit");
   		exitButton.setToolTipText("Click here to exit program");
   		gbc.gridx = 0;
   		gbc.gridy = 5;
   		gbc.gridwidth = 1;
		exitButton.addActionListener(this);
   		add(exitButton,gbc);
   		
   		resetButton = new JButton ("Reset");
   		resetButton.setToolTipText("Click here to reset all input fields");
   		gbc.gridx = 1;
   		gbc.gridy = 5;
   		gbc.gridwidth = 1;
		resetButton.addActionListener(this);
   		add(resetButton,gbc);
   		
   		loginButton  = new JButton ("Login");
   		loginButton.setToolTipText("Click here to login");
   		gbc.gridx = 2;
   		gbc.gridy = 5;
   		gbc.gridwidth = 1;
		loginButton.addActionListener(this);
   		add(loginButton,gbc);
   		
   		this.setBackground(new Color(6,152,114));
		setPreferredSize(new Dimension(900,700));
		
		 usernameField.addKeyListener(new KeyAdapter()
		 { 
			
			 public void keyReleased(KeyEvent e1)
			{
				
				if(e1.getKeyCode() == KeyEvent.VK_ENTER)
				{
					
					int counter = 0;
					username = usernameField.getText();
					@SuppressWarnings("deprecation")
					String password = passwordField.getText();
					
					if(username.length() == 0)
					{
						
						counter++;
						if(password.length() == 0)
						{
							
							JOptionPane.showMessageDialog(null, "Username and Password Blank.", "ERROR", JOptionPane.ERROR_MESSAGE);
						
						}
						else
						{
							
							JOptionPane.showMessageDialog(null, "Username Blank.", "ERROR", JOptionPane.ERROR_MESSAGE);
						
						}
					}
					else if(password.length() == 0)
					{
						
						counter++;
						JOptionPane.showMessageDialog(null, "Password Blank.", "ERROR", JOptionPane.ERROR_MESSAGE);
					
					}
					else if(username.length() > 40 || password.length() > 40)
					{
						
						counter ++;
						JOptionPane.showMessageDialog(null, "Username or Password cannot be greater than 40 characters.", "ERROR", JOptionPane.ERROR_MESSAGE);
					
					}
					
					if(counter == 0)
					{
						
						int id = 0;
						int privilege;
						int loginDet[] = {0,0};
						try 
						{
							
							loginDet = Login.verify(username,password);
							id = loginDet[0];
							privilege = loginDet[1];
						
						} catch (ClassNotFoundException e2) 
						{ 
				
							e2.printStackTrace();
						
						}
						
						if(id != 0)
						{
							
							loginDetails = loginDet;
							BasicStructure.gotoCard("2"); // if Success go to next card
							try {
								
								twoMainMenu.changeErrorText();
							
							} catch (ClassNotFoundException | SQLException e3) 
							{
								
								e3.printStackTrace();
							
							}
						}
						else
						{
							
							JOptionPane.showMessageDialog(null, "Incorrect Username/Password.", "ERROR", JOptionPane.ERROR_MESSAGE);

						}	
					}
					
					counter = 0;
				
				}
			}
			public void keyTyped(KeyEvent e1) {			
				//System.out.println("test1");
			}
			public void keyPressed(KeyEvent e1) {			
				//System.out.println("test2");
			}
		});
		
		 passwordField.addKeyListener(new KeyAdapter()
		 { 
			
			 public void keyReleased(KeyEvent e1)
			{
				
				if(e1.getKeyCode() == KeyEvent.VK_ENTER)
				{
					
					int counter = 0;
					username = usernameField.getText();
					@SuppressWarnings("deprecation")
					String password = passwordField.getText();
					
					if(username.length() == 0)
					{
						
						counter++;
						if(password.length() == 0)
						{
							
							JOptionPane.showMessageDialog(null, "Username and Password Blank.", "ERROR", JOptionPane.ERROR_MESSAGE);
						
						}
						else
						{
							
							JOptionPane.showMessageDialog(null, "Username Blank.", "ERROR", JOptionPane.ERROR_MESSAGE);
						
						}
					}
					else if(password.length() == 0)
					{
						
						counter++;
						JOptionPane.showMessageDialog(null, "Password Blank.", "ERROR", JOptionPane.ERROR_MESSAGE);
					
					}
					else if(username.length() > 40 || password.length() > 40)
					{
						
						counter ++;
						JOptionPane.showMessageDialog(null, "Username or Password cannot be greater than 40 characters.", "ERROR", JOptionPane.ERROR_MESSAGE);
					
					}
					
					if(counter == 0)
					{
						
						int id = 0;
						int privilege;
						int loginDet[] = {0,0};
						try 
						{
							
							loginDet = Login.verify(username,password);
							id = loginDet[0];
							privilege = loginDet[1];
						
						} catch (ClassNotFoundException e2) 
						{ 
				
							e2.printStackTrace();
						
						}
						
						if(id != 0)
						{
							
							loginDetails = loginDet;
							BasicStructure.gotoCard("2"); // if Success go to next card
							try {
								
								twoMainMenu.changeErrorText();
							
							} catch (ClassNotFoundException | SQLException e3) 
							{
								
								e3.printStackTrace();
							
							}
						}
						else
						{
							
							JOptionPane.showMessageDialog(null, "Incorrect Username/Password.", "ERROR", JOptionPane.ERROR_MESSAGE);

						}	
					}
					
					counter = 0;
				
				}
			}
			public void keyTyped(KeyEvent e1) {			
				//System.out.println("test1");
			}
			public void keyPressed(KeyEvent e1) {			
				//System.out.println("test2");
			}
		}); 
	} 

	/**
	 * Event Handler for the login Panel.
	 * @param e = Button pressed by user.
	 */
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource() == exitButton)
		{
			
			System.exit(0);
		
		}
		else if(e.getSource() == loginButton)
		{
			
			int counter = 0;
			username = usernameField.getText();
			@SuppressWarnings("deprecation")
			String password = passwordField.getText();
			if(username.length() == 0)
			{
				
				counter++;
				if(password.length() == 0)
				{
					
					JOptionPane.showMessageDialog(null, "Username and Password Blank.", "ERROR", JOptionPane.ERROR_MESSAGE);
				
				}
				else
				{
					
					JOptionPane.showMessageDialog(null, "Username Blank.", "ERROR", JOptionPane.ERROR_MESSAGE);
				
				}
			}
			else if(password.length() == 0)
			{
				
				counter++;
				JOptionPane.showMessageDialog(null, "Password Blank.", "ERROR", JOptionPane.ERROR_MESSAGE);
			
			}
			else if(username.length() > 40 || password.length() > 40)
			{
				
				counter ++;
				JOptionPane.showMessageDialog(null, "Username or Password cannot be greater than 40 characters.", "ERROR", JOptionPane.ERROR_MESSAGE);
			
			}
			
			if(counter == 0)
			{
				
				int id = 0;
				int privilege;
				int loginDet[] = {0,0};
				try 
				{
					
					loginDet = Login.verify(username,password);
					id = loginDet[0];
					privilege = loginDet[1];
				
				} catch (ClassNotFoundException e1) 
				{
					
					e1.printStackTrace();
				
				}
				
				if(id != 0)
				{
					
					loginDetails = loginDet;
					BasicStructure.gotoCard("2"); // if Success go to next card
					try {
						
						twoMainMenu.changeErrorText();
					
					} catch (ClassNotFoundException | SQLException e1) 
					{
						
						e1.printStackTrace();
					
					}
				}
				else
				{
					
					JOptionPane.showMessageDialog(null, "Incorrect Username/Password.", "ERROR", JOptionPane.ERROR_MESSAGE);

				}	
			}
			
			counter = 0;
			
		}
		else if(e.getSource() == resetButton)
		{
			
			usernameField.setText("");
			passwordField.setText("");
		
		}
	}
}
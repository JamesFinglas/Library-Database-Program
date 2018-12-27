package database;
import cards.oneLoginPanel;
import executable.BasicStructure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class handles login/out events
 * @author Cathal Murphy & James Finglas
 */
public class Login 
{
	
	/**
	 * This method validates the username and password against the database to see if they're correct. 
	 * @param username 
	 * @param password
	 * @return LoginDetails, An array that contains user Id and Privilege
	 * @throws ClassNotFoundException
	 */
	public static int[] verify(String username, String password) throws ClassNotFoundException
	{
		
		Statement stmt = null;
		
		try
		{
			
			 stmt = DatabaseConnection.startConnection();
			
			String sqlCheck = "SELECT * FROM Users WHERE user_Name = " + "'" + username + "'" + " AND user_Password = "+ "'"  + password + "'" + ";";
			ResultSet rset = stmt.executeQuery(sqlCheck);
			
			while(rset.next())
			{
				
				int user_Id = rset.getInt("user_Id");
				int user_Privilege = rset.getInt("user_Privilege");
				int loginDetails[] = {user_Id,user_Privilege};
				return loginDetails;
			
			}
		} catch(SQLException e)
		{
			
			e.printStackTrace();
		
		} finally
		{
			
			try {
				
				stmt.close();
			
			} catch (SQLException e) 
			{
				
				e.printStackTrace();
			
			}
		}
		
		int notFound[] = {0,0};
		return notFound;
	}
	
	/**
	 * This method handles logging out and sends the user back to the login screen.
	 */
	public static void logout()
	{
		
		int x = 0;
		oneLoginPanel.loginDetails[0] = x;
		oneLoginPanel.loginDetails[1] = x; 
		BasicStructure.gotoCard("1");
		
	}
	
	/**
	 * Gets user Name to be displayed in a welcome JLabel in Main Menu
	 * @param id
	 * @return currently logged in users name
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static String getName(int id) throws SQLException, ClassNotFoundException
	{
		
		Statement stmt = DatabaseConnection.startConnection();
		
		String sqlCheck = "SELECT * FROM Users WHERE user_Id = " + "'" + id + "'" + ";";
		ResultSet rset = stmt.executeQuery(sqlCheck);
		
		while(rset.next())
		{
			
			String user_Name = rset.getString("user_Name");
			stmt.close();
			return user_Name;
		
		}
		
		stmt.close();
		return "blank";
		
	}
}

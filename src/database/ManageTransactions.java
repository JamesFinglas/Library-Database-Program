package database;

import java.util.*;

import javax.swing.JOptionPane;

import java.sql.*;

import com.mysql.*;

/**
 * This class was used for testing purposes and is now depreciated.
 * Original use was managing transactions and checking format of dates
 * @author Cathal Murphy &James Finglas
 */
public class ManageTransactions
{

	/**
	 * This method handles adding new books
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void insertTransaction(String book_Id, String member_Id, String user_Id, Object frame) throws ClassNotFoundException, SQLException
	{
		
		Statement stmt = DatabaseConnection.startConnection();
		int fees = 0;
		
		String borrow_Date = JOptionPane.showInputDialog(frame, "Please enter the current Date in this format (dd mm yyyy)"); 

		char[] testArray2 =borrow_Date.toCharArray();
		testArray2 = arrayCheck(testArray2, frame);
		
		boolean check3 = true;
		
		while(check3)
		{
			
			for(int i = 0; i < testArray2.length; i++)
			{
				
		    	if(testArray2[i] >=  48 && testArray2[i] <= 57 || testArray2[i] ==  32)
		    	{
		    		
		    		check3 = false;
		        
		    	}
		    	else
		    	{
		    		
		    		borrow_Date = JOptionPane.showInputDialog(frame, "Please enter the current Date in this format (dd mm yyyy)"); 
		    		testArray2 = borrow_Date.toCharArray();
		    		testArray2 = arrayCheck(testArray2, frame);
		    	
		    	}
			}
		}

		String due_Date = JOptionPane.showInputDialog(frame, "Please enter the due Date in this format (dd mm yyyy)"); 
		char[] testArray = due_Date.toCharArray();
		testArray = arrayCheck(testArray, frame);
		boolean check2 = true;
		
		while(check2)
		{
			
			for(int i = 0; i < testArray.length; i++)
			{
				
		    	if(testArray[i] >=  48 && testArray[i] <= 57 || testArray[i] ==  32)
		    	{
		    		
		    		check2 = false;
		        
		    	}
		    	else
		    	{
		    		
		    		due_Date = JOptionPane.showInputDialog(frame, "Please enter the due Date in this format (dd mm yyyy)"); 
		    		testArray = due_Date.toCharArray();
		    		testArray = arrayCheck(testArray, frame);
		    	
		    	}
			}
		}
		
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
	}
	
	/**
	 * This method handles retrieving a transaction
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String getLoanDateDetails(int id) throws ClassNotFoundException, SQLException

	{
		Statement stmt = DatabaseConnection.startConnection();
		String strSelect = "select * from Transactions where book_Id =" + id;
		//System.out.println("The SQL query is: " + strSelect);
		ResultSet rset = stmt.executeQuery(strSelect);
		
			while(rset.next())
			{
				String due_Date = rset.getString("due_Date");
				System.out.println(due_Date);
				stmt.close();
				return due_Date;
			}
			
			stmt.close();
			return "01 01 2000";
			
	}

	
	/**
	 * This method checks to see if the date format is correct.
	 * @param testArray
	 * @param frame
	 * @return
	 */
	public static char[] arrayCheck(char[] testArray, Object frame)
	{
		
		String date;
		
		while(testArray.length > 10)
		{
			
			date = JOptionPane.showInputDialog(frame, "Invalid input please enter date in numerical form in this format(dd mm yyyy)"); 
    		testArray = date.toCharArray();
		}
		
		
		boolean check = true;
		
		while(check)
		{
			
			if(testArray[0] == 32 || testArray[1] == 32 ||testArray[2] != 32 || testArray[3] == 32 || testArray[4] == 32 || testArray[5] !=32 || testArray[6] == 32 || testArray[7] == 32 || testArray[8] == 32 || testArray[9] == 32)
			{
				
				date = JOptionPane.showInputDialog(frame, "Invalid input please enter date in numerical form in this format(dd mm yyyy)"); 
	    		testArray = date.toCharArray();
			
			}
			else 
			{
				check = false;
			}
		}
		
		return testArray;
	}
}

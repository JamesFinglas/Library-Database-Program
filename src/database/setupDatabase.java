package database;
import java.util.*;
import java.sql.*;
import com.mysql.*;

/**
 * This class handles the initial database setup
 * @author James Finglas
 */
public class setupDatabase 
{
	
	/**
	 * This method handles the initial database creation
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void createDatabase() throws ClassNotFoundException, SQLException
	{
		
		Statement stmt1 = DatabaseConnection.startInitialConnection();	
		
		try
		{
			
			String sqlCreate1 = "create database autoindex"; 
			stmt1.executeUpdate(sqlCreate1);
			//String sqlCreate2 = "ALTER DATABASE autoindex CHARACTER SET latin1 COLLATE latin1_general_cs"; 
			//stmt1.executeUpdate(sqlCreate2);
		} catch(SQLException e)
		{
			
			//e.printStackTrace();
		
		}
		
		Statement stmt = DatabaseConnection.startConnection();

		try
		{
			
			String sqlCreate2 =	"use autoindex";
			String sqlCreate3 = "create table IF NOT EXISTS Users(user_Id int auto_increment,user_Name varchar(40),user_Password varchar(40),user_Privilege int,primary key(user_Id));";
			String sqlCreate5 =	"create table IF NOT EXISTS Books(book_Id int auto_increment,title varchar(40),author varchar(40),publisher varchar(40),onLoan varchar(40),primary key(book_Id));";
			String sqlCreate6 = "create table IF NOT EXISTS Members(member_Id int auto_increment,member_Name varchar(40),member_Address varchar(40),member_Fine int,card_Id int,primary key(member_Id));";
			String sqlCreate7 = "create table IF NOT EXISTS Cards(card_Id int auto_increment,member_Id int,member_Name varchar(40),member_Address varchar(40),member_Fine int,primary key(card_Id));";
			String sqlCreate8 =	"create table IF NOT EXISTS Transactions(transaction_Id int auto_increment,book_Id int,user_Id int,member_Id int,fees int,borrow_Date VARCHAR(40),due_Date varchar(40),return_Date varchar(40),primary key(transaction_Id));";
			stmt.execute(sqlCreate2);
			stmt.execute(sqlCreate3);
			stmt.execute(sqlCreate5);
			stmt.execute(sqlCreate6);
			stmt.execute(sqlCreate7);
			stmt.execute(sqlCreate8);

			String sqlSearch = "SELECT * FROM Users WHERE user_Id = 1";
			ResultSet rset = stmt.executeQuery(sqlSearch);

			if(rset.next()== false )
			{
				
					// ADMIN ACCOUNT DO NOT REMOVE
					String sqlCreate4 = "insert into Users(user_Name,user_Password,user_Privilege) values ('admin','admin',1);";
					stmt.execute(sqlCreate4);
					
					/* NON ADMIN ACCOUNT FOR TESTING PURPOSES
					String sqlCreateX = "insert into Users(user_Name,user_Password,user_Privilege) values ('John','pass',0);";
					stmt.execute(sqlCreateX);
					*/
			}
		} catch(SQLException e)
		{
			
			e.printStackTrace();
		
		} finally
		{
			
			stmt1.close();
			stmt.close();	
		
		}	
	}

	/**
	 * This method handles deleting All Tables.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void deleteAllTables() throws ClassNotFoundException, SQLException
	{	
		Statement stmt = DatabaseConnection.startConnection();
		
		String sqlDelete1 = "drop table Cards; ";
		String sqlDelete2 = "drop table Users; ";
		String sqlDelete3 = "drop table Members; ";
		String sqlDelete4 = "drop table Books; ";
		String sqlDelete5 = "drop table Transactions; ";
		
		stmt.execute(sqlDelete1);
		stmt.execute(sqlDelete2);
		stmt.execute(sqlDelete3);
		stmt.execute(sqlDelete4);
		stmt.execute(sqlDelete5);
		
		stmt.close();
	}
}

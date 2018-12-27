package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is the database connection method
 * @author Cathal Murphy & James Finglas
 */
public class DatabaseConnection 
{
	
	/**
	 * This method handles the intial creation of the database and inserts the super admin
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Statement startInitialConnection() throws ClassNotFoundException, SQLException
	{
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?useSSL=false","root","root");
		Statement stmt = conn.createStatement();
		return stmt;	
	
	}
	
	/**
	 * This method starts our connection to database on a per function basis
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Statement startConnection() throws ClassNotFoundException, SQLException
	{
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/autoindex?useSSL=false","root","root");
		Statement stmt = conn.createStatement();
		return stmt;	
	
	}
}

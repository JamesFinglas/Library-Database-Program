package tables;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import cards.threeUsersPanel;

import tables.UserTable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.mysql.jdbc.Statement;

import database.DatabaseConnection;

/**
 * This is a table builder for searching Users by Id
 * @author Cathal Murphy & James Finglas
 */
public class UsersById extends JPanel
{
	
	private static boolean isResultSetEmpty = false;
	private String id = threeUsersPanel.instance.getId();
	JTable table;

		/**
		 * This method is the table constructor
		 */
		public UsersById()
		{
			
			this.setLayout(new GridLayout());
			Font font1 = new Font("Courier", Font.BOLD,17);

			JPanel tableContainer = new JPanel(new GridLayout());
				String[] columnNames = {"ID", "UserName", "Password", "Privilege"};
				
				int gaurd = 0;
				
				try
				{
					
					Object[][] data = userDataById(id);
					gaurd = 1;
					table = new JTable(data, columnNames);
					table.setBackground(new JButton().getBackground());
					JTableHeader header = table.getTableHeader();
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.setFont(font1);
					tableContainer.add(table);
				
				} catch (ClassNotFoundException e) 
				{
					
					e.printStackTrace();
				
				} catch (SQLException e) 
				{
					
					e.printStackTrace();
				
				}
				
				if(gaurd == 0)
				{
					
					Object[][] data = {{"ID", "UserName", "Password", "Privilege"},{"ERROR","ERROR","ERROR","ERROR"}};
					table = new JTable(data, columnNames);
					table.setBackground(new JButton().getBackground());
					JTableHeader header = table.getTableHeader();
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.setFont(font1);
					tableContainer.add(table);

				}
				
				if(isResultSetEmpty)
				{
					
					UserTable instance = new UserTable();
					this.add(instance);
				
				}
				else 
				{
					
					tableContainer.setBackground(new Color(76,156,0));
			   		JScrollPane scrollPane = new JScrollPane(tableContainer,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			   		resizeColumnWidth(table);
			   		this.setPreferredSize(new Dimension(600,200));
			   		this.setBorder(BorderFactory.createEmptyBorder());
					this.add(scrollPane);
				
				}
		}
		
		/**
		 * This method retrieves the data to be put into the table.
		 * @return
		 * @throws ClassNotFoundException
		 * @throws SQLException
		 */
		public static Object[][] userDataById(String id) throws ClassNotFoundException, SQLException
		{
			
			Statement stmt = DatabaseConnection.startConnection();
			String strSelect = "select * from Users WHERE user_Id =" + "'" + id + "'" ;
			ResultSet rset = stmt.executeQuery(strSelect);

			int userCount = 0;
			int rowCount = 0;
			
			while(rset.next())
			{
				
				userCount++;
			
			}
			
			Object[][] users = new Object[userCount+1][4];
			
			while(rset.previous())
			{
				
				// Reset the result set;
			
			}
			
			users[rowCount][0] = "ID";
			users[rowCount][1] = "Username";
			users[rowCount][2] = "Password";
			users[rowCount][3] = "Privilege";
			rowCount++;
			
			while(rset.next())
			{
				
				int user_Id = rset.getInt("user_Id");
				String userName = rset.getString("user_Name");
				String userPassword = rset.getString("user_Password");
				String privilege = rset.getString("user_Privilege");
				users[rowCount][0] = user_Id;
				users[rowCount][1] = userName;
				users[rowCount][2] = userPassword;
				users[rowCount][3] = privilege;
				rowCount++;
			
			}
			
			stmt.close();
			
			if(userCount == 0)
			{
				
				JOptionPane.showMessageDialog(null, "No records found","Result",JOptionPane.INFORMATION_MESSAGE);	
				isResultSetEmpty = true;
			
			}
			
			return users;
		}
		
		/**
		 * This method allows the table columns to stretch to fit its contents
		 * @param table
		 */
		public void resizeColumnWidth(JTable table) 
		{
		    final TableColumnModel columnModel = table.getColumnModel();
		    for (int column = 0; column < table.getColumnCount(); column++) 
		    {
		        int width = 15; // Min width
		        for (int row = 0; row < table.getRowCount(); row++) 
		        {
		        	
		            TableCellRenderer renderer = table.getCellRenderer(row, column);
		            Component comp = table.prepareRenderer(renderer, row, column);
		            width = Math.max(comp.getPreferredSize().width +1 , width);
		            columnModel.getColumn(column).setPreferredWidth(width);
		            
		        
		        }
		 }
	}
}
